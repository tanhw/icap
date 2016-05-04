<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#factele').blur(function(){
		isCheckTel($(this));
	});
	$('#facfax').blur(function(){
		isCheckTel($(this));
	});
});

function isCheckTel(m){
	var value = m.val();
	if(!/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/.test(value)){
		m.val('');
	}
}

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="posfac/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<input type="hidden" name="factoryid" value="${tPosFac.factoryid }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*厂商名称：</td>
					<td >
						<input type="text" id="facname" name="facname" required="true"
							maxlength="20" class="easyui-validatebox textbox input155" value="${tPosFac.facname }"/>
					</td>
					<td style="text-align: right;">厂商代码：</td>
					<td>
						<input type="text" id="faccode" name="faccode" 
							maxlength="20" class="easyui-validatebox textbox input155" value="${tPosFac.faccode }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">厂商联系人：</td>
					<td>
						<input type="text" id="faccontact" name="faccontact" 
							maxlength="20" class="easyui-validatebox textbox input155" value="${tPosFac.faccontact }"/>
					</td>
					<td style="text-align: right;">厂商电话：</td>
					<td>
						<input type="text" id="factele" name="factele" 
							maxlength="12" class="easyui-validatebox input155" value="${tPosFac.factele }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">厂商传真：</td>
					<td>
						<input type="text" id="facfax" name="facfax" 
							maxlength="20" class="easyui-validatebox textbox input155" value="${tPosFac.facfax }"/>
					</td>
					<td style="text-align: right;">厂商描述：</td>
					<td>
						<input type="text" id="facdesc" name="facdesc" 
							maxlength="20" class="easyui-validatebox textbox input155" value="${tPosFac.facdesc }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">厂商邮箱：</td>
					<td>
						<input type="text" id="facmail" name="facmail" 
							maxlength="20" data-options="validType:'email'" class="easyui-validatebox textbox input155" value="${tPosFac.facmail }"/>
					</td>
					<td style="text-align: right;">厂商地址：</td>
					<td>
						<input type="text" id="facaddr" name="facaddr" 
							maxlength="20" class="easyui-validatebox textbox input155" value="${tPosFac.facaddr }"/>
					</td>
				</tr>
				<tr style="height: 40px;">
					<td style="text-align: right;">供应设备描述：</td>
					<td>
						<input type="text" id="facprodesc" name="facprodesc" 
							maxlength="20" class="easyui-validatebox textbox input155" value="${tPosFac.facprodesc }"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>