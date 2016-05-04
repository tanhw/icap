package com.web.action.pos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.comm.CommParamsBusiness;
import com.business.pos.PosInfoBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TPosInfo;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/posinfo")
public class PosInfoAction extends BaseAction{
	
	@Autowired
	private PosInfoBusiness posInfoBusiness;
	
	@Autowired
	private CommParamsBusiness commParamsBusiness;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 终端管理
	 */
	@Override
	@RequestMapping("/index.html")
	@RightCode(menuCode="C70000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmlFile = "posinfoList";
		
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
	@LogAction(logDesc="终端管理分页查询" , fieldName="request")
	public JsonDataWrapper<TPosInfo> list(HttpServletRequest request){
		
		JsonDataWrapper<TPosInfo> res = new JsonDataWrapper<TPosInfo>(true,RespCodeConstant.Success.toString());
		
		try {
			
			res = posInfoBusiness.lsit(request);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosInfo>(false,e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/query.html")
	public String query(HttpServletRequest request)throws Exception{
		
		commParamsBusiness.selectByUPParam("POSTYPE", request, "postypeList");
		
		commParamsBusiness.selectByUPParam("POSSTATUS", request, "posstatusList");
		
		commParamsBusiness.selectByUPParam("POSBRAND", request, "posbrandList");
		
		request.setAttribute("unitid", SessionHandler.getCurrentUnitId());
		
		return "pos/posinfoQuery";
	}
	
	/**
	 * 详情信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request)throws Exception{
		
		try {
			
			String posid = request.getParameter("id");
			
			commParamsBusiness.selectByUPParam("BUSI", request, "busiidList");
			
			commParamsBusiness.selectByUPParam("POSTYPE", request, "postypeList");
			
			commParamsBusiness.selectByUPParam("POSSTATUS", request, "posstatusList");
			
			commParamsBusiness.selectByUPParam("POSBRAND", request, "posbrandList");
			
			if(posid == null || posid.equals("")){
				
				request.setAttribute("isModify", "false");
				return "pos/posinfoDetail";
			}
			
			posInfoBusiness.detail(request, posid);
			request.setAttribute("isModify", "true");
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		return "pos/posinfoDetail";
	}
	
	/**
	 * 
	 * 保存 修改
	 * 
	 * @param request
	 * @param tPosInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc="终端管理增加或修改",fieldName="posid")
	public JsonDataWrapper<TPosInfo> save(HttpServletRequest request,TPosInfo tPosInfo){
		
		JsonDataWrapper<TPosInfo> res = new JsonDataWrapper<TPosInfo>(true,RespCodeConstant.Success.toString());
		
		try {
			
			String isModify = request.getParameter("isModify");
			
			if(isModify.equals("false")){
				
				//增加
				posInfoBusiness.add(request, tPosInfo);
			}else{
				//修改
				posInfoBusiness.update(request, tPosInfo);
			}
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosInfo>(false,e.getMessage());
		}
		
		return res;
	}
	/**
	 *  删除
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del.html")
	@LogAction(logDesc="终端管理删除",fieldName="posid")
	public JsonDataWrapper<TPosInfo> del(HttpServletRequest request){
		
		JsonDataWrapper<TPosInfo> res = new JsonDataWrapper<TPosInfo>(true,RespCodeConstant.Success.toString());
		
		try {
			String posid = request.getParameter("id");

			posInfoBusiness.del(request, posid);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosInfo>(false,e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * 修改主密钥下载标志状态为启用
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.html")
	@ResponseBody
	@LogAction(logDesc="终端管理主密钥下载标志",fieldName="posid")
	public JsonDataWrapper<TPosInfo> edit(HttpServletRequest request) {
		JsonDataWrapper<TPosInfo> res = new JsonDataWrapper<TPosInfo>(true,
				RespCodeConstant.Success.toString());
		
		try {
			
			String posid = request.getParameter("id");
			
			posInfoBusiness.edit(request, posid);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosInfo>(false,e.getMessage());
		}
		return res;
	}
	
	/**
	 * 修改keya密钥下载标志
	 * @param request
	 * @return
	 */
	@RequestMapping("/downkeya.html")
	@ResponseBody
	@LogAction(logDesc="终端管理keya密钥下载标志",fieldName="posid")
	public JsonDataWrapper<TPosInfo> downkeya(HttpServletRequest request) {
		JsonDataWrapper<TPosInfo> res = new JsonDataWrapper<TPosInfo>(true,
				RespCodeConstant.Success.toString());
		
		try {
			
			String posid = request.getParameter("id");
			
			posInfoBusiness.downkeya(request, posid);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosInfo>(false,e.getMessage());
		}
		return res;
	}
	
	/**
	 * 终端 检查重复
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkRepeat.html")
	public JsonDataWrapper<TPosInfo> checkRepeat(HttpServletRequest request) {

		JsonDataWrapper<TPosInfo> res = new JsonDataWrapper<TPosInfo>(true,
				RespCodeConstant.Success.toString());

		try {
			posInfoBusiness.checkRepeat(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosInfo>(false, e.getMessage());
		}
		return res;
	}
}
