package com.core.controller.service.dict;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.mapper.TDictMapper;
import com.core.controller.service.BaseService;
import com.core.models.TDict;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;

@Service("dictService")
public class DictService extends BaseService<TDictMapper, TDict> implements
		IDictService {
	
	private TDictMapper mapper;
	
	@Autowired
	public void setTDictMapper(TDictMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	@Override
	public Long addBasic(TDict obj) throws Exception {
		obj.setIsactive(CommonConstant.IsActive.True.toString());
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TDict obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void delBasic(TDict obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getCcode());
	}

	@Override
	public List<TDict> findParentByUpcode() throws Exception {
		return mapper.selectParentByUpcode();
	}

	@Override
	public List<TDict> findChildByUpcode(String cupcode) throws Exception {
		return mapper.selectChildByUpcode(cupcode);
	}

	
	
	
	
}
