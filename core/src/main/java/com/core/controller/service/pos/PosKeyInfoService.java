package com.core.controller.service.pos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TPosKeyInfoMapper;
import com.core.controller.service.BaseService;
import com.core.models.TPosKeyInfo;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;

@Service("posKeyInfoService")
public class PosKeyInfoService extends
		BaseService<TPosKeyInfoMapper, TPosKeyInfo> implements
		IPosKeyInfoService {
	
	private TPosKeyInfoMapper mapper;
	
	@Autowired
	public void setTPosKeyInfoMapper(TPosKeyInfoMapper mapper){
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	/**
	 * 添加
	 */
	@Override
	public Long addBasic(TPosKeyInfo obj) throws Exception {
		return (long) mapper.insertSelective(obj);
	}
	
	/**
	 * 修改
	 */
	@Override
	public void modifyBasic(TPosKeyInfo obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}
	
	/**
	 * 删除
	 */
	@Override
	public void delBasic(TPosKeyInfo obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getPosid());
	}

}
