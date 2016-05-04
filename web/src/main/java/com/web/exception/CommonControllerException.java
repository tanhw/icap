/**
 * 
 */
package com.web.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.core.controller.cache.MessageHandler;
import com.toolbox.log.LogUtil;

/**
 * 异常处理类
 * 
 * @ author sys
 *
 */

@Controller
public class CommonControllerException implements HandlerExceptionResolver {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerExceptionResolver#resolveException
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * java.lang.Exception)
	 */
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3) {
		// TODO Auto-generated method stub

		Map<String, Object> model = new HashMap<String, Object>();

		String errMsg = arg3.getMessage();

		if (errMsg != null) {
			if (errMsg.equals("E00001"))
				return new ModelAndView("user/login");
		}

		logger.info(errMsg, arg3);

		model.put("ex", arg3.getClass().getSimpleName());
		model.put("error", arg3.getMessage());
		model.put("errorMsg",
				MessageHandler.getErrorMsgByCode(arg3.getMessage()));
		// return new ModelAndView("error", model);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			arg3.printStackTrace(new PrintStream(baos));
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info(LogUtil.getTrace(e));
			}
		}

		model.put("excptionInfoDev", baos.toString());

		return new ModelAndView("common/error", model);
	}

}
