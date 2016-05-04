<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function (){
	$('#cardno').blur(function (){
		isNumber($(this));
	});

	$('#cardno').change(function (){
		var cardno = $(this).val();
		if(!cardno){
			return;
		}
		$.ajax({
			async:false,
			   type: "POST",
			   data:{
				   cardno:cardno
			   		},
			   		url: "blackInfo/checkRepeat.html",
					   dataType: 'json',
					   success: function(data){
							if (!data.success){
								top.$.messager.alert('错误', data.message, 'info');
								$('#cardno').val("");
							}
					   }
		});
	});
	
});
function isNumber(m){
	var value = m.val();
	if(!/^[\d| ]*$/.test(value)){
		m.val('');
	}
}

function checkRegex(obj) {
	var str = obj.val();
	var pattern = new RegExp("[`~!@#$%-+_ ^''&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|【】‘；：”“'。，、？]");
	var rs = "";
	for (var i = 0; i < str.length; i++) {
		rs = rs + str.substr(i, 1).replace(pattern, '');
	}
	$('#name').val(rs);
}

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="card/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<input type="hidden" name="cardid" value="${cardinfo.cardid }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*卡号：</td>
					<td>
						<input type="text" id="cardno" name="cardno" maxlength="19"
							required="true" ondblclick="cardnoQuery()"  class="easyui-validatebox textbox input155" value="${cardinfo.cardno }"/>
					</td>
					<td style="text-align: left;color: red;" colspan="6">  -- 卡号输入提示 、单击输入 / 双击选择</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">园区：</td>
					<td>
						<input type="text" id="campid" name="campid" onclick="campQuery()" readonly="readonly"
							maxlength="28" class="easyui-validatebox textbox input155" value="${cardinfo.campid }"/>
					</td>
					<td style="text-align: right;">状态：</td>
					<td>
						<select class="easyui-validatebox textbox input155" id="status" name="status" required="true" >
							<option value="">请选择</option>
							<c:forEach items="${cardlist }" var="list">
								<option value="${list.cvalue }" <c:if test="${list.cvalue == cardinfo.status}">selected="selected"</c:if> >${list.cdesc }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>