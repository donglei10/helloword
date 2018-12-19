<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>

        <link type="text/css" rel="stylesheet" href="${path }/static/desktops/css/css.css"/>
        <link type="text/css" rel="stylesheet" href="${path }/static/desktops/css/jquery.tool.css"/>
        <link type="text/css" rel="stylesheet" href="${path }/static/desktops/css/smartMenu.css"/>
        <script type="text/javascript" src="${path }/static/desktops/js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript">
        var shortcut = [
 						[0,"团员管理","${path}/static/desktops/images/icos/logo.jpg","http://localhost:9999/ustc/work/activityController",1100,600],
 						[1,"活动管理","${path}/static/desktops/images/icos/bus.png","http://localhost:9999/ustc/work/activityController",1100,600]
 						];
            $().ready(function(){
                document.body.onselectstart = document.body.ondrag = function(){return false;}
                Core.init();
            });
        </script>
    </head>
    <body id="lxcn" style="background:url(${path}/static/desktops/images/background.jpg) repeat right bottom transparent;">
        <div id="task-bar">
            <ul class="task-window">                
            </ul>
            <ul class="task-panel">
                <li class="sys" title="测试桌面"><b class="">测试桌面</b></li>
            </ul>
        </div>
        <div id="desk"><ul></ul></div>
    </body>
     <script type="text/javascript" src="${path }/static/desktops/js/jquery.tool.js"></script>
        <script type="text/javascript" src="${path }/static/desktops/js/templates.js"></script>
        <script type="text/javascript" src="${path }/static/desktops/js/jquery-smartMenu.js"></script>
        <script type="text/javascript" src="${path }/static/desktops/js/core.js"></script>
</html>