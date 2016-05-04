function createtimeDesc(value,rec){
	return rec.createtimeDesc;
}

function setQuery(){
	
	$("#bankcode").val(queryParam["bankcode"]);
	$("#bankname").val(queryParam["bankname"]);
	$("#unitid").val(queryParam["unitid"]);
}
function querySubmit(){
	
	queryParam["bankcode"]=$("#bankcode").val();
	queryParam["bankname"]=$("#bankname").val();
	queryParam["unitid"]=$("#unitid").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm"); 
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',500,180,'bank/query.html');
}

function modify(){ //修改 界面
	if(isadminDisabled()){return;}
	modifyBase('银行修改','icon-edit',500,260,'bank/detail.html','bankid');
}

function add(){ //新增 界面
	if(isadminDisabled()){return;}
	addBase('银行增加','icon-add',500,260,'bank/detail.html');
}
function del(){
	if(isadminDisabled()){return;}
	executeSingleBase("bank/del.html","您确定要删除记录吗?","bankid");
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

var selectFlag;
function unitQuery(){ //分页筛选单位ID界面
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
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



