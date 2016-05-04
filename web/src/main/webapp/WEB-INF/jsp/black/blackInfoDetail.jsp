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
	$('#name').blur(function (){
		checkRegex($(this));
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
		<form id="fm" method="post" action ="blackInfo/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<input type="hidden" name="blackseq" value="${blackInfo.blackseq }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*卡号：</td>
					<td>
						<input type="text" id="cardno" name="cardno" maxlength="19"
							required="true" ondblclick="cardnoQuery()"  class="easyui-validatebox textbox input155" value="${blackInfo.cardno }"/>
					</td>
					<td style="text-align: left;color: red;" colspan="6">  -- 卡号输入提示 、单击输入 / 双击选择</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">姓名：</td>
					<td>
						<input type="text" id="name" name="name"
							maxlength="28" class="easyui-validatebox textbox input155" value="${blackInfo.name }"/>
					</td>
					<td style="text-align: right;">增量标识：</td>
					<td>
						<select class="easyui-validatebox textbox input155" id="mark" name="mark" required="true" >
							<option value="0" <c:if test="${blackInfo.mark == '0' }"> selected="selected"</c:if>>有效</option>
							<option value="1" <c:if test="${blackInfo.mark == '1' }"> selected="selected"</c:if>>无效</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>