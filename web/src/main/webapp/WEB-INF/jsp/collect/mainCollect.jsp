<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%@include file="../common/headCommon.jsp" %>

</head>
<script>
    $(document).ready(function(){
        $('ul a').click(function(){

            $('#cc').layout('add',{
                region: 'center',
                title: '报表'
            });
            $('#cc').layout('add',{
                region: 'north',
                height: 200,
                title: '报表条件'
            });

            var id = $(this).attr('id');

            $('#cc').layout('panel','center').panel({href:'collect/tableCollect.html?id=' + id});
            $('#cc').layout('panel','north').panel({href:'collect/whereCollect.html?id=' + id});

        });
    });

</script>
<body style="margin: 0px; padding: 0px;">

    <div class="easyui-layout" fit="true">
        <div data-options="region:'center'" border="false">
            <div id="cc" class="easyui-layout" fit="true"></div>
        </div>
        <div data-options="region:'west',title:'选择报表',split:true" style="width:200px; text-align: center;">
            <c:if test="${tabCofList == null}">
                <h1 style="margin-top: 100px;">系统管理员无权查询报表</h1>
            </c:if>
            <ul style="list-style: none;">
                <c:forEach items="${tabCofList}" var="list">
                <li style="margin-top: 20px;">
                    <a class="easyui-linkbutton" id="${list.confid }" data-options="iconCls:'icon-search'">${list.confname}</a>
                </li>
                </c:forEach>
            </ul>

        </div>
    </div>

</body>
</html>
