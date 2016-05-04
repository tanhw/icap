<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="dict/save.html">
			<input type="hidden" name="isModify" value="${isModify }" />
			<input type="hidden" name="roleseq" value='${sysrole.roleseq}'/>
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				
				<tr style="height: 40px;" >
				<td style="text-align: right;">*字典编号：</td>
				<td>
					<input type="text" id="ccode" name="ccode" maxlength="30" required="true" class="easyui-validatebox textbox input155" <c:if test="${dict.ccode != null }">readonly="readonly"</c:if>  value="${dict.ccode }" /> 
				</td>
				<td style="text-align: right;">内容值：</td>
				<td>
					<input type="text" id="cvalue" name="cvalue" maxlength="200" class="easyui-validatebox textbox input155" value="${dict.cvalue }" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">内容描述：</td>
				<td>
					<input type="text" id="cdesc" name="cdesc" maxlength="200" class="easyui-validatebox textbox input155" value="${dict.cdesc }" />
				</td>
				<td style="text-align: right;">字典类型：</td>
				<td>
					<input type="text" id="ctype" name="ctype" maxlength="2" class="easyui-validatebox textbox input155" value="${dict.ctype }" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">机构ID：</td>
				<td>
					<input type="text" id="unitid" name="unitid" readonly="readonly" onclick="unitQuery()" value="${dict.unitid }" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">父级编号：</td>
				<td>
					<input type="text" id="cupcode" name="cupcode" readonly="readonly" onclick="dictQuery()" class="easyui-validatebox textbox input155" value="${dict.cupcode }" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">*是否有效：</td>
				<td>
					<select class="easyui-validatebox textbox input155" name="isactive" id="isactive" required="true">
						<option value="0">有效</option>
						<option value="1">无效</option>
					</select>
				</td>
				<td style="text-align: right;">排序：</td>
				<td colspan="3">
					<input type="text" id="corder" name="corder" maxlength="2" class="easyui-validatebox textbox input155" value="${dict.corder }" />
				</td>
			</tr>
			</table>
		</form>
	</div>
</div>