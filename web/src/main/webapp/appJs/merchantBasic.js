function branchstateDesc(value,rec){
	return rec.branchstateDesc;
}
function setQuery(){
	
	$("#merseq").val(queryParam["merseq"]);
	$("#branchid").val(queryParam["branchid"]);
	$("#bankmerid").val(queryParam["bankmerid"]);
	$("#branchstate").val(queryParam["branchstate"]);
	$("#unitid").val(queryParam["unitid"]);
}
function querySubmit(){
	
	queryParam["merseq"]=$("#merseq").val();
	queryParam["branchid"]=$("#branchid").val();
	queryParam["bankmerid"]=$("#bankmerid").val();
	queryParam["branchstate"]=$("#branchstate").val();
	queryParam["unitid"]=$("#unitid").val();

	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',520,210,'merchant/query.html');
}

function modify(){ //修改 界面
	if(isadminDisabled())return;
	modifyBase('商户修改','icon-edit',540,450,'merchant/detail.html','merseq');
}

function add(){ //新增 界面
	if(isadminDisabled())return;
	if(!checkReg()) return;
	
	addBase('商户增加','icon-add',540,450,'merchant/detail.html');
}
function del(){
	if(isadminDisabled())return;
	executeSingleBase("merchant/del.html","您确定要删除记录吗?","merseq");
}

function defaultDblClick(){
//	modify();
}



function checkReg(){
	var checkFlag = false;
	$.ajax({
		   async:false,
		   type: "POST",
		   url: "checkReg.html",
		   success:function(data){
				var theback = eval("(" + data + ")");
				if (!theback.success) {
					top.$.messager.alert('错误', theback.message, 'error',function(){
						checkFlag =  false;
					});
				}else{
					checkFlag = true;
				}
			}
		});
	  
	return checkFlag;
}

var selectFlag;
function unitQuery(){ //分页筛选商户ID界面
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}

function merQuery(){ //分页筛选商户ID界面
	selectFlag="merseq";
	pageQuery('列表选择','icon-search',600, 300,'merchant/pageIndex.html');
}

function acompQuery(){ //分页筛选商户ID界面
	selectFlag="acompanyid";
	pageQuery('列表选择','icon-search',600, 300,'areacomp/pageIndex.html');
}

/*
 * 分页条件框筛选
 */
function pageSubmit(){
	queryParam["unitid"]=$("#pageunitid").val();
	queryParam["unitname"]=$("#pageunitname").val();
	queryParam["merseq"]=$("#pagemerseq").val();
	alert($("#pagebranchchn").val());
	queryParam["branchchn"]=$("#pagebranchchn").val();
	queryParam["branchid"]=$("#pagebranchid").val();
	queryParam["acompanyid"]=$("#pageacompanyid").val();
	queryParam["companyname"]=$("#pagecompanyname").val();
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
		
		if(selectFlag == 'merseq'){
			$('#merseq').val(rows[0].merseq);
			$('#branchid').val(rows[0].branchid);
		}
		
		if(selectFlag == 'unitid'){
			$('#unitid').val(rows[0].unitid);
		}
		
		if(selectFlag == 'acompanyid'){
			$('#acompanyid').val(rows[0].acompanyid);
		}
	}
	$('#pagePanel').dialog('close');
}