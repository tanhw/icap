<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#banktele').blur(function () {
		isNumber($(this));
	});
	$('#bankfax').blur(function () {
		isNumber($(this));
	});
	$('#bankcode').blur(function () {
		isNumber($(this));
	});
	$('#bankname').blur(function () {
		checkRegex($(this));
	});
	$('#bankaddr').blur(function () {
		checkRegex($(this));
	});
	$('#bankcontact').blur(function () {
		checkRegex($(this));
	});
});

function isNumber(m){
	var value = m.val();
	if(!/^[\d]*$/.test(value)){
		m.val('');
	}
}
function checkRegex(obj) {
	var str = obj.val();
	var pattern = new RegExp("[`~%-+_!@%-+_ #$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var rs = "";
	for (var i = 0; i < str.length; i++) {
		rs = rs + str.substr(i, 1).replace(pattern, '');
	}
	var id = obj.attr("id");

	if(id == "bankname"){
		$('#bankname').val(rs);
	}else if(id == "bankaddr" ){
		$('#bankaddr').val(rs);
	}else{
		$('#bankcontact').val(rs);
	}

}

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="bank/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<input type="hidden" name="bankid" value="${bankInfo.bankid }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*银行名称：</td>
					<td>
						<input type="text" id="bankname" name="bankname" required="true"
							maxlength="20" class="easyui-validatebox textbox input155" value="${bankInfo.bankname }"/>
					</td>
					<td style="text-align: right;">*银行代码：</td>
					<td>
						<input type="text" id="bankcode" name="bankcode" required="true"
                               <c:if test="${bankInfo.bankcode != null}">readonly="readonly"</c:if>
							   maxlength="8" class="easyui-validatebox textbox input155" value="${bankInfo.bankcode }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">银行联系人：</td>
					<td>
						<input type="text" id="bankcontact" name="bankcontact"
							maxlength="20" class="easyui-validatebox textbox input155" value="${bankInfo.bankcontact }"/>
					</td>
					<td style="text-align: right;">银行地址：</td>
					<td>
						<input type="text" id="bankaddr" name="bankaddr"
							   maxlength="20" class="easyui-validatebox textbox input155" value="${bankInfo.bankaddr }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">联系人电话：</td>
					<td>
						<input type="text" id="banktele" name="banktele"
							   maxlength="11" class="easyui-validatebox textbox input155" value="${bankInfo.banktele }"/>
					</td>
					<td style="text-align: right;">银行传真：</td>
					<td>
						<input type="text" id="bankfax" name="bankfax"
							   maxlength="20" class="easyui-validatebox textbox input155" value="${bankInfo.bankfax }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">银行邮箱：</td>
					<td>
						<input type="text" id="bankmail" name="bankmail"
							maxlength="20" data-options="validType:'email'" class="easyui-validatebox textbox input155" value="${bankInfo.bankmail }"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>