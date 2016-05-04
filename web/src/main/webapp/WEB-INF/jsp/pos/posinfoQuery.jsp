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
				<td style="text-align: right;">商户序号：</td>
				<td>
					<input type="text" id="merseq" name="merseq" maxlength="8" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">商户编号：</td>
				<td>
					<input type="text" id="branchid" name="branchid"  class="easyui-validatebox textbox input155"/>
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">SAM卡号：</td>
				<td>
					<input type="text" id="samid" name="samid" maxlength="16" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">车辆编号：</td>
				<td>
					<input type="text" id="busid" name="busid" maxlength="8" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">POS当前批次号：</td>
				<td>
					<input type="text" id="batchno" name="batchno" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">POS终端序列号：</td>
				<td>
					<input type="text" id="termseq" name="termseq" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">POS机ID：</td>
				<td>
					<input type="text" id="posid" name="posid" maxlength="8"  class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">POS品牌：</td>
				<td>
					<select id="posbrand" name="posbrand" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<c:forEach items="${posbrandList}" var="item">
							<option value="${item.cvalue}">${item.cdesc}</option>
						</c:forEach>
					</select>
			</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">POS类别：</td>
				<td>
					<select id="postype" name="postype" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<c:forEach items="${postypeList}" var="item">
							<option value="${item.cvalue}">${item.cdesc}</option>
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right;">POS状态：</td>
				<td>
					<select id="status" name="status" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<c:forEach items="${posstatusList}" var="item">
							<option value="${item.cvalue}">${item.cdesc}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
				<tr style="height: 40px;" >
				<td  style="text-align: right;">主密钥下载标志:</td>
				<td>
					<select id="tmkdownflag" name="tmkdownflag" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<option value="0">已下载</option>
						<option value="1">未下载</option>
					</select>
				</td>
				<td style="text-align: right;">KEYA密钥下载标志：</td>
				<td>
					<select id="keyadownflag" name="keyadownflag" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<option value="1">未下载</option>
						<option value="0">已下载</option>
					</select>
				</td>
			</tr>
			<c:if test="${unitid == null }">
			<tr style="height: 40px;" >
				<td style="text-align: right;">机构ID：</td>
				<td>
					<input type="text" id="unitid" name="unitid" maxlength="8" class="easyui-validatebox textbox input155"/>
				</td>
			</tr>
			</c:if>
		</table>
	</div>
</div>