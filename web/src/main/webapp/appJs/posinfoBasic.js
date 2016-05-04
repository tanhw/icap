function postypeDesc(value,rec){
	return rec.postypeDesc;
}
function statusDesc(value,rec){
	return rec.statusDesc;
}
function createtimeDesc(value,rec){
	return rec.createtimeDesc;
}
function tmkdownflagDesc(value,rec){
	return rec.tmkdownflagDesc;
}

function keyadownflagDesc(value,rec){
	return rec.keyadownflagDesc;
}

function lasttimestampDesc(value,rec){
	return rec.lasttimestampDesc;
}

function posbrandDesc(value,rec){
	return rec.posbrandDesc;
}
function maxmoneyDesc(value,rec){
	return rec.maxmoneyDesc;
}
function totalmoneyDesc(value,rec){
	return rec.totalmoneyDesc;
}

function setQuery(){
	$("#posid").val(queryParam["posid"]);
	$("#postype").val(queryParam["postype"]);
	$("#branchid").val(queryParam["branchid"]);
	$("#merseq").val(queryParam["merseq"]);
	$("#storeseq").val(queryParam["storeseq"]);
	$("#busid").val(queryParam["busid"]);
	$("#samid").val(queryParam["samid"]);
	$("#termseq").val(queryParam["termseq"]);
	$("#batchno").val(queryParam["batchno"]);
	$("#status").val(queryParam["status"]);
	$("#unitid").val(queryParam["unitid"]);
	$("#tmkdownflag").val(queryParam["tmkdownflag"]);
	$("#keyadownflag").val(queryParam["keyadownflag"]);
	$("#posbrand").val(queryParam["posbrand"]);
}
function querySubmit(){
	
	queryParam["posid"]=$("#posid").val();
	queryParam["postype"]=$("#postype").val();
	queryParam["branchid"]=$("#branchid").val();
	queryParam["merseq"]=$("#merseq").val();
	queryParam["storeseq"]=$("#storeseq").val();
	queryParam["busid"]=$("#busid").val();
	queryParam["samid"]=$("#samid").val();
	queryParam["termseq"]=$("#termseq").val();
	queryParam["batchno"]=$("#batchno").val();
	queryParam["status"]=$("#status").val();
	queryParam["unitid"]=$("#unitid").val();
	queryParam["tmkdownflag"]=$("#tmkdownflag").val();
	queryParam["keyadownflag"]=$("#keyadownflag").val();
	queryParam["posbrand"]=$("#posbrand").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',570,370,'posinfo/query.html');
}

function modify(){ //修改 界面
	if(isadminDisabled())return;
	modifyBase('终端修改','icon-edit',580,370,'posinfo/detail.html','posid');
}

function add(){ //新增 界面
	if(isadminDisabled())return;
	addBase('终端增加','icon-add',580,370,'posinfo/detail.html');
}
function del(){
	if(isadminDisabled())return;
	executeSingleBase("posinfo/del.html","您确定要删除记录吗?","posid");
}

function edit(){
	if(isadminDisabled())return;
	executeSingleBase("posinfo/edit.html","您确定要重置主密钥下载标志吗?","posid");
}

function downkeya(){
	if(isadminDisabled())return;
	executeSingleBase("posinfo/downkeya.html","您确定要重置keya密钥下载标志吗?","posid");
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
	queryParam["merseq"]=$("#pagemerseq").val();
	queryParam["branchchn"]=$("#pagebranchchn").val();
	queryParam["branchid"]=$("#pagebranchid").val();
	queryParam["facname"]=$("#pagefacname").val();
	queryParam["storeseq"]=$("#pagestoreseq").val();
	queryParam["storename"]=$("#pagestorename").val();
	queryParam["busid"]=$("#pagebusid").val();
	queryParam["tlic"]=$("#pagetlic").val();
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
		
		if(selectFlag == 'merseq'){
			$('#merseq').val(rows[0].merseq);
			$('#branchid').val(rows[0].branchid);
		}
		if(selectFlag == 'storeseq'){
			$('#storeseq').val(rows[0].storeseq);
		}
		if(selectFlag == 'busid'){
			$('#busid').val(rows[0].busid);
		}
		
	}
	$('#pagePanel').dialog('close');
	
}
function unitQuery(){ //分页筛选单位ID界面
	
	$("#merseq").val('');
	$("#storeseq").val('');
	
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}

function merQuery(){ //分页筛选商户ID界面
	$('#busid').val('');
	selectFlag="merseq";
	pageQuery('列表选择','icon-search',600, 300,'merchant/pageIndex.html');
}

function storeQuery(){
	
	selectFlag="storeseq";
	pageQuery('列表选择','icon-search',600, 300,'merstore/pageIndex.html');
}

function busQuery(){ //分页筛选车辆ID界面
	var merseq = $("#merseq").val();
	if(merseq == null || merseq == ''){
		top.$.messager.alert('提示信息', '必须先选择某个商户!', 'info');
		return false;
	}
	
	selectFlag="busid";
	pageQuery('列表选择','icon-search',600, 300,'busInfo/pageIndex.html?merseq='+merseq);
}

function businfoQuery(){ //分页筛选车辆ID界面
	selectFlag="busid";
	pageQuery('列表选择','icon-search',600, 300,'busInfo/pageIndex.html');
}

function changeTable(info,table){
	if(info != 1002){
		table.show();
	}else{
		table.hide();
	}
			
}

