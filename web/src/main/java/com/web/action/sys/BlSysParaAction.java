package com.web.action.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.models.TBatTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.tbl.BlSysParaBusiness;
import com.constant.RespCodeConstant;
import com.core.annotation.LogAction;
import com.core.annotation.RightCode;
import com.core.controller.common.SessionHandler;
import com.core.controller.util.MenuUtil;
import com.core.models.TSysPara;
import com.core.models.common.JsonDataWrapper;
import com.toolbox.log.LogUtil;
import com.web.action.BaseAction;
import com.web.queryFrame.engine.UiHandler;
import com.web.queryFrame.models.UiColumn;

@Controller
@RequestMapping("/syspara")
public class BlSysParaAction extends BaseAction {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BlSysParaBusiness blSysParaBusiness;
	
	@RequestMapping("/index.html")
	@RightCode(menuCode = "S20000")
	@Override
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xmlFile = "blSysParaList";
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
	@RequestMapping("/list.html")
	@ResponseBody
	@LogAction(logDesc = "参数配置分页查询", fieldName = "request")
	public JsonDataWrapper<TSysPara> list(HttpServletRequest request) {

		JsonDataWrapper<TSysPara> res = new JsonDataWrapper<TSysPara>(true,
				RespCodeConstant.Success.toString());

		try {
			
			res = blSysParaBusiness.list(request);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysPara>(false, e.getMessage());
		}
		return res;
	}

	/**
	 * 查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/query.html")
	public String query(HttpServletRequest request) {
		return "tbl/blSysParaQuery";
	}
	
	/**
	 * 页面详情
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/detail.html")
	public String detail(HttpServletRequest request) throws Exception{
		
		String uname = null;
		String paraName = null;
		String paraNo = null;

		try {

			String seq = request.getParameter("id");

			if (seq != null && (!seq.equals(""))) {

				String mainkeyinfo[] = seq.split(",");
				uname = mainkeyinfo[0];
				paraName = mainkeyinfo[1];
				paraNo = mainkeyinfo[2];
			}else {
				request.setAttribute("isModify", "false");
				return "tbl/blSysParaDetail";
			}
			
			blSysParaBusiness.detail(request, uname, paraName, paraNo);
			request.setAttribute("isModify", "true");
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			throw new Exception();
		}
		return "tbl/blSysParaDetail";
	}
	
	/**
	 * 保存 修改
	 * @param request
	 * @param tblSysPara
	 * @return
	 */
	@RequestMapping("/save.html")
	@ResponseBody
	@LogAction(logDesc="参数配置增加或修改",fieldName="uname,paraName")
	public JsonDataWrapper<TSysPara> save(HttpServletRequest request,TSysPara tblSysPara){
		
		JsonDataWrapper<TSysPara> res = new JsonDataWrapper<TSysPara>(true,
				RespCodeConstant.Success.toString());
		
		try {
			
			String isModify = request.getParameter("isModify");
			
			if(isModify.equals("false")){
				
				//添加
				
				blSysParaBusiness.add(request, tblSysPara);
			}else {
				//修改
				blSysParaBusiness.update(request, tblSysPara);
			}
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysPara>(false,e.getMessage());
		}
		
		return res;
	}
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/del.html")
	@ResponseBody
	@LogAction(logDesc="参数配置删除",fieldName="uname,paraName")
	public JsonDataWrapper<TSysPara> del(HttpServletRequest request){
		
		
		JsonDataWrapper<TSysPara> res = new JsonDataWrapper<TSysPara>(true,
				RespCodeConstant.Success.toString());
		
		String uname = null;
		String paraName = null;
		String paraNo = null;
		
		try {
			
			String seq = request.getParameter("id");
			
			String mainkeyinfo[] = seq.split(",");
			uname = mainkeyinfo[0];
			paraName = mainkeyinfo[1];
			paraNo = mainkeyinfo[2];
			
			blSysParaBusiness.del(request, uname, paraName, paraNo);
			
		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TSysPara>(false,e.getMessage());
		}
		return res;
	}

	/**
	 * 手动执行跑批任务
	 *
	 * @return
	 */
	@RequestMapping("/queryTask.html")
	public String queryTask() {
		return "tbl/excuteTask";
	}

	/**
	 * 手动执行跑批任务保存
	 *
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/exceuteTaskSave.html")
	public JsonDataWrapper<TBatTask> exceuteTaskSave(HttpServletRequest request, TBatTask tBatTask) {
		JsonDataWrapper<TBatTask> res = new JsonDataWrapper<TBatTask>(true, RespCodeConstant.Success.toString());

		try {

			blSysParaBusiness.exceuteTaskSave(request, tBatTask);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			res = new JsonDataWrapper<TBatTask>(false, e.getMessage());
		}

		return res;
	}

}
