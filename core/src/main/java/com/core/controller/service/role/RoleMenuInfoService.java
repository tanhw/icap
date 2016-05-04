package com.core.controller.service.role;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TRoleMenuInfoMapper;
import com.core.controller.service.BaseService;
import com.core.models.TRoleMenuInfo;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;

@Service("roleMenuInfoService")
public class RoleMenuInfoService extends
		BaseService<TRoleMenuInfoMapper, TRoleMenuInfo> implements
		IRoleMenuInfoService {
	
	private TRoleMenuInfoMapper mapper;
	
	@Autowired
	public void setTRoleMenuInfoMapper(TRoleMenuInfoMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	
	@Override
	public Long addBasic(TRoleMenuInfo obj) throws Exception {
		obj.setRoleMenuSeq(mapper.getIdValue());
		return (long) mapper.insertSelective(obj);
	}
	
	@Override
	public void modifyBasic(TRoleMenuInfo obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void delBasic(TRoleMenuInfo obj) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRoleMenuArray(List<String> menuCodeList, long roleseq) throws Exception {

			for (String menuCode : menuCodeList) {
				TRoleMenuInfo obj = new TRoleMenuInfo();
				obj.setMenuCode(menuCode);
				obj.setRoleSeq(roleseq);
				obj.setRoleMenuSeq(mapper.getIdValue());
				mapper.insertSelective(obj);
			}

	}
	
	@Override
	public void delRoleMenuArray(List<String> menuCodeList, long roleseq) throws Exception {

			for (String menuCode : menuCodeList) {
				TRoleMenuInfo obj = new TRoleMenuInfo();
				obj.setMenuCode(menuCode);
				obj.setRoleSeq(roleseq);
				mapper.deleteRoleMenuWhere(obj);
			}

	}


	@Override
	public int selectCountByUpMenu(String menuCode) throws Exception {
		
		return mapper.selectCountByUpMenu(menuCode);
	}
	
	@Override
	public void deleteByPrimaryMenuCode(String menuCode) throws Exception {
		mapper.deleteByPrimaryMenuCode(menuCode);
	}

	@Override
	public int deleteByParamsRole(String menucode, Integer roletype) {
		return mapper.deleteByParamsRole(menucode, roletype);
	}
	
	@Override
	public int deleteByUnitid(Long unitid) {
		return mapper.deleteByUnitid(unitid);
	}
	
}
