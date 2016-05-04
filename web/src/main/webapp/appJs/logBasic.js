function opflagDesc(value,rec){
	return rec.opflagDesc;
}
function optimeDesc(value,rec){
	return rec.optimeDesc;
}
function setQuery(){
	$("#oplogname").val(queryParam["oplogname"]);
	$("#oprealname").val(queryParam["oprealname"]);
	$("#opflag").val(queryParam["opflag"]);
	$("#unitid").val(queryParam["unitid"]);	
}
function querySubmit(){
	
	queryParam["oplogname"]=$("#oplogname").val();
	queryParam["oprealname"]=$("#oprealname").val();
	queryParam["opflag"]=$("#opflag").val();
	queryParam["unitid"]=$("#unitid").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('日志查询','icon-search',520,180,'log/logQuery.html');
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

