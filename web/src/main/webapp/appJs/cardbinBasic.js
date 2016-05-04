function createtimeDesc(value,rec){
	return rec.createtimeDesc;
}

function setQuery(){
	$("#cardbin").val(queryParam["cardbin"]);
	$("#binname").val(queryParam["binname"]);
	$("#bankid").val(queryParam["bankid"]);
	$("#unitid").val(queryParam["unitid"]);	
}
function querySubmit(){
	
	queryParam["cardbin"]=$("#cardbin").val();
	queryParam["binname"]=$("#binname").val();
	queryParam["bankid"]=$("#bankid").val();
	queryParam["unitid"]=$("#unitid").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',480,180,'cardbin/query.html');
}

function modify(){ //修改 界面
	if(isadminDisabled())return;
	modifyBase('卡bin修改','icon-edit',500,180,'cardbin/detail.html','binseq');
}

function add(){ //新增 界面
	if(isadminDisabled())return;
	addBase('卡bin增加','icon-add',500,180,'cardbin/detail.html');
}
function del(){
	if(isadminDisabled())return;
	executeSingleBase("cardbin/del.html","您确定要删除记录吗?","binseq");
}

function defaultDblClick(){
//	modify();
}

var selectFlag;

function unitQuery(){ //区域筛选ID界面
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}

function bankQuery(){ //区域筛选ID界面
	selectFlag="bankid";
	pageQuery('列表选择','icon-search',600, 300,'bank/pageIndex.html');
}


/*
 * 分页条件框筛选
 */
function pageSubmit(){
	queryParam["unitid"]=$("#pageunitid").val();
	queryParam["unitname"]=$("#unitname").val();
	queryParam["bankid"]=$("#pagebankid").val();
	queryParam["bankname"]=$("#pagebankname").val();
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
		if(selectFlag == 'bankid'){
			$('#bankid').val(rows[0].bankid);
		}
		
		if(selectFlag == 'unitid'){
			$('#unitid').val(rows[0].unitid);
		}
	}
	$('#pagePanel').dialog('close');
}

