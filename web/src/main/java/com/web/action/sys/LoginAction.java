package com.web.action.sys;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.comm.CommParamsBusiness;
import com.business.login.LoginBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.models.common.JsonDataWrapper;
import com.core.password.RSAUtils;
import com.toolbox.log.LogUtil;

@Controller
@RequestMapping("/")
public class LoginAction {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginBusiness bussiness;
	
	@Autowired
	private CommParamsBusiness commParamsBusiness;

	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request) throws Exception {
		
		commParamsBusiness.selectByUPParam("OPERATORTYPE", request, "operatortypeList");
		
		commParamsBusiness.selectByParam("VERIFYCODE", request, "verifycode");
		
		return "login/login";
	}
	
	@RequestMapping(value = "cardinit.html", method = RequestMethod.GET)
	public String cardinit(HttpServletRequest request) throws Exception {

		return "initcard/applet";
	}
	/**
	 * 登录
	 * @ author 许西
	 * @param loginName
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("logon.html")
	@RightCode(menuCode = "9999")
	@LogAction(logDesc = "登录系统", fieldName = "loginName")
	public JsonDataWrapper<String> login(
			@RequestParam(value = "unitId") String unitId,
			@RequestParam(value = "loginName") String loginName,
			@RequestParam(value = "passWord") String passWord,
			@RequestParam(value = "operatorType") String operatorType,
			HttpServletRequest request) throws Exception {
		JsonDataWrapper<String> res;

		try {
			
			/** 验证用户 确认登录类别 **/
			passWord = RSAUtils.decrypt(passWord, 1).trim();
			res = bussiness.doLoginAndLeftMenu(request, loginName, passWord, operatorType,unitId);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<String>(false, e.getMessage());
		}

		return res;
	}
	
	
	
	/**
	 * 登出
	 * @ author 许西
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("logout.html")
	@RightCode(menuCode = "9999")
	@LogAction(logDesc = "登出系统", fieldName = "logout")
	public JsonDataWrapper<String> logout() throws Exception {
	
		JsonDataWrapper<String> res = new JsonDataWrapper<String>(
				true, RespCodeConstant.Success.toString());
		try {
//			SessionHandler.clearCurrent();
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<String>(false, e.getMessage());
		}

		return res;
	}

	
	
}
