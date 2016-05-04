package com.core.controller.service.unit;

import com.core.controller.mapper.TUnitAdminMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TUnitAdmin;

public interface IUnitAdminService extends
		IBaseService<TUnitAdminMapper, TUnitAdmin> {

	void deleteByUnitid(Long unitid) throws Exception;

}
