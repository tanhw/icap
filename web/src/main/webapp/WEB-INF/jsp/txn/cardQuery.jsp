<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<form id="fm" method="post" action ="bustxnlog/expCardTxnBase.html">
		<table border="0" cellpadding="0" cellspacing="0" id="ct">
			<tr style="height: 40px;" >
				<td style="text-align: right;">开始时间:</td>
				<td> 
					<input type="text" id="startTime"	name="startTime"  required="true" editable="false"
					    value="${startTime }" class="easyui-datetimebox textbox input155" formatter="formatterDate" parser="parserDate" />
				</td>
				<td style="text-align: right;">结束时间：</td>
				<td>
					<input type="text" id="endTime"	name="endTime"  required="true" editable="false"
						value="${endTime }"
						 class="easyui-datetimebox textbox input155" formatter="formatterDate" parser="parserDate"  />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">卡号：</td>
				<td>
					<input type="text" id="cardno" name="cardno" maxlength="19" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">卡片物理类型：</td>
				<td>
					<select id="cardmodel" name="cardmodel" maxlength="1" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<c:forEach items="${phsctypeList }" var="item">
							<option value="${item.cvalue}">${item.cdesc}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	</form>
	</div>
</div>