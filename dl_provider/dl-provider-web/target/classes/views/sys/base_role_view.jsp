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
	<form id="ff" name="ff" method="post" action="${path }/sys/baseRoleController/doInput">
	 <input type="hidden" name="id"  id="id" value="${baseRole.id }"/>
	 <input type="hidden" name="state"  id="state" value="${baseRole.state }"/>
	    <div>
			<label for="code">角色编码:</label>
			${baseRole.code }
			<font color="red">*</font>
			</div>
	     <div>
			<label for="name">角色名称:</label>
			${baseRole.name }
			<font color="red">*</font>
	    </div>
	     <div>
			<label for="type">角色类型:</label>
			${baseRole.type }
			<font color="red">*</font>
			</div>
	     <div>
			<label for="remark">备注:</label>
			${baseRole.remark }
	    </div>
    	<div>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="location.href='${path }/sys/baseRoleController'">返回</a>
    	</div>
	</form>
</body>
</html>