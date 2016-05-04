package com.business.merchant;

import com.constant.CommonConstant;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.bank.IBanksInfoService;
import com.core.controller.service.black.IBlackInfoService;
import com.core.controller.service.merchant.IMerchantInfoService;
import com.core.controller.service.merchant.IMeropInfoService;
import com.core.controller.service.pos.IPosInfoService;
import com.core.models.*;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service("merchantInfoBusiness")
public class MerchantInfoBusiness {
	
	@Autowired
	private IMerchantInfoService merchantInfoService;
	
	@Autowired
	private IBlackInfoService blackInfoService;
	
	@Autowired
	private IMeropInfoService meropInfoService;
	
	@Autowired
	private IPosInfoService posInfoService;
	
	@Autowired
	private IBanksInfoService banksInfoService;
	
	
	public JsonDataWrapper<TMerchantInfo> list(HttpServletRequest request)throws Exception{
		
		String branchid = request.getParameter("branchid");
		String unitid = request.getParameter("unitid");
		String bankmerid = request.getParameter("bankmerid");
		String branchstate = request.getParameter("branchstate");
		String merseq = request.getParameter("merseq");
		String branchchn = request.getParameter("branchchn");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(SessionHandler.getCurrentUnitId() != null){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}
		
		if(SessionHandler.getCurrentMerchantId() != null){
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}
		
		if (branchstate != null && (!branchstate.equals(""))) {
			params.put("branchstate", branchstate);
		} else {
			params.put("branchstate", CommonConstant.CommState.TRUE.toString());
		}

		params.put("branchid", branchid);
		params.put("bankmerid", bankmerid);
		params.put("unitid", unitid);
		params.put("merseq", merseq);
		params.put("branchchn", branchchn);
		
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
		
		RollPage<TMerchantInfo>  merData = merchantInfoService.findListPageByParams(params, order, pageNum, pageSize);
		
		return new JsonDataWrapper<TMerchantInfo>(merData);
	}
	
	/**
	 * 商户详细信息
	 * @param request
	 * @param merseq
	 * @throws Exception
	 */
	public void detail(HttpServletRequest request ,String merseq)throws Exception{
		
		request.setAttribute("isModify", "true");
		
		TMerchantInfo merInfo = merchantInfoService.findObjByKey(Long.parseLong(merseq));
		
		request.setAttribute("merInfo", merInfo);
		
	}
	
	/**
	 * 添加
	 * 
	 * @param request
	 * @param tMerchantInfo
	 * @throws Exception
	 */
	public void add(HttpServletRequest request,TMerchantInfo tMerchantInfo)throws Exception{
		
		tMerchantInfo.setUnitid(SessionHandler.getCurrentUnitId());
		merchantInfoService.addBasic(tMerchantInfo);
	}
	
	/**
	 * 修改
	 * 
	 * @param request
	 * @param tMerchantInfo
	 * @throws Exception
	 */
	public void update(HttpServletRequest request,TMerchantInfo tMerchantInfo)throws Exception{
		
		String branchid = tMerchantInfo.getBranchid();// 商户编号
		Long merseq = tMerchantInfo.getMerseq();// 商户序号
		
		
		/**
		 * tms绑定表
		 */
		TPosTmsBind tPosTmsBind = new TPosTmsBind();
		tPosTmsBind.setBranchid(branchid);
		tPosTmsBind.setMerseq(Integer.parseInt(merseq.toString()));
		merchantInfoService.modifyTmsBranchidByParams(tPosTmsBind);
		
		/**
		 * tms程序表
		 */
		TPosTmsData tPosTmsData = new TPosTmsData();
		tPosTmsData.setBranchid(branchid);
		tPosTmsData.setMerseq(Integer.parseInt(merseq.toString()));
		merchantInfoService.modifyTmsDataBranchidByParams(tPosTmsData);
		
		/**
		 * 终端密钥
		 */
		TPosKeyInfo tPosKeyInfo = new TPosKeyInfo();
		tPosKeyInfo.setBranchid(branchid);
		tPosKeyInfo.setMerseq(merseq);
		merchantInfoService.modifyKeyBranchidByParams(tPosKeyInfo);
		
		/**
		 * 终端信息
		 */
		TPosInfo tPosInfo = new TPosInfo();
		tPosInfo.setBranchid(branchid);
		tPosInfo.setMerseq(merseq);
		merchantInfoService.modifyPosBranchidByParams(tPosInfo);
		
		merchantInfoService.modifyBasic(tMerchantInfo);
	}
	
	/**
	 * 删除
	 * @param request
	 * @param merseq
	 * @throws Exception
	 */
	public void del(HttpServletRequest request,String merseq)throws Exception{
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merseq", merseq);
		
		
		RollPage<TBlackInfo> blackPage = blackInfoService.findListPageByParams(params, null, 1, 1);
		
		if(blackPage.getRecordSum() != 0){
			throw new Exception("E00202");
		}
		
		RollPage<TMeropInfo> meropPage = meropInfoService.findListPageByParams(params, null, 1, 1);
		
		if(meropPage.getRecordSum() != 0){
			throw new Exception("E80109");
		}
		
		RollPage<TPosInfo> posPage = posInfoService.findListPageByParams(params, null, 1, 1);
		
		if(posPage.getRecordSum() != 0){
			throw new Exception("E00213");
		}
		
		TMerchantInfo tMerchantInfo = new TMerchantInfo();
		tMerchantInfo.setMerseq(Long.parseLong(merseq));
		merchantInfoService.delBasic(tMerchantInfo);
	}

	public void checkRepeat(HttpServletRequest request) throws Exception {


		String branchid = request.getParameter("branchid");

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("branchid", branchid);

		TMerchantInfo tMerchantInfo = merchantInfoService.findObj(params);

		if (tMerchantInfo != null) {
			throw new Exception("E20038");
		}

	}
}
