function posbrandDesc(value,rec){
	return rec.posbrandDesc;
}
function saveSubmit(){
	
	if(!$('#filefunc').val()){
		top.$.messager.alert('提示信息', '请填写功能描述信息！', 'info');
		return ;
	}
	
	if(!$('#catchfile').val()){
		top.$.messager.alert('提示信息', '请选择程序文件，注意文件名规范！', 'info');
		return ;
	}
	
	
	
	ajaxFileUpload($('#merseq').val(),$('#branchid').val(),$('#filefunc').val(),$('#postype').val(),$('#posbrand').val());
	
}

function add(){ //导入 界面
	if(isadminDisabled())return;
	addBase('文件导入','icon-add',530,300,'posTmsdata/detail.html');
}


function del(){
	if(isadminDisabled())return;
	executeSingleBase("posTmsdata/del.html","您确定要删除此TMS程序吗?","filename");
}


/*
 * 分页条件框筛选
 */
function pageSubmit(){
	queryParam["merseq"]=$("#pagemerseq").val();
	queryParam["branchchn"]=$("#pagebranchchn").val();
	queryParam["brancheng"]=$("#pagebrancheng").val();
	pageGrid.datagrid({queryParams: queryParam});
}

/*
 * 塞值
 */
function selectSubmit() {
	var rows=pageGrid.datagrid('getSelections');
	var num = rows.length;	
	if (num==0){
		if(selectFlag == 'merseq'){
			$('#merseq').val("");
			$('#branchid').val("");
		}else{
			$('#'+selectFlag).val('');
		}
	}else{
		
		if(selectFlag == 'merseq'){
			$('#merseq').val(rows[0].merseq);
			$('#branchid').val(rows[0].branchid);
		}
	}
	$('#pagePanel').dialog('close');
	
}

function merQuery(){ //分页筛选商户ID界面
	
	selectFlag="merseq";
	pageQuery('列表选择','icon-search',600, 300,'merchant/pageIndex.html');
}


function ajaxFileUpload(merseq,branchid,filefunc,postype,posbrand) {
	$.ajaxFileUpload  
    ({  
            url:'posTmsdata/upload.html?merseq='+merseq+'&branchid='+branchid+'&filefunc='+filefunc+'&postype='+postype+'&posbrand='+posbrand,  
            secureuri:false,  
            fileElementId:'catchfile',
            dataType: 'json',  
            success: function (theback, status)  
            {  
            	if (!theback.success){
					top.$.messager.alert('错误', theback.message, 'error');
            	}
				else{
					top.$.messager.alert('提示信息', theback.message, 'info',function(){
						winClose();
						grid.datagrid('reload');
					});
				}
            	
            },  
            error: function (data, status, e)  
            {  
                alert(data.status);  
                alert(data.message+" error:  "+e);  
            }  
        }  
    )  
    return false;  
}
