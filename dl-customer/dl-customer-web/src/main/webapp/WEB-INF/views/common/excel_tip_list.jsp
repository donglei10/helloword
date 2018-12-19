<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<html>
<head>
</head>
<body >
		<table class="easyui-datagrid" id="dg" title="Excel导入错误提示" style="width:100%;height:485px;"  ></table>
		<div id="tb" style="padding:5px;height:auto;">
			<div class="easyui-panel" style="padding:5px;text-align: center;">
						<a href="#" class="easyui-linkbutton" iconCls="icon-back" onclick="back()" >返回</a>
			</div>
			</div>
	<script type="text/javascript">
		$(function(){
			$('#dg').datagrid({
				  toolbar:'#tb',
				  rownumbers:true,
				  singleSelect:true,
				  pagination:false,
				  fitColumns:true,
				  singleSelect:false,
				  selectOnCheck:true,
				  checkOnSelect:true,
				  columns:[[  
				        {field:'rownumStr',title:'行',fixed:true,width:50},    
				        {field:'cellnumStr',title:'列',fixed:true,width:50},    
				        {field:'message',title:'提示',width:100},    
				    ]],
			});
			$('#dg').datagrid({ data:  ${list} });
		});
		
		function back(){
			location.href = "${path}/${url}";	
		}
	</script>
</body>
</html>