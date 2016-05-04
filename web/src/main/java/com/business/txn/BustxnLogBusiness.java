package com.business.txn;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.core.controller.common.SessionHandler;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.DateUtil;
import com.toolbox.util.StringUtil;

@Service("bustxnLogBusiness")
public class BustxnLogBusiness {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 脱机交易流水分页列表
	 * 
	 * @param request
	 * @return
	 */
	/*public JsonDataWrapper<TBustxnLog> list(HttpServletRequest request)
			throws Exception {

		String settlestat = request.getParameter("settlestat");
		String unitid = request.getParameter("unitid");
		String merseq = request.getParameter("merseq");
		String posid = request.getParameter("posid");
		String posupseq = request.getParameter("posupseq");
		String busid = request.getParameter("busid");
		String linecode = request.getParameter("linecode");
		String samid = request.getParameter("samid");
		String citycode = request.getParameter("citycode");
		String areaid = request.getParameter("areaid");
		String busiid = request.getParameter("busiid");
		String cardno = request.getParameter("cardno");
		String cardcode = request.getParameter("cardcode");
		String cardkind = request.getParameter("cardkind");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String cardmodel = request.getParameter("cardmodel");
		String branchid = request.getParameter("branchid");
		String sonunitid = request.getParameter("sonunitid");

		if (sonunitid != null && !"".equals(sonunitid)) {

			unitid = sonunitid;
		} else {
			if (SessionHandler.getCurrentUnitId() != null) {
				unitid = SessionHandler.getCurrentUnitId().toString();
			}
		}

		if (SessionHandler.getCurrentMerchantId() != null) {
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("settlestat", settlestat);
		params.put("unitid", unitid);
		params.put("merseq", merseq);
		params.put("posid", posid);
		params.put("posupseq", posupseq);
		params.put("busid", busid);
		params.put("linecode", linecode);
		params.put("samid", samid);
		params.put("citycode", citycode);
		params.put("areaid", areaid);
		params.put("busiid", busiid);
		params.put("cardno", cardno);
		params.put("cardcode", cardcode);
		params.put("cardkind", cardkind);
		params.put("cardmodel", cardmodel);
		params.put("branchid", branchid);

		String nowDate = DateUtil.formatDate(DateUtil.getDate(), "yyyyMMdd");
		if (startTime != null) {
			params.put("start", DateUtil.formatDate(startTime,
					"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
		} else {
			params.put("start", nowDate + "000000");
		}

		if (endTime != null) {
			params.put("end", DateUtil.formatDate(endTime,
					"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
		} else {
			params.put("end", nowDate + "235959");
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

		RollPage<TBustxnLog> bustxnData = bustxnLogService
				.findListPageByParams(params, order, pageNum, pageSize);
		return new JsonDataWrapper<TBustxnLog>(bustxnData);
	}

	*//**
	 * 脱机交易流水分页列表
	 * 
	 * @param request
	 * @return
	 *//*
	public JsonDataWrapper<TBustxnLog> cardlist(HttpServletRequest request)
			throws Exception {

		String settlestat = request.getParameter("settlestat");
		String unitid = request.getParameter("unitid");
		String merseq = request.getParameter("merseq");
		String branchid = request.getParameter("branchid");
		String posid = request.getParameter("posid");
		String busid = request.getParameter("busid");
		String linecode = request.getParameter("linecode");
		String samid = request.getParameter("samid");
		String citycode = request.getParameter("citycode");
		String areaid = request.getParameter("areaid");
		String busiid = request.getParameter("busiid");
		String cardno = request.getParameter("cardno");
		String cardkind = request.getParameter("cardkind");
		String direction = request.getParameter("direction");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String cardmodel = request.getParameter("cardmodel");

		if (SessionHandler.getCurrentUnitId() != null) {
			unitid = SessionHandler.getCurrentUnitId().toString();
		}

		if (SessionHandler.getCurrentMerchantId() != null) {
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("settlestat", settlestat);
		params.put("unitid", unitid);
		params.put("merseq", merseq);
		params.put("branchid", branchid);
		params.put("posid", posid);
		params.put("busid", busid);
		params.put("linecode", linecode);
		params.put("samid", samid);
		params.put("citycode", citycode);
		params.put("areaid", areaid);
		params.put("busiid", busiid);
		params.put("cardno", cardno);
		params.put("cardkind", cardkind);
		params.put("direction", direction);
		params.put("cardmodel", cardmodel);

		String nowDate = DateUtil.formatDate(DateUtil.getDate(), "yyyyMMdd");
		if (startTime != null) {
			params.put("start", DateUtil.formatDate(startTime,
					"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
		} else {
			params.put("start", nowDate + "000000");
		}

		if (endTime != null) {
			params.put("end", DateUtil.formatDate(endTime,
					"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
		} else {
			params.put("end", nowDate + "235959");
		}

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		Integer pageNum = 1;
		if (page != null)
			pageNum = Integer.parseInt(page);
		Integer pageSize = 20;
		if (rows != null)
			pageSize = Integer.parseInt(rows);

		RollPage<TBustxnLog> bustxnData = bustxnLogService.findListCardCount(
				params, pageNum, pageSize);
		return new JsonDataWrapper<TBustxnLog>(bustxnData);
	}

	*//**
	 * 页面详情
	 * 
	 * @param request
	 * @param sysdatetime
	 * @param sysseqno
	 *//*
	public void detail(HttpServletRequest request, String sysdatetime,
			String sysseqno) throws Exception {

		request.setAttribute("isModify", "true");

		TBustxnLog tBustxnLog = new TBustxnLog();

		tBustxnLog.setSysdatetime(sysdatetime);
		tBustxnLog.setSysseqno(sysseqno);

		tBustxnLog = bustxnLogService.findObjByKey(tBustxnLog);

		request.setAttribute("tBustxnLog", tBustxnLog);

	}

	*//**
	 * 报表导出
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	public void expTxnBase(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String unitid = request.getParameter("unitid");
		String merseq = request.getParameter("merseq");
		String cardno = request.getParameter("cardno");
		String samid = request.getParameter("samid");
		String busid = request.getParameter("busid");
		String linecode = request.getParameter("linecode");
		String areaid = request.getParameter("areaid");
		String posid = request.getParameter("posid");
		String branchid = request.getParameter("branchid");
		String settlestat = request.getParameter("settlestat");
		String busiid = request.getParameter("busiid");
		String crdkind = request.getParameter("crdkind");
		String cardmodel = request.getParameter("cardmodel");

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");

		if (SessionHandler.getCurrentUnitId() != null) {
			unitid = SessionHandler.getCurrentUnitId().toString();
		}

		if (SessionHandler.getCurrentMerchantId() != null) {
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}

		Map<String, Object> params = new HashMap<String, Object>();

		// 编写xls
		response.reset();
		response.setContentType("application/msexcel");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition",
				"attachment;  filename=\"" + startTime + "-" + endTime
						+ URLEncoder.encode("TxnData.xls", "utf-8") + "\"");

		Resource res = new ClassPathResource("reportDemo/txnQuery.xls");

		params.put("unitid", unitid);
		params.put("merseq", merseq);
		params.put("cardno", cardno);
		params.put("samid", samid);
		params.put("busid", busid);
		params.put("linecode", linecode);
		params.put("areaid", areaid);
		params.put("posid", posid);
		params.put("branchid", branchid);
		params.put("settlestat", settlestat);
		params.put("busiid", busiid);
		params.put("crdkind", crdkind);
		params.put("cardmodel", cardmodel);
		String nowDate = DateUtil.formatDate(DateUtil.getDate(), "yyyyMMdd");
		if (startTime != null) {
			params.put("start", DateUtil.formatDate(startTime,
					"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
		} else {
			params.put("start", nowDate + "000000");
		}

		if (endTime != null) {
			params.put("end", DateUtil.formatDate(endTime,
					"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
		} else {
			params.put("end", nowDate + "235959");
		}

		List<TBustxnLog> bustxnlogList = bustxnLogService
				.findListByParamsCount(params);

		int countSum = bustxnlogList.size();// 交易总笔数

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("bustxnlogList", bustxnlogList);
		dataMap.put("countSum", countSum);

		InputStream in = null;
		HSSFWorkbook workbook = null;
		OutputStream out = null;

		// 下载代码
		XLSTransformer transformer = new XLSTransformer();

		try {

			in = new BufferedInputStream(res.getInputStream());

			workbook = (HSSFWorkbook) transformer.transformXLS(in, dataMap);

			out = response.getOutputStream();

			workbook.write(out);

		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public void expCardTxnBase(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String unitid = request.getParameter("unitid");
		String merseq = request.getParameter("merseq");
		String cardno = request.getParameter("cardno");
		String samid = request.getParameter("samid");
		String busid = request.getParameter("busid");
		String linecode = request.getParameter("linecode");
		String areaid = request.getParameter("areaid");
		String citycode = request.getParameter("citycode");
		String posid = request.getParameter("posid");
		String branchid = request.getParameter("branchid");
		String settlestat = request.getParameter("settlestat");
		String busiid = request.getParameter("busiid");
		String crdkind = request.getParameter("crdkind");
		String cardmodel = request.getParameter("cardmodel");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");

		if (SessionHandler.getCurrentUnitId() != null) {
			unitid = SessionHandler.getCurrentUnitId().toString();
		}

		if (SessionHandler.getCurrentMerchantId() != null) {
			merseq = SessionHandler.getCurrentMerchantId().toString();
		}

		// 编写xls
		response.reset();
		response.setContentType("application/msexcel");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition",
				"attachment;  filename=\"" + startTime + "-" + endTime
						+ URLEncoder.encode("CardTxnData.xls", "utf-8") + "\"");

		Resource res = new ClassPathResource("reportDemo/cardQuery.xls");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitid", unitid);
		params.put("merseq", merseq);
		params.put("cardno", cardno);
		params.put("samid", samid);
		params.put("busid", busid);
		params.put("linecode", linecode);
		params.put("areaid", areaid);
		params.put("citycode", citycode);
		params.put("posid", posid);
		params.put("branchid", branchid);
		params.put("settlestat", settlestat);
		params.put("busiid", busiid);
		params.put("crdkind", crdkind);
		params.put("cardmodel", cardmodel);
		String nowDate = DateUtil.formatDate(DateUtil.getDate(), "yyyyMMdd");
		if (startTime != null) {
			params.put("start", DateUtil.formatDate(startTime,
					"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
		} else {
			params.put("start", nowDate + "000000");
		}

		if (endTime != null) {
			params.put("end", DateUtil.formatDate(endTime,
					"yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss"));
		} else {
			params.put("end", nowDate + "235959");
		}

		List<TBustxnLog> bustxnlogList = bustxnLogService.findByParamsCards(params);

		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("bustxnlogList", bustxnlogList);

		InputStream in = null;
		HSSFWorkbook workbook = null;
		OutputStream out = null;

		// 下载代码
		XLSTransformer transformer = new XLSTransformer();

		try {

			in = new BufferedInputStream(res.getInputStream());

			workbook = (HSSFWorkbook) transformer.transformXLS(in, dataMap);

			out = response.getOutputStream();

			workbook.write(out);

		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}

	}*/

}
