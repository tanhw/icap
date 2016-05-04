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
			$('#message').attr("style","color: red;");
		}else{
			$('#message').removeAttr("style");
		}
	});
	$('#phone').blur(function (){
		isNumber($(this));
	});
});

function isCheckTel(m){
	var value = m.val();
	if(!/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/.test(value)){
		m.val('');
	}
}
function isNumber(m){
	var value = m.val();
	if(!/^[\d]*$/.test(value)){
		m.val('');
	}
}
</script>
<div class="easyui-layout" fit="true">

	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="unitadmin/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<input type="hidden" name="unitadminseq" value="${unitAdmin.unitadminseq }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">*登录名：</td>
					<td>
						<input type="text" id="loginname" name="loginname" style="ime-mode:disabled"
							maxlength="20" required="true"  validType="length[5,20]" invalidMessage="登录名称有效数值至少为5位!" <c:if test="${isModify }">disabled="disabled" </c:if>  class="easyui-validatebox textbox input155" value="${unitAdmin.loginname }"/>
					</td>
					<c:if test="${!isModify }">
					<td colspan="2" style="padding-left: 10px;">
					<span id="message"  >提示信息: <br/>首字母字母大小写,且最小5字母或数字!</span>
					</td>
					
				</tr>
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">*登录密码：</td>
					<td >
						<input type="password" id="loginpwd" name="loginpwd" 
							maxlength="28" required="true"  class="easyui-validatebox textbox input155" />
					</td>
					</c:if>
					<td style="text-align: right;">*角色序号：</td>
					<td>
						<input type="text" id="roeseq" name="roeseq" maxlength="20" readonly="readonly" onclick="roleQuery()"
							<c:if test="${unitAdmin.roeseq != null }">disabled="disabled"</c:if>
							value="${unitAdmin.roeseq}" required="true" class="easyui-validatebox textbox input155" />
					</td>
				</tr>
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">*真实姓名：</td>
					<td>
						<input type="text" id="realname" name="realname"
							maxlength="50" required="true" class="easyui-validatebox textbox input155"  value='${unitAdmin.realname}' />
					</td>
					<td style="text-align: right;">电话：</td>
					<td>
						<input type="text" id="phone" name="phone" maxlength="11" class="easyui-validatebox textbox input155"
							value="${unitAdmin.phone}"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">邮箱:</td>
					<td>
						<input type="text" id="mail" name="mail" data-options="validType:'email'" maxlength="12" class="easyui-validatebox textbox input155"
							value="${unitAdmin.mail}"/>
					</td>
					<td style="text-align: right;">*是否有效：</td>
					<td>
						<select id="isactive" name="isactive"  class="easyui-validatebox textbox input155" required="true">
							<option value="0" <c:if test="${unitAdmin.isactive == '0' }"> selected='selected'</c:if>> --- 有效 ---</option>
							<option value="1" <c:if test="${unitAdmin.isactive == '1' }"> selected='selected'</c:if>> --- 无效 ---</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>