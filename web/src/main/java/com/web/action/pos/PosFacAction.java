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
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.pos.PosFacBusiness;
import com.constant.CommonConstant;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.FieldPage;
import com.core.models.TPosFac;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/posfac")
public class PosFacAction extends BaseAction {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PosFacBusiness posFacBusiness;

	/**
	 * 终端厂商首页
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode = "C60000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmlFile = "posfacList";

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
	@LogAction(logDesc = "硬件厂商分页查询", fieldName = "request")
	public JsonDataWrapper<TPosFac> list(HttpServletRequest request) {

		JsonDataWrapper<TPosFac> res = new JsonDataWrapper<TPosFac>(true,
				RespCodeConstant.Success.toString());

		try {
			
			res = posFacBusiness.list(request);
			
		} catch (Exception e) {
			
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosFac>(false,e.getMessage());
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
		return "pos/posfacQuery";
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
			String factoryid = request.getParameter("id");

			if (factoryid == null || factoryid.equals("")) {

				request.setAttribute("isModify", "false");
				return "pos/posfacDetail";
			}
			
			posFacBusiness.detail(request, factoryid);
			request.setAttribute("isModify", "true");
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		return "pos/posfacDetail";
	}
	
	/**
	 * 保存 修改
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc="硬件厂商增加或修改" , fieldName="factoryid,facname")
	public JsonDataWrapper<TPosFac> save(HttpServletRequest request,TPosFac tPosFac){
		
		JsonDataWrapper<TPosFac> res = new JsonDataWrapper<TPosFac>(true,RespCodeConstant.Success.toString());
		
		try {
			String isModify = request.getParameter("isModify");

			if (isModify.equals("false")) {

				// 增加
				posFacBusiness.add(request, tPosFac);
			} else {

				// 修改
				posFacBusiness.update(request, tPosFac);
			}
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosFac>(false,e.getMessage());
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
	@LogAction(logDesc = "硬件厂商删除", fieldName = "factoryid,facname")
	public JsonDataWrapper<TPosFac> del(HttpServletRequest request){
		
		JsonDataWrapper<TPosFac> res = new JsonDataWrapper<TPosFac>(true,
				RespCodeConstant.Success.toString());

		try {
			String factoryid = request.getParameter("id");

			posFacBusiness.del(request, factoryid);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TPosFac>(false, e.getMessage());
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

		String xmlFile = "posfacList";

		List<UiColumn> list = new ArrayList<UiColumn>();
		list.add(new UiColumn("厂商编号", "factoryid"));
		list.add(new UiColumn("厂商名称", "facname"));

		request.setAttribute("pageColumn", list);
		request.setAttribute("params", "?pageFlag=unitPage");

		request.setAttribute("pageFiled", "posfac");
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

		FieldList.add(new FieldPage("厂商编号", "pagefactoryid",
				CommonConstant.Field.Text.toString()));
		FieldList.add(new FieldPage("厂商名称", "pagefacname", CommonConstant.Field.Text
				.toString()));

		request.setAttribute("conditionFields", FieldList);

		return "pageCondition";
	}
}
