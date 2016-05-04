	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="css/public.css" />
	<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css" />
	<link rel = "demo Icon" href="images/demo.ico">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js" ></script>
	<script type="text/javascript" src="js/public.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="js/verifyCode.js"></script>
	<script type="text/javascript" src="js/password/rsaEnc.js"></script>

	<%@ page import="com.core.password.RSAUtils"%>
	<%
	    String rsaPublicKeyN = RSAUtils.getPublicKeys(1)[0];
	    String rsaPublicKeyE = RSAUtils.getPublicKeys(1)[1];
	%>
	<script type="text/javascript">
		var rsaPublicKeyN = "<%=rsaPublicKeyN%>";
		var rsaPublicKeyE = "<%=rsaPublicKeyE%>";
		var rsaIndex = 1;	
	</script>