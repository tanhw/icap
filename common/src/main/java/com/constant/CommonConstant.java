/**
 * 
 */
package com.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 公用常量类 
 * @ author sys
 *
 */
public class CommonConstant {

	/**
	 * 全局字典信息
	 */
	private static Map<String, Map<String, String>> commonParamsMap = new HashMap<String, Map<String, String>>();
	
	/**
	 * 返回全局字典DESC
	 * 
	 * @param parentName// 父类型的字典编号
	 * @return
	 */
	public static String getDesc(String parentName, String childvalue) {
		if (commonParamsMap.containsKey(parentName)) {
			return commonParamsMap.get(parentName).get(childvalue);
		} else {
			return childvalue;
		}

	}
	
	/**
	 * 设置更新全局字典
	 * @param map
	 */
	public static void updateCommonParamsMap(Map<String, Map<String, String>> map){
		commonParamsMap.putAll(map);
	}
	
	
	/**
	 * 是否有效
	 * @ author sys
	 *sss
	 */
	public enum IsActive {
		True("0","有效"),
		False("1","无效")
		;
		
		String value;
		String desc;
		
		IsActive(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		
		public String getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}
		public static String getDescByValue(String value){
			if(True.toString().equals(value)){
				return  True.desc;
			}else{
				return False.desc;
			}
		}
	}

	/**
	 * 是否有效
	 * @ author sys
	 *
	 */
	public enum ConsType {
		money("00","现金交易"),
		number("01","扣费交易")
		;

		String value;
		String desc;

		ConsType(String value,String desc){
			this.value=value;
			this.desc=desc;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}

		public String getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}
		public static String getDescByValue(String value){
			if(money.toString().equals(value)){
				return  money.desc;
			}else{
				return number.desc;
			}
		}
	}
	
	
	/**
	 * 是否有效
	 * @ author sys
	 *
	 */
	public enum RegFlag {
		Login("0","登陆"),
		Normal("1","一般"),
		Intecep("2","拦截器")
		;
		
		String value;
		String desc;
		
		RegFlag(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		
		public String getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}
		public static String getDescByValue(String value){
			if(Login.toString().equals(value)){
				return  Login.desc;
			}else if(Intecep.toString().equals(value)){
				return  Intecep.desc;
			}else{
				return Normal.desc;
			}
		}
	}

	
	/**
	 * 菜单级别权限
	 * @ author sys
	 *
	 */
	public enum MenuType {
		Mers(1,"商户权限"),
		Unit(2,"普通机构权限"),
		adminUnit(3,"机构管理权限"),
		admin(4,"普通管理员权限"),
		sys(5,"系统管理员权限")
		;
		
		int value;
		String desc;
		
		MenuType(int value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		public int getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}
	}

	
	/**
	 * license状态
	 * @ author sys
	 *
	 */
	public enum LicenseStatus {
		Normal("0","正常"),
		ValidLicense("E90001","license无效"),
		ValidSign("E90002","签名验证失败，非法license"),
		ValidTime("E90003","license过期"),
		Unchecked("E90004","license验证错误"),
		UnReg("E90005","未注册"),
		FULLMER("E90006","有效商户数已满")
		;
		
		String value;
		String desc;
		
		LicenseStatus(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		
		public String getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}
		public static String getDescByValue(String value){
			if(Normal.toString().equals(value)){
				return  Normal.desc;
			}else if(ValidLicense.toString().equals(value)){
				return ValidLicense.desc;
			}else if(ValidTime.toString().equals(value)){
				return ValidTime.desc;
			}else if(Unchecked.toString().equals(value)){
				return Unchecked.desc;
			} else if(UnReg.toString().equals(value)){
				return UnReg.desc;
			}else if(FULLMER.toString().equals(value)){
				return FULLMER.desc;
			}else {
				return ValidSign.desc;
			}
		}
	}

	/**
	 * 勾兑状态
	 * @ author 许西
	 *
	 */
	public enum CheckFlag {
		
		Untreated("0","未勾兑"),
		Already("1","已勾兑");
		
		String value;
		String desc;
		
		CheckFlag(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public String getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}
		public static String getDescByValue(String value){
			if(Already.toString().equals(value)){
				return  Already.desc;
			}else{
				return Untreated.desc;
			}
		}
	}
	
	/**
	 * 处理状态
	 * @ author 许西
	 *
	 */
	public enum BatchFlag {
		
		Untreated("0","未处理"),
		Already("1","已处理");
		
		String value;
		String desc;
		
		BatchFlag(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public String getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}
		public static String getDescByValue(String value){
			if(Already.toString().equals(value)){
				return  Already.desc;
			}else{
				return Untreated.desc;
			}
		}
	}
	
	
	
	/**
	 * 操作员类别
	 * @ author sys
	 *
	 */
	public enum OperatorType {
		sys("1","系统管理员"),
		admin("2","机构管理员"),
		camp("3","园区操作员"),
		com("4","公司操作员"),
		mer("5","商户操作员")
		;
		
		String value;
		String desc;
		
		OperatorType(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		
		public String getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}
		public static String getDescByValue(String value){
			if(sys.toString().equals(value)){
				return  sys.desc;
			}else if(admin.toString().equals(value)){
				return  admin.desc;
			}else if(com.toString().equals(value)){
				return  com.desc;
			}else{
				return mer.desc;
			}
		}
	}
	
	
	
	
	
	
	/**
	 * 密码重置标志
	 * @ author sys
	 *
	 */
	public enum IsResetPassword {
		True("1","重置"),
		False("0","正常")
		;
		
		String value;
		String desc;
		
		IsResetPassword(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		
		public String getDesc(){
			return this.desc;
		}
	}
	
	/**
	 * 成功标志
	 * @ author sys
	 *
	 */
	public enum SuccessFlag {
		Success("0","成功"),
		Error("1","失败")
		;
		
		String value;
		String desc;
		
		SuccessFlag(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		
		public String getDesc(){
			return this.desc;
		}
		
		public static String getDescByValue(String value){
			
			if (Success.value.equals(value)) 
				return Success.desc;
			else 
				return Error.desc;
		}
	}
	
	/**
	 * 菜单级别
	 * @ author sys
	 *
	 */
	public enum MenuLevel{
		
		Level1("1"),Level2("2"),Level3("3"),Level4("4");
		
		String value;
		MenuLevel(String value){
			this.value=value;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
	}
	
	
	/**
	 * 菜单位置
	 * @ author sys
	 *
	 */
	public enum MenuPosition{
		
		Left("1","左边菜单"),Top("2","上边菜单"),Foot("3","下边菜单"),Button("4","功能按钮");
		
		String value;
		String desc;
		MenuPosition(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		public String getDesc(){
			return this.desc;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		public static String getDescByValue(String value){
			if(Left.toString().equals(value)){
				return  Left.desc;
			}else if(Top.toString().equals(value)){
				return Top.desc;
			}else if(Foot.toString().equals(value)){
				return Foot.desc;
			}else {
				return Button.desc;
			}
		}
	}
	
	
	
	
	
	/**
	 * 上传状态
	 * @ author sys
	 *
	 */
	public enum UploadStatus {
		
		NotUploaded("0","未上传"),Uploaded("1","已上传"),UploadedFail("2","上传失败"),Success("3","处理成功"),Fail("4","处理失败");
		
		String value;
		String desc;
		UploadStatus(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		public String getDesc(){
			return this.desc;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			
			if (NotUploaded.value.equals(value)) {
				return NotUploaded.desc;
			}else if (Uploaded.value.equals(value)) {
				return Uploaded.desc;
			}else
			if (UploadedFail.value.equals(value)) {
				return UploadedFail.desc;
			}else 
			if (Success.value.equals(value)) {
				return Success.desc;
			}
			else 
				return Fail.desc;
		}
	}
	
	
	
	
	/**
	 * 交易状态
	 * @ author sys
	 *
	 */
	public enum TxnStatus {
		
		Success("0","成功"),Cancel("1","取消"),Fail("2","失败"),Process("3","处理中");
		
		String value;
		String desc;
		TxnStatus(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		public String getDesc(){
			return this.desc;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			
			if (Success.value.equals(value)) 
				return Success.desc;
			else if(Cancel.value.equals(value)) {
				return Cancel.desc;
			}
			else if(Fail.value.equals(value)) {
				return Fail.desc;
			}
			else 
				return Process.desc;
		}
	}
	
	
	

	
	/**
	 * 属性选择
	 * @ author sys
	 *
	 */
	public enum Field {
		Text("mane"),
		Time("time")
		;
		
		String value;
		
		Field(String value){
			this.value=value;
			
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	/**
	 * 角色类别
	 * @author tanhaiwen
	 *
	 */
	public enum RoleType{
		
		SysRole("0", "系统超级角色"), NormalRole("1", "普通系统角色"), UnitAdminRole("2",
				"机构管理角色"), UnitNormalRole("3", "普通机构角色"),UnitChildNormalRole("4", "商户&园区");
		
		String value;
		String desc;
		
		private RoleType(String value,String desc) {
			
			this.value = value;
			this.desc = desc;
		}
		
		public String getDesc() {
			return desc;
		}
		public String getValue() {
			return value;
		}
		public int getValueInt() {
			return Integer.parseInt(value);
		}
		
		@Override
		public String toString() {
			
			return this.value;
		}
		
		public static String getDescByValue(String value) {
			
			if (SysRole.value.equals(value)) {
				return SysRole.desc;
			} else if (NormalRole.value.equals(value)) {
				return NormalRole.desc;
			} else if (UnitAdminRole.value.equals(value)) {
				return UnitAdminRole.desc;
			} else if (UnitNormalRole.value.equals(value)) {
				return UnitNormalRole.desc;
			} else if (UnitChildNormalRole.value.equals(value)) {
				return UnitChildNormalRole.desc;
			} else{
				return null;
			}
		}
	}
	
	/**
	 * 
	 * 商户状态
	 * @author tanhaiwen
	 *
	 */
	public enum BranchState{
		StartUser("0","启用"),
		StopUser("1","停用");
		
		String value;
		String desc;
		
		private BranchState(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getDesc() {
			return desc;
		}
		public String getValue() {
			return value;
		}
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			if(StartUser.value.equals(value)){
				return StartUser.desc;
			}else if(StopUser.value.equals(value)){
				return StopUser.desc;
			}else{
				return null;
			}
		}
	}
	
	
	/**
	 * 商品状态
	 * @author tanhaiwen
	 *
	 */
	public enum GoodStatus{
		OffSale("0","未售"),
		OnSale("1","已售");
		
		String value;
		String desc;
		
		private GoodStatus(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(OffSale.value.equals(value)){
				return OffSale.desc;
			}else if(OnSale.value.equals(value)){
				return OnSale.desc;
			}else{
				return null;
			}
		}
	}
	
	/**
	 * 是否有效
	 * @ author sys
	 *
	 */
	public enum CrdStatus {
		normal("0","可正常使用卡"),
		black("1","黑名单卡"),
		locking("2","黑名单卡已锁"),
		notusing("3","黑名单卡"),
		cancel("4","已退卡"),
		report("5","挂失"),
		change("6","已换卡"),
		;
		
		String value;
		String desc;
		
		CrdStatus(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		
		public String getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}
		public static String getDescByValue(String value){
			if(normal.toString().equals(value)){
				return  normal.desc;
			}else if(black.toString().equals(value)){
				return  black.desc;
			}else if(locking.toString().equals(value)){
				return  locking.desc;
			}else if(notusing.toString().equals(value)){
				return  notusing.desc;
			}else if(cancel.toString().equals(value)){
				return  cancel.desc;
			}else if(report.toString().equals(value)){
				return  report.desc;
			}else if(change.toString().equals(value)){
				return  change.desc;
			}else {
				return null;
			}
		}
	}
	
	
	/**
	 * 是否是测试卡
	 * @author tanhaiwen
	 *
	 */
	public enum IsTestCard{
		TRUE(0,"是"),
		FALSE(1,"否");
		
		int value;
		String desc;
		
		private IsTestCard(int value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public int getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
		
		@Override
		public String toString() {
			return String.valueOf(value);
		}
		
		public static String getDescByValue(int value){
			
			for(IsTestCard testCard : IsTestCard.values()){
				if(testCard.value == value){
					return testCard.desc;
				}
			}
			return null;
		}
	}

	
	
	
	/**
	 * 卡物理类型
	 * @author tanhaiwen
	 *
	 */
	public enum PhscType{
		M1Card("1","M1卡"),PbocCard("3","银行pboc卡");
		
		String value;
		String desc;
		
		private PhscType(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(M1Card.value.equals(value)){
				return M1Card.desc;
			}else if(PbocCard.value.equals(value)){
				return PbocCard.desc;
			}else{
				return null;
			}
		}
	}
	/**
	 * 卡片状态(标志)
	 * 
	 * @author tanhw
	 *
	 */
	public enum CardFlag{
		NotUserCard("0","未发卡"),
		UserCard("1","已发卡"),
		ChangeCard("2","已换卡");

		String value;
		String desc;
		
		private CardFlag(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(NotUserCard.value.equals(value)){
				return NotUserCard.desc;
			}else if(UserCard.value.equals(value)){
				return UserCard.desc;
			}else if(ChangeCard.value.equals(value)){
				return ChangeCard.desc;
			}else{
				return null;
			}
		}
	}
	
	
	/**
	 * 公共启用标志
	 * 
	 * @ author 许西
	 *
	 */
	public enum CommState{
		
		TRUE("0","启用"),
		FALSE("1","停用");
		
		String value;
		String desc;
		
		private CommState(String value,String desc) {
			
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(TRUE.value.equals(value)){
				return TRUE.desc;
			}else if(FALSE.value.equals(value)){
				return FALSE.desc;
			}else {
				return null;
			}
		}
	}
	/**
	 * 票价类型
	 * 
	 * @ author 许西
	 *
	 */
	public enum FeeType{
		
		ONE("0","单票制 "),
		MANY("1","多票制");
		
		String value;
		String desc;
		
		private FeeType(String value,String desc) {
			
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(ONE.value.equals(value)){
				return ONE.desc;
			}else if(MANY.value.equals(value)){
				return MANY.desc;
			}else {
				return null;
			}
		}
	}
	
//	/**
//	 * 票价类型
//	 * 
//	 * @ author 许西
//	 *
//	 */
//	public enum CarType{
//		
//		BIGCAR("10","大型车 "),
//		MIDDLECAR("20","中型车 "),
//		SMALLCAR("30","小型车 ");
//		
//		String value;
//		String desc;
//		
//		private CarType(String value,String desc) {
//			
//			this.value = value;
//			this.desc = desc;
//		}
//		public String getValue() {
//			return value;
//		}
//		public String getDesc() {
//			return desc;
//		}
//		@Override
//		public String toString() {
//			// TODO Auto-generated method stub
//			return this.value;
//		}
//		public static String getDescByValue(String value){
//			
//			if(BIGCAR.value.equals(value)){
//				return BIGCAR.desc;
//			}else if(MIDDLECAR.value.equals(value)){
//				return MIDDLECAR.desc;
//			}else if(SMALLCAR.value.equals(value)){
//				return SMALLCAR.desc;
//			}else {
//				return null;
//			}
//		}
//	}
//	
	
	/**
	 * 上下行区分
	 * 
	 * @ author 许西
	 *
	 */
	public enum DirectInfo{
		
		ONTDISTSH("0","不区分上下行"),
		DISTSH("1","区分上下行 ");
		
		String value;
		String desc;
		
		private DirectInfo(String value,String desc) {
			
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(ONTDISTSH.value.equals(value)){
				return ONTDISTSH.desc;
			}else if(DISTSH.value.equals(value)){
				return DISTSH.desc;
			}else {
				return null;
			}
		}
	}
	
	
	/**
	 * 主密钥下载标志
	 * 
	 * @author tanhw
	 *
	 */
	public enum TmkDownFlag{
		
		TRUE("0","已下载"),
		FALSE("1","未下载");
		
		String value;
		String desc;
		
		private TmkDownFlag(String value,String desc) {
			
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(TRUE.value.equals(value)){
				return TRUE.desc;
			}else if(FALSE.value.equals(value)){
				return FALSE.desc;
			}else {
				return null;
			}
		}
	}
	
	/**
	 * Keya密钥下载标志
	 * 
	 * @author tanhw
	 *
	 */
	public enum KeyaDownFlag{
		
		TRUE("0","已下载"),
		FALSE("1","未下载");
		 
		String value;
		String desc;
		
		private KeyaDownFlag(String value,String desc) {
			
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(TRUE.value.equals(value)){
				return TRUE.desc;
			}else if(FALSE.value.equals(value)){
				return FALSE.desc;
			}else {
				return null;
			}
		}
	}
	
	
	/**
	 * 线路票价启用状态
	 * @author tanhw
	 *
	 */
	public enum IsUsed {
		FALSE("0","未启用"),
		TRUE("1","启用");
		
		String value;
		String desc;
		
		private IsUsed(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			
			return this.value;
		}
		public static String getDescByValue(String value){
			if(FALSE.value.equals(value)){
				return FALSE.desc;
			}else if(TRUE.value.equals(value)){
				return TRUE.desc;
			}else {
				return null;
			}
		}
	}
	
	
	/**
	 *
	 * 记录状态 
	 * @author tanhw
	 *
	 */
	public enum RecordStat{
		NotRecord("0","未记录"),
		Record("1","已记录");
		
		String value;
		String desc;
		
		private RecordStat(String value,String desc) {
			
			this.value = value;
			this.desc = desc;
		}
		
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(NotRecord.value.equals(value)){
				return NotRecord.desc;
			}else if(Record.value.equals(value)){
				return Record.desc;
			}else {
				return null;
			}
		}
	}
	
	/**
	 * 状态
	 * @author tanhw
	 *
	 */
	public enum UserDataStatus{
		Normal("0","正常"),
		Failure("1","失效");
		
		String value;
		String desc;
		
		private UserDataStatus(String value,String desc) {
			
			this.value = value;
			this.desc = desc;
		}
		
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			if(Normal.value.equals(value)){
				return Normal.desc;
			}else if(Failure.value.equals(value)){
				return Failure.desc;
			}else {
				return  null;
			}
		}
	}
	
	public enum Mark{
		True("0","有效"),
		False("1","无效");
		
		String value;
		String desc;
		
		private Mark(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			if(True.value.equals(value)){
				return True.desc;
			}else if(False.value.equals(value)){
				return False.desc;
			}else {
				return null;
			}
		}
	}
	
	/**
	 * 扣费类型
	 * 
	 * @author tanhw
	 *
	 */
	public enum DeductionType {
		BalDeduc("0", "余额扣费"), TimesDeduc("1", "余次扣费");

		String value;
		String desc;

		private DeductionType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}

		public static String getDescByValue(String value) {

			if (BalDeduc.value.equals(value)) {
				return BalDeduc.desc;
			} else if (TimesDeduc.value.equals(value)) {
				return TimesDeduc.desc;
			} else {
				return null;
			}
		}
	}
	
	/**
	 * 清次周期类型
	 * 
	 * @author tanhw
	 *
	 */
	public enum PeriodType {
		MonthPeriod("0", "月周期清次"), YearPeriod("1", "年周期清次");

		String value;
		String desc;

		private PeriodType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}

		public static String getDescByValue(String value) {

			if (MonthPeriod.value.equals(value)) {
				return MonthPeriod.desc;
			} else if (YearPeriod.value.equals(value)) {
				return YearPeriod.desc;
			} else {
				return null;
			}
		}
	}
	
	
	// ***************************************************************
	
	
	/**
	 * 返回描述
	 * 
	 * @author THW
	 * 
	 */
	public enum RespsDesc {

		Success("00", "成功"), Fail("99", "失败"), ERROR_51("51", "未找到此卡库存信息"), ERROR_52(
				"52", "未找到此卡记名信息"), ERROR_54("54", "已做退收费交易"), ERROR_61("61",
				"密钥待加密数据错误"), ERROR_62("62", "KEY_B密钥获取错误"), ERROR_63("63",
				"调用加密机错误"), ERROR_81("81", "超过最大余额数"), ERROR_71("71",
				"ESB链接超时"),ERROR_72("72","ESB无返回"), ERROR_53("53", "充值流水表中未找到此卡信息"), ERROR_55(
				"55", "没有流水相关信息"),ERROR_56("56", "客户号不匹配"),ERROR_57(
				"57", "此卡非正常状态无法进行此交易"),ERROR_82("82","撤销金额不匹配"),ERROR_58("58", "此卡非正常状态无法充值"),
				ERROR_59("59","与原挂失机构不符，原挂失机构为:");

		
		String value;
		String desc;

		private RespsDesc(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}

		public static String getDescByValue(String value) {
			if (Success.value.equals(value)) {
				return Success.desc;
			} else if (ERROR_51.value.equals(value)) {
				return ERROR_51.desc;
			} else if (ERROR_52.value.equals(value)) {
				return ERROR_52.desc;
			} else if (ERROR_53.value.equals(value)) {
				return ERROR_53.desc;
			} else if (ERROR_54.value.equals(value)) {
				return ERROR_54.desc;
			} else if (ERROR_55.value.equals(value)) {
				return ERROR_55.desc;
			} else if (ERROR_56.value.equals(value)) {
				return ERROR_56.desc;
			} else if (ERROR_57.value.equals(value)) {
				return ERROR_57.desc;
			}else if  (ERROR_58.value.equals(value)) {
				return ERROR_58.desc;
			}else if  (ERROR_59.value.equals(value)) {
				return ERROR_59.desc;
			} else if (ERROR_61.value.equals(value)) {
				return ERROR_61.desc;
			} else if (ERROR_62.value.equals(value)) {
				return ERROR_62.desc;
			}else if  (ERROR_63.value.equals(value)) {
				return ERROR_63.desc;
			}else if  (ERROR_71.value.equals(value)) {
				return ERROR_71.desc;
			}else if (ERROR_72.value.equals(value)) {
				return ERROR_72.desc;
			}else if (ERROR_81.value.equals(value)) {
				return ERROR_81.desc;
			}else if (ERROR_82.value.equals(value)) {
				return ERROR_82.desc;
			}else {
				return Fail.desc;
			}

		}

		public static String getValue(String value) {
			if (Success.value.equals(value)) {
				return Success.desc;
			} else if (ERROR_51.value.equals(value)) {
				return ERROR_51.value;
			} else if (ERROR_52.value.equals(value)) {
				return ERROR_52.value;
			} else if (ERROR_53.value.equals(value)) {
				return ERROR_53.value;
			} else if (ERROR_54.value.equals(value)) {
				return ERROR_54.value;
			} else if (ERROR_55.value.equals(value)) {
				return ERROR_55.value;
			} else if (ERROR_56.value.equals(value)) {
				return ERROR_56.value;
			} else if (ERROR_57.value.equals(value)) {
				return ERROR_57.value;
			}else if  (ERROR_58.value.equals(value)) {
				return ERROR_58.value;
			}else if  (ERROR_59.value.equals(value)) {
				return ERROR_59.value;
			} else if (ERROR_61.value.equals(value)) {
				return ERROR_61.value;
			} else if (ERROR_62.value.equals(value)) {
				return ERROR_62.value;
			}else if  (ERROR_63.value.equals(value)) {
				return ERROR_63.value;
			}else if  (ERROR_71.value.equals(value)) {
				return ERROR_71.value;
			}else if (ERROR_72.value.equals(value)) {
				return ERROR_72.value;
			}else if (ERROR_81.value.equals(value)) {
				return ERROR_81.value;
			}else if (ERROR_82.value.equals(value)) {
				return ERROR_82.value;
			}else {
				return Fail.value;
			}
		}
	}

	
	/**
	 * 报文类型
	 * 
	 * @ author sys
	 * 
	 */
	public enum MsgType {
		signIn("1001", "签到"),
		paraDownload("1002", "参数下载公交"),
		dataUpload("1003", "脱机交易上传"),
		busparaDownload("2001", "公交参数下载"),
		issueCard("2003", "M1发卡"),
		busCharge("2002", "公交卡充次"),
		busChargeNotice("2005", "公交卡充次通知"),
		busKmsDownload("2004", "公交卡密钥下载"),
		pbocIssueCard("2006", "PBOC发卡"),
		campsignIn("3001", "园区签到"),
		paraDownloadcamp("3002", "参数下载园区"),
		campdataUpload("3003", "园区脱机交易上传"),
		campparaDownload("4001", "园区参数下载"),
		campissueCard("4003", "园区M1发卡"),
		campCharge("4007", "园区m1充值"),
		campChargeNotice("3008", "园区卡充次通知"),
		campKmsDownload("4002", "园区卡密钥下载"),
		camppbocIssueCard("4004", "园区PBOC发卡"),
		camppStorage("4006", "园区圈存"),
		campPbocCharge("4005","园区PBOC充值"),
		pcDataUpload("6001","PC交易数据上传");
		
		String value;
		String desc;

		MsgType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return this.value;
		}

		public static String getDescByValue(String value) {

			if (paraDownload.value.equals(value)) {
				return paraDownload.desc;
			} else if (signIn.value.equals(value)) {
				return signIn.desc;
			} else if (issueCard.value.equals(value)) {
				return issueCard.desc;
			} else if (dataUpload.value.equals(value)) {
				return dataUpload.desc;
			} else if (busparaDownload.value.equals(value)) {
				return busparaDownload.desc;
			} else if (busCharge.value.equals(value)) {
				return busCharge.desc;
			} else if (busChargeNotice.value.equals(value)) {
				return busChargeNotice.desc;
			} else if (pbocIssueCard.value.equals(value)) {
				return pbocIssueCard.desc;
			} else if (campsignIn.value.equals(value)) {
				return campsignIn.desc;
			} else if (campissueCard.value.equals(value)) {
				return campissueCard.desc;
			} else if (campdataUpload.value.equals(value)) {
				return campdataUpload.desc;
			} else if (campparaDownload.value.equals(value)) {
				return campparaDownload.desc;
			} else if (campCharge.value.equals(value)) {
				return campCharge.desc;
			} else if (campChargeNotice.value.equals(value)) {
				return campChargeNotice.desc;
			} else if (camppbocIssueCard.value.equals(value)) {
				return camppbocIssueCard.desc;
			} else if (paraDownloadcamp.value.equals(value)) {
				return paraDownloadcamp.desc;
			} else if (camppStorage.value.equals(value)){
				return camppStorage.desc;
			} else if (campPbocCharge.value.equals(value)) {
				return campPbocCharge.desc;
			}else if (pcDataUpload.value.equals(value)) {
				return pcDataUpload.desc;
			}else {
				return null;
			}
		}
	}
	
	/**
	 * 报文类型
	 * 
	 * @ author sys
	 * 
	 */
	public enum paraType {
		mainKey("00", "主密钥"),
		blackUser("01", "黑名单"),
		cardBin("02", "卡bin"),
		tms("03","终端程序(TMS)"),
		whiteList("11","白名单管理");
		String value;
		String desc;
		
		paraType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getDesc() {
			return desc;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value) {
			
			if (mainKey.value.equals(value)) {
				return mainKey.desc;
			}else if (blackUser.value.equals(value)){
				return blackUser.desc;
		    }else if (cardBin.value.equals(value)){
				return cardBin.desc;
			}else if (whiteList.value.equals(value)){
				return whiteList.desc;
			}else{
				return null;
			}
		}
	}
	
	/**
	 * 是否有效
	 * 
	 * @ author sys
	 * 
	 */
	public enum HanldStatus {
		Hanlding("0", "处理中"), Hanlded("1", "完毕");

		String value;
		String desc;

		HanldStatus(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}

		public String getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static String getDescByValue(String value) {
			if (Hanlding.toString().equals(value)) {
				return Hanlding.desc;
			} else {
				return Hanlded.desc;
			}
		}
	}
	
	
	/**
	 * KMS错误码
	 */
	public enum KMSError{
		isOK("0000", "调用成功"), 
        headerLengthError("1001", "请求报文头长度错误"),
        businessTypeError("1002", "请求的交易类型错"),
        keyIdError("1003", "请求的keyId错"),
        encryptionError("1004", "加密机ID错"),
        timeOut("1005", "指令调用超时"),
        portNotEnabled("1006", "接口未配置或未启用"),
        executeError("1007", "执行命令出错"),
        powerNotEnough("9001", "非法的权限请求"),
        systemError("9999", "系统错误")
        ;
		
		String value;
		String desc;
		
		KMSError(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		@Override
		public String toString() {
			return this.value;
		}

		public String getValue() {
			return this.value;
		}
		
		public static String getDescByValue(String value) {
			if (isOK.toString().equals(value)) {
				return isOK.desc;
			}else if (headerLengthError.toString().equals(value)){
				return headerLengthError.desc;
			}else if (businessTypeError.toString().equals(value)){
				return businessTypeError.desc;
			}else if (keyIdError.toString().equals(value)){
				return keyIdError.desc;
			}else if (encryptionError.toString().equals(value)){
				return encryptionError.desc;
			}else if (timeOut.toString().equals(value)){
				return timeOut.desc;
			}else if (portNotEnabled.toString().equals(value)){
				return portNotEnabled.desc;
			}else if (executeError.toString().equals(value)){
				return executeError.desc;
			}else if (powerNotEnough.toString().equals(value)){
				return powerNotEnough.desc;
			}else if (systemError.toString().equals(value)){
				return systemError.desc;
			}else {
				return null;
			}
		}
	
	} 
	/**
	 * 加密机错误码
	 */
	public enum EncryptionError{
		unknownError("99", "未知加密机处理错误"),
		isOK("00", "无错误"), 
		oddEvenError("10", "第一个成份奇偶校验错"),
        notHaveKeyError("12", "用户存储区没有装载密钥"),
        LMKError("13", "LMK错误,报告给管理员"),     
        dataError("15", "输入数据错"),
        indexError("21", "无效的用户存储索引"),
        ;
		
		String value;
		String desc;
		
		EncryptionError(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		@Override
		public String toString() {
			return this.value;
		}

		public String getValue() {
			return this.value;
		}
		
		public static String getDescByValue(String value) {
			if (isOK.toString().equals(value)) {
				return isOK.desc;
			}else if (unknownError.toString().equals(value)){
				return unknownError.desc;
			}else if (oddEvenError.toString().equals(value)){
				return oddEvenError.desc;
			}else if (notHaveKeyError.toString().equals(value)){
				return notHaveKeyError.desc;
			}else if (LMKError.toString().equals(value)){
				return LMKError.desc;
			}else if (dataError.toString().equals(value)){
				return dataError.desc;
			}else if (indexError.toString().equals(value)){
				return indexError.desc;
			}else {
				return null;
			}
		}
	}
	
	/**
	 * 报文响应
	 * 
	 * @author tanhw
	 *
	 */
	public enum MsgResp{

		Success("00","成功"),
		TxnTypeErr("01","交易类型错误"),
		TxnMsgErr("02","交易报文错误"),
		MsgMacErr("03","报文MAC错误"),
		Batching("06","任务跑批中,请稍后上传"),
		SysCuting("07","系统日切中"),
		TimeOut("08","超时"),
		SysErr("09","系统错误"),
		MerNotFound("10","商户不存在"),
		MerStatNotNormal("11","商户状态不正常"),
		PosNotFound("20","终端不存在"),
		PosStatNotNormal("21","终端状态不正常"),
		PosBelongsMerNotAgree("22","终端所属商户不符"),
		PosNotDownMainkey("23","终端不允许下载主密钥"),
		PosKeyInfoNotExist("24","终端密钥信息不存在，需先下载主密钥"),
		PosNotTmsDown("25","终端无程序下载"),
		CardNotFound("40","卡不存在"),
		NotUseCard("41","未发卡"),
		UseCard("42","已发卡"),
		AuthTimesNotEnough("43","授权余次不足"),
		LineCodeNotFound("50","线路号不存在"),
		PriceNotFound("51","票价不存在"),
		StationNotFound("52","站点不存在");
		
		String value;
		String desc;	
		
		private MsgResp(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getDesc() {
			return desc;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			
			if(Success.value.equals(value)){
				return Success.desc;
			}else if(TxnTypeErr.value.equals(value)){
				return TxnTypeErr.desc;
			}else if(TxnMsgErr.value.equals(value)){
				return TxnMsgErr.desc;
			}else if(Batching.value.equals(value)){
				return Batching.desc;
			}else if(MsgMacErr.value.equals(value)){
				return MsgMacErr.desc;
			}else if(SysCuting.value.equals(value)){
				return SysCuting.desc;
			}else if(TimeOut.value.equals(value)){
				return TimeOut.desc;
			}else if(SysErr.value.equals(value)){
				return SysErr.desc;
			}else if(MerNotFound.value.equals(value)){
				return MerNotFound.desc;
			}else if(MerStatNotNormal.value.equals(value)){
				return MerStatNotNormal.desc;
			}else if(PosNotFound.value.equals(value)){
				return PosNotFound.desc;
			}else if(PosStatNotNormal.value.equals(value)){
				return PosStatNotNormal.desc;
			}else if(PosBelongsMerNotAgree.value.equals(value)){
				return PosBelongsMerNotAgree.desc;
			}else if(PosNotDownMainkey.value.equals(value)){
				return PosNotDownMainkey.desc;
			}else if(PosKeyInfoNotExist.value.equals(value)){
				return PosKeyInfoNotExist.desc;
			}else if(PosNotTmsDown.value.equals(value)){
				return PosNotTmsDown.desc;
			}else if(CardNotFound.value.equals(value)){
				return CardNotFound.desc;
			}else if(NotUseCard.value.equals(value)){
				return NotUseCard.desc;
			}else if(UseCard.value.equals(value)){
				return UseCard.desc;
			}else if(AuthTimesNotEnough.value.equals(value)){
				return AuthTimesNotEnough.desc;
			}else if(LineCodeNotFound.value.equals(value)){
				return LineCodeNotFound.desc;
			}else if(PriceNotFound.value.equals(value)){
				return PriceNotFound.desc;
			}else if(StationNotFound.value.equals(value)){
				return StationNotFound.desc;
			}else {
				return null;
			}
		}
	}
	
	
	/**
	 * 发kms交易类型
	 * @ author sys
	 *
	 */
	public enum KmsType {
		
		PosMain("1000","POS主密钥"),PosWork("1001","POS工作密钥"),PosMac("1002","PosMac校验"),
		M1KeyA("1003","M1KeyA下载"),M1KeyB("1004","M1KeyB下载")
		,M1Tac("1005","M1Tac校验"),M1TacKey("1006","M1Tac密钥下载"),TreeDes("1007","3Desc加密");
		
		String value;
		String desc;
		KmsType(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		public String getDesc(){
			return this.desc;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			
			if (PosMain.value.equals(value)) 
				return PosMain.desc;
			else if(PosWork.value.equals(value)) {
				return PosWork.desc;
			}
			else if(PosMac.value.equals(value)) {
				return PosMac.desc;
			}
			else if(M1KeyA.value.equals(value)) {
				return M1KeyA.desc;
			}
			else if(M1KeyB.value.equals(value)) {
				return M1KeyB.desc;
			} else if(M1TacKey.value.equals(value)) {
				return M1TacKey.desc;
			} else if(TreeDes.value.equals(value)) {
				return TreeDes.desc;
			} else {
				return M1Tac.desc;
			}
		}
	}
	/**
	 * 后续报文标识
	 * 
	 * @ author 
	 * 
	 */
	public enum Follow {
		False("0", "无"), True("1", "有");

		String value;
		String desc;

		Follow(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}

		public String getValue() {
			return this.value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static String getDescByValue(String value) {
			if (False.toString().equals(value)) {
				return False.desc;
			} else {
				return True.desc;
			}
		}
	}
	/**
	 * 公交参数下载 -参数类型
	 * 
	 * @ author rain
	 *
	 */
	public enum BusParaDown{
		
		basePrice("01","线路基础票价信息"),
		manyPrice("02","线路多级票价信息"),
		proportion("03","充值金额次数比例信息");
		
		String value;
		String desc;
		
		private BusParaDown(String value,String desc) {
			
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(basePrice.value.equals(value)){
				return basePrice.desc;
			}else if(manyPrice.value.equals(value)){
				return manyPrice.desc;
			}else if(proportion.value.equals(value)){
				return proportion.desc;
			}else {
				return null;
			}
		}
	}
	
	/**
	 * 园区参数下载  - 参数类型
	 * @author rain
	 *
	 */
	public enum CampParaDown{

		privilege("01","园区优惠策略");
		
		String value;
		String desc;
		
		private CampParaDown(String value,String desc) {
			
			this.value = value;
			this.desc = desc;
		}
		public String getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}
		public static String getDescByValue(String value){
			
			if(privilege.value.equals(value)){
				return privilege.desc;
			}else {
				return null;
			}
		}		
	}
	
	/**
	 * 交易类型
	 * 
	 * @author tanhaiwen
	 *
	 */
	public enum TxnType{
		
		ChargeTimeQuery("01", "交易查询"), ChargeTimeConfirm("02", "交易确认");

		String value;
		String desc;
		
		private TxnType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getDesc() {
			return desc;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value) {
			if (ChargeTimeQuery.toString().equals(value)) {
				return ChargeTimeQuery.desc;
			} else {
				return ChargeTimeConfirm.desc;
			}
		}
	}
	
	/**
	 * 策略类别
	 * @author tanhw
	 *
	 */
	public enum StrategyType {
		ComsumAmt("0","扣款金额"),
		DiscountAmt("1","优惠金额"),
		StrategyPerentage("2","优惠百分比"),
		StrategyTimes("3","抵扣次数")
		;
		
		String value;
		String desc;
		
		StrategyType(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		@Override
		public String toString() {
			return this.value;
		}
		public String getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}

		public static String getDescByValue(String value) {
			if (ComsumAmt.toString().equals(value)) {
				return ComsumAmt.desc;
			} else if (DiscountAmt.toString().equals(value)) {
				return DiscountAmt.desc;
			} else if (StrategyPerentage.toString().equals(value)) {
				return StrategyPerentage.desc;
			} else {
				return StrategyTimes.desc;
			}
		}
	}

			

	

	/**
	 * 性别
	 * 
	 * @author 
	 *
	 */
	public enum Sex{
		
		man("1", "男"), woman("2", "女");

		String value;
		String desc;
		
		private Sex(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getDesc() {
			return desc;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value) {
			if (man.toString().equals(value)) {
				return man.desc;
			} else {
				return woman.desc;
			}
		}
	}
		
	
	public enum CampTxnType{
		QD("0","签到"),
		JS("1","批结算"),
		XN("2","虚拟账户消费"),
		DZ("3","电子钱包消费"),
		QC("4","账户圈存");
		
		String value;
		String desc;
		
		private CampTxnType(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getDesc() {
			return desc;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){

			if (QD.value.equals(value)) {
				return QD.desc;
			} else if (JS.value.equals(value)) {
				return JS.desc;
			} else if (XN.value.equals(value)) {
				return XN.desc;
			} else if (DZ.value.equals(value)) {
				return DZ.desc;
			} else if (QC.value.equals(value)) {
				return QC.desc;
			} else {
				return null;
			}
		}
	}
	
	public enum AuditFlag{
		
		Audit("0","已审核"),
		NotAudit("1","未审核"),
		AuditReject("2","审核驳回");
		
		String value;
		String desc;
		
		private AuditFlag(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getDesc() {
			return desc;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			if(Audit.value.equals(value)){
				return Audit.desc;
			}else if(NotAudit.value.equals(value)){
				return NotAudit.desc;
			}else if(AuditReject.value.equals(value)){
				return AuditReject.desc;
			}else {
				return null;
			}
		}
	}
	/**
	 * 对账状态
	 * @author tanhw
	 *
	 */
	public enum SettleStatus {
		
		Settled("0","已对账"),NotSettled("1","未对账");
		
		String value;
		String desc;
		SettleStatus(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		public String getDesc(){
			return this.desc;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			
			if (Settled.value.equals(value)) {
				return Settled.desc;
			}else 
				return NotSettled.desc;
		}
	}
	
	/**
	 * 交易来源状态
	 * @author tanhw
	 *
	 */
	public enum TxnSource {
		
		Normal("0","线上"),Manual("1","人工");
		
		String value;
		String desc;
		TxnSource(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		public String getDesc(){
			return this.desc;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			
			if (Normal.value.equals(value)) {
				return Normal.desc;
			}else 
				return Manual.desc;
		}
	}
	/**
	 * 交易状态
	 * @author tanhw
	 *
	 */
	public enum RespDesc {
		
		Success("00","成功"),ValidPos("97","非法终端"),ValidPosStatus("80","终端状态异常"),
		ValidBatchId("Z1","批次号非法"),CheckMacFial("84","mac或mac校验处理失败"),FreezeAccount("41","账户冻结"),
		Touzhi("51","余额不足或账户透支"),SysFail("96","系统异常，请检查后台相关数据配置"),NoAccount("39","账户不存在"),
		RepetTxn("94","重复交易"),ValidQC("57","非法圈存"),NoChargeRule("25","未绑定圈存规则"),
		AmtError("77","结算金额不一致");
		
		String value;
		String desc;
		RespDesc(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		
		public String getDesc(){
			return this.desc;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){
			
			if (Success.value.equals(value)) 
				return Success.desc;
			else if(ValidPos.value.equals(value)) {
				return ValidPos.desc;
			}
			else if(ValidPosStatus.value.equals(value)) {
				return ValidPosStatus.desc;
			}
			else if(ValidBatchId.value.equals(value)) {
				return ValidBatchId.desc;
			}
			else if(CheckMacFial.value.equals(value)) {
				return CheckMacFial.desc;
			}
			else if(FreezeAccount.value.equals(value)) {
				return FreezeAccount.desc;
			}
			else if(Touzhi.value.equals(value)) {
				return Touzhi.desc;
			}
			else if(SysFail.value.equals(value)) {
				return SysFail.desc;
			}
			else if(NoAccount.value.equals(value)) {
				return NoAccount.desc;
			}
			else if(RepetTxn.value.equals(value)) {
				return RepetTxn.desc;
			}
			else if(ValidQC.value.equals(value)) {
				return ValidQC.desc;
			}
			else if(NoChargeRule.value.equals(value)) {
				return NoChargeRule.desc;
			}
			else {
				return AmtError.desc;
			}
			
		}
	}
	
	/**
	 * 打款规则
	 * @author tanhw
	 *
	 */
	public enum AutoStatus{
		True("0", "是"), False("1", "否");

		String value;
		String desc;

		private AutoStatus(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value) {
			if (True.value.equals(value)) {
				return True.desc;
			} else if (False.value.equals(value)) {
				return False.desc;
			} else {
				return null;
			}
		}
	}
	/**
	 * 任务类型
	 * @author tanhw
	 *
	 */
	public enum TaskType{
		CreateFile("01", "生成对账文件"), BlendResultFile("02", "勾兑结果文件"), StatisReport("03", "统计报表");

		String value;
		String desc;

		private TaskType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return this.value;
		}

		public static String getDescByValue(String value) {
			if (CreateFile.value.equals(value)) {
				return CreateFile.desc;
			} else if (BlendResultFile.value.equals(value)) {
				return BlendResultFile.desc;
			} else if (StatisReport.value.equals(value)) {
				return StatisReport.desc;
			} else {
				return null;
			}
		}
	}

	/**
	 *  Pboc 发卡 子交易
	 *
	 * @ author rain
	 *
	 */

	public enum  PbocTxnType{
		IssueCard("01","发卡"),
		Renovate("02","更新"),
		Affirm("03","更新确认")
		;

		String value;
		String desc;

		PbocTxnType(String value,String desc){
			this.value=value;
			this.desc=desc;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.value;
		}

		public String getValue(){
			return this.value;
		}
		public String getDesc(){
			return this.desc;
		}
		public static String getDescByValue(String value){
			if(IssueCard.toString().equals(value)){
				return  IssueCard.desc;
			}else if(Renovate.toString().equals(value)){
				return  Renovate.desc;
			}else{
				return Affirm.desc;
			}
		}
	}

	/**
	 * 账户类型
	 */
	public enum AccountType{

		DZ("00", "电子现金账户"),
		XN("01", "虚拟账户消费"),
		OTHER("02", "依据后台规则");

		String value;
		String desc;

		private AccountType(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return this.value;
		}

		public static String getDescByValue(String value) {
			if (DZ.value.equals(value)) {
				return DZ.desc;
			} else if (XN.value.equals(value)) {
				return XN.desc;
			} else if (OTHER.value.equals(value)) {
				return OTHER.desc;
			} else {
				return null;
			}
		}
	}
	
	/**
	 * 跑批执行状态
	 * 
	 * @author tanhaiwen
	 *
	 */
	public enum BatchStatus {
		BatchSucc("0", "跑批成功"), NotBatch("1", "未跑批"), Batching("2", "跑批中"), BatchFail(
				"9", "跑批失败");

		String value;
		String desc;

		private BatchStatus(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}

		public String getValue() {
			return value;
		}

		public String getDesc() {
			return desc;
		}

		@Override
		public String toString() {
			return this.value;
		}

		public static String getDescByValue(String value) {
			if (BatchSucc.value.equals(value)) {
				return BatchSucc.desc;
			} else if (NotBatch.value.equals(value)) {
				return NotBatch.desc;
			} else if (Batching.value.equals(value)) {
				return Batching.desc;
			} else if (BatchFail.value.equals(value)) {
				return BatchFail.desc;
			} else {
				return null;
			}
		}
	}
	
	/**
	 * 前置系统
	 * 报文加密标志
	 * @author RAIN
	 *
	 */
	public enum MsgEnc{
		UnSecret("0","不加密"),
		Secret("1","加密"),
		MAC("2","不加密MAC"),
		SecretAndMAC("3","加密后MAC");
		
		String value;
		String desc;
		
		private MsgEnc(String value,String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getDesc() {
			return desc;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value){

			if (UnSecret.value.equals(value)) {
				return UnSecret.desc;
			} else if (Secret.value.equals(value)) {
				return Secret.desc;
			} else if (MAC.value.equals(value)) {
				return MAC.desc;
			} else if (SecretAndMAC.value.equals(value)) {
				return SecretAndMAC.desc;
			} else {
				return null;
			}
		}
	}
	
	/**
	 * 版本号
	 * @author rain
	 *
	 */
	public enum MsgVersion{
		POS("F01", "POS端发起交易"), PC("F11", "PC端上传交易");

		String value;
		String desc;

		private MsgVersion(String value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}
		@Override
		public String toString() {
			return this.value;
		}
		
		public static String getDescByValue(String value) {
			if (POS.value.equals(value)) {
				return POS.desc;
			} else if (PC.value.equals(value)) {
				return PC.desc;
			} else {
				return null;
			}
		}
	}
	
}