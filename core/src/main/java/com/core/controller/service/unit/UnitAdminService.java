package com.core.controller.service.unit;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TUnitAdminMapper;
import com.core.controller.service.BaseService;
import com.core.models.TUnitAdmin;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;
import com.toolbox.util.DateUtil;

@Service("unitAdminService")
public class UnitAdminService extends BaseService<TUnitAdminMapper, TUnitAdmin>
		implements IUnitAdminService {
	
	private TUnitAdminMapper mapper;
	
	@Autowired
	public void setTUnitAdminMapper(TUnitAdminMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	/**
	 * 新增
	 */
	@Override
	public Long addBasic(TUnitAdmin obj) throws Exception {
		obj.setUnitadminseq(mapper.getIdValue());
		obj.setCreatetime(DateUtil.getDate());
		return (long) mapper.insertSelective(obj);
	}
	
	/**
	 * 修改
	 * @ author 檀海稳
	 */
	@Override
	public void modifyBasic(TUnitAdmin obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void delBasic(TUnitAdmin obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getUnitadminseq());
	}
	
	@Override
	public void deleteByUnitid(Long unitid) throws Exception {
		mapper.deleteBynitid(unitid);
	}
	

}
