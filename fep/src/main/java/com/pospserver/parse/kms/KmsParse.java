package com.pospserver.parse.kms;

import com.pospserver.models.MessageMina;
import com.pospserver.models.kms.KmsBody;

public class KmsParse {

	/**
	 * 解析KMS报文
	 * 
	 * @author xi.xu
	 * @date 2015年7月8日 下午12:22:11
	 * @param message
	 * @return
	 * @throws
	 */
	public static KmsBody parseKmsMessage(MessageMina message) throws Exception {

		if (message.getMessagbody() == null) {
			// 报文体错误异常
		}
		String body = new String(message.getMessagbody(), "UTF-8");
		
		int lenth = message.getLength();

		KmsBody kmsbody = new KmsBody();

		kmsbody.setCode(body.substring(36, 40));
		kmsbody.setKeyCode(body.substring(40, 42));

		if (lenth >= 42) {
			kmsbody.setKey(body.substring(42));
		}

		return kmsbody;
	}

}
