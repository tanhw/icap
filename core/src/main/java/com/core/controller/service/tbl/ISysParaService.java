package com.core.controller.service.tbl;

import com.core.controller.mapper.TSysParaMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TSysPara;

public interface ISysParaService extends IBaseService<TSysParaMapper, TSysPara>{
	
	TSysPara findBlSysByKey(TSysPara tblSysPara)throws Exception;

	
	TSysPara selectBlSysParaByParam(String uName, String paraName, String paraNo)throws Exception;

	String getParaValue(String uname, String paraname);
	
}
