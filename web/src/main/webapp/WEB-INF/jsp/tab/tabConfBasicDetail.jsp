<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
$("td:even").style="text-align: left; ";
$("td:odd").style="text-align:right; width:30px";
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<form id="fm" method="post" action ="config/save.html">
			<input type="hidden" name="isModify" value="${isModify }" />
			<input type="hidden" name="confid" value='${tTabCofBasic.confid}'/>
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;" >
				<td style="text-align: right;">*配置名称：</td>
				<td>
					<input type="text" id="confname" name="confname" maxlength="20" required="true" class="easyui-validatebox textbox input155"  value="${tTabCofBasic.confname }" /> 
				</td>
				<td style="text-align: right;">*业务类型：</td>
				<td>
					<select id="busi" name="busi" class="easyui-validatebox textbox input155">
						<option value="">请选择</option>
						<c:forEach items="${busiList }" var="list">
							<option value="${list.cvalue }" <c:if test="${tTabCofBasic.busi == list.cvalue}">selected="selected"</c:if> >${list.cdesc}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">*标题：</td>
				<td colspan="3" style="padding-top: 10px;">
					<textarea id="title" name="title" rows="4" cols="60"   style="height:60px; width: 380px;" class="textarea easyui-validatebox">${tTabCofBasic.title}</textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">*字段栏：</td>
				<td colspan="3" style="padding-top: 10px;">
					<textarea id="filed" name="filed" rows="1" cols="60"   style="height:60px; width: 380px;" class="textarea easyui-validatebox">${tTabCofBasic.filed}</textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">导出标题：</td>
				<td colspan="3" style="padding-top: 10px;">
					<textarea id="exptitle" name="exptitle" rows="4" cols="60"   style="height:60px; width: 380px;" class="textarea easyui-validatebox">${tTabCofBasic.exptitle}</textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">*字段栏名称：</td>
				<td colspan="3" style="padding-top: 10px;">
					<textarea id="fileddesc" name="fileddesc" rows="1" cols="60"   style="height:60px; width: 380px;" class="textarea easyui-validatebox">${tTabCofBasic.fileddesc}</textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">*统计字段栏：</td>
				<td colspan="3" style="padding-top: 10px;">
					<textarea id="collectfiled" name="collectfiled" rows="1" cols="60"   style="height:60px; width: 380px;" class="textarea easyui-validatebox">${tTabCofBasic.collectfiled}</textarea>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">*SQL：</td>
				<td colspan="3" style="padding-top: 10px;">
					<input class="easyui-textbox" id="sql" name="sql" data-options="multiline:true" style="height:60px; width: 380px;" value="${tTabCofBasic.sql}"></input>
				</td>
			</tr>
			<tr style="height: 40px;" >
				<td style="text-align: right;">*是否有效：</td>
				<td>
					<select class="easyui-validatebox textbox input155" name="isactive" id="isactive">
						<option value="0" <c:if test="${tTabCofBasic.isactive == '0' }">selected="selected"</c:if> >有效</option>
						<option value="1" <c:if test="${tTabCofBasic.isactive == '1' }">selected="selected"</c:if> >无效</option>
					</select>
				</td>
			</tr>
			</table>
		</form>
	</div>
</div>