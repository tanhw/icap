package com.posp.manage;

import com.pospserver.models.ServerMessage;

public interface IMainKeyBusiness {
	
	
	/**
	 *  主密钥下载
	 * @param message
	 * @return
	 * @throws Exception
	 */

	public ServerMessage doBusiness(ServerMessage message) throws Exception;
	

}
