var fixWidth=200;
var fixHeight=63;
var queryTimeTemp=(new Date()).valueOf();
var queryParam={};
var urlRef=false;
var submitFlat = false;

function addBase(title,icon,width,height,url,fun){
	var _fun=saveSubmit;
	if(fun){
		_fun=fun;
	}
	var myw =($(window).width()-fixWidth-width)/2;
	var myh = ($(window).height()-fixHeight-height)/2;
	url=pageUrl(url,"timeTemp",(new Date()).valueOf());
	$('#detailPanel').dialog({
		title: title,
		iconCls:icon,
		width: width,
		height:height,
		closed: true,
		top:myh,
		left:myw,
		href:url,
		buttons:[{
			text:'保存',
			iconCls:'icon-ok',
			handler:_fun
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:winClose
		}]
	}).dialog('open');
	if (!urlRef){
		urlRef=true;
	}
	else
		$('#detailPanel').dialog("refresh");
}

function modifyBase(title,icon,width,height,url,idField,gridTab,fun){
	var _fun=saveSubmit;
	if(fun)_fun=fun;
	
	var myw =($(window).width()-fixWidth-width)/2;  
	var myh = ($(window).height()-fixHeight-height)/2;
	var _grid=grid;
	var rows;
	if (gridTab)_grid=gridTab;
	
	rows = _grid.datagrid('getSelections');
	var num = rows.length;	
	
	if (num == 0) {
		top.$.messager.alert('提示信息', '请选择一条记录进行操作!', 'info');
		return;
	}
	else if (num > 1) {
		top.$.messager.alert('提示信息', '只能选择一条记录进行操作!', 'info');
		return;
	}
	else{
		url=pageUrl(url,"id",eval("rows[0]."+idField));
		url=pageUrl(url,"timeTemp",(new Date()).valueOf());
		$('#detailPanel').dialog({
			title: title,
			iconCls:icon,
			width: width,
			height:height,
			closed: true,
			top:myh+40,
			left:myw,
			href:url,
			buttons:[{
				text:'保存',
				iconCls:'icon-ok',
				handler:_fun
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:winClose
			}]
		}).dialog('open');
		if (!urlRef){
			urlRef=true;
		}
		else
			$('#detailPanel').dialog("refresh");
	}
}

function bindBase(title,icon,width,height,url,idField,gridTab,fun){
	var _fun=saveSubmit;
	if(fun)_fun=fun;
	var myw =($(window).width()-fixWidth-width)/2;  
	var myh = ($(window).height()-fixHeight-height)/2;
	var _grid=grid;
	var rows;
	if (gridTab)_grid=gridTab;
	
	rows = _grid.datagrid('getSelections');
	var num = rows.length;	
	
	if (num == 0) {
		top.$.messager.alert('提示信息', '请选择一条记录进行操作!', 'info');
		return;
	}
	else if (num > 1) {
		top.$.messager.alert('提示信息', '只能选择一条记录进行操作!', 'info');
		return;
	}
	else{
		url=pageUrl(url,"id",eval("rows[0]."+idField));
		url=pageUrl(url,"timeTemp",(new Date()).valueOf());
		$('#detailPanel').dialog({
			title: title,
			iconCls:icon,
			width: width,
			height:height,
			closed: true,
			top:myh,
			left:myw,
			href:url,
			buttons:[{
				text:'保存',
				iconCls:'icon-ok',
				handler:_fun
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:winClose
			}]
		}).dialog('open');
		if (!urlRef){
			urlRef=true;
		}
		else
			$('#detailPanel').dialog("refresh");
	}
}




function showBase(title,icon,width,height,url,idField,gridTab){
	var myw =($(window).width()-fixWidth-width)/2;  
	var myh = ($(window).height()-fixHeight-height)/2;
	var _grid=grid;
	var rows;
	if (gridTab)_grid=gridTab;
	
	rows = _grid.datagrid('getSelections');
	var num = rows.length;	
	
	if (num == 0) {
		top.$.messager.alert('提示信息', '请选择一条记录进行操作!', 'info');
		return;
	}
	else if (num > 1) {
		top.$.messager.alert('提示信息', '只能选择一条记录进行操作!', 'info');
		return;
	}
	else{
		url=pageUrl(url,"id",eval("rows[0]."+idField));
		url=pageUrl(url,"timeTemp",(new Date()).valueOf());
		$('#detailPanel').dialog({
			title: title,
			iconCls:icon,
			width: width,
			height:height,
			closed: true,
			top:myh,
			left:myw,
			//content:createDialogFrame(url),
			href:url,
			buttons:[{
				text:'关闭',
				iconCls:'icon-cancel',
				handler:winClose
			}]
		}).dialog('open');
		if (!urlRef){
			urlRef=true;
		}
		else
			$('#detailPanel').dialog("refresh");
	}
}

function modifyTreeBase(title,icon,width,height,url,idField,gridTab){
	var myw =($(window).width()-fixWidth-width)/2;  
	var myh = ($(window).height()-fixHeight-height)/2;
	var _grid=grid;
	var rows;
	if (gridTab)_grid=gridTab;
	
	rows = _grid.treegrid('getSelected');
	
	url=pageUrl(url,"id",eval("rows."+idField));
	url=pageUrl(url,"timeTemp",(new Date()).valueOf());
	$('#detailPanel').dialog({
		title: title,
		iconCls:icon,
		width: width,
		height:height,
		closed: true,
		top:myh,
		left:myw,
		href:url,
		buttons:[{
			text:'保存',
			iconCls:'icon-ok',
			handler:saveSubmit
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:winClose
		}]
	}).dialog('open');
	if (!urlRef){
		urlRef=true;
	}
	else
		$('#detailPanel').dialog("refresh");
}


function executeSingleBase(url,msg,idField){
	var rows=grid.datagrid('getSelections');
	var num=rows.length;
	if (num==0){
		top.$.messager.alert('提示信息','请选择一条记录进行操作!','info');
		return;
	}else if (num > 1) {
		top.$.messager.alert('提示信息', '只能选择一条记录进行操作!', 'info');
		return;
	}
	else{
		top.$.messager.confirm('提示信息', msg, function(r){
			if (r){
				url=pageUrl(url,"id",eval("rows[0]."+idField));
				url=pageUrl(url,"timeTemp",(new Date()).valueOf());
				if(submitFlat) {
					top.$.messager.alert('提示信息', '请勿重复提交！', 'info');
					return;
				}
				submitFlat = true;
				$.post(url,function(theback){
					if (!theback.success)
						top.$.messager.alert('错误', theback.message, 'error',function(){
							if (theback.url){
								top.location.href=theback.url;
							}
						});
					else{
						top.$.messager.alert('提示信息', theback.message, 'info',function(){
							grid.datagrid('reload');
						});
					}
					submitFlat = false;
				},"json");
				
			}
		});
	}
}

function executeBase(url,msg,idField,gridTab){
	var _grid=grid;
	if (gridTab) _grid=gridTab;
	var rows=_grid.datagrid('getSelections');
	var num=rows.length;
	if (num==0){
		top.$.messager.alert('提示信息','请选择一条记录进行操作!','info');
		return;
	}
	else{
		top.$.messager.confirm('提示信息', msg, function(r){
			if (r){
				var ids=[];
				var rows=_grid.datagrid('getSelections');
				for (var i=0;i<rows.length;i++ ){
					ids.push(eval("rows[i]."+idField));
				}
				url=pageUrl(url,"ids",ids);
				url=pageUrl(url,"timeTemp",(new Date()).valueOf());
				
				if(submitFlat) {
					top.$.messager.alert('提示信息', '请勿重复提交！', 'info');
					return;
				}
				submitFlat = true;
				
				$.post(url,function(theback){
					if (!theback.success)
						top.$.messager.alert('错误', theback.message, 'error',function(){
							if (theback.url){
								top.location.href=theback.url;
							}
						});
					else{
						top.$.messager.alert('提示信息', theback.message, 'info',function(){
							_grid.datagrid('reload');
						});
					}
					submitFlat = false;
				},"json");
				
			}
		});
	}
}


function ajaxBase(url,msg){
	top.$.messager.confirm('提示信息', msg, function(r){
		if (r){
			url=pageUrl(url,"timeTemp",(new Date()).valueOf());
			
			if(submitFlat) {
				top.$.messager.alert('提示信息', '请勿重复提交！', 'info');
				return;
			}
			submitFlat = true;
			
			$.post(url,function(theback){
				if (!theback.success)
					top.$.messager.alert('错误', theback.message, 'error',function(){
						if (theback.url){
							top.location.href=theback.url;
						}
					});
				else{
					top.$.messager.alert('提示信息', theback.message, 'info',function(){
						winClose();
						grid.datagrid('reload');
					});
				}
				submitFlat = false;
			},"json");
			
		}
	});
}

function adQueryBase(title,icon,width,height,url){
	var myw =($(window).width()-fixWidth-width)/2;  
	var myh = ($(window).height()-fixHeight-height)/2;
	url=pageUrl(url,"timeTemp",queryTimeTemp);
	$('#detailPanel').dialog({
		title: title,
		iconCls:icon,
		width: width,
		height:height,
		closed: true,
		top:myh,
		left:myw,
		href:url,
		buttons:[{
			text:'查询',
			iconCls:'icon-ok',
			handler:querySubmit
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:winClose
		}]
	}).dialog('open');
	
	if (!urlRef){
		urlRef=true;
	}
	else
		$('#detailPanel').dialog("refresh");
}



function pageQuery(title,icon,width,height,url){
	var myw =($(window).width()-fixWidth-width+200)/2;  
	var myh = ($(window).height()-fixHeight-height+200)/2;
	url=pageUrl(url,"timeTemp",queryTimeTemp);
	$('#pagePanel').dialog({
		title: title,
		iconCls:icon,
		width: width,
		height:height,
		closed: true,
		top:myh,
		left:myw,
		href:url,
		buttons:[{
			text:'选择',
			iconCls:'icon-ok',
			handler:selectSubmit
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:pageClose
		}]
	}).dialog('open');
	
}


function fmSubmitBase(fmName,gridTab){
	//var fm=$($('#mainDialogFrame')[0].contentWindow.document).find("#"+fmName);
	if(submitFlat) {
		top.$.messager.alert('提示信息', '请勿重复提交！', 'info');
		return;
	}
	submitFlat = true;
	var fm=$("#"+fmName);
	var _grid=grid;
	if (gridTab) _grid=gridTab;
	var url=fm.attr("action");
	fm.form('submit', {
		url:url,
		onSubmit: function(){
			if(!$(this).form('validate')) {
				submitFlat = false;
			}
			return $(this).form('validate');
			
		},
		success:function(data){
			var theback=eval("(" +data + ")");
			if (!theback.success)
				top.$.messager.alert('错误', theback.message, 'error',function(){
					if (theback.url){
						top.location.href=theback.url;
					}
				});
			else{
				top.$.messager.alert('提示信息', theback.message, 'info',function(){
					winClose();
					_grid.datagrid('reload');
				});
			}
			submitFlat = false;
		}
	});
}


function fmSubmitTreeBase(url,gridTab){
	
	if(submitFlat) {
		top.$.messager.alert('提示信息', '请勿重复提交！', 'info');
		return;
	}
	submitFlat = true;
	var _grid=grid;
	if (gridTab) _grid=gridTab;
	$('#fm').form('submit', {
		url:url,
		onSubmit: function(){
			if(!$(this).form('validate')) {
				submitFlat = false;
			}
			return $(this).form('validate');
		},
		success:function(data){
			var theback=eval("(" +data + ")");
			if (!theback.success)
				top.$.messager.alert('错误', theback.message, 'error',function(){
					if (theback.url){
						top.location.href=theback.url;
					}
				});
			else{
				top.$.messager.alert('提示信息', theback.message, 'info',function(){
					winClose();
					_grid.treegrid('reload');
				});
			}
			submitFlat = true;
		}
	});
}

function fmSubmitAuditBase(url){
	if(submitFlat) {
		top.$.messager.alert('提示信息', '请勿重复提交！', 'info');
		return;
	}
	submitFlat = true;
	$('#fmAudit').form('submit', {
		url:url,
		onSubmit: function(){
			if(!$(this).form('validate')) {
				submitFlat = false;
			}
			return $(this).form('validate');
		},
		success:function(data){
			var theback=eval("(" +data + ")");
			if (!theback.success)
				top.$.messager.alert('错误', theback.message, 'error',function(){
					if (theback.url){
						top.location.href=theback.url;
					}
				});
			else{
				top.$.messager.alert('提示信息', theback.message, 'info',function(){
					winClose();
					grid.datagrid('reload');
				});
			}
			submitFlat = false;
		}
	});
}

function winClose(){
	submitFlat = false;
	$('#detailPanel').dialog('close');
}

function pageClose(){
	$('#pagePanel').dialog('close');
}


function openRealWindow(url)
{
    var w = window.open(url,"_blank");
}


function deleteTree(url,msg,idField,gridTab){
	var _grid=grid;
	if (gridTab) _grid=gridTab;
	var rows = _grid.treegrid('getSelected');
	
	var num=rows.length;
	if (num==0){
		top.$.messager.alert('提示信息','请选择一条记录进行操作!','info');
		return;
	}
	else{
		top.$.messager.confirm('提示信息', msg, function(r){
			if (r){
				var rows = _grid.treegrid('getSelected');
				var id=eval("rows."+idField); 
				url=pageUrl(url,"id",id);
				url=pageUrl(url,"timeTemp",(new Date()).valueOf());
				 $(function(){
					  $.ajax({   
				 		 	 type: "POST",
				             url:url,  
				             success:function(data){
				            	 var theback=eval("(" +data + ")");
				            	 top.$.messager.alert('提示信息', theback.message, 'info',function(){
				            		 _grid.treegrid('reload');
				 				});
				            }   
				         });  
				 });
			}
		});
	}
}

	

/***************************************************/
//form提交数据时，xhEditor处理
function submitXhEditor(xhEditorId) {
	var content = $("#"+xhEditorId).val();
	$("#"+xhEditorId).xheditor();
	$("#"+xhEditorId).val(content);
}
/*****************************************************/



//===========上传文件相关 start======================
function checkFormat(fileId) {
	var filePath = document.getElementById(fileId).value;
	var i = filePath.lastIndexOf('.');
	var len = filePath.length;
	var str = filePath.substring(len,i+1);
	var extName = "XLS";
	if(extName.indexOf(str.toUpperCase()) < 0) {
	  clearFile(fileId);
	  top.$.messager.alert('提示信息', '请选择正确的xls后缀的文件!', 'info');
	  return;   
   } 
}  

function clearFile(id){
	var up = (typeof id=="string")?document.getElementById(id):id; 
	if (typeof up != "object") return null; 
	var tt = document.createElement("span"); 
	tt.id = "__tt__"; 
	up.parentNode.insertBefore(tt,up); 
	var tf = document.createElement("form"); 
	tf.appendChild(up); 
	document.getElementsByTagName("body")[0].appendChild(tf); 
	tf.reset(); 
	tt.parentNode.insertBefore(up,tt); 
	tt.parentNode.removeChild(tt); 
	tt = null; 
	tf.parentNode.removeChild(tf); 
}

function getPath(obj) {
	  if (obj) {
	 	if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
	  	obj.select(); return document.selection.createRange().text;
	  	}
	  else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
	 	 if (obj.files) {
	 	 return obj.files.item(0).getAsDataURL();
	 	 }
	 	 return obj.value;
	  }
	 	 return obj.value;
	  }
}

/** 控制最高系统操作 **/
function isadminDisabled(){
	var result ;
	$.ajax({
		   async:false,
		   type: "POST",
		   url: "isAdminDisabled.html",
		   dataType: 'json',
		   success: function(msg){
			   result = msg;
		   }
		});
	
	  if(!result){
		   top.$.messager.alert('提示信息', '系统操作员不支持此操作！', 'info');
		   return true;
		}
}


/** 控制商户&公司无权限操作 **/
function ismerDisabled(){
	var result ;
	$.ajax({
		   async:false,
		   type: "POST",
		   url: "isMersDisabled.html",
		   dataType: 'json',
		   success: function(msg){
			   result = msg;
		   }
		});
	
	  if(!result){
		   top.$.messager.alert('提示信息', '商户(公司)操作员不支持系统(权限)操作！', 'info');
		   return true;
		}
}

/** 日期范围控制 **/
function  dateDiff(){
	var startTime = $("input[name='startTime']").val();
	var endTime = $("input[name='endTime']").val();
	
	startTime = startTime.substring(0,7);
	endTime = endTime.substring(0,7);
	
	var  aDate, bDate;
	aDate  =  startTime.split("-");    
	bDate  =  endTime.split("-");  
	 
	var num1 = (aDate[0]*12)+parseInt(aDate[1]);
	var num2 = (bDate[0]*12)+parseInt(bDate[1]);
	if((num2-num1)>3){
		top.$.messager.alert('提示信息', '交易查询日期范围请勿超过3个月！', 'info');
		return true;
	}
	return false;
}
