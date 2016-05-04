package com.core.controller.service.log;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TOpLogMapper;
import com.core.controller.service.BaseService;
import com.core.models.TOpLog;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;

@Service("opLogService")
public class OpLogService extends BaseService<TOpLogMapper, TOpLog> implements
		IOpLogService {
	
	private TOpLogMapper mapper;
	
	@Autowired
	public void setTOpLogMapper(TOpLogMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	
	@Override
	public Long addBasic(TOpLog obj) throws Exception {
		obj.setLogseq(mapper.getIdValue());
		return (long) mapper.insertSelective(obj);
	}
	
	@Override
	public void modifyBasic(TOpLog obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void delBasic(TOpLog obj) throws Exception {
		
	}

}
