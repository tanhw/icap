<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<script type="text/javascript">
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
});
</script>

<div id="cc" class="easyui-layout" fit='true'>   
<form id="fm" method="post" >
	<input type="hidden" name="unitid" value="${unitid }" />
	<input type="hidden" name="roleseq" value="${role.roleseq }" />
	<input type="hidden" name="unitadminseq" value="${unitAdmin.unitadminseq }" />
    <div data-options="collapsible:false,region:'west',title:'新的机构管理员',split:true" style="width:270px;padding:10px">
		<table border="0" cellpadding="0" cellspacing="0" id="ct">
			<tr style="height: 40px;" >
				<td colspan="2">
					<span id="message"  >提示信息: <br/>首字母字母大小写,且最小5字母或数字!</span>
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">*登录名：</td>
				<td>
					<input type="text" required="true" id="loginname" name="loginname"  <c:if test="${isModify && unitAdmin.loginname != null }">readonly="readonly" </c:if> maxlength="100" class="easyui-validatebox textbox input155" value="${unitAdmin.loginname }" />
				</td>
			</tr>
			<c:if test="${!isModify || unitAdmin.loginname == null }">
			<tr style="height: 40px;" >
				<td style="text-align: right;">*密码：</td>
				<td>
					<input type="password" required="true" name="loginpwd" maxlength="100" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			</c:if>
			<tr style="height: 40px;" >
				<td style="text-align: right;">*真实姓名：</td>
				<td>
					<input type="text" name="realname" required="true" class="easyui-validatebox textbox input155" value="${unitAdmin.realname }" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">电话：</td>
				<td>
					<input type="text" name="phone" class="easyui-numberbox input155" value="${unitAdmin.phone }" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">邮箱：</td>
				<td>
					<input type="text" name="mail" class="easyui-validatebox textbox input155" data-options="validType:'email'" value="${unitAdmin.mail }" />
				</td>
			</tr>
		</table>
    </div>

    <div data-options="collapsible:false,region:'east',title:'权限分配',split:true" style="width:270px;padding:10px">
		<c:if test="${!isModify }">
				<ul id="tt" class="easyui-tree" data-options="url:'role/initMenuUnit.html?unitid=${unitid}',cascadeCheck:true,animate:true,checkbox:true"></ul>
		</c:if>
		<c:if test="${isModify }">
			<ul id="tt" class="easyui-tree" data-options="url:'role/roleidAllotMenu.html?roleseq=${role.roleseq }&unitid=${unitid}',cascadeCheck:true,animate:true,checkbox:true" ></ul>
		</c:if>
	</div>

    <div data-options="region:'center',title:'新的角色信息',split:true" style="padding:10px">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*角色名称：</td>
					<td>
						<input type="text" required="true" name="rolename" class="easyui-validatebox textbox input155" value="${role == null ? '机构管理员':role.rolename }" />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">角色描述：</td>
					<td>
						<input type="text" name="roledesc" class="easyui-validatebox textbox input155" value="${role == null ? '机构管理员角色':role.roledesc }" />
					</td>
				</tr>
			</table>
    </div>
</form>
</div>  

