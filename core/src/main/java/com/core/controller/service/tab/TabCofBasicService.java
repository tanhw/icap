package com.core.controller.service.tab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TTabCofBasicMapper;
import com.core.controller.service.BaseService;
import com.core.models.TTabCofBasic;

import java.util.List;

@Service
public class TabCofBasicService extends BaseService<TTabCofBasicMapper,TTabCofBasic> implements ITabCofBasicService {

	private TTabCofBasicMapper mapper;

	@Autowired
	public TabCofBasicService(TTabCofBasicMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}

	@Override
	public Long addBasic(TTabCofBasic obj) throws Exception {

		obj.setConfid(Integer.parseInt(mapper.getIdValue().toString()));
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TTabCofBasic obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void delBasic(TTabCofBasic obj) throws Exception {
		mapper.deleteByPrimaryKey(obj);
	}

	@Override
	public List<TTabCofBasic> findByUnitid(String unitid) throws Exception {
		return mapper.selectByUnitid(unitid);
	}

}
