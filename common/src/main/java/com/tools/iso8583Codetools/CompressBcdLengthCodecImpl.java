package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.ILengthCodec;
import com.toolbox.convert.ConvertTools;

/**
 * The Class FloatBcdLengthCodecImpl.有长度位（BCD）的加解码类
 */
public class CompressBcdLengthCodecImpl implements ILengthCodec {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.ILengthCodec#Decoder(java.io.
	 * ByteArrayInputStream, int)
	 */
	public int decoder(byte[] inbytes) throws Exception {

		String sLength = ConvertTools.bcd2Str(inbytes);
		return (int) Math.round(Double.parseDouble(sLength) / 2);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.ILengthCodec#Encoder(int, int)
	 */
	public byte[] encoder(int length, int maxwidth) throws Exception {
		int outputwidth = maxwidth * 2;
		byte[] temp = null;
		if (maxwidth <= 0 || length < 0) {
			return new byte[] {};
		}
		int maxvlaue = (int) Math.pow(10, outputwidth);
		if (length >= maxvlaue) {
			return new byte[] {};
		}
		StringBuffer strBuffer = new StringBuffer();
		String sLength = String.format(strBuffer.append("%0").append(outputwidth).append("d").toString(), length * 2);

		temp = ConvertTools.str2Bcd(sLength);

		return temp;
	}

}
