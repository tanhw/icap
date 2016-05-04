<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#branchphone').blur(function(){
		isNumber($(this));
	});
	$('#branchid').blur(function(){
		isNumber($(this));
	});
	$('#fax').blur(function(){
		isNumber($(this));
	});
	$('#feeseq').blur(function(){
		isNumber($(this));
	});
	$('#poscount').blur(function(){
		isNumber($(this));
	});
	$('#brancheng').blur(function(){
		checkRegex($(this));
	});
	$('#branchchn').blur(function(){
		checkRegex($(this));
	});
	$('#stfid').blur(function(){
		checkRegex($(this));
	});
	$('#branchadre').blur(function(){
		checkRegex($(this));
	});
	$('#brancity').blur(function(){
		checkRegex($(this));
	});
	$('#branchdesc').blur(function(){
		checkRegex($(this));
	});

	$('#branchid').change(function (){
		var branchid = $(this).val();
		if(!branchid){
			return;
		}
		$.ajax({
			async:false,
			type: "POST",
			data:{
				branchid:branchid
			},
			url: "merchant/checkRepeat.html",
			dataType: 'json',
			success: function(data){
				if (!data.success){
					top.$.messager.alert('错误', data.message, 'error');
					$('#branchid').val("");
				}
			}
		});
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
	var pattern = new RegExp("[`~%-+_!@#$%+-_ ^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var rs = "";
	for (var i = 0; i < str.length; i++) {
		rs = rs + str.substr(i, 1).replace(pattern, '');
	}

	var id = obj.attr("id");

	if (id == "branchchn") {
		$('#branchchn').val(rs);
	} else if (id == "brancheng") {
		$('#brancheng').val(rs);
	}else if(id == "stfid"){
		$('#stfid').val(rs);
	}else if(id == "brancity"){
		$('#brancity').val(rs);
	}else if(id == "branchdesc"){
		$('#branchdesc').val(rs);
	}else{
		$('#branchadre').val(rs);
	}
}

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="merchant/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<input type="hidden" name="merseq" value="${merInfo.merseq }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*商户编号：</td>
					<td>
						<input type="text" id="branchid" name="branchid" required="true" validType="length[15,15]" invalidMessage="商户编号有效数值为15位!"
							   <c:if test="${merInfo.branchid != null}">readonly="readonly"</c:if>
							maxlength="15" class="easyui-validatebox textbox input155" value="${merInfo.branchid }"/>
					</td>
					<td style="text-align: right;">银行收单商户号：</td>
					<td>
						<input type="text" id="bankmerid" name="bankmerid" class="easyui-validatebox textbox input155"
							maxlength="15" value="${merInfo.bankmerid }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">商户英文名：</td>
					<td>
						<input type="text" id="brancheng" name="brancheng" 
							maxlength="50" class="easyui-validatebox textbox input155" value="${merInfo.brancheng }"/>
					</td>
				
					<td style="text-align: right;">商户中文名：</td>
					<td>
						<input type="text" id="branchchn" name="branchchn" 
							maxlength="50" class="easyui-validatebox textbox input155" value="${merInfo.branchchn }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">商户负责人：</td>
					<td>
						<input type="text" id="stfid" name="stfid" 
							maxlength="20"  class="easyui-validatebox textbox input155" value="${merInfo.stfid }"/>
					</td>
					<td style="text-align: right;">商户地址：</td>
					<td>
						<input type="text" id="branchadre" name="branchadre" 
							maxlength="20" class="easyui-validatebox textbox input155" value="${merInfo.branchadre }"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">商户logo：</td>
					<td>
						<input type="text" id="branlogo" name="branlogo" 
							maxlength="20" class="easyui-validatebox textbox input155" value="${merInfo.branlogo }"/>
					</td>
					<td style="text-align: right;">所在城市：</td>
					<td>
						<input type="text" id="brancity" name="brancity"  maxlength="50" 
							class="easyui-validatebox textbox input155"  value='${merInfo.brancity}' />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">商户网站：</td>
					<td>
						<input type="text" id="site" name="site" maxlength="50" 
							class="easyui-validatebox textbox input155"  value='${merInfo.site}' />
					</td>
					<td style="text-align: right;">手续费序号：</td>
					<td>
						<input type="text" id="feeseq" name="feeseq" maxlength="18"
							   class="easyui-validatebox textbox input155"  value='${merInfo.feeseq}' />
					</td>
				</tr>
				<tr style="height: 40px;" >
				<td style="text-align: right;">POS终端数量：</td>
				<td>
					<input type="text" id="poscount" name="poscount"
						   maxlength="10" class="easyui-validatebox textbox input155" value="${merInfo.poscount }"/>
				</td>
				<td style="text-align: right;">商户描述：</td>
				<td>
					<input type="text" id="branchdesc" name="branchdesc"
						   maxlength="20" class="easyui-validatebox textbox input155" value="${merInfo.branchdesc }"/>
				</td>
			</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">区域公司ID：</td>
					<td>
						<input type="text" id="acompanyid" name="acompanyid" readonly="readonly" onclick="acompQuery()"
						  value="${merInfo.acompanyid}"	class="easyui-validatebox textbox input155"/>
					</td>
					<td style="text-align: right;">*商户状态：</td>
					<td colspan="3">
						<select id="branchstate" name="branchstate"  class="easyui-validatebox textbox input155" required="true">
							<option value="0" <c:if test="${merInfo.branchstate == '0' }"> selected='selected'</c:if>>启用</option>
							<option value="1" <c:if test="${merInfo.branchstate == '1' }"> selected='selected'</c:if>>停用</option>
						</select>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">商户电话：</td>
					<td>
						<input type="text" id="branchphone" name="branchphone"
							   maxlength="11" class="easyui-validatebox textbox input155" value="${merInfo.branchphone }"/>
					</td>
					<td style="text-align: right;">传真：</td>
					<td>
						<input type="text" id="fax" name="fax" maxlength="11"
							   class="easyui-validatebox textbox input155" value="${merInfo.fax }"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>