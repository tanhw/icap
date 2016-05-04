package com.core.controller.service.merchant;

import com.core.controller.mapper.TMerchantInfoMapper;
import com.core.controller.service.BaseService;
import com.core.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("merchantInfoService")
public class MerchantInfoService extends
		BaseService<TMerchantInfoMapper, TMerchantInfo> implements
		IMerchantInfoService {
	
	private TMerchantInfoMapper mapper;
	
	@Autowired
	public void setTMerchantInfoMapper(TMerchantInfoMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	/**
	 * 添加
	 */
	@Override
	public Long addBasic(TMerchantInfo obj) throws Exception {
		
		obj.setMerseq(mapper.getIdValue());
		return (long) mapper.insertSelective(obj);
	}
	
	/**
	 * 修改
	 */
	@Override
	public void modifyBasic(TMerchantInfo obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void delBasic(TMerchantInfo obj) throws Exception {
		// TODO Auto-generated method stub
		mapper.deleteByPrimaryKey(obj.getMerseq());
	}

	@Override
	public void modifyTmsBranchidByParams(TPosTmsBind posTmsBind)
			throws Exception {
		mapper.updateTmsBranchidByParams(posTmsBind);
		
	}

	@Override
	public void modifyTmsDataBranchidByParams(TPosTmsData posTmsData)
			throws Exception {
		mapper.updateTmsDataBranchidByParams(posTmsData);
		
	}

	@Override
	public void modifyKeyBranchidByParams(TPosKeyInfo posKeyInfo)
			throws Exception {
		mapper.updateKeyBranchidByParams(posKeyInfo);
		
	}

	@Override
	public void modifyPosBranchidByParams(TPosInfo posInfo) throws Exception {
		mapper.updatePosBranchidByParams(posInfo);

	}
	
	
	
}
