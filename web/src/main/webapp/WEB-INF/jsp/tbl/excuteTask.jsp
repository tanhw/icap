<%--
  Created by IntelliJ IDEA.
  User: tanhaiwen
  Date: 2015/9/10
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<script>
  $("td:even").style="text-align: left; ";
  $("td:odd").style="text-align:right; width:30px";

  function ww4(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    var h = date.getHours();
    return  y+''+(m<10?('0'+m):m)+''+(d<10?('0'+d):d);

  }
  function w4(s){
    var y = s.substring(0,4);
    var m = s.substring(4,6);
    var d = s.substring(6,8);
    if (s != ''){
      return new Date(y,m-1,d);
    } else {
      return new Date();
    }
  }
</script>
<div class="easyui-layout" fit="true">
  <div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
    <form id="fm" method="post" action ="syspara/exceuteTaskSave.html">
      <input type="hidden" name="taskseq" value="${taskseq}">
      <table border="0" cellpadding="0" cellspacing="0" id="ct">
        <tr style="height: 40px;" >
          <td style="text-align: right;">*任务日期：</td>
          <td>
            <input type="text" id="taskdate" name="taskdate" required="true" maxlength="8" data-options="formatter:ww4,parser:w4" editable="false" class="easyui-datebox textbox input155"/>
          </td>
          <td style="text-align: right;">*任务类型：</td>
          <td>
            <select id="tasktype" name="tasktype" required="true"  class="easyui-validatebox textbox input155">
              <option value="01">生成对帐文件</option>
              <option value="02">勾兑结果文件</option>
              <option value="03">统计报表</option>
            </select>
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>