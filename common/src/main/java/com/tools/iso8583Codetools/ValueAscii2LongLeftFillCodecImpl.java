package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.IValueCodec;

/**
 * The Class ValueAscii2LongLeftFillCodecImpl.
 * 将ASCII的BYTE数组与Long互转，同时字符是右对齐，左补'0'
 */
public class ValueAscii2LongLeftFillCodecImpl implements IValueCodec<Long> {
	private static final String ASCIICODER = "US-ASCII";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.IValueCodec#Decoder(java.io.
	 * ByteArrayInputStream, int)
	 */
	public Long decoder(byte[] inbytes) throws Exception {

		String sLength = new String(inbytes);
		return Long.valueOf(sLength.trim());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huateng.bomap.common.codec.face.IValueCodec#Encoder(java.lang.Object,
	 * int)
	 */
	public byte[] encoder(Long obj, int maxwidth) throws Exception {
		if (obj == null) {
			return new byte[] {};
		}
		long maxvlaue = (long) Math.pow(10, maxwidth);
		long vlaue = obj.longValue();
		if (Math.abs(vlaue) >= maxvlaue) {
			return new byte[] {};
		}
		return String.format(new StringBuffer().append("%0").append(maxwidth).append("d").toString(), vlaue).getBytes(ASCIICODER);
	}

}
