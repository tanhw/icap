package com.web.action.log;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.log.LogBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TOpLog;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/log")
public class OpLogAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogBusiness logBusiness;
	
	/**
	 * 日志管理首页
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode = "B12000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmlFile = "logList";
		List<UiColumn> list = UiHandler.getUiListColumn(xmlFile);
		request.setAttribute("showColumn", list);

		request.setAttribute("childMenu", MenuUtil.getFormatMenu(
				SessionHandler.getCurrentRightCode(),
				SessionHandler.getAllMeun()));
		request.setAttribute("listParam", UiHandler.getUiListParam(xmlFile));
		request.setAttribute("importJs", UiHandler.getUiJs(xmlFile));

		return "queryList";
	}
	
	/**
	 * 分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.html")
	@LogAction(logDesc = "日志分页查询", fieldName = "request")
	public JsonDataWrapper<TOpLog> list(HttpServletRequest request,
			HttpServletResponse response) {
		
		JsonDataWrapper<TOpLog> res = new JsonDataWrapper<TOpLog>(true,
				RespCodeConstant.Success.toString());
		
		try {
			res = logBusiness.list(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TOpLog>(false,e.getMessage());
		}
		
		return res;
		
	}
	
	/**
	 * 查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logQuery.html")
	public String query(HttpServletRequest request) {
		return "log/logQuery";
	}

}
