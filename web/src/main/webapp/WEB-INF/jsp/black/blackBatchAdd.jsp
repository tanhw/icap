<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
	$("td:even").style = "text-align: left; ";
	$("td:odd").style = "text-align:right; width:30px";
</script>
<script type="text/javascript" src="js/ajaxfileupload.js">
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
			<table border="0" cellpadding="0" cellspacing="0" id="ct">
				<tr style="height: 40px;">
					<td style="text-align: right;">*选择批量文件：</td>
					<td style="width: 200px;">
						<input type="file" id="blacks" name="blacks" onchange="checkFormat('blacks')"
							class="easyui-validatebox input155" /></td>
					<td style="text-align: left; color: red;" colspan="2">*黑名单批量文件模板下载请 <a href="javascript:void(0);" onclick="download_file()">点击这里</a></td>
				</tr> 
				<tr style="height: 40px;">
					<td style="text-align: right;">增量标示：</td>
					<td>
						<select id="mark" name="mark" class="easyui-validatebox textbox input155">
							<option value="">请选择</option>
							<option value="0">有效</option>
							<option value="1">无效</option>
						</select>
					</td>
				</tr>
			</table>
	</div>
</div>