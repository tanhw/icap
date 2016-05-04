package com.web.action.unit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.unit.UnitAdminBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TUnitAdmin;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/unitadmin")
public class UnitAdminAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UnitAdminBusiness unitAdminBusiness;
	
	/**
	 * 机构管理员首页
	 */
	@Override
	@RequestMapping("/index.html")
	@RightCode(menuCode="B15000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmlFile = "unitAdminList";
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
	 * 分页列表
	 * 
	 * @ author 檀海稳、
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.html")
	@LogAction(logDesc = "机构管理分页查询", fieldName = "request")
	public JsonDataWrapper<TUnitAdmin> list(HttpServletRequest request,
			HttpServletResponse response) {

		JsonDataWrapper<TUnitAdmin> res = new JsonDataWrapper<TUnitAdmin>(true,
				RespCodeConstant.Success.toString());

		try {
			res = unitAdminBusiness.list(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TUnitAdmin>(false, e.getMessage());
		}
		return res;
	}
	
	/**
	 * 查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/unitadminQuery.html")
	public String query(HttpServletRequest request) {
		
		String unitid = request.getParameter("unitid");
		
		if(SessionHandler.getCurrentUnitId() != null){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}
		
		request.setAttribute("unitid", unitid);
		
		return "unit/unitadminQuery";
	}
	/**
	 * 详细信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/unitadminDetail.html")
	public String detail(HttpServletRequest request)throws Exception{
		
		try {
			String unitAdminSeq = request.getParameter("id");

			if (unitAdminSeq == null || unitAdminSeq.equals("")) {
				request.setAttribute("isModify", "false");
				return "unit/unitadminDetail";
			}

			unitAdminBusiness.detail(request, unitAdminSeq);

			request.setAttribute("isModify", "true");

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		return "unit/unitadminDetail";
	}
	
	/**
	 * 保存 修改
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "机构管理增加或修改", fieldName = "unitadminseq,loginname")
	public JsonDataWrapper<TUnitAdmin> save(HttpServletRequest request,TUnitAdmin tUnitAdmin){
		
		JsonDataWrapper<TUnitAdmin> res = new JsonDataWrapper<TUnitAdmin>(true,
				RespCodeConstant.Success.toString());
		
		try {
			String isModify = request.getParameter("isModify");

			if (isModify.equals("false")) {

				// 增加d
				unitAdminBusiness.add(request, tUnitAdmin);
			} else {
				// 修改
				unitAdminBusiness.update(request, tUnitAdmin);
			}
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			
			res = new JsonDataWrapper<TUnitAdmin>(false,e.getMessage());
		}
		return res;
	}
	
	
	/**
	 * setpassword
	 * 
	 * @ author 许西
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/password.html")
	@LogAction(logDesc = "机构管理员密码重置", fieldName = "menucode,menuname")
	public JsonDataWrapper<TUnitAdmin> password(HttpServletRequest request,
			HttpServletResponse response) {

		JsonDataWrapper<TUnitAdmin> res = new JsonDataWrapper<TUnitAdmin>(true, RespCodeConstant.Success.toString());
		try {
			String id = request.getParameter("id");
			unitAdminBusiness.setPassword(id);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TUnitAdmin>(false, e.getMessage());
		}
		return res;
	}
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del.html")
	@LogAction(logDesc="机构管理员删除",fieldName="unitadminseq,realname")
	public JsonDataWrapper<TUnitAdmin> del(HttpServletRequest request){
		
		JsonDataWrapper<TUnitAdmin> res = new JsonDataWrapper<TUnitAdmin>(true,RespCodeConstant.Success.toString());
		
		try {
			
			String unitadminseq = request.getParameter("id");
			unitAdminBusiness.del(request, unitadminseq);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TUnitAdmin>(false,e.getMessage());
		}
		return res;
	}
}
