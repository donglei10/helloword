 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../views/common/head.jsp"%>
<link rel="shortcut icon" href="${path }/static/images/favicon.ico" type="image/x-icon" />
<style>
	.ul_index{padding:2px;margin: 0px; width: 80%;list-style-type: none; } 
	.ul_index li{ padding:0px;margin: 2px; }
	.ul_index li a  {  line-height: 20px; }
    .ul_index li div{margin: 2px 0px;padding-left: 10px; padding-top: 2px;}
    .ul_index li div.hover{border: 1px dashed #99BBE8;background: #E0ECFF;cursor: pointer;}
    .ul_index li div.hover a{color: #416AA3;}
    .ul_index li div.selected{
            border: 1px solid #99BBE8;
            background: #E0ECFF;
            cursor: default;
        }
     .ul_index li div.selected a {
            color: #416AA3;
            font-weight: bold;
        }
	
</style>
<%
//BaseUser baseuser = (BaseUser) session.getAttribute("WEB_LOGIN_USER");
//if(baseuser==null){
//	 response.sendRedirect(request.getContextPath()+"/login");
//}
%>
<script type="text/javascript">
	function logout(){
		location.href="${path }";
	} 
</script>
</head>
<title>课堂集</title>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;background:#B3DFDA;background:url(${path }/static/image/index/top_bg.png) repeat; ">
		<img alt="" src="${path }/static/image/index/top_ico.png" style="margin-left: 15px; float:left;">
		<span style="float: right;line-height: 50px;padding-right: 20px;color: #999;font-size: 14px;">
			欢迎${WEB_LOGIN_USER.username}    <a style="cursor: pointer;" onclick="logout();">退出</a>
		</span>
	</div>
	<div data-options="region:'west',split:true,title:'菜单'" style="width:228px;padding:1px;overflow-x: hidden;">
		<div style="width:200px;height:auto;background:linear-gradient(to bottom, #eff5ff 0px, #e0ecff 100%) repeat-x scroll 0 0 rgba(0, 0, 0, 0);padding:5px;">
			<div class="easyui-panel" title="业务管理" collapsible="true" style="width:200px;height:auto;padding:10px;">
				<ul  class="ul_index">
					<li><div><a href="javaScript:void(-1);" onclick="menutable()">菜单管理</a></div></li>
					<li><div><a href="javaScript:void(-1);" onclick="qrcodetable()">桌子管理</a></div></li>
				</ul>
			</div>
			<div class="easyui-panel" title="系统管理" collapsible="true" style="width:200px;height:auto;padding:10px;">
				<ul  class="ul_index">
					<li><div><a href="javaScript:void(-1);" onclick="dicManager()">字典管理</a></div></li>
					<li><div><a href="javaScript:void(-1);" onclick="userManager()">用户管理</a></div></li>
					<li><div><a href="javaScript:void(-1);" onclick="menuManager()">菜单管理</a></div></li>
					<li><div><a href="javaScript:void(-1);" onclick="roleManager()">角色管理</a></div></li>
					<li><div><a href="javaScript:void(-1);" onclick="noticeManager()">角色管理</a></div></li>
				</ul>
			</div>
		</div>

	</div>
<!-- 	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div> -->
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;text-align: center;  background: url(${path }/static/image/index/bottom.png) center;"> 
	 </div>
	<div data-options="region:'center',title:' '" id="mainCenter" style="overflow: hidden;">
		<iframe id="cccc" style="width: 100%;height: 100%;border: 0px;" ></iframe>
<!-- 			<div class="easyui-layout" data-options="fit:true"> -->
<!-- 				<div data-options="region:'west',split:true" style="width:180px"></div> -->
<!-- 				<div data-options="region:'center'"></div> -->
<!-- 			</div> -->
	</div>
</body>
<script type="text/javascript">
	 
	function userManager(){
	 	$("#cccc").attr("src","${path }/sys/baseUserController");
	}
	function dicManager(){
	 	$("#cccc").attr("src","${path }/sys/baseDicController");
	}
	function menuManager(){
	 	$("#cccc").attr("src","${path }/sys/baseMenuController");
	}
	function roleManager(){
	 	$("#cccc").attr("src","${path }/sys/baseRoleController");
	}
	function noticeManager(){
	 	$("#cccc").attr("src","${path }/sys/noticeController");
	}
	
</script>
</html>