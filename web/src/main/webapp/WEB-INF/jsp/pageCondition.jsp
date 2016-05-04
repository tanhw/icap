<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div>

	<c:forEach var="fields" items="${conditionFields}">
		<c:if test="${fields.text}">
			${fields.word}<input type="text" id="${fields.name}" name="${fields.name}" class="easyui-textbox" style="width: 80px">
		</c:if>
		<c:if test="${fields.time}">
			${fields.word}<input type="text" id="${fields.name}" name="${fields.name}"  class="easyui-datebox" style="width: 80px">
		</c:if>
		<c:if test="${fields.combo}">
			${fields.word}：
			<select id="${fields.name}" name="${fields.name}" class="easyui-combobox" panelHeight="auto"
				style="width: 80px">
				<c:forEach var="listData" items="${fields.list}" varStatus="vs">
					<option value="${listData.value}">${listData.name}</option>
				</c:forEach>
			</select>
		</c:if>
	</c:forEach>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="pageSubmit()">查询>></a>
</div>