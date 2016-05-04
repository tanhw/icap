package com.web.action.cardbin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.cardbin.BankCardbinBusiness;
import com.constant.CommonConstant;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.FieldPage;
import com.core.models.TBankCardbin;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/cardbin")
public class BankCardbinAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BankCardbinBusiness bankCardbinBusiness;
	/**
	 * 卡Bin管理首页
	 */
	@Override
	@RequestMapping("/index.html")
	@RightCode(menuCode="A40000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmlFile = "cardbinList";
		List<UiColumn> list = UiHandler.getUiListColumn(xmlFile);
		request.setAttribute("showColumn", list);

		request.setAttribute("childMenu", MenuUtil.getFormatMenu(
				SessionHandler.getCurrentRightCode(),
				SessionHandler.getAllMeun()));
		request.setAttribute("listParam", UiHandler.getUiListParam(xmlFile));
		request.setAttribute("importJs", UiHandler.getUiJs(xmlFile));

		return "queryList";
	}
	
	@ResponseBody
	@RequestMapping("/list.html")
	@LogAction(logDesc = "卡Bin管理分页查询", fieldName = "request")
	public JsonDataWrapper<TBankCardbin> list(HttpServletRequest request) {
		
		JsonDataWrapper<TBankCardbin> res = new JsonDataWrapper<TBankCardbin>(
				true, RespCodeConstant.Success.toString());
		
		try {
			
			res = bankCardbinBusiness.list(request);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBankCardbin>(false,e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * 查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/query.html")
	public String query(HttpServletRequest request){
		
		request.setAttribute("unitid", SessionHandler.getCurrentUnitId());
		
		return "cardbin/cardbinQuery";
		
	}
	
	/**
	 * 详细信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request)throws Exception{
		
		try {
			String binseq = request.getParameter("id");

			if (binseq == null || binseq.equals("")) {

				request.setAttribute("isModify", "false");
				return "cardbin/cardbinDetail";

			}

			bankCardbinBusiness.detail(request, binseq);
			request.setAttribute("isModify", "true");

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}

		return "cardbin/cardbinDetail";
	}
	
	
	/**
	 * 保存
	 * @param request
	 * @param tBankCardbin
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "卡Bin管理增加或修改", fieldName = "binseq,binname")
	public JsonDataWrapper<TBankCardbin> save(HttpServletRequest request,TBankCardbin tBankCardbin){
		
		JsonDataWrapper<TBankCardbin> res = new JsonDataWrapper<TBankCardbin>(
				true, RespCodeConstant.Success.toString());
		
		try {
			
			String isModify = request.getParameter("isModify");
			
			if(isModify.equals("false")){
				
				//增加
				bankCardbinBusiness.add(request, tBankCardbin);
			}else{
				
				//修改
				bankCardbinBusiness.update(request, tBankCardbin);
			}
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBankCardbin>(false,e.getMessage());
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

		String xmlFile = "cardbinList";

		List<UiColumn> list = new ArrayList<UiColumn>();
		list.add(new UiColumn("卡Bin", "cardbin"));
		list.add(new UiColumn("卡Bin名称", "binname"));

		request.setAttribute("pageColumn", list);

		request.setAttribute("pageFiled", "cardbin");
		request.setAttribute("pageParam", UiHandler.getUiListParam(xmlFile));

		return "pageList";
	}
	
	/**
	 *选择页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/pageCondition.html")
	public String pageCondition(HttpServletRequest request) {

		List<FieldPage> FieldList = new ArrayList<FieldPage>();

		FieldList.add(new FieldPage("卡Bin", "pagecardbin",
				CommonConstant.Field.Text.toString()));
		FieldList.add(new FieldPage("卡Bin名称", "pagebinname",
				CommonConstant.Field.Text.toString()));

		request.setAttribute("conditionFields", FieldList);

		return "pageCondition";
	}
	
	
	/**
	 * 删除
	 * 
	 * @ author 许西
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del.html")
	@LogAction(logDesc = "卡BIN删除", fieldName = "binseq,binname")
	public JsonDataWrapper<TBankCardbin> del(HttpServletRequest request,
			HttpServletResponse response) {

		JsonDataWrapper<TBankCardbin> res = new JsonDataWrapper<TBankCardbin>(true, RespCodeConstant.Success.toString());
		try {
			String binseq = request.getParameter("id");
			
			bankCardbinBusiness.del(request, binseq);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBankCardbin>(false, e.getMessage());
		}
		return res;
	}
	
}
