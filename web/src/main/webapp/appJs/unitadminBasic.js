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
	$("#unitid").val(queryParam["unitid"]);
}
function querySubmit(){
	
	queryParam["loginname"]=$("#loginname").val();
	queryParam["realname"]=$("#realname").val();
	queryParam["roeseq"]=$("#roeseq").val();
	queryParam["unitid"]=$("#unitid").val();
	queryParam["isactive"]=$("#isactive").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',500,230,'unitadmin/unitadminQuery.html');
}

function modify(){ //修改 界面
	if(isadminDisabled())return;
	modifyBase('机构操作员修改','icon-edit',500,249,'unitadmin/unitadminDetail.html','unitadminseq');
}

function add(){ //新增 界面
	if(isadminDisabled())return;
	addBase('机构操作员增加','icon-add',500,249,'unitadmin/unitadminDetail.html');	
}
function del(){
	executeSingleBase("unitadmin/del.html","您确定要删除记录吗?","unitadminseq");
}


function passWd(){
	executeSingleBase("unitadmin/password.html","您确定要重置此机构的操作员密码吗?","unitadminseq");
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
	queryParam["rolename"]=$("#pagerolename").val();
	queryParam["roledesc"]=$("#pageroledesc").val();
	pageGrid.datagrid({queryParams: queryParam});
}

var selectFlag;

function unitQuery(){
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}

function roleQuery(){ //分页筛选角色序号界面
	selectFlag="roeseq";
	pageQuery('列表选择','icon-search',600, 300,'role/pageIndex.html?unitFlagRole=true');
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
		
		if(selectFlag == 'unitid'){
			$('#unitid').val(rows[0].unitid);
		}
	}
	$('#pagePanel').dialog('close');
}

