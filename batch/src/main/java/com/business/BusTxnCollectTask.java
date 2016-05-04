package com.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.constant.CommonConstant;
import com.core.controller.service.batch.IBatCutCtlService;
import com.core.models.TBatDayCutCtl;
import com.toolbox.log.LogUtil;
import com.toolbox.util.DateUtil;
import org.springframework.stereotype.Service;

/**
 * Created by 西 on 2015/7/30. 交易流水统计
 */
@Service("BusTxnCollectTask")
public class BusTxnCollectTask implements IHandleTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IBatCutCtlService batCutCtlService;

    @Override
    public boolean doBusiness(String taskDate) {


        try {
            Map<String, Object> params = new HashMap<String, Object>();

            TBatDayCutCtl dayCutCtl = batCutCtlService.findObj(params);

            if (dayCutCtl != null) {

                String lastupddt = dayCutCtl.getCurrtxndt();

                String settledate = DateUtil.addDay(lastupddt, -2, "yyyyMMdd");

                if (taskDate != null) {
                    settledate = taskDate;
                }

                //List<TBustxnCollect> buslist = bustxnCollectService.findListBySettledate(settledate);

                //bustxnCollectService.addBatch(buslist,settledate);

                logger.error("****** 报表成功 [{}] ******", settledate);

            } else {
                logger.error("******error : batcutctl not exists data!");
                logger.info(CommonConstant.MsgResp.SysErr.toString());
            }

        } catch (Exception e) {
            logger.info(LogUtil.getTrace(e));
            return false;
        }

        return true;
    }


}
