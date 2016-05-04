package com.web.action.menu;

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
import com.business.menu.MenuBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TSysmenu;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/menu")
public class MenuAction extends BaseAction {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MenuBusiness menuBussiness;
	
	@Autowired
	private CommParamsBusiness commParamsBusiness;

	/**
	 * 菜单管理首页
	 * 
	 * @param request
	 * @ author 许西
	 * @return
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode = "S15000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmlFile = "menuList";
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
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/list.html")
	@LogAction(logDesc = "菜单分页查询", fieldName = "request")
	public JsonDataWrapper<TSysmenu> list(HttpServletRequest request) {

		JsonDataWrapper<TSysmenu> res = new JsonDataWrapper<TSysmenu>(true,
				RespCodeConstant.Success.toString());
		try {
			res = menuBussiness.list(request);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysmenu>(false, e.getMessage());
		}

		return res;
	}

	/**
	 * 详情页面 新增页面
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request) throws Exception {

		try {

			String menucode = request.getParameter("id");
			
			commParamsBusiness.selectByUPParam("BUSIKIND", request, "menukindlist");

			// 新增
			if (menucode == null || menucode.equals("")) {
				request.setAttribute("isModify", "false");
				return "menu/menuDetail";
			}

			menuBussiness.detail(request, menucode);
			// 修改
			request.setAttribute("isModify", "true");

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}

		return "menu/menuDetail";
	}

	/**
	 * 条件查询界面
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/menuQuery.html")
	public String query(HttpServletRequest request) throws Exception {
		commParamsBusiness.selectByUPParam("BUSIKIND", request, "menukindlist");
		return "menu/menuQuery";
	}

	/**
	 * 保存
	 * @ author 许西
	 * @param menu
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "菜单增加或修改", fieldName = "menucode,menuname")
	public JsonDataWrapper<TSysmenu> save(TSysmenu menu,
			HttpServletRequest request) {

		JsonDataWrapper<TSysmenu> res = new JsonDataWrapper<TSysmenu>(true,
				RespCodeConstant.Success.toString());

		try {

			String isModify = request.getParameter("isModify");
			// 添加
			if (isModify.equals("false")) {
				menuBussiness.save(menu, request);
			} else {// 修改
				menuBussiness.update(menu, request);
			}
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysmenu>(false, e.getMessage());
		}
		return res;
	}

	/**
	 * 根据不同的菜单级别显示不同的父级菜单
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showUpMenu.html")
	public String showUpMenu(HttpServletRequest request,@RequestParam(value="level") Long menulevel) throws Exception {

		// 监测是否为数字类型
		try {
			menuBussiness.upMenu(request, menulevel);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		return "menu/ajax_upMenu";
	}

	/**
	 * 删除
	 * 
	 * @ author 许西
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del.html")
	@LogAction(logDesc = "菜单删除", fieldName = "menucode,menuname")
	public JsonDataWrapper<TSysmenu> del(HttpServletRequest request,
			HttpServletResponse response) {

		JsonDataWrapper<TSysmenu> res = new JsonDataWrapper<TSysmenu>(true,
				RespCodeConstant.Success.toString());
		try {
			menuBussiness.del(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysmenu>(false, e.getMessage());
		}
		return res;
	}
	


	
}
