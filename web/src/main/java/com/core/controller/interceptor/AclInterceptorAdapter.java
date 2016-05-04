/**
 * 
 */
package com.core.controller.interceptor;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

import com.core.controller.common.SessionHandler;
import com.core.controller.service.unit.IUnitInfoService;
import com.toolbox.log.LogUtil;
import com.toolbox.util.StringUtil;

/**
 * 拦截器
 * 
 * @ author sys
 * 
 */

public class AclInterceptorAdapter extends HandlerInterceptorAdapter {
	
	private static final String SESSION="sessionHandler";

	private
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//业务系统名字
	private @Value("${system.title}") String systemTitle;

	@Autowired
	private IUnitInfoService unitInfoService;
	
	
	/**
	 * 在@Controller方法前进行拦截
	 */
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		session.setAttribute("systemTitle", systemTitle);
		
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String className = handlerMethod.getBean().getClass().getSimpleName();
		String methodName = handlerMethod.getMethod().getName();
		
		
		Map<String, Object> sessionMap=(Map<String, Object>) session.getAttribute(SESSION);
		
		SessionHandler.setCurrentIP(request.getRemoteAddr()) ;
		SessionHandler.setCurrentAction("Action:" + className + " Method:" + methodName);
		
		if(className.indexOf("TabConBasicAction") == -1 && !filterSpecial(request, response)) {
			return false;
		}

		if (className.indexOf("LoginAction")!=-1||className.indexOf("CaptchaAction")!=-1){
			return true;
		}
		if (className.indexOf("VerifyCodeAction")!=-1){
			return true;
		}

		if (sessionMap==null ){
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

			try {
				SessionHandler.clearCurrent();
				String url = "<script>alert('login timeout!');top.location.href='" + basePath
				+ "login.html'</script>";
				response.getWriter().write(url);
			} catch (IOException e) {
				logger.info(LogUtil.getTrace(e));
			}
			return false;
		}
		SessionHandler.setCurrentAll(sessionMap);
		
		
		
		return true;
	}
	
	
	/**
	 * 过滤特殊字符串
	 * @ author sys
	 * 2013-2-21下午12:59:12
	 * @param request
	 * @param response
	 * @return
	 */
	private boolean filterSpecial(HttpServletRequest request,
			HttpServletResponse response) {
		try{
		Map<String, String[]> maps = request.getParameterMap();
		Iterator<Entry<String, String[]>> iterator = maps.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = iterator.next();
			Object value = entry.getValue();
			String valueStr = "";
			if (value instanceof String[]) {
				valueStr = (String) ((String[]) value)[0];
			} else {
				if (value instanceof String) {
					valueStr = value.toString();
				}
			}

			if (!StringUtil.hasFilterSpecial(valueStr)) {
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort() + path + "/";
				String url = "<script>location.href='" + basePath
						+ "error/004.html'</script>";
				response.getWriter().write(url);
				return false;
			}
		}
		}catch(Exception e){
			logger.info(LogUtil.getTrace(e));
			return false;
		}
		return true;
	}

	
	/**
	 * 在Controller方法后进行拦截
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String className = handlerMethod.getBean().getClass().getSimpleName();

		if (className.indexOf("LoginAction")!=-1){
			HttpSession session = request.getSession();
			MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();
			
			String resolvedMethodName = methodNameResolver.getHandlerMethodName(request);
			if (resolvedMethodName.equals("logon")){
				
				Map<String, Object> sessionMap= SessionHandler.getCurrentAll();
				if (sessionMap!=null){
					session.setAttribute(SESSION, sessionMap);
				}
			}
			else if (resolvedMethodName.equals("logout")){
				session.removeAttribute(SESSION);
			} 
			
			SessionHandler.clearCurrent();
			return;
		} 
		
		SessionHandler.clearCurrent();
		
	}

}
