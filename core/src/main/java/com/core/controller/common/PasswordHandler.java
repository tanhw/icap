/**
 * 
 */
package com.core.controller.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.toolbox.sign.Md5;

/**
 * 密码加密类
 * @ author sys
 *
 */
@Component
public class PasswordHandler {

	private @Value("${global.password}") String globalPasswordReset;
	
	private static String passwordReset;
	
	/**
	 * 密码加密
	 * @ author sys
	 * @param password
	 * @return
	 */
	public static String getPassword(String password){
		return Md5.getMD5ofStrByUpperCase("HT"+password+"CS");
	}
	
	/**
	 * 重置密码
	 * @ author sys
	 * @return
	 */
	public static String resetPassword(){
		return getPassword(passwordReset);
	}
	
	@PostConstruct
	private void init(){
		passwordReset=globalPasswordReset;
	}
	
}
