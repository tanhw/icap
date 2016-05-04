<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$(function(){
	setQuery();
});  
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<table border="0" cellpadding="0" cellspacing="0" id="ct">
			<tr style="height: 40px;" >
				<td style="text-align: right;">菜单编号：</td>
				<td>
					<input type="text" id="menucode" name="menucode" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">菜单名称：</td>
				<td>
					<input type="text" id="menuname" name="menuname" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
				<tr style="height: 40px;" >
				<td style="text-align: right;">上级菜单：</td>
				<td>
					<input type="text" id="upmenu" name="upmenu" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">菜单级别：</td>
				<td>
					<select id="menulevel" name="menulevel" class="easyui-validatebox textbox input155" onchange="_showUpMenu();">
						<option value="">--请选择--</option>
						<option value="1" <c:if test="${menu.menulevel == '1' }"> selected='selected'</c:if>>--一级菜单--</option>
						<option value="2" <c:if test="${menu.menulevel == '2' }"> selected='selected'</c:if>>--二级菜单--</option>
						<option value="3" <c:if test="${menu.menulevel == '3' }"> selected='selected'</c:if>>--三级菜单--</option>
					</select>
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">是否有效：</td>
				<td>
					<select id="isactive" name="isactive" class="easyui-validatebox textbox input155">
						<option value="">请选择</option>
						<option value="0">有效</option>
						<option value="1">无效</option>
					</select>
				</td>
				<td style="text-align: right;">菜单类别：</td>
				<td>
					<select class="easyui-validatebox textbox input155" id="menukind" name="menukind">
						<option value="">请选择</option>
						<c:forEach items="${menukindlist}" var="list">
							<option value="${list.cvalue}" <c:if test="${menu.menukind == list.cvalue}">selected="selected"</c:if> >${list.cdesc}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	</div>
</div>