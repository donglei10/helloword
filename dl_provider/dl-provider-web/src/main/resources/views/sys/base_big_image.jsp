<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<html>
<head>
</head>
<body >
	<form id="ff" name="ff" method="post" >
	    <div style="width: 100%;text-align: center;">
	    	<img alt="" src="${path }/fileUpDownController/download/${pathImage }">
	    </div>
	    <br/>
    	<div style="width: 100%;text-align: center;">
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="art.dialog.close();">关闭</a>
    	</div>
	</form>
</body>
</html>