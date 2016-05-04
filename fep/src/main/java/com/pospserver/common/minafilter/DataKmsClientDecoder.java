package com.pospserver.common.minafilter;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.pospserver.models.MessageMina;
import com.toolbox.util.ByteUtil;

public class DataKmsClientDecoder extends CumulativeProtocolDecoder {

	
	@SuppressWarnings("unused")
	private int codeclength = 0;

	public DataKmsClientDecoder(int codeclen) {
		this.codeclength = codeclen;
	}

	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		byte[] len = new byte[codeclength];
		
		in.get(len, 0, codeclength);
		
		int length = ByteUtil.getByteInt(len);
		
		++length;
			
		byte[] data = new byte[length];

		in.get(data, 0, length);

		MessageMina inmessage = this.setInMessageInfo(session, length , data);
		
		out.write(inmessage);
		
		return true;
		
	}
	

	/**
	* set Message body info
	* @author xi.xu 
	* @date 2015年7月3日 下午3:53:42  
	* @param session
	* @param length message length
	* @param messagbody body info
	* @return  
	* @throws
	*/
	private MessageMina setInMessageInfo(IoSession session, int length, byte[] messagbody){
		
		MessageMina inmessage = new MessageMina(); //报文实体类
		inmessage.setMessagbody(messagbody); //设置报文体
		return inmessage;
	}
	
	
}