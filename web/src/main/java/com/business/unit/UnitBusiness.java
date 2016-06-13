package com.business.unit;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.core.controller.service.dict.IDictService;
import com.core.controller.service.unit.ICheckInfoService;
import com.core.models.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.common.PasswordHandler;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.bank.IBanksInfoService;
import com.core.controller.service.black.IBlackInfoService;
import com.core.controller.service.menu.ISysMenuService;
import com.core.controller.service.merchant.IMerchantInfoService;
import com.core.controller.service.role.IRoleMenuInfoService;
import com.core.controller.service.role.ISysRoleService;
import com.core.controller.service.tab.ITabCofBasicService;
import com.core.controller.service.tab.IUnitTabConfService;
import com.core.controller.service.unit.IUnitAdminService;
import com.core.controller.service.unit.IUnitInfoService;
import com.core.controller.util.MenuUtil;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.BeanUtil;
import com.toolbox.util.StringUtil;

@Service
public class UnitBusiness {

    @Autowired
    private IUnitInfoService unitInfoService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private IUnitAdminService unitAdminService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private IRoleMenuInfoService roleMenuInfoService;

    @Autowired
    private IBlackInfoService blackInfoService;

    @Autowired
    private IBanksInfoService banksInfoService;

    @Autowired
    private IUnitTabConfService unitTabConfService;

    @Autowired
    private ICheckInfoService checkInfoService;


    @Autowired
    private IDictService dictService;


    /**
     * 列表
     *
     * @param request
     * @return
     * @throws Exception
     * @ author 许西
     */
    public JsonDataWrapper<TUnitInfo> list(HttpServletRequest request)
            throws Exception {

        String unitname = request.getParameter("unitname");
        String unitid = request.getParameter("unitid");
        String pageFlag = request.getParameter("pageFlag");
        String unitcontact = request.getParameter("unitcontact");
        String unittele = request.getParameter("unittele");
        String unitmail = request.getParameter("unitmail");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("unitname", unitname);
        params.put("unitcontact", unitcontact);
        params.put("unittele", unittele);
        params.put("unitmail", unitmail);


        Long nowunitid = SessionHandler.getCurrentUnitId();

        if(nowunitid == null){

            String unitkind = dictService.findObjByKey("UNITBASICKIND").getCvalue();
            params.put("unitkind", unitkind);
        }

        if (pageFlag != null && pageFlag.equals("unitPage")) {
            params.put("unitid", nowunitid);
        } else {
            params.put("parentid", nowunitid);
            params.put("unitid", unitid);

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

        RollPage<TUnitInfo> data = unitInfoService.findListPageByParams(params,
                order, pageNum, pageSize);
        return new JsonDataWrapper<TUnitInfo>(data);
    }

    /**
     * 选择详情
     *
     * @param request
     * @throws Exception
     * @ author 许西
     */
    public void detail(HttpServletRequest request, String unitid)
            throws Exception {

        // 修改
        request.setAttribute("isModify", "true");
        TUnitInfo unitInfo = unitInfoService.findObjByKey(Long
                .parseLong(unitid));

        request.setAttribute("unit", unitInfo);
    }

    /**
     * 保存
     *
     * @param request
     * @throws Exception
     * @ author 许西
     */
    public void save(TUnitInfo unit, HttpServletRequest request)
            throws Exception {
        /**
         * 验证确认码
         */
        String code = request.getParameter("code");

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        TCheckInfo obj = checkInfoService.findObj(params);
        if (obj == null) {
            throw new Exception("E10008");
        }

        //获取当前机构的父级ID
        Long unitId = SessionHandler.getCurrentUnitId();
        TUnitInfo unitInfo = null;
        if (unitId != null) {
            unitInfo = unitInfoService.findObjByKey(unitId);
            if (null != unitInfo.getParentid()) {
                //拼接创建的机构的父级ID,由当前机构父级ID+当前机构ID
                String newParentId = unitInfo.getParentid() + "," + unitId;
                unit.setParentid(newParentId);
            } else {
                unit.setParentid(unitId.toString());
            }
        }
        unitInfoService.addBasic(unit); // ADD

        //确认码已经使用
        checkInfoService.delBasic(obj);
    }

    /**
     * 更新
     *
     * @param request
     * @throws Exception
     * @ author 许西
     */
    public void update(TUnitInfo unit, HttpServletRequest request)
            throws Exception {
        unitInfoService.modifyBasic(unit); // update
    }


    /**
     * 生成确认码
     *
     * @throws Exception
     * @ author 许西
     */
    public String getCode() throws Exception {

        String code = UUID.randomUUID().toString();

        TCheckInfo obj = new TCheckInfo(code);

        checkInfoService.addBasic(obj);

        return code;
    }


    /**
     * 验证确认码
     *
     * @throws Exception
     * @ author 许西
     */
    public void checkCode(HttpServletRequest request) throws Exception {

        String code = request.getParameter("code");

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);

        TCheckInfo obj = checkInfoService.findObj(params);

        if (obj == null) {
            throw new Exception("E10008");
        }

    }


    /**
     * 查询分配的操作员信息
     *
     * @param unitid
     * @ author 许西
     */
    public void deployDetail(HttpServletRequest request, String unitid) throws Exception {

        Map<String, Object> params1 = new HashMap<String, Object>();
        params1.put("unitid", unitid);
        params1.put("roletype", CommonConstant.RoleType.UnitAdminRole.toString());
        TSysrole role = sysRoleService.findObj(params1);

        if (role != null) {
            Map<String, Object> params2 = new HashMap<String, Object>();
            params2.put("unitid", unitid);
            params2.put("roeseq", role.getRoleseq());
            TUnitAdmin unitAdmin = unitAdminService.findObj(params2);

            request.setAttribute("role", role);
            request.setAttribute("unitAdmin", unitAdmin);
            request.setAttribute("isModify", true);
        }

        request.setAttribute("unitid", unitid);
    }


    /**
     * 更新
     *
     * @throws Exception
     * @ author 许西
     */
    public void deployUnitSave(Long unitid, TUnitAdmin unitAdmin,
                               TSysrole sysrole, String menucode) throws Exception {

        if (sysrole.getRoleseq() != null) {

            /** 角色 操作员更新 **/
            unitInfoService.modifyOpAndRole(unitAdmin, sysrole);

            /** 更新角色权限**/

            menucode = menucode.replaceAll(" ", "");

            List<TSysmenu> allMenuList = null;

            Object operator = SessionHandler.getCurrentUser(); // 获取当前用户
            String operatorType = SessionHandler.getCurrentOpType();
            TSysrole nowRole = SessionHandler.getCurrentRole();
            int menutype = CommonConstant.MenuType.adminUnit.getValue();

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
                allMenuList = sysMenuService.findSysmenuByRoleid(nowRole.getRoleseq(), menutype, null);//获取本身菜单
            }

            List<TSysmenu> roleMenuList = sysMenuService.findSysmenuByRoleid(sysrole.getRoleseq(), null,null);//查找角色原菜单权限(注意)

            String roleMenuCode = MenuUtil.formatMenuCodeStr(roleMenuList); //格式化菜单

            List<String> codeRetain = MenuUtil.compareCodeStr(menucode, roleMenuCode);// 查找被勾选的

            MenuUtil.isRoleMenuUp(roleMenuList, allMenuList, codeRetain);

            List<String> codeRemove = MenuUtil.compareCodeStr(roleMenuCode, menucode);// 查找被去选的

            codeRemove = MenuUtil.orderDesMenuCode(roleMenuList, codeRemove);// 查找被去选的

            if (codeRetain.size() > 0) {
                roleMenuInfoService.addRoleMenuArray(codeRetain, sysrole.getRoleseq());//勾选菜单
            }

            if (codeRemove.size() > 0) {
                roleMenuInfoService.delRoleMenuArray(codeRemove, sysrole.getRoleseq());//去选菜单
            }

        } else if (sysrole.getRoleseq() == null && unitAdmin.getUnitadminseq() == null) {
            String[] menucodeList = menucode.split(",");
            unitAdmin.setUnitid(unitid);
            sysrole.setUnitid(unitid);
            sysrole.setRoletype(CommonConstant.RoleType.UnitAdminRole.toString());
            unitAdmin.setLoginpwd(PasswordHandler.getPassword(unitAdmin
                    .getLoginpwd()));
            unitInfoService.addUnitByadmin(unitAdmin, sysrole, menucodeList);
        } else {
            throw new Exception("E60001");
        }

    }

    /**
     * 选择删除
     *
     * @param request
     * @throws Exception
     * @ author 许西
     */
    public void del(HttpServletRequest request, String unitid) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("unitid", unitid);

        Order order = Order.asc("unitid");

        RollPage<TBlackInfo> blackPage = blackInfoService.findListPageByParams(params, order, 1, 1);

        if (blackPage.getRecordSum() != 0) {
            throw new Exception("E00202");
        }

        RollPage<TBanksInfo> bankInfo = banksInfoService.findListPageByParams(params, order, 1, 1);

        if (bankInfo.getRecordSum() != 0) {
            throw new Exception("E00219");
        }


        /** 删除 **/
        TUnitInfo obj = new TUnitInfo();
        obj.setUnitid(Long.parseLong(unitid));
        unitInfoService.delBasic(obj);

    }

    public void regist(HttpServletRequest request, String unitid)
            throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("unitid", unitid);

        TUnitInfo tUnitInfo = unitInfoService.findObj(params);

        request.setAttribute("tUnitInfo", tUnitInfo);
    }

    /**
     * 机构报表配置绑定
     *
     * @param @param  request
     * @param @param  unitid
     * @param @throws Exception
     * @return
     * @throws
     * @author tanhw
     * @date 2015年8月14日 下午5:23:12
     */
    public void bindConfByunitSave(HttpServletRequest request, String unitid,String confides) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("unitid", unitid);
        List<TUnitTabConf> unitTabConf = unitTabConfService.findListByParams(params, null);

        String tempid  ="";
        for (TUnitTabConf temp : unitTabConf) {
            tempid += (temp.getConfid().toString() + ",");
        }

        String[] confs = confides.split(","); //前台权限
        String[] tempids = tempid.split(","); //后台原本权限

        List<String> set = new ArrayList<>(); //勾选
        List<String> out = new ArrayList<>(); //去选

        //比较新勾选的
        for (String temp : confs) {
            boolean result = tempid.indexOf(temp) == -1;
            if (result) {
                set.add(temp);
            }
        }

        //比较去选的
        for (String temp : tempids) {
            boolean result = confides.indexOf(temp) == -1;
            if (result) {
                out.add(temp);
            }
        }


        //add
        for (String id : set) {
            TUnitTabConf obj = new TUnitTabConf();
            obj.setConfid(Integer.parseInt(id));
            obj.setUnitid(Integer.parseInt(unitid));
            unitTabConfService.addBasic(obj);
        }

        //del
        for (String id : out) {
            params.put("confid", id);
            TUnitTabConf obj = unitTabConfService.findObj(params);
            unitTabConfService.delBasic(obj);
        }

    }

    /**
     * 检查重复
     *
     * @param @param  request
     * @param @param  unitid
     * @param @throws Exception
     * @return
     * @throws
     * @author tanhw
     * @date 2015年8月21日 下午3:42:07
     */
    public void checkRepeat(HttpServletRequest request) throws Exception {

        String unitid = request.getParameter("unitid");

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("unitid", unitid);

        TUnitInfo tUnitInfo = unitInfoService.findObj(params);

        if (tUnitInfo != null) {
            throw new Exception("E20032");
        }
    }
}
