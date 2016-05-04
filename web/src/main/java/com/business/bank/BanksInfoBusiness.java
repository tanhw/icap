package com.business.bank;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.common.SessionHandler;
import com.core.controller.service.bank.IBanksInfoService;
import com.core.controller.service.cardbin.IBankCardbinService;
import com.core.models.TBankCardbin;
import com.core.models.TBanksInfo;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;

@Service
public class BanksInfoBusiness {
	
	@Autowired
	private IBanksInfoService banksInfoService;
	
	@Autowired
	private IBankCardbinService bankCardbinService;
	
	/**
	 * 分页列表
	 * 
	 * @param request
	 * @return
	 */
	public JsonDataWrapper<TBanksInfo> list(HttpServletRequest request)throws Exception{
		
		String bankid = request.getParameter("bankid");
		String bankcode = request.getParameter("bankcode");
		String bankname = request.getParameter("bankname");
		String unitid = request.getParameter("unitid");
		
		
		if(SessionHandler.getCurrentUnitId() != null){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}
		
		Map<String, Object> params = new HashMap<String,Object>();
		
		params.put("bankid", bankid);
		params.put("bankcode", bankcode);
		params.put("bankname", bankname);
		params.put("unitid", unitid);
		
		
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
		
		RollPage<TBanksInfo> bankData =  banksInfoService.findListPageByParams(params, order, pageNum, pageSize);
		
		return new JsonDataWrapper<TBanksInfo>(bankData);
	}
	
	/**
	 * 详细信息
	 * 
	 * @param request
	 * @param bankid
	 * @throws Exception
	 */
	public void detail(HttpServletRequest request,String bankid)throws Exception{
		
		request.setAttribute("isModify", "true");
		
		TBanksInfo tBanksInfo = banksInfoService.findObjByKey(Long.parseLong(bankid));
		
		request.setAttribute("bankInfo", tBanksInfo);
		
	}
	
	/**
	 * 添加
	 * @param request
	 * @param tBanksInfo
	 * @throws Exception
	 */
	public void add(HttpServletRequest request,TBanksInfo tBanksInfo)throws Exception{
		
		tBanksInfo.setUnitid(SessionHandler.getCurrentUnitId());
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		params.put("unitid", SessionHandler.getCurrentUnitId());
		params.put("bankcode",tBanksInfo.getBankcode());
		TBanksInfo obj = banksInfoService.findObj(params);
		if(obj==null){
			banksInfoService.addBasic(tBanksInfo);
		}else{
			throw new Exception("E50007");
		}
		
		
	}
	/**
	 * 修改
	 * @param request
	 * @param tBanksInfo
	 * @throws Exception
	 */
	public void update(HttpServletRequest request,TBanksInfo tBanksInfo)throws Exception{
		
		banksInfoService.modifyBasic(tBanksInfo);
		
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param bankid
	 * @throws Exception
	 */
	public void del(HttpServletRequest request, String bankid) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bankid", bankid);
		
		Order order = Order.asc("bankid");
		RollPage<TBankCardbin> cardbinPage = bankCardbinService.findListPageByParams(params, order, 1, 1);
		
		if(cardbinPage.getRecordSum() != 0){
			throw new Exception("E00210");
		}
		
		TBanksInfo tBanksInfo = new TBanksInfo();
		tBanksInfo.setBankid(Long.parseLong(bankid));
		banksInfoService.delBasic(tBanksInfo);

	}
}
