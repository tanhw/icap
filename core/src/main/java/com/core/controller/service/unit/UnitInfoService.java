package com.core.controller.service.unit;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.common.PasswordHandler;
import com.core.controller.mapper.TUnitInfoMapper;
import com.core.controller.service.BaseService;
import com.core.controller.service.merchant.IMerchantInfoService;
import com.core.controller.service.role.IRoleMenuInfoService;
import com.core.controller.service.role.ISysRoleService;
import com.core.models.TRoleMenuInfo;
import com.core.models.TSysrole;
import com.core.models.TUnitAdmin;
import com.core.models.TUnitInfo;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;
import com.toolbox.util.DateUtil;

@Service("unitInfoService")
public class UnitInfoService extends BaseService<TUnitInfoMapper, TUnitInfo>
		implements IUnitInfoService {
	
	private TUnitInfoMapper mapper;
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	@Autowired
	private IRoleMenuInfoService roleMenuInfoService;
	
	@Autowired
	private IUnitAdminService unitAdminService;
	
	@Autowired
	private IMerchantInfoService merchantService;
	
	@Autowired
	public void setTUnitInfoMapper(TUnitInfoMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	@Override
	public Long addBasic(TUnitInfo obj) throws Exception {
		obj.setCreatetime(DateUtil.getDate());
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TUnitInfo obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void delBasic(TUnitInfo obj) throws Exception {
		roleMenuInfoService.deleteByUnitid(obj.getUnitid());
		sysRoleService.deleteByUnitid(obj.getUnitid());
		unitAdminService.deleteByUnitid(obj.getUnitid());
		mapper.deleteByPrimaryKey(obj.getUnitid());
	}
	
	@Override
	public void addUnitByadmin(TUnitAdmin unitAdmin, TSysrole sysrole, String[] menucodeList)throws Exception {
		Long roleseq = sysRoleService.getIdValue();
		
		/**
		 * 新增角色
		 */
		sysrole.setRoleseq(roleseq);
		sysRoleService.addBasic(sysrole);
		
		/**  
		 * 增加机构管理员
		 */
		unitAdmin.setUnitadminseq(mapper.getIdValue());
		unitAdmin.setRoeseq(roleseq);
		unitAdmin.setIsactive(CommonConstant.IsActive.True.toString());
		unitAdminService.addBasic(unitAdmin);
		
		/**
		 * 角色增加权限
		 */
		for (String menuCode : menucodeList) {
			TRoleMenuInfo obj = new TRoleMenuInfo();
			obj.setRoleMenuSeq(mapper.getIdValue());
			obj.setMenuCode(menuCode);
			obj.setRoleSeq(roleseq);
			roleMenuInfoService.addBasic(obj);
		}
		
		
	}

	
	/**
	 * 更新角色信息及操作员信息
	 * @ author 许西.xu 
	 * @date 2015年4月12日 下午4:05:30  
	 * @param @param operator
	 * @param @param role
	 * @param @throws Exception
	 * @return void 
	 * @throws
	 */
	@Override
	public void modifyOpAndRole(TUnitAdmin operator, TSysrole role)throws Exception{
		sysRoleService.modifyBasic(role);//更新角色信息
		if(operator.getUnitadminseq() == null ){//判断是否是新增操作员
			/**  
			 * 增加机构管理员
			 */
			operator.setUnitadminseq(mapper.getIdValue());
			operator.setRoeseq(role.getRoleseq());
			operator.setIsactive(CommonConstant.IsActive.True.toString());
			operator.setLoginpwd(PasswordHandler.getPassword(operator.getLoginpwd()));
			unitAdminService.addBasic(operator);
		}else{
			unitAdminService.modifyBasic(operator);
		}
		
	}

}
