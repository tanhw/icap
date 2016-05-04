package com.core.controller.service.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TBatDayCutCtlMapper;
import com.core.controller.service.BaseService;
import com.core.models.TBatDayCutCtl;

@Service("batCutCtlService")
public class BatCutCtlService extends BaseService<TBatDayCutCtlMapper, TBatDayCutCtl> implements
		IBatCutCtlService {

	private TBatDayCutCtlMapper mapper;
	
	@Autowired
	public void setTBatDayCutCtlMapper(TBatDayCutCtlMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	@Override
	public Long addBasic(TBatDayCutCtl obj) throws Exception {
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TBatDayCutCtl obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);		
	}

	@Override
	public void delBasic(TBatDayCutCtl obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
