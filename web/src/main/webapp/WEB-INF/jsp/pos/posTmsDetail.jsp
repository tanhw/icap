<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#posid').blur(function(){
		if (!/^[0-9]*$/.test($(this).val())) {
			$(this).val('');
		}
	});
	
	$('#busid').blur(function(){
		if (!/^[0-9]*$/.test($(this).val())) {
			$(this).val('');
		}
	});
	
});

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="posinfo/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				
				
				
				<tr style="height: 40px;" >
					<td style="text-align: right;">*功能描述：</td>
					<td >
						<input type="text" id="filefunc" name="filefunc" required="true" maxlength="50" 
						 class="easyui-validatebox textbox input155"  />
					</td>
					<td style="text-align: right;">*POS类别：</td>
					<td>
						<select id="postype" id="postype" name="postype" class="easyui-validatebox textbox input155" required="true">
							<c:forEach items="${postypeList }" var="list">
								<option value="${list.cvalue }" <c:if test="${tPosInfo.postype == list.cvalue}">selected="selected"</c:if> >${list.cdesc }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">*pos品牌：</td>
					<td colspan="3">
					   <select id="posbrand" name="posbrand" class="easyui-validatebox textbox input155" required="true">
							<c:forEach items="${posbrandList }" var="list">
								<option value="${list.cvalue }" <c:if test="${tPosInfo.posbrand == list.cvalue}">selected="selected"</c:if> >${list.cdesc }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">商户序号：</td>
					<td>
						<input type="text" id="merseq" name="merseq" readonly="readonly" onclick="merQuery()"
							<c:if test="${tPosInfo.merseq != null }">disabled="disabled"</c:if>
							maxlength="20" class="easyui-validatebox textbox input155"/>
					</td>
					<td style="text-align: right;">商户编号：</td>
					<td >
						<input type="text" id="branchid" name="branchid" readonly="readonly" maxlength="50"
							 class="easyui-validatebox textbox input155"  />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">*文件：</td>
					<td colspan="3">
					    <input type="file" id="catchfile" name="catchfile" />
					</td>
				</tr>
				
				<tr>
					<td style="text-align: right;">注意文件名规范：</td>
					<td colspan="3" style="color: red;">xx：12位大写英文或数字下划线；yyyyMMdd:年月日； n:数字序号； </td>
				</tr>
				<tr>
					<td style="text-align: right;">示例：</td>
					<td colspan="3" style="color: red;">xxxxxxxxxxxxyyyyMMddn.bin； </td>
				</tr>
				
			</table>
		</form>
	</div>
</div>