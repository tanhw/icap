package com.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.business.IHandleTask;

@Service
public class MainTaskHandle {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier(value="DateChangeTask")
    private IHandleTask dateChangeTask;

    @Autowired
    @Qualifier(value="BanktUploadTask")
    private IHandleTask banktUploadTask;

    @Autowired
    @Qualifier(value="BusTxnCollectTask")
    private IHandleTask busTxnCollectTask;

    @Autowired
    @Qualifier(value="CheckDataTask")
    private IHandleTask checkDataTask;

    @Autowired
    @Qualifier(value="FileUpLoadTask")
    private IHandleTask fileUpLoadTask;

    /**
     * 日切
     *
     * @throws
     * @author xi.xu
     * @date 2015年7月16日 下午5:15:24
     */
    @Scheduled(cron = "0 55 19 ? * *")
    public void dateChangeTask() throws Exception {
        logger.info("**** Start dateChangeTask ****");

        if (!dateChangeTask.doBusiness(null)) {
            logger.error("日切处理错误");
        }else{
            logger.info("日期切换成功");
        }

        logger.info("**** End dateChangeTask ****");
    }


    /**
     * 对账业务
     *
     * @throws
     * @author xi.xu
     * @date 2015年7月16日 下午5:15:24
     */
    @Scheduled(cron = "0 56 19 ? * *")
    public void bankPosoffTask() throws Exception {
        logger.info("**** Start ****");

        logger.info("**** 开始生成对帐文件 ****");
        if (!banktUploadTask.doBusiness(null)) {
            logger.error("生成对帐文件错误");
        } else {
            logger.info("生成对帐文件成功");
        }

        logger.info("**** 开始统计报表 ****");
        if (!busTxnCollectTask.doBusiness(null)) {
            logger.error("统计报表错误");
        } else {
            logger.info("统计报表成功");
        }

        logger.info("**** End ****");

    }

    /**
     * 对账文件上传
     *
     * @throws
     * @author tanhw
     * @date
     */
    @Scheduled(cron = "0 05 20 ? * *")
    public void fileUpLoadTask() throws Exception {
        logger.info("**** Start clearCoreTask ****");

        if (!fileUpLoadTask.doBusiness(null)) {
            logger.error("对账文件传输失败");
        } else {
            logger.info("对账文件传输成功");
        }

        logger.info("**** End fileUpLoadTask ****");
    }

    /**
     * 清除确认码
     *
     * @throws
     * @author xi.xu
     * @date
     */
    @Scheduled(cron = "0 00 21 ? * *")
    public void clearCoreTask() throws Exception {
        logger.info("**** Start clearCoreTask ****");

        if (!checkDataTask.doBusiness(null)) {
            logger.error("清除确认码处理错误");
        }else{
            logger.info("清除确认码处理成功");
        }

        logger.info("**** End clearCoreTask ****");
    }
}
