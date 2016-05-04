<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<script>
$(function(){
	
	destroy=function(){
		$.messager.confirm("提示信息", "您要退出系统 确定吗？", function(r){
			if (r){
				var url=pageUrl("logout.html","timeTemp",(new Date()).valueOf());
				$.post(url,function(theback){
					if (!theback.success)
						top.$.messager.alert('错误', theback.message, 'error');
					else{
						top.location.href="login.html";
					}
				},"json");
				
			}
		});
	};
	
	
	$('#menu1').menubutton({menu:'#menu1c'});  
	$('#menu2').menubutton({menu:'#menu2c'});
	
});

//修改密码
function setMyPassword(){
	
	var myh =(window.screen.height-330)/2;  
	var myw = (window.screen.width-330)/2;
	var url="myPassword.html";
	url=pageUrl(url,"timeTemp",(new Date()).valueOf());
	$('#indexPanel').window({
		title: "密码修改",
		iconCls:"icon-edit",
		width: 280,
		height:230,
		closed: true,
		resizable:false,
		minimizable:false,
		maximizable:false,
		collapsible:false,
		modal:true,
		top:myh,
		left:myw,
		href:url
	}).window('open').window("refresh");
}

//查看信息
function getMyInfo(){
	
	var myh =(window.screen.height-430)/2;  
	var myw = (window.screen.width-330)/2;
	var url="myInfo.html";
	url=pageUrl(url,"timeTemp",(new Date()).valueOf());
	$('#indexPanel').window({
		title: "用户信息",
		iconCls:"icon-man",
		width: 600,
		height:330,
		closed: true,
		resizable:false,
		minimizable:false,
		maximizable:false,
		collapsible:false,
		modal:true,
		top:myh,
		left:myw,
		href:url
	}).window('open').window("refresh");
}

</script>

<table cellspacing="0" cellpadding="0" width="100%" height="100%" border="0" style="background-image: url('images/top.jpg');">
	<tr>
		<td valign="middle" width="250" style="font-size: 20px;padding-left:10px;border: 1px;font-family: microsoft Yahei;color: white;" align="left" > ${systemTitle }</td>
		<td align="right" valign="bottom"  >
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td style="font-family: microsoft Yahei;color: white;">当前操作员：</td>
					<td><div >  
							<a href="javascript:void(0)" id="menu1"  data-options="iconCls:'icon-user'" style="font-family: microsoft Yahei;color: white;">${realName }
								<div id="menu1c" style="width:100px;">  
									<div data-options="iconCls:'icon-edit'" onclick="setMyPassword()">修改密码</div>  
									<div data-options="iconCls:'icon-redo'" onclick="getMyInfo()">查看信息</div>  
								</div>
							</a>  
							<a href="javascript:void(0)" id="menu2" data-options="iconCls:'icon-leaver'" style="font-family: microsoft Yahei;color: white;" onclick="destroy()">注销
							</a> 
						</div>
					</td>
				</tr>
			</table>
		</td>
		</tr>
</table>
