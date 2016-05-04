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
			$(this).val("");
		}
	});
	$('#phone').focusout(function(){
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
		<form id="fm" method="post" action ="merop/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<input type="hidden" name="meropseq" value="${meropInfo.meropseq }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">*商户ID：</td>
					<td>
						<input type="text" id="merseq" name="merseq" readonly="readonly"  onclick="merQuery()"
							<c:if test="${meropInfo.merseq != null}">disabled="disabled"</c:if>
							required="true" maxlength="50" class="easyui-validatebox textbox input155"  value='${meropInfo.merseq}' />
					</td>
					<td style="text-align: right;">*角色序号：</td>
					<td>
						<input type="text" id="roeseq" name="roeseq" readonly="readonly" required="true"  onclick="roleQuery()"
							<c:if test="${meropInfo.roeseq != null}">disabled="disabled"</c:if> maxlength="20" class="easyui-validatebox textbox input155" value="${meropInfo.roeseq }"/>
					</td>
				</tr>
				
				<tr style="height: 40px;" >
				<td style="text-align: right;">*登录名：</td>
				<td>
					<input type="text" id="loginname" name="loginname" style="ime-mode:disabled"
						   maxlength="20" required="true" validType="length[5,20]" invalidMessage="登录名称有效数值至少为5位!"  <c:if test="${isModify }">disabled="disabled" </c:if> class="easyui-validatebox textbox input155" value="${meropInfo.loginname }"/>
				</td>
				<td style="text-align: right;">*登录密码：</td>
				<td colspan="3">
					<input type="password" id="loginpwd" name="loginpwd"
						   maxlength="28" required="true"  <c:if test="${isModify }">disabled="disabled" </c:if> class="easyui-validatebox textbox input155" value="${meropInfo.loginpwd }"/>
				</td>
			</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">*真实姓名：</td>
					<td>
						<input type="text" id="realname" name="realname" 
							maxlength="20" required="true"  class="easyui-validatebox textbox input155" value="${meropInfo.realname }"/>
					</td>
					<td style="text-align: right;">电话：</td>
					<td>
						<input type="text" id="phone" name="phone" 
							maxlength="11" class="easyui-validatebox textbox input155" value="${meropInfo.phone }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">邮箱：</td>
					<td colspan="3">
						<input type="text" id="mail" data-options="validType:'email'" name="mail" 
							maxlength="50" class="easyui-validatebox textbox input155" value="${meropInfo.mail }"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>