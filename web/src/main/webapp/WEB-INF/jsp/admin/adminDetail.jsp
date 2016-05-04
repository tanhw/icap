<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#loginname').focusout(function(){
		value = $(this).val();
		if(value && !/^([a-zA-Z])(\w{4,})$/.test(value)){
		}
	});
	$('#phone').blur(function(){
		isCheckTel($(this));
	});
	$('#realname').blur(function(){
		checkRegex($(this));
	});
});

function isCheckTel(m){
	var value = m.val();
	if(!/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/.test(value)){
		m.val('');
	}
}

function checkRegex(obj) {
	var str = obj.val();
	var pattern = new RegExp("[`~!@#$^%-+_ &*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var rs = "";
	for (var i = 0; i < str.length; i++) {
		rs = rs + str.substr(i, 1).replace(pattern, '');
	}
	$('#realname').val(rs);
}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="admin/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<input type="hidden" name="adminseq" value="${tAdminInfo.adminseq }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*登录名称：</td>
					<td>
						<input type="text" id="loginname" name="loginname" required="true" maxlength="20" style="ime-mode:disabled"
							   <c:if test="${tAdminInfo.loginname != null}">readonly="readonly"</c:if>
							   validType="length[5,20]" invalidMessage="登录名称有效数值至少为5位!" class="easyui-validatebox textbox input155" value="${tAdminInfo.loginname }"/>
					</td>
					<td style="text-align: right;">*登录密码：</td>
					<td>
						<input type="password" id="loginpwd" name="loginpwd" required="true" maxlength="28"
							<c:if test="${tAdminInfo.loginpwd != null}"> disabled="disabled"</c:if> class="easyui-validatebox textbox input155" value="${tAdminInfo.loginpwd }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">*角色序号：</td>
					<td>
						<input type="text" id="roeseq" name="roeseq" readonly="readonly" maxlength="20" class="easyui-validatebox textbox input155" onclick="adminQuery()"
							<c:if test="${tAdminInfo.roeseq != null}">disabled="disabled"</c:if>
							value="${tAdminInfo.roeseq}" required="true"/>
					</td>
					<td style="text-align: right;">*真实姓名：</td>
					<td>
						<input type="text" id="realname" name="realname" required="true" maxlength="50" class="easyui-validatebox textbox input155"  value='${tAdminInfo.realname}' />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">电话：</td>
					<td>
						<input type="text" id="phone" name="phone" maxlength="20" class="easyui-validatebox textbox input155" 
							value="${tAdminInfo.phone}" />
					</td>
					<td style="text-align: right;">邮箱：</td>
					<td>
						<input type="text" id="mail" name="mail" data-options="validType:'email'" maxlength="50" class="easyui-validatebox textbox input155"  value='${tAdminInfo.mail}' />
					</td>
				</tr>
				<tr style="height: 40px;">
					<td style="text-align: right;">*是否有效：</td>
					<td>
						<select id="isactive" name="isactive"  class="easyui-validatebox textbox input155" required="true">
							<option value="0" <c:if test="${tAdminInfo.isactive == '0' }"> selected='selected'</c:if>>有效</option>
							<option value="1" <c:if test="${tAdminInfo.isactive == '1' }"> selected='selected'</c:if>>无效</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>