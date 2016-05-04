package com.core.controller.service.merchant;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.mapper.TMeropInfoMapper;
import com.core.controller.service.BaseService;
import com.core.models.TMeropInfo;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;
import com.toolbox.util.DateUtil;

@Service("meropInfoService")
public class MeropInfoService extends BaseService<TMeropInfoMapper, TMeropInfo> implements IMeropInfoService {

	
	private TMeropInfoMapper mapper;
	
	@Autowired
	public void setTMeropInfoMapper(TMeropInfoMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}

	/**
	 * 添加
	 */
	@Override
	public Long addBasic(TMeropInfo obj) throws Exception {
		
		obj.setMeropseq(mapper.getIdValue());
		obj.setCreatetime(DateUtil.getDate());
		obj.setIsactive(CommonConstant.IsActive.True.toString());
		return (long) mapper.insertSelective(obj);
	}
	
	/**
	 * 修改
	 */
	@Override
	public void modifyBasic(TMeropInfo obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void delBasic(TMeropInfo obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getMeropseq());
	}

}
