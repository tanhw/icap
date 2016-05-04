function isactiveDesc(value,rec){
	return rec.isactiveDesc;
}

function createtimeDesc(value,rec){
	return rec.createtimeDesc;
}
function modifytimeDesc(value,rec){
	return rec.modifytimeDesc;
}
function lastlogtimeDesc(value,rec){
	return rec.lastlogtimeDesc;
}

function setQuery(){
	$("#loginname").val(queryParam["loginname"]);
	$("#realname").val(queryParam["realname"]);
	$("#roeseq").val(queryParam["roeseq"]);
	$("#isactive").val(queryParam["isactive"]);	
}
function querySubmit(){
	
	queryParam["loginname"]=$("#loginname").val();
	queryParam["realname"]=$("#realname").val();
	queryParam["roeseq"]=$("#roeseq").val();
	queryParam["isactive"]=$("#isactive").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',500,180,'admin/adminQuery.html');
}

function modify(){ //修改 界面
	modifyBase('系统修改','icon-edit',500,270,'admin/adminDetail.html','adminseq');
}

function add(){ //新增 界面
	addBase('系统增加','icon-add',500,270,'admin/adminDetail.html');
}

function resetpwd(){
	executeSingleBase("admin/resetpwd.html","您确定要重置密码吗?","adminseq");
}

function del(){
	executeSingleBase("admin/del.html","您确定要删除记录吗?","adminseq");
}

function defaultDblClick(){
//	modify();
}


var selectFlag;
function adminQuery(){ //角色ID界面
	selectFlag="roeseq";
	pageQuery('列表选择','icon-search',400, 300,'role/pageIndex.html');
}

/*
 * 分页条件框筛选
 */
function pageSubmit(){
	queryParam["rolename"]=$("#pagerolename").val();
	queryParam["roledesc"]=$("#pageroledesc").val();
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
		
		if(selectFlag == 'roeseq'){
			$('#roeseq').val(rows[0].roleseq);
		}
	}
	$('#pagePanel').dialog('close');
	
}

