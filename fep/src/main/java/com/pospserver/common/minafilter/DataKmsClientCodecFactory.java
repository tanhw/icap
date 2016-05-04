package com.pospserver.common.minafilter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class DataKmsClientCodecFactory implements ProtocolCodecFactory {
	private final DataKmsClientEncoder encoder;
	private final DataKmsClientDecoder decoder;

	public DataKmsClientCodecFactory(int codeclen) {
		encoder = new DataKmsClientEncoder();
		decoder = new DataKmsClientDecoder(codeclen);
	}
	
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return encoder;
	}

}
