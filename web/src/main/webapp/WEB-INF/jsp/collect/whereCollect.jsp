<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<script>

    /**
    *提交条件
     */
    function queryCollect(){
        var confid = $('#confid').val();
        var whereContent = "?id=" + confid;
        $('#wheretable input').each(function(){
            var value = $(this).val();
            if(value){
                var name = $(this).attr('name');
                whereContent = whereContent + "&" + name + "=" + value;
            }
        });
        whereContent = whereContent + "&crdkind=" + $('#crdkind').val();
        whereContent = whereContent + "&status=" + $('#status').val();
        $('#cc').layout('panel','center').panel({href:'collect/tableCollect.html' + whereContent}); //提交
    }

    /**
     *重置清空所有输入条件
     */
    function cleanWhere(){
        $('#wheretable input').each(function(){
            var id = $(this).attr('id');
            if(!(id =='startTime'|| id == 'endTime'))$(this).val('');
        });

        $('#crdkind').val('');
        $('#status').val('');
    }

    function exportCollect(){

        $('#fn').submit();
        showtask();
    }

    function showtask() {

        var myh = (window.screen.height - 600) / 2;
        var myw = (window.screen.width - 600) / 2;

        $('#taskpage').window({
            title: "进度",
            iconCls: "icon-edit",
            width: 500,
            height: 100,
            resizable: false,
            minimizable: false,
            closable:false,
            maximizable: false,
            modal: true,
            top: myh,
            left: myw
        }).window('open').window("refresh");

        $('#p').progressbar({
            value: 1,
            width: 400
        });

      var timer = setInterval(function(){
            var myDate = new Date();
            var s =myDate.getSeconds();
            var flag = ((s %10) == 0);
            if(flag){
                $.ajax({
                    type: "POST",
                    async:true,
                    url: 'collect/refreshTime.html',
                    dataType:"json",
                    success: function(task){

                        var value = task;
                        if (value < 100){
                            $('#p').progressbar('setValue', value);
                        }else{
                            $('#taskpage').window('close');
                            clearInterval(timer);
                        }
                    }
                })
            }

        } , 1000);



    }

    $(document).ready(function(){
        $('#wheretable input').change(function(){
            $('#modify').val(false);
        });

        $('#startTime').datebox({
            onSelect: function(date){
                $('#modify').val(false);
            }
        });

        $('#endTime').datebox({
            onSelect: function(date){
                $('#modify').val(false);
            }
        });
    });

</script>
<form id="fn" method="post" action="collect/expLineSummaryColl.html">
<input type="hidden" id="confid" name="id" value="${confid}">
<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
    <div id="taskpage" style="padding:5px;background: #fafafa;" class="easyui-window" closed="true" >
        <div id="p" style="margin-top: 15px;margin-left: 45px;"></div>
    </div>
    <table border="0" cellpadding="0" cellspacing="0" id="wheretable">
       
        <c:forEach items="${whereMap}" var="map" varStatus="status">
        <c:if test="${status.index % 3 ==0}">
        <tr style="height: 40px;" >
        </c:if>
            <td style="text-align: right;">${map.key}：</td>
            <td>
                <c:if test="${map.value == 'crdkind'}">
                    <select id="${map.value}" name="${map.value}" class="easyui-validatebox textbox input155">
                        <option value="" >请选择 </option>
                        <c:forEach items="${crdkindList }" var="list">
                            <option value="${list.cvalue}" >${list.cdesc }</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${map.value != 'crdkind'}">
                <input type="text" id="${map.value}" name="${map.value}" class="easyui-validatebox textbox input155" />
                </c:if>
            </td>
        <c:if test="${status.index % 3 == 2}">
        </tr>
        </c:if>
        </c:forEach>
        
        <tr style="height: 40px;" >
            <td style="text-align: right;">状态：</td>
            <td>
                <select id="status" name="status" class="easyui-validatebox textbox input155">
                    <option value="" >请选择 </option>
                    <option value="1" >正常交易</option>
                    <option value="2" >异常交易</option>
                </select>
            </td>
            <td style="text-align: right;">开始时间:</td>
            <td>
                <input type="text" id="startTime"	name="startTime"  required="true" editable="false"
                       value="${startTime }" class="easyui-datebox textbox input155"  />
            </td>
            <td style="text-align: right;">结束时间：</td>
            <td>
                <input type="text" id="endTime"	name="endTime"  required="true" editable="false"
                       value="${endTime }"
                       class="easyui-datebox textbox input155"   />
            </td>
        </tr>
        <tr>
            <td colspan="6" align="center">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0)" onclick="queryCollect()">条件查询</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0)" onclick="exportCollect()">导出报表</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" href="javascript:void(0)" onclick="cleanWhere()">清空条件</a>
            </td>
        </tr>
    </table>
</div>
</form>