<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";
</script>
<div class="easyui-layout" fit="true">


	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="menu/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*菜单编号：</td>
					<td>
						<c:if test="${not empty menu.menucode }">
								<input type="hidden" name="menucode" value='${menu.menucode }'/>
						</c:if>
						<input type="text" id="menucode" name="menucode" maxlength="8" required="true"  class="easyui-validatebox textbox input155" 
							<c:if test="${not empty menu.menucode }">
								disabled="disabled" value='${menu.menucode }'
							</c:if>
						/>
					</td>
					<td style="text-align: right;">*菜单名称：</td>
					<td>
						<input type="text" id="menuname" name="menuname" maxlength="10" required="true" class="easyui-validatebox textbox input155" value="${menu.menuname }"/>
					</td>
				</tr>
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">*菜单描述：</td>
					<td>
						<input type="text" id="menuDesc" required="true" name="menudesc"
							maxlength="20" class="easyui-validatebox textbox input155" value="${menu.menudesc }"/>
					</td>
					<td style="text-align: right;">菜单URL：</td>
					<td>
						<input type="text" id="url" name="url"
							maxlength="50" class="easyui-validatebox textbox input155"  value='${menu.url}' />
					</td>
				</tr>
				
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">*菜单级别：</td>
					<td>
						<select id="menulevel" name="menulevel" class="easyui-validatebox textbox input155" required="true"  onchange="_showUpMenu();">
							<option value="1" <c:if test="${menu.menulevel == '1' }"> selected='selected'</c:if>>一级菜单</option>
							<option value="2" <c:if test="${menu.menulevel == '2' }"> selected='selected'</c:if>>二级菜单</option>
							<option value="3" <c:if test="${menu.menulevel == '3' }"> selected='selected'</c:if>>三级菜单</option>
						</select>
					</td>
				
					<td style="text-align: right;">上级菜单：</td>
					<td id="upMenuTd">
						<c:if test="${empty upMenuList }">
							<span style="color:green">请选择菜单级别后选择</span>
						</c:if>
						<c:if test="${not empty upMenuList }">
							<select id="upmenu" name="upmenu" class="easyui-validatebox textbox input155">
								<c:forEach items="${upMenuList }" var = "_menu">
									<option value="${_menu.menucode }" <c:if test="${_menu.menucode == menu.upmenu }"> selected='selected'</c:if>>${_menu.menuname }</option>
								</c:forEach>
							</select>
						</c:if>
					</td>
				</tr>
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">按钮图标：</td>
					<td>
						<select id="icon" name="icon" class="easyui-validatebox textbox input155" >
							<option value="">请选择</option>
							<option value="icon-search" <c:if test="${menu.icon == 'icon-search' }"> selected='selected'</c:if>>查询</option>
							<option value="icon-add" 	<c:if test="${menu.icon == 'icon-add' }"> selected='selected'</c:if>>增加</option>
							<option value="icon-remove" <c:if test="${menu.icon == 'icon-remove' }"> selected='selected'</c:if>>删除</option>
							<option value="icon-edit" 	<c:if test="${menu.icon == 'icon-edit' }"> selected='selected'</c:if>>修改</option>
						</select>
					</td>
					<td style="text-align: right;">*菜单位置：</td>
					<td>
						<select id="position" class="easyui-validatebox textbox input155" name="position" required="true" >
							<option value="1" <c:if test="${menu.position == '1' }"> selected='selected'</c:if>>左边菜单</option>
							<option value="4" <c:if test="${menu.position == '4' }"> selected='selected'</c:if>>功能按钮</option>
						</select>
					</td>
				</tr>
				
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">*是否有效：</td>
					<td>
						<select class="easyui-validatebox textbox input155" id="isactive" name="isactive" required="true" >
							<option value="0" <c:if test="${menu.isactive == '0' }"> selected='selected'</c:if>>有效</option>
							<option value="1" <c:if test="${menu.isactive == '1' }"> selected='selected'</c:if>>无效</option>
						</select>
					</td>
					<td style="text-align: right;">菜单排序：</td>
					<td>
						<input type="text" id="sort" name="sort" class="easyui-numberbox input155" value="${menu.sort }"/>
					</td>
				</tr>
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">*菜单权限终止级别：</td>
					<td>
						<select class="easyui-validatebox textbox input155" id="menutype" name="menutype" required="true" >
							<option value="5" <c:if test="${menu.menutype == '5' }"> selected='selected'</c:if>>系统</option>
							<option value="4" <c:if test="${menu.menutype == '4' }"> selected='selected'</c:if>>普通系统</option>
							<option value="3" <c:if test="${menu.menutype == '3' }"> selected='selected'</c:if>>机构</option>
							<option value="2" <c:if test="${menu.menutype == '2' }"> selected='selected'</c:if>>普通机构</option>
							<option value="1" <c:if test="${menu.menutype == '1' }"> selected='selected'</c:if>>商户&公司</option>
						</select>
					</td>
					<td style="text-align: right;">*菜单类别：</td>
					<td>
						<select class="easyui-validatebox textbox input155" id="menukind" name="menukind" required="true">
							<c:forEach items="${menukindlist}" var="list">
								<option value="${list.cvalue}" <c:if test="${menu.menukind == list.cvalue}">selected="selected"</c:if> >${list.cdesc}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>