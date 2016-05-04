package com.tool;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;



import com.models.MessageMina;
import com.toolbox.util.ByteUtil;

public class ClientDecoder extends CumulativeProtocolDecoder {
	

	@SuppressWarnings("unused")
	private int codeclength = 0;

	public ClientDecoder(int codeclen) {
		this.codeclength = codeclen;
	}

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
			

		byte[] len = new byte[codeclength];
		
		in.get(len, 0, codeclength);
		
		int length = ByteUtil.getByteInt(len);
			
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
		inmessage.setLength(length); //设置报文长度
		inmessage.setMessagbody(messagbody); //设置报文体
		InetSocketAddress address = (InetSocketAddress) session.getServiceAddress(); //得到socket地址信息
		inmessage.setRemoteip(address.getAddress().getHostAddress()); //设置链接方地址
		inmessage.setRemoteport(address.getPort()); //设置链接方端口
		
		return inmessage;
	}
	
	
}