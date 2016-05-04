package com.business.pos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.common.SessionHandler;
import com.core.controller.service.pos.IPosInfoService;
import com.core.controller.service.pos.IPosTmsBindService;
import com.core.models.TPosInfo;
import com.core.models.TPosTmsBind;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;

@Service("PosTmsBindBusiness")
public class PosTmsBindBusiness {

	@Autowired
	private IPosTmsBindService posTmsBindService;

	@Autowired
	private IPosInfoService posInfoService;

	/**
	 * 分页列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JsonDataWrapper<TPosTmsBind> lsit(HttpServletRequest request)
			throws Exception {

		String unitid = request.getParameter("unitid");
		String merseq = request.getParameter("merseq");
		String branchid = request.getParameter("branchid");
		String posid = request.getParameter("posid");

		if (SessionHandler.getCurrentUnitId() != null) {
			unitid = SessionHandler.getCurrentUnitId().toString();
		}

		if (SessionHandler.getCurrentMerchantId() != null) {
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("merseq", merseq);
		params.put("unitid", unitid);
		params.put("branchid", branchid);
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

		RollPage<TPosTmsBind> posInfoBind = posTmsBindService
				.findListPageByParams(params, order, pageNum, pageSize);

		return new JsonDataWrapper<TPosTmsBind>(posInfoBind);
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param posTmsBind
	 * @throws Exception
	 */
	public void add(HttpServletRequest request, TPosTmsBind posTmsBind,
			String postype,String posbrand) throws Exception {
		String merseq = posTmsBind.getMerseq() != null ? posTmsBind.getMerseq().toString():"";
		
		if (SessionHandler.getCurrentMerchantId() != null) {
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitid", SessionHandler.getCurrentUnitId());
		params.put("merseq", merseq);
		params.put("postype", postype);
		params.put("posbrand", posbrand);

		List<TPosInfo> posInfoList = posInfoService.findListByParams(params,
				null);
		if (posInfoList.size() > 0) {
			posTmsBindService.addBatchObj(posInfoList, SessionHandler
					.getCurrentUnitId().toString(), merseq, posTmsBind.getBranchid(), posTmsBind
					.getFilename());
		}else{
			throw new Exception("E20017");
		}
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param posseq
	 * @throws Exception
	 */
	public void del(HttpServletRequest request, TPosTmsBind posTmsBind,
			String postype,String posbrand) throws Exception {

		String merseq = posTmsBind.getMerseq() != null ? posTmsBind.getMerseq().toString():"";
		
		if (SessionHandler.getCurrentMerchantId() != null) {
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitid", SessionHandler.getCurrentUnitId());
		params.put("merseq", merseq);
		params.put("postype", postype);
		params.put("posbrand", posbrand);

		List<TPosInfo> posInfoList = posInfoService.findListByParams(params, null);
		if (posInfoList.size() > 0) {
			posTmsBindService.delBatchObj(posInfoList);
		}
	}
}
