package com.posp.manage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.service.pos.IPosInfoService;
import com.core.models.TPosInfo;
import com.posp.utils.TlvDataDict;
import com.pospserver.models.ServerMessage;

/**
 * 主密钥下载
 *
 * @author rain
 *
 */
@Service
public class MainKeyBusiness implements IMainKeyBusiness {

	@Autowired
	IPosInfoService posInfoService;

	private static Logger logger = LoggerFactory.getLogger(MainKeyBusiness.class);

	public ServerMessage doBusiness(ServerMessage serverMessage) throws Exception {

		Map<String,Object> requestBody = serverMessage.getRequestBody();

	//	TPosInfo pos = posInfoService.findObjByKey(requestBody.get(TlvDataDict.Fields.PlatformPosId));

//		if(pos==null ){
//
//			logger.error("**** the pos not found ,posid: "+requestBody.get( TlvDataDict.Fields.PlatformPosId) +"****");
//
//			throw new Exception(CommonConstant.MsgResp.PosNotFound.toString());
//
//		}

		List<String> responseBody = new ArrayList<String>();

		responseBody.add(TlvDataDict.Fields.MsgType.getTag()+TlvDataDict.MsgType.MainKey.getResponse());//返回交易码

		responseBody.add(TlvDataDict.Fields.PlatformMercId.getTag()+"105152683980279"); //平台商户号

		responseBody.add(TlvDataDict.Fields.PlatformPosId.getTag()+"00036090");//平台终端号
		responseBody.add(TlvDataDict.Fields.BankMercId.getTag()+"105152683980279");//银行商户号
		responseBody.add(TlvDataDict.Fields.BankPosId.getTag()+"00036090");// 银行终端号

		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		responseBody.add(TlvDataDict.Fields.SystemDateTime.getTag()+format.format(date)); //系统时间
		responseBody.add(TlvDataDict.Fields.MainKey.getTag()+"13B9024F9CE9BA0FE446D69047B83184"); //主密钥

		serverMessage.setResponseBody(responseBody);

		return serverMessage;

	}
	
	public static void main(String[] args) {
		
		System.out.println(Long.toHexString(17L));
	}
}
