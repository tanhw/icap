package com.core.controller.mapper;

import com.core.models.TSysPara;

public interface TSysParaMapper extends IBaseMapper<TSysPara> {
	
	/**
	 * 查询
	 * 
	 * @param seq
	 * @return
	 */
	TSysPara selectBlSysByPrimaryKey(TSysPara tblSysPara);
}