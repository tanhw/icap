<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

$(document).ready(function(){
	$('#posid').blur(function(){
		isNumber($(this));
	});
	$('#maxmoney').blur(function(){
		isNumber($(this));
	});
	$('#totalmoney').blur(function(){
		isNumber($(this));
	});
	$('#physicsno').blur(function(){
		isNumber($(this));
	});
	$('#samid').blur(function(){
		isNumber($(this));
	});
	$('#termseq').blur(function(){
		isNumber($(this));
	});

	$('#busid').blur(function(){
		if (!/^[0-9]*$/.test($(this).val())) {
			$(this).val('');
		}
	});
	
	$('#posid').change(function (){
		
	var posid = $(this).val();
			if (!posid) {
				return;
			}
			$.ajax({
				async:false,
				   type: "POST",
				   data:{
					   posid:posid
				   		},
				   		url: "posinfo/checkRepeat.html",
						   dataType: 'json',
						   success: function(data){
								if (!data.success){
									top.$.messager.alert('错误', data.message, 'info');
									$('#posid').val("");
								}
						   }
			});
		});

		changeTable($(this).val(), $('.busid'))

		$('#busiid').change(function() {
			changeTable($(this).val(), $('.busid'))
		});

	});
function isNumber(m){
	var value = m.val();
	if(!/^[\d]*$/.test(value)){
		m.val('');
	}
}

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="posinfo/save.html">
			<input type="hidden" name="isModify" value="${isModify }">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
					<td style="text-align: right;">*业务类型：</td>
					<td>
						<select id="busiid" name="busiid" class="easyui-validatebox textbox input155" required="true">
							<c:forEach items="${busiidList }" var="list">
								<option value="${list.cvalue }" <c:if test="${tPosInfo.busiid == list.cvalue}">selected="selected"</c:if> >${list.cdesc }</option>
							</c:forEach>
						</select>
					</td>
					<td style="text-align: right;">*POS类别：</td>
					<td>
						<select id="postype" name="postype" class="easyui-validatebox textbox input155" required="true">
							<c:forEach items="${postypeList }" var="list">
								<option value="${list.cvalue }" <c:if test="${tPosInfo.postype == list.cvalue}">selected="selected"</c:if> >${list.cdesc }</option>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr style="height: 40px;" >
					<td style="text-align: right;">*POS机ID：</td>
					<td>
						<input type="text" id="posid" name="posid" required="true" maxlength="8" validType="length[8,8]" invalidMessage="POSID为8位有效数字!"
							 <c:if test="${tPosInfo.posid != null }">readonly="readonly"</c:if>
							 class="easyui-validatebox textbox input155"  value='${tPosInfo.posid}' />
					</td>
					<td style="text-align: right;">*POS状态：</td>
					<td>
						<select id="status" name="status" class="easyui-validatebox textbox input155" required="true">
							<c:forEach items="${posstatusList }" var="list">
								<option value="${list.cvalue }" <c:if test="${tPosInfo.status == list.cvalue}">selected="selected"</c:if> >${list.cdesc }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">*商户序号：</td>
					<td>
						<input type="text" id="merseq" name="merseq" required="true" readonly="readonly" <c:if test="${tPosInfo.merseq == null }"> onclick="merQuery()"</c:if>
							maxlength="20" class="easyui-validatebox textbox input155" value="${tPosInfo.merseq }"/>
					</td>
					<td style="text-align: right;">*商户编号：</td>
					<td>
						<input type="text" id="branchid" name="branchid" required="true" maxlength="50" readonly="readonly"
							 class="easyui-validatebox textbox input155"  value='${tPosInfo.branchid}' />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td  style="text-align: right;">车辆编号:</td>
					<td>
						<input type="text" id="busid" name="busid"  maxlength="8" validType="length[8,8]" invalidMessage="busid为8位有效数字!"
							 readonly="readonly" onclick="busQuery()" 
							 class="easyui-validatebox textbox input155"  value='${tPosInfo.busid}' />
					</td>
					<td style="text-align: right;">*POS品牌：</td>
					<td>
						<select id="posbrand" name="posbrand" class="easyui-validatebox textbox input155" required="true">
							<c:forEach items="${posbrandList }" var="list">
								<option value="${list.cvalue }" <c:if test="${tPosInfo.posbrand == list.cvalue}">selected="selected"</c:if> >${list.cdesc }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">单笔交易最大金额：</td>
					<td>
						<input type="text" id="maxmoney" name="maxmoney" maxlength="12" 
							 class="easyui-validatebox textbox input155" value="${tPosInfo.maxmoney }"/>
					</td>
					<td style="text-align: right;">累积交易最大金额：</td>
					<td>
						<input type="text" id="totalmoney" name="totalmoney"  maxlength="12" 
							 class="easyui-validatebox textbox input155"  value='${tPosInfo.totalmoney}' />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">POS终端序列号：</td>
					<td>
						<input type="text" id="termseq" name="termseq"  maxlength="25"
							 class="easyui-validatebox textbox input155"  value='${tPosInfo.termseq}' />
					</td>
					<td  style="text-align: right;">设备物理编号:</td>
					<td>
						<input type="text" id="physicsno" name="physicsno"  maxlength="20" 
							 class="easyui-validatebox textbox input155"  value='${tPosInfo.physicsno}' />
					</td>
				</tr>
				<tr style="height: 40px;" >
					<td style="text-align: right;">SAM卡号：</td>
					<td>
						<input type="text" id="samid" name="samid" maxlength="16" 
							class="easyui-validatebox textbox input155" value="${tPosInfo.samid }"/>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>