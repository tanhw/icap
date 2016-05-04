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
			<tr style="height: 40px;">
				<td style="text-align: right;">参数名称:</td>
				<td>
					<input type="text" id="paraName" name="paraName" class="easyui-validatebox textbox input155"/>
				</td>
				<td style="text-align: right;">参数编号:</td>
				<td>
					<input type="text" id="paraNo" name="paraNo" class="easyui-validatebox textbox input155"/>
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">参数值：</td>
				<td>
					<input type="text" id="paraValue" name="paraValue" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">记录状态：</td>
				<td>
					<select name="recordStat" id="recordStat" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<option value="0">未记录</option>
						<option value="1">已记录</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
</div>