package com.web.action.black;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.business.black.BlackInfoBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TBlackInfo;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.toolbox.util.DateUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

/**
 * 黑名单
 * 
 * @ author 许西
 *
 */
@RequestMapping("/blackInfo")
@Controller
public class BlackInfoAction extends BaseAction {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BlackInfoBusiness blackInfoBusiness;
	

	/**
	 * 首页
	 */
	@Override
	@RequestMapping("/index.html")
	@RightCode(menuCode = "A50000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmlFile = "blackInfoList";
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
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.html")
	@LogAction(logDesc = "商户管理分页查询", fieldName = "request")
	public JsonDataWrapper<TBlackInfo> list(HttpServletRequest request) {

		JsonDataWrapper<TBlackInfo> res = new JsonDataWrapper<TBlackInfo>(true,
				RespCodeConstant.Success.toString());

		try {
			res = blackInfoBusiness.list(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBlackInfo>(false, e.getMessage());
		}

		return res;

	}

	/**
	 * 查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/query.html")
	public String query(HttpServletRequest request) {

		String unitid = request.getParameter("unitid");

		if (SessionHandler.getCurrentUnitId() != null) {
			unitid = SessionHandler.getCurrentUnitId().toString();
		}

		request.setAttribute("unitid", unitid);

		return "black/blackInfoQuery";
	}

	/**
	 * 商户详细信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request) throws Exception {

		try {
			String blackseq = request.getParameter("id");

			if (blackseq == null || blackseq.equals("")) {

				request.setAttribute("isModify", "false");
				return "black/blackInfoDetail";
			}

			blackInfoBusiness.detail(request, blackseq);

			request.setAttribute("isModify", "true");
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}

		return "black/blackInfoDetail";
	}

	/**
	 * 保存 修改
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "黑名单管理增加或修改", fieldName = "blackseq,name")
	public JsonDataWrapper<TBlackInfo> save(HttpServletRequest request,
			TBlackInfo blackInfo) {

		JsonDataWrapper<TBlackInfo> res = new JsonDataWrapper<TBlackInfo>(true,
				RespCodeConstant.Success.toString());
		try {
			String isModify = request.getParameter("isModify");

			if (isModify.equals("false")) {
				// 增加
				blackInfoBusiness.add(request, blackInfo);
			} else {
				// 修改
				blackInfoBusiness.update(request, blackInfo);
			}

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBlackInfo>(false, e.getMessage());
		}
		return res;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping("/del.html")
	@ResponseBody
	@LogAction(logDesc = "黑名单删除", fieldName = "blackseq,name")
	public JsonDataWrapper<TBlackInfo> del(HttpServletRequest request) {

		JsonDataWrapper<TBlackInfo> res = new JsonDataWrapper<TBlackInfo>(true,
				RespCodeConstant.Success.toString());

		try {

			String id = request.getParameter("id");

			blackInfoBusiness.del(request, id);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBlackInfo>(false, e.getMessage());
		}
		return res;
	}

	/**
	 * 查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/batchaddQuery.html")
	public String batchaddQuery(HttpServletRequest request) {
		return "black/blackBatchAdd";
	}

	/**
	 * 批量导入
	 * 
	 * @param uploadFile
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/precheck.html")
	public JsonDataWrapper<TBlackInfo> blackBatchAdd(
			@RequestParam("blacks") MultipartFile uploadFile,
			HttpServletRequest request) {

		JsonDataWrapper<TBlackInfo> res = new JsonDataWrapper<TBlackInfo>(true,
				RespCodeConstant.Success.toString());
		Map<String,Integer> map =null;
		try {
			
			map = blackInfoBusiness.blackBatchAdd(uploadFile, request);

			res.setMessage("成功导入数据："+map.get("seccCount")+" 条;"
					+"重复数据："+map.get("repeat")+" 条;"+"失败数据："+map.get("fail")+ " 条");

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBlackInfo>(false, e.getMessage());
		}

		return res;
	}
	
	/**
	 * 下载Excel模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/downloadExcelModel.html")
	public void downloadExcelModel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			blackInfoBusiness.downloadExcelModel(request, response);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}

	}
	
	/**
	 * 黑名单 检查重复
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkRepeat.html")
	public JsonDataWrapper<TBlackInfo> checkRepeat(HttpServletRequest request){
		
		JsonDataWrapper<TBlackInfo> res = new JsonDataWrapper<TBlackInfo>(true,
				RespCodeConstant.Success.toString());
		
		try {
			blackInfoBusiness.checkRepeat(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBlackInfo>(false,e.getMessage());
		}
		return res;
	}
	
	/**
	 * 一键黑名单清除 查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/clearQuery.html")
	public String clearQuery(HttpServletRequest request) {

		request.setAttribute("startTime", DateUtil.getNowMonth());
		request.setAttribute("endTime", DateUtil.getTime());

		return "black/clearQuery";
	}
	
	/**
	 * 一键清除黑名单
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/blackClear.html")
	public JsonDataWrapper<TBlackInfo> blackClear(HttpServletRequest request) {

		JsonDataWrapper<TBlackInfo> res = new JsonDataWrapper<TBlackInfo>(true,
				RespCodeConstant.Success.toString());

		try {
			
			blackInfoBusiness.blackClear(request);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBlackInfo>(false,e.getMessage());
		}

		return res;
	}
}
 