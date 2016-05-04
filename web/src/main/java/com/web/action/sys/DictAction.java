package com.web.action.sys;

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

import com.business.dict.DictBusiness;
import com.constant.CommonConstant;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.FieldPage;
import com.core.models.TDict;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/dict")
public class DictAction extends BaseAction{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DictBusiness dictBussiness;
	
	/**
	 * 首页
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode = "S70000")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String xmlFile = "dictList";
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
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/list.html")
	@LogAction(logDesc = "角色分页查询", fieldName = "request")
	public JsonDataWrapper<TDict> list(HttpServletRequest request) {

		JsonDataWrapper<TDict> res = new JsonDataWrapper<TDict>(true,
				RespCodeConstant.Success.toString());

		try {
			res = dictBussiness.list(request);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TDict>(false, e.getMessage());
		}

		return res;
	}

	@RequestMapping("/query.html")
	public String query(HttpServletRequest request) throws Exception {
		
		request.setAttribute("unitid", SessionHandler.getCurrentUnitId());
		
		return "dict/dictQuery";
	}
	
	/**
	 * 详情页面 新增页面
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request) throws Exception {

		try {

			String ccode = request.getParameter("id");
			
			// 新增
			if (ccode == null || ccode.equals("")) {
				request.setAttribute("isModify", "false");
				return "dict/dictDetail";
			}

			dictBussiness.detail(request, ccode);
			
			// 修改
			request.setAttribute("isModify", "true");

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}

		return "dict/dictDetail";
	}
	
	/**
	 * 保存
	 * @ author 许西
	 * @param menu
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "字典添加或修改", fieldName = "ccode")
	public JsonDataWrapper<TDict> save(TDict dict,
			HttpServletRequest request) {

		JsonDataWrapper<TDict> res = new JsonDataWrapper<TDict>(true, RespCodeConstant.Success.toString());

		try {

			String isModify = request.getParameter("isModify");
			// 添加
			if (isModify.equals("false")) {
				dictBussiness.save(dict, request);
			} else {// 修改
				dictBussiness.update(dict, request);
			}
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TDict>(false, e.getMessage());
		}
		return res;
	}
	
	
	/**
	 * 字典选择页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/pageIndex.html")
	public String pageIndex(HttpServletRequest request) {

		String xmlFile = "dictList";

		List<UiColumn> list = new ArrayList<UiColumn>();
		list.add(new UiColumn("字典编号", "ccode"));
		list.add(new UiColumn("字典值", "cvalue"));

		request.setAttribute("pageColumn", list);

		request.setAttribute("pageFiled", "dict");
		request.setAttribute("pageParam", UiHandler.getUiListParam(xmlFile));

		return "pageList";
	}
	
	/**
	 * 字典选择页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/pageCondition.html")
	public String pageCondition(HttpServletRequest request) {

		List<FieldPage> FieldList = new ArrayList<FieldPage>();

		FieldList.add(new FieldPage("字典编号", "pageccode",
				CommonConstant.Field.Text.toString()));
		FieldList.add(new FieldPage("字典值", "pagecvalue", CommonConstant.Field.Text
				.toString()));

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
	@LogAction(logDesc = "字典删除", fieldName = "ccode")
	public JsonDataWrapper<TDict> del(HttpServletRequest request,
			HttpServletResponse response)throws Exception {

		JsonDataWrapper<TDict> res = new JsonDataWrapper<TDict>(true, RespCodeConstant.Success.toString());
		try {
			String ccode = request.getParameter("id");
			dictBussiness.del(request, ccode);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TDict>(false, e.getMessage());
		}
		return res;
	}
	

	/**
	 * 更新公共字典Map
	 * 
	 * @ author 许西
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateCommonMap.html")
	@LogAction(logDesc = "字典map更新", fieldName = "ccode")
	public JsonDataWrapper<TDict> updateCommonMap(HttpServletRequest request,
			HttpServletResponse response)throws Exception {

		JsonDataWrapper<TDict> res = new JsonDataWrapper<TDict>(true, RespCodeConstant.Success.toString());
		try {
			dictBussiness.updateCommonParamsMap();
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TDict>(false, e.getMessage());
		}
		return res;
	}
	
}
