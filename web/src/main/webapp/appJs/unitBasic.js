function createtimeDesc(value,rec){
	return rec.createtimeDesc;
}

function unitkindDesc(value,rec){
	return rec.unitkindDesc;
}

function defaultDblClick(){
//	modify();
}

function setQuery(){
	$("#unitid").val(queryParam["unitid"]);
	$("#unitname").val(queryParam["unitname"]);
	$("#unitcontact").val(queryParam["unitcontact"]);
	$("#unittele").val(queryParam["unittele"]);
	$("#unitmail").val(queryParam["unitmail"]);
}
function querySubmit(){
	
	queryParam["unitid"]=$("#unitid").val();
	queryParam["unitname"]=$("#unitname").val();
	queryParam["unitcontact"]=$("#unitcontact").val();
	queryParam["unittele"]=$("#unittele").val();
	queryParam["unitmail"]=$("#unitmail").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}


function query(){ //查询界面
	adQueryBase('条件查询','icon-search',470,220,'unit/query.html');
}

function confBank(){ //机构修改
	modifyBase('清算银行配置','icon-edit',280,210,'unit/configBank.html','unitid');
}

function modify(){ //机构修改
	modifyBase('机构修改','icon-edit',500,320,'unit/detail.html','unitid');
}

function add(){ //机构增加
	addBase('机构增加','icon-add',500,320,'unit/detail.html');
}

function del(){
	executeSingleBase("unit/del.html","删除机构是危险的操作,确定此操作?","unitid");
}

function confBank(){ //机构修改
	modifyBase('清算银行配置','icon-edit',280,210,'unit/configBank.html','unitid');
}

function createcode(){ //生成确认码
	showBase('生成确认码','icon-add',410,150,'unit/createcode.html');
}

function deploy() { // 机构分配管理员
	deployBase('机构分配管理员', 'icon-edit', 810, 500,
			'unit/deploy.html', 'unitid', false,
			deployUnit);
}

function bind() { // 机构绑定
	deployBase('机构绑定报表配置', 'icon-edit', 240, 320,'unit/bind.html', 'unitid',false,bindConfByUnitid);
}

function showBase(title,icon,width,height,url	){
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
			text:'关闭',
			iconCls:'icon-cancel',
			handler:winClose
		}]
	}).dialog('open');
}

function deployBase(title, icon, width, height, url, idField, gridTab, fun) {
	var _fun = saveSubmit;

	var myw = ($(window).width() - fixWidth - width) / 2;
	var myh = ($(window).height() - fixHeight - height) / 2;
	var _grid = grid;
	var rows;
	if (gridTab)
		_grid = gridTab;
	if (fun)
		_fun = fun;

	rows = _grid.datagrid('getSelections');
	var num = rows.length;

	if (num == 0) {
		top.$.messager.alert('提示信息', '请选择一条记录进行操作!', 'info');
		return;
	} else if (num > 1) {
		top.$.messager.alert('提示信息', '只能选择一条记录进行操作!', 'info');
		return;
	} else {
		url = pageUrl(url, "id", eval("rows[0]." + idField));
		$('#detailPanel').dialog({
			title : title,
			iconCls : icon,
			width : width,
			height : height,
			closed : true,
			top : myh + 40,
			left : myw,
			href : url,
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : _fun
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : winClose
			} ]
		}).dialog('open');
		if (!urlRef) {
			urlRef = true;
		} else
			$('#detailPanel').dialog("refresh");
	}
}



//菜单权限保存
var menuFlat = false;

function deployUnit(){

	if (menuFlat) {
		top.$.messager.alert('提示信息', '请勿重复提交！', 'info');
		return;
	}
	menuFlat = true;
	var node = $('#tt').tree('getChecked');
	var ids = [];
	for (var i = 0; i < node.length; i++) {
		ids.push(node[i].id);
	}
	var url = "unit/deployUnitSave.html";
	url = pageUrl(url, "ids", ids);

	var fm = $('#fm');
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
					$('#detailPanel').dialog('close');  
					_grid.datagrid('reload');
				});
			}
			menuFlat = false;
		}
	});

	menuFlat = false;
}

function bindConfByUnitid(){

	if (menuFlat) {
		top.$.messager.alert('提示信息', '请勿重复提交！', 'info');
		return;
	}
	menuFlat = true;
	var node = $('#tt').tree('getChecked');
	var ids = [];
	for (var i = 0; i < node.length; i++) {
		ids.push(node[i].id);
	}
	var url = "unit/bindConfByunitSave.html";
	url = pageUrl(url, "ids", ids);

	var fm = $('#fm');
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
					$('#detailPanel').dialog('close');  
					_grid.datagrid('reload');
				});
			}
			menuFlat = false;
		}
	});

	menuFlat = false;
}