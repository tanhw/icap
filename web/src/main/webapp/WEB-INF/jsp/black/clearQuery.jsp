<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="blackInfo/blackClear.html">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">开始时间：</td>
					<td>
						<input type="text" id="startTime" name="startTime" required="true" editable="false" class="easyui-datetimebox textbox input155" formatter="formatterDate" parser="parserDate" value="${startTime }"/>
					</td>
					<td style="text-align: right;">结束时间：</td>
					<td>
						<input type="text" id="endTime" name="endTime" required="true" editable="false" class="easyui-datetimebox textbox input155" formatter="formatterDate" parser="parserDate" value="${endTime }"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>