package com.pospserver.common.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.posp.manage.IMainKeyBusiness;
import com.posp.utils.TlvDataDict;
import com.pospserver.models.MessageMina;
import com.pospserver.models.ServerMessage;
import com.pospserver.packge.ServerSourcePackge;
import com.pospserver.parse.ServerMessageParse;
import com.toolbox.util.StringUtil;

/**
 * <a href="DynamicLengthController.java.html"><b><i>View Source</i></b></a>
 * 
 * Description ★ 这个类主要负责转换那些动态长度的报文
 * 
 * @author Administrator
 */
@Controller("dynlenghtcontroller")
@Scope("prototype")
public class DynamicLengthController implements IController {


	@Autowired
	private IMainKeyBusiness mainKeyBusiness;

	@Autowired
	private ServerMessageParse serverMessageParse;

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(DynamicLengthController.class);

	public MessageMina processmessage(MessageMina inmessage) throws Exception {

		byte[] messageBytes = inmessage.getMessagbody();// 接受的消息

		MessageMina respMessage = new MessageMina();// 返回消息

		ServerMessage serverMessage = serverMessageParse.parseServerMessage(messageBytes); // 得到解析

		Map<String,Object>  body = serverMessage.getRequestBody();

		String msgType = (String) body.get(TlvDataDict.Fields.MsgType.getTag());

		try {

			/**
			 * 根据具体的msgType解析具体request，并调用相应接口
			 */
			logger.info("the MsgType: " + msgType );
			if (TlvDataDict.MsgType.MainKey.getRequest().equals(msgType)) { //  ***** 主密钥下载 ******

				serverMessage = mainKeyBusiness.doBusiness(serverMessage);

			} else {//


			} 

			/**
			 * 封装
			 */

			respMessage = ServerSourcePackge.packgeServerHeader(inmessage, serverMessage);


		} catch (Exception e) {

			respMessage = ServerSourcePackge.packgeErrorInfo(inmessage, serverMessage, e);
		}
		return respMessage;
	}
	public static void main(String[] args) {
		System.out.println(StringUtil.leftAddChar(Long.toHexString(15),4,"0"));
	}
}