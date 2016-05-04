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
				<td style="text-align: right;">机构ID：</td>
				<td>
					<input type="text" id="unitid" name="unitid" maxlength="8" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">操作员ID：</td>
				<td>
					<input type="text" id="oplogname" name="oplogname" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">操作是否成功：</td>
				<td>
					<select id="opflag" name="opflag" class="easyui-validatebox textbox input155">
						<option value="">请选择</option>
						<option value="0">成功</option>
						<option value="1">失败</option>
					</select>
				</td>
				<td style="text-align: right;">操作员姓名：</td>
				<td>
					<input type="text" id="oprealname" name="oprealname" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
		</table>
	</div>
</div>