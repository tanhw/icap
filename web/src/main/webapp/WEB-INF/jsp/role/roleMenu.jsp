<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<div class="easyui-layout" >
	<ul id="tt" class="easyui-tree" data-options="url:'role/roleidAllotMenu.html?roleseq=${roleid }',cascadeCheck:true,animate:true,checkbox:true," ></ul>
</div>
<input type="hidden" id="roleId" value="${roleid }"/>
