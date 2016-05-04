package com.pospserver.common.minafilter;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pospserver.models.MessageMina;

public class ServerEncoder extends ProtocolEncoderAdapter {
	@SuppressWarnings("unused")
	private int codeclength = 0;
	private static final Logger logger = LoggerFactory
			.getLogger(ServerEncoder.class);

	public ServerEncoder(int codeclen) {
		this.codeclength = codeclen;
	};

	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		try {
		
			MessageMina outmessage = (MessageMina) message;
			
			int bytesize = outmessage.getLength();

			logger.info("**** response body Length:[{}] ****", bytesize);
			
			byte[] byteArry = new byte[codeclength];
			
			byteArry[0]=(byte)((bytesize+5)/256); //长度+5字节TPDU
			
			byteArry[1]=(byte)((bytesize+5)%256); //长度+5字节TPDU
			
			IoBuffer buffer = IoBuffer.allocate(bytesize + codeclength);
			
			buffer.setAutoExpand(true);
			
			buffer.put(byteArry);
			if(outmessage.getTpdu()!=null){
				buffer.put(outmessage.getTpdu());
			}
			if(outmessage.getMessagbody()!=null){			
				buffer.put(outmessage.getMessagbody());
			}
			
			buffer.flip();
			
			out.write(buffer);

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}


}
