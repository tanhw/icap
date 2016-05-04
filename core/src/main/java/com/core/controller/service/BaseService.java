/**
 * 
 */
package com.core.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.IBaseMapper;
import com.core.models.common.Order;
import com.core.models.common.RollPage;
import com.toolbox.util.ClearNullUtil;

/**
 * 基本Service实现类
 * @ author sys
 *
 */
@Service
public abstract class BaseService<T extends IBaseMapper<M> ,M> implements IBaseService<T, M> {
	
	protected @Value("${global.pageSize}") Integer pageSizeDefault;
	
	protected T mapper;
	
	public void setMapper(T mapper) {
		this.mapper = mapper;
	}
	
	public abstract Long addBasic(M obj) throws Exception;

	public abstract void modifyBasic(M obj) throws Exception;
	
	public abstract void delBasic(M obj) throws Exception;
	
	
	public  <K> M findObjByKey(K seq) throws Exception{
		return mapper.selectByPrimaryKey(seq);
	}
	
	public M findObj(Map<String, Object> params) throws Exception{
		ClearNullUtil.mapClear(params);
		return mapper.selectByParams(params);
	}
	
	public List<M> findListByParams(Map<String, Object> params, Order order)throws Exception{
		ClearNullUtil.mapClear(params);
		String orderParam = (order == null) ? null : order.toString();
		return mapper.selectListByParams(params, null, null, orderParam);
	}

	public RollPage<M> findListPageByParams(Map<String, Object> params,
			Order order, Integer pageNum, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		
		ClearNullUtil.mapClear(params);
		String orderParam=(order==null)?null:order.toString();
		
		Integer recordSum= mapper.selectCountByParams(params);
		
		RollPage<M> rollPage=new RollPage<M>();
		
		rollPage.setRecordSum(recordSum);
		
		if (pageSize==null) 
			rollPage.setPageSize(pageSizeDefault);
		else
			rollPage.setPageSize(pageSize);
		
		pageNum=(pageNum==null)?1:pageNum;
		
		rollPage.setPageNum(pageNum);
		
		Integer startIndex=(rollPage.getPageNum() - 1) * rollPage.getPageSize();
		Integer endIndex=startIndex+rollPage.getPageSize();
		
		if (recordSum>0) {
			rollPage.setRecordList(mapper.selectListByParams(params, startIndex, endIndex, orderParam));
		}
		else{
			rollPage.setRecordList(new ArrayList<M>());
		}
		return rollPage;
		
	}
	
}
