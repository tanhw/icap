package com.business.dict;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.dict.IDictService;
import com.core.models.TDict;
import com.core.models.common.JsonDataWrapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.StringUtil;

@Service
public class DictBusiness {

	@Autowired
	private IDictService dictService;

	/**
	 * 列表
	 * 
	 * @ author 许西
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JsonDataWrapper<TDict> list(HttpServletRequest request)
			throws Exception {

		String ccode = request.getParameter("ccode");
		String cvalue = request.getParameter("cvalue");
		String ctype = request.getParameter("ctype");
		String cdesc = request.getParameter("cdesc");
		String isactive = request.getParameter("isactive");
		String cupcode = request.getParameter("cupcode");
		String unitid = request.getParameter("unitid");
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(SessionHandler.getCurrentUnitId() != null){
			unitid = SessionHandler.getCurrentUnitId().toString();
		}
		
		if (isactive != null && (!isactive.equals(""))) {
			params.put("isactive", isactive);
		} else {
			params.put("isactive", CommonConstant.IsActive.True.toString());
		}
		
		params.put("ctype", ctype);
		params.put("ccode", ccode);
		params.put("cvalue", cvalue);
		params.put("cdesc", cdesc);
		params.put("cupcode", cupcode);
		params.put("unitid", unitid);

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String sortOrder = request.getParameter("order");
		String sortName = request.getParameter("sort");

		Integer pageNum = 1;
		if (page != null)
			pageNum = Integer.parseInt(page);
		Integer pageSize = 20;
		if (rows != null)
			pageSize = Integer.parseInt(rows);

		Order order = null;
		if (StringUtil.checkNull(false, sortOrder, sortName)) {
			if (sortOrder.equals("desc"))
				order = Order.desc(sortName);
			else
				order = Order.asc(sortName);
		}

		RollPage<TDict> data = dictService.findListPageByParams(params, order,
				pageNum, pageSize);

		return new JsonDataWrapper<TDict>(data);
	}

	/**
	 * 选择详情
	 * 
	 * @ author 许西
	 * @param request
	 * @param menucode
	 * @throws Exception
	 */
	public void detail(HttpServletRequest request, String ccode)
			throws Exception {

		// 修改
		request.setAttribute("isModify", "true");

		TDict dict = dictService.findObjByKey(ccode);

		request.setAttribute("dict", dict);
	}

	/**
	 * 保存
	 * 
	 * @ author 许西
	 * @param menu
	 * @param request
	 * @throws Exception
	 */
	public void save(TDict dict, HttpServletRequest request) throws Exception {

		dictService.addBasic(dict); // ADD
	}

	/**
	 * 更新
	 * 
	 * @ author 许西
	 * @param menu
	 * @param request
	 * @throws Exception
	 */
	public void update(TDict dict, HttpServletRequest request) throws Exception {
		dictService.modifyBasic(dict); // update

	}

	/**
	 * 选择删除
	 * 
	 * @ author 许西
	 * @param request
	 * @param menucode
	 * @throws Exception
	 */
	public void del(HttpServletRequest request, String ccode) throws Exception {

		/**
		 * 检查字典
		 */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cupcode", ccode);
		
		Order order = Order.asc("ccode");
		
		RollPage<TDict> rolePage = dictService.findListPageByParams(params, order, 1, 1);
		
		if(rolePage.getRecordSum() != 0){
			throw new Exception("E70001");
		}
		
		/** 删除 **/
		TDict obj = new TDict();
		obj.setCcode(ccode);
		dictService.delBasic(obj);

	}

	/**
	 * 更新数据字典公共map
	 * 
	 * @throws Exception
	 */
	public void updateCommonParamsMap() throws Exception {

		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

		List<TDict> parentDictList = dictService.findParentByUpcode();

		for (TDict obj : parentDictList) {
			Map<String, String> childMap = new HashMap<String, String>();
			List<TDict> childDictList = dictService.findChildByUpcode(obj
					.getCcode());

			for (TDict t : childDictList) {
				childMap.put(t.getCvalue(), t.getCdesc());
			}
			map.put(obj.getCcode(), childMap);
		}

		CommonConstant.updateCommonParamsMap(map);

	}

}
