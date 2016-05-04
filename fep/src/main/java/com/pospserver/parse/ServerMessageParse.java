package com.pospserver.parse;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.posp.utils.TlvDataDict;
import com.posp.utils.TlvMessage;
import com.pospserver.models.ServerMessage;
import com.pospserver.parse.kms.KmsMacCheck;
import com.toolbox.convert.ConvertTools;

/**
 * 报文解析
 * @author 西
 *
 */
@Service("serverMessageParse")
public class ServerMessageParse {

	/** 报文 **/
	@Autowired
	private ServerMessage serverMessage;
	
	@Autowired
	private KmsMacCheck kmsMacCheck;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 解析
	* @author xi.xu 
	* @date 2015年7月6日 下午12:05:14  
	* @return 
	* @throws
	 */
	/**
	 * @param messageBytes
	 * @throws Exception
	 */
	public ServerMessage parseServerMessage(byte[] messageBytes) throws Exception{
		String source  =  new String(messageBytes, "iso8859-1");
		
		int i  = 0;
		
		serverMessage.setAppType(source.substring(i,i+3));   i = i + 3 ;  //渠道系统编号
		serverMessage.setMsgVersion(source.substring(i,i+2));   i = i + 2 ;  //  软件版本号	
		serverMessage.setMsgEnc(source.substring(i,i+1));   i = i + 1 ; //是否加密
		serverMessage.setReserve(source.substring(i,i+6));   i = i + 6 ;       //保留域
		
		byte [] bodyMessage = new byte[messageBytes.length-12]; //去掉报文头 12字节
		
		System.arraycopy(messageBytes,12,bodyMessage,0,bodyMessage.length);
	
		
		if (CommonConstant.MsgEnc.MAC.toString().equals(serverMessage.getMsgEnc())//MAC
				|| CommonConstant.MsgEnc.SecretAndMAC.toString().equals(serverMessage.getMsgEnc())//加密后MAC
				){
		
			int dataLength =  bodyMessage.length - 4;
			byte[] macData = new byte[4];
			byte[] msgData = new byte[dataLength];
			
			System.arraycopy(bodyMessage, dataLength, macData, 0, 4);
			
			System.arraycopy(bodyMessage, 0, msgData, 0, dataLength);//干掉Mac后的 数据
			
			String requestMac = ConvertTools.bytesToHexString(macData);
				
			Map<String,Object> bodyMap = TlvMessage.getTlvData(msgData);
					
			logger.info("------------ requestMac = " + requestMac + " ------------");
			
			String returnMac = kmsMacCheck.getMac
					(new String(msgData, "iso8859-1"),(String)bodyMap.get(TlvDataDict.Fields.PlatformPosId.getTag()));
			
			logger.info("------------ returnMac = " + returnMac + " ------------");
			
			if(requestMac.equals(returnMac) ==false){
				
				throw new Exception(CommonConstant.MsgResp.MsgMacErr.toString());
			}
			
			serverMessage.setRequestBody(bodyMap);
			
			return serverMessage;
		}else if (CommonConstant.MsgEnc.SecretAndMAC.toString().equals(serverMessage.getMsgEnc())//加密后MAC
				|| CommonConstant.MsgEnc.Secret.toString().equals(serverMessage.getMsgEnc())//加密
				){
			// TODO: 对报文进行解密处理
			//serverMessage.setMAC();
		
			return null;
		}else {
			Map<String,Object> bodyMap = TlvMessage.getTlvData(bodyMessage);
			serverMessage.setRequestBody(bodyMap);
			
			return serverMessage;
		}

	}


	public ServerMessage getserverMessage() {
		return serverMessage;
	}


	public void setserverMessage(ServerMessage serverMessage) {
		this.serverMessage = serverMessage;
	}
	
}
