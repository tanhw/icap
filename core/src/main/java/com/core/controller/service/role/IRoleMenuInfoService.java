package com.core.controller.service.role;

import java.util.List;

import com.core.controller.mapper.TRoleMenuInfoMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TRoleMenuInfo;

public interface IRoleMenuInfoService extends
		IBaseService<TRoleMenuInfoMapper, TRoleMenuInfo> {
	
	/**
	 * 批量增加
	 * @param menuCodeList
	 * @param roleseq
	 * @throws Exception
	 */
	public void addRoleMenuArray(List<String> menuCodeList, long roleseq) throws Exception;
	
	/**
	 * 批量删除
	 * @param menuCodeList
	 * @param roleseq
	 * @throws Exception
	 */
	void delRoleMenuArray(List<String> menuCodeList, long roleseq) throws Exception ;
	
	public int selectCountByUpMenu(String menuCode) throws Exception;
	
	
	public void deleteByPrimaryMenuCode(String menuCode) throws Exception;
	
	public int deleteByParamsRole(String menucode, Integer roletype);

	int deleteByUnitid(Long unitid);
	
}
