package com.tool;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ClientCodecFactory implements ProtocolCodecFactory {
	private final ClientEncoder encoder;
	private final ClientDecoder decoder;

	public ClientCodecFactory(int codeclen) {
		encoder = new ClientEncoder(codeclen);
		decoder = new ClientDecoder(codeclen);
	}

	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return encoder;
	}

}
