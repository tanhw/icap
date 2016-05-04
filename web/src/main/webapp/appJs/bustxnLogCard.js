function cardmodelDesc(value,rec){
	return rec.cardmodelDesc;
}

function busiidDesc(value,rec){
	return rec.busiidDesc;
}

function setQuery(){
	$("#cardno").val(queryParam["cardno"]);
	$("#cardmodel").val(queryParam["cardmodel"]);
}
function querySubmit(){
	if(dateDiff())return ;
	
	queryParam["cardcode"]=$("#cardcode").val();
	queryParam["endTime"]=$("input[name='endTime']").val();
	queryParam["startTime"]=$("input[name='startTime']").val();
	queryParam["cardmodel"]=$("#cardmodel").val();
	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	if(dateDiff())return ;
	fmSubmitBase("fm");
	winClose();
}

function cardQuery(){ //查询界面
	adQueryBase('条件查询','icon-search',500,200,'bustxnlog/cardQuery.html');
}

function expCardTxnBase(){
	exportTxnBase('导出报表','icon-edit',500,200,'bustxnlog/cardQuery.html','',false);
}

function exportTxnBase(title,icon,width,height,url,idField,gridTab,fun){
	var _fun=saveSubmit;
	if(fun)_fun=fun;
	var myw =($(window).width()-fixWidth-width)/2;  
	var myh = ($(window).height()-fixHeight-height)/2;
	var _grid=grid;
	if (gridTab)_grid=gridTab;
		url=pageUrl(url,"timeTemp",(new Date()).valueOf());
		$('#detailPanel').dialog({
			title: title,
			iconCls:icon,
			width: width,
			height:height,
			closed: true,
			top:myh-20,
			left:myw,
			href:url,
			buttons:[{
				text:'导出报表',
				iconCls:'icon-ok',
				handler:_fun
			},{
				text:'关闭窗口',
				iconCls:'icon-cancel',
				handler:winClose
			}]
		}).dialog('open');
		if (!urlRef){
			urlRef=true;
		}
		else
			$('#detailPanel').dialog("refresh");
}

