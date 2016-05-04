package com.pospserver.common.post;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.constant.CommonConstant;
import com.core.controller.service.tbl.ISysParaService;
import com.pospserver.common.handler.PospClientHandler;
import com.pospserver.common.minafilter.DataKmsClientCodecFactory;
import com.pospserver.models.MessageMina;
import com.toolbox.convert.ConvertTools;
import com.toolbox.log.LogUtil;

/**
 * socket客户端工具
 * 
 * 
 */
@Service("PostDataKmsClient")
@Scope("prototype")
public class PostDataKmsClient implements IPostClient {

	private ConnectFuture cf = null;

	private NioSocketConnector connector = null;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	MessageMina message = null;

	@Autowired
	private ISysParaService sysParaService;

	public boolean createConnection(MessageMina messageMina) {
		boolean result = false;

		String ip = null;
		int port = 0;
		long timeout = 0;

		try {

			logger.info("**** create the connection !****");


			ip = sysParaService.getParaValue("FEP", "DATA_KMS_IP");// pos连接ip

			if (ip == null || ip.equals("")) {

				logger.error("**** not find server ip ****");

				throw new Exception(CommonConstant.MsgResp.SysErr.toString());
			}

			port = Integer.parseInt(sysParaService.getParaValue("FEP", "DATA_KMS_PORT"));// pos连接端口

			if(port + "" == null || "".equals(ip)){

				logger.error("**** not find server prot ****");
				throw new Exception(CommonConstant.MsgResp.SysErr.toString());
			}

			timeout = Long.parseLong(sysParaService.getParaValue("FEP", "TIME_OUT"));//  连接超时时间

			if(timeout + "" == null || "".equals(timeout)){

				logger.error("**** not find server timeout ****");

				throw new Exception(CommonConstant.MsgResp.SysErr.toString());
			}

			message = null;
			connector = new NioSocketConnector();

			connector.getSessionConfig().setUseReadOperation(true);

			DataKmsClientCodecFactory clientcode = new DataKmsClientCodecFactory(4);

			connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(clientcode));

			connector.setHandler(new PospClientHandler()); // 客户端的消息处理器

			connector.setConnectTimeoutMillis(timeout * 6000 * 2);	// set connect timeout

			logger.info("**** open connection !****");		// 连接到服务器

			cf = connector.connect(new InetSocketAddress(ip, port));

			cf.awaitUninterruptibly();

			if (cf == null) {

				logger.error("**** socket cann't connection !****");

				throw new Exception("71");				
			}
			logger.info("connect is connect:" + cf.isConnected());

			IoSession session = cf.getSession();

			if (session != null) {

				logger.info("**** get session success !****");
			} else {

				logger.error("**** session cann't create !****");

				throw new Exception("71");
			}

			logger.info("**** Service request  message : [{}]", ConvertTools.bytesToHexString(messageMina.getMessagbody()));

			session.write(messageMina).awaitUninterruptibly();

			session.setAttribute("handStatus",
					CommonConstant.HanldStatus.Hanlding.toString());
			result = true;

			ReadFuture readFuture = session.read();

			if (readFuture.awaitUninterruptibly(
					timeout * 6000 * 2, TimeUnit.MILLISECONDS)) {

				logger.info("**** get back OK !****");

				message = (MessageMina) readFuture.getMessage();

				logger.info("**** Service response  message : [{}]", ConvertTools.bytesToHexString(message.getMessagbody()));

				result = true;
			} else {
				// 超时
				logger.info("**** get back lose !****");

				((AbstractIoSession) session).offerReadFuture(null);// 针对同步实现的bug
				result = false;
				if (session != null) {

					session.close(true);
				}
				if (connector != null) {

					connector.dispose();
				}
			
				throw new Exception(
						CommonConstant.RespsDesc.ERROR_72.toString());
			}

		} catch (Exception e) {

			logger.error(LogUtil.getTrace(e));

			logger.error("************" + e.getMessage() + "*****************");

			logger.error("**** Connection failure , Please inspection network !****");
			if (cf != null) {

				IoSession session = cf.getSession();
				if (session != null) {

					session.close(true);
				}
			}
			if (connector != null) {

				connector.dispose();
			}
		

			result = false;
		} finally {

			if (cf != null) {

				IoSession session = cf.getSession();
				if (session != null) {

					session.close(true);
				}
			}
			if (connector != null) {

				connector.dispose();

			}
		}
		return result;
	}

	public MessageMina closeConnect() throws Exception {

		logger.info("关闭socket");

		String handStatus = (String) cf.getSession().getAttribute("handStatus");

		logger.info("handStatus:" + handStatus);

		long count = 1;

		while (cf != null
				&& cf.getSession() != null
				&& (handStatus = (String) cf.getSession().getAttribute(
						"handStatus")) != null
						&& handStatus.equals(CommonConstant.HanldStatus.Hanlding
								.toString())) {
			handStatus = (String) cf.getSession().getAttribute("handStatus");

			if (handStatus
					.equals(CommonConstant.HanldStatus.Hanlded.toString())) {

				logger.info("**** get back success ****");

				break;
			}
			TimeUnit.MILLISECONDS.sleep(1000);
			count++;
			if (count == 5) {

				logger.info("****time out,at 5 s !****");
				break;
			}

		}
		if (cf != null && cf.isConnected()) {

			if (cf.getSession() != null && cf.getSession().isConnected()) {

				cf.getSession().close(true);

				logger.info("**** session close success !****");
			}
			if (!connector.isDisposed()) {

				connector.dispose();

				logger.info("**** connector close success !****");


			} else {
				logger.info("**** connection closed !****");
			}
		}

		return message;
	}

}
