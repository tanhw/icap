package com.pospserver.parse.kms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.service.pos.IPosInfoService;
import com.core.controller.service.tbl.ISysParaService;
import com.core.models.TPosInfo;
import com.pospserver.common.post.IPostClient;
import com.pospserver.models.MessageMina;
import com.pospserver.models.kms.KmsHeader;
import com.pospserver.packge.kms.KmsPackge;

/**
 * 连接KMS获取MAC
 * @author rain
 *
 */
@Service("kmsMacCheck")
public class KmsMacCheck {
	

	@Autowired
	IPosInfoService posInfoService;
	
	@Autowired
	ISysParaService sysParaService;
	
	@Autowired
	@Qualifier(value="PostClient")
	IPostClient post;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @param workKey
	 * @param length
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public String getMac(String data, String posid) throws Exception{
		TPosInfo posInfo = posInfoService.findObjByKey(posid);
		String returnMac = "";
		if(posInfo!=null){
			
			if(posInfo.getPoskey()!=null&& !"".equals(posInfo.getPoskey())){
				
				String serviceid = sysParaService.getParaValue("FEP", "MAC_INTERFACE"); 
				String unitid = sysParaService.getParaValue("FEP", "KMS_UNIT"); 
				String encry = sysParaService.getParaValue("FEP", "KMS_ENCRY"); 
				String keyId = sysParaService.getParaValue("FEP", "MAIN_KEY"); 
				
			
				KmsHeader kms = new KmsHeader();
				kms.setUnitId(unitid); 
				kms.setServiceId(serviceid);
				kms.setKeyId(keyId);
				kms.setEncryId(encry);
				//交易类型
				kms.setTxnType(CommonConstant.KmsType.PosMac.toString());
				
				kms.setWorkKey(posInfo.getPoskey());
				
				kms.setMacData(data);
				
				//发送给KMS的报文
				MessageMina outmessage = KmsPackge.packgeKmsMessage
						   (CommonConstant.KmsType.PosMac.toString(),kms);
				
				//从KMS返回的报文
				MessageMina message = null;
			
				synchronized (this) {

					boolean result = post.createConnection(outmessage);
					if (result) {
						message = post.closeConnect();
					} else {
						throw new Exception("71"); // 抛异常
					}
				}
				String body = new String(message.getMessagbody(), "UTF-8");
				
				String kmsCode = body.substring(36,40) ;// kms 返回码
							
				if(CommonConstant.KMSError.isOK.toString().equals(kmsCode)){
					
					String hsmCode = body.substring(40,42) ;// 加密机 返回码
					
					if(CommonConstant.EncryptionError.isOK.toString().equals(hsmCode)){
						
						returnMac = body.substring(42); //返回的MAC值
		
					}else{
						logger.error("**** error！！！ hsmCode is: "+hsmCode 
								+"  " +CommonConstant.EncryptionError.getDescByValue(hsmCode)+" ****");
						throw new Exception("09"); 
					}
					
				}else{
					logger.error("**** error！！！ kmsCode is: "+kmsCode 
							+"  " +CommonConstant.KMSError.getDescByValue(kmsCode)+" ****");
					throw new Exception("09"); 
				}
				
			}else {
				throw new Exception(CommonConstant.MsgResp.MsgMacErr.toString());

			}
		}else {
			throw new Exception(CommonConstant.MsgResp.PosNotFound.toString()); 
		}
		
		return returnMac;
	}

}
