package com.core.controller.mapper;

import java.util.List;

import com.core.models.TDict;

public interface TDictMapper extends IBaseMapper<TDict> {

	/**
	 * 查询所有父类数据
	 * @return
	 */
	List<TDict> selectParentByUpcode();
	
	/**
	 * 查询所有子类数据
	 * @param cupcode
	 * @return
	 */
	List<TDict> selectChildByUpcode(String cupcode);
}