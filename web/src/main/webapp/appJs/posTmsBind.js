function postypeDesc(value,rec){
	return rec.postypeDesc;
}
function setQuery(){
	$("#posid").val(queryParam["posid"]);
	$("#branchid").val(queryParam["branchid"]);
	$("#merseq").val(queryParam["merseq"]);
	$("#unitid").val(queryParam["unitid"]);
}
function querySubmit(){
	
	queryParam["posid"]=$("#posid").val();
	queryParam["branchid"]=$("#branchid").val();
	queryParam["merseq"]=$("#merseq").val();
	queryParam["unitid"]=$("#unitid").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}



function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',480,180,'posTmsBind/query.html');
}

function add(){ //新增 界面
	if(isadminDisabled())return;
	addBase('终端新增绑定','icon-add',500,210,'posTmsBind/detail.html');
}

function del(){ //解除绑定界面
	if(isadminDisabled())return;
	addBase('终端解除绑定','icon-add',500,210,'posTmsBind/delPage.html');
}


function merQuery(){ //分页筛选商户ID界面
	
	selectFlag="merseq";
	pageQuery('列表选择','icon-search',600, 300,'merchant/pageIndex.html');
}

function unitQuery(){ //分页筛选商户ID界面
	
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}

function tmsdataQuery(){ //分页筛选商户ID界面
	var merseq = $('#merseq').val();
	selectFlag="filename";
	pageQuery('列表选择','icon-search',600, 300,'posTmsdata/pageIndex.html?merseq='+merseq);
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
		if(selectFlag == 'filename'){
			$('#filename').val("");
			$('#posbrand').val("");
		}
		$('#'+selectFlag).val('');
	}else{
		
		if(selectFlag == 'merseq'){
			$('#merseq').val(rows[0].merseq);
			$('#branchid').val(rows[0].branchid);
		}
		if(selectFlag == 'filename'){
			$('#filename').val(rows[0].filename);
			$('#posbrand').val(rows[0].posbrand);
			$("#posbrand option").attr("disabled","disabled")
			$("#posbrand option:selected").removeAttr("disabled")
		}
		
		if(selectFlag == 'unitid'){
			$('#unitid').val(rows[0].unitid);
		}
	}
	$('#pagePanel').dialog('close');
	
}

