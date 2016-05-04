package com.core.controller.mapper;

import com.core.models.TUnitAdmin;

public interface TUnitAdminMapper extends IBaseMapper<TUnitAdmin> {

	
	
	/**
	 * 根据机构删除机构操作员及管理员
	 * @ author 许西.xu 
	 * @date 2015年4月14日 上午9:12:17  
	 * @param @param unitid
	 * @param @return
	 * @return int 
	 * @throws
	 */
	 int deleteBynitid(Long unitid);
	
}