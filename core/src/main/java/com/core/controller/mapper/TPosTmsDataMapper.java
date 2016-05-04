package com.core.controller.mapper;

import org.apache.ibatis.annotations.Param;

import com.core.models.TPosTmsData;

public interface TPosTmsDataMapper extends IBaseMapper<TPosTmsData> {
	/**
	 * 根据posid关联查询
	 * @param posid
	 * @return 
	 */
	
	TPosTmsData selectByPosId(@Param("posid") String posid);
	
}