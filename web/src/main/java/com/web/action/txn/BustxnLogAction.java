package com.web.action.txn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.controller.service.unit.IUnitInfoService;
import com.core.models.TUnitInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.comm.CommParamsBusiness;
import com.business.txn.BustxnLogBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.toolbox.util.DateUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/bustxnlog")
public class BustxnLogAction extends BaseAction{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BustxnLogBusiness bustxnLogBusiness;
	
	@Autowired
	private CommParamsBusiness commParamsBusiness;

	@Autowired
	private IUnitInfoService unitInfoService;
	
	/**
	 * 脱机交易流水首页
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode="K10000")
	@Override
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmlFile = "bustxnLogList";
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
	 * @param request
	 * @return
	 *//*
	@RequestMapping("/list.html")
	@ResponseBody
	@LogAction(logDesc = "脱机交易流水分页查询", fieldName = "request")
	public JsonDataWrapper<TBustxnLog> list(HttpServletRequest request){
		
		JsonDataWrapper<TBustxnLog> res = new JsonDataWrapper<TBustxnLog>(true,
				RespCodeConstant.Success.toString());
		
		try {
			
			res = bustxnLogBusiness.list(request);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBustxnLog>(false,e.getMessage());
		}
		return res;
	}*/
	
	/**
	 * 脱机交易流水查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/query.html")
	public String query(HttpServletRequest request)throws Exception{

		Map<String, Object> params = new HashMap<String, Object>();

		List<TUnitInfo> unitInfoList = new ArrayList<TUnitInfo>();

		String unitid = SessionHandler.getCurrentUnitId().toString();

		if (unitid != null && !"".equals(unitid)) {

			params.put("parentid", unitid);

			unitInfoList = unitInfoService.findListByParams(params, null);
		}

		commParamsBusiness.selectByUPParam("SETTLESTAT", request,
				"settlestatList");
		commParamsBusiness.selectByUPParam("CRDKIND", request, "crdkindList");
		commParamsBusiness.selectByUPParam("PHSCTYPE", request, "phsctypeList");
		commParamsBusiness.selectByUPParam("BUSI", request, "busiList");
		
		request.setAttribute("startTime", DateUtil.getNowMonth());
		request.setAttribute("endTime", DateUtil.getTime());
		request.setAttribute("unitid", SessionHandler.getCurrentUnitId());
		request.setAttribute("unitInfoList", unitInfoList);
		
		return "txn/txnQuery";
	}
	
	/**
	 * 详情页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request)throws Exception{
		
		String sysdatetime = null;
		String sysseqno = null;
		
		try {

			String seq = request.getParameter("id");

			if (seq != null && (!seq.equals(""))) {
				String[] mainkeyinfo = seq.split(",");

				sysdatetime = mainkeyinfo[0];

				sysseqno = mainkeyinfo[1];
			}
			
			//bustxnLogBusiness.detail(request, sysdatetime, sysseqno);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		return "txn/bustxnLogDetail";
	}
	
	/**
	 * 双击详情页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/showData.html")
	public String showData(HttpServletRequest request) throws Exception {
		String sysdatetime = null;
		String sysseqno = null;
		try {
			String seq = request.getParameter("id");
			if (seq != null && (!seq.equals(""))) {
				String[] mainkeyinfo = seq.split(",");
				sysdatetime = mainkeyinfo[0];
				sysseqno = mainkeyinfo[1];
			}
			//bustxnLogBusiness.detail(request, sysdatetime, sysseqno);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		return "txn/bustxnlogShowDetail";
	}
	
	
	/**
	 * 报表导出
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/expTxnBase.html")
	public void expTxnBase(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {

			//bustxnLogBusiness.expTxnBase(request, response);

		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	
	/**
	 * 卡片消费报表导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/expCardTxnBase.html")
	public void expCardTxnBase(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			//bustxnLogBusiness.expCardTxnBase(request, response);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	
	/**
	 * 脱机交易卡片消费明细流水首页
	 */
	@RequestMapping("/CardIndex.html")
	@RightCode(menuCode="R70000")
	public String CardIndex(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmlFile = "bustxnCardList";
		List<UiColumn> list = UiHandler.getUiListColumn(xmlFile);
		request.setAttribute("showColumn", list);

		request.setAttribute("childMenu", MenuUtil.getFormatMenu(
				SessionHandler.getCurrentRightCode(),
				SessionHandler.getAllMeun()));
		request.setAttribute("listParam", UiHandler.getUiListParam(xmlFile));
		request.setAttribute("importJs", UiHandler.getUiJs(xmlFile));

		return "queryList";
	}
	
/*
	*//**
	 * 卡片消费明细分页列表
	 * @param request
	 * @return
	 *//*
	@RequestMapping("/cardlist.html")
	@ResponseBody
	@LogAction(logDesc = "脱机交易卡片消费明细流水分页查询", fieldName = "request")
	public JsonDataWrapper<TBustxnLog> cardlist(HttpServletRequest request){
		
		JsonDataWrapper<TBustxnLog> res = new JsonDataWrapper<TBustxnLog>(true,
				RespCodeConstant.Success.toString());
		
		try {
			
			res = bustxnLogBusiness.cardlist(request);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBustxnLog>(false,e.getMessage());
		}
		return res;
	}*/
	
	/**
	 * 卡片消费查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/cardQuery.html")
	public String cardQuery(HttpServletRequest request)throws Exception {
		commParamsBusiness.selectByUPParam("PHSCTYPE", request, "phsctypeList");
		commParamsBusiness.selectByUPParam("SETTLESTAT", request,
				"settlestatList");
		commParamsBusiness.selectByUPParam("CRDKIND", request, "crdkindList");
		commParamsBusiness.selectByUPParam("BUSI", request, "busiList");
		request.setAttribute("startTime", DateUtil.getNowMonth());
		request.setAttribute("endTime", DateUtil.getTime());
		
		return "txn/cardQuery";
	}
}
