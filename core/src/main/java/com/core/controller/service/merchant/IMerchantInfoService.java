package com.core.controller.service.merchant;

import com.core.controller.mapper.TMerchantInfoMapper;
import com.core.controller.service.IBaseService;
import com.core.models.*;

public interface IMerchantInfoService extends
		IBaseService<TMerchantInfoMapper, TMerchantInfo> {

	void modifyTmsBranchidByParams(TPosTmsBind posTmsBind) throws Exception;

	void modifyTmsDataBranchidByParams(TPosTmsData posTmsData) throws Exception;

	void modifyKeyBranchidByParams(TPosKeyInfo posKeyInfo) throws Exception;

	void modifyPosBranchidByParams(TPosInfo posInfo) throws Exception;
	
}
