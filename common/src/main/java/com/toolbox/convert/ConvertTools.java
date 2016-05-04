package com.toolbox.convert;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

public class ConvertTools {
	/** Mask for bit 0 of a byte. */
	private static final int BIT_0 = 1;

	/** Mask for bit 1 of a byte. */
	private static final int BIT_1 = 0x02;

	/** Mask for bit 2 of a byte. */
	private static final int BIT_2 = 0x04;

	/** Mask for bit 3 of a byte. */
	private static final int BIT_3 = 0x08;

	/** Mask for bit 4 of a byte. */
	private static final int BIT_4 = 0x10;

	/** Mask for bit 5 of a byte. */
	private static final int BIT_5 = 0x20;

	/** Mask for bit 6 of a byte. */
	private static final int BIT_6 = 0x40;

	/** Mask for bit 7 of a byte. */
	private static final int BIT_7 = 0x80;

	private static final int[] BITS = { BIT_0, BIT_1, BIT_2, BIT_3, BIT_4, BIT_5, BIT_6, BIT_7 };

	/** A table of hex digits */
	public static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6',
		'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	/**
	 * Return true if the string is HexChars(1234567890abcdefABCDEF).
	 *
	 */
	public static byte isHexChar(char ch)
	{
		if ('a' <= ch && ch <= 'f')
			return (byte)(ch - 'a' + 10);
		if ('A' <= ch && ch <= 'F')
			return (byte)(ch - 'A' + 10);
		if ('0' <= ch && ch <= '9')
			return (byte)(ch - '0');

		return -1;
	}
	/**
	 * 把16进制字符串转换成字节数组
	 * @param s source string (with Hex representation)
	 * @return byte array
	 */
	public static byte[] hexStringToByte(String s)
	{
		if (null == s)
			return null;

		return hexStringToByte (s, 0, s.length());
	}
	/**
	 * @param   hexString   source string (with Hex representation)
	 * @param   offset      starting offset
	 * @param   count       the length
	 * @return  byte array
	 */
	public static byte[] hexStringToByte(String hexString, int offset, int count)
	{
		if (null == hexString || offset < 0 || count < 2 || (offset + count) > hexString.length())
			return null;

		byte[] buffer =  new byte[count >> 1];
		int stringLength = offset + count;
		int byteIndex = 0;
		for(int i = offset; i < stringLength; i++)
		{
			char ch = hexString.charAt(i);
			if (ch == ' ')
				continue;
			if (ch == '\r' || ch == '\n')
				continue;
			byte hex = isHexChar(ch);
			if (hex < 0)
				return null;
			int shift = (byteIndex%2 == 1) ? 0 : 4;
			if ((byteIndex>>1) >= buffer.length)
				break;
			buffer[byteIndex>>1] |= hex << shift;
			byteIndex++;
		}
		byteIndex = byteIndex>>1;
			if (byteIndex > 0) {
				if (byteIndex < buffer.length) {
					byte[] newBuff = new byte[byteIndex];
					System.arraycopy(buffer, 0, newBuff, 0, byteIndex);
					buffer = null;
					return newBuff;
				}
			} else {
				buffer = null;
			}
			return buffer;
	}

	private static byte toByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2) {
				sb.append(0);
			}
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 把字节数组转换为对象
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static final Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream oi = new ObjectInputStream(in);
		Object o = oi.readObject();
		oi.close();
		return o;
	}

	/**
	 * 把可序列化对象转换成字节数组
	 * 
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static final byte[] objectToBytes(Serializable s) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream ot = new ObjectOutputStream(out);
		ot.writeObject(s);
		ot.flush();
		ot.close();
		return out.toByteArray();
	}

	public static final String objectToHexString(Serializable s) throws IOException {
		return bytesToHexString(objectToBytes(s));
	}

	public static final Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
		return bytesToObject(hexStringToByte(hex));
	}

	/**
	 * @函数功能: BCD码转为10进制串(阿拉伯数据)
	 * @输入参数: BCD码
	 * @输出结果: 10进制串
	 */
	public static String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
	}

	/**
	 * @函数功能: 10进制串转为BCD码
	 * @输入参数: 10进制串
	 * @输出结果: BCD码
	 */
	public static byte[] str2Bcd(String ascii) {
		String asc = ascii;
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = null;
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	public static long bytes2int(byte[] resource) {
		if (resource == null) {
			return 0;
		}
		if (resource.length >= 4) {
			return 0;
		}
		int mask = 0xff;
		int temp = 0;
		int res = 0;
		byte[] b = new byte[] { 0, 0, 0, 0 };
		System.arraycopy(resource, 0, b, 4 - resource.length, resource.length);
		for (int i = 0; i < 4; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}

	public static byte[] int2bytes(int num) {
		byte[] b = new byte[4];
		long value = num;
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) ((value >>> (24 - i * 8)) & 0xFF);
		}
		return b;
	}

	/**
	 * 把字节数组转换成ASCII '0''1'表示字符串,不使用apache的codec的原因是它的bincodec是反过来的
	 * 
	 * @param raw
	 * @return
	 */
	public static final char[] bytesToascii(byte[] raw) {
		if (raw == null || raw.length == 0) {
			return new char[0];
		}
		// get 8 times the bytes with 3 bit shifts to the left of the length
		char[] lascii = new char[raw.length << 3];
		/*
		 * We decr index jj by 8 as we go along to not recompute indices using
		 * multiplication every time inside the loop.
		 */
		for (int ii = raw.length - 1, jj = lascii.length - 1; ii >= 0; ii--, jj -= 8) {
			for (int bits = 0; bits < BITS.length; ++bits) {
				if ((raw[ii] & BITS[bits]) == 0) {
					lascii[jj - bits] = '0';
				} else {
					lascii[jj - bits] = '1';
				}
			}
		}
		return lascii;
	}

	public static final byte[] acsiiTobytes(char[] ascii) {
		if (ascii == null || ascii.length == 0) {
			return new byte[0];
		}
		// get length/8 times bytes with 3 bit shifts to the right of the length
		byte[] lraw = new byte[ascii.length >> 3];
		/*
		 * We decr index jj by 8 as we go along to not recompute indices using
		 * multiplication every time inside the loop.
		 */
		for (int ii = lraw.length - 1, jj = ascii.length - 1; ii >= 0; ii--, jj -= 8) {
			for (int bits = 0; bits < BITS.length; ++bits) {
				if (ascii[jj - bits] == '1') {
					lraw[ii] |= BITS[bits];
				}
			}
		}
		return lraw;

	}

	/**
	 * CRC32 校验
	 * 
	 * @param hex
	 * @return String
	 * @throws Exception
	 */
	public static String getCRC32Code(String hex) throws Exception {
		byte[] hexBytes = hexStringToByte(hex.toUpperCase());
		InputStream is = new ByteArrayInputStream(hexBytes);
		CRC32 crc32 = new CRC32();
		for (CheckedInputStream checkedinputstream = new CheckedInputStream(is, crc32); checkedinputstream.read() != -1;) {
		}
		return Long.toHexString(crc32.getValue()).toUpperCase();
	}

	/**
	 * CRC32 校验
	 * 
	 * @param hexBytes
	 * @return
	 * @throws Exception
	 */
	public static String getCRC32Code(byte[] hexBytes) throws Exception {
		InputStream is = new ByteArrayInputStream(hexBytes);
		CRC32 crc32 = new CRC32();
		for (CheckedInputStream checkedinputstream = new CheckedInputStream(is, crc32); checkedinputstream.read() != -1;) {
		}
		return Long.toHexString(crc32.getValue()).toUpperCase();
	}

	/**
	 * 将bitmap字符串转换成十进制
	 * 
	 * @desc 0111111111000000000000000000000000000000000000000000000000000000
	 * @param bitmap
	 * @return
	 */
	public static String convertBitmapToHexString(String bitmap) {
		return ConvertTools.bytesToHexString(ConvertTools.acsiiTobytes(bitmap.toCharArray()));
	}



	/**
	 * 将10进制数字字符串转化为指定的后补F的16进制ascii字符串如:666转换为363636
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String CharAsciiString2hexAsciiStringWithLength(String s ) throws Exception {

		StringBuffer sb = new StringBuffer();
		char [] c = s.toCharArray();
		for(int i=0; i < c.length; i++){

			int temp = (int)c[i];
			String hex = Integer.toHexString(temp);
			sb.append(hex);
		}
		return sb.toString();

	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String s = "0123456789ABCDEF";
		byte[] bcd = ASCII_To_BCD(s.getBytes(), s.length());
		String s1 = bcd2Str2(bcd);

	}

	private static byte asc_to_bcd(byte asc) {
		byte bcd;

		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	public static byte[] ASCII_To_BCD(byte[] ascii, int asc_len) {
		byte[] bcd = new byte[asc_len / 2];
		int j = 0;
		for (int i = 0; i < (asc_len + 1) / 2; i++) {
			bcd[i] = asc_to_bcd(ascii[j++]);
			bcd[i] = (byte) (((j >= asc_len) ? 0x00 : asc_to_bcd(ascii[j++])) + (bcd[i] << 4));
		}
		return bcd;
	}

	public static String bcd2Str2(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;

		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}
	/**
	 * 16进制字符串转10进制
	 * @param hex
	 * @return
	 */

	public static int hexStringToOctal(String hex){

		int result = 0;

		for(char c:hex.toCharArray()){
			result = result*16 + Character.digit(c, 16);
		}	

		return result;
	}


	/**
	 *  HEX转成String
	 * @param abyte0
	 * @param beginIndex
	 * @param endIndex
	 * @param spaceFlag 是否补位 
	 * @return
	 */

	public static String toHexString(byte abyte0[], int beginIndex, int endIndex, boolean spaceFlag)
	{
		if(null == abyte0)
			return null;
		if(0 == abyte0.length)
			return "";
		StringBuffer sbuf = new StringBuffer();
		appendHex(sbuf, abyte0[beginIndex]);
		for(int i = (beginIndex + 1); i < endIndex; i++)
		{
			if (spaceFlag)
				sbuf.append(' ');
			appendHex(sbuf, abyte0[i]);
		}
		String returnString = sbuf.toString();
		sbuf = null;
		return returnString;
	}
	public static void appendHex(StringBuffer stringbuffer, byte byte0)
	{
		stringbuffer.append(toHexChar(byte0 >> 4));
		stringbuffer.append(toHexChar(byte0));
	}

	public static char toHexChar(int nibble)
	{
		return hexDigit[(nibble & 0xF)];
	}

}
