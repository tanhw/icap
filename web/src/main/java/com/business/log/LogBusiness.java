package com.business.log;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.common.SessionHandler;
import com.core.controller.service.log.IOpLogService;
import com.core.models.TOpLog;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;

@Service("logBusiness")
public class LogBusiness {
	
	@Autowired
	private IOpLogService opLogService;
	
	/**
	 * 日志管理分页列表
	 * 
	 * @param request
	 * @return
	 */
	public JsonDataWrapper<TOpLog> list(HttpServletRequest request)throws Exception{
		
		String oplogname = request.getParameter("oplogname");
		String oprealname = request.getParameter("oprealname");
		String opflag = request.getParameter("opflag");
		String unitid = request.getParameter("unitid");
		
		Map<String, Object> params = new HashMap<String,Object>();
		
		params.put("oplogname", oplogname);	
		params.put("oprealname", oprealname);
		params.put("opflag", opflag);
		
		if(unitid != null && (!unitid.equals(""))){
			params.put("unitid", unitid);
		}else{
			params.put("unitid", SessionHandler.getCurrentUnitId());
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
		
		
		RollPage<TOpLog> logData = opLogService.findListPageByParams(params,
				order, pageNum, pageSize);
		
		return new JsonDataWrapper<TOpLog>(logData);
	}
	
}
