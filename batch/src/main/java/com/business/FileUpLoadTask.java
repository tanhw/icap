package com.business;

import com.constant.CommonConstant;
import com.core.controller.service.dict.IDictService;
import com.core.controller.service.tbl.ISysParaService;
import com.toolbox.util.DateUtil;
import com.toolbox.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.Date;

@Service("FileUpLoadTask")
public class FileUpLoadTask implements IHandleTask{

	@Autowired
	IDictService dictService;

	@Autowired
	private ISysParaService sysParaService;

	private static Logger logger = LoggerFactory.getLogger(FileUpLoadTask.class);

	@Override
	public boolean doBusiness(String date) throws Exception {

		boolean result = false;

		String hospital = dictService.findObjByKey("HOSPITAL").getCvalue(); //医院编号

		StringBuffer fileName = new StringBuffer();

		fileName.append(hospital);//医院编号,现一卡通平台
		fileName.append("INF");
		fileName.append(DateUtil.formatDate(new Date(), "yyMMdd"));//上传日期
		fileName.append("01C");//固定01C (01C表示脱机清算数据)

		String path = dictService.findObjByKey("POSSOFFPATH").getCvalue() + fileName.toString();

		logger.info("******path : ******" + path);

		File file = new File(path);
		byte fileByte[] = getByte(file);
		if (fileByte == null || fileByte.length < 1) {
			logger.error("**** File Not found[" + path + "]****");
			return false;
		}
		int fileSize = fileByte.length;

		String ip = sysParaService.getParaValue("BATCH", "PUT_FILE_IP");// pos连接ip

		if (ip == null || ip.equals("")) {

			logger.error("**** not find server ip ****");

			throw new Exception(CommonConstant.MsgResp.SysErr.toString());
		}

		int port = Integer.parseInt(sysParaService.getParaValue("BATCH", "PUT_FILE_PORT"));// pos连接端口

		if (port + "" == null || "".equals(port)) {

			logger.error("**** not find server prot ****");
			throw new Exception(CommonConstant.MsgResp.SysErr.toString());
		}

		int timeout = Integer.parseInt(sysParaService.getParaValue("BATCH", "PUT_FILE_TIMEOUT"));//  连接超时时间

		if (timeout + "" == null || "".equals(timeout)) {

			logger.error("**** not find server timeout ****");

			throw new Exception(CommonConstant.MsgResp.SysErr.toString());
		}

		Socket socket = null;

		synchronized (this) {

			try {
				result = false;
				socket = new Socket();
				if (socket == null) {
					logger.error("***创建服务器Socket连接服务器失败 ****");
					return false;
				}
				try {
					socket.setReuseAddress(true);
					SocketAddress remoteAddr = new InetSocketAddress(ip, port);
					socket.connect(remoteAddr, timeout); //连接远程服务器, 并且绑定匿名的本地端口

					result = socket.isConnected() && !socket.isClosed();
					if (result == false) {
						logger.error("**** Connect settle host fail ****");
						return false;
					}
					logger.info("socket isConnected[" + result + "]");

				} catch (Exception ec) {
					logger.error("****连接服务器失败 ****Host[" + ip + "]port[" + port + "]");
					return false;
				}

				// 发送第1 ，2次报文
				// 第一次：接收4个字节的报文长度
				// 第二次：接收交易报文内容
					// 格式如下：
					// *  交易代码     6位    100001
					// *  医院产品代码 6位    000001
					// *  交易类型     1位    C 正常 R 错误
					// *  文件名称     18位
					// *  文件长度     8位
					// *  交易日期     8位
					// *  交易时间     6位
					// *  销帐日期     8位
				byte[] sendBuffer = getMessage(fileName.toString(), fileSize);
				if (sendBuffer == null) {
					logger.error("sendMessage is null");
					return false;
				}

				logger.info("sendMessage : " + sendBuffer.length);

				result = sendMessage(socket, sendBuffer);

				if (result == false) {
					logger.error("**** send data fail ****");
					return false;
				}

				logger.info("***** start recvMessage : ****");

				// 接收返回，报文格式为：  RQ+总几次
				byte[] recvBuff = new byte[4096];

				int recvLength = recvMessage(socket, recvBuff, timeout);

				if (recvLength < 1) {
					logger.error("**** Recv data fail ****");
					return false;
				}
				if (recvLength < 3) // 3=RQ+1个字节次数
				{
					logger.error("**** Recv data lenth error ****");
					return false;
				}
				String rq = new String(recvBuff);

				logger.info(" *******rq message : *******" + rq);

				if (false == "RQ".equals(rq.substring(0, 2).toUpperCase())) {
					logger.error("**** Recv data format error ****");
					return false;
				}
				// 第三次: 接收到报文后，银行端根据“文件长度”，计算每次接收1025字节，需要接受的次数，像客户端发送请求指令  RQ+总几次，然后客户端可以开始发送清算文件。
				int unitLength = 1024;  // 每包数据长度
				int currLength = 0;
				byte buffer[];
				for (int i = 0; i < fileSize; ) {
					currLength = fileSize - i;
					if (currLength > unitLength) { // 如果剩余数据超出每包数据长度，则每包发送指定的数据包长度
						currLength = unitLength;
					}
					buffer = new byte[currLength];
					System.arraycopy(fileByte, i, buffer, 0, currLength);
					result = sendMessage(socket, buffer);
					if (result == false) {
						logger.error("**** send data fail ****");
						return false;
					}
					i = i + currLength;
				}
				// 文件发送完毕后断开Socket连接
				return true;
			} catch (Exception e) {
				logger.error("**** File Upload Fail ****");
				e.printStackTrace();
				return false;
			} finally {
				try {
					if (socket != null) {
						socket.close(); //关闭Socket
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result;
			}
		}
	}

	/**
	 * 传送数据到DMZ服务端，请求返回
	 * @param socket
	 * @param sendBuffer
	 * @return
	 */
	private boolean sendMessage(Socket socket, byte[] sendBuffer) {
		try {
			boolean isConnected = socket.isConnected() && !socket.isClosed();
			logger.info("sendMessage isConnected[" + isConnected + "]");

			if (isConnected) {
				//logger.info("sendMessage sendBuffer[" + sendBuffer.length + "]:" + StringUtil.toHexString(sendBuffer));

				OutputStream socketOut = socket.getOutputStream();

				socketOut.write(sendBuffer);
				socketOut.flush(); // 刷新输出流，使Server马上收到该字符串
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 返回DMZ服务端RQ标识
	 *
	 * @param socket
	 * @param recvBuff
	 * @param timeout
	 * @return
	 */
	private int recvMessage(Socket socket, byte[] recvBuff, int timeout) {
		//接收服务器的反馈
		int len = -1;
		int offset = 0;

		try {
			boolean isConnected = socket.isConnected() && !socket.isClosed();
			logger.info("recvMessage isConnected[" + isConnected + "]");

			if (isConnected) {
				InputStream socketIn = socket.getInputStream();

				try {
					socket.setSoTimeout(timeout);
					len = socketIn.read(recvBuff, offset, 1);
					offset = len;
				} catch (SocketTimeoutException e) {
					logger.error("收到数据！");
					len = 0;
				}
				if (len > 0) {// 收到数据接续接收
					try {
						// 收到数据后，重置接收超时，以便于立即收到数据
						len = 0;
						socket.setSoTimeout(200);
						len = socketIn.read(recvBuff, offset, recvBuff.length - offset);
						logger.info("又收到数据长度:" + len);

					} catch (SocketTimeoutException e) {
						logger.error("等待读取超时!");
					}
					if (len > 0) {
						len = offset + len;
						logger.info("一共收到数据长度:" + len);
					}
				}
			}
		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("recvLength[" + len + "]");
		if (len > 0) {
			//logger.info("recvMessage recvBuff:" + StringUtil.toHexString(recvBuff, 0, len));
		}
		return len;
	}

	/**
	 * 报文封装
	 */
	private byte[] getMessage(String fileName, int length)
	{
		byte[] buffer  = null;
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("100001");//交易代码
		strBuffer.append("000001"); //产品代码
		strBuffer.append("C");    //交易类型
		strBuffer.append(fileName);//文件名称
		strBuffer.append(StringUtil.leftAddChar(length + "", 8, "0"));//文件长度
		try {
			strBuffer.append(DateUtil.formatDate(new Date(), "yyyyMMdd"));//交易日期
			strBuffer.append(DateUtil.formatDate(new Date(), "HHmmss"));//交易时间
			strBuffer.append(DateUtil.formatDate(new Date(), "yyyyMMdd"));//销帐日期
		} catch (Exception e) {
			logger.error("Date formate error");
			return null;
		}

		// 长度为HEX格式
		//messageMina.setMessagbody(ByteUtil.getNewArray(NumberUtil.intToByte4(buffer.length()), buffer.toString().getBytes()));
		// 长度为ASCII格式
		buffer = (StringUtil.leftAddChar(strBuffer.length() + "", 4, "0") + strBuffer.toString()).getBytes();

		return buffer;
	}

	/**
	 * 文件转字节
	 *
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	private byte[] getByte(File file) throws Exception {

		if (file == null) {

			logger.error("**** the file is null ****");
			return null;
		}
		InputStream in = new FileInputStream(file);

		ByteArrayOutputStream out = new ByteArrayOutputStream((int) file.length());

		byte[] bytes = new byte[(int) file.length()];

		int n;
		while ((n = in.read(bytes)) != -1) {
			out.write(bytes, 0, n);
		}
		in.close();
		out.close();

		return out.toByteArray();
	}
}