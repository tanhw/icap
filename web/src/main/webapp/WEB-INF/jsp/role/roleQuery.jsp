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
				<td style="text-align: right;">角色编号：</td>
				<td>
					<input type="text" id="roleseq" name="roleseq" maxlength="18" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">角色名：</td>
				<td>
					<input type="text" id="rolename" name="rolename" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
		</table>
	</div>
</div>