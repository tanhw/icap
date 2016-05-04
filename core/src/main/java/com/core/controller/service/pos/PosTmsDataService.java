package com.core.controller.service.pos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TPosTmsBindMapper;
import com.core.controller.mapper.TPosTmsDataMapper;
import com.core.controller.service.BaseService;
import com.core.models.TPosTmsData;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;

@Service
public class PosTmsDataService extends BaseService<TPosTmsDataMapper, TPosTmsData> implements IPosTmsDataService{

	private TPosTmsDataMapper mapper;

	@Autowired
	private TPosTmsBindMapper posTmsBindMapper;

	@Autowired
	public void setBrandBasicMapper(TPosTmsDataMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	/**
	 * 增加
	 */
	@Override
	public Long addBasic(TPosTmsData obj) throws Exception {
		return (long) mapper.insertSelective(obj);
	}
	
	/**
	 * 修改
	 */
	@Override
	public void modifyBasic(TPosTmsData obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
	}
	
	/**
	 * 删除
	 */
	@Override
	public void delBasic(TPosTmsData obj) throws Exception {
		mapper.deleteByPrimaryKey(obj);
		posTmsBindMapper.deleteByFilename(obj.getFilename());
	}
	
	/**
	 * 通过posid关联查询
	 * @return
	 */

	@Override
	public TPosTmsData findByPosId(String posid){
		return mapper.selectByPosId(posid);
	}
	
	
}
