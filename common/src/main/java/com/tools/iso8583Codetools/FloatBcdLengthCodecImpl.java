package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.ILengthCodec;
import com.huateng.bomap.common.util.ConvertTools;

/**
 * The Class FloatBcdLengthCodecImpl.有长度位（BCD）的加解码类
 */
public class FloatBcdLengthCodecImpl implements ILengthCodec {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.ILengthCodec#Decoder(java.io.
	 * ByteArrayInputStream, int)
	 */
	public int decoder(byte[] inbytes) throws Exception {

		String sLength = ConvertTools.bcd2Str(inbytes);
		return Integer.parseInt(sLength);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.ILengthCodec#Encoder(int, int)
	 */
	public byte[] encoder(int length, int maxwidth) throws Exception {
		int outputwidth = maxwidth * 2;
		if (maxwidth <= 0 || length < 0) {
			return new byte[] {};
		}
		int maxvlaue = (int) Math.pow(10, outputwidth);
		if (length >= maxvlaue) {
			return new byte[] {};
		}

		String sLength = String.format(new StringBuffer().append("%0").append(outputwidth).append("d").toString(), length);

		return ConvertTools.str2Bcd(sLength);

	}

}
