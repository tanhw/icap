package com.core.controller.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.business.dict.DictBusiness;
import com.toolbox.log.LogUtil;

public class ComInstantiationProcessor implements
		ApplicationListener<ContextRefreshedEvent> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DictBusiness dictBusiness;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		 if(event.getApplicationContext().getParent() == null){
			 try {
					dictBusiness.updateCommonParamsMap();// 初始化数据字典数据加载到内存中
				} catch (Exception e) {
					logger.info(LogUtil.getTrace(e));
				}
	    }  
	}
}