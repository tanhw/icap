<%@ page language="java" pageEncoding="UTF-8" %>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>${systemTitle }</title>
    <script type="text/javascript">
        if (top != self) {
            alert("系统登录超时,请重新登录！");
            if (top.location != self.location) {
                top.location = self.location;
            }
        }
    </script>

    <%@include file="common/headCommon.jsp" %>
    <script>
        $(document).ready(function () {
            var lastLoginTimeFlag = ${lastLoginTimeFlag};
            if (lastLoginTimeFlag) {
                modifyPasswd();
            }
        });

        function modifyPasswd() {

            var myh = (window.screen.height - 330) / 2;
            var myw = (window.screen.width - 330) / 2;
            var url = "myPassword.html";
            url = pageUrl(url, "timeTemp", (new Date()).valueOf());
            $('#indexPanel').window({
                title: "密码修改",
                iconCls: "icon-edit",
                width: 280,
                height: 230,
                resizable: false,
                minimizable: false,
                closable:false,
                maximizable: false,
                collapsible: false,
                modal: true,
                top: myh,
                left: myw,
                href: url
            }).window('open').window("refresh");

        }

        function indexWinClose() {
            $('#indexPanel').window('close');
        }

        function tabsContextmenu() {
            $(".tabs-inner").bind('contextmenu', function (e) {
                $('#tabsMM').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
                var subtitle = $(this).children("span").text();
                $('#tabsMM').data("currtab", subtitle);
                var currTab = $('#mainTab').tabs('getTab', subtitle);
                if (!currTab.panel('options').closable) {
                    $('#tabsMM').menu('disableItem', '#mm-tabclose');
                    $('#tabsMM').menu('disableItem', '#mm-refresh');
                } else {
                    $('#tabsMM').menu('enableItem', '#mm-tabclose');
                    $('#tabsMM').menu('enableItem', '#mm-refresh');
                }
                return false;
            });

        }

        function tabsContextmenuEven() {
            //关闭当前
            $('#mm-tabclose').click(function () {
                var currtab_title = $('#tabsMM').data("currtab");
                var currTab = $('#mainTab').tabs('getTab', currtab_title);
                if (!currTab.panel('options').closable) return;
                $('#mainTab').tabs('close', currtab_title);
            });
            //全部关闭
            $('#mm-tabcloseall').click(function () {
                var _tabs = $('#mainTab').tabs('tabs');
                for (var i = 0; i < _tabs.length; i++) {
                    var _tab_op = _tabs[i].panel('options');
                    if (_tab_op.closable) {
                        $('#mainTab').tabs('close', _tab_op.title);
                        i--;
                    }
                }
            });
            //关闭其它
            $('#mm-tabcloseother').click(function () {
                var currtab_title = $('#tabsMM').data("currtab");

                var _tabs = $('#mainTab').tabs('tabs');
                for (var i = 0; i < _tabs.length; i++) {
                    var _tab_op = _tabs[i].panel('options');
                    if (_tab_op.closable && _tab_op.title != currtab_title) {
                        $('#mainTab').tabs('close', _tab_op.title);
                        i--;
                    }
                }
            });
            //刷新
            $('#mm-refresh').click(function () {
                var currtab_title = $('#tabsMM').data("currtab");
                var currTab = $('#mainTab').tabs('getTab', currtab_title);
                if (currTab && currTab.find('iframe').length > 0) {
                    var _refresh_iframe = $(currTab.find('iframe')[0]);
                    _refresh_iframe.attr("src", _refresh_iframe.attr("src"));
                }
                $('#mainTab').tabs('select', currtab_title);
            });
        }

        function addTab(menuName, nodeId, url) {

            if (checkUrl(url)) {
                if ($('#mainTab').tabs('exists', menuName)) {
                    $('#mainTab').tabs('select', menuName);
                } else {
                    $('#mainTab').tabs('add', {
                        title: menuName,
                        content: createFrame(url),
                        cache: false,
                        closable: true
                    });
                    tabsContextmenu();
                }
            } else {
                eval(url);
            }
        }


        function createFrame(url) {
            var s = '<div style="width:100%;height:100%;"><iframe name="mainFrame" scrolling="no" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe></div>';
            return s;
        }

        $(function () {
            /*
             $("#leftMenu").tree({
             onClick:function(node){
             if($(this).tree('isLeaf', node.target))
             addTab(node.text,node.id,node.attributes.url);
             else{
             if (node.attributes.url!="")
             addTab(node.text,node.id,node.attributes.url);
             else
             $(this).tree('toggle', node.target);
             }
             }
             });
             */
            tabsContextmenu();
            tabsContextmenuEven();

        });
    </script>
</head>

<body class="easyui-layout">
<div data-options="region:'north'" border="true"
     style="height:65px;overflow:hidden;background-color: white;/* background-image: url('images/top-img-1.png'); */">
    <%@include file="common/top.jsp" %>
</div>
<div data-options="region:'west'" split="true" title="功能菜单" style="width:180px;padding:0px;overflow:auto;">
    <c:import url="/leftMenu.html" charEncoding="utf-8"></c:import>
</div>
<div data-options="region:'center'" title="我的工作区" id="mainPanle">
    <div id="mainTab" class="easyui-tabs" fit="true" border="false">
        <div title="首页" style="padding:3px 3px 0px 3px;">
            <div style="height: 100%;width: 100%;">
                <img alt="登录图片" width="100%" src=""/>
            </div>
        </div>
    </div>
</div>
<div id="indexPanel" style="padding:5px;background: #fafafa;" class="easyui-window" closed="true"></div>
<div id="tabsMM" class="easyui-menu" style="width:150px;">
    <div id="mm-tabclose">关闭当前标签页</div>
    <div id="mm-tabcloseall">关闭全部标签页</div>
    <div id="mm-tabcloseother">关闭其它标签页</div>
    <div class="menu-sep"></div>
    <div id="mm-refresh">刷新当前标签页</div>
</div>
</body>
</html>
