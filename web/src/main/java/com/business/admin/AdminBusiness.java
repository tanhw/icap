package com.business.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.common.PasswordHandler;
import com.core.controller.service.admin.IAdminInfoService;
import com.core.models.TAdminInfo;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.DateUtil;
import com.toolbox.util.StringUtil;


@Service("adminBusiness")
public class AdminBusiness {
	
	@Autowired
	private IAdminInfoService adminInfoService;
	 
	/**
	 * 系统管理分页列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JsonDataWrapper<TAdminInfo> list(HttpServletRequest request)throws Exception {
		
		String loginname = request.getParameter("loginname");
		String realname = request.getParameter("realname");
		String roleseq = request.getParameter("roeseq");
		String isactive = request.getParameter("isactive");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("loginname", loginname);
		params.put("realname", realname);
		params.put("roeseq", roleseq);

		if (isactive != null && (!isactive.equals(""))) {
			params.put("isactive", isactive);
		} else {
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
		
		RollPage<TAdminInfo> adminData = adminInfoService.findListPageByParams(
				params, order, pageNum, pageSize);
		
		return new JsonDataWrapper<TAdminInfo>(adminData);
	}
	
	/**
	 * 详情信息
	 * 
	 * @param request
	 * @param adminSeq
	 */
	public void detail(HttpServletRequest request,String adminSeq)throws Exception{
		
		request.setAttribute("isModify", "true");
		
		TAdminInfo tAdminInfo = adminInfoService.findObjByKey(Long.parseLong(adminSeq));
		
		request.setAttribute("tAdminInfo", tAdminInfo);
		
	}
	
	/**
	 * 增加
	 * 
	 * @param request
	 * @param tAdminInfo
	 * @throws Exception
	 */
	public void add(HttpServletRequest request, TAdminInfo tAdminInfo)
			throws Exception {
		tAdminInfo.setLoginpwd(PasswordHandler.getPassword(tAdminInfo.getLoginpwd()));
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("loginname", tAdminInfo.getLoginname());
		
		TAdminInfo obj =adminInfoService.findObj(params);
		if(obj==null){
			adminInfoService.addBasic(tAdminInfo);
		}else{
			throw new Exception("E10003");
		}
		

	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param tAdminInfo
	 */
	public void update(HttpServletRequest request, TAdminInfo tAdminInfo)
			throws Exception {
		tAdminInfo.setModifytime(DateUtil.getDate());

		String adminseq = tAdminInfo.getAdminseq().toString();

		if(adminseq.equals("2")){
			throw new Exception("E10103");
		}

		adminInfoService.modifyBasic(tAdminInfo);
	}
	
	/**
	 * 操作员删除
	 * @param request
	 * @param adminseq
	 * @throws Exception
	 */
	public void del(HttpServletRequest request,String adminseq)throws Exception{
		
		TAdminInfo tAdminInfo = new TAdminInfo();
		
		if(adminseq.equals("2")){
			throw new Exception("E10101");
		}
		
		tAdminInfo.setAdminseq(Long.parseLong(adminseq));
		adminInfoService.delBasic(tAdminInfo);
	}
	
	/**
	 * 密码重置
	 * @param request
	 * @param adminseq
	 * @throws Exception
	 */
	public void setPassWord(HttpServletRequest request,String adminseq)throws Exception{
		
		TAdminInfo tAdminInfo = new TAdminInfo();
		
		if(adminseq.equals("2")){
			throw new Exception("E10102");
		}
		
		tAdminInfo.setAdminseq(Long.parseLong(adminseq));
		tAdminInfo.setLoginpwd(PasswordHandler.resetPassword());
		
		adminInfoService.modifyBasic(tAdminInfo);
		
	}
}
