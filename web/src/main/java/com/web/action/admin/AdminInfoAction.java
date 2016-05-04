package com.web.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.admin.AdminBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TAdminInfo;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/admin")
public class AdminInfoAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminBusiness adminBusiness;
	
	/**
	 * 系统管理首页
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode="S12000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmlFile = "adminList";
		List<UiColumn> list = UiHandler.getUiListColumn(xmlFile);
		request.setAttribute("showColumn", list);

		request.setAttribute("childMenu", MenuUtil.getFormatMenu(
				SessionHandler.getCurrentRightCode(),
				SessionHandler.getAllMeun()));
		request.setAttribute("listParam", UiHandler.getUiListParam(xmlFile));
		request.setAttribute("importJs", UiHandler.getUiJs(xmlFile));

		return "queryList";
		
	}
	/**
	 * 系统管理分页列表
	 * @ author 檀海稳、
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.html")
	@LogAction(logDesc = "系统管理分页查询", fieldName = "request")
	public JsonDataWrapper<TAdminInfo> list(HttpServletRequest request){
		
		JsonDataWrapper<TAdminInfo> res = new JsonDataWrapper<TAdminInfo>(true,
				RespCodeConstant.Success.toString());
		
		try {

			res = adminBusiness.list(request);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TAdminInfo>(false,
					e.getMessage());
		}
		return res;
	}
	
	/**
	 * 查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminQuery.html")
	public String query(HttpServletRequest request){
		return "admin/adminQuery";
	}
	
	/**
	 * 详细信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminDetail.html")
	public String detail(HttpServletRequest request) throws Exception{
		
		try {
			String adminSeq = request.getParameter("id");

			if (adminSeq == null || adminSeq.equals("")) {

				request.setAttribute("isModify", "false");
				return "admin/adminDetail";
			}

			adminBusiness.detail(request, adminSeq);
			request.setAttribute("isModify", "true");
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		return "admin/adminDetail";
	}
	
	/**
	 * 保存 修改
	 * 
	 * @param request
	 * @param tAdminInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "系统管理员增加或修改", fieldName = "adminseq,loginname")
	public JsonDataWrapper<TAdminInfo> save(HttpServletRequest request,
			TAdminInfo tAdminInfo) {
		
		JsonDataWrapper<TAdminInfo> res = new JsonDataWrapper<TAdminInfo>(true,
				RespCodeConstant.Success.toString());
		
		try {
			String isModify = request.getParameter("isModify");
			
			if(isModify.equals("false")){
				
				//增加
				adminBusiness.add(request, tAdminInfo);
			}else{
				
				//修改
				adminBusiness.update(request, tAdminInfo);
			}
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TAdminInfo>(false,e.getMessage());
		}
		return res;
	}
	
	/**
	 * 操作员删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/del.html")
	@ResponseBody
	@LogAction(logDesc = "系统管理员删除", fieldName = "adminseq,loginname")
	public JsonDataWrapper<TAdminInfo> del(HttpServletRequest request){
		
		
		JsonDataWrapper<TAdminInfo> res = new JsonDataWrapper<TAdminInfo>(true,
				RespCodeConstant.Success.toString());
		
		try {
			
			String adminseq = request.getParameter("id");
			adminBusiness.del(request, adminseq);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TAdminInfo>(false,e.getMessage());
		}
		return res;
 	}
	
	/**
	 * 密码重置
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/resetpwd.html")
	@ResponseBody
	@LogAction(logDesc = "系统管理员密码重置", fieldName = "adminseq,loginname")
	public JsonDataWrapper<TAdminInfo> setPassWord(HttpServletRequest request) {

		JsonDataWrapper<TAdminInfo> res = new JsonDataWrapper<TAdminInfo>(true,
				RespCodeConstant.Success.toString());

		try {

			String adminseq = request.getParameter("id");

			adminBusiness.setPassWord(request, adminseq);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TAdminInfo>(false, e.getMessage());
		}
		return res;
	}
}
