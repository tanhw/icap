package com.core.controller.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.core.models.TSysmenu;

public interface TSysmenuMapper extends IBaseMapper<TSysmenu>{
 
	List<TSysmenu> selectListByRole(@Param(value="roleseq") Long roleseq,@Param(value="menutype") Integer menutype,@Param(value="menukind") String menukind);

	List<TSysmenu> selectListByRoleid(@Param(value="roleseq") Long roleseq,@Param(value="menutype") Integer menutype,@Param(value="menukind") String menukind);

}