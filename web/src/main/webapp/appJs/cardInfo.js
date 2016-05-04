function createtimeDesc(value,rec){
	return rec.createtimeDesc;
}

function statusDesc(value,rec){
	return rec.statusDesc
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
	adQueryBase('条件查询','icon-search',430,180,'card/query.html');
}

function modify(){ //修改
	if(isadminDisabled())return;
	modifyBase('名单修改','icon-edit',480,180,'card/detail.html','cardid');
}

function add(){ //增加
	if(isadminDisabled())return;
	addBase('名单添加','icon-add',480,180,'card/detail.html');
}

function del(){
	if(isadminDisabled())return;
	executeSingleBase("card/del.html","确定此操作?","cardid");
}

function cardnoQuery(){ //筛选ID界面
	selectFlag="cardno";
	pageQuery('列表选择','icon-search',600, 300,'userData/pageIndex.html');
}

function campQuery(){ //筛选ID界面
	selectFlag="camp";
	pageQuery('列表选择','icon-search',600, 300,'camp/pageIndex.html');
}

/*
 * 分页条件框筛选
 */
function pageSubmit(){
	queryParam["cardno"]=$("#pagecardno").val();
	queryParam["name"]=$("#pagename").val();
	queryParam["paperid"]=$("#pagepaperid").val();
	queryParam["campname"]=$("#pagecampname").val();
	queryParam["campid"]=$("#pagecampid").val();
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

		if(selectFlag == 'camp'){
			$('#campid').val(rows[0].campid);
		}

	}
	$('#pagePanel').dialog('close');
}


