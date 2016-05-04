package com.core.controller.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.constant.CommonConstant;
import com.core.models.TSysmenu;
import com.toolbox.util.BeanUtil;

public class MenuUtil {

	private MenuUtil() {

	}

	/**
	 * 格式化菜单 （递归）
	 *
	 * @author 许西
	 * @param menuList
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static List<TSysmenu> getFormatMenu(String menuCode,
											   List<TSysmenu> menuList) throws Exception {
		List<TSysmenu> NowMenuList = new ArrayList<TSysmenu>();
		List<TSysmenu> menuListAll = null;

		menuListAll = BeanUtil.deepCopy(menuList);

		int i = 0;
		while (i < menuListAll.size()) {
			if (menuListAll.get(i).getUpmenu().equals(menuCode)) {
				TSysmenu menu = menuListAll.get(i);
				menu.setChildMenuMap(getFormatMenu(menu.getMenucode(),
						menuListAll));
				NowMenuList.add(menu);
			}
			i++;
		}

		return NowMenuList;
	}

	/**
	 * 匹配是否已经选中
	 *
	 * @param menuListAll
	 *            被匹配
	 * @return
	 */
	public static void IscheckedMenu(List<TSysmenu> menuListAll,
									 String menuCodeStr) {

		for (TSysmenu menu : menuListAll) {
			if (menuCodeStr.indexOf(menu.getMenucode().toString()) != -1) {
				if (menu.getChildMenuMap() != null
						&& menu.getChildMenuMap().size() > 0) {
					IscheckedMenu(menu.getChildMenuMap(), menuCodeStr);
				}else{
					menu.setChecked(true);
				}
			}
		}
	}

	/**
	 * 把菜单Code组成字符串
	 *
	 * @param menuList
	 * @return
	 */
	public static String formatMenuCodeStr(List<TSysmenu> menuList) {
		String menuCodeStr = "";
		for (TSysmenu menu : menuList) {
			if (menu != null) {
				menuCodeStr += menu.getMenucode();
				menuCodeStr += ",";
			}
		}
		return menuCodeStr;
	}

	/**
	 * 比较两个menuCode的字符串 A B 找出 A中不存在B的menuCode
	 *
	 * @author 许西
	 *            返回有比区别的menuCode数组
	 * @return
	 */
	public static List<String> compareCodeStr(String arg1, String arg2) {

		List<String> list = new ArrayList<String>();
		if(!arg1.equals("")){
			String[] arrayArg1 = arg1.split(",");
			list.addAll(Arrays.asList(arrayArg1));
		}
		if(!arg2.equals("")){
			String[] arrayArg2 = arg2.split(",");
			list.removeAll(Arrays.asList(arrayArg2));
		}
		return list;
	}

	/**
	 * 去选menucode排序，三级菜单优先删除
	 *
	 * @author 西
	 * @param menuList
	 * @param menuCode
	 * @return
	 */
	public static List<String> orderDesMenuCode(List<TSysmenu> menuList,
												List<String> menuCode) {

		for(TSysmenu sysmenu : menuList){
			int index = menuCode.indexOf(sysmenu.getMenucode());
			if(index != -1){
				if(sysmenu.getMenulevel().equals("1")){
					String code = menuCode.get(index);
					menuCode.remove(index);
					menuCode.add(code);
				}else if(sysmenu.getMenulevel().equals("3")){
					String code = menuCode.get(index);
					menuCode.remove(index);
					menuCode.add(0, code);
				}
			}
		}

		return menuCode;
	}

	/**
	 * 剔除子菜单的一级菜单
	 *
	 * @author 西
	 * @param menuList
	 * @return
	 */
	public static List<TSysmenu> delNotChildMenu(List<TSysmenu> menuList) {

		int index = 0;
		while (index < menuList.size()) {
			TSysmenu sysmenu = menuList.get(index);
			if (sysmenu.getMenulevel().equals(
					CommonConstant.MenuLevel.Level1.toString())) {// 检查菜单是否为1级菜单
				if (sysmenu.getChildren() == null
						|| sysmenu.getChildren().size() == 0) { // 检查是否拥有子菜单
					menuList.remove(index);
				} else {
					index++;// 继续下一个检查
				}
			} else {
				break;// 2级菜单不检查
			}
		}

		return menuList;
	}

	/**
	 * 补全父级菜单
	 * @param roleMenuList
	 * @param allMenuList
	 * @param menuCodeList
	 */
	public static void isRoleMenuUp(List<TSysmenu> roleMenuList, List<TSysmenu> allMenuList, List<String> menuCodeList){

		for(TSysmenu menu : allMenuList){
			if(!menu.getUpmenu().equals("0") && menuCodeList.indexOf(menu.getMenucode()) != -1 && menuCodeList.indexOf(menu.getUpmenu()) == -1){
				boolean sign = true;
				for(TSysmenu roleMenu:roleMenuList){
					if(menu.getUpmenu().equals(roleMenu.getMenucode())){
						sign = false;
						break;
					}
				}
				if(sign){
					menuCodeList.add(menu.getUpmenu());
					isRoleMenuUp(roleMenuList,allMenuList,menuCodeList);
				}
			}
		}
	}

}
