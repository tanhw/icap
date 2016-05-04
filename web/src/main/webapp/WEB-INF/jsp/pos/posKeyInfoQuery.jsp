<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
	$(function(){
		setQuery();
	});
	$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function() {

	$('#unitid').blur(function() {
		isNumber($(this));
	});
	$('#merseq').blur(function() {
		isNumber($(this));
	});
	$('#busid').blur(function() {
		isNumber($(this));
	});
	$('#posid').blur(function() {
		isNumber($(this));
	});
});

function isNumber(m){
	var value = m.val();
	if(!/^[\d| ]*$/.test(value)){
		m.val('');
	}
}
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<table border="0" cellpadding="0" cellspacing="0" id="ct">
			<tr style="height: 40px;" >
				<c:if test="${unitid == null }">
				<td style="text-align: right;">机构ID：</td>
				<td>
					<input type="text" id="unitid" name="unitid" maxlength="8" class="easyui-validatebox textbox input155" value="${unitid}"/>
				</td>
				</c:if>
				<td style="text-align: right;">商户序号：</td>
				<td>
					<input type="text" id="merseq" name="merseq" maxlength="8"  class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">终端编号：</td>
				<td>
					<input type="text" id="posid" name="posid" maxlength="8"  class="easyui-validatebox textbox input155"/>
				</td>
				<td style="text-align: right;">商户编号：</td>
				<td>
					<input type="text" id="branchid" name="branchid"  class="easyui-validatebox textbox input155"/>
				</td>
			</tr>
		</table>
	</div>
</div>