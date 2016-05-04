/**
 * 
 */
package com.core.controller.interceptor;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.constant.CommonConstant;
import com.core.annotation.LogAction;
import com.core.controller.cache.MessageHandler;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.log.IOpLogService;
import com.core.models.TAdminInfo;
import com.core.models.TMeropInfo;
import com.core.models.TOpLog;
import com.core.models.TUnitAdmin;
import com.toolbox.log.LogUtil;
import com.toolbox.util.DateUtil;



/**
 * 记录日志（拦截器）
 * @ author sys
 *
 */

@Component
@Aspect
public class LogIntercepter {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private IOpLogService logService;
	
	
	/**
	 * 设置 切入点
	 * @ author sys
	 * @param logAction
	 */
	@Pointcut("execution(* com..*.*(..)) && @annotation(logAction)")
	private void logAnnotationMethod(LogAction logAction) {
	}
	
	/**
	 * 正常日志记录
	 * @ author sys
	 * @param joinPoint
	 * @param logAction
	 * @param result
	 */
	@AfterReturning(pointcut="logAnnotationMethod(logAction)")   
	public void doAfterReturning(JoinPoint joinPoint, LogAction logAction) { 
		TOpLog log=new TOpLog();
		
		log.setOptime(DateUtil.getTime());
		log.setOpip(SessionHandler.getCurrentIP());
		log.setLogtype(SessionHandler.getCurrentAction());
		log.setUnitid(SessionHandler.getCurrentUnitId());
		
		try{
			String opType  = SessionHandler.getCurrentOpType();
			if(opType != null && opType.equals(CommonConstant.OperatorType.sys.toString())) {
				TAdminInfo admin = (TAdminInfo)SessionHandler.getCurrentUser();
				log.setOplogname(admin.getLoginname());
				log.setOprealname(admin.getRealname());
			} else if(opType != null && opType.equals(CommonConstant.OperatorType.admin.toString())) {
				TUnitAdmin unitadmin = (TUnitAdmin)SessionHandler.getCurrentUser();
				log.setOplogname(unitadmin.getLoginname());
				log.setOprealname(unitadmin.getRealname());
			}  else if(opType != null && opType.equals(CommonConstant.OperatorType.mer.toString())) {
				TMeropInfo merop = (TMeropInfo)SessionHandler.getCurrentUser();
				log.setOplogname(merop.getLoginname());
				log.setOprealname(merop.getRealname());
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.warn("用户信息无法获取");
			logger.info(LogUtil.getTrace(e));
		}
		
		
		
		String logDesc= logAction.logDesc();
		String fieldNames= logAction.fieldName();
		if(fieldNames.equals("logout")) {
			SessionHandler.clearCurrent();
		}
		
		log.setOpdesc(logDescFormat(logDesc,fieldNames,joinPoint));
		log.setOpflag(CommonConstant.SuccessFlag.Success.toString());
		
		try {
			logService.addBasic(log);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
		}
	}

	/**
	 * 遇到异常操作
	 * @ author sys
	 */
	@AfterThrowing(pointcut="logAnnotationMethod(logAction)",throwing="ex")
	public void exception(JoinPoint joinPoint,LogAction logAction,Exception ex){
		logger.info(LogUtil.getTrace(ex));
		TOpLog log=new TOpLog();
		
		
		log.setOptime(DateUtil.getTime());
		log.setOpip(SessionHandler.getCurrentIP());
		log.setLogtype(SessionHandler.getCurrentAction());
		log.setUnitid(SessionHandler.getCurrentUnitId());
		try{
			String opType  = SessionHandler.getCurrentOpType();
			if(opType.equals(CommonConstant.OperatorType.sys.toString())) {
				TAdminInfo admin = (TAdminInfo)SessionHandler.getCurrentUser();
				log.setOplogname(admin.getLoginname());
				log.setOprealname(admin.getRealname());
			} else if(opType.equals(CommonConstant.OperatorType.admin.toString())) {
				TUnitAdmin unitadmin = (TUnitAdmin)SessionHandler.getCurrentUser();
				log.setOplogname(unitadmin.getLoginname());
				log.setOprealname(unitadmin.getRealname());
				
			} else if(opType.equals(CommonConstant.OperatorType.mer.toString())) {
				TMeropInfo merop = (TMeropInfo)SessionHandler.getCurrentUser();
				log.setOplogname(merop.getLoginname());
				log.setOprealname(merop.getRealname());
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.warn("用户信息无法获取");
			logger.info(LogUtil.getTrace(e));
		}
		
		String logDesc= logAction.logDesc();
		String fieldNames= logAction.fieldName();
		StringBuffer sb=new StringBuffer();
		sb.append(logDescFormat(logDesc,fieldNames,joinPoint))
		  .append(",异常描述：")
		  .append(MessageHandler.getErrorMsgByCode(ex.getMessage()));
		log.setOpdesc(sb.toString());
		log.setOpflag(CommonConstant.SuccessFlag.Error.toString());
		
		try {
			logService.addBasic(log);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
		}
	}

	
	/**
	 * 格式化日志描述
	 * @ author sys
	 * @param logType
	 * @param logDesc
	 * @param fieldNames
	 * @param joinPoint
	 * @return
	 */
	private String logDescFormat(String logDesc,String fieldNames,JoinPoint joinPoint){
		if (fieldNames!=null && !fieldNames.equals("")){			
			String [] fieldName= fieldNames.split(",");
			if(joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				Object obj=joinPoint.getArgs()[0];
				if (obj.getClass().isArray()){
					StringBuilder sb=new StringBuilder();
							
					Object [] beans=(Object []) obj;
					for (Object o : beans) {
						try{
							String temp=logDesc;
							for (String file : fieldName) {
										
								Object val= PropertyUtils.getProperty(o,file);
								temp=temp.replaceAll("\\$"+file, String.valueOf(val));
										
							}
							sb.append(temp);
							sb.append(",");
						}catch(Exception e){
							logger.error("logDesc format error:"+e.getMessage());
						}
					}
					if (sb.length()>0)
						sb.deleteCharAt(sb.length()-1);
					logDesc=sb.toString();
				}else{
					for (String filed : fieldName) {
						try{
							Object val= PropertyUtils.getProperty(obj,filed);
							logDesc = logDesc.replaceAll("\\$"+filed, String.valueOf(val));
						}catch(Exception e){
							logger.error("logDesc format error:"+e.getMessage());
						}
					}
				}
			}
		}
		return logDesc;
	}
}
