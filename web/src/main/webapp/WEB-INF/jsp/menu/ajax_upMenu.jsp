<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>


<c:if test="${fn:length(menuList) ==0}">
	<span>查询不到您需要的记录</span>
</c:if>
<c:if test="${fn:length(menuList) >0}">
	<select id="upmenu" class="easyui-validatebox textbox input155" name="upmenu" >
		<c:forEach items="${menuList}" var="menu">
			<option value="${menu.menucode }">--${menu.menuname }--</option>
		</c:forEach>
	</select>
</c:if>

