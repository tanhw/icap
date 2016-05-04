package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.IExtendLengthCodec;
import com.huateng.bomap.common.exception.MapperException;
import com.huateng.bomap.common.util.ConvertTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Class Tlv8583LengthCodecImpl.858长度加减码段
 * 子域长度（即L本身）的属性也为bit，占1～3个字节长度。具体编码规则如下： 1)
 * 当L字段最左边字节的最左bit位（即bit8）为0，表示该L字段占一个字节
 * ，它的后续7个bit位（即bit7～bit1）表示子域取值的长度，采用二进制数表示子域取值长度的十进制数
 * 。例如，某个域取值占3个字节，那么其子域取值长度表示为“00000011”。所以，若子域取值的长度在1～127字节之间，那么该L字段本身仅占一个字节。
 * 2) 当L字段最左边字节的最左bit位（即bit8）为1，表示该L字段不止占一个字节，那么它到底占几个字节由该最左字节的后续7个bit位（即bit7～
 * bit1）的十进制取值表示。例如，若最左字节为10000010，表示L字段除该字节外，后面还有两个字节。其后续字节的十进制取值表示子域取值的长度。例如，
 * 若L字段为“1000 0001 1111
 * 1111”，表示该子域取值占255个字节。所以，若子域取值的长度在127～255字节之间，那么该L字段本身需占两个字节。 本类只考虑最大655536长度
 */

public class Tlv8583LengthCodecImpl implements IExtendLengthCodec {
	private static final Logger LOGGER = LoggerFactory.getLogger(Tlv8583LengthCodecImpl.class);

	public int decoder(InputStream inStream, int maxwidth) throws Exception {
		// 首先先读一个字节
		byte[] fisrtdata = new byte[1];
		int readed = 0;
		try {

			while (readed < 1) {
				readed = readed + inStream.read(fisrtdata, readed, 1 - readed);

			}
		} catch (IOException e) {
			LOGGER.error("the Tlv8583LengthCodecImpl read total [] bytes ,the [] bytes has problem", 1, readed);
			throw new MapperException("the property  of mapper  read bytes has problem", e);
		}

		// 判断第8位是不是"1";
		if ((fisrtdata[0] & 0X80) > 0) {
			// 再读多少个字节由最低2位定义
			int nextbytelen = (fisrtdata[0] & 0x03);
			byte[] nextdata = new byte[nextbytelen];
			readed = 0;
			try {

				while (readed < nextbytelen) {
					readed = readed + inStream.read(nextdata, readed, nextbytelen - readed);

				}
			} catch (IOException e) {
				LOGGER.error("the Tlv8583LengthCodecImpl read total [] bytes ,the [] bytes has problem", nextbytelen, readed);
				throw new MapperException("the property  of mapper  read bytes has problem", e);
			}
			return (int) ConvertTools.bytes2int(nextdata);
		}
		return (int) ConvertTools.bytes2int(fisrtdata);

	}

	public byte[] encoder(int length, int maxwidth) throws Exception {
		byte[] result = null;
		/** 只考虑长度位最大为2个字节的情况 **/
		ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
		if (maxwidth > 2 || length > 65536) {
			return new byte[0];
		}
		if (length < 128) {
			result = ConvertTools.int2bytes(length);
			resultStream.write(result[3]);
		} else if (length > 127 && length < 256) {
			result = ConvertTools.int2bytes(length);
			resultStream.write(0x81);
			resultStream.write(result[3]);
		} else {
			result = ConvertTools.int2bytes(length);
			resultStream.write(0x82);
			resultStream.write(result[2]);
			resultStream.write(result[3]);
		}
		return resultStream.toByteArray();
	}

}
