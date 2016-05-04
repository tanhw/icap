package com.pospserver.common.post;

import com.pospserver.models.MessageMina;

public interface IPostClient {
	
	boolean createConnection(MessageMina messageMina);
	
	MessageMina closeConnect() throws Exception;
}
