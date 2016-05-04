package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.IValueCodec;
import com.huateng.bomap.common.util.ConvertTools;

public class ValueBitmap2CharArraysCodecImpl implements IValueCodec<char[]> {

	public char[] decoder(byte[] inbytes) throws Exception {

		return ConvertTools.bytesToascii(inbytes);
	}

	public byte[] encoder(char[] obj, int maxwidth) throws Exception {

		return ConvertTools.acsiiTobytes(obj);
	}

}
