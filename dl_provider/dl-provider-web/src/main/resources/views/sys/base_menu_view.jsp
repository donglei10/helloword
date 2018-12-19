<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<html>
<head>
<style type="text/css">
	.table_th th{
		background-color: linear-gradient(to bottom, #eff5ff 0px, #e0ecff 100%) repeat-x scroll 0 0 rgba(0, 0, 0, 0);
	}
	。buttion_file{
		width: 15px;
		height:10px;
	}
</style>
</head>
<body >
	<form id="ff" name="ff" method="post" action="${path }/sys/baseMenuController/doInput">
	 <input type="hidden" name="id"  id="id" value="${baseMenu.id }"/>
	 <input type="hidden" name="state"  id="state" value="${baseMenu.state }"/>
	    <div>
			<label for="text">菜单名称:</label>
			${baseMenu.text }
			<font color="red">*</font>
			</div>
		<div>
			<label for="parentName">父级菜单:</label>
			${baseMenu.parentName }
			<font color="red">*</font>
			</div>
	     <div>
			<label for="url">菜单连接:</label>
			${baseMenu.url }
			<font color="red">*</font>
	    </div>
	     <div>
			<label for="ico">菜单图标:</label>
			${baseMenu.ico }
			<font color="red">*</font>
			</div>
	     <div>
			<label for="remark">备注:</label>
			${baseMenu.remark }
	    </div>
    	<div>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="location.href='${path }/sys/baseMenuController'">返回</a>
    	</div>
	</form>
</body>
</html>