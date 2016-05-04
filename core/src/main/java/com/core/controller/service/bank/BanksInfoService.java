package com.core.controller.service.bank;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TBanksInfoMapper;
import com.core.controller.service.BaseService;
import com.core.models.TBanksInfo;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;
import com.toolbox.util.DateUtil;

@Service("banksInfoService")
public class BanksInfoService extends BaseService<TBanksInfoMapper, TBanksInfo>
		implements IBanksInfoService {
	
	private TBanksInfoMapper mapper;
	
	@Autowired
	public void setTBanksInfoMapper(TBanksInfoMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	@Override
	public Long addBasic(TBanksInfo obj) throws Exception {
		obj.setBankid(mapper.getIdValue());
		obj.setCreatetime(DateUtil.getDate());
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TBanksInfo obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void delBasic(TBanksInfo obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getBankid());
	}

}
