package com.core.controller.service.unit;

import com.core.controller.mapper.TUnitInfoMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TSysrole;
import com.core.models.TUnitAdmin;
import com.core.models.TUnitInfo;

public interface IUnitInfoService extends
		IBaseService<TUnitInfoMapper, TUnitInfo> {
	
	public void addUnitByadmin(TUnitAdmin unitAdmin, TSysrole sysrole, String[] menucodeList)throws Exception;
	
	void modifyOpAndRole(TUnitAdmin operator, TSysrole role) throws Exception;
}
