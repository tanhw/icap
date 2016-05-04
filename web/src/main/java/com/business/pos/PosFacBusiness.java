package com.business.pos;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.service.pos.IPosInfoService;
import com.core.controller.service.pos.IPosacService;
import com.core.models.TPosFac;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;

@Service("PosFacBusiness")
public class PosFacBusiness {
	
	@Autowired
	private IPosacService posacService;
	
	@Autowired
	private IPosInfoService posInfoService;
	
	/**
	 * 硬件厂商分页列表
	 * 
	 * @param request
	 * @return
	 */
	public JsonDataWrapper<TPosFac> list(HttpServletRequest request)throws Exception{
		
		String faccode = request.getParameter("faccode");
		String facname = request.getParameter("facname");
		String faccontact = request.getParameter("faccontact");
		String factele = request.getParameter("factele");
		String facmail = request.getParameter("facmail");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("faccode", faccode);
		params.put("facname", facname);
		params.put("faccontact", faccontact);
		params.put("factele", factele);
		params.put("facmail", facmail);
		
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
		
		RollPage<TPosFac> posData = posacService.findListPageByParams(params, order, pageNum, pageSize);
		
		return new JsonDataWrapper<TPosFac>(posData);
		
	}
	
	/**
	 * 详情信息
	 * 
	 * @param request
	 * @param factoryid
	 * @throws Exception
	 */
	public void detail(HttpServletRequest request,String factoryid)throws Exception{
		
		request.setAttribute("isModify", "true");
		
		TPosFac tPosFac = posacService.findObjByKey(Long.parseLong(factoryid));
		
		request.setAttribute("tPosFac", tPosFac);
		
	}
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param tPosFac
	 */
	public void add(HttpServletRequest request,TPosFac tPosFac)throws Exception{
		posacService.addBasic(tPosFac);
	}
	
	/**
	 * 修改
	 * 
	 * @param request
	 * @param tPosFac
	 * @throws Exception
	 */
	public void update(HttpServletRequest request,TPosFac tPosFac)throws Exception{
		posacService.modifyBasic(tPosFac);
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param factoryid
	 */
	public void del(HttpServletRequest request,String factoryid)throws Exception{
		
		TPosFac tPosFac = new TPosFac();
		
		tPosFac.setFactoryid(Long.parseLong(factoryid));
		
		posacService.delBasic(tPosFac);
	}
}
