package com.pospserver.common.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.constant.CommonConstant;
import com.pospserver.models.MessageMina;
import com.toolbox.log.LogUtil;


/**
 * 客户端业务处理逻辑
 * 
 */
public class PospClientHandler extends IoHandlerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(PospClientHandler.class);
	
	
	/**
	 * 属性
	 */
	private MessageMina messageMina;

	public MessageMina getMessageMina() {
		return messageMina;
	}

	public void setMessageMina(MessageMina messageMina) {
		this.messageMina = messageMina;
	}
	

	
	/**
	 * 收到消息
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		try {
			session.setAttribute("handStatus", CommonConstant.HanldStatus.Hanlded.toString());
		} catch (Exception e) {
			session.close(true);
			logger.error(LogUtil.getTrace(e));
			logger.error("错误：" +  e.getMessage());
		}
		
	}
	

	
	/**
	 * 当一个新连接session创建时触发
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("**** The new socket, session opened! ****");
		SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();   
        cfg.setSoLinger(0); 
        cfg.setKeepAlive(true);  
		
	}
	
	/**
	 * 当一个连接session关闭时触发
	 */
    @Override  
    public void sessionClosed(IoSession session) throws Exception { 
    	logger.info("**** session is Connected before: "+session.isConnected());  
        super.sessionClosed(session);  
        logger.info("**** session is Connected after: "+session.isConnected());  
        logger.info("**** The socket, session closed! ****");  
    }  
  

	/**
	 * 当有异常发生时触发
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		session.close(true);
		logger.error("**** The new exception occurred");
		logger.error(LogUtil.getTrace(cause));
	}
    

}
