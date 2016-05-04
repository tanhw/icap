<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#cardbin').blur(function(){
		if (!/^[0-9]*$/.test($(this).val())) {
			$(this).val('');
		}
	});
	$('#binname').blur(function(){
		checkRegex($(this));
	});
});

function checkRegex(obj) {
	var str = obj.val();
	var pattern = new RegExp("[`~%-+_!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var rs = "";
	for (var i = 0; i < str.length; i++) {
		rs = rs + str.substr(i, 1).replace(pattern, '');
	}
	$('#binname').val(rs);
}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="cardbin/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<input type="hidden" name="binseq" value="${bankCardbin.binseq}">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
			<tr style="height: 40px;" >
				<td style="text-align: right;">*银行ID：</td>
				<td colspan="3">
					<input type="text" id="bankid" name="bankid"  required="true" onclick="bankQuery()" readonly="readonly" class="easyui-validatebox textbox input155" maxlength="18"  value="${bankCardbin.bankid}"/>
				</td>
				<td style="text-align: right;">*卡bin：</td>
				<td>
					<input type="text" id="cardbin" name="cardbin" required="true"   class="easyui-validatebox textbox input155"   maxlength="9"  value="${bankCardbin.cardbin}"/>
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">卡bin名称：</td>
				<td>
					<input type="text" id="binname" name="binname" class="easyui-validatebox textbox input155"  maxlength="100"  value="${bankCardbin.binname}"/>
				</td>
			</tr>
			</table>
		</form>
	</div>
</div>