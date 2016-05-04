function setQuery(){
	$("#facname").val(queryParam["facname"]);
	$("#faccode").val(queryParam["faccode"]);
	$("#faccontact").val(queryParam["faccontact"]);
	$("#factele").val(queryParam["factele"]);
	$("#facmail").val(queryParam["facmail"]);
}
function querySubmit(){
	
	queryParam["facname"]=$("#facname").val();
	queryParam["faccode"]=$("#faccode").val();
	queryParam["faccontact"]=$("#faccontact").val();
	queryParam["factele"]=$("#factele").val();
	queryParam["facmail"]=$("#facmail").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',500,210,'posfac/query.html');
}

function modify(){ //修改 界面
	modifyBase('终端厂商修改','icon-edit',520,300,'posfac/detail.html','factoryid');
}

function add(){ //新增 界面
	addBase('终端厂商增加','icon-add',520,300,'posfac/detail.html');
}
function del(){
	executeSingleBase("posfac/del.html","您确定要删除记录吗?","factoryid");
}

function defaultDblClick(){
//	modify();
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
		top.$.messager.alert('提示信息','请选择一条记录进行操作!','info');
		return;
	}else if (num > 1) {
		top.$.messager.alert('提示信息', '只能选择一条记录进行操作!', 'info');
		return;
	}else{
		var id = rows[0].unitid;
		$('#unitid').val(id);
		$('#pagePanel').dialog('close');
			
	}
	
	
}
function unitQuery(){ //分页筛选单位ID界面
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}
