/**
 * 
 */
package com.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.core.models.*;
import com.core.password.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.comm.CommParamsBusiness;
import com.business.login.LoginBusiness;
import com.business.unit.UnitBusiness;
import com.constant.CommonConstant;
import com.constant.RespCodeConstant;
import com.core.controller.common.PasswordHandler;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.toolbox.util.DateUtil;

/**
 * 框架页面
 * @ author sys
 *
 */
@Controller
@RequestMapping("/")
public class IndexAction {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UnitBusiness unitBusiness;
	
	@Autowired
	private LoginBusiness loginBusiness;
	
	@Autowired
	private CommParamsBusiness commParamsBusiness;
	
	/**
	 * 首页-框架页面
	 * @ author sys
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="index.html")
	public String systemIndexPage(HttpServletRequest request) throws Exception{
		
		commParamsBusiness.selectByUPParam("OPERATORTYPE", request, "operatortypeList");
		
		commParamsBusiness.selectByParam("VERIFYCODE", request, "verifycode");
		
		String opType  = SessionHandler.getCurrentOpType();

		if(opType == null){
			return "login/login";
		} 

		Date lastLoginTime;
		if(opType.equals(CommonConstant.OperatorType.sys.toString())) {
			TAdminInfo admin = (TAdminInfo)SessionHandler.getCurrentUser();
			if(admin==null) return "login/login";
			request.getSession().setAttribute("realName", admin.getRealname());
			request.getSession().setAttribute("loginName", admin.getLoginname());
			String time = DateUtil.formatDate(new Date(), "yyyy-MM-dd EEEE");
			request.getSession().setAttribute("logtime", time);
			request.getSession().setAttribute("loguser", admin);
			lastLoginTime = admin.getLastlogtime();
		} else if(opType.equals(CommonConstant.OperatorType.admin.toString())) {
			TUnitAdmin unitadmin = (TUnitAdmin)SessionHandler.getCurrentUser();
			if(unitadmin==null) return "login/login";
			request.getSession().setAttribute("realName", unitadmin.getRealname());
			request.getSession().setAttribute("loginName", unitadmin.getLoginname());
			String time = DateUtil.formatDate(new Date(), "yyyy-MM-dd EEEE");
			request.getSession().setAttribute("logtime", time);
			request.getSession().setAttribute("loguser", unitadmin);
			lastLoginTime = unitadmin.getLastlogtime();
		}  else if(opType.equals(CommonConstant.OperatorType.mer.toString())) {
			TMeropInfo merop = (TMeropInfo)SessionHandler.getCurrentUser();
			if(merop==null) return "login/login";
			request.getSession().setAttribute("realName", merop.getRealname());
			request.getSession().setAttribute("loginName", merop.getLoginname());
			String time = DateUtil.formatDate(new Date(), "yyyy-MM-dd EEEE");
			request.getSession().setAttribute("logtime", time);
			request.getSession().setAttribute("loguser", merop);
			lastLoginTime = merop.getLastlogtime();
		}else {
			throw new Exception("E20037");
		}

		if(lastLoginTime == null) {
			request.setAttribute("lastLoginTimeFlag", true);
		}else{
			request.setAttribute("lastLoginTimeFlag", false);
		}

			return "index";
		}
	
	
	/**
	 * 我的信息 页面
	 * 
	 * @ author sys
	 * @param request
	 * @return
	 */
	@RequestMapping("myInfo.html")
	public String myInfo(HttpServletRequest request) throws Exception {

		try {

			String opType = SessionHandler.getCurrentOpType();
			Object userObj = SessionHandler.getCurrentUser();
			TSysrole opRole = SessionHandler.getCurrentRole();
			

			/** 区别登录类别 **/

			if (CommonConstant.OperatorType.sys.getValue().equals(opType)) { // 管理员登录
				TAdminInfo admin = (TAdminInfo) userObj;
				request.setAttribute("obj", admin);
			} else if (CommonConstant.OperatorType.admin.getValue().equals(opType)) { // 机构管理员
				TUnitAdmin unitAdmin = (TUnitAdmin) userObj;
				request.setAttribute("obj", unitAdmin);
			} else if (CommonConstant.OperatorType.mer.getValue().equals(opType)) {// 商户管理员
				TMeropInfo merOpInfo = (TMeropInfo) userObj;
				request.setAttribute("obj", merOpInfo);
			}

			request.setAttribute("opType", CommonConstant.OperatorType.getDescByValue(opType));
			request.setAttribute("opRole", opRole);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
		}
		return "common/myInfo";
	}
	
	
	/**
	 * 左边菜单 页面
	 * @ author sys
	 * @param request
	 * @return
	 */
	@RequestMapping(value="leftMenu.html", method=RequestMethod.GET)
	public String leftMenuPage(HttpServletRequest request)throws Exception{
		
	try {
		List<TSysmenu> AllMeun = SessionHandler.getAllMeun();
		
		/** 格式化菜单 **/
		List<TSysmenu> menuList = MenuUtil.getFormatMenu("0", AllMeun );
			
		request.setAttribute("leftMenu", menuList) ;
		
	} catch (Exception e) {
		logger.info(LogUtil.getTrace(e));
	}
		return "leftMenu";
	}
	
	
	/**
	 * 错误 
	 * @param errorCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("error/{errorCode}.html")
	public String error(@PathVariable("errorCode") String errorCode) throws Exception{
		throw new Exception("E00"+errorCode);
	}
	
	/**
	 * 检测是否有效操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isAdminDisabled.html")
	public boolean isAdminDisabled(){
		boolean result = false;
		if(SessionHandler.getCurrentUnitId() != null){
			result = true;
		}
		return result ;
	}
	/**
	 * 检测是否有效操作
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isMersDisabled.html")
	public boolean isMersDisabled(){
		boolean result = false;
		if(!SessionHandler.getCurrentRole().getRoletype().equals(CommonConstant.RoleType.UnitChildNormalRole.toString())){
			result = true;
		}
		return result ;
	}
	
	
	@ResponseBody
	@RequestMapping("/checkReg.html")
	public JsonDataWrapper<TMerchantInfo> checkReg() throws Exception{
		JsonDataWrapper<TMerchantInfo> res = new JsonDataWrapper<TMerchantInfo>(
				true, RespCodeConstant.Success.toString());
		return res;
	}
	
	/**
	 * update password page
	 * @ author 许西
	 * @return
	 */
	@RequestMapping("/myPassword.html")
	public String myPassword(){
		return "common/myPassword";
	}
	
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/savePawd.html")
	public JsonDataWrapper<String> savePawd(HttpServletRequest request) {
		JsonDataWrapper<String> res = new JsonDataWrapper<String>(true,
				RespCodeConstant.Success.toString());

		try {
			String passwd = request.getParameter("password");
			String oldpasswd = request.getParameter("oldpasswd");

			passwd = RSAUtils.decrypt(passwd, 1).trim();
			oldpasswd = RSAUtils.decrypt(oldpasswd, 1).trim();

			loginBusiness.modifyPawd(request, PasswordHandler.getPassword(passwd),PasswordHandler.getPassword(oldpasswd));

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<String>(false,e.getMessage());
		}
		return res;
	}
	
}
