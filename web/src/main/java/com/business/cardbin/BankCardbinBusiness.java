package com.business.cardbin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.common.SessionHandler;
import com.core.controller.service.cardbin.IBankCardbinService;
import com.core.models.TBankCardbin;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;

@Service
public class BankCardbinBusiness {
	
	@Autowired
	private IBankCardbinService bankCardbinService;
	
	/**
	 * 卡Bin管理分页列表
	 * @param request
	 * @return
	 */
	public JsonDataWrapper<TBankCardbin> list(HttpServletRequest request)throws Exception{
		
		String cardbin = request.getParameter("cardbin");
		String binname = request.getParameter("binname");
		String bankid = request.getParameter("bankid");
		String unitid = request.getParameter("unitid");
		
		
		if(SessionHandler.getCurrentUnitId() != null){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("cardbin", cardbin);
		params.put("binname", binname);
		params.put("bankid", bankid);
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
		
		RollPage<TBankCardbin> binData = bankCardbinService.findListPageByParams(params, order, pageNum, pageSize);
		
		return new JsonDataWrapper<TBankCardbin>(binData);
	}
	
	/**
	 * 详细信息
	 * @param request
	 * @param binseq
	 * @throws Exception
	 */
	public void detail(HttpServletRequest request,String binseq)throws Exception{
		
		request.setAttribute("isModify", "true");
		
		TBankCardbin bankCardbin = bankCardbinService.findObjByKey(Long.parseLong(binseq));
		
		request.setAttribute("bankCardbin", bankCardbin);
		
	}
	
	/**
	 * 添加
	 * 
	 * @param request
	 * @param tBankCardbin
	 * @throws Exception
	 */
	public void add(HttpServletRequest request,TBankCardbin tBankCardbin)throws Exception{
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("unitid", SessionHandler.getCurrentUnitId());
		params.put("cardbin", tBankCardbin.getCardbin());
		
		TBankCardbin obj = bankCardbinService.findObj(params);
		
		if(obj != null ){
			throw new Exception("E20009");
		}
		
		tBankCardbin.setUnitid(SessionHandler.getCurrentUnitId());
		bankCardbinService.addBasic(tBankCardbin);
	}
	
	/**
	 * 修改
	 * 
	 * @param request
	 * @param tBankCardbin
	 * @throws Exception
	 */
	public void update(HttpServletRequest request,TBankCardbin tBankCardbin)throws Exception{
		bankCardbinService.modifyBasic(tBankCardbin);
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param tBankCardbin
	 * @throws Exception
	 */
	public void del(HttpServletRequest request,String binseq)throws Exception{
		TBankCardbin bankCardbin = new TBankCardbin();
		bankCardbin.setBinseq(Long.parseLong(binseq));
		bankCardbinService.delBasic(bankCardbin);
	}
}
