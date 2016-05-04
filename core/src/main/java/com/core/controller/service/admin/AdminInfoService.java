package com.core.controller.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TAdminInfoMapper;
import com.core.controller.service.BaseService;
import com.core.models.TAdminInfo;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;
import com.toolbox.util.DateUtil;

@Service("adminInfoService")
public class AdminInfoService extends BaseService<TAdminInfoMapper, TAdminInfo>
		implements IAdminInfoService {

	private TAdminInfoMapper mapper;

	@Autowired
	public void setTAdminInfoMapper(TAdminInfoMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}

	@Override
	public Long addBasic(TAdminInfo obj) throws Exception {
		obj.setAdminseq(mapper.getIdValue());
		obj.setCreatetime(DateUtil.getDate());
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TAdminInfo obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);

	}

	@Override
	public void delBasic(TAdminInfo obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getAdminseq());
	}

}
