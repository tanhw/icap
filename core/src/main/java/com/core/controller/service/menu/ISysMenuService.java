package com.core.controller.service.menu;

import java.util.List;

import com.core.controller.mapper.TSysmenuMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TSysmenu;

public interface ISysMenuService extends IBaseService<TSysmenuMapper, TSysmenu>{
	
	
	/**
	 * 根据roleseq查询菜单
	 * @param roleSeq
	 * @return
	 */
	List<TSysmenu> findSysmenuByRole(long roleSeq,Integer menutype,String busikind) throws Exception;

	/**
	 * 根据roleseq查询菜单
	 * @param roleSeq
	 * @return
	 */
	List<TSysmenu> findSysmenuByRoleid(long roleSeq,Integer menutype,String busikind) throws Exception;

	/**
	 * 复选删除
	 * @param menucodeAry
	 */
	void delObjByKeyAry(String[] menucodeAry) throws Exception;
	
	
	/**
	 * 级联删除权限
	 * @param obj
	 * @throws Exception
	 */
	public Boolean delByRoleMenu(String menuCode) throws Exception ;
	
}
