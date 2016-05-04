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

import com.business.pos.PosKeyInfoBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TPosKeyInfo;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/poskey")
public class PosKeyInfoAction extends BaseAction{
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public PosKeyInfoBusiness posKeyInfoBusiness;
	
	/**
	 * 终端密钥信息
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode="P10000")
	@Override
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmlFile = "posKeyInfoList";

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
	 */
	@RequestMapping("/list.html")
	@ResponseBody
	@LogAction(logDesc = "终端密钥信息分页查询", fieldName = "request")
	public JsonDataWrapper<TPosKeyInfo> list(HttpServletRequest request){
		
		JsonDataWrapper<TPosKeyInfo> res = new JsonDataWrapper<TPosKeyInfo>(
				true, RespCodeConstant.Success.toString());
		
		try {
			
			res = posKeyInfoBusiness.list(request);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosKeyInfo>(false,e.getMessage());
		}
		return res;
	}
	
	/**
	 * 终端密钥管理查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/query.html")
	public String query(HttpServletRequest request){
		
		String unitid = request.getParameter("unitid");
		
		if(SessionHandler.getCurrentUnitId() != null){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}
		
		request.setAttribute("unitid", unitid);
		
		return "pos/posKeyInfoQuery";
	}
	
	/**
	 * 终端密钥管理详情描述
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request){
		return "";
	}
}
