<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){

	$('#reload').click(function(){
		$('#detailPanel').dialog("refresh");
	});

})

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<table border="0" cellpadding="0" cellspacing="0" id="ct">
			<tr style="height: 40px;" >
				<td style="text-align: right;">新增机构确认码：</td>
				<td>
					<input type="text" value="${code}" style="border:1px solid gray;width: 216px;"> &nbsp; <input id="reload" class="icon-reload" style="width: 26px;height: 26px;border: 0px solid" type="button" />
				</td>
			</tr>
		</table>
	</div>
</div>