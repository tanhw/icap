package com.task;

import com.business.IHandleTask;
import com.core.controller.service.bat.IBatTaskService;
import com.core.models.TBatTask;
import com.core.models.common.Order;
import com.toolbox.log.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 西 on 2015/9/9.
 */
@Service
public class BatTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier(value = "ConsumeTask")
    private IHandleTask consumeTask;

    @Autowired
    @Qualifier(value = "BankPosoffTask")
    private IHandleTask bankPosoffTask;

    @Autowired
    @Qualifier(value = "DateChangeTask")
    private IHandleTask dateChangeTask;


    @Autowired
    @Qualifier(value = "BanktUploadTask")
    private IHandleTask banktUploadTask;

    @Autowired
    @Qualifier(value = "BusTxnCollectTask")
    private IHandleTask busTxnCollectTask;

    @Autowired
    private IBatTaskService batTaskService;

    /**
     * 手工触发任务
     *
     * @throws
     * @author xi.xu
     * @date 2015年7月16日 下午5:15:24
     */
   @Scheduled(cron = "0 0 0/1 * * ?")
    public void batTask() {
        try {
            logger.info("**** 开始检查任务 ****");

            Map<String, Object> params = new HashMap<>();

            Order order = Order.asc("taskseq");

            List<TBatTask> list = batTaskService.findListByParams(params, order);

            for (TBatTask obj : list) {
                String taskdate = obj.getTaskdate();
                String tasktype = obj.getTasktype();

                switch (tasktype) {
                    case "01": //生成对帐文件
                    {
                        logger.info("**** 开始生成对帐文件 ****");
                        if (!banktUploadTask.doBusiness(taskdate)) {
                            logger.error("生成对帐文件错误");
                        } else {
                            logger.info("生成对帐文件成功");
                        }

                        break;
                    }
                    case "02"://勾兑结果文件
                    {
                        logger.info("**** 开始勾兑结果文件 ****");
                        if (!bankPosoffTask.doBusiness(taskdate)) {
                            logger.error("勾兑结果文件错误");
                        } else {
                            logger.info("勾兑结果文件成功");
                        }
                        break;
                    }
                    case "03": {
                        logger.info("**** 开始统计报表 ****");
                        if (!busTxnCollectTask.doBusiness(taskdate)) {
                            logger.error("统计报表错误");
                        } else {
                            logger.info("统计报表成功");
                        }
                        break;
                    }
                }

                batTaskService.delBasic(obj);
            }

        } catch (Exception e) {
            LogUtil.getTrace(e);
        }
    }

}
