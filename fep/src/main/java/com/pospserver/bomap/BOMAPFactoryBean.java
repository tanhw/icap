package com.pospserver.bomap;

import java.io.InputStream;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.huateng.bomap.common.mapper.BOMAP;

public class BOMAPFactoryBean implements FactoryBean<BOMAP>, InitializingBean {

	private Resource configLocation;
	private BOMAP submapservice;

	protected BOMAP buildsubmapservice(Resource configLocation) throws Exception {
		InputStream is = configLocation.getInputStream();
		return new BOMAP(is);

	}

	public void afterPropertiesSet() throws Exception {
		if (this.configLocation == null) {
			throw new IllegalArgumentException("configLocation is required");
		}

		this.submapservice = buildsubmapservice(this.configLocation);
	}

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	public BOMAP getObject() throws Exception {
		return this.submapservice;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getObjectType() {
		return (this.submapservice != null ? this.submapservice.getClass() : BOMAP.class);
	}

	public boolean isSingleton() {
		return true;
	}

}
