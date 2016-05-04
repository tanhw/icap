package com.web.action.sys;

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

import com.business.role.RoleBusiness;
import com.constant.CommonConstant;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.FieldPage;
import com.core.models.TSysmenu;
import com.core.models.TSysrole;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@RequestMapping("/role")
@Controller
public class RoleAction extends BaseAction{

	@Autowired
	private RoleBusiness roleBussiness;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * role首页
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode = "B14000")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String xmlFile = "roleList";
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
	@LogAction(logDesc = "角色分页查询", fieldName = "request")
	public JsonDataWrapper<TSysrole> list(HttpServletRequest request) {

		JsonDataWrapper<TSysrole> res = new JsonDataWrapper<TSysrole>(true,
				RespCodeConstant.Success.toString());

		try {
			res = roleBussiness.list(request);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysrole>(false, e.getMessage());
		}

		return res;
	}

	@RequestMapping("/query.html")
	public String query(HttpServletRequest request) throws Exception {
		
		return "role/roleQuery";
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

			String roleseq = request.getParameter("id");
			
			request.setAttribute("role", SessionHandler.getCurrentRole());
			
			request.setAttribute("unitid", SessionHandler.getCurrentUnitId());

			if(SessionHandler.getUnit() != null) {
				String unitkind = SessionHandler.getUnit().getUnitkind();
				request.setAttribute("unitkind",unitkind);
			}

			// 新增
			if (roleseq == null || roleseq.equals("")) {
				request.setAttribute("isModify", "false");
				return "role/roleDetail";
			}

			roleBussiness.detail(request, roleseq);
			
			// 修改
			request.setAttribute("isModify", "true");

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}

		return "role/roleDetail";
	}
	

	/**
	 * 保存
	 * @ author 许西
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "角色添加或修改", fieldName = "roleseq,rolename")
	public JsonDataWrapper<TSysrole> save(TSysrole role,
			HttpServletRequest request) {

		JsonDataWrapper<TSysrole> res = new JsonDataWrapper<TSysrole>(true, RespCodeConstant.Success.toString());

		try {

			String isModify = request.getParameter("isModify");
			// 添加
			if (isModify.equals("false")) {
				roleBussiness.save(role, request);
			} else {// 修改
				roleBussiness.update(role, request);
			}
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysrole>(false, e.getMessage());
		}
		return res;
	}
	
	
	
	/**
	 * 查询可分配菜单 页面
	 * 
	 * @author 许西
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/allotMenuPage.html")
	public String allotMenuPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleid = request.getParameter("id");
		request.setAttribute("roleid", roleid);
		return "role/roleMenu";
	}

	/**
	 * 查询可分配菜单
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 * @author 许西
	 */
	@ResponseBody
	@RequestMapping("/initMenuUnit.html")
	@LogAction(logDesc = "查询可分配菜单", fieldName = "request")
	public List<TSysmenu> initMenuUnit(HttpServletRequest request) throws Exception {

		List<TSysmenu> menuList;
		try {
			menuList = roleBussiness.initMenuByUnit(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			menuList = new ArrayList<TSysmenu>();
		}
		return menuList;
	}

	/**
	 * 查询可分配菜单
	 *
	 * @author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/roleidAllotMenu.html")
	@LogAction(logDesc = "查询可分配菜单", fieldName = "request")
	public List<TSysmenu> roleAllotMenu(HttpServletRequest request,@RequestParam(value = "roleseq") Long roleseq) throws Exception {
		List<TSysmenu> menuList;
		try {
			menuList = roleBussiness.allMenuByRole(request,roleseq);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			menuList = new ArrayList<TSysmenu>();
		}

		return menuList;
	}

	/**
	 * 保存单位菜单
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/roleMenuSave.html")
	@LogAction(logDesc = "修改角色权限保存", fieldName = "roleseq,rolename")
	public JsonDataWrapper<TSysrole> roleMenuSave(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "ids") String id,
			@RequestParam(value = "roleId") long roleseq) throws Exception {

		JsonDataWrapper<TSysrole> res = new JsonDataWrapper<TSysrole>(true,
				RespCodeConstant.Success.toString());
		String ids = id.replaceAll(" ", "");

		try {
			roleBussiness.roleMenuSave(roleseq, ids);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysrole>(false, e.getMessage());
		}

		return res;
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
	@LogAction(logDesc = "角色删除", fieldName = "roleseq,rolename")
	public JsonDataWrapper<TSysrole> del(HttpServletRequest request,
			HttpServletResponse response)throws Exception {

		JsonDataWrapper<TSysrole> res = new JsonDataWrapper<TSysrole>(true, RespCodeConstant.Success.toString());
		try {
			String roleseq = request.getParameter("id");
			roleBussiness.del(request, roleseq);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysrole>(false, e.getMessage());
		}
		return res;
	}
	
	/**
	 * 角色选择页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/pageCondition.html")
	public String pageCondition(HttpServletRequest request) {

		List<FieldPage> FieldList = new ArrayList<FieldPage>();

		FieldList.add(new FieldPage("角色名称", "pagerolename",
				CommonConstant.Field.Text.toString()));
		FieldList.add(new FieldPage("角色描述", "pageroledesc", CommonConstant.Field.Text
				.toString()));

		
		request.setAttribute("conditionFields", FieldList);

		return "pageCondition";
	}
	
	/**
	 * 选择页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/pageIndex.html")
	public String pageIndex(HttpServletRequest request) {

		String unitFlagRole = request.getParameter("unitFlagRole");
		
		String xmlFile = "roleList";

		List<UiColumn> list = new ArrayList<UiColumn>();
		list.add(new UiColumn("角色名", "rolename"));
		list.add(new UiColumn("描述", "roledesc"));
		list.add(new UiColumn("类型", "roletypeDesc"));

		request.setAttribute("pageColumn", list);
		

		request.setAttribute("pageFiled", "role");
		request.setAttribute("pageParam", UiHandler.getUiListParam(xmlFile));
		
		if(unitFlagRole != null && !unitFlagRole.equals("")){
			request.setAttribute("params", "?unitFlagRole="+unitFlagRole);
		}
		
		return "pageList";
	}
	
}
