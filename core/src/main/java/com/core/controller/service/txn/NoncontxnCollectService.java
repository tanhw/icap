package com.core.controller.service.txn;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TNoncontxnCollectMapper;
import com.core.controller.service.BaseService;
import com.core.models.TNoncontxnCollect;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;

@Service("noncontxnCollectService")
public class NoncontxnCollectService extends
		BaseService<TNoncontxnCollectMapper, TNoncontxnCollect> {

	private TNoncontxnCollectMapper mapper;

	@Autowired
	public void setTNoncontxnCollectMapper(TNoncontxnCollectMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}

	@Override
	public Long addBasic(TNoncontxnCollect obj) throws Exception {

		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TNoncontxnCollect obj) throws Exception {

	}

	@Override
	public void delBasic(TNoncontxnCollect obj) throws Exception {

	}
}
