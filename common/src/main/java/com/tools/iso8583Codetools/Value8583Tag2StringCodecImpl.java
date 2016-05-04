package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.IExtendValueCodec;
import com.huateng.bomap.common.exception.MapperException;
import com.huateng.bomap.common.util.ConvertTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * The Class Tlv8583TagCodecImpl.8583的tlv的tag读取类 tag标签的属性为bit，由16进制表示，占1～2个字节长度。
 * 例如，“9F33”为一个占用两个字节的tag标签。 而“95”为一个占用一个字节的tag标签。
 * 若tag标签的第一个字节（注：字节排序方向为从左往右数，第一个字节即为最左边的字节。bit排序规则同理。）的后四个bit为“1111”，
 * 则说明该tag占两个字节，例如“9F33”；否则占一个字节，例如“95”
 */
public class Value8583Tag2StringCodecImpl implements IExtendValueCodec<String> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Value8583Tag2StringCodecImpl.class);

	public String decoder(InputStream inStream) throws Exception {
		// 首先先读一个字节
		byte[] fisrtdata = new byte[1];
		int readed = 0;
		try {

			while (readed < 1) {
				readed = readed + inStream.read(fisrtdata, readed, 1 - readed);

			}
		} catch (IOException e) {
			LOGGER.error(
					"the Value8583Tag2StringCodecImpl read total [] bytes ,the [] bytes has problem",
					1, readed);
			throw new MapperException(
					"the property  of mapper  read bytes has problem", e);
		}

		String resultS = ConvertTools.bytesToHexString(fisrtdata);
		// // 判断后4位是不是"1111";
		// if ("F".equalsIgnoreCase(resultS.substring(1))) {
		// // 再读一个字节
		// byte[] nextdata = new byte[1];
		// readed = 0;
		// try {
		// while (readed < 1) {
		// readed = readed
		// + inStream.read(nextdata, readed, 1 - readed);
		// }
		// } catch (IOException e) {
		// LOGGER.error(
		// "the Value8583Tag2StringCodecImpl read total [] bytes ,the [] bytes has problem",
		// 1, readed);
		// throw new MapperException(
		// "the property  of mapper  read bytes has problem", e);
		// }
		//
		// resultS = resultS + ConvertTools.bytesToHexString(nextdata);
		// }
		return resultS;

	}

	public byte[] encoder(String obj, int maxwidth) throws Exception {
		byte[] bresult;
		if (obj != null) {
			bresult = ConvertTools.hexStringToByte(obj);
		} else {
			bresult = new byte[0];
		}
		return bresult;
	}

}
