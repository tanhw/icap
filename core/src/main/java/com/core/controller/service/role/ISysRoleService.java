package com.core.controller.service.role;

import com.core.controller.mapper.TSysroleMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TSysrole;

public interface ISysRoleService extends IBaseService<TSysroleMapper, TSysrole> {
	
	public Long getIdValue()throws Exception ;

	void deleteByUnitid(Long unitid) throws Exception;
	
}
