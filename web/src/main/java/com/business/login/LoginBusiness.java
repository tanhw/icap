package com.business.login;

import com.business.comm.CommParamsBusiness;
import com.constant.CommonConstant;
import com.core.controller.common.PasswordHandler;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.admin.IAdminInfoService;
import com.core.controller.service.menu.ISysMenuService;
import com.core.controller.service.merchant.IMeropInfoService;
import com.core.controller.service.role.ISysRoleService;
import com.core.controller.service.unit.IUnitAdminService;
import com.core.controller.service.unit.IUnitInfoService;
import com.core.models.*;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginBusiness {

    @Autowired
    private IAdminInfoService adminInfoService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private IUnitAdminService unitAdminService;

    @Autowired
    private IUnitInfoService unitInfoService;

    @Autowired
    private IMeropInfoService meropInfoService;

    @Autowired
    private CommParamsBusiness commParamsBusiness;

    /**
     * 列表
     *
     * @param request
     * @param userName
     * @param userPwd
     * @param userType
     * @param unit_Id
     * @return
     * @throws Exception
     * @ author 许西
     */
    public JsonDataWrapper<String> doLoginAndLeftMenu(
            HttpServletRequest request, String userName, String userPwd,
            String userType, String unit_Id) throws Exception {


        TDict isVerfycode = commParamsBusiness.selectByParam("VERIFYCODE");

        if (isVerfycode != null && isVerfycode.getCvalue().equals("true")) {
            String veryCode = request.getParameter("veryCode");
            //验证码信息
            String validateC = (String) request.getSession().getAttribute(
                    "validateCode");
            if (veryCode == null || veryCode.equals("")) {
                return new JsonDataWrapper<String>(false, "E00009");
            } else {
                if (!veryCode.equalsIgnoreCase(validateC)) {
                    return new JsonDataWrapper<String>(false, "E00010");
                }
            }
        }

        //登录信息
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loginname", userName);

        /** set  IP now **/
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0
                || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0
                || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0
                || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        List<TSysmenu> leftMenu = null; // 右边菜单，准加载
        Long roleSeq = null;
        String unitkind = null;

        /** 区别登录类别 **/

        if (CommonConstant.OperatorType.sys.getValue().equals(userType)) { // 管理员登录
            TAdminInfo adminInfo = adminInfoService.findObj(params);

            if (adminInfo == null) {//  账户不存在
                return new JsonDataWrapper<String>(false, "E10001");
            }

            String isactive = adminInfo.getIsactive();
            if (isactive == "1" || isactive.equals("1")) {
                throw new Exception("E20039");
            }

            Date lockTime = adminInfo.getLocklogtime();
            //如果锁定时间等于空，或者小于当前时间，则可正常验证信息


            if (lockTime != null && Long.parseLong(DateUtil.formatDate(lockTime, "yyyyMMddHHmmss")) - Long.parseLong(DateUtil.formatDate(DateUtil.getDate(), "yyyyMMddHHmmss")) > 0) {
                return new JsonDataWrapper<String>(false, "E10005");
            }


            if (!adminInfo.getLoginpwd().equals(PasswordHandler.getPassword(userPwd))) {

                Integer num = adminInfo.getPwderrnum();
                if (num != null && num >= 4) {
                    adminInfo.setPwderrnum(0);
                    adminInfo.setLocklogtime(new Date(DateUtil.getDate().getTime() + 1800000));
                } else {
                    adminInfo.setPwderrnum(num == null ? 1 : ++num);
                }
                adminInfoService.modifyBasic(adminInfo);
                return new JsonDataWrapper<String>(false, "E10011");
            }

            /** 修改最后一次登录信息 **/
            adminInfo.setLastlogip(ip);

            if (adminInfo.getLastlogtime() != null) {
                adminInfo.setLastlogtime(adminInfo.getNowlogtime());
            }

//			adminInfo.setLastlogtime(adminInfo.getNowlogtime());
            adminInfo.setNowlogtime(DateUtil.getDate());
            adminInfo.setPwderrnum(0);
            adminInfoService.modifyBasic(adminInfo);

            SessionHandler.setCurrentUser(adminInfo);// 设置此操作员
            roleSeq = adminInfo.getRoeseq();

        } else if (CommonConstant.OperatorType.admin.getValue()
                .equals(userType)) { // 机构管理员
            //添加机构号(机构号为页面上送)
            params.put("unitid", Long.valueOf(unit_Id));
            TUnitAdmin tUnitAdmin = unitAdminService.findObj(params);

            if (tUnitAdmin == null) {
                params.clear();
                params.put("unitid", Long.valueOf(unit_Id));
                TUnitInfo tUnitInfo = unitInfoService.findObj(params);
                if (tUnitInfo == null) {
                    return new JsonDataWrapper<String>(false, "E10007");
                } else {
                    return new JsonDataWrapper<String>(false, "E10001");
                }
            }

            String isactive = tUnitAdmin.getIsactive();
            if (isactive == "1" || isactive.equals("1")) {
                throw new Exception("E20039");
            }

            Date locktime = tUnitAdmin.getLocklogtime();

            if (locktime != null
                    && Long.parseLong(DateUtil.formatDate(locktime,
                    "yyyyMMddHHmmss"))
                    - Long.parseLong(DateUtil.formatDate(
                    DateUtil.getDate(), "yyyyMMddHHmmss")) > 0) {
                return new JsonDataWrapper<String>(false, "E10005");
            }

            if (!tUnitAdmin.getLoginpwd().equals(PasswordHandler.getPassword(userPwd))) {

                Integer errNum = tUnitAdmin.getPwderrnum();

                if (errNum != null && errNum >= 4) {
                    tUnitAdmin.setPwderrnum(0);
                    tUnitAdmin.setLocklogtime(new Date(DateUtil.getDate().getTime() + 1800000));
                } else {
                    tUnitAdmin.setPwderrnum(errNum == null ? 1 : ++errNum);
                }

                unitAdminService.modifyBasic(tUnitAdmin);
                return new JsonDataWrapper<String>(false, "E10011");

            }

            /** 修改最后一次登录thing **/
            tUnitAdmin.setLastlogip(ip);

            if (tUnitAdmin.getLastlogtime() != null) {
                tUnitAdmin.setLastlogtime(tUnitAdmin.getNowlogtime());
            }

            tUnitAdmin.setNowlogtime(DateUtil.getDate());
            tUnitAdmin.setPwderrnum(0);
            unitAdminService.modifyBasic(tUnitAdmin);

            SessionHandler.setCurrentUser(tUnitAdmin);
            roleSeq = tUnitAdmin.getRoeseq();

            TUnitInfo tUnitInfo = unitInfoService.findObjByKey(Long
                    .parseLong(unit_Id));

            if (tUnitInfo != null) {// 无机构
                unitkind = tUnitInfo.getUnitkind();
            } else {
                throw new Exception("E20023");
            }

        } else if (CommonConstant.OperatorType.mer.getValue().equals(userType)) {// 商户管理员
            //添加机构号(机构号为页面上送)
            params.put("unitid", Long.valueOf(unit_Id));
            TMeropInfo merop = meropInfoService.findObj(params);
            if (merop == null) {
                return new JsonDataWrapper<String>(false, "E10001");
            }

            String isactive = merop.getIsactive();
            if (isactive == "1" || isactive.equals("1")) {
                throw new Exception("E20039");
            }

            Date locktime = merop.getLocklogtime();

            if (locktime != null
                    && Long.parseLong(DateUtil.formatDate(locktime,
                    "yyyyMMddHHmmss"))
                    - Long.parseLong(DateUtil.formatDate(
                    DateUtil.getDate(), "yyyyMMddHHmmss")) > 0) {
                return new JsonDataWrapper<String>(false, "E10005");
            }

            // 输入密码如果与当前密码不一致,判断错误次数,超过4次,冻结30分钟
            if (!merop.getLoginpwd().equals(
                    PasswordHandler.getPassword(userPwd))) {

                Integer errNum = merop.getPwderrnum();

                if (errNum != null && errNum >= 4) {
                    merop.setPwderrnum(0);
                    merop.setLocklogtime(new Date(DateUtil.getDate().getTime() + 1800000));
                } else {
                    merop.setPwderrnum(errNum == null ? 1 : ++errNum);
                }

                meropInfoService.modifyBasic(merop);
                return new JsonDataWrapper<String>(false, "E10011");
            }

            /** 修改最后一次登录thing **/
            merop.setLastlogip(ip);
            if (merop.getLastlogtime() != null) {
                merop.setLastlogtime(merop.getNowlogtime());
            }

            merop.setNowlogtime(DateUtil.getDate());
            merop.setPwderrnum(0);
            meropInfoService.modifyBasic(merop);

            SessionHandler.setCurrentUser(merop);
            roleSeq = merop.getRoeseq();
            SessionHandler.setCurrentMerchantId(merop.getMerseq());

        }
        if (roleSeq == null) {
            return new JsonDataWrapper<String>(false, "E10006");
        }



        leftMenu = menuService.findSysmenuByRole(roleSeq, null, unitkind);
        TSysrole sysrole = sysRoleService.findObjByKey(roleSeq);

        if (sysrole == null) {
            throw new Exception("E20019");
        }

        Long unitId = sysrole.getUnitid();
        if (unitId != null && unitId != 0) {
            TUnitInfo unit = unitInfoService.findObjByKey(unitId);
            SessionHandler.setCurrentUnitId(unitId);// 机构ID
            SessionHandler.setUnit(unit);// 机构ID
        }

        /** 设置登录信息 **/
        String hostIp = request.getRemoteAddr();//
        SessionHandler.setAllMeun(leftMenu);// 设置登录的左边菜单
        SessionHandler.setCurrentIP(hostIp);// 设置登录的IP
        SessionHandler.setCurrentOpType(userType);// 操作员类型
        SessionHandler.setCurrentRole(sysrole);// 操作员角色
        SessionHandler.setCurrentLoginTime(new Date());

        return new JsonDataWrapper<String>(true, null);

    }


    /**
     * 列表
     *
     * @return
     * @throws Exception
     * @ author 许西
     */
    public JsonDataWrapper<String> modifyPawd(HttpServletRequest request, String passwod, String oldpasswd) throws Exception {


        String opType = SessionHandler.getCurrentOpType();
        Object userObj = SessionHandler.getCurrentUser();

        /** 区别登录类别 **/

        if (CommonConstant.OperatorType.sys.getValue().equals(opType)) { // 管理员登录
            TAdminInfo admin = (TAdminInfo) userObj;

            String adminPasswd = admin.getLoginpwd();

            if (adminPasswd != oldpasswd && !adminPasswd.equals(oldpasswd)) {
                throw new Exception("E20036");
            }

            if (admin.getLastlogtime() == null) {
                admin.setLastlogtime(admin.getNowlogtime());
            }

            admin.setLoginpwd(passwod);
            adminInfoService.modifyBasic(admin);
        } else if (CommonConstant.OperatorType.admin.getValue().equals(opType)) { // 机构管理员
            TUnitAdmin unitAdmin = (TUnitAdmin) userObj;

            String unitPasswd = unitAdmin.getLoginpwd();

            if (unitPasswd != oldpasswd && !unitPasswd.equals(oldpasswd)) {
                throw new Exception("E20036");
            }

            if (unitAdmin.getLastlogtime() == null) {
                unitAdmin.setLastlogtime(unitAdmin.getNowlogtime());
            }

            unitAdmin.setLoginpwd(passwod);
            unitAdminService.modifyBasic(unitAdmin);
        } else if (CommonConstant.OperatorType.mer.getValue().equals(opType)) {// 商户管理员
            TMeropInfo merOpInfo = (TMeropInfo) userObj;

            String merPasswd = merOpInfo.getLoginpwd();

            if (merPasswd != oldpasswd && !merPasswd.equals(oldpasswd)) {
                throw new Exception("E20036");
            }

            if (merOpInfo.getLastlogtime() == null) {
                merOpInfo.setLastlogtime(merOpInfo.getNowlogtime());
            }

            merOpInfo.setLoginpwd(passwod);
            meropInfoService.modifyBasic(merOpInfo);
        }

        return new JsonDataWrapper<String>(true, null);
    }

}
