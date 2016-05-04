<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<script>
	$(document).ready(function(){
		//正则验证
		var length = 0;
		$('input').keyup(function(){
			var value = $(this).val();
			var num = value.length;
			if(!/^[\d]*$/.test(value)){
				if((length < num) && (num-1 > length)){
					value = value.substr(0, length);
					num = length;
				}else{
					value = value.substr(0, num - 1);
					num-- ;
				}
				$(this).val(value);
			}
			length = num;
		});
		$('input').click(function(){
			$(this).val('');
		})
	});

</script>

<form id="fm" method="post"  action ="unit/save.html" >
	<input type="hidden" name="isModify" value="${isModify}" />
	<input type="hidden" name="unitid" value="${unit.unitid}" />
	<table border="0" cellpadding="0" cellspacing="0" id="ct">
		<tr style="height: 40px;" >
			<td style="text-align: right;">*清算银行机构：</td>
			<td>
				<input type="text" name="bankorgid" class="easyui-validatebox textbox input155" required="true" value="${unit.bankorgid}"validType="length[8,8]" maxlength="8" />
			</td>
		</tr>
		<tr style="height: 40px;" >
			<td style="text-align: right;">*清算银行号：</td>
			<td>
				<input type="text" name="bankid" class="easyui-validatebox textbox input155" required="true" value="${unit.bankid}" validType="length[4,4]" maxlength="4"/>
			</td>
		</tr>
		<tr style="height: 40px;" >
			<td style="text-align: right;">*银行批次号：</td>
			<td>
				<input type="text" id="bankposoffseq" name="bankposoffseq" class="easyui-validatebox textbox input155" required="true" value="${unit.bankposoffseq}" validType="length[6,6]" maxlength="6"/>
			</td>
		</tr>
	</table>
</form>


