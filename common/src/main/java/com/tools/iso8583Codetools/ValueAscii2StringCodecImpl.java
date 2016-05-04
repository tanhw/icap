package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.IValueCodec;

/**
 * The Class ValueAsciiRightFillCodec. 将ASCII的BYTE数组与String互转，同时字符是左对齐右补空格
 */
public class ValueAscii2StringCodecImpl implements IValueCodec<String> {
	private static final String GBK = "gbk";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.IValueCodec#Decoder(java.io.
	 * ByteArrayInputStream, int)
	 */
	public String decoder(byte[] inbytes) throws Exception {

		String result = new String(inbytes);

		return result.trim();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huateng.bomap.common.codec.face.IValueCodec#Encoder(java.lang.Object,
	 * int)
	 */
	public byte[] encoder(String obj, int maxwidth) throws Exception {

		byte[] result = null;
		if (obj == null) {
			return new byte[] {};
		}
		result = obj.getBytes(GBK);

		return result;
	}

}
