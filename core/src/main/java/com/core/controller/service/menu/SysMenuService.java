package com.core.controller.service.menu;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.mapper.TSysmenuMapper;
import com.core.controller.service.BaseService;
import com.core.controller.service.role.IRoleMenuInfoService;
import com.core.models.TSysmenu;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;

@Service("sysMenuService")
public class SysMenuService extends BaseService<TSysmenuMapper, TSysmenu>
		implements ISysMenuService {
	
	private TSysmenuMapper mapper;
	
	@Autowired
	private IRoleMenuInfoService roleMenuInfoService;
	
	@Autowired
	public void setTSysmenuMapper(TSysmenuMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	@Override
	public Long addBasic(TSysmenu obj) throws Exception {
		obj.setIsactive(CommonConstant.IsActive.True.toString());
		return (long) mapper.insertSelective(obj);
		
	}

	@Override
	public void modifyBasic(TSysmenu obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}
	
	@Override
	public void delBasic(TSysmenu obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getMenucode());
	}

	@Override
	public Boolean delByRoleMenu(String menuCode) throws Exception {
		
		Boolean ischange = false;
		
		int childMenu = roleMenuInfoService.selectCountByUpMenu(menuCode);
		
		if(childMenu == 0 ){
			roleMenuInfoService.deleteByPrimaryMenuCode(menuCode);
			mapper.deleteByPrimaryKey(menuCode);
			ischange = true;
		}

		return ischange;
	}
	
	@Override
	public List<TSysmenu> findSysmenuByRole(long roleSeq,Integer menutype,String busikind) throws Exception{
		return mapper.selectListByRole(roleSeq,menutype,busikind);
	}
	
	@Override
	public List<TSysmenu> findSysmenuByRoleid(long roleSeq,Integer menutype,String busikind) throws Exception{
		return mapper.selectListByRoleid(roleSeq, menutype, busikind);
	}
	
	@Override
	public void delObjByKeyAry(String[] menucodeAry) throws Exception{
		for (String menuCode : menucodeAry) {
			mapper.deleteByPrimaryKey(menuCode);
		}

	}

}
