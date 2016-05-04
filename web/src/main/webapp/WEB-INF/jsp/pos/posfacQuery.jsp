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
				<td style="text-align: right;">厂商代码：</td>
				<td>
					<input type="text" id="faccode" name="faccode" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">厂商名称：</td>
				<td>
					<input type="text" id="facname" name="facname" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">厂商联系人：</td>
				<td>
					<input type="text" id="faccontact" name="faccontact" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">厂商电话：</td>
				<td>
					<input type="text" id="factele" name="factele" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">厂商邮箱：</td>
				<td>
					<input type="text" id="facmail" name="facmail" data-options="validType:'email'" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
		</table>
	</div>
</div>