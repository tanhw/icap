package com.business;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.core.controller.service.batch.IBatCutCtlService;
import com.core.models.TBatDayCutCtl;

/**
 * 消费类数据转移
 * @author rain
 *
 */

	public class ExpenditureDataTask implements IHandleTask{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IBatCutCtlService batCutCtlService;
	
	/**
	 * 先调用sql 后期考虑性能 转成 掉用SHEEL脚本
	 */
	@Override
	public boolean doBusiness(String taskDate) {
		
		try {
			//系统时间表
			TBatDayCutCtl tBatDayCutCtl = batCutCtlService.findObj(new HashMap());
			/*//充值 流水转移，  现状态不算时间
			buscardChargehislogService.batchInsert();
			//干掉当前表数据
			buscardChargehislogService.deleteAll();
			
			//脱机交易流水转移
			bustxnLogService.batchInsert(tBatDayCutCtl.getPrevtxndt());
				
			//干掉当前表数据
			bustxnLogService.deleteByDate(tBatDayCutCtl.getPrevtxndt());*/
			
	
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
		return true;
	}

}
