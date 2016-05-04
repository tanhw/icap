package com.core.controller.service.black;

import com.core.controller.mapper.TCardInfoMapper;
import com.core.controller.service.BaseService;
import com.core.models.TCardInfo;
import com.toolbox.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardInfoService extends BaseService<TCardInfoMapper, TCardInfo> implements ICardInfoService {


	private TCardInfoMapper mapper;

	@Autowired
	public void setCardInfoMapper (TCardInfoMapper mapper) {
		this.mapper = mapper;
		super.mapper = mapper;
	}


	@Override
	public Long addBasic(TCardInfo obj) throws Exception {
		obj.setCreateTime(DateUtil.getTime());
		obj.setCardid(mapper.getIdValue());
		return (long) mapper.insertSelective(obj);
	}

	@Override
	public void modifyBasic(TCardInfo obj) throws Exception {
		mapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void delBasic(TCardInfo obj) throws Exception {
		mapper.deleteByPrimaryKey(obj.getCardid());
	}
}
