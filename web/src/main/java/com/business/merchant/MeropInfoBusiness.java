package com.business.merchant;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.common.PasswordHandler;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.merchant.IMeropInfoService;
import com.core.models.TMeropInfo;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.DateUtil;
import com.toolbox.util.StringUtil;

@Service("MeropInfoBusiness")
public class MeropInfoBusiness {
	
	@Autowired
	private IMeropInfoService meropInfoService;
	
	public JsonDataWrapper<TMeropInfo> list(HttpServletRequest request)throws Exception{
		
		String meropseq = request.getParameter("meropseq");
		String merseq = request.getParameter("merseq");
		String realname = request.getParameter("realname");
		String isactive = request.getParameter("isactive");
		String unitid = request.getParameter("unitid");
		String roeseq = request.getParameter("roeseq");
		
		if(SessionHandler.getCurrentUnitId() != null){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}
		
		if(SessionHandler.getCurrentMerchantId() != null){
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}
		if(isactive == null || isactive.equals("")){
			isactive = CommonConstant.IsActive.True.toString();
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("meropseq", meropseq);
		params.put("merseq", merseq);
		params.put("realname", realname);
		params.put("isactive", isactive);
		params.put("unitid", unitid);
		params.put("roeseq", roeseq);
		
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
		
		
		RollPage<TMeropInfo>  merData = meropInfoService.findListPageByParams(params, order, pageNum, pageSize);
		
		return new JsonDataWrapper<TMeropInfo>(merData);
	}
	
	/**
	 * 商户详细信息
	 * @param request
	 * @param merseq
	 * @throws Exception
	 */
	public void detail(HttpServletRequest request ,String meropseq)throws Exception{
		
		request.setAttribute("isModify", "true");
		
		TMeropInfo meropInfo = meropInfoService.findObjByKey(Long.parseLong(meropseq));
		
		request.setAttribute("meropInfo", meropInfo);
		
	}
	
	/**
	 * 添加
	 * 
	 * @param request
	 * @param tMerchantInfo
	 * @throws Exception
	 */
	public void add(HttpServletRequest request,TMeropInfo meropInfo)throws Exception{
		meropInfo.setLoginpwd(PasswordHandler.getPassword(meropInfo.getLoginpwd()));
		meropInfo.setUnitid(SessionHandler.getCurrentUnitId());
		Map<String,Object> params = new HashMap<String, Object>();
		
		params.put("unitid", SessionHandler.getCurrentUnitId());
		params.put("merseq",meropInfo.getMerseq());
		params.put("loginname",meropInfo.getLoginname());
		
		TMeropInfo obj = meropInfoService.findObj(params);
		if(obj==null){
			meropInfoService.addBasic(meropInfo);
		}else{
			throw new  Exception("E10003");
		}
	}
	
	/**
	 * 修改
	 * 
	 * @param request
	 * @param tMerchantInfo
	 * @throws Exception
	 */
	public void update(HttpServletRequest request,TMeropInfo meropInfo)throws Exception{
		meropInfo.setModifytime(DateUtil.getDate());
		meropInfoService.modifyBasic(meropInfo);
	}
	
	/**
	 * 删除
	 * @param request
	 * @param merseq
	 * @throws Exception
	 */
	public void del(HttpServletRequest request,String meropseq)throws Exception{
		
		TMeropInfo meropInfo = new TMeropInfo();
		meropInfo.setMeropseq(Long.parseLong(meropseq));
		meropInfoService.delBasic(meropInfo);
	}
}
