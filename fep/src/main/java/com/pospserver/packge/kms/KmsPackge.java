package com.pospserver.packge.kms;

import com.constant.CommonConstant;
import com.pospserver.models.MessageMina;
import com.pospserver.models.kms.KmsHeader;
import com.toolbox.util.StringUtil;

/**
 * KMS报文封装类
 * 
 * @author rain
 *
 */
public class KmsPackge {


	/**
	 * 报文KMS封装
	 * 
	 * @param ksmsdsd
	 * @return
	 * @throws Exception
	 */
	public static MessageMina  packgeKmsMessage(String kmsType, KmsHeader kms) throws Exception{

		MessageMina messageMina =new MessageMina();

		StringBuffer body = new StringBuffer();
		body.append(kms.getUnitId());
		body.append(kms.getServiceId());
		body.append(kms.getKeyId());
		body.append(kms.getEncryId());
		body.append(kms.getTxnType());

		if (CommonConstant.KmsType.PosMain.toString().equals(kmsType)){
			if (body.length() == 36) {
				messageMina.setLength(36);
				messageMina.setMessagbody(body.toString().getBytes());

			}else{
				//报文长度不对，抛异常
				throw new Exception("09");
			}
		}else if (CommonConstant.KmsType.PosWork.toString().equals(kmsType)){
			if(kms.getMainKey()==null){

				throw new Exception(CommonConstant.MsgResp.PosKeyInfoNotExist.toString());
			}

			//报文头36位 , 主密钥 32位
			body.append(kms.getMainKey());
			if (body.length() == 68) {
				messageMina.setLength(68);

				messageMina.setMessagbody(body.toString().getBytes());

			}else{
				//报文长度不对，抛异常
				throw new Exception("09");
			}
		}else if (CommonConstant.KmsType.M1KeyA.toString().equals(kmsType)){
			body.append("016");//TODO
			body.append("0000000000000000");
			if (body.length() == 55) {
				messageMina.setLength(55);

				messageMina.setMessagbody(body.toString().getBytes());

			}else{
				//报文长度不对，抛异常
				throw new Exception("09");
			}
		}else if(CommonConstant.KmsType.M1KeyB.toString().equals(kmsType)){// M1KeyB下载
			body.append("016");//TODO
			body.append("0000000000000000");
			if (body.length() == 55) {
				messageMina.setLength(55);

				messageMina.setMessagbody(body.toString().getBytes());

			}else{
				//报文长度不对，抛异常
				throw new Exception("09");
			}
		}else if (CommonConstant.KmsType.PosMac.toString().equals(kmsType)){
			
			body.append(kms.getWorkKey());
			body.append(StringUtil.leftAddChar(kms.getMacData().length()+"", 4, "0"));
			body.append(kms.getMacData());
			messageMina.setLength(body.toString().length());
			messageMina.setMessagbody(body.toString().getBytes());

		}
		return messageMina;
	}
}
