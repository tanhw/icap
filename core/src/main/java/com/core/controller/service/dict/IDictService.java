package com.core.controller.service.dict;

import java.util.List;

import com.core.controller.mapper.TDictMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TDict;

public interface IDictService extends IBaseService<TDictMapper, TDict> {

	
	/**
	 * 查询所有父类数据
	 * @return
	 */
	List<TDict> findParentByUpcode() throws Exception;
	
	
	/**
	 * 查询所有子类数据
	 * @param cupcode
	 * @return
	 */
	List<TDict> findChildByUpcode(String cupcode) throws Exception;
	
}
