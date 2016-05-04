package com.core.controller.service.tab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TUnitTabConfMapper;
import com.core.controller.service.BaseService;
import com.core.models.TUnitTabConf;

@Service
public class UnitTabConfService extends
		BaseService<TUnitTabConfMapper, TUnitTabConf> implements
		IUnitTabConfService {
	
	private TUnitTabConfMapper mapper;

	@Autowired
	public UnitTabConfService(TUnitTabConfMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	@Override
	public Long addBasic(TUnitTabConf obj) throws Exception {
		obj.setConfunitid(Integer.parseInt(mapper.getIdValue().toString()));
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TUnitTabConf obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delBasic(TUnitTabConf obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getConfunitid());
	}

}
