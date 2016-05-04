/**
 * 
 */
package com.toolbox.util;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ author sys
 *
 */
public class StringUtil {

	
	/**
	 * 已分隔符来输出传入的字符串
	 * @ author sys
	 * @param split
	 * @param obj
	 * @return
	 */
	public static String toStringSpilt(String split,String ... obj){
		if (obj==null) return "";
		
		StringBuffer sbBuffer=new StringBuffer();
		for (String s:obj){
			if (s!=null && !s.equals("")){
				if (sbBuffer.length()==0)
					sbBuffer.append(s);
				else 
					sbBuffer.append(split).append(s);
			}
		}
		
		return sbBuffer.toString();
	}
	
	
	/**
	 * 按照长度获取字符串，如果超出截取最大长度，后面加...
	 * @ author sys
	 * @param str
	 * @param len
	 * @return
	 */
	public static String maxString(String str,Integer len){
		if (str==null) return str;
		if (str.length()<=len) return str;
		return str.substring(0, len)+"...";
	}

	/**
	 * 判断传入参数,如果是Null或者空值，返回false，不为空返回true
	 * @ author sys
	 * @param checkAll   True:所有的为空才返回,False:只要有一个为空返回
	 * @return
	 */
	public static Boolean checkNull(Boolean checkAll,Object ...objects ){
		Boolean ret=true;
		if (objects==null) return false;
		for (Object s : objects) {
			if (s==null || s.toString().trim().equals("")){
				if (!checkAll) 
					return false;
				else {
					ret= false;
				}
			}
		}
		return ret;
	}
	
	
	
	/**
	 * 返回等长字符，如果前缀+字符串>长度，返回字符串
	 * @ author sys
	 * @param prefix 前缀
	 * @param len
	 * @param str
	 * @return
	 */
	public static String getMaxLength(String prefix,int len,String str){
		if (!checkNull(false,str) || str.length()>=len ) return str;
		
		if (prefix.length()+str.length()>len) return str;
		
		StringBuilder sb=new StringBuilder();
		sb.append(prefix);
		for (int i = 0; i < len-prefix.length()- str.length(); i++) {
			sb.append("0");
		}
		sb.append(str);
		
		return sb.toString();
	}
	
	/**
	 * 字符串分隔
	 * @author 朱北海
	 * @param split
	 * @param obj
	 * @return
	 */
	public static String[] toStringSpilt(String split,String obj){
		if (obj==null) return new String[0];
		
		String[] strSplit = obj.split(split);
		return strSplit;
	}
	
	/**
	 * 验证List是否为空
	 * @author gyq
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Boolean checkList(List list){
		if(list != null && list.size() != 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 过滤特殊字符,只要是判断网页的参数传递
	 * 目前过滤的是 <,>,&
	 * @ author sys
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String filterSpecial(String str) throws Exception{
		if (str==null) return null;
		String res=str.replaceAll("<[^>]*>", "");
		res=res.replaceAll("<", "");
		res=res.replaceAll(">", "");
		res=res.replaceAll("&", "");
		
		return res;
	}
	
	
	/**
	 * 判断是否包含特殊字符串
	 * @author 程欣伟
	 * 2013-2-21下午12:52:12
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static boolean hasFilterSpecial(String str) throws Exception {
		
		if(str.equals(filterSpecial(str))){
			return true;
		}
		return false;
		
	}
	
	
	/**
	 * 获得随机密码方法
	 * 
	 */
	public static String getRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}
	
	public static String leftAddChar(String seq,int length,String s) {
		
		if("".equals(seq)||seq==null){
			seq=s;
		}
		
		if(seq.length() > length) {
			return seq;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length - seq.length(); i++) {
			sb.append(s);
		}
		sb.append(seq);
		return sb.toString();
	}
	
	
	public static String rightAddChar(String seq,int length,String s) {
	
		if("".equals(seq)||seq==null){
			seq=s;
		}
		
		if(seq.length() > length) {
			return seq;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(seq);
		for (int i = 0; i < length - seq.length(); i++) {
			sb.append(s);
		}
		return sb.toString();
	}
	
	/**
	 * 支持中英补位
	 * @param seq
	 * @param length
	 * @param s
	 * @return
	 */
	
	public static String rightAddCharCN(String seq,int length,String s) {
	
		if("".equals(seq)||seq==null){
			seq=s;
		}
		
		if(seq.length() > length) {
			return seq;
		}else if(seq.getBytes().length> length){//中文
			return seq;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(seq);
		if(seq.length()==seq.getBytes().length){
			for (int i = 0; i < length - seq.length(); i++) {
				sb.append(s);
			}
		}else{
			for (int i = 0; i < length - seq.getBytes().length; i++) {
				sb.append(s);
			}
		}
		return sb.toString();
	}
	
	
	public static boolean downLoadFile(String filePath,
	        HttpServletResponse response, String fileName, String fileType)
	        throws Exception {
	        File file = new File(filePath);  //根据文件路径获得File文件
	        //设置文件类型(这样设置就不止是下Excel文件了，一举多得)
	        if("pdf".equals(fileType)){
	           response.setContentType("application/pdf;charset=GBK");
	        }else if("xls" ==  fileType || "xls".equals(fileType)){
	           response.setContentType("application/vnd.ms-excel;charset=GBK");
	        }else if("doc".equals(fileType)){
	           response.setContentType("application/msword;charset=GBK");
	        }
	        //文件名
	        response.setHeader("Content-Disposition", "attachment;filename=\""
	            + new String(fileName.getBytes(), "ISO8859-1") + "\"");
	        response.setContentLength((int) file.length());
	        byte[] buffer = new byte[4096];// 缓冲区
	        BufferedOutputStream output = null;
	        BufferedInputStream input = null;
	        try {
	          output = new BufferedOutputStream(response.getOutputStream());
	          input = new BufferedInputStream(new FileInputStream(file));
	          int n = -1;
	          //遍历，开始下载
	          while ((n = input.read(buffer, 0, 4096)) > -1) {
	             output.write(buffer, 0, n);
	          }
	          output.flush();   //不可少
	          response.flushBuffer();//不可少
	        } catch (Exception e) {
	          //异常自己捕捉 
	        	e.printStackTrace();
	        } finally {
	           //关闭流，不可少
	           if (input != null)
	                input.close();
	           if (output != null)
	                output.close();
	        }
	       return false;
	    }
	
	
	/** 
	  * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回 
	  * @param sourceDate 
	  * @param formatLength 
	  * @return 重组后的数据 
	  */  
	 public static String frontCompWithZore(int sourceDate,int formatLength)  
	 {  
	  /* 
	   * 0 指前面补充零 
	   * formatLength 字符总长度为 formatLength 
	   * d 代表为正数。 
	   */  
	  String newString = String.format("%0"+formatLength+"d", sourceDate);  
	  return  newString;  
	 }

	public static String formatRegEx(String regEx) {

		String regex = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(regex);

		return m.replaceAll("").trim();

	}

}
