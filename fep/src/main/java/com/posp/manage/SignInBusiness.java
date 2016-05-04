package com.posp.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.service.pos.IPosInfoService;
import com.core.controller.service.tbl.ISysParaService;
import com.core.models.TPosInfo;
import com.pospserver.common.post.IPostClient;
import com.pospserver.models.MessageMina;
import com.pospserver.models.ServerMessage;
import com.pospserver.models.kms.KmsBody;
import com.pospserver.models.kms.KmsHeader;
import com.pospserver.packge.kms.KmsPackge;
import com.pospserver.parse.kms.KmsParse;

/**
 * 签到
 * @author rain
 *
 */

@Service("signInBusiness")

public class SignInBusiness  implements ISignInBusiness{

	@Autowired
	IPosInfoService iPosInfoService;
	
	@Autowired
	ISysParaService sysParaService;
	
	@Autowired
	@Qualifier(value="PostClient")
	IPostClient post;
	
	
	/**
	 * 业务处理
	 */

	public ServerMessage doBusiness(ServerMessage serverMessage) throws Exception{
		
				
//		String key = this.getTAKKey(header.getTemId());
//		
//		signInBody.setTermKey("M"+key.substring(0,16)+key.substring(32,40));
//		
		return null;
	}
	
	
	/**
	 * 从KMS获取工作密钥
	 * @return
	 * @throws 
	 */

	private String getTAKKey(String posId) throws Exception{
		//从KMS返回的报文
		MessageMina message=null;

		KmsHeader kms = new KmsHeader();
		
		TPosInfo tPosInfo =iPosInfoService.findObjByKey(posId);
		if(CommonConstant.TmkDownFlag.FALSE.toString().equals
				(tPosInfo.getTmkdownflag())||tPosInfo.getTmkdownflag()==null
				||"".equals(tPosInfo.getTmkdownflag())){
			//主密钥没下载
			throw new Exception(CommonConstant.MsgResp.PosKeyInfoNotExist.toString());
			
		}	
		
		String serviceid = sysParaService.getParaValue("FEP", "WORK_KEY_INTERFACE"); 
		String unitid = sysParaService.getParaValue("FEP", "KMS_UNIT"); 
		String encry = sysParaService.getParaValue("FEP", "KMS_ENCRY"); 

		kms.setUnitId(unitid); 
		kms.setServiceId(serviceid);
		kms.setKeyId("00000000");
		kms.setEncryId(encry);
		//交易类型
		kms.setTxnType(CommonConstant.KmsType.PosWork.toString());
		//主密钥
		kms.setMainKey(tPosInfo.getTmk());
		
		//发送给KMS的报文
		MessageMina outmessage = KmsPackge.packgeKmsMessage
				   (CommonConstant.KmsType.PosWork.toString(),kms);
		
		
		// 开始链接
		synchronized (this) {

			boolean result = post.createConnection(outmessage);
			if (result) {
				message = post.closeConnect();
			} else {
				throw new Exception("71"); // 抛异常
			}
		}
			
		KmsBody kmsBody = KmsParse.parseKmsMessage(message);//解析KMS报文
		
		tPosInfo.setPoskey(kmsBody.getKey().substring(16,32));
		
		tPosInfo.setStatus("00");
		
		iPosInfoService.modifyBasic(tPosInfo);
		
		return kmsBody.getKey();
	}

}
