package com.web.action.bank;

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

import com.business.bank.BanksInfoBusiness;
import com.constant.CommonConstant;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.FieldPage;
import com.core.models.TBanksInfo;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/bank")
public class BanksInfoAction extends BaseAction{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BanksInfoBusiness banksInfoBusiness;
	
	/**
	 * 银行管理首页
	 */
	@Override
	@RequestMapping("/index.html")
	@RightCode(menuCode="C50000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmlFile = "bankList";
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
	@LogAction(logDesc = "银行管理分页查询", fieldName = "request")
	public JsonDataWrapper<TBanksInfo> list(HttpServletRequest request) {
		
		JsonDataWrapper<TBanksInfo> res = new JsonDataWrapper<TBanksInfo>();
		
		try {
			
			res = banksInfoBusiness.list(request);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBanksInfo>(false,e.getMessage());
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
		
		return "bank/bankQuery";
	}
	
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request)throws Exception{
		
		try {
			String bankid = request.getParameter("id");

			if (bankid == null || bankid.equals("")) {
				request.setAttribute("isModify", "false");
				return "bank/bankDetail";
			}
			
			banksInfoBusiness.detail(request, bankid);
			request.setAttribute("isModify", "true");
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		return "bank/bankDetail";
		
	}
	
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "银行管理增加或修改", fieldName = "bankid,bankname")
	public JsonDataWrapper<TBanksInfo> save(HttpServletRequest request,
			TBanksInfo tBanksInfo) {
		
		JsonDataWrapper<TBanksInfo> res = new JsonDataWrapper<TBanksInfo>(true,
				RespCodeConstant.Success.toString());
		
		try {
			
			String isModify = request.getParameter("isModify");
			
			if(isModify.equals("false")){
				
				//增加
				banksInfoBusiness.add(request, tBanksInfo);
			}else{
				
				//修改
				banksInfoBusiness.update(request, tBanksInfo);
			}
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBanksInfo>(false,e.getMessage());
		}
		return res;
	}
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del.html")
	@LogAction(logDesc = "银行删除", fieldName = "bankid,bankname")
	public JsonDataWrapper<TBanksInfo> del(HttpServletRequest request){
		
		JsonDataWrapper<TBanksInfo> res = new JsonDataWrapper<TBanksInfo>(true,
				RespCodeConstant.Success.toString());
		
		try {
			String bankid = request.getParameter("id");

			banksInfoBusiness.del(request, bankid);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBanksInfo>(false,e.getMessage());
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
		
		String xmlFile = "bankList";

		List<UiColumn> list = new ArrayList<UiColumn>();
		list.add(new UiColumn("银行ID", "bankid"));
		list.add(new UiColumn("银行名称", "bankname"));
		list.add(new UiColumn("银行代码", "bankcode"));
		
		request.setAttribute("pageColumn", list);

		request.setAttribute("pageFiled", "bank");
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
		
		FieldList.add(new FieldPage("银行ID", "pagebankid",
				CommonConstant.Field.Text.toString()));
		FieldList.add(new FieldPage("银行名称", "pagebankname",
				CommonConstant.Field.Text.toString()));

		request.setAttribute("conditionFields", FieldList);

		return "pageCondition";
	}
}
