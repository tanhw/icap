<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>

$(function(){
	setQuery();
});

$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#unitid').blur(function(){
		if (!/^[0-9]*$/.test($(this).val())) {
			$(this).val('');
		}
	});
	$('#unittele').blur(function(){
		isNumber($(this));
	});
});
function isNumber(m){
	var value = m.val();
	if(!/^[\d| |-]*$/.test(value)){
		m.val('');
	}
}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<table border="0" cellpadding="0" cellspacing="0" id="ct">
			<tr style="height: 40px;" >
				<td style="text-align: right;">机构号：</td>
				<td>
					<input type="text" id="unitid" name="unitid" maxlength="8" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">机构名称：</td>
				<td>
					<input type="text" id="unitname" name="unitname" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">联系人：</td>
				<td>
					<input type="text" id="unitcontact" name="unitcontact" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">联系电话：</td>
				<td>
					<input type="text" id="unittele" name="unittele" maxlength="15" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">邮箱：</td>
				<td>
					<input type="text" id="unitmail" name="unitmail" data-options="validType:'email'" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">机构种类：</td>
				<td>
					<select id="unitkind" name="unitkind" class="easyui-validatebox textbox input155">
						<option value="">请选择</option>
						<c:forEach items="${unitkindList }" var="list">
							<option value="${list.cvalue }">${list.cdesc}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	</div>
</div>