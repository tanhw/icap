package com.core.controller.service.unit;

import com.core.controller.mapper.TCheckInfoMapper;
import com.core.controller.service.BaseService;
import com.core.models.TCheckInfo;
import com.toolbox.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CheckInfoService extends BaseService<TCheckInfoMapper,TCheckInfo> implements ICheckInfoService{


    private TCheckInfoMapper mapper;

    @Autowired
    public CheckInfoService(TCheckInfoMapper mapper) {
        this.mapper = mapper;
        super.mapper = mapper;
    }

    @Override
    public Long addBasic(TCheckInfo obj) throws Exception {
        obj.setId(mapper.getIdValue());
        obj.setCreatetime(DateUtil.getTime());
        return (long) mapper.insert(obj);
    }

    @Override
    public void modifyBasic(TCheckInfo obj) throws Exception {

    }

    @Override
    public void delBasic(TCheckInfo obj) throws Exception {
        mapper.deleteByPrimaryKey(obj.getId());
    }

    @Override
    public void delCreateDate(Date createTime) throws Exception {
        mapper.deleteByPrimaryDate(createTime);
    }

}
