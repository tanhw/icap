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
				<td style="text-align: right;">配置名称：</td>
				<td>
					<input type="text" id="confname" name="confname" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">业务类型：</td>
				<td>
					<select id="busi" name="busi" class="easyui-validatebox textbox input155">
						<option value="">请选择</option>
						<c:forEach items="${busiList }" var="list">
							<option value="${list.cvalue }">${list.cdesc}</option>
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right;">是否有效：</td>
				<td>
					<select class="easyui-validatebox textbox input155" name="isactive" id="isactive">
						<option value="">请选择</option>
						<option value="0">有效</option>
						<option value="1">无效</option>
					</select>
				</td>
			</tr>
		</table>
	</div>
</div>