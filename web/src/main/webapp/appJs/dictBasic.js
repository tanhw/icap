function activeDesc(value,rec){
	return rec.activeDesc;
}

function setQuery(){
	$("#ccode").val(queryParam["ccode"]);
	$("#cdesc").val(queryParam["cdesc"]);
	$("#cvalue").val(queryParam["cvalue"]);
	$("#ctype").val(queryParam["ctype"]);
	$("#cupcode").val(queryParam["cupcode"]);
	$("#isactive").val(queryParam["isactive"]);
	$("#unitid").val(queryParam["unitid"]);
}
function querySubmit(){
	
	queryParam["ccode"]=$("#ccode").val();
	queryParam["cvalue"]=$("#cvalue").val();
	queryParam["cdesc"]=$("#cdesc").val();
	queryParam["ctype"]=$("#ctype").val();
	queryParam["isactive"]=$("#isactive").val();
	queryParam["cupcode"]=$("#cupcode").val();
	queryParam["unitid"]=$("#unitid").val();
	
	winClose();
	grid.datagrid({queryParams: queryParam});
}


function saveSubmit(){
	fmSubmitBase("fm");
}

function query(){ //查询界面
	adQueryBase('条件查询','icon-search',500,260,'dict/query.html');
}

function modify(){ //修改角色页面
	modifyBase('字典修改','icon-edit',500,260,'dict/detail.html','ccode');
}

function add(){ //新增 界面
	addBase('字典增加','icon-add',500,260,'dict/detail.html');
}
function del(){
	executeSingleBase("dict/del.html","您确定要删除记录吗?","ccode");
}



var selectFlag;
function unitQuery(){ //分页筛选单位ID界面
	selectFlag="unitid";
	pageQuery('列表选择','icon-search',600, 300,'unit/pageIndex.html');
}

function dictQuery(){ //分页字典单位ID界面
	selectFlag="cupcode";
	pageQuery('列表选择','icon-search',400, 300,'dict/pageIndex.html');
}

/*
 * 分页条件框筛选
 */
function pageSubmit(){
	queryParam["ccode"]=$("#pageccode").val();
	queryParam["cvalue"]=$("#pagecvalue").val();
	queryParam["unitid"]=$("#pageunitid").val();
	queryParam["unitname"]=$("#pageunitname").val();
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
		
		if(selectFlag == 'cupcode'){
			var id = rows[0].ccode;
			var code = $('#ccode').val();
			if(id == code){
				alert("父级字典不能设置为自己或相同");
			}else{
				$('#cupcode').val(id);
			}
			
		}
		if(selectFlag == 'unitid'){
			$('#unitid').val(rows[0].unitid);
		}
	}
	$('#pagePanel').dialog('close');
}


function updateCommonMap(){
	
	$.ajax({
		   async:false,
		   type: "POST",
		   url: "dict/updateCommonMap.html",
		   dataType: 'json',
		   success: function(theback){
			   
			   if (!theback.success)
					top.$.messager.alert('错误', theback.message, 'error',function(){
						if (theback.url){
							top.location.href=theback.url;
						}
					});
				else{
					top.$.messager.alert('提示信息', "字典缓存更新成功！！", 'info',function(){
						grid.datagrid('reload');
					});
				}
		   }
		});
	
}
 