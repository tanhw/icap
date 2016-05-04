package com.core.controller.service.bat;

import com.core.controller.mapper.TBatTaskMapper;
import com.core.controller.service.BaseService;
import com.core.models.TBatTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 西 on 2015/9/9.
 */
@Service
public class BatTaskService extends BaseService<TBatTaskMapper, TBatTask> implements IBatTaskService {

    private TBatTaskMapper mapper;

    @Autowired
    public void setTBatTaskMapper(TBatTaskMapper mapper) {
        this.mapper = mapper;
        super.mapper = mapper;
    }

    @Override
    public Long addBasic(TBatTask obj) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();

        int count = mapper.selectCountByParams(params);

        obj.setTaskseq((count + 1) + "");

        return (long) mapper.insertSelective(obj);
    }

    @Override
    public void modifyBasic(TBatTask obj) throws Exception {

    }

    @Override
    public void delBasic(TBatTask obj) throws Exception {
        mapper.deleteByPrimaryKey(obj.getTaskseq());
    }
}
