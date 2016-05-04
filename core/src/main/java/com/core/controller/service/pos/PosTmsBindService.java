package com.core.controller.service.pos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.mapper.TPosTmsBindMapper;
import com.core.controller.service.BaseService;
import com.core.models.TPosInfo;
import com.core.models.TPosTmsBind;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;

@Service
public class PosTmsBindService extends BaseService<TPosTmsBindMapper, TPosTmsBind> implements IPosTmsBindService{

private TPosTmsBindMapper mapper;
	
	@Autowired
	public void setBrandBasicMapper(TPosTmsBindMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}
	
	/**
	 * 增加
	 */
	@Override
	public Long addBasic(TPosTmsBind obj) throws Exception {
		return (long) mapper.insertSelective(obj);
	}
	
	/**
	 * 修改
	 */
	@Override
	public void modifyBasic(TPosTmsBind obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
	}
	
	/**
	 * 删除
	 */
	@Override
	public void delBasic(TPosTmsBind obj) throws Exception {
		mapper.deleteByPrimaryKey(obj);
	}
	
	@Override
	public Long addBatchObj(List<TPosInfo> posList, String unitid,
			String merseq, String branchid, String filename) throws Exception  {
		mapper.deleteBatchObj(posList);
		return (long) mapper.insertBatchObj(posList, unitid, merseq, branchid, filename);
	}
	
	@Override
	public void delBatchObj(List<TPosInfo> posList)throws Exception  {
		mapper.deleteBatchObj(posList);
	}

	@Override
	public void delByfilename(String filename) throws Exception {
		mapper.deleteByFilename(filename);
		
	}

}
