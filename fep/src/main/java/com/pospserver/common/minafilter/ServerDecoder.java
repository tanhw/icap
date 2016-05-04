package com.pospserver.common.minafilter;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pospserver.models.MessageMina;
import com.toolbox.convert.ConvertTools;
import com.toolbox.log.LogUtil;
import com.toolbox.util.ByteUtil;

public class ServerDecoder extends CumulativeProtocolDecoder {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private int codeclength = 0;

	public ServerDecoder(int codeclen) {
		this.codeclength = codeclen;
	}

	private byte[] data = null; //报文
	
	byte[] tpdu = new byte[5]; //定义5字节报文TPDU空间

	@Override
	protected boolean doDecode(IoSession session, IoBuffer inBuffer, ProtocolDecoderOutput out) {

		logger.info("**** The txn access doDecode: Start ****");

		try {

		byte[] unfinished = (byte[]) session.getAttribute("buffereddata"); //已接受的分段报文
		Integer buflength = (Integer) session.getAttribute("bufferedlength"); //报文总长度
		Integer readedlength = (Integer) session.getAttribute("readlength"); //已接受报文长度
		
		boolean readed = false;
		MessageMina inmessage = null;

		if (unfinished == null) {// 第一次接收报文
			
			byte[] len = new byte[codeclength]; //定义2字节报文长度空间
			
			inBuffer.get(len, 0, codeclength); // 取2字节报文长度头

			inBuffer.get(tpdu, 0, 5); // 取5字节报文TPDU
			
			int length = ByteUtil.getByteIntBinary(len[0]) * 256 + ByteUtil.getByteIntBinary(len[1]); //计算长度

			logger.info("1、**** The txn length[{}] ****", length);  //监控日志
			
			length = length - 5; //去TPDU长度
			
			data = new byte[length];//根据长度定义报文空间
			
			session.setAttribute("bufferedlength", length); // 报文长度
			
			int relength = inBuffer.remaining(); // 获取分段报文实际长度
			
			//判断报文是否读取完毕
			if (relength > 0 && relength < length) { //未读完
				
				byte[] body = new byte[relength]; //定义当前报文空间
				
				inBuffer.get(body, 0, relength); //获取当前报文

				logger.info("2、**** The Info [{}] ****", ConvertTools.bytesToHexString(body));  //监控日志
				
				System.arraycopy(body, 0, data, 0, relength); //数组capy

				session.setAttribute("buffereddata", data); // 传入已接受报文
				
				session.setAttribute("readlength", relength); //传入已接受长度

				readed = true;

			} else if (relength == length) {// 一次性读取完毕

				byte[] body = new byte[relength];
				
				inBuffer.get(body, 0, relength);

				logger.info("3、**** The Info [{}] ****", ConvertTools.bytesToHexString(body));  //监控日志

				inmessage = this.setInMessageInfo(session, length, tpdu ,body);
				
				out.write(inmessage); //带走它
				
				readed = true;

			}

		} else {// 继续读取后续分段报文
			
			data = unfinished; //恢复已经接收的报文

			int relength = inBuffer.remaining(); //获取当前缓冲区保温长度
			
			int currlength = readedlength + relength; //计算已接收报文和当前报文长度的和
			
			if (currlength < buflength) {// 判断仍然没接收完
				
				byte[] body = new byte[relength]; //定义当前报文空间
				
				inBuffer.get(body, 0, relength); //获取当前报文

				logger.info("4、**** The Info [{}] ****", ConvertTools.bytesToHexString(body));  //监控日志

				System.arraycopy(body, 0, data, readedlength, relength); //数组capy

				session.setAttribute("buffereddata", data); // 传入已接受报文
				
				session.setAttribute("readlength", relength); //传入已接受长度
				
				readed = true;

			} else if (currlength == buflength) {// 读取完毕

				byte[] body = new byte[relength]; //定义当前报文空间
				
				inBuffer.get(body, 0, relength); //获取当前报文

				logger.info("5、**** The Info [{}] ****", ConvertTools.bytesToHexString(body));  //监控日志
				
				System.arraycopy(body, 0, data, 0, relength); //数组capy

				inmessage = this.setInMessageInfo(session, buflength, tpdu , data);
				
				out.write(inmessage); //快点带走它，别烦
				
				readed = true;

			} else {//长度超限
				
				byte[] body = new byte[relength]; //定义当前报文空间
				
				inBuffer.get(body, 0, relength); //获取当前报文

				logger.info("6、**** The Info [{}] ****", ConvertTools.bytesToHexString(body));  //监控日志
				
				//现将旧数据移到大数组中。
				byte[] bigArray = new byte[currlength];
				System.arraycopy(data, 0, bigArray, 0, data.length);
				System.arraycopy(body, 0, bigArray, readedlength, relength);
				
				inmessage = this.setInMessageInfo(session, buflength, tpdu , data);
				
				out.write(inmessage);
				
				readed = true;
			}
		}

			logger.info("**** The txn access doDecode: End ****");

		if (readed) {
			logger.info("**** The return true ****");
			return true;
		}
		}catch (Exception e){
			logger.error(LogUtil.getTrace(e));
		}

		logger.info("**** The return false ****");
		return false;
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
	private MessageMina setInMessageInfo(IoSession session, int length, byte[] tpdu, byte[] messagbody){
		
		MessageMina inmessage = new MessageMina(); //报文实体类
		inmessage.setLength(length); //设置报文长度
		inmessage.setTpdu(tpdu); //设置TPDU
		inmessage.setMessagbody(messagbody); //设置报文体
		InetSocketAddress address = (InetSocketAddress) session.getServiceAddress(); //得到socket地址信息
		inmessage.setRemoteip(address.getAddress().getHostAddress()); //设置链接方地址
		inmessage.setRemoteport(address.getPort()); //设置链接方端口
		
		return inmessage;
	}

	
}