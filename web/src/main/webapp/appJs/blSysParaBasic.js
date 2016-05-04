function recordStatDesc(value,rec){
	return rec.recordStatDesc;
}
function setQuery(){
	$("#paraName").val(queryParam["paraName"]);
	$("#paraNo").val(queryParam["paraNo"]);
	$("#paraValue").val(queryParam["paraValue"]);
	$("#recordStat").val(queryParam["recordStat"]);
}

function querySubmit(){
	
	queryParam["paraName"]=$("#paraName").val();
	queryParam["paraNo"]=$("#paraNo").val();
	queryParam["paraValue"]=$("#paraValue").val();
	queryParam["recordStat"]=$("#recordStat").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}


function query(){ //查询界面
	adQueryBase('条件查询','icon-search',480,180,'syspara/query.html');
}

function modify(){ //修改
	modifyBase('参数配置修改','icon-edit',500,220,'syspara/detail.html','mainkeyinfo');
}

function add(){ //增加
	addBase('参数配置增加','icon-add',500,220,'syspara/detail.html');
}

function del(){
	executeSingleBase("syspara/del.html","确定此操作?","mainkeyinfo");
}

function excutetask(){
	addBase('手动触发任务','icon-add',480,130,'syspara/queryTask.html');
}