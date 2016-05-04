package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.IValueCodec;
import com.huateng.bomap.common.util.BytesTool;
import com.huateng.bomap.common.util.ConvertTools;

/**
 * The Class ValueBcd2LongLeftFillCodecImpl. 将BCD的BYTE数组与Long互转，同时字符是右对齐，左补'0'
 */
public class ValueBcd2LongLeftFillCodecImpl implements IValueCodec<Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.IValueCodec#Decoder(java.io.
	 * ByteArrayInputStream, int)
	 */
	public Long decoder(byte[] inbytes) throws Exception {

		String sLength = ConvertTools.bcd2Str(inbytes);
		return Long.valueOf(sLength);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huateng.bomap.common.codec.face.IValueCodec#Encoder(java.lang.Object,
	 * int)
	 */
	public byte[] encoder(Long obj, int maxwidth) throws Exception {
		String sLength = null;

		int tempwidth = maxwidth;

		if (obj == null) {
			return new byte[] {};
		}
		sLength = obj.toString();

		byte[] temp = null;
		if (sLength.length() > tempwidth * 2) {
			return new byte[] {};
		}
		temp = ConvertTools.str2Bcd(sLength);
		byte[] filledbyte = BytesTool.leftFill(temp, tempwidth, (byte) 0x00);

		return filledbyte;
	}

}
