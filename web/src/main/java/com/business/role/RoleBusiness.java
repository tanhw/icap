package com.business.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;

import com.core.controller.service.unit.IUnitInfoService;
import com.core.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.admin.IAdminInfoService;
import com.core.controller.service.menu.ISysMenuService;
import com.core.controller.service.merchant.IMeropInfoService;
import com.core.controller.service.role.IRoleMenuInfoService;
import com.core.controller.service.role.ISysRoleService;
import com.core.controller.service.unit.IUnitAdminService;
import com.core.controller.util.MenuUtil;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.BeanUtil;
import com.toolbox.util.StringUtil;

@Service
public class RoleBusiness {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private IRoleMenuInfoService roleMenuInfoService;

    @Autowired
    private IAdminInfoService adminInfoService;

    @Autowired
    private IMeropInfoService meropInfoService;

    @Autowired
    private IUnitAdminService unitAdminService;

    @Autowired
    private IUnitInfoService unitInfoService;

    /**
     * 列表
     *
     * @param request
     * @return
     * @throws Exception
     * @ author 许西
     */
    public JsonDataWrapper<TSysrole> list(HttpServletRequest request)
            throws Exception {

        String roleseq = request.getParameter("roleseq");
        String rolename = request.getParameter("rolename");
        String roledesc = request.getParameter("roledesc");
        String roletype = request.getParameter("roletype");
        String unitFlagRole = request.getParameter("unitFlagRole");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleseq", roleseq);
        params.put("rolename", rolename);
        params.put("roledesc", roledesc);
        params.put("roletype", roletype);

        Long unitid = SessionHandler.getCurrentUnitId();

        if (unitid == null) {
            params.put("roletype", CommonConstant.RoleType.NormalRole.toString());
        } else {

            TSysrole role = SessionHandler.getCurrentRole();

            if (unitFlagRole != null && unitFlagRole.equals("true")) {
                params.put("roletype", CommonConstant.RoleType.UnitNormalRole.toString());
            }
            if (role.getRoletype().equals(CommonConstant.RoleType.UnitNormalRole.toString()) || (unitFlagRole != null && unitFlagRole.equals("false"))) {

                params.put("roletype", CommonConstant.RoleType.UnitChildNormalRole.toString());
            }
        }
        params.put("unitid", unitid);

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

        RollPage<TSysrole> data = sysRoleService.findListPageByParams(params,
                order, pageNum, pageSize);

        return new JsonDataWrapper<TSysrole>(data);
    }


    /**
     * 展示权限
     *
     * @param roleseq
     * @return
     * @throws Exception
     */
    public List<TSysmenu> allMenuByRole(HttpServletRequest request,Long roleseq) throws Exception {

        String unitid = request.getParameter("unitid");

        TUnitInfo unit;
        if(unitid != null){
            unit = unitInfoService.findObjByKey(Long.parseLong(unitid));
        }else {
            unit = (TUnitInfo) SessionHandler.getUnit();
        }

        List<TSysmenu> roleMenuList = sysMenuService.findSysmenuByRoleid(roleseq, null, null);
        List<TSysmenu> menuList = null;

        /** 分析角色 **/
        TSysrole selectRole = sysRoleService.findObjByKey(roleseq);
        String roleType = selectRole.getRoletype();

        Integer menutype = null;
        if (roleType.equals(CommonConstant.RoleType.SysRole.toString())) {
            menutype = CommonConstant.MenuType.sys.getValue();
        } else if (roleType.equals(CommonConstant.RoleType.NormalRole.toString())) {
            menutype = CommonConstant.MenuType.admin.getValue();
        } else if (roleType.equals(CommonConstant.RoleType.UnitAdminRole.toString())) {
            menutype = CommonConstant.MenuType.adminUnit.getValue();
        } else if (roleType.equals(CommonConstant.RoleType.UnitNormalRole.toString())) {
            menutype = CommonConstant.MenuType.Unit.getValue();
        } else if (roleType.equals(CommonConstant.RoleType.UnitChildNormalRole.toString())) {
            menutype = CommonConstant.MenuType.Mers.getValue();
        }

        /**
         * 如果是系统管理员 可以分配所有菜单 没有当前菜单分配限制
         */
        TSysrole nowRole = SessionHandler.getCurrentRole();

        if (nowRole.getRoletype().equals(CommonConstant.RoleType.SysRole.toString())) {
            Map<String, Object> params = new HashMap<String, Object>();
            Order order = null;
            params.put("menutype", menutype);
            params.put("isactive", CommonConstant.IsActive.True.toString());
            menuList = sysMenuService.findListByParams(params, order);
        } else {

            menuList = sysMenuService.findSysmenuByRoleid(nowRole.getRoleseq(), menutype, unit.getUnitkind());
        }

        if (menuList == null || menuList.size() == 0) {
            throw new Exception("E40005");
        }
        menuList = MenuUtil.getFormatMenu("0", menuList);// 格式化菜单
        MenuUtil.IscheckedMenu(menuList, MenuUtil.formatMenuCodeStr(roleMenuList));// 验证选中项
        menuList = MenuUtil.delNotChildMenu(menuList);//剔除无用字段

        return menuList;
    }


    /**
     * 展示权限
     *
     * @return
     * @throws Exception
     */
    public List<TSysmenu> initMenuByUnit(HttpServletRequest request) throws Exception {

        String unitid = request.getParameter("unitid");

        TUnitInfo unit = unitInfoService.findObjByKey(Long.parseLong(unitid));

        List<TSysmenu> menuList = null;
        int menutype = CommonConstant.MenuType.adminUnit.getValue();

        /**
         * 如果是系统管理员 可以分配所有菜单 没有当前菜单分配限制
         */
        TSysrole nowRole = SessionHandler.getCurrentRole();


        if (nowRole.getRoletype().equals(CommonConstant.RoleType.SysRole.toString())) {
            Map<String, Object> params = new HashMap<String, Object>();
            Order order = null;
            params.put("menutype", menutype);
            menuList = sysMenuService.findListByParams(params, order);
        } else {
            menuList = sysMenuService.findSysmenuByRoleid(nowRole.getRoleseq(), menutype, unit.getUnitkind());
        }

        if (menuList == null || menuList.size() == 0) {
            throw new Exception("E40005");
        }

        menuList = MenuUtil.getFormatMenu("0", menuList);// 格式化菜单
        menuList = MenuUtil.delNotChildMenu(menuList);//剔除无用字段

        return menuList;
    }

    /**
     * 保存菜单
     *
     * @param roleseq
     * @param menuids
     */
    public void roleMenuSave(long roleseq, String menuids) throws Exception {

        List<TSysmenu> allMenuList = null;

        Object operator = SessionHandler.getCurrentUser(); // 获取当前用户
        String operatorType = SessionHandler.getCurrentOpType();

        if (CommonConstant.OperatorType.sys.getValue().equals(operatorType)) { // 管理员登录

            TAdminInfo adminInfo = (TAdminInfo) operator;

            if (adminInfo.getRoeseq().equals(1L)) { //特殊超级管理员查询全部菜单
                Map<String, Object> params = new HashMap<String, Object>();
                Order order = null;
                allMenuList = sysMenuService.findListByParams(params, order);
            } else {
                allMenuList = BeanUtil.deepCopy(SessionHandler.getAllMeun());//获取本身菜单
            }

        } else {
            allMenuList = BeanUtil.deepCopy(SessionHandler.getAllMeun());//获取本身菜单
        }

        List<TSysmenu> roleMenuList = sysMenuService.findSysmenuByRoleid(roleseq, null, null);//查找角色原菜单权限

        String roleMenuCode = MenuUtil.formatMenuCodeStr(roleMenuList); //格式化菜单

        List<String> codeRetain = MenuUtil.compareCodeStr(menuids, roleMenuCode);// 查找被勾选的

        MenuUtil.isRoleMenuUp(roleMenuList, allMenuList, codeRetain);

        List<String> codeRemove = MenuUtil.compareCodeStr(roleMenuCode, menuids);// 查找被去选的

        codeRemove = MenuUtil.orderDesMenuCode(roleMenuList, codeRemove);// 查找被去选的


        if (codeRetain.size() > 0) {
            roleMenuInfoService.addRoleMenuArray(codeRetain, roleseq);//勾选菜单
        }

        if (codeRemove.size() > 0) {
            roleMenuInfoService.delRoleMenuArray(codeRemove, roleseq);//去选菜单
        }

    }


    /**
     * 保存
     *
     * @param request
     * @throws Exception
     * @ author 许西
     */
    public void save(TSysrole role, HttpServletRequest request)
            throws Exception {

        String OperatorType = SessionHandler.getCurrentOpType();

        if (OperatorType.equals(CommonConstant.OperatorType.sys.toString())) {
            role.setRoletype(CommonConstant.RoleType.NormalRole.toString());
        }

        sysRoleService.addBasic(role); // ADD
    }

    /**
     * 更新
     *
     * @param request
     * @throws Exception
     * @ author 许西
     */
    public void update(TSysrole role, HttpServletRequest request)
            throws Exception {
        sysRoleService.modifyBasic(role); // update

    }


    /**
     * 选择详情
     *
     * @param request
     * @throws Exception
     * @ author 许西
     */
    public void detail(HttpServletRequest request, String roleseq)
            throws Exception {

        // 修改
        request.setAttribute("isModify", "true");
        TSysrole sysrole = sysRoleService.findObjByKey(Long.parseLong(roleseq));

        request.setAttribute("sysrole", sysrole);
    }


    /**
     * 选择删除
     *
     * @param request
     * @throws Exception
     * @ author 许西
     */
    public void del(HttpServletRequest request, String roleseq)
            throws Exception {

        /**
         * 检查角色
         */
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleSeq", roleseq);

        RollPage<TRoleMenuInfo> roleMenuPage = roleMenuInfoService.findListPageByParams(params, null, 1, 1);

        if (roleMenuPage.getRecordSum() != 0) {
            throw new Exception("E50301");
        }

        params.clear();
        params.put("roeseq", roleseq);

        Order order = Order.asc("roeseq");

        RollPage<TAdminInfo> adminInfoPage = adminInfoService.findListPageByParams(params, order, 1, 1);

        if (adminInfoPage.getRecordSum() != 0) {
            throw new Exception("E80401");
        }

        RollPage<TMeropInfo> meropInfoPage = meropInfoService.findListPageByParams(params, order, 1, 1);

        if (meropInfoPage.getRecordSum() != 0) {
            throw new Exception("E80109");
        }

        RollPage<TUnitAdmin> unitAdminPage = unitAdminService.findListPageByParams(params, order, 1, 1);

        if (unitAdminPage.getRecordSum() != 0) {
            throw new Exception("E80104");
        }


        /**  删除 **/
        TSysrole obj = new TSysrole();
        obj.setRoleseq(Long.parseLong(roleseq));
        sysRoleService.delBasic(obj);

    }


}
