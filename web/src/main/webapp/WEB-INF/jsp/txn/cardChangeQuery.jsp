<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<script>
	$(function(){
		setQuery();
	});
	$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
	<form id="fm" method="post" action ="buscardchange/expCardChangeBase.html">
		<table border="0" cellpadding="0" cellspacing="0" id="ct">
			<tr style="height: 40px;" >
				<c:if test="${unitid == null}">
				<td style="text-align: right;">机构号：</td>
				<td>
					<input type="text" id="unitid" name="unitid" maxlength="8" onclick="unitQuery()" class="easyui-validatebox textbox input155" />
				</td>
				</c:if>
				<td style="text-align: right;">商户号：</td>
				<td>
					<input type="text" id="merseq" name="merseq" maxlength="8" onclick="merQuery()" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">原卡号：</td>
				<td>
					<input type="text" id="oldcardno" name="oldcardno" maxlength="19" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">新卡号：</td>
				<td>
					<input type="text" id="newcardno" name="newcardno" maxlength="19" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">银行编号：</td>
				<td>
					<input type="text" id="bankid" name="bankid" maxlength="8" onclick="bankQuery()" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">商户编号：</td>
				<td>
					<input type="text" id="merid" name="merid" maxlength="15" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">网点编号：</td>
				<td>
					<input type="text" id="netid" name="netid" maxlength="8" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">有效期：</td>
				<td>
					<input type="text" id="crdexpdate" name="crdexpdate" maxlength="8" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">卡片种类：</td>
				<td>
					<select id="crdkind" name="crdkind" maxlength="4" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<c:forEach items="${crdkindList }" var="item">
							<option value="${item.cvalue}">${item.cdesc}</option>
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right;">清算状态：</td>
				<td>
					<select id="settlestat" name="settlestat" maxlength="2" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<c:forEach items="${settlestatList }" var="item">
							<option value="${item.cvalue}">${item.cdesc}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">开始时间:</td>
				<td> 
					<input type="text" id="startTime"	name="startTime"  required="true" editable="false"
					    value="${startTime }" class="easyui-datetimebox textbox input155" formatter="formatterDate" parser="parserDate" />
				</td>
				<td style="text-align: right;">结束时间：</td>
				<td>
					<input type="text" id="endTime"	name="endTime"  required="true" editable="false"
						value="${endTime }"
						 class="easyui-datetimebox textbox input155" formatter="formatterDate" parser="parserDate"  />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">证件类型：</td>
				<td>
					<select id="papertype" name="papertype" maxlength="1" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<c:forEach items="${papertypeList }" var="item">
							<option value="${item.cvalue}">${item.cdesc}</option>
						</c:forEach>
					</select>
				</td>
				<td style="text-align: right;">性别：</td>
				<td>
					<select id="sex" name="sex" maxlength="1" class="easyui-validatebox textbox input155" >
						<option value="">请选择</option>
						<c:forEach items="${sexList }" var="item">
							<option value="${item.cvalue}">${item.cdesc}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">证件号：</td>
				<td>
					<input type="text" id="paperid" name="paperid" maxlength="20" class="easyui-validatebox textbox input155" />
				</td>
				<td style="text-align: right;">姓名：</td>
				<td>
					<input type="text" id="name" name="name" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">城市代码：</td>
				<td>
					<input type="text" id="citycode" name="citycode" maxlength="4" class="easyui-validatebox textbox input155" />
				</td>
			</tr>
		</table>
	</form>
	</div>
</div>