package com.core.controller.service.cardbin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TBankCardbinMapper;
import com.core.controller.service.BaseService;
import com.core.models.TBankCardbin;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;
import com.toolbox.util.DateUtil;

@Service("bankCardbinService")
public class BankCardbinService extends
		BaseService<TBankCardbinMapper, TBankCardbin> implements
		IBankCardbinService {
	
	private TBankCardbinMapper mapper;
	
	@Autowired
	public void setTBankCardbinMapper(TBankCardbinMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	@Override
	public Long addBasic(TBankCardbin obj) throws Exception {
		obj.setBinseq(mapper.getIdValue());
		obj.setCreatetime(DateUtil.getDate());
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TBankCardbin obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void delBasic(TBankCardbin obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getBinseq());
	}

}
