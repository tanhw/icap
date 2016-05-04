package com.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <a href="CommonFunction.java.html"><b><i>View Source</i></b></a>
 * 
 * Description ★ 通用函数
 * 
 * @author Administrator
 */
public final class CommonFunction {

	private CommonFunction() {
	}

	/**
	 * 按yyyyMMdd得到当前日期
	 * 
	 * @return
	 */
	public static String getCurrDate() {
		String currentDate = null;
		/* 使用webserver机器时间 */
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		currentDate = formater.format(new Date());
		return currentDate;
	}

	/**
	 * 按HHmmss得到当前时间
	 * 
	 * @return
	 */
	public static String getCurrTime() {
		String currentDate = null;
		/* 使用webserver机器时间 */
		SimpleDateFormat formater = new SimpleDateFormat("HHmmss");
		currentDate = formater.format(new Date());
		return currentDate;
	}

	/**
	 * 字符串填充函数
	 * 
	 * @param string
	 * @param filler
	 * @param totalLength
	 * @param atEnd
	 * @return
	 */
	public static String fillString(String string, char filler, int totalLength, boolean atEnd) {
		byte[] tempbyte = (string == null ? "" : string).getBytes();
		int currentLength = tempbyte.length;
		int delta = totalLength - currentLength;
		for (int i = 0; i < delta; i++) {
			if (atEnd) {
				string += filler;
			} else {
				string = filler + string;
			}
		}
		return string;
	}



	public static String getLocalDateTime14() {
		SimpleDateFormat dateTime14Fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateTime14Fmt.format(Calendar.getInstance().getTime());
	}

}