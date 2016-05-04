package com.core.controller.service.black;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.controller.common.SessionHandler;
import com.core.controller.mapper.TBlackInfoMapper;
import com.core.controller.service.BaseService;
import com.core.controller.service.dict.IDictService;
import com.core.models.TBlackInfo;
import com.core.models.TDict;
import com.core.models.common.Order;
import com.toolbox.util.ClearNullUtil;
import com.toolbox.util.DateUtil;

@Service("BlackInfoService")
public class BlackInfoService extends BaseService<TBlackInfoMapper, TBlackInfo> implements IBlackInfoService {

	
	private TBlackInfoMapper mapper;
	
	@Autowired
	private IDictService dictService;
	
	@Autowired
	public void setTBlackInfoMapper(TBlackInfoMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}

	/**
	 * 添加
	 */
	@Override
	public Long addBasic(TBlackInfo obj) throws Exception {
		
		
		TDict dict = new TDict();
		dict.setCcode("BLACKVERSION");
		dict.setCvalue(DateUtil.formatTime(DateUtil.getTime(), "yyyyMMddHHmmss"));
		dictService.modifyBasic(dict);
		
		obj.setBlackseq(mapper.getIdValue());
		obj.setCreateTime(DateUtil.getDate());
		return (long) mapper.insertSelective(obj);
	}
	
	/**
	 * 修改
	 */
	@Override
	public void modifyBasic(TBlackInfo obj) throws Exception {
		TDict dict = new TDict();
		dict.setCcode("BLACKVERSION");
		dict.setCvalue(DateUtil.formatTime(DateUtil.getTime(), "yyyyMMddHHmmss"));
		dictService.modifyBasic(dict);
		mapper.updateByPrimaryKeySelective(obj);
		
	}

	@Override
	public void delBasic(TBlackInfo obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getBlackseq());
	}
	
	@Override
	public void addBlackByList(List<TBlackInfo> blackList) throws Exception {
		for (TBlackInfo tBlackInfo : blackList) {

			tBlackInfo.setBlackseq(mapper.getIdValue());
			tBlackInfo.setCreateTime(DateUtil.getDate());
			tBlackInfo.setUnitid(SessionHandler.getCurrentUnitId());

			mapper.insertSelective(tBlackInfo);
		}
	}

	@Override
	public void delBlackByList(List<TBlackInfo> list) throws Exception {

		for (TBlackInfo tBlackInfo : list) {
			mapper.deleteByPrimaryKey(tBlackInfo);
		}
	}
}
