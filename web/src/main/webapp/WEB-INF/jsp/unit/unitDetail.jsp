<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#unitid').blur(function(){
		if (!/^([1-9]\d[0-9]{6})$/.test($(this).val())) {
			$(this).val('');
		}
	});

	$('#unitid').change(function () {

		var unitid = $(this).val();
		if(!unitid){
			return
		}
		$.ajax({
			async:false,
			   type: "POST",
			   data:{
				   unitid:unitid
			   		},
			   		url: "unit/checkRepeat.html",
					   dataType: 'json',
					   success: function(data){
							if (!data.success){
								top.$.messager.alert('错误', data.message, 'info');
								$('#unitid').val("");
							}
					   }
		});

	});
	$('#unittele').blur(function(){
		isNumber($(this));
	});
	$('#unitfax').blur(function(){
		isNumber($(this));
	});
	$('#unitname').blur(function(){
		checkRegex($(this));
	});
	$('#unitdesc').blur(function(){
		checkRegex($(this));
	});
	$('#unitcontact').blur(function(){
		checkRegex($(this));
	});
	$('#unitaddr').blur(function(){
		checkRegex($(this));
	});

	//** 验证确认码
	$('#code').blur(function(){
		var code = $(this).val();
		var status = $(this).attr("readonly")
		if(!code || code.length != 36 || status == "readonly"){
			return ;
		}
		$.ajax({
			async:false,
			type: "POST",
			data:{
				code:code
			},
			url: "unit/checkCode.html",
			dataType: 'json',
			success: function(data){
				if (!data.success){
					top.$.messager.alert('错误', data.message, 'info');
					$('#code').val("");
				}else{
					$('#more').html("-- 确认码验证正确");
					$('#code').attr("readonly","readonly");
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
	var pattern = new RegExp("[`~!@#$^%-+_ &*()=|{}':;',\\[\\].<>/?~！@#￥……&* ——|{}【】‘；：”“'。，、？]");
	var rs = "";
	for (var i = 0; i < str.length; i++) {
		rs = rs + str.substr(i, 1).replace(pattern, '');
	}
	var id = obj.attr("id");
	if(id == "unitname"){
		$('#unitname').val(rs);
	}else if(id == "unitdesc"){
		$('#unitdesc').val(rs);
	}else if(id == "unitcontact"){
		$('#unitcontact').val(rs);
	}else{
		$('#unitaddr').val(rs);
	}
}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="unit/save.html">
			<input type="hidden" name="isModify" value="${isModify }" />
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<c:if test="${!isModify}">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*确认码：</td>
					<td>
						<input type="text" id="code" name="code" required="true" maxlength="36"
							validType="length[0,36]" invalidMessage="确认码有效数值必须为36位!"
							 class="easyui-validatebox textbox input155" />
					</td>
					<td style="text-align: left;color: red;" colspan="2" id="more"> -- 请联系统厂商获取新增机构确认码</td>
				</tr>
				</c:if>
				<tr style="height: 40px;" >
					<td style="text-align: right;">*机构ID：</td>
					<td>
						<input type="text" id="unitid" name="unitid" required="true" maxlength="8"
							<c:if test="${isModify}">readonly="readonly"</c:if>
							validType="length[0,8]" invalidMessage="机构ID有效数值必须为8位!"
							 class="easyui-validatebox textbox input155"  value='${unit.unitid}' />
					</td>
					<td style="text-align: left;color: red;" colspan="2">  -- 机构ID输入,首数字不能为0 --</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">*机构名称：</td>
					<td>
						<input type="text" id="unitname" name="unitname" required="true" maxlength="50" class="easyui-validatebox textbox input155"  value='${unit.unitname}' />
					</td>
					<td style="text-align: right;">机构描述：</td>
					<td>
						<input type="text" id="unitdesc" name="unitdesc"  maxlength="50"  value='${unit.unitdesc}' class="easyui-validatebox textbox input155" />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">联系人：</td>
					<td>
						<input type="text" id="unitcontact" name="unitcontact" maxlength="20"  value='${unit.unitcontact}' class="easyui-validatebox textbox input155" />
					</td>
					<td style="text-align: right;">地址：</td>
					<td>
						<input type="text" id="unitaddr" name="unitaddr" maxlength="50"  value='${unit.unitaddr}' class="easyui-validatebox textbox input155" />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">联系电话：</td>
					<td>
						<input type="text" id="unittele" name="unittele" maxlength="11"  value='${unit.unittele}'  class="easyui-validatebox textbox input155" />
					</td>
					<td style="text-align: right;">传真：</td>
					<td>
						<input type="text" id="unitfax" name="unitfax" maxlength="11"  value='${unit.unitfax}' class="easyui-validatebox textbox input155"  />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">邮箱：</td>
					<td>
							<input type="text" id="unitmail" name="unitmail" maxlength="20" data-options="validType:'email'" value='${unit.unitmail}' data-options="validType:'email'" class="easyui-validatebox textbox input155" />
					</td>
					<td style="text-align: right;">机构种类：</td>
					<td>
						<select id="unitkind" name="unitkind" class="easyui-validatebox textbox input155" <c:if test="${isModify}">disabled="disabled"</c:if>>
							<c:forEach items="${unitkindList }" var="list">
								<option value="${list.cvalue }" <c:if test="${unit.unitkind == list.cvalue}">selected="selected"</c:if> >${list.cdesc}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>