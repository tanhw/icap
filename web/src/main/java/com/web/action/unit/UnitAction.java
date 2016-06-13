package com.web.action.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.comm.CommParamsBusiness;
import com.business.unit.UnitBusiness;
import com.constant.CommonConstant;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.FieldPage;
import com.core.models.TSysrole;
import com.core.models.TUnitAdmin;
import com.core.models.TUnitInfo;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/unit")
public class UnitAction extends BaseAction{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UnitBusiness unitBussiness;
	
	@Autowired
	private CommParamsBusiness commParamsBusiness;

	/**
	 * unit首页
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.html")
	@RightCode(menuCode = "B13000")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String xmlFile = "unitList";
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
	@LogAction(logDesc = "机构分页查询", fieldName = "request")
	public JsonDataWrapper<TUnitInfo> list(HttpServletRequest request) {

		JsonDataWrapper<TUnitInfo> res;

		try {

			res = unitBussiness.list(request);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TUnitInfo>(false, e.getMessage());
		}

		return res;
	}

	/**
	 * 机构选择页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/pageIndex.html")
	public String pageIndex(HttpServletRequest request) {

		String xmlFile = "unitList";

		List<UiColumn> list = new ArrayList<UiColumn>();
		list.add(new UiColumn("机构编号", "unitid"));
		list.add(new UiColumn("机构名称", "unitname"));

		request.setAttribute("pageColumn", list);
		request.setAttribute("params", "?pageFlag=unitPage");

		request.setAttribute("pageFiled", "unit");
		request.setAttribute("pageParam", UiHandler.getUiListParam(xmlFile));

		return "pageList";
	}

	/**
	 * 机构选择页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/pageCondition.html")
	public String pageCondition(HttpServletRequest request) {
		List<FieldPage> FieldList = new ArrayList<FieldPage>();
		FieldList.add(new FieldPage("机构编号", "pageunitid", CommonConstant.Field.Text
				.toString()));
		FieldList.add(new FieldPage("机构名称", "pageunitname",
				CommonConstant.Field.Text.toString()));

		request.setAttribute("conditionFields", FieldList);

		return "pageCondition";
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
			
			commParamsBusiness.selectByUPParam("UNITBUSIKIND", request, "unitkindList");// 机构种类
			
			String unitid = request.getParameter("id");

			// 新增
			if (unitid == null || unitid.equals("")) {
				request.setAttribute("isModify", "false");
				return "unit/unitDetail";
			}

			unitBussiness.detail(request, unitid);
			request.setAttribute("isModify", "true");// 修改

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw e;
		}

		return "unit/unitDetail";
	}

	/**
	 * 生成确认码页面
	 *
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/createcode.html")
	public String createcode(HttpServletRequest request) throws Exception {

		String code = unitBussiness.getCode();
		request.setAttribute("code", code);

		return "unit/codepage";
	}
	
	/**
	 * 保存
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.html")
	@LogAction(logDesc = "机构添加或修改", fieldName = "unitid,unitname")
	public JsonDataWrapper<TUnitInfo> save(TUnitInfo unit,
			HttpServletRequest request) {

		JsonDataWrapper<TUnitInfo> res = new JsonDataWrapper<TUnitInfo>(true,
				RespCodeConstant.Success.toString());

		try {

			String isModify = request.getParameter("isModify");
			// 添加
			if (isModify.equals("false")) {
				unitBussiness.save(unit, request);
			} else {// 修改
				unitBussiness.update(unit, request);
			}
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TUnitInfo>(false, e.getMessage());
		}
		return res;
	}

	/**
	 * 详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/query.html")
	public String query(HttpServletRequest request) throws Exception {
		
		commParamsBusiness.selectByUPParam("BUSIKIND", request, "unitkindList");// 机构种类
		
		request.setAttribute("unitid", SessionHandler.getCurrentUnitId());
		
		return "unit/unitQuery";
	}
	
	
	/**
	 * 机构权限分配
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deploy.html")
	@LogAction(logDesc = "机构权限分配", fieldName = "unitid,unitname")
	public String deploy(HttpServletRequest request,@RequestParam(value="id")String unitid) throws Exception {
		
		unitBussiness.deployDetail(request, unitid);
		
		return "unit/deployUnit";
	}
	
	
	/**
	 * 保存单位菜单
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/deployUnitSave.html")
	@LogAction(logDesc = "配置机构操作员", fieldName = "unitid,unitname")
	public JsonDataWrapper<TSysrole> deployUnitSave(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "ids") String menucode,
			@RequestParam(value = "unitid") Long unitid,
			TUnitAdmin unitAdmin,
			TSysrole sysrole) throws Exception {

		JsonDataWrapper<TSysrole> res = new JsonDataWrapper<TSysrole>(true, RespCodeConstant.Success.toString());

		try {
			unitBussiness.deployUnitSave(unitid,unitAdmin,sysrole,menucode);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysrole>(false, e.getMessage());
		}

		return res;
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
	@LogAction(logDesc = "机构删除", fieldName = "unitid,unitname")
	public JsonDataWrapper<TSysrole> del(HttpServletRequest request,
			HttpServletResponse response) {

		JsonDataWrapper<TSysrole> res = new JsonDataWrapper<TSysrole>(true, RespCodeConstant.Success.toString());
		try {
			String unitid = request.getParameter("id");
			
			unitBussiness.del(request, unitid);
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysrole>(false, e.getMessage());
		}
		return res;
	}
	
	/**
	 * 绑定查询
	 * 
	 * @author tanhw
	 * @date 2015年8月14日 下午5:24:00
	 * @param @param request
	 * @param @return
	 * @return
	 * @throws
	 */
	@RequestMapping("/bind.html")
	public String bindQuery(HttpServletRequest request) {

		String unitid = request.getParameter("id");

		request.setAttribute("unitid", unitid);

		return "unit/bindUnit";
	}
	
	/**
	 * 绑定报表配置绑定
	 * 
	 * @author tanhw
	 * @date 2015年8月14日 下午5:24:21
	 * @param @param request
	 * @param @return
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/bindConfByunitSave.html")
	public JsonDataWrapper<TUnitInfo> bindConfByunitSave(HttpServletRequest request,@RequestParam(value="unitid")String unitid,@RequestParam(value="ids")String confides) {

		JsonDataWrapper<TUnitInfo> res = new JsonDataWrapper<TUnitInfo>(true,RespCodeConstant.Success.toString());

		try {

			unitBussiness.bindConfByunitSave(request, unitid, confides);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TUnitInfo>(false, e.getMessage());
		}

		return res;
	}
	
	/**
	 * 检查重复
	 * 
	 * @author tanhw
	 * @date 2015年8月21日 下午3:48:20
	 * @param @param request
	 * @param @return
	 * @param @throws Exception
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/checkRepeat.html")
	public JsonDataWrapper<TUnitInfo> checkRepeat(HttpServletRequest request)
			throws Exception {

		JsonDataWrapper<TUnitInfo> res = new JsonDataWrapper<TUnitInfo>(true,
				RespCodeConstant.Success.toString());

		try {

			unitBussiness.checkRepeat(request);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TUnitInfo>(false, e.getMessage());
		}

		return res;
	}

	/**
	 * 验证确认码
	 *
	 * @author tanhw
	 * @date 2015年8月21日 下午3:48:20
	 * @param @param request
	 * @param @return
	 * @param @throws Exception
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/checkCode.html")
	public JsonDataWrapper<String> checkCode(HttpServletRequest request)
			throws Exception {

		JsonDataWrapper<String> res = new JsonDataWrapper<>(true,RespCodeConstant.Success.toString());

		try {

			unitBussiness.checkCode(request);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<String>(false, e.getMessage());
		}

		return res;
	}

	/**
	 * 机构清算信息配置
	 *
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/configBank.html")
	@LogAction(logDesc = "机构清算信息配置", fieldName = "unitid,unitname")
	public String configBank(HttpServletRequest request,@RequestParam(value="id")String unitid) throws Exception {
		unitBussiness.detail(request, unitid);
		return "unit/confBank";
	}

}
