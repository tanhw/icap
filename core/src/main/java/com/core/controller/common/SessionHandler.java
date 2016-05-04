/**
 * 
 */
package com.core.controller.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.core.models.TSysmenu;
import com.core.models.TSysrole;
import com.core.models.TUnitInfo;

/**
 * 各接入机构、供应商(商户) Session控制中心
 * 
 * @ author sys
 * 
 */
public class SessionHandler {

	private static final String S_ROLE = "S_ROLE";

	private static final String S_ACTION_CODE = "S_ACTION_CODE";
	private static final String S_USER = "S_USER";
	private static final String S_ACTION = "S_ACTION";
	private static final String S_COM_ID = "S_COM_ID";
	private static final String S_CARD_NO = "S_CARD_NO";
	private static final String S_OP_TYPE = "S_OP_TYPE";
	private static final String S_IP = "S_IP";
	private static final String S_USER_UNITID = "S_USER_UNITID";
	private static final String S_USER_CAMPID = "S_USER_CAMPID";
	private static final String S_MENU = "S_MENU";
	private static final String S_UNIT = "S_UNIT";
	private static final String S_MER = "S_MER";
	private static final String S_CAMP = "S_CAMP";
	private static final String S_CHECK = "S_CHECK";
	private static final String S_LOGIN_TIME = "S_LOGIN_TIME";

	private static final ThreadLocal<Map<String, Object>> currentThread = new ThreadLocal<Map<String, Object>>();

	/**
	 * 获取当前操作员权限
	 * 
	 * @ author sys
	 * @return
	 */
	public static TSysrole getCurrentRole() {
		if (currentThread.get() != null)
			return (TSysrole) currentThread.get().get(S_ROLE);
		else {
			return null;
		}
	}

	/**
	 * 设置当前操作员权限
	 * 
	 * @ author sys
	 * @param roleMap
	 */
	public static void setCurrentRole(TSysrole sysrole) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_ROLE, sysrole);
		currentThread.set(threadMap);
	}
	
	

	/**
	 * 设置当前登录时间
	 * 
	 * @ author sys
	 * @param menuCode
	 */
	public static void setCurrentLoginTime(Date loginTime) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_LOGIN_TIME, loginTime);
		currentThread.set(threadMap);
	}

	/**
	 * 获取 当前登录时间
	 * 
	 * @ author sys
	 * @return
	 */
	public static Date getCurrentLoginTime() {
		if (currentThread.get() != null)
			return (Date) currentThread.get().get(S_LOGIN_TIME);
		else {
			return null;
		}
	}


	/**
	 * 设置当前 操作的功能菜单
	 * 
	 * @ author sys
	 * @param menuCode
	 */
	public static void setCurrentRightCode(String menuCode) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_ACTION_CODE, menuCode);
		currentThread.set(threadMap);
	}

	/**
	 * 获取 当前 操作的功能菜单
	 * 
	 * @ author sys
	 * @return
	 */
	public static String getCurrentRightCode() {
		if (currentThread.get() != null)
			return (String) currentThread.get().get(S_ACTION_CODE);
		else {
			return null;
		}
	}

	/**
	 * 获取存入的所有信息
	 * 
	 * @ author sys
	 * @return
	 */
	public static Map<String, Object> getCurrentAll() {
		return currentThread.get();
	}

	/**
	 * 设置所有信息
	 * 
	 * @ author sys
	 * @param sessionAll
	 */
	public static void setCurrentAll(Map<String, Object> sessionAll) {
		currentThread.set(sessionAll);
	}

	/**
	 * 获取当期操作员信息
	 * 
	 * @ author sys
	 * @return
	 */
	public static Object getCurrentUser() {
		if (currentThread.get() != null)
			return currentThread.get().get(S_USER);
		else {
			return null;
		}
	}
	
	/**
	 * 设置当前操作员信息
	 * 
	 * @ author sys
	 * @param user
	 */
	public static void setCurrentUser(Object user) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_USER, user);
		currentThread.set(threadMap);
	}

	/**
	 * 设置 当前功能按钮 代号
	 * 
	 * @ author sys
	 * @param actionCode
	 */
	public static void setCurrentAction(String actionCode) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_ACTION, actionCode);
		currentThread.set(threadMap);
	}

	/**
	 * 获取 当前 功能按钮 代号
	 * 
	 * @ author sys
	 * @return
	 */
	public static String getCurrentAction() {
		if (currentThread.get() != null)
			return (String) currentThread.get().get(S_ACTION);
		else {
			return null;
		}
	}

	/**
	 * 设置 当前 操作者IP
	 * 
	 * @ author sys
	 * @param actionCode
	 */
	public static void setCurrentIP(String ip) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_IP, ip);
		currentThread.set(threadMap);
	}

	/**
	 * 获取 当前 操作员IP
	 * 
	 * @ author sys
	 * @return
	 */
	public static String getCurrentIP() {
		if (currentThread.get() != null)
			return (String) currentThread.get().get(S_IP);
		else {
			return null;
		}
	}

	/**
	 * 设置当前 商户号
	 * 
	 * @ author sys
	 * @param menuCode
	 */
	public static void setCurrentMerchantId(Long merCode) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_MER, merCode);
		currentThread.set(threadMap);
	}

	
	/**
	 * 获取 当前 商户号
	 * 
	 * @ author sys
	 * @return
	 */
	public static Long getCurrentMerchantId() {
		if (currentThread.get() != null)
			return (Long) currentThread.get().get(S_MER);
		else {
			return null;
		}
	}
	
	/**
	 * 设置当前 园区号
	 * 
	 * @ author sys
	 * @param menuCode
	 */
	public static void setCurrentCampId(Long campid) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_CAMP, campid);
		currentThread.set(threadMap);
	}

	
	/**
	 * 获取 当前 园区号
	 * 
	 * @ author sys
	 * @return
	 */
	public static Long getCurrentCampId() {
		if (currentThread.get() != null)
			return (Long) currentThread.get().get(S_CAMP);
		else {
			return null;
		}
	}
	
	/**
	 * 设置当前 公司ID
	 * 
	 * @ author sys
	 * @param menuCode
	 */
	public static void setCurrentCompanyID(Long ID) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_COM_ID, ID);
		currentThread.set(threadMap);
	}

	
	/**
	 * 获取 当前 公司
	 * 
	 * @ author sys
	 * @return
	 */
	public static Long getCurrentCompanyID() {
		if (currentThread.get() != null)
			return (Long) currentThread.get().get(S_COM_ID);
		else {
			return null;
		}
	}

	/**
	 * 设置当前操作员类别
	 * 
	 * @ author sys
	 * @param menuCode
	 */
	public static void setCurrentOpType(String type) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_OP_TYPE, type);
		currentThread.set(threadMap);
	}

	
	/**
	 * 设置当前 卡号
	 * 
	 * @ author sys
	 * @param menuCode
	 */
	public static void setCurrentCardNO(String cardNo) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_CARD_NO, cardNo);
		currentThread.set(threadMap);
	}

	
	/**
	 * 获取 当前 卡号
	 * 
	 * @ author sys
	 * @return
	 */
	public static String getCurrentCardNO() {
		if (currentThread.get() != null)
			return (String) currentThread.get().get(S_CARD_NO);
		else {
			return null;
		}
	}
	
	/**
	 * 获取 当前 操作员类别
	 * 
	 * @ author sys
	 * @return
	 */
	public static String getCurrentOpType() {
		if (currentThread.get() != null)
			return (String) currentThread.get().get(S_OP_TYPE);
		else {
			return "";
		}
	}
	
	
	
	/**
	 * 获取当期机构
	 * 
	 * @ author sys
	 * @return
	 */
	public static Long getCurrentUnitId() {
		if (currentThread.get() != null)
			return (Long) currentThread.get().get(S_USER_UNITID);
		else {
			return null;
		}
	}

	/**
	 * 设置当前机构
	 * 
	 * @ author sys
	 * @param userType
	 */
	public static void setCurrentUnitId(Long unitId) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_USER_UNITID, unitId);
		currentThread.set(threadMap);
	}
	
	/**
	 * 获取当前园区
	 * 
	 * @ author sys
	 * @return
	 */
	public static Long getCurrentCampid() {
		if (currentThread.get() != null)
			return (Long) currentThread.get().get(S_USER_CAMPID);
		else {
			return null;
		}
	}

	/**
	 * 设置当前园区
	 * 
	 * @ author sys
	 * @param userType
	 */
	public static void setCurrentCampid(Long campid) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_USER_CAMPID, campid);
		currentThread.set(threadMap);
	}
	


	/**
	 * 清空当前用户信息
	 * 
	 * @ author sys
	 */
	public static void clearCurrent() {
		currentThread.set(null);
	}
	
	/**
	 * 获取当前操作员菜单
	 * 
	 * @ author sys
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<TSysmenu> getAllMeun() {
		if (currentThread.get() != null){
			return (List<TSysmenu>) currentThread.get().get(S_MENU);
		}
		else {
			return null;
		}
	}
	
	/**
	 * 设置当前操作员菜单
	 * 
	 * @ author sys
	 * @param userBusiness
	 */
	public static void setAllMeun(List<TSysmenu> menuList) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_MENU, menuList);
		currentThread.set(threadMap);
	}
	
	
	/**
	 * 获取当前机构
	 * 
	 * @ author 许西
	 * @return
	 */
	public static TUnitInfo getUnit() {
		if (currentThread.get() != null){
			return (TUnitInfo) currentThread.get().get(S_UNIT);
		}
		else {
			return null;
		}
	}
	
	/**
	 * 设置当前机构
	 * 
	 *@ author 许西
	 * @param userBusiness
	 */
	public static void setUnit(TUnitInfo unit) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_UNIT, unit);
		currentThread.set(threadMap);
	}
	
	
	
	/**
	 * 获取机构校验状态
	 * 
	 * @ author 许西
	 * @return
	 */
	public static Boolean getUnitCheck() {
		if (currentThread.get() != null){
			return (Boolean) currentThread.get().get(S_CHECK);
		}
		else {
			return false;
		}
	}
	
	/**
	 * 设置机构校验状态
	 * 
	 *@ author 许西
	 * @param userBusiness
	 */
	public static void setUnitcheck(Boolean ischeck) {
		Map<String, Object> threadMap = currentThread.get();
		if (threadMap == null)
			threadMap = new HashMap<String, Object>();
		threadMap.put(S_CHECK, ischeck);
		currentThread.set(threadMap);
	}
}
