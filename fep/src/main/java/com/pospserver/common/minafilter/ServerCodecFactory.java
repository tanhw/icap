package com.pospserver.common.minafilter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ServerCodecFactory implements ProtocolCodecFactory {
	private final ServerEncoder encoder;
	private final ServerDecoder decoder;

	public ServerCodecFactory(int codeclen) {
		encoder = new ServerEncoder(codeclen);
		decoder = new ServerDecoder(codeclen);
	}

	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return encoder;
	}

}
