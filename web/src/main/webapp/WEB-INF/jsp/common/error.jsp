<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<style type="text/css">
 body{
  margin:0;
  padding:0;
  font-size:12px;
 }
.errorBox{
  padding:20px;
  background:url(images/errorbg.png) no-repeat 0 0;
  height:450px;
  width:800px;
  position:absolute;
  top:50%;
  left:50%;
  margin:-225px 0 0 -400px;
}
.errorfont{ padding:70px 0 0 220px;width:400px;}
.errorfont p{
 line-height:30px;
 font-size:15px;
 font-family:"微软雅黑";
 text-align:left;

}
</style>
</head>
<body style="margin: 0px;padding: 0px;">
<div class="errorBox">
  <div class="errorfont">
    <p style="font-size:18px; color:#F00;">${ex }</p>
	<p>${errorMsg }</p>
	<!-- <p>异常具体描述：${excptionInfoDev }</p> -->
  </div>
</div>	
</body>
</html>
