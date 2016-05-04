package com.pospserver.common.minafilter;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pospserver.models.MessageMina;

public class DataKmsClientEncoder extends ProtocolEncoderAdapter {

	private static final Logger logger = LoggerFactory.getLogger(DataKmsClientEncoder.class);

	
	public void encode(IoSession session, Object message,ProtocolEncoderOutput out) throws Exception {
		try {
		
			MessageMina outmessage = (MessageMina) message;
			
			int bytesize = outmessage.getLength();
			
			IoBuffer buffer = IoBuffer.allocate(bytesize);
			
			buffer.setAutoExpand(true);
			
			buffer.put(outmessage.getMessagbody());
			
			buffer.flip();
			
			out.write(buffer);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}


}
