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
import com.business.pos.PosTmsBindBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TPosTmsBind;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/posTmsBind")
public class PosTmsBindAction {

	@Autowired
	private PosTmsBindBusiness posTmsBindBusiness;

	@Autowired
	private CommParamsBusiness commParamsBusiness;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 终端绑定
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode = "P30000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmlFile = "postmsbindList";

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
	@LogAction(logDesc = "终端绑定分页查询", fieldName = "request")
	public JsonDataWrapper<TPosTmsBind> list(HttpServletRequest request) {

		JsonDataWrapper<TPosTmsBind> res = new JsonDataWrapper<TPosTmsBind>(
				true, RespCodeConstant.Success.toString());

		try {
			res = posTmsBindBusiness.lsit(request);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosTmsBind>(false, e.getMessage());
		}

		return res;
	}

	@RequestMapping("/query.html")
	public String query(HttpServletRequest request) throws Exception {
		commParamsBusiness.selectByUPParam("POSBRAND", request, "posbrandList");
		
		request.setAttribute("unitid", SessionHandler.getCurrentUnitId());
		
		return "pos/posTmsBindQuery";
	}

	/**
	 * 详情信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request) throws Exception {

		commParamsBusiness.selectByUPParam("POSTYPE", request, "postypeList");
		commParamsBusiness.selectByUPParam("POSBRAND", request, "posbrandList");
		request.setAttribute("isModify", "false");
		return "pos/posTmsBindDetail";
	}
	
	
	/**
	 * 
	 * 保存
	 * @param request
	 * @param TPosTmsBind
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc="终端绑定增加",fieldName = "posid")
	public JsonDataWrapper<TPosTmsBind> save(HttpServletRequest request,TPosTmsBind posTmsBind,String postype,String posbrand){
		
		JsonDataWrapper<TPosTmsBind> res = new JsonDataWrapper<TPosTmsBind>(true,RespCodeConstant.Success.toString());
		
		try {
			posTmsBindBusiness.add(request, posTmsBind,postype,posbrand);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosTmsBind>(false,e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * 
	 * 保存绑定解除
	 * 
	 * @param request
	 * @param TPosTmsBind
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delSave.html")
	@LogAction(logDesc="终端绑定解除",fieldName = "posid")
	public JsonDataWrapper<TPosTmsBind> delSave(HttpServletRequest request,TPosTmsBind posTmsBind,String postype,String posbrand){
		
		JsonDataWrapper<TPosTmsBind> res = new JsonDataWrapper<TPosTmsBind>(true,RespCodeConstant.Success.toString());
		
		try {
			posTmsBindBusiness.del(request, posTmsBind, postype, posbrand);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosTmsBind>(false,e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * 详情信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delPage.html")
	@LogAction(logDesc = "终端绑定", fieldName = "posid")
	public String delPage(HttpServletRequest request) throws Exception {

		commParamsBusiness.selectByUPParam("POSTYPE", request, "postypeList");
		
		commParamsBusiness.selectByUPParam("POSBRAND", request, "posbrandList");

		request.setAttribute("isModify", "false");
		return "pos/posTmsBinddelPage";
	}
}
