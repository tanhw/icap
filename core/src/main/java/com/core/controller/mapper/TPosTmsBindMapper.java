package com.core.controller.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.core.models.TPosInfo;
import com.core.models.TPosTmsBind;

public interface TPosTmsBindMapper extends IBaseMapper<TPosTmsBind> {
	
	int insertBatchObj(@Param(value="posList") List<TPosInfo> posList, @Param(value="unitid")String unitid, @Param(value="merseq")String merseq, @Param(value="branchid")String branchid, @Param(value="filename")String filename);
	
	int deleteBatchObj(@Param(value="posList") List<TPosInfo> posList);
	
	int deleteByFilename(String filename);
}