function roletypeDesc(value,rec){
	return rec.roletypeDesc;
}

function setQuery(){
	$("#roleseq").val(queryParam["roleseq"]);
	$("#rolename").val(queryParam["rolename"]);
}
function querySubmit(){
	
	queryParam["roleseq"]=$("#roleseq").val();
	queryParam["rolename"]=$("#rolename").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

/*
 * 分页条件框筛选
 */
function pageSubmit(){
	queryParam["unitid"]=$("#pageunitid").val();
	queryParam["unitname"]=$("#pageunitname").val();
	pageGrid.datagrid({queryParams: queryParam});
}



/*
 * 塞值
 */
function selectSubmit() {
	
	var rows=pageGrid.datagrid('getSelections');
	var num = rows.length;
	if (num==0){
		$('#'+selectFlag).val('');
	}else{
		
		if(selectFlag == 'unitid'){
			$('#unitid').val(rows[0].unitid);
		}
	}
	$('#pagePanel').dialog('close');
}




function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',480,130,'role/query.html');
}
var selectFlag;
function unitQuery(){ //分页筛选单位ID界面
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}


function modify(){ //修改角色页面
	modifyBase('角色修改','icon-edit',480,200,'role/detail.html','roleseq');
}

function add(){ //新增 界面
	addBase('角色增加','icon-add',480,200,'role/detail.html');
}
function del(){
	executeSingleBase("role/del.html","您确定要删除记录吗?","roleseq");
}


function roleMenuList() { // 角色菜单分配 界面
	modifyMenu('角色菜单分配', 'icon-edit', 250 , 330,
			'role/allotMenuPage.html', 'roleseq', false,
			roleMenuSave);
}

function adminMenuList() { // 系统管理员菜单分配 界面
	modifyAdminMenu('系统管理员菜单分配', 'icon-edit', 250 , 330,
			'role/allotMenuPage.html', false,
			roleMenuSave);
}



function modifyMenu(title, icon, width, height, url, idField, gridTab, fun) {
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
		url = pageUrl(url, "timeTemp", (new Date()).valueOf());
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



function modifyAdminMenu(title, icon, width, height, url, gridTab, fun) {
	var _fun = saveSubmit;

	var myw = ($(window).width() - fixWidth - width) / 2;
	var myh = ($(window).height() - fixHeight - height) / 2;
	var _grid = grid;
	if (gridTab)
		_grid = gridTab;
	if (fun)
		_fun = fun;

		url = pageUrl(url, "id", '1');
		url = pageUrl(url, "timeTemp", (new Date()).valueOf());
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


//菜单权限保存
var mark =1;
var menuFlat = false;
function roleMenuSave(){
	if(menuFlat) {
		top.$.messager.alert('提示信息', '请勿重复提交！', 'info');
		return;
	}
	menuFlat = true;
	var node = $('#tt').tree('getChecked');
	var ids=[];
	for(var i=0;i<node.length;i++ ){
		ids.push(node[i].id);
	}
	var url="role/roleMenuSave.html";
	url=pageUrl(url,"ids",ids);
	url=pageUrl(url,"roleId",$("#roleId").val());
	
	ajaxBase(url,"此操作会覆盖原有权限，确定保存吗？");
	menuFlat = false;
	mark = 1;
}

 