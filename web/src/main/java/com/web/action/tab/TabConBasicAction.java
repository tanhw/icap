package com.web.action.tab;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.comm.CommParamsBusiness;
import com.business.tab.TabConBasicBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TTabCofBasic;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/config")
public class TabConBasicAction extends BaseAction{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TabConBasicBusiness tabConBasicBusiness;
	
	@Autowired
	private CommParamsBusiness commParamsBusiness;
	
	/**
	 * 首页
	 */
	@Override
	@RequestMapping("/index.html")
	@RightCode(menuCode="S50000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmlFile = "tabConfBasicList";
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
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.html")
	@LogAction(logDesc = "商户管理分页查询", fieldName = "request")
	public JsonDataWrapper<TTabCofBasic> list(HttpServletRequest request){
		
		JsonDataWrapper<TTabCofBasic> res = new JsonDataWrapper<TTabCofBasic>(
				true, RespCodeConstant.Success.toString());
		
		try {
			res = tabConBasicBusiness.list(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TTabCofBasic>(false, e.getMessage());
		}
		
		return  res;
		
	}
	
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/query.html")
	public String query(HttpServletRequest request)throws Exception{
		
		commParamsBusiness.selectByUPParam("BUSI", request, "busiList");
		
		return "tab/tabConfBasicQuery";
	}
	
	/**
	 * 详细信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request)throws Exception{
		
		commParamsBusiness.selectByUPParam("BUSI", request, "busiList");
		
		try {
			String confid = request.getParameter("id");

			if (confid == null || confid.equals("")) {

				request.setAttribute("isModify", "false");
				return "tab/tabConfBasicDetail";
			}
			
			tabConBasicBusiness.detail(request, confid);
			
			request.setAttribute("isModify", "true");
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		
		return "tab/tabConfBasicDetail";
	}
	/**
	 * 保存 修改
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "报表配置管理增加或修改", fieldName = "confid,confname")
	public JsonDataWrapper<TTabCofBasic> save(HttpServletRequest request,TTabCofBasic tTabCofBasic){
		
		JsonDataWrapper<TTabCofBasic> res = new JsonDataWrapper<TTabCofBasic>(
				true, RespCodeConstant.Success.toString());
		
		try {
			String isModify = request.getParameter("isModify");

			if (isModify.equals("false")) {

				// 增加
				tabConBasicBusiness.add(request, tTabCofBasic);
			} else {

				// 修改
				tabConBasicBusiness.update(request, tTabCofBasic);
			}

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TTabCofBasic>(false,e.getMessage());
		}
		return res;
	}
	
	/**
	 * 删除
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del.html")
	@LogAction(logDesc = "报表配置管理删除", fieldName = "confid,confname")
	public JsonDataWrapper<TTabCofBasic> del(HttpServletRequest request) {

		JsonDataWrapper<TTabCofBasic> res = new JsonDataWrapper<TTabCofBasic>(
				true, RespCodeConstant.Success.toString());

		try {

			String confid = request.getParameter("id");

			tabConBasicBusiness.del(request, confid);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TTabCofBasic>(false, e.getMessage());
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping("/allConfBasic.html")
	public List<TTabCofBasic> allConfBasic(HttpServletRequest request,@RequestParam(value="unitid")String unitid)
			throws Exception {

		List<TTabCofBasic> confBasiclist;

		try {

			confBasiclist = tabConBasicBusiness.allConfBasic(request,unitid);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			confBasiclist = new ArrayList<TTabCofBasic>();
		}

		return confBasiclist;
	}
	
}
