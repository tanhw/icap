package com.business.unit;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.common.PasswordHandler;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.unit.IUnitAdminService;
import com.core.models.TUnitAdmin;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.DateUtil;
import com.toolbox.util.StringUtil;


@Service
public class UnitAdminBusiness {
	
	@Autowired
	private IUnitAdminService unitAdminService;
	
	/**
	 * 分页列表
	 * @ author 檀海稳、
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JsonDataWrapper<TUnitAdmin> list(HttpServletRequest request)throws Exception{
		
		String loginname = request.getParameter("loginname");
		String realname = request.getParameter("realname");
		String roeseq = request.getParameter("roeseq");
		String isactive = request.getParameter("isactive");
		String unitid = request.getParameter("unitid");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(SessionHandler.getCurrentUnitId() != null){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}
		
		params.put("loginname", loginname);
		params.put("realname", realname);
		params.put("roeseq", roeseq);
		params.put("unitid",unitid);
		
		if(isactive != null && (!isactive.equals(""))){
			params.put("isactive", isactive);
		}else{
			params.put("isactive", CommonConstant.IsActive.True.toString());
		}
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sortOrder = request.getParameter("order");
		String sortName = request.getParameter("sort");

		Integer pageNum = 1;
		if (page != null)
			pageNum = Integer.parseInt(page);
		Integer pageSize = 20;
		if (rows != null)
			pageSize = Integer.parseInt(rows);

		Order order = null;
		if (StringUtil.checkNull(false, sortOrder, sortName)) {
			if (sortOrder.equals("desc"))
				order = Order.desc(sortName);
			else
				order = Order.asc(sortName);
		}
		
		RollPage<TUnitAdmin> unitData = unitAdminService.findListPageByParams(
				params, order, pageNum, pageSize);
		
		return new JsonDataWrapper<TUnitAdmin>(unitData);
	}
	
	/**
	 * 详细信息
	 * @param request
	 * @param unidadminseq
	 */
	public void detail(HttpServletRequest request, String unidadminseq) throws Exception{
		
		request.setAttribute("isModify", "true");
		
		TUnitAdmin tUnitAdmin = unitAdminService.findObjByKey(Long.parseLong(unidadminseq));

		request.setAttribute("unitAdmin", tUnitAdmin);
	}
	
	/**
	 * 增加
	 * 
	 * @param request
	 * @param tUnitAdmin
	 * @throws Exception
	 */
	public void add(HttpServletRequest request, TUnitAdmin tUnitAdmin)
			throws Exception {
		tUnitAdmin.setLoginpwd(PasswordHandler.getPassword(tUnitAdmin.getLoginpwd()));
		tUnitAdmin.setUnitid(SessionHandler.getCurrentUnitId());
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		params.put("unitid", SessionHandler.getCurrentUnitId());
		params.put("loginname", tUnitAdmin.getLoginname());
		TUnitAdmin obj = unitAdminService.findObj(params);
		if(obj==null){
			unitAdminService.addBasic(tUnitAdmin);
		}else{
			throw new Exception("E10003");
		}		
		
	}
	
	/**
	 * 修改
	 * 
	 * @param request
	 * @param tUnitAdmin
	 * @throws Exception
	 */
	public void update(HttpServletRequest request, TUnitAdmin tUnitAdmin)
			throws Exception {
		tUnitAdmin.setModifytime(DateUtil.getDate());
		unitAdminService.modifyBasic(tUnitAdmin);
	}
	
	/**
	 * 密码重置
	 * @author 许西
	 * @param unitid
	 */
	public void setPassword(String id)throws Exception{
		
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("unitadminseq", id);
			TUnitAdmin unitAdmin = unitAdminService.findObj(params2);
			unitAdmin.setLoginpwd(PasswordHandler.resetPassword());
			unitAdminService.modifyBasic(unitAdmin);
		
	}
	
	/**
	 * 删除
	 * @param request
	 * @param unitadminseq
	 * @throws Exception
	 */
	public void del(HttpServletRequest request,String unitadminseq)throws Exception{
		
		String opType = SessionHandler.getCurrentOpType();
		if(CommonConstant.OperatorType.admin.getValue().equals(opType)){
			TUnitAdmin userObj = (TUnitAdmin)SessionHandler.getCurrentUser();
			if(userObj.getUnitadminseq().toString().equals(unitadminseq)){
				throw new Exception("E00218");
			}
		}
		
		TUnitAdmin tUnitAdmin = new TUnitAdmin();
		tUnitAdmin.setUnitadminseq(Long.parseLong(unitadminseq));
		unitAdminService.delBasic(tUnitAdmin);
		
	}
	
}
