<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<script type="text/javascript">
    var map = new Map();
jQuery.fn.rowspan = function(colIdx,rows) { //封装的一个JQuery小插件
    return this.each(function(){
        var that;

        var key = colIdx;
        that = map[key];

        $('tr:gt('+ rows +')', this).each(function(index) {

            $('td:eq('+colIdx+')', this).filter(':hidden').each(function(){
                that = null;
                var key = colIdx;
                map[key] = null;
            });

            $('td:eq('+colIdx+')', this).each(function() {
                        //行
                        if ($(this).filter(':visible').html() == 'null') {
                            var now = $(this);
                            var num = 1;
                            while(now.next('td').html()  == 'null'){
                                ++num;
                                now = now.next('td');
                                now.hide();
                            }
                            var i;
                            for(i=0;i<6;i++){
                                now = now.next('td');
                                now.attr("style",'text-align: right;padding-right: 30px;background-color: #DDD;color: red;');
                            }
                            $(this).attr("colspan",num);
                            if($(this).prev('td').html()){
                                $(this).html("小计：");
                            }else{
                                $(this).html("总计：");
                            }
                            $(this).attr("style",'background-color: #DDD;color: red;text-align: left;padding-left: 20px;');
                        }
                        //列
                        if (that!=null && $(this).filter(':visible').html() == $(that).html()) {
                            rowspan = $(that).attr("rowSpan");
                            if (rowspan == undefined) {
                                $(that).attr("rowSpan",1);
                                rowspan = $(that).attr("rowSpan");
                            }
                            rowspan = Number(rowspan)+1;
                            $(that).attr("rowSpan",rowspan);
                            $(this).hide();
                        } else {
                            that = this;
                            var key = colIdx;
                            map[key] = that;
                        }
                    });
        });
    });
}
   $(document).ready(function(){
       var tempStr = $('#colectCss').val();
       var tempArr = tempStr.split(",");

       for(temp in tempArr){
           $('#collectTable').rowspan(temp,0);
       }

       var flag;
       $('#moreinfo').click(function(){

           if(flag == true){
               return
           }
           flag = true;

           var modify = $('#modify').val();
           if(modify == 'false'){
               top.$.messager.alert('提示信息', '已改变条件，请重新点击“条件查询”按钮！', 'info');
               flag = false;
           }

           var id = $('#confid').val();
           var pageSize = $('#pageSize').val();
           var pageNum = $('#pageNum').val();
           var recordSum = $('#recordSum').val();


           ++pageNum

           var whereContent = "?";
           $('#wheretable input').each(function(){
               var value = $(this).val();
               if(value){
                   var name = $(this).attr('name');
                   whereContent = whereContent + "&" + name + "=" + value;
               }
           });
           whereContent = whereContent + "&crdkind=" + $('#crdkind').val();
           whereContent = whereContent + "&status=" + $('#status').val();
           var url = "collect/more.html" + whereContent;
           $.ajax({
               type: "POST",
               url: url,
               data: {
                   id:id,
                   pageNum:pageNum
                },
               dataType:"html",
               success: function(html){
                   var rows = $('#collectTable tr').size();
                   $('#collectTable tr:last').after(html);

                   var tempStr = $('#colectCss').val();
                   var tempArr = tempStr.split(",");

                   for(temp in tempArr){
                       $('#collectTable').rowspan(temp,rows -1);
                   }

                   $('#pageNum').val(pageNum);

                   if((pageNum * pageSize) >= recordSum ){
                       $('#moreinfo').hide();
                   }
                   flag = false;
               }
           });
       });
   });
</script>

<div data-options="region:'',title:''" style="margin-top:30px;"   class="table_css">
    <input type="hidden" id="colectCss" value="${colectCss}">
    <input type="hidden" id="pageSize" value="${rollPagelist.pageSize}">
    <input type="hidden" id="pageNum" value="${rollPagelist.pageNum}">
    <input type="hidden" id="recordSum" value="${rollPagelist.recordSum}">
    <input type="hidden" id="modify" value="true">

    <table id="collectTable" class="datagrid-htable" style="margin-bottom: 10px;"  border="0" cellspacing="0" cellpadding="0" width="90%">
        <thead>
        <tr class="tr_css">
            <c:if test="${notfind}">
            <td colspan="${colspan + 6}"><h1 style="font-size: 20px;">${headline }</h1><h3><font color="red">（${startTime }至${endTime }） 共${rollPagelist.recordSum}行 </font></h3></td>
            </c:if>
            <c:if test="${!notfind}"><h1 style="font-size: 20px; color:red;">无法查询此机构或机构不存在</h1></c:if>
        </tr>
        ${title}
        ${body}
        </thead>
    </table>
    <div style="text-align: center;width: 90% ">
        <c:if test="${rollPagelist.recordSum > (rollPagelist.pageNum *  rollPagelist.pageSize)}">
            <a class="easyui-linkbutton" id="moreinfo" data-options="size:'large'" style="width: 250px;" ><h1 style="font-size: 20px;">点击加载更多</h1></a>
        </c:if>
    </div>
</div>