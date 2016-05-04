<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="easyui-accordion" fit="true" border="false">
	<c:forEach var="menu1" items="${leftMenu}">
		<div title="${menu1.menuname }" style="overflow:auto;padding:5px;">
			<c:forEach var="menu2" items="${menu1.childMenu}">
					<p>
					<a class="easyui-linkbutton" plain="true" data-options="iconCls:'icon-leaf'" href="javascript:void(0)" onclick="addTab('${menu2.menuname }','${menu2.menucode }','${menu2.url }')">${menu2.menuname }</a>
					</p>
			</c:forEach>
		</div>
	</c:forEach>
</div>