<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$(function(){
	setQuery();
});

submitFlatPwd = false;
function modifyPasswd(){
	var pawd = $('#passwd').val();
	var pawdent = $('#passwdEnter').val();
	
	if(pawd != pawdent){
		top.$.messager.alert('提示信息', '两次输入不一样！', 'info');
		return ;
	}
	
	if(submitFlatPwd) {

		return;
	}
	submitFlatPwd = true;
	var fm=$("#ff");
	var url="savePawd.html";

	var jStaticForm = document.ff;

	var oldpasswd = jStaticForm.oldpasswd.value;
	jStaticForm.oldpasswd.value = RSAEnc(oldpasswd,rsaPublicKeyN,rsaPublicKeyE);

	var password = jStaticForm.password.value;
	jStaticForm.password.value = RSAEnc(password,rsaPublicKeyN,rsaPublicKeyE);

	var passwdEnter = jStaticForm.passwdEnter.value;
	jStaticForm.passwdEnter.value = RSAEnc(passwdEnter,rsaPublicKeyN,rsaPublicKeyE);

	fm.form('submit', {
		url:url,
		onSubmit: function(){
			if(!$(this).form('validate')) {
				submitFlat = false;
			}
			return $(this).form('validate');
			
		},
		success:function(data){
			var theback=eval("(" +data + ")");
			if (!theback.success)
				top.$.messager.alert('错误', theback.message, 'error',function(){
					if (theback.url){
						top.location.href=theback.url;
					}
				});
			else{
				top.$.messager.alert('提示信息', theback.message, 'info',function(){
					$('#indexPanel').window('close');
				});
			}
			submitFlatPwd = false;
		}
	});
}

function clearPasswd(){
	$('#passwd').val('');
	$('#passwdEnter').val('');
	$('#oldpasswd').val('');
}

$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
	<form id="ff" method="post" name="ff">
		<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">旧密码：</td>
					<td>
						<input type="password" id="oldpasswd" name="oldpasswd" style="ime-mode:disabled" required="true" maxlength="28" class="easyui-validatebox textbox input155"  />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">新密码：</td>
					<td>
						<input type="password" id="passwd" name="password" style="ime-mode:disabled" required="true" maxlength="28" class="easyui-validatebox textbox input155"  />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;" >确认新密码：</td>
						<td>
							<input type="password" id="passwdEnter" name="passwdEnter" style="ime-mode:disabled" required="true" maxlength="28" class="easyui-validatebox textbox input155"  />
						</td>
					</tr>
				<tr style="height: 40px;" >
					<td colspan="2" style="text-align: center;" >
						<a id="btn" href="javascript:void(0)" onclick="modifyPasswd()" class="easyui-linkbutton" >修改</a>  
						&nbsp;
						<a id="btn" href="javascript:void(0)" onclick="clearPasswd()" class="easyui-linkbutton" >清除</a> 
					</td>
				</tr>
		</table>
		</form>
	</div>
</div>