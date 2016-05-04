package com.posp.utils;

/**
 *  前置系统
 *  交易名称/交易字段  字典
 * @author rain
 *
 */
public class TlvDataDict {

	/**
	 * 	字段
	 * @author rain
	 *
	 */
	public enum Fields{

		MsgType("0001", "消息类型","ASC"),
		PlatformMercId("1001", "平台商户号","ASC"),
		PlatformPosId("1002", "平台终端号","ASC"),
		BankMercId("1003", "银行商户号","ASC"),
		BankPosId("1004", "银行终端号","ASC"),
		UpLoadDateTime("1005", "交易上送时间","BCD"),
		SystemDateTime("1006", "系统时间","BCD"),
		MainKey("1007", "主密钥","BCD"),		
		;


		String tag;
		String name;
		String type;//	数据类型 

		private Fields(String  tag, String name,String type) {
			this.tag = tag;
			this.name = name;
			this.type = type;
		}

		public String getTag() {
			return tag;
		}

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		public static String getNameByTag(String tag){
			if(MsgType.tag.equals(tag)){

				return MsgType.name;

			}else if(PlatformMercId.tag.equals(tag)){

				return PlatformMercId.name;

			}else if(PlatformPosId.tag.equals(tag)){

				return PlatformPosId.name;

			}else if(BankMercId.tag.equals(tag)){

				return BankMercId.name;

			}else if(BankPosId.tag.equals(tag)){

				return BankPosId.name;

			}else if(UpLoadDateTime.tag.equals(tag)){

				return UpLoadDateTime.name;

			}else if(SystemDateTime.tag.equals(tag)){

				return SystemDateTime.name;

			}else if(MainKey.tag.equals(tag)){

				return MainKey.name;

			}else {
				return null;
			}	
		}

		public static String getTypeByTag(String tag){
			if(MsgType.tag.equals(tag)){

				return MsgType.type;

			}else if(PlatformMercId.tag.equals(tag)){

				return PlatformMercId.type;

			}else if(PlatformPosId.tag.equals(tag)){

				return PlatformPosId.type;

			}else if(BankMercId.tag.equals(tag)){

				return BankMercId.type;

			}else if(BankPosId.tag.equals(tag)){

				return BankPosId.type;

			}else if(UpLoadDateTime.tag.equals(tag)){

				return UpLoadDateTime.type;

			}else if(SystemDateTime.tag.equals(tag)){

				return SystemDateTime.type;

			}else if(MainKey.tag.equals(tag)){

				return MainKey.type;

			}else {
				return null;
			}	
		}
	}

	/**
	 *  交易代码
	 * @author rain
	 *
	 */

	public enum MsgType{
		
		MainKey("1100", "1110","主密钥下载"),
		SignIn("1200", "1210","签到"),
		;
		String request; // 请求交易类型
		String response;// 返回交易类型
		String name;

		private MsgType(String  request, String response,String name) {

			this.request = request;
			this.response = response;
			this.name = name;
		}

		public String getRequest() {
			return request;
		}

		public String getResponse() {
			return response;
		}

		public String getName() {
			return name;
		}

	}	
	
}
