function createtimeDesc(value,rec){
	return rec.createtimeDesc;
}

function modifytimeDesc(value,rec){
	return rec.modifytimeDesc;
}

function lastlogtimeDesc(value,rec){
	return rec.lastlogtimeDesc;
}

function isactiveDesc(value,rec){
	return rec.isactiveDesc;
}

function setQuery(){
	$("#merseq").val(queryParam["merseq"]);
	$("#realname").val(queryParam["realname"]);
	$("#meropseq").val(queryParam["meropseq"]);
	$("#isactive").val(queryParam["isactive"]);
	$("#unitid").val(queryParam["unitid"]);
	$("#roeseq").val(queryParam["roeseq"]);
}

function querySubmit(){
	
	queryParam["merseq"]=$("#merseq").val();
	queryParam["realname"]=$("#realname").val();
	queryParam["meropseq"]=$("#meropseq").val();
	queryParam["isactive"]=$("#isactive").val();
	queryParam["unitid"]=$("#unitid").val();
	queryParam["roeseq"]=$("#roeseq").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',480,180,'merop/query.html');
}

function modify(){ //修改
	if(isadminDisabled())return;
	modifyBase('商户操作员修改','icon-edit',500,260,'merop/detail.html','meropseq');
}

function add(){ //增加
	if(isadminDisabled())return;
	addBase('增加商户操作员','icon-add',500,260,'merop/detail.html');
}

function del(){
	if(isadminDisabled())return;
	executeSingleBase("merop/del.html","确定此操作?","meropseq");
}


function unitQuery(){ //分页筛选商户ID界面
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}


function merQuery(){ //分页筛选商户ID界面
	selectFlag="merseq";
	pageQuery('列表选择','icon-search',600, 300,'merchant/pageIndex.html');
}


function roleQuery(){ //分页筛选角色序号界面
	selectFlag="roeseq";
	pageQuery('列表选择','icon-search',600, 300,'role/pageIndex.html?unitFlagRole=false')  ;
}

/*
 * 分页条件框筛选
 */
function pageSubmit(){
	queryParam["unitid"]=$("#pageunitid").val();
	queryParam["unitname"]=$("#pageunitname").val();
	queryParam["rolename"]=$("#pagerolename").val();
	queryParam["roledesc"]=$("#pageroledesc").val();
	queryParam["merseq"]=$("#pagemerseq").val();
	queryParam["branchid"]=$("#pagebranchid").val();
	pageGrid.datagrid({queryParams: queryParam});
}


/*
 * 塞值
 */
function selectSubmit() {
	
	var rows=pageGrid.datagrid('getSelections');
	var num = rows.length;
	if (num==0){
		if(selectFlag == 'roeseq'){
			$('#storeseq').val('');
		}
		$('#'+selectFlag).val('');
	}else{
		
		if(selectFlag == 'merseq'){
			$('#merseq').val(rows[0].merseq);
		}
		
		if(selectFlag == 'roeseq'){
			$('#roeseq').val(rows[0].roleseq);
		}
		
		if(selectFlag == 'unitid'){
			$('#unitid').val(rows[0].unitid);
		}
	}
	$('#pagePanel').dialog('close');
}

