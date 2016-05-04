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
					<td style="text-align: right;">操作员类型：</td>
					<td>
							<samp style="color: red">${opType }</samp>
					</td>
					<td style="text-align: right;" >角色类型：</td>
					<td>
							<samp style="color: red">${opRole.roletypeDesc }</samp>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;" width="25%">角色名：</td>
					<td width="25%">
							<samp style="color: red">${opRole.rolename }</samp>
					</td>
					<td style="text-align: right;" width="25%">角色描述：</td>
					<td width="25%">
							<samp style="color: red">${opRole.roledesc }</samp>
					</td>
				</tr>
			<c:if test="${obj != null }">
				<tr style="height: 40px;" >
					<td style="text-align: right;">登陆名：</td>
					<td>
							<samp>${obj.loginname }</samp>
					</td>
					<td style="text-align: right;">姓名：</td>
					<td>
							<samp>${obj.realname }</samp>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">电话：</td>
					<td>
							<samp>${obj.phone }</samp>
					</td>
					<td style="text-align: right;">邮箱：</td>
					<td>
							<samp>${obj.mail }</samp>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">上次登录时间：</td>
					<td>
							<samp>${obj.lastlogtimeDesc }</samp>
					</td>
					<td style="text-align: right;">上次登录IP：</td>
					<td>
							<samp>${obj.lastlogip }</samp>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
</div>