<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<script type="text/javascript">
</script>

<div id="cc" class="easyui-layout" fit='true'>   
<form id="fm" method="post" >
	<input type="hidden" name="unitid" value="${unitid }" />
    <div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<c:if test="${!isModify }">
				<ul id="tt" class="easyui-tree" data-options="url:'config/allConfBasic.html?unitid=${unitid }',cascadeCheck:true,animate:true,checkbox:true,"></ul>
		</c:if>
	</div>
</form>
</div>  

