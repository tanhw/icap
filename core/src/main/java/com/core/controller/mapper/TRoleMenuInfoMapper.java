package com.core.controller.mapper;

import org.apache.ibatis.annotations.Param;

import com.core.models.TRoleMenuInfo;

public interface TRoleMenuInfoMapper extends IBaseMapper<TRoleMenuInfo> {

	/**
	 * 物理删除记录
	 * 
	 * @ author sys
	 * @param seq
	 * @return
	 */
	int deleteRoleMenuWhere(TRoleMenuInfo obj);
	
    /**
     * 查询 符合条件的记录总数
     * @ author sys
     * @param params
     * @return
     */
    int selectCountByUpMenu(String menuCode);
    
	/**
	 * 物理删除记录
	 * @ author sys
	 * @param seq
	 * @return
	 */
	int deleteByPrimaryMenuCode(String enuCode);
	
	
	/**
	 * 根据机构角色删除关联
	 * @ author 许西.xu 
	 * @date 2015年4月14日 上午9:43:12  
	 * @param @param roleSeq
	 * @param @return
	 * @return int 
	 * @throws
	 */
	int deleteByUnitid(Long unitid);
	
	
	
	/**
	 * 物理删除记录
	 * @ author sys
	 * @param seq
	 * @return
	 */
	int deleteByParamsRole(@Param(value="menucode") String menucode, @Param(value="roletype")Integer roletype);
	
	
	
	
}