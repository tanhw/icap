package com.posp.manage;

import com.pospserver.models.ServerMessage;

public interface ISignInBusiness {

	public   ServerMessage doBusiness(ServerMessage serverHeader) throws Exception;
}
