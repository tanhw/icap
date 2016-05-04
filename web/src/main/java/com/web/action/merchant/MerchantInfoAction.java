
package com.web.action.merchant;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.merchant.MerchantInfoBusiness;
import com.constant.CommonConstant;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.FieldPage;
import com.core.models.TMerchantInfo;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/merchant")
public class MerchantInfoAction extends BaseAction{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MerchantInfoBusiness merchantInfoBusiness;
	
	/**
	 * 商户信息管理首页
	 */
	@Override
	@RequestMapping("/index.html")
	@RightCode(menuCode="C30000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmlFile = "merchantList";
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
	@ResponseBody
	@RequestMapping("/list.html")
	@LogAction(logDesc = "商户管理分页查询", fieldName = "request")
	public JsonDataWrapper<TMerchantInfo> list(HttpServletRequest request){
		
		JsonDataWrapper<TMerchantInfo> res = new JsonDataWrapper<TMerchantInfo>(
				true, RespCodeConstant.Success.toString());
		
		try {
			res = merchantInfoBusiness.list(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TMerchantInfo>(false, e.getMessage());
		}
		
		return  res;
		
	}
	
	/**
	 * 商户查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/query.html")
	public String query(HttpServletRequest request){
		
		request.setAttribute("unitid", SessionHandler.getCurrentUnitId());
		
		return "merchant/merchantQuery";
	}
	
	/**
	 * 商户详细信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request)throws Exception{
		
		try {
			String merseq = request.getParameter("id");

			if (merseq == null || merseq.equals("")) {

				request.setAttribute("isModify", "false");
				return "merchant/merchantDetail";
			}
			
			merchantInfoBusiness.detail(request, merseq);
			
			request.setAttribute("isModify", "true");
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		
		return "merchant/merchantDetail";
	}
	/**
	 * 保存 修改
	 * @param request
	 * @param tMerchantInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "商户管理增加或修改", fieldName = "merseq,branchid")
	public JsonDataWrapper<TMerchantInfo> save(HttpServletRequest request,TMerchantInfo tMerchantInfo){
		
		JsonDataWrapper<TMerchantInfo> res = new JsonDataWrapper<TMerchantInfo>(
				true, RespCodeConstant.Success.toString());
		
		try {
			String isModify = request.getParameter("isModify");

			if (isModify.equals("false")) {

				// 增加
				merchantInfoBusiness.add(request, tMerchantInfo);
			} else {

				// 修改
				merchantInfoBusiness.update(request, tMerchantInfo);
			}

		}  catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TMerchantInfo>(false, e.getMessage());
		}
		return res;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/del.html")
	@LogAction(logDesc = "商户管理删除", fieldName = "merseq,branchid")
	public JsonDataWrapper<TMerchantInfo> del(HttpServletRequest request){
		
		JsonDataWrapper<TMerchantInfo> res = new JsonDataWrapper<TMerchantInfo>(
				true, RespCodeConstant.Success.toString());
		
		try {
			
			String merseq = request.getParameter("id");
			
			merchantInfoBusiness.del(request, merseq);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TMerchantInfo>(false,e.getMessage());
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

		String xmlFile = "merchantList";

		List<UiColumn> list = new ArrayList<UiColumn>();
		list.add(new UiColumn("商户序号", "merseq"));
		list.add(new UiColumn("商户编号", "branchid"));
		list.add(new UiColumn("商户中文名", "branchchn"));

		request.setAttribute("pageColumn", list);

		request.setAttribute("pageFiled", "merchant");
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

		FieldList.add(new FieldPage("商户序号", "pagemerseq",
				CommonConstant.Field.Text.toString()));
		FieldList.add(new FieldPage("商户编号", "pagebranchid",
				CommonConstant.Field.Text.toString()));
		request.setAttribute("conditionFields", FieldList);

		return "pageCondition";
	}

	/**
	 * 商户 检查重复
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkRepeat.html")
	public JsonDataWrapper<TMerchantInfo> checkRepeat(HttpServletRequest request) {

		JsonDataWrapper<TMerchantInfo> res = new JsonDataWrapper<TMerchantInfo>(true,
				RespCodeConstant.Success.toString());

		try {
			merchantInfoBusiness.checkRepeat(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TMerchantInfo>(false, e.getMessage());
		}
		return res;
	}

}
