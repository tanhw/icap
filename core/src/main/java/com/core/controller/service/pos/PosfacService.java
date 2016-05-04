package com.core.controller.service.pos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TPosFacMapper;
import com.core.controller.service.BaseService;
import com.core.models.TPosFac;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;

@Service("posfacService")
public class PosfacService extends BaseService<TPosFacMapper, TPosFac>
		implements IPosacService {
	
	private TPosFacMapper mapper;
	
	@Autowired
	public void setTPosFacMapper(TPosFacMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}

	@Override
	public Long addBasic(TPosFac obj) throws Exception {
		obj.setFactoryid(mapper.getIdValue());
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TPosFac obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void delBasic(TPosFac obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getFactoryid());
		
	}
}
