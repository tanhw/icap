package com.web.action.pos;

import java.util.ArrayList;
import java.util.List;

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

import com.business.comm.CommParamsBusiness;
import com.business.pos.PosTmsDataBusiness;
import com.constant.CommonConstant;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.FieldPage;
import com.core.models.TPosTmsData;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/posTmsdata")
public class PosTmsDataAction {
	
	@Autowired
	private PosTmsDataBusiness posTmsDataBusiness;
	
	@Autowired
	private CommParamsBusiness commParamsBusiness;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 终端TMS管理
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode="P20000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmlFile = "postmsdataList";
		
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
	@LogAction(logDesc="终端TMS管理分页查询" , fieldName="request")
	public JsonDataWrapper<TPosTmsData> list(HttpServletRequest request){
		
		JsonDataWrapper<TPosTmsData> res = new JsonDataWrapper<TPosTmsData>(true,RespCodeConstant.Success.toString());
		
		try {
			
			res = posTmsDataBusiness.lsit(request);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosTmsData>(false,e.getMessage());
		}
		
		return res;
	}
	

	/**
	 * 详情信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request)throws Exception{
		
		commParamsBusiness.selectByUPParam("POSTYPE", request, "postypeList");
		
		commParamsBusiness.selectByUPParam("POSBRAND", request, "posbrandList");
		
		request.setAttribute("unitid", SessionHandler.getCurrentUnitId());
		
		return "pos/posTmsDetail";
	}
	 
	
	@ResponseBody
	@RequestMapping("/upload")
	public JsonDataWrapper<TPosTmsData> upload(HttpServletRequest request,
			@RequestParam("catchfile") MultipartFile tmsFile) {
		
		JsonDataWrapper<TPosTmsData> res = new JsonDataWrapper<TPosTmsData>(true, RespCodeConstant.Success.toString());
		
		try {
			
			posTmsDataBusiness.upload(request, tmsFile);
			
		} catch (Exception e) {
			e.printStackTrace();
			res = new JsonDataWrapper<TPosTmsData>(false, e.getMessage());
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
	@LogAction(logDesc="终端TMS删除",fieldName = "request")
	public JsonDataWrapper<TPosTmsData> del(HttpServletRequest request){
		
		JsonDataWrapper<TPosTmsData> res = new JsonDataWrapper<TPosTmsData>(true,RespCodeConstant.Success.toString());
		
		try {
			String filename = request.getParameter("id");

			posTmsDataBusiness.del(request, filename);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosTmsData>(false,e.getMessage());
		}
		
		return res;
	}
	
	
	
	/**
	 * 选择页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/pageIndex.html")
	public String pageIndex(HttpServletRequest request) {

		String xmlFile = "postmsdataList";
		
		String merseq = request.getParameter("merseq");

		List<UiColumn> list = new ArrayList<UiColumn>();
		list.add(new UiColumn("TMS程序名", "filename"));
		list.add(new UiColumn("pos品牌", "posbrand"));
		list.add(new UiColumn("程序功能描述", "filefunc"));
		
		request.setAttribute("pageColumn", list);
		request.setAttribute("params", "?merseq="+merseq);
		
		request.setAttribute("pageFiled", "posTmsdata");
		request.setAttribute("pageParam", UiHandler.getUiListParam(xmlFile));

		return "pageList";
	}
	
	/**
	 * 选择页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/pageCondition.html")
	public String pageCondition(HttpServletRequest request) {

		List<FieldPage> FieldList = new ArrayList<FieldPage>();

		FieldList.add(new FieldPage("TMS程序名称", "pagefilename",
				CommonConstant.Field.Text.toString()));
		
		request.setAttribute("conditionFields", FieldList);

		return "pageCondition";
	}
	
}
