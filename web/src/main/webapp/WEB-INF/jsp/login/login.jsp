<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemTitle }</title>
<%@include file="../common/headCommon.jsp"%>
<script>
	$(function() {
		$('body').css('visibility', 'visible');
	});

	function login() {
		var operatorType = $('#operatorType').val();
		
		if(operatorType != '1'){
			var unitId = $('#unitId').val();
			if(unitId == null || unitId == ''){
				top.$.messager.alert('提示信息', ' --机构不能为空 ,请选择输入机构--', 'error');
				return;
			}
		}
		var jStaticForm = document.loginFm;
		var password = jStaticForm.passWord.value;
		jStaticForm.passWord.value = RSAEnc(password,rsaPublicKeyN,rsaPublicKeyE);
		
		$('#loginFm').form('submit', {
			url : "logon.html",
			data:{unitId:unitId,operatorType:operatorType},
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(data) {
				var theback = eval("(" + data + ")");
				if (!theback.success){
					top.$.messager.alert('错误', theback.message, 'error');
					changeImg();
				}
				else {
					top.location.href = "index.html";
				}
			}
		});
	}

	function quit() {
		window.opener = null;
		window.open('', '_self');
		window.close();
	}
	
//	$(function(){
//		document.onkeydown = function(e){
//			 var ev = document.all ? window.event : e;
//			 if(ev.keyCode == 13){
//
//				 var jStaticForm = document.loginFm;
//			 var password = jStaticForm.passWord.value;
//				 jStaticForm.passWord.value = RSAEnc(password,rsaPublicKeyN,rsaPublicKeyE);
//
//				 $('#loginFm').form('submit', {
//						url : "logon.html",
//						onSubmit : function() {
//							return $(this).form('validate');
//						},
//						success : function(data) {
//							var theback = eval("(" + data + ")");
//							if (!theback.success){
//								top.$.messager.alert('错误', theback.message, 'error');
//								changeImg();
//							}
//							else {
//								top.location.href = "index.html";
//							}
//						}
//					});
//			 }
//		}
//	});
	
	$(document).ready(function() {

		$('#unitId').blur(function() {
			isNumber($(this));
		});
		
		$('#operatorType').change(function (){
			var operatorType = $('#operatorType').val();
			if(operatorType != '1'){
				$('.hid').show();
				$('#unitId').removeAttr("disabled");
			}else{
				$('.hid').hide();
				$('#unitId').attr("disabled");
			}
		});
	});

	function isNumber(m){
		var value = m.val();
		if(!/^[\d| ]*$/.test(value)){
			m.val('');
		}
	}
	
</script>
</head>

<body style="visibility: hidden;background-color: #c0c0c0">
	<div class="easyui-window" iconCls="icon-man" title="公共事业付费平台"
		minimizable="false" maximizable="false" resizable="false"
		closable="false" style="width: 387px; height: 370px; padding: 0px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div data-options="region:'west'" border="false"
				style="overflow: hidden;">
				<div>
					<img alt="登录图片" height="70" width="387" src="images/loginLogo1.gif" />
				</div>
				<div id="mainTab" class="easyui-tabs" border="false">
					<div title="登录" style="padding: 20px;">
						<form id="loginFm" method="post" name="loginFm">
							<table border="0" cellpadding="0" cellspacing="0">
								<tr style="height: 40px; text-align: right">
									<td>类 别：</td>
									<td><select class="easyui-validatebox textbox" id="operatorType" name="operatorType" style="width: 92%">
										<c:forEach items="${operatortypeList}" var="list" >
											<option value="${list.cvalue }">${list.cdesc}</option>
										</c:forEach>
									</select>
									</td>
								</tr>
								<tr style="height: 40px; text-align: right" class="hid">
									<td style="width: 60px;"  class="hid" >机构号：</td>
									<td style="width: 200px;" class="hid">
									<input type="test" id="unitId" name="unitId" style="width: 90%" class="easyui-validatebox textbox input155" 
										 maxlength='8' validType="length[8,8]" /></td>
								</tr>
								<tr style="height: 40px; text-align: right">
									<td style="width: 60px;">用户名：</td>
									<td style="width: 200px;"><input type="text"
										name="loginName" style="width: 90%"
										class="easyui-validatebox textbox input155" required="true"
										validType="length[5,20]" /></td>
								</tr>
								<tr style="height: 40px; text-align: right">
									<td>密 码：</td>
									<td><input type="password" name="passWord" 
										style="width: 90%" class="easyui-validatebox textbox input155"
										required="true" /></td>	
								</tr>
								<c:if test="${verifycode }">
								<tr style="height: 40px; text-align: right">
									<td>验证码：</td>
									<td>
										 <input type="text" id="veryCode" name="veryCode" class="easyui-validatebox textbox input60"  />   
										<img class="textbox input60" id="imgObj" src="verify.html"/>   
								        <a href="javascript:void(0);" onclick="changeImg()" style="font-size: 12px;">换一个</a> 
									</td>
								</tr>
								</c:if>
							</table>
						</form>
					</div>
					<div title="关于" style="padding: 20px;">
						<p>${systemTitle } V1.0.0</p>
						<p>版权所有 © 2014 - 2016 </p>
					</div>
				</div>

			</div>
			<div region="south" border="false"
				style="text-align: right; height: 35px; line-height: 35px; background: #e0ecff; border-top: 1px solid #99bbe8; overflow: hidden;">
				<div
					style="text-align: right; padding-right: 5px; overflow: hidden;">
					<a class="easyui-linkbutton" iconCls="icon-ok"
						href="javascript:void(0)" onclick="login()">登录</a> <a
						class="easyui-linkbutton" iconCls="icon-cancel"
						href="javascript:void(0)" onclick="quit()">取消</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
