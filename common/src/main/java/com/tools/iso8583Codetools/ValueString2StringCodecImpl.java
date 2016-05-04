package com.tools.iso8583Codetools;

import com.huateng.bomap.common.codec.face.IValueCodec;
import com.toolbox.convert.ConvertTools;

public class ValueString2StringCodecImpl implements IValueCodec<String> {

	public String decoder(byte[] inbytes) throws Exception {
		return ConvertTools.bytesToHexString(inbytes);
	}

	public byte[] encoder(String obj, int maxwidth) throws Exception {
		return ConvertTools.hexStringToByte(obj);
	}

}
