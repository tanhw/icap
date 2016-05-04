package com.core.controller.service.unit;

import com.core.controller.mapper.TCheckInfoMapper;
import com.core.controller.service.IBaseService;
import com.core.models.TCheckInfo;

import java.util.Date;

public interface ICheckInfoService extends IBaseService<TCheckInfoMapper,TCheckInfo>{

    public void delCreateDate(Date createTime) throws Exception;
}
