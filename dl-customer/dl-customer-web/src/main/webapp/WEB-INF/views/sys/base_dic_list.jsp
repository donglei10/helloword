<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<html>
<style>
	。search_table {
		
	}
	。search_table tr td {
		border:  #95b8e7  1px solid;
	}
	
</style>
<head>
</head>
<body >
		<table class="easyui-datagrid" id="dg" title="系统管理-字典管理" style="width:100%;height:485px;"  ></table>
		<div id="tb" style="padding:5px;height:auto;">
			<div style="padding:5px;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">添加</a>
<!-- 				<a href="#"  class="easyui-linkbutton"	 iconCls="icon-edit" plain="true"></a> -->
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a> -->
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a> -->
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			</div>
			<div class="easyui-panel" style="padding:5px;">
						字典编码:<input id="dicCode"  class="easyui-input" style="width:120px;">
						字典值:<input id="dicValue" class="easyui-input" style="width:120px">
						<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadGrid()" >查询</a>
			</div>
			</div>
	
	<script type="text/javascript">
		$(function(){
			$('#dg').datagrid({
				  toolbar:'#tb',
				  rownumbers:true,
				  singleSelect:true,
				  pagination:true,
				  fitColumns:true,
				  method:"post",
				  url:'${path }/sys/baseDicController/listData',
				  method:'post' ,
				    columns:[[    
				        {field:'dicCode',title:'字典编码',width:100},    
				        {field:'dicValue',title:'字典值',width:100},    
				        {field:'dicType',title:'字典类型',width:100},    
				        {field:'dicText',title:'说明',width:100},    
				        {field:'id',title:'操作',width:130,align:'right',fixed:true,formatter:actonMethod}    
				    ]],
				    onLoadSuccess:function(){ 
			            $('#dg').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题 
			        },     
			}).datagrid('getPager');// .pagination({pageSize:10, pageList: [10,15,20,30,50]});  
		});
		
		function loadGrid(){
			$('#dg').datagrid('load',{
				"dicCode":$("#dicCode").val(),
				"dicValue":$("#dicValue").val()
			});    
		}
		
		/**
		* 操作方法
		*/
		function actonMethod(value,row,index){
				var view = '' ;
				view += '<a href="#" iconCls="icon-cut" class="easyui-linkbutton"  onclick="del('+value+')">删除</a> || ';
				view += '<a href="#" iconCls="icon-edit"  onclick="update('+value+')">修改</a> || ';
				view += '<a href="#" iconCls="icon-search"  onclick="view('+value+')">查看</a> ';
				return view;
		}
		/**
		* 删除方法
		*/
		function del(id){
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
				if(r){
					$.post('${path }/sys/baseDicController/del',{id:id},function (data){
						if(data=="success"){
							$.messager.show({title:'删除提示',msg:'数据删除成功！',showType:'show'});
							$('#dg').datagrid('reload'); 
						}else{
							$.messager.show({title:'删除提示',msg:'数据删除失败！',showType:'show'});
						}
					});
				} 
			});  
		}
		/**
		* 修改方法
		*/
		function update(id){
			var url = "${path }/sys/baseDicController/input?id="+id;
			location.href=url;
		}
		/**
		* 新增方法
		*/
		function add(){
			var url = "${path }/sys/baseDicController/input?id=";
			location.href=url;
		}
		/**
		* 查看方法
		*/
		function view(id){
			var url = "${path }/sys/baseDicController/view?id="+id;
			location.href=url;
		}
	</script>
</body>
</html>