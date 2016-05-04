package com.core.controller.service.pos;

import java.util.List;

import com.core.controller.mapper.TPosTmsBindMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TPosInfo;
import com.core.models.TPosTmsBind;

public interface IPosTmsBindService extends IBaseService<TPosTmsBindMapper, TPosTmsBind>{

	Long addBatchObj(List<TPosInfo> posList, String unitid, String merseq, String branchid, String filename)throws Exception ;
	
	void delBatchObj(List<TPosInfo> posList)throws Exception ;
	
	void delByfilename(String filename) throws Exception;
}
