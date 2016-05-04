function createtimeDesc(value,rec){
	return rec.createtimeDesc;
}

function markDesc(value,rec){
	return rec.markDesc
}

function setQuery(){
	$("#blackseq").val(queryParam["blackseq"]);
	$("#cardno").val(queryParam["cardno"]);
	$("#name").val(queryParam["name"]);
	$("#mark").val(queryParam["mark"]);
	$("#unitid").val(queryParam["unitid"]);
}

function querySubmit(){
	
	queryParam["blackseq"]=$("#blackseq").val();
	queryParam["cardno"]=$("#cardno").val();
	queryParam["name"]=$("#name").val();
	queryParam["mark"]=$("#mark").val();
	queryParam["unitid"]=$("#unitid").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}


function query(){ //查询界面
	adQueryBase('条件查询','icon-search',430,180,'blackInfo/query.html');
}

function modify(){ //修改
	if(isadminDisabled())return;
	modifyBase('黑名单修改','icon-edit',480,180,'blackInfo/detail.html','blackseq');
}

function add(){ //增加
	if(isadminDisabled())return;
	addBase('黑名单添加','icon-add',480,180,'blackInfo/detail.html');
}

function del(){
	if(isadminDisabled())return;
	executeSingleBase("blackInfo/del.html","确定此操作?","blackseq");
}

function blackBatchAdd() { // 新增 界面
	if(isadminDisabled())return;
	batchBaseAdd('批量文件增加', 'icon-add', 560, 180, 'blackInfo/batchaddQuery.html');
}

function blackClear() { // 新增 界面
	if(isadminDisabled())return;
	blackinfoClear('一键黑名单清除', 'icon-edit', 480, 150, 'blackInfo/clearQuery.html');
}

function blackinfoClear(title, icon, width, height, url, fun) {
	var _fun = saveSubmit;
	if (fun) {
		_fun = fun;
	}
	var myw = ($(window).width() - fixWidth - width) / 2;
	var myh = ($(window).height() - fixHeight - height) / 2;
	url = pageUrl(url, "timeTemp", (new Date()).valueOf());
	$('#detailPanel').dialog({
		title : title,
		iconCls : icon,
		width : width,
		height : height,
		closed : true,
		top : myh,
		left : myw,
		href : url,
		buttons : [ {
			text:'开始清除',
			iconCls:'icon-ok',
			handler:_fun
		},{
			text : '关闭窗口',
			iconCls : 'icon-cancel',
			handler : winClose
		} ]
	}).dialog('open');
	if (!urlRef) {
		urlRef = true;
	} else
		$('#detailPanel').dialog("refresh");
}

//及时存储
function ajaxcheck() {
	var companyid = $("#companyid").val();
	var mark = $("#mark").val();
	
	$.ajaxFileUpload({
		url : 'blackInfo/precheck.html?mark=' + mark,
		secureuri : false,
		async : false,
		dataType : 'json',
		fileElementId : 'blacks',//要上传的文件或
		success : function(data) {
			if (!data.success){
				top.$.messager.alert('错误', data.message, 'error');
        	}
			else{
				top.$.messager.alert('提示信息', data.message, 'info',function(){
					winClose();
					grid.datagrid('reload');
				});
			}
		}
	});
	winClose();
}

function batchBaseAdd(title, icon, width, height, url, fun) {
	var _fun = ajaxcheck;
	if (fun) {
		_fun = fun;
	}
	var myw = ($(window).width() - fixWidth - width) / 2;
	var myh = ($(window).height() - fixHeight - height) / 2;
	url = pageUrl(url, "timeTemp", (new Date()).valueOf());
	$('#detailPanel').dialog({
		title : title,
		iconCls : icon,
		width : width,
		height : height,
		closed : true,
		top : myh,
		left : myw,
		href : url,
		buttons : [ {
			text:'开始导入',
			iconCls:'icon-ok',
			handler:_fun
		},{
			text : '关闭窗口',
			iconCls : 'icon-cancel',
			handler : winClose
		} ]
	}).dialog('open');
	if (!urlRef) {
		urlRef = true;
	} else
		$('#detailPanel').dialog("refresh");
}

function cardnoQuery(){ //区域筛选ID界面
	selectFlag="cardno";
	pageQuery('列表选择','icon-search',600, 300,'userData/pageIndex.html');
}

function unitQuery(){ //区域筛选ID界面
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}

function merQuery(){ //区域筛选ID界面
	selectFlag="merseq";
	pageQuery('列表选择','icon-search',600, 300,'merchant/pageIndex.html');
}

/*
 * 分页条件框筛选
 */
function pageSubmit(){
	
	queryParam["unitid"]=$("#pageunitid").val();
	queryParam["unitname"]=$("#pageunitname").val();
	queryParam["accountseq"]=$("#pageaccountseq").val();
	queryParam["cardno"]=$("#pagecardno").val();
	queryParam["userinfoseq"]=$("#pageuserinfoseq").val();
	queryParam["merseq"]=$("#pagemerseq").val();
	queryParam["branchid"]=$("#pagebranchid").val();
	queryParam["branchchn"]=$("#pagebranchchn").val();
	pageGrid.datagrid({queryParams: queryParam});
}

/*
 * 塞值
 */
function selectSubmit() {
	
	var rows=pageGrid.datagrid('getSelections');
	var num = rows.length;
	if (num==0){
		$('#cardno').val('');
	}else{
		
		if(selectFlag == 'cardno'){
			$('#cardno').val(rows[0].cardno);
			$('#name').val(rows[0].name);
		}
		
		if(selectFlag == 'unitid'){
			$('#unitid').val(rows[0].unitid);
		}
		
		if(selectFlag == 'merseq'){
			$('#merseq').val(rows[0].merseq);
		}
		
	}
	$('#pagePanel').dialog('close');
}


function download_file(){
	var elemIF = document.createElement("iframe");
	elemIF.src = "blackInfo/downloadExcelModel.html";//文件路径
	elemIF.style.display = "none";
	document.body.appendChild(elemIF);
	
}

