package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.IValueCodec;
import com.huateng.bomap.common.util.BytesTool;
import com.huateng.bomap.common.util.ConvertTools;

/**
 * The Class ValueBcd2LongLeftFillCodecImpl. 将BCD的BYTE数组与String互转
 */
public class ValueBcd2StringFillCodecImpl implements IValueCodec<String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.IValueCodec#Decoder(java.io.
	 * ByteArrayInputStream, int)
	 */
	public String decoder(byte[] inbytes) throws Exception {
		StringBuffer temp = new StringBuffer(inbytes.length * 2);
		for (int i = 0; i < inbytes.length; i++) {
			temp.append((byte) ((inbytes[i] & 0xf0) >>> 4));
			temp.append((byte) (inbytes[i] & 0x0f));
		}
		return temp.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huateng.bomap.common.codec.face.IValueCodec#Encoder(java.lang.Object,
	 * int)
	 */
	public byte[] encoder(String obj, int maxwidth) throws Exception {
		String sLength = null;

		int tempwidth = maxwidth;

		if (obj == null) {
			return new byte[] {};
		}
		sLength = obj;

		byte[] temp = null;
		if (sLength.length() > tempwidth * 2) {
			return new byte[] {};
		}
		temp = ConvertTools.str2Bcd(sLength);
		return BytesTool.leftFill(temp, tempwidth, (byte) 0x00);
	}

}
