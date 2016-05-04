package com.pospserver.packge;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.constant.CommonConstant;
import com.posp.utils.TlvMessage;
import com.pospserver.models.MessageMina;
import com.pospserver.models.ServerMessage;
import com.toolbox.log.LogUtil;
import com.toolbox.util.ByteUtil;

/**
 *  封装返回报文
 * @author rain
 *
 */
public class ServerSourcePackge {

	private static Logger logger = LoggerFactory.getLogger(ServerSourcePackge.class);


	public static MessageMina packgeServerHeader(MessageMina inmessage, ServerMessage  serverMessage ) throws Exception{

		//组装报文头
		byte []  headBytes = (serverMessage.getAppType() 
											+ serverMessage.getMsgVersion()
											 + serverMessage.getMsgEnc() 
											 + serverMessage.getReserve()).getBytes();

		//组装报文体
		byte [] bodyBytes =TlvMessage.setTlvData(serverMessage.getResponseBody());
		
		byte [] responseBytes =ByteUtil.getNewArray(headBytes,bodyBytes);

		MessageMina message = inmessage;

		message.setLength(responseBytes.length);
		message.setMessagbody(responseBytes);

		return message;
	}


	/**
	 * @param inmessage 
	 * @throws Exception 
	 * error封装
	 * @author xi.xu 
	 * @date 2015年7月8日 下午1:03:23  
	 * @return
	 * @throws
	 */
	public static MessageMina packgeErrorInfo(MessageMina inmessage, ServerMessage serverMessage ,Exception e) throws Exception{

		logger.info(LogUtil.getTrace(e));
	
		/**
		 * set公共信息
		 */
		/** *****************start*************************** **/
		String ex = e.getMessage();

		if(ex.length() == 2){
			serverMessage.setRespCode(e.getMessage()); // 返回应答号
		}else{
			serverMessage.setRespCode(CommonConstant.MsgResp.SysErr.toString()); // 返回应答号 系统错误
		}
	
		
		//组装报文头
		byte []  headBytes = (serverMessage.getAppType() 
				+ serverMessage.getMsgVersion()
				 + serverMessage.getMsgEnc() 
				 + serverMessage.getReserve()
				 + serverMessage.getRespCode()).getBytes("UTF-8");	

		MessageMina message = inmessage;
		message.setLength(headBytes.length);
		message.setMessagbody(headBytes);

		return message;
	}


}
