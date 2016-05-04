package com.web.action.collect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.controller.service.tab.ITabCofBasicService;
import com.core.models.TDict;
import com.core.models.TTabCofBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.business.comm.CommParamsBusiness;
import com.business.txn.BusTxnCollectBusiness;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/collect")
public class MainCollectAction {

	@Autowired
	private BusTxnCollectBusiness busBussiness;

	@Autowired
	private ITabCofBasicService tabCofBasicService;

	@Autowired
	private CommParamsBusiness commParamsBusiness;

	/**
	 * 查询主页面
	 *
	 * @ author 许西
	 * @date 2015年3月23日 上午11:47:44
	 * @param @param request
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/collect.html")
	public String collect(HttpServletRequest request)throws Exception {

		busBussiness.mainCollect(request);

		return "collect/mainCollect";
	}

	/**
	 * 查询的报表主体
	 * @param request
	 * @return
	 */
	@RequestMapping("/tableCollect.html")
	public String tableCollect(HttpServletRequest request)throws Exception{

		String confid = request.getParameter("id"); //菜单按钮ID

		TTabCofBasic obj = tabCofBasicService.findObjByKey(Integer.parseInt(confid));

		TDict bus = commParamsBusiness.selectByParam("BUS");

		if (obj.getBusi().equals(bus.getCvalue())) {
			busBussiness.tableCollect(request, obj);
		}

		return "collect/tableCollect";

		//		TDict camp = commParamsBusiness.selectByParam("CAMPUS");
		//
		//		if (obj.getBusi().equals(camp.getCvalue())) {
		//			campBussiness.tableCollect(request,obj);
		//		}else {
		//
		//		}
	}

	/**
	 * 查询的报表条件
	 * @param request
	 * @return
	 */
	@RequestMapping("/whereCollect.html")
	public String whereCollect(HttpServletRequest request)throws Exception{

		commParamsBusiness.selectByUPParam("CRDKIND", request, "crdkindList");// 内部卡类型

		busBussiness.whereCollect(request);

		return "collect/whereCollect";
	}


	/**
	 * 更多信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/more.html")
	public String more(HttpServletRequest request)throws Exception{

		String confid = request.getParameter("id"); //菜单按钮ID

		TTabCofBasic obj = tabCofBasicService.findObjByKey(Integer.parseInt(confid));

		TDict bus = commParamsBusiness.selectByParam("BUS");

		if (obj.getBusi().equals(bus.getCvalue())) {
			busBussiness.tableCollect(request,obj);
		}

		return "collect/more";
	}

	/**
	 * 线路汇总导出报表
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping("/expLineSummaryColl.html")
	public void expLineSummaryColl(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String confid = request.getParameter("id"); //菜单按钮ID

		TTabCofBasic obj = tabCofBasicService.findObjByKey(Integer.parseInt(confid));

		TDict bus = commParamsBusiness.selectByParam("BUS");

		if (obj.getBusi().equals(bus.getCvalue())) {
			//busBussiness.exportCollect(request, response);
		}
	}

	@ResponseBody
	@RequestMapping("/refreshTime.html")
	public int refreshTime(HttpServletRequest request) {
		int task = (int) request.getSession().getAttribute("task");
		return task;
	}

}
