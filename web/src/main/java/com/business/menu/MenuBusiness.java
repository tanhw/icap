package com.business.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.menu.ISysMenuService;
import com.core.controller.service.role.IRoleMenuInfoService;
import com.core.controller.util.MenuUtil;
import com.core.models.TAdminInfo;
import com.core.models.TSysmenu;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.BeanUtil;
import com.toolbox.util.StringUtil;

@Service
public class MenuBusiness {

	@Autowired
	private ISysMenuService sysmenuService;
	
	@Autowired
	private IRoleMenuInfoService roleMenuInfoService;

	/**
	 * 列表
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JsonDataWrapper<TSysmenu> list(HttpServletRequest request)
			throws Exception {

		String menucode = request.getParameter("menucode");
		String menuname = request.getParameter("menuname");
		String menulevel = request.getParameter("menulevel");
		String upmenu = request.getParameter("upmenu");
		String isactive = request.getParameter("isactive");
		String position = request.getParameter("position");
		String menukind = request.getParameter("menukind");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menucode", menucode);
		params.put("menuname", menuname);
		params.put("menulevel", menulevel);
		params.put("upmenu", upmenu);
		params.put("position", position);
		params.put("menukind", menukind);

		if (isactive != null && !isactive.equals("")) {
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

		RollPage<TSysmenu> data = sysmenuService.findListPageByParams(params,
				order, pageNum, pageSize);

		return new JsonDataWrapper<TSysmenu>(data);
	}

	/**
	 * 选择详情
	 * 
	 * @ author 许西
	 * @param request
	 * @param menucode
	 * @throws Exception
	 */
	public void detail(HttpServletRequest request, String menucode)
			throws Exception {

		// 修改
		request.setAttribute("isModify", "true");
		TSysmenu sysmenu = sysmenuService.findObjByKey(menucode);

		// 查看菜单等级 只有当不是一级菜单的时候才做查询
		String level = sysmenu.getMenulevel();

		if (!level.equals(CommonConstant.MenuLevel.Level1.toString())) {
			String upMenuCode = sysmenu.getUpmenu();
			TSysmenu upSysmenu = sysmenuService.findObjByKey(upMenuCode);

			if(upSysmenu != null){
				request.setAttribute("upMenuList", getMenuListByLevel(upSysmenu.getMenulevel()));
			}
		}

		request.setAttribute("menu", sysmenu);
	}



	/**
	 * 保存
	 * @ author 许西
	 * @param menu
	 * @param request
	 * @throws Exception
	 */
	public void save(TSysmenu menu, HttpServletRequest request)
			throws Exception {
		/** 默认父级别 **/
		if (menu.getUpmenu() == null) {
			menu.setUpmenu("0");
		}
		/** 验证重复code **/
		TSysmenu isMenu = sysmenuService.findObjByKey(menu.getMenucode());
		if (isMenu == null) {
			sysmenuService.addBasic(menu); // ADD
		} else {
			throw new Exception("E40003");
		}

	}

	/**
	 * 更新
	 * @ author 许西
	 * @param menu
	 * @param request
	 * @throws Exception
	 */
	public void update(TSysmenu menu, HttpServletRequest request)
			throws Exception {
		/** 默认父级别 **/
		if (menu.getUpmenu() == null) {
			menu.setUpmenu("0");
		}
		/** 验证code **/
		if (menu.getMenucode().equals(menu.getUpmenu())) {
			throw new Exception("E40001");
		}
		
		int menutype = menu.getMenutype();
		
		if(CommonConstant.MenuType.sys.getValue() == menutype){
			roleMenuInfoService.deleteByParamsRole(menu.getMenucode(), CommonConstant.RoleType.SysRole.getValueInt());
		}
		if(CommonConstant.MenuType.admin.getValue() == menutype){
			roleMenuInfoService.deleteByParamsRole(menu.getMenucode(), CommonConstant.RoleType.NormalRole.getValueInt());
		}
		if(CommonConstant.MenuType.adminUnit.getValue() == menutype){
			roleMenuInfoService.deleteByParamsRole(menu.getMenucode(), CommonConstant.RoleType.UnitAdminRole.getValueInt());
		}
		if(CommonConstant.MenuType.Unit.getValue() == menutype){
			roleMenuInfoService.deleteByParamsRole(menu.getMenucode(), CommonConstant.RoleType.UnitNormalRole.getValueInt());
		}
		if(CommonConstant.MenuType.Mers.getValue() == menutype){
			roleMenuInfoService.deleteByParamsRole(menu.getMenucode(), CommonConstant.RoleType.UnitChildNormalRole.getValueInt());
		}
		
		sysmenuService.modifyBasic(menu); // update

	}
	
	/**
	 * 删除
	 * @ author 许西
	 * @param request
	 */
	public void del(HttpServletRequest request)throws Exception{
		
		String menucode = request.getParameter("id");
		Boolean ischange = sysmenuService.delByRoleMenu(menucode);
		
		if(!ischange){
			throw new Exception("E40006");
		}
	}
	
	
	/**
	 * @ author 许西
	 * @param request
	 * @param levelMenu
	 * @throws Exception
	 */
	public void upMenu(HttpServletRequest request, long levelMenu) throws Exception{
		Long level = levelMenu - 1L;
		request.setAttribute("menuList",getMenuListByLevel(level.toString()));
	}
	
	/**
	 * 根据菜单级别获取 菜单列表
	 * 
	 * @ author 许西
	 * @param level
	 * @return
	 * @throws Exception
	 */
	private List<TSysmenu> getMenuListByLevel(String level) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menulevel", level);
		params.put("isActive", CommonConstant.IsActive.True.toString());

		List<TSysmenu> menuList = sysmenuService.findListByParams(params,
				null);
		
		return menuList;
	}
	
	
}
