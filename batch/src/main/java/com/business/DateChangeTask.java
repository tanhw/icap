package com.business;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.core.controller.service.batch.IBatCutCtlService;
import com.core.models.TBatDayCutCtl;
import com.toolbox.log.LogUtil;
import com.toolbox.util.DateUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Song on 2015/7/24. 日切处理
 */
@Service("DateChangeTask")
public class DateChangeTask implements IHandleTask {

	@Autowired
	private IBatCutCtlService batCutCtlService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean doBusiness(String taskDate) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			TBatDayCutCtl obj = batCutCtlService.findObj(params);
			String prevDt = obj.getCurrtxndt();
			obj.setPrevtxndt(prevDt);
			String nowDt = DateUtil.addDay(prevDt, 1, "yyyyMMdd");
			obj.setCurrtxndt(nowDt);
			obj.setLastupddt(DateUtil.formatDate(DateUtil.getDate(),"yyyyMMdd"));
			obj.setLastupdtm(DateUtil.formatTime(DateUtil.getTime(), "HHmmss"));

			batCutCtlService.modifyBasic(obj);

		} catch (Exception e) {
			logger.info(LogUtil.getTrace(e));
			return false;
		}
		return true;
	}

}
