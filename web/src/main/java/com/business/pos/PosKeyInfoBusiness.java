package com.business.pos;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.common.SessionHandler;
import com.core.controller.service.pos.IPosKeyInfoService;
import com.core.models.TPosKeyInfo;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;

@Service("posKeyInfoBusiness")
public class PosKeyInfoBusiness {
	
	@Autowired
	public IPosKeyInfoService posInfoService;
	
	/**
	 * 终端密钥管理分页列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JsonDataWrapper<TPosKeyInfo> list(HttpServletRequest request) throws Exception{
		
		String unitid = request.getParameter("unitid");
		String branchid = request.getParameter("branchid");
		String merseq = request.getParameter("merseq");
		String posid = request.getParameter("posid");
		
		if(SessionHandler.getCurrentUnitId() != null){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}
		
		if(SessionHandler.getCurrentMerchantId() != null){
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("unitid", unitid);
		params.put("branchid", branchid);
		params.put("merseq", merseq);
		params.put("posid", posid);
		
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
		
		RollPage<TPosKeyInfo> poskeyData = posInfoService.findListPageByParams(
				params, order, pageNum, pageSize);
		
		return new JsonDataWrapper<TPosKeyInfo>(poskeyData);
	}
	
}
