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
				<td style="text-align: right;">登录名称：</td>
				<td>
					<input type="text" id="loginname" name="loginname" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">真实名称：</td>
				<td>
					<input type="text" id="realname" name="realname" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">角色序号：</td>
				<td>
					<input type="text" id="roeseq" name="roeseq" class="easyui-validatebox textbox input155" readonly="readonly" 
						onclick="adminQuery()"/>
				</td>
				<td style="text-align: right;">是否有效：</td>
				<td>
					<select id="isactive" name="isactive"  class="easyui-validatebox textbox input155">
							<option value="0" >--- 有效 ---</option>
							<option value="1" >--- 无效 ---</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
</div>