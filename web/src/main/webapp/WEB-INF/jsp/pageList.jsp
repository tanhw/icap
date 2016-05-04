<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body style="margin: 0px; padding: 0px;">
	<input type="hidden" id="basePath" value="<%=basePath%>" />
	<script>
	var pageGrid;
	pageGrid = $('#pageTable').datagrid({
			url:'${pageParam.url}${params }',singleSelect:true,
			toolbar:'#pageTable_toolbar',
			fit: true,
			nowrap: false,
			striped: true,
			collapsible:true,
			remoteSort: true,
			striped: true,
			pageSize:10,
			pagination:true,
			rownumbers:true,
				onLoadSuccess:function(){
					if (pageGrid)
						pageGrid.datagrid('clearSelections');
				}
		});


	
</script>
	<table id="pageTable">
		<thead>
			<tr>
				<c:if test="${pageParam.checkbox}">
					<th field="ck" checkbox="true"></th>
				</c:if>
				<c:forEach var="column" items="${pageColumn}">
					<th sortable="${column.isSort }"
						<c:if test="${column.formatter!=null}" >formatter="${column.formatter }"</c:if>
						field="${column.field }" width="${column.width/2 }"
						align="${column.align }"
						<c:if test="${column.styler!=null}" >styler="${column.styler }"</c:if>>${column.columnName }</th>
				</c:forEach>
			</tr>
		</thead>
	</table>

	<div id="pageTable_toolbar">
		<table>
			<tr>
				<td>
					<div>
						<c:import url="/${pageFiled}/pageCondition.html" charEncoding="utf-8"></c:import>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
