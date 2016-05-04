package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.IValueCodec;
import com.huateng.bomap.common.util.BytesTool;
import com.huateng.bomap.common.util.ConvertTools;

/**
 * The Class ValueByte2HexStringCodecImpl. 将BYTE数组与十六进制的ASCII码互转
 */
public class ValueByte2HexStringCodecImpl implements IValueCodec<String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.huateng.bomap.common.codec.face.IValueCodec#Decoder(java.io.
	 * ByteArrayInputStream, int)
	 */
	public String decoder(byte[] inbytes) throws Exception {
		return ConvertTools.bytesToHexString(inbytes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huateng.bomap.common.codec.face.IValueCodec#Encoder(java.lang.Object,
	 * int)
	 */
	public byte[] encoder(String obj, int maxwidth) throws Exception {
		if (obj == null) {
			return new byte[] {};
		}
		byte[] resultbytes = ConvertTools.hexStringToByte(obj);
		return BytesTool.getSubBytes(resultbytes, 0, maxwidth);
	}
}
