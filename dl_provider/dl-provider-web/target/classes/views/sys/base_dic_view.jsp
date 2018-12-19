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
	<form id="ff" name="ff" method="post" action="${path }/sys/baseDicController/doInput">
	 <input type="hidden" name="id"  id="id" value="${baseDic.id }"/>
	 <input type="hidden" name="state"  id="state" value="${baseDic.state }"/>
	    <div>
			<label for="activityName">字典编码:</label>
			${baseDic.dicCode }
			<font color="red">*</font>
			</div>
	     <div>
			<label for="activityType">字典值:</label>
			${baseDic.dicValue }
			<font color="red">*</font>
	    </div>
	     <div>
			<label for="otherOrganizer">字典类型:</label>
			${baseDic.dicType }
			<font color="red">*</font>
			</div>
	     <div>
			<label for="hostOrganizer">备注:</label>
			${baseDic.dicType }
	    </div>
    	<div>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="location.href='${path }/sys/baseDicController'">返回</a>
    	</div>
	</form>
</body>
</html>