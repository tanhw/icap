/**
 * 
 */
package com.core.controller.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;

/**
 * 权限 判断
 * @ author sys
 *
 */

@Component
@Aspect
public class RightIntercepter {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @Value("${global.menuCode.except}") 
	 private String txnCodeExcept;
	
	/**
	 * 
	 * @ author sys
	 * @param rightTxn
	 */
	@Pointcut("execution(* com.web..*.*(..)) && @annotation(rightCode)")
	private void rightCodeAnnotationMethod(RightCode rightCode) {
	}
	
	/**
	 * 
	 * @ author sys
	 */
	@Before("rightCodeAnnotationMethod(rightCode)")
	public void beforeRight(JoinPoint joinPoint,RightCode rightCode) {
		String menuCode=rightCode.menuCode();
		
		if (menuCode==null) throw new RuntimeException("E99990");
		if (txnCodeExcept != null){
			//进行权限判断
			int code = txnCodeExcept.indexOf(menuCode);
			if(code == -1){
				Object op = SessionHandler.getCurrentUser();
				if(op == null) {
					HttpServletRequest request = (HttpServletRequest)joinPoint.getArgs()[0];
					HttpServletResponse response = (HttpServletResponse)joinPoint.getArgs()[1];
					
					String path = request.getContextPath();
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

					try {
						SessionHandler.clearCurrent();
						response.sendRedirect(basePath + "login.html");
					} catch (IOException e) {
						logger.info(e.getMessage());
					}
				}
			}
		}
		
		SessionHandler.setCurrentRightCode(menuCode);
	}
	
}
