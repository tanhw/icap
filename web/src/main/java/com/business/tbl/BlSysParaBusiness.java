package com.business.tbl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.core.controller.service.bat.IBatTaskService;
import com.core.models.TBatTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.service.tbl.ISysParaService;
import com.core.models.TSysPara;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;

@Service("blSysParaBusiness")
public class BlSysParaBusiness {
	
	@Autowired
	private ISysParaService blSysParaService;
	@Autowired
	private IBatTaskService iBatTaskService;
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public JsonDataWrapper<TSysPara> list(HttpServletRequest request) throws Exception{
		
		String paraName = request.getParameter("paraName");
		String paraNo = request.getParameter("paraNo");
		String paraValue = request.getParameter("paraValue");
		String recordStat = request.getParameter("recordStat");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("paraName", paraName);
		params.put("paraNo", paraNo);
		params.put("paraValue", paraValue);
		params.put("recordStat", recordStat);
		
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
		
		RollPage<TSysPara> sysParaData = blSysParaService.findListPageByParams(params, order, pageNum, pageSize);
		
		return new JsonDataWrapper<TSysPara>(sysParaData);
		
	}
	
	/**
	 * 页面详情
	 * @param request
	 * @param uname
	 * @param paraName
	 * @param paraNo
	 * @throws Exception
	 */
	public void detail(HttpServletRequest request, String uname,
			String paraName, String paraNo) throws Exception {
		
		request.setAttribute("isModify", "true");
		
		TSysPara tblSysPara = new TSysPara();
		
		tblSysPara.setUname(uname);
		tblSysPara.setParaName(paraName);
		tblSysPara.setParaNo(paraNo);
		
		tblSysPara = blSysParaService.findObjByKey(tblSysPara);
		
		request.setAttribute("tblSysPara", tblSysPara);
	}
	
	/**
	 * 添加
	 * @param request
	 * @param tblSysPara
	 * @throws Exception 
	 */
	public void add(HttpServletRequest request,TSysPara tblSysPara) throws Exception{
		blSysParaService.addBasic(tblSysPara);
	}
	
	/**
	 * 修改
	 * @param request
	 * @param tblSysPara
	 * @throws Exception 
	 */
	public void update(HttpServletRequest request,TSysPara tblSysPara) throws Exception{
		blSysParaService.modifyBasic(tblSysPara);
	}
	
	/**
	 * 删除
	 * @param request
	 * @param uname
	 * @param paraName
	 * @param paraNo
	 * @throws Exception 
	 */
	public void del(HttpServletRequest request,String uname,String paraName, String paraNo) throws Exception{
		
		TSysPara tblSysPara = new TSysPara();
		
		tblSysPara.setUname(uname);
		tblSysPara.setParaName(paraName);
		tblSysPara.setParaNo(paraNo);
		
		blSysParaService.delBasic(tblSysPara);
	}

	public void exceuteTaskSave(HttpServletRequest request, TBatTask tBatTask) throws Exception {

		iBatTaskService.addBasic(tBatTask);

	}
}
