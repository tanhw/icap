package com.core.controller.mapper;

import com.core.models.*;

public interface TMerchantInfoMapper extends IBaseMapper<TMerchantInfo> {

	int updateTmsBranchidByParams(TPosTmsBind posTmsBind);
	
	int updateTmsDataBranchidByParams(TPosTmsData posTmsData);
	
	int updateKeyBranchidByParams(TPosKeyInfo posKeyInfo);
	
	int updatePosBranchidByParams(TPosInfo posInfo);
	
}