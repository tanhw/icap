package com.core.controller.service.role;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TSysroleMapper;
import com.core.controller.service.BaseService;
import com.core.models.TSysrole;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;

@Service("SysRoleService")
public class SysRoleService extends BaseService<TSysroleMapper, TSysrole>
		implements ISysRoleService {
	
	private TSysroleMapper mapper;
	
	@Autowired
	public void setTSysroleMapper(TSysroleMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	
	@Override
	public Long addBasic(TSysrole obj) throws Exception {
		if(obj != null && obj.getRoleseq() == null) {
			obj.setRoleseq(mapper.getIdValue());
		}
		return (long) mapper.insertSelective(obj);
	}

	public Long getIdValue()throws Exception {
		return mapper.getIdValue();
	}
	
	@Override
	public void modifyBasic(TSysrole obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);

	}

	@Override
	public void delBasic(TSysrole obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getRoleseq());
	}

	@Override
	public void deleteByUnitid(Long unitid) throws Exception {
		mapper.deleteByPrimaryUnitid(unitid);
	} 

}
