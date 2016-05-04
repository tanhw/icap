package com.toolbox.util;

import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式化价格（保留2位小数）
 * @ author sys
 *
 */
public class FormatPrice {
	
	private int maxFractionReserve = 2;
	private Pattern pattern;

	public FormatPrice() {
		doCompile();
	}

	public FormatPrice(int maxFractionReserve) {
		this.maxFractionReserve = maxFractionReserve;
		if (this.maxFractionReserve < 0)
			this.maxFractionReserve = 0;
		doCompile();
	}

	private void doCompile() {
		pattern = Pattern.compile("([\\d]*[.]?[\\d]{0,"
				+ Math.max(0, maxFractionReserve) + "}).*");
	}

	/**
	 * 
	 * 核心函数，取数字
	 * @param src - 需要截取的原始数字转换成的字符串
	 * @throws NumberFormatException
	 *             输入格式无法识别
	 * @throws NullPointerException
	 *             空字符串
	 * @throws NoSuchElementException
	 *             无法识别的数字
	 */
	public String coreMatchCut(String src) throws NumberFormatException,
			NullPointerException, NoSuchElementException {
		Double.valueOf(src);
		Matcher matcher = pattern.matcher(src);
		if (matcher.find()){
			return matcher.group(1);
		}
		throw new NoSuchElementException("unrecognized string '" + src + "'");

	}

	/**
	 * 
	 * 如果小数不足，则补0(比如0.5 则显示0.50)
	 * @param src
	 */
	public String amendFraction(String src) {
		String ret = src;
		ret = amendDot(src);
		if (maxFractionReserve > 0) {
			int lenFraction = ret.substring(ret.indexOf(".")).length();
			if (lenFraction < maxFractionReserve + 1) {
				for (int i = lenFraction; i < maxFractionReserve + 1; i++)
					ret += "0";
			}
		}
		return ret;
	}

	/**
	 * 
	 * 整数位填0(如果没有)
	 * @param src
	 */
	public String amendInteger(String src) {
		String ret = src;
		ret = amendDot(ret);
		int posDot = ret.indexOf(".");
		if (posDot == 0 || ret.length() == 0){
			ret = "0" + ret;
		}
		return ret;
	}


	private String amendDot(String src) {
		String ret = src;
		int posDot = ret.indexOf(".");
		if (maxFractionReserve < 1 && posDot >= 0) {
			ret = ret.substring(0, ret.indexOf("."));
		} else if (maxFractionReserve > 0 && posDot < 0) {
			ret += ".";
		}
		return ret;
	}

	
	public static String formatAMT(Long amt) {

		double tempamt = amt / 100.00;
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("0.00");
		return myformat.format(tempamt);

	}

	public static String formatAMT(Double amt) {

		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("0.00");
		return myformat.format(amt);

	}

	public static Long formatLongAMT(String atm) {

		Long LAtm = 0l;

		if (atm != null && !atm.equalsIgnoreCase("")) {

			Double DAtm = new Double(atm);

			LAtm = Math.round(DAtm * 100);
			return LAtm;
		} else {
			return LAtm;
		}

	}
	
}
