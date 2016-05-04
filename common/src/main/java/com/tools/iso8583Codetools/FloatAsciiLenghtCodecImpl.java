package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.ILengthCodec;

/**
 * The Class FloatAsciiLenghtCodecImpl. 有长度位（ASCII）的加解码类
 */
public class FloatAsciiLenghtCodecImpl implements ILengthCodec {

	private static final String ASCIICHARSET = "US-ASCII";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.ILengthCodec#Decoder(java.io.
	 * ByteArrayInputStream, int)
	 */
	public int decoder(byte[] inbytes) throws Exception {
		String sLength = new String(inbytes, ASCIICHARSET);
		return Integer.parseInt(sLength);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.ILengthCodec#Encoder(int, int)
	 */
	public byte[] encoder(int length, int maxwidth) throws Exception {

		/** 左填0 **/
		if (maxwidth <= 0 || length < 0) {
			return new byte[] {};
		}
		int maxvlaue = (int) Math.pow(10, maxwidth);
		if (length >= maxvlaue) {
			return new byte[] {};
		}

		return String.format(new StringBuffer().append("%0").append(maxwidth).append("d").toString(), length).getBytes(ASCIICHARSET);

	}

}
