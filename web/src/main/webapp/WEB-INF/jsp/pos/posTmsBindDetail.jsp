<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#merseq').blur(function(){
		if (!/^[0-9]*$/.test($(this).val())) {
			$(this).val('');
		}
	});
	
});

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="posTmsBind/save.html">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*TMS程序：</td>
					<td >
						<input type="text" id="filename" name="filename" required="true" maxlength="50" readonly="readonly" onclick="tmsdataQuery()"  
						 class="easyui-validatebox textbox input155"  />
					</td>
					<td style="text-align: right;">*POS品牌：</td>
					<td>
						<select id="posbrand" name="posbrand" class="easyui-validatebox textbox input155" draggable="false">
							<option value="">请选择POS品牌</option>
							<c:forEach items="${posbrandList }" var="list">
								<option value="${list.cvalue }">${list.cdesc }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">*POS类别：</td>
					<td>
						<select id="postype" name="postype" class="easyui-validatebox textbox input155" required="true">
							<c:forEach items="${postypeList }" var="list">
								<option value="${list.cvalue }" >${list.cdesc }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">商户序号：</td>
					<td>
						<input type="text" id="merseq" name="merseq" readonly="readonly" onclick="merQuery()"
							maxlength="8" class="easyui-validatebox textbox input155"/>
					</td>
					<td style="text-align: right;">商户编号：</td>
					<td >
						<input type="text" id="branchid" name="branchid" maxlength="15" readonly="readonly"
							 class="easyui-validatebox textbox input155"  />
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>