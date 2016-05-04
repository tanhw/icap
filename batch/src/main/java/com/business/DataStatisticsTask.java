package com.business;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.constant.CommonConstant;
import com.core.controller.service.batch.IBatCutCtlService;
import com.core.models.TBatDayCutCtl;
import com.toolbox.log.LogUtil;
import com.toolbox.util.DateUtil;

public class DataStatisticsTask implements IHandleTask {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IBatCutCtlService batCutCtlService;
	
	/**
	 * 非消费类数据统计
	 */
	@Override
	public boolean doBusiness(String taskDate) {

		Map<String, Object> params = new HashMap<String, Object>();

		try {

			TBatDayCutCtl dayCutCtl = batCutCtlService.findObj(params);

			if (dayCutCtl != null) {
				String lastupddt = dayCutCtl.getPrevtxndt();

				String settledate = DateUtil.addDay(lastupddt, -2, "yyyyMMdd");

				//buscardsaleLogService.addNonConBySettledate(settledate);

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
