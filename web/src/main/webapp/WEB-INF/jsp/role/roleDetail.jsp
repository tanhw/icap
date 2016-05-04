<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="role/save.html">
			<input type="hidden" name="isModify" value="${isModify }" />
			<input type="hidden" name="roleseq" value='${sysrole.roleseq}'/>
			<input type="hidden" name="unitid"  value="${unitid }" />
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">*角色名称：</td>
					<td>
						<input type="text" id="rolename" required="true" name="rolename"
							maxlength="20" class="easyui-validatebox textbox input155" value="${sysrole.rolename }"/>
					</td>
					<td style="text-align: right;">角色描述：</td>
					<td>
						<input type="text" id="roledesc" name="roledesc"
							maxlength="50" class="easyui-validatebox textbox input155"  value='${sysrole.roledesc}' />
					</td>
				</tr>
				<c:if test="${unitid != null }">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*角色类别：</td>
					<td>
						<select id="roletype" name="roletype"  class="easyui-validatebox textbox input155" <c:if test="${isModify }">disabled="disabled"</c:if>  required="true">
							<c:if test="${role.roletype == 2 }"><option value="3" <c:if test="${sysrole.roletype == '3' }"> selected='selected'</c:if>>--- 机构角色 ---</option></c:if>
							<c:if test="${unitkind != 0}"><option value="4" <c:if test="${sysrole.roletype == '4' }"> selected='selected'</c:if>><c:if test="${unitkind == 1}">--- 商户 ---</c:if><c:if test="${unitkind == 2}">--- 园区角色 ---</c:if></option></c:if>
						</select>
					</td>
				</tr>
				</c:if>
			</table>
		</form>
	</div>
</div>