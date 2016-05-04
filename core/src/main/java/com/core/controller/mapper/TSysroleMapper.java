package com.core.controller.mapper;

import com.core.models.TSysrole;

public interface TSysroleMapper extends IBaseMapper<TSysrole> {
	

	/**
	 * 根据机构删除角色
	 * @ author 许西.xu 
	 * @date 2015年4月14日 上午9:57:08  
	 * @param @param unitid
	 * @param @return
	 * @return int 
	 * @throws
	 */
	int deleteByPrimaryUnitid(Long unitid);
	
}