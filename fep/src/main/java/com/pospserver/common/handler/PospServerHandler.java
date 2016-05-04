package com.pospserver.common.handler;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.constant.CommonConstant;
import com.pospserver.common.controller.IController;
import com.pospserver.models.MessageMina;
import com.toolbox.convert.ConvertTools;
import com.toolbox.log.LogUtil;

@Controller("pospHandler")
@Scope("prototype")
public class PospServerHandler extends IoHandlerAdapter {

	private  final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IController bussinesscontroller;

	/**
	 * 收到来自客户端的消息
	 * @throws Exception 
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		try {

		// 首先先接收一干完整帧/包的数据
		MessageMina receivedmessage = (MessageMina) message;

		InetSocketAddress remoteall = (InetSocketAddress) session.getRemoteAddress();

		if(remoteall != null){

			logger.info("**** have address! ****");

			receivedmessage.setRemoteip(remoteall.getAddress().toString());

			receivedmessage.setRemoteport(remoteall.getPort());
		}else{
			logger.error("**** no have address! ****");
		}

		// 然后直接调用统一的业务接口
		logger.info("**** Client request message info: [{}] ****", ConvertTools.bytesToHexString(receivedmessage.getMessagbody()));

		MessageMina sendmessage = bussinesscontroller.processmessage(receivedmessage);

		if(sendmessage.getMessagbody()!=null){
			logger.info("**** Client respsonse  message info: [{}] ****", ConvertTools.bytesToHexString(sendmessage.getMessagbody()));
		}

		// 最后发回处理后的报文
		session.write(sendmessage);

//		wf.awaitUninterruptibly(45000, TimeUnit.MILLISECONDS);

		
		/*if(session!= null && session.isConnected() && session.getCloseFuture().awaitUninterruptibly(45000, TimeUnit.MILLISECONDS)){ 
			logger.info("**** session closed sussce****");
			
				session.close(false);
				logger.info("**** session closed successful****");
			
		}else{ 
			logger.info("**** write message timeout!session closed ****");
			if(session!= null) {
				session.close(true);
			}
		}*/
		
		}catch (Exception e){
		
			logger.error(LogUtil.getTrace(e));
			throw new  Exception(CommonConstant.MsgResp.SysErr.toString());
		}
	}

	/**
	 * 当一个新连接session创建时触发
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {

		//logger.info("****************server Session created***********");

		/**
		 * 对方链接信息
		 */
		//InetSocketAddress address = (InetSocketAddress) session.getServiceAddress(); //得到socket地址信息

		//logger.info("**** client request address: [{}] ****", address.getAddress().getHostAddress()); //地址

		//logger.info("**** client request address: [{}] ****", address.getPort()); //端口

		/**
		 * other
		 */
		SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();   

		cfg.setKeepAlive(true);

		cfg.setReuseAddress(true);

		cfg.setSoLinger(0);

		cfg.setIdleTime(IdleStatus.BOTH_IDLE, 120); // 设置服务端空闲会话触发时间点

	}

	/**
	 * 当有异常发生时触发
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {

		session.close(true);

		logger.info("**** The new exception occurred");

		logger.info(LogUtil.getTrace(cause));
	}

	
	
	

}