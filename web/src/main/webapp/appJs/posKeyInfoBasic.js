function setQuery(){
	$("#unitid").val(queryParam["unitid"]);
	$("#branchid").val(queryParam["branchid"]);
	$("#merseq").val(queryParam["merseq"]);
	$("#posid").val(queryParam["posid"]);
}
function querySubmit(){
	
	queryParam["unitid"]=$("#unitid").val();
	queryParam["branchid"]=$("#branchid").val();
	queryParam["merseq"]=$("#merseq").val();
	queryParam["posid"]=$("#posid").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',500,180,'poskey/query.html');
}

function modify(){ //修改 界面
	if(isadminDisabled())return;
	modifyBase('终端密钥修改','icon-edit',570,400,'poskey/detail.html','posid');
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
	}
	$('#pagePanel').dialog('close');
	
}
function unitQuery(){ //分页筛选单位ID界面
	
	$("#merseq").val('');
	
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}

function merQuery(){ //分页筛选商户ID界面
	var unitid = $("#unitid").val();
	if(unitid == null || unitid == ''){
		top.$.messager.alert('提示信息', '必须先选择某个单位!', 'info');
		return false;
	}
	
	selectFlag="merseq";
	pageQuery('列表选择','icon-search',600, 300,'merchant/pageIndex.html');
}
