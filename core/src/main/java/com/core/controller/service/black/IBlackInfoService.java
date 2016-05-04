package com.core.controller.service.black;

import com.core.controller.mapper.TBlackInfoMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TBlackInfo;

import java.util.List;

public interface IBlackInfoService extends
		IBaseService<TBlackInfoMapper, TBlackInfo> {
	
	public void addBlackByList(List<TBlackInfo> blackList)throws Exception;
	
	public void  delBlackByList(List<TBlackInfo> list)throws Exception;
	
}
