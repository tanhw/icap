function isactiveDesc(value,rec){
	return rec.isactiveDesc;
}

function setQuery(){
	
	$("#confname").val(queryParam["confname"]);
	$("#isactive").val(queryParam["isactive"]);
	$("#busi").val(queryParam["busi"]);
}
function querySubmit(){
	
	queryParam["confname"]=$("#confname").val();
	queryParam["isactive"]=$("#isactive").val();
	queryParam["busi"]=$("#busi").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm"); 
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',500,180,'config/query.html');
}

function modify(){ //修改 界面
	modifyBase('报表配置修改','icon-edit',530,530,'config/detail.html','confid');
}

function add(){ //新增 界面
	addBase('报表配置增加','icon-add',530,530,'config/detail.html');
}
function del(){
	executeSingleBase("config/del.html","您确定要删除记录吗?","confid");
}

function defaultDblClick(){
//	modify();
}