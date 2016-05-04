package com.business.comm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.common.SessionHandler;
import com.core.controller.service.dict.IDictService;
import com.core.controller.service.tbl.ISysParaService;
import com.core.models.TDict;
import com.core.models.common.Order;

@Service
public class CommParamsBusiness {

	@Autowired
	private IDictService service;
	
	@Autowired
	private ISysParaService blSysParaService;
	
	
	/**
	 * 根据主键，返回当个OBJ字典数据
	 * 
	 * @param param 数据字典类型的 ccode  
	 * @return
	 * @throws Exception
	 */
	public TDict selectByParam(String param) throws Exception{
		TDict obj = service.findObjByKey(param);
		return obj;
	}

	/**
	 * 根据数据字典父级类型查询子OBJ字典数据
	 * 
	 * @param param 数据字典父级类型的 ccode  
	 * @return 返回父级的子字典数据
	 * @throws Exception
	 */
	public List<TDict> selectByUPParam(String param) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cupcode", param);
		params.put("isactive", CommonConstant.IsActive.True.toString());
		params.put("unitid", SessionHandler.getCurrentUnitId());
		
		Order order = Order.asc("cupcode");
		List<TDict> list = service.findListByParams(params, order);
		return list;
	}
	
	
	/**
	 * 根据主键，返回当个OBJ字典数据,结果设置request中
	 * 
	 * @param param 数据字典类型的 ccode 
	 * @param request
	 * @param RestName request获取标识
	 * @throws Exception
	 */
	public void selectByParam(String param,HttpServletRequest request, String RestName) throws Exception{
		TDict obj = service.findObjByKey(param);
		if(obj != null)
		request.setAttribute(RestName, obj.getCvalue());
	}
	
	/**
	 * 
	 * 根据数据字典父级类型查询子OBJ字典数据,结果设置request中
	 * 
	 * @param param  数据字典父级类型的 ccode  ,结果设置request中
	 * @param request
	 * @param RestlistName request获取标识
	 * @throws Exception
	 */
	public void selectByUPParam(String param,HttpServletRequest request, String RestlistName) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cupcode", param);
		params.put("isactive", CommonConstant.IsActive.True.toString());
		params.put("unitid", SessionHandler.getCurrentUnitId());
		Order order = Order.asc("corder");
		List<TDict> list = service.findListByParams(params, order);
		request.setAttribute(RestlistName, list);
	}
	
	/**
	 * 
	 * 根据数据字典父级类型查询子OBJ字典数据,结果设置request中
	 * 
	 * @param param  数据字典父级类型的 ccode  ,结果设置request中
	 * @param request
	 * @param RestlistName request获取标识
	 * @throws Exception
	 */
	public void selectByUPParam(String param,HttpServletRequest request, String RestlistName, String cType) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cupcode", param);
		params.put("ctype", cType);
		params.put("unitid", SessionHandler.getCurrentUnitId());
		Order order = Order.asc("corder");
		List<TDict> list = service.findListByParams(params, order);
		request.setAttribute(RestlistName, list);
	}
	
}
