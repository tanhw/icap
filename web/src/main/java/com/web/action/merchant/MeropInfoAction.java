package com.web.action.merchant;

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

import com.business.merchant.MeropInfoBusiness;
import com.constant.CommonConstant;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TMeropInfo;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/merop")
public class MeropInfoAction extends BaseAction{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MeropInfoBusiness meropInfoBusiness;
	
	/**
	 * 商户信息管理首页
	 */
	@Override
	@RequestMapping("/index.html")
	@RightCode(menuCode="S60000")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String xmlFile = "meropList";
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
	public JsonDataWrapper<TMeropInfo> list(HttpServletRequest request){
		
		JsonDataWrapper<TMeropInfo> res = new JsonDataWrapper<TMeropInfo>(
				true, RespCodeConstant.Success.toString());
		
		try {
			res = meropInfoBusiness.list(request);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TMeropInfo>(false, e.getMessage());
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
		
		return "merchant/meroptQuery";
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
				return "merchant/meropDetail";
			}
			
			meropInfoBusiness.detail(request, merseq);
			
			request.setAttribute("isModify", "true");
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}
		
		return "merchant/meropDetail";
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
	public JsonDataWrapper<TMeropInfo> save(HttpServletRequest request,TMeropInfo meropInfo){
		
		JsonDataWrapper<TMeropInfo> res = new JsonDataWrapper<TMeropInfo>(true, RespCodeConstant.Success.toString());
		try {
			String isModify = request.getParameter("isModify");

			if (isModify.equals("false")) {
				// 增加
				meropInfoBusiness.add(request, meropInfo);
			} else {
				// 修改
				meropInfoBusiness.update(request, meropInfo);
			}

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TMeropInfo>(false,e.getMessage());
		}
		return res;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/del.html")
	@ResponseBody
	@LogAction(logDesc = "商户管理删除", fieldName = "merseq,branchid")
	public JsonDataWrapper<TMeropInfo> del(HttpServletRequest request){
		
		JsonDataWrapper<TMeropInfo> res = new  JsonDataWrapper<TMeropInfo>(true, RespCodeConstant.Success.toString());
		
		try {
			
			String merseq = request.getParameter("id");
			
			meropInfoBusiness.del(request, merseq);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TMeropInfo>(false,e.getMessage());
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
		
		String xmlFile = "roleList";

		List<UiColumn> list = new ArrayList<UiColumn>();
		list.add(new UiColumn("角色名称", "rolename"));
		list.add(new UiColumn("描述", "roledesc"));

		request.setAttribute("pageColumn", list);

		request.setAttribute("pageFiled", "role");
		request.setAttribute("pageParam", UiHandler.getUiListParam(xmlFile));
		
		request.setAttribute("params", "?roletype=" + CommonConstant.RoleType.UnitChildNormalRole.toString());

		return "pageList";
	}
}
