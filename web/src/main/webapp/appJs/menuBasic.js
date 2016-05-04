function menuLevelDesc(value,rec){
	return rec.menuLevelDesc;
}

function menukindDesc(value,rec){
	return rec.menukindDesc;
}

function menuPositionDesc(value,rec){
	if(value == 1) {
		return "左边菜单";
	} else if(value == 2) {
		return "上边菜单";
	} else if(value == 3) {
		return "下边菜单";
	} else {
		return "功能按钮";
	}
}


function setQuery(){
	$("#menucode").val(queryParam["menucode"]);
	$("#menuname").val(queryParam["menuname"]);
	$("#menulevel").val(queryParam["menulevel"]);
	$("#upmenu").val(queryParam["upmenu"]);
	$("#isactive").val(queryParam["isactive"]);
	$("#menukind").val(queryParam["menukind"]);
}
function querySubmit(){
	
	queryParam["menucode"]=$("#menucode").val();
	queryParam["menuname"]=$("#menuname").val();
	queryParam["menulevel"]=$("#menulevel").val();
	queryParam["upmenu"]=$("#upmenu").val();
	queryParam["isactive"]=$("#isactive").val();
	queryParam["menukind"]=$("#menukind").val();

	winClose();
	grid.datagrid({queryParams: queryParam});
}

function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',500,230,'menu/menuQuery.html?menucode=S000011');
}

function modify(){ //修改 界面
	modifyBase('菜单修改','icon-edit',550,350,'menu/detail.html?menucode=S000011','id');
}

function add(){ //新增 界面
	addBase('添加菜单','icon-add',550,350,'menu/detail.html?menucode=S000012');
}
function del(){
	executeSingleBase("menu/del.html","您确定要删除记录吗?","id");
}

function defaultDblClick(){
//	modify();
}


//根据不同的级别显示不同的上级菜单
function _showUpMenu(){
	var level = $("#menulevel").val();
	if(level!=1){
		$.ajax({
			url:"menu/showUpMenu.html",
			data:{
				level:level
			},
			type:"post",
			dataType:"html",
			success:function(data){
				$("#upMenuTd").html(data);
			},
			error:function(data){
				$("#upMenuTd").html("<span style='color:red'>系统查询异常，请稍后再试<span>");
			}
			
		});
	}else{
		$("#upMenuTd").html("<span style='color:green'>一级菜单不存在上级菜单<span>");
	}
}
