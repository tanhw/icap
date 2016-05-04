<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#uname').blur(function(){
		checkRegex($(this));
	});
	$('#paraName').blur(function(){
		checkRegex($(this));
	});
	$('#paraNo').blur(function(){
		checkRegex($(this));
	});
	$('#paraNo').blur(function(){
		isNumber($(this));
	});
});
function checkRegex(obj) {
	var str = obj.val();
	var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var rs = "";
	for (var i = 0; i < str.length; i++) {
		rs = rs + str.substr(i, 1).replace(pattern, '');
	}
	var id = obj.attr('id');
	if (id == 'uname') {
		$('#uname').val(rs);
	} else if (id == 'paraName') {
		$('#paraName').val(rs);
	} else {
		$('#paraNo').val(rs);
	}
}

function isNumber(m){
	var value = m.val();
	if(!/^[\d| ]*$/.test(value)){
		m.val('');
	}
}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="syspara/save.html">
			<input type="hidden" name="isModify" value="${isModify}">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*配置名称：</td>
					<td>
						<input type="text" id="uname" name="uname" required="true" maxlength="10"  class="easyui-validatebox textbox input155"
							<c:if test="${tblSysPara.uname != null}">readonly="readonly"</c:if> value="${tblSysPara.uname}"/>
					</td>
					<td style="text-align: right;">*参数名称：</td>
					<td>
						<input type="text" id="paraName" name="paraName" required="true" maxlength="20"   class="easyui-validatebox textbox input155"
							<c:if test="${tblSysPara.paraName != null}">readonly="readonly"</c:if> value="${tblSysPara.paraName}"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">*参数编号：</td>
					<td>
						<input type="text" id="paraNo" name="paraNo" required="true" maxlength="2"  class="easyui-validatebox textbox input155"
							<c:if test="${tblSysPara.paraNo != null}">readonly="readonly"</c:if>value="${tblSysPara.paraNo}"/>
					</td>
					<td style="text-align: right;">*参数值：</td>
					<td>
						<input type="text" id="paraValue" name="paraValue" required="true"  class="easyui-validatebox textbox input155" value="${tblSysPara.paraValue}"/>
					</td>
				</tr>
				<tr style="height: 40px;" >
                            <td style="text-align: right;">*参数描述：</td>
                            <td>
                                <input type="text" id="paraDesc" name="paraDesc" required="true"  class="easyui-validatebox textbox input155" value="${tblSysPara.paraDesc}"/>
                            </td>
                            <td style="text-align: right;">*记录状态：</td>
                            <td>
                                <select id="recordStat" name="recordStat" required="true"  class="easyui-validatebox textbox input155">
                                    <option value="0" <c:if test="${tblSysPara.recordStat == '0'}">selected="selected"</c:if>>未记录</option>
                                    <option value="1" <c:if test="${tblSysPara.recordStat == '1'}">selected="selected"</c:if>>已记录</option>
                                </select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>