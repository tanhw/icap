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
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="common/headCommon.jsp"%>

<script type="text/javascript" src="appJs/base.js"></script>
<c:forEach var="importJs" items="${importJs}">
	<script type="text/javascript" src="${importJs }"></script>
</c:forEach>
<script>
var grid;



$(function(){
	grid= $('#listTable').datagrid({
		border:false,
		fit: true,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'${listParam.url}',
		sortName: '${listParam.sortName}',
		sortOrder: '${listParam.sortOrder}',
		remoteSort: true,
		toolbar:'#listTable_toolbar',
		pageSize:20,
		pagination:true,
		rownumbers:true,
		onDblClickRow:function(rowIndex, rowData){
			grid.datagrid('clearSelections');
			grid.datagrid('selectRow',rowIndex);
			defaultDblClick();
		},
		onLoadSuccess:function(){
			if (grid)
				grid.datagrid('clearSelections');
		}
	});

});

</script>
</head>
<body style="margin: 0px; padding: 0px;">
	<input type="hidden" id="basePath" value="<%=basePath%>" />
	<table id="listTable">
		<thead>
			<tr>
				<c:if test="${listParam.checkbox}">
					<th field="ck" checkbox="true"></th>
				</c:if>
				<c:forEach var="column" items="${showColumn}">
					<th sortable="${column.isSort }"
						<c:if test="${column.formatter!=null}" >formatter="${column.formatter }"</c:if>
						field="${column.field }" width="${column.width }"
						align="${column.align }"
						<c:if test="${column.styler!=null}" >styler="${column.styler }"</c:if>>${column.columnName }</th>
				</c:forEach>
			</tr>
		</thead>
	</table>
	<div id="detailPanel" style="padding: 0px; background: #fafafa;"
		class="easyui-dialog" closed="true"></div>
	
	<div id="pagePanel" style="padding: 0px; background: #fafafa;"
		class="easyui-dialog" closed="true"></div>

	<div id="listTable_toolbar">
		<c:forEach var="cMenu" items="${childMenu}" varStatus="statMenu">
			<c:if test="${!statMenu.first}">
				<div class="datagrid-btn-separator"></div>
			</c:if>
			<a id="m_${cMenu.menucode}" style="float: left;"
				class="easyui-linkbutton" data-options="iconCls:'${cMenu.icon}'" plain="true"
				onclick="${cMenu.url}()">${cMenu.menuname}</a>
		</c:forEach>
	</div>
</body>
</html>
