<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<script type="text/javascript">
$(document).ready(function(){
	
	$('#tableHtml input').blur(function(){
		if (!/^\d{4}$/.test($(this).val())) {
			top.$.messager.alert('提示信息', '输入的票价信息有错误，格式应为：四位纯数字！', 'info');
			$(this).val('');
		}
	});
	
	});

</script>

<ul>
<c:forEach begin="1" end="${tableSize }" varStatus="y">
<li><div style="width:32px;float:left; text-align: right;" >${y.index }</div><c:forEach begin="1" end="${y.index }" varStatus="x"><input type="text" class="easyui-validatebox textbox" id="_${y.index }${x.index }" size="2" maxlength="4" /></c:forEach></li>
</c:forEach>
</ul>

