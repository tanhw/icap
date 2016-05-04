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
				<td style="text-align: right;">字典编号：</td>
				<td>
					<input type="text" id="ccode" name="ccode" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">是否有效：</td>
				<td>
					<select class="easyui-validatebox textbox input155" name="isactive" id="isactive">
						<option value="0">有效</option>
						<option value="1">无效</option>
					</select>
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">内容值：</td>
				<td>
					<input type="text" id="cvalue" name="cvalue" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">内容描述：</td>
				<td>
					<input type="text" id="cdesc" name="cdesc" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">字典类型：</td>
				<td>
					<input type="text" id="ctype" name="ctype" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">父级编号：</td>
				<td>
					<input type="text" id="cupcode" readonly="readonly" onclick="dictQuery()" name="cupcode" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<c:if test="${unitid == null }">
			<tr style="height: 40px;" >
				<td style="text-align: right;">机构ID：</td>
				<td>
					<input type="text" id="unitid" name="unitid" readonly="readonly" onclick="unitQuery()" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			</c:if>
		</table>
	</div>
</div>