package com.core.controller.service.pos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.mapper.TPosInfoMapper;
import com.core.controller.service.BaseService;
import com.core.models.TPosInfo;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;
import com.toolbox.util.DateUtil;

@Service("PosInfoService")
public class PosInfoService extends BaseService<TPosInfoMapper, TPosInfo>
		implements IPosInfoService {
	
	private TPosInfoMapper mapper;
	
	@Autowired
	public void setBrandBasicMapper(TPosInfoMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	/**
	 * 增加
	 */
	@Override
	public Long addBasic(TPosInfo obj) throws Exception {
		obj.setBatchno("000001");
		obj.setPostraceno("000001");
		obj.setCreatetime(DateUtil.getDate());
		obj.setTmkdownflag(CommonConstant.TmkDownFlag.FALSE.toString());
		obj.setKeyadownflag(CommonConstant.KeyaDownFlag.FALSE.toString());
		return (long) mapper.insertSelective(obj);
	}
	
	/**
	 * 修改
	 */
	@Override
	public void modifyBasic(TPosInfo obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
	}
	
	/**
	 * 删除
	 */
	@Override
	public void delBasic(TPosInfo obj) throws Exception {
		mapper.deleteByPrimaryKey(obj);
	}
	
}
