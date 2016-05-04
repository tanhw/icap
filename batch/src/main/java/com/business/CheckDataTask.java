package com.business;

import com.core.controller.service.batch.IBatCutCtlService;
import com.core.controller.service.unit.ICheckInfoService;
import com.core.models.TBatDayCutCtl;
import com.core.models.TCheckInfo;
import com.toolbox.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("CheckDataTask")
public class CheckDataTask implements IHandleTask{

    @Autowired
    private ICheckInfoService checkInfoService;

    @Autowired
    private IBatCutCtlService batCutCtlService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean doBusiness(String taskdate) throws Exception {

        try {

            Map<String, Object> params = new HashMap<String, Object>();
            TBatDayCutCtl batDayCutCtl = batCutCtlService.findObj(params);

            checkInfoService.delCreateDate(DateUtil.getTime(batDayCutCtl.getPrevtxndt(),"yyyyMMdd"));

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("********确认码数据更新失败:");
            return false;
        }
        return true;
    }


}
