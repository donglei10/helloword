<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<html>
<head>
</head>
<body >
	<div>
	</div>
	    <input type="hidden" name="id"  id="id" value="${baseRole.id }"/>
		<table class="easyui-datagrid" id="dg" title="角色管理-角色用户管理" style="width:100%;height:485px;"  ></table>
		<div id="tb" style="padding:5px;height:auto;">
			<div style="margin-bottom:5px; border-bottom:  #95b8e7 1px solid;" >
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="add()">添加</a>
<!-- 				<a href="#"  class="easyui-linkbutton"	 iconCls="icon-edit" plain="true"></a> -->
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a> -->
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a> -->
				<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delAll()">删除</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="exportExcel()">导出</a> -->
			</div>
			<div >
				登录ID: <input id="userid" class="easyui-input" style="width:120px">
				用户名: <input  id="username" class="easyui-input" style="width:120px">
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
				  singleSelect:false,
				  selectOnCheck:true,
				  checkOnSelect:true,
				  url:'${path }/sys/baseRoleController/listUsersData',
				  queryParams:{
					  "id":$("#id").val()
				  },
				  method:'post' ,
				    columns:[[    
						{field:'id',checkbox:true},
				        {field:'userid',title:'登录ID',width:100},    
				        {field:'username',title:'用户名',width:100},    
				        {field:'phone',title:'手机号码',width:100,align:'right'} ,   
				        {field:'createTime',title:'创建时间',width:100,align:'right'}  ,  
				        {field:'none',title:'操作',width:130,align:'right',fixed:true,formatter:actonMethod}    
				    ]]    

			}).datagrid('getPager') .pagination({pageSize:10, pageList: [10,15,20,30,50]});  
		})
		
		function loadGrid(){
			$('#dg').datagrid('load',{
				//"userid":$("#userid").val(),
				//"username":$("#username").val(),
				"id":$("#id").val()
			});    
		}
		
		/**
		* 操作方法
		*/
		function actonMethod(value,row,index){
				var view = '' ;
				view += '<a href="#" iconCls="icon-cut" class="easyui-linkbutton"  onclick="del('+row.id+')">移除</a> || ';
				//view += '<a href="#" iconCls="icon-edit"  onclick="update('+row.id+')">修改</a> || ';
				view += '<a href="#" iconCls="icon-search"  onclick="view('+row.id+')">查看</a> ';
				return view;
		}
		
		function actonMethodAddUser(value,row,index){
			var view = '' ;
			//view += '<a href="#" iconCls="icon-cut" class="easyui-linkbutton"  onclick="addUser('+row.id+')">添加</a> || ';
			//view += '<a href="#" iconCls="icon-search"  onclick="view('+row.id+')">查看</a> ';
			return view;
		}
		/**
		* 
		*/
		function del(id){
			//TO-DO
			/*$.messager.confirm('确认','您确认想从角色中移除此用户吗？',function(r){    
				if(r){
					$.post('${path }/sys/baseUserController/del',{id:id},function (data){
						if(data=="success"){
							$.messager.show({title:'移除提示',msg:'移除成功！',showType:'show'});
							$('#dg').datagrid('reload'); 
						}else{
							$.messager.show({title:'移除提示',msg:'移除失败！',showType:'show'});
						}
					});
				} 
			});  */
		}
		/**
		* 删除方法
		*/
		function delAll(){
			var checkedItems = $('#dg').datagrid('getChecked');
			var ids = [];
			var names = [];
			$.each(checkedItems, function(index, item){
				ids.push(item.id);
				names.push((index+1)+".userid="+item.userid+"<br/>");
			}); 
			if(ids.length>0){
				$.messager.confirm('确认','您确认想要删除以下记录吗？<br/>'+(names.join("")),function(r){  
					if(r){
						$.post('${path }/sys/baseUserController/dels',{ids:ids.join(",")},function (data){
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
		}
		/**
		* 修改方法
		*/
		function update(id){
			var url = "${path }/sys/baseUserController/input?id="+id;
			location.href=url;
		}
		/**
		* 新增方法
		*/
		function add(){
			//var url = "${path }/sys/baseUserController/input?id=";
			//location.href=url;
			var div = $('<div id="addRoleUser" class="easyui-window"  style="width:560px;height:140px;padding:10px;"><table class="easyui-datagrid" id="addRoleUserdg" title="角色用户管理" style="width:100%;height:485px;"  ></table></div>');
			var $newwin;
			$newwin = div.window({
			    title: '添加人员',
			    width: 560,
			    height: 400,
			    top: 50,
			    shadow: true,
			    modal: true,
			    iconCls: 'icon-redo',
			    closed: true,
			    minimizable: false,
			    maximizable: false,
			    collapsible: false
			});
			$newwin.window('open');
			
			$('#addRoleUserdg').datagrid({
				  //toolbar:'#tb',
				  rownumbers:true,
				  singleSelect:true,
				  pagination:true,
				  fitColumns:true,
				  singleSelect:false,
				  selectOnCheck:true,
				  checkOnSelect:true,
				  url:'${path }/sys/baseUserController/listData',
				  method:'post' ,
				    columns:[[    
						{field:'id',checkbox:true},
				        {field:'userid',title:'登录ID',width:100},    
				        {field:'username',title:'用户名',width:100},    
				        {field:'phone',title:'手机号码',width:100,align:'right'} ,   
				        {field:'createTime',title:'创建时间',width:100,align:'right'}  ,  
				        {field:'none',title:'操作',width:130,align:'right',fixed:true,formatter:actonMethodAddUser}    
				    ]]    

			}).datagrid('getPager') .pagination({pageSize:10, pageList: [10,15,20,30,50]});  

		}
		/**
		* 查看方法
		*/
		function view(id){
			var url = "${path }/sys/baseUserController/view?id="+id;
			location.href=url;
		}
		
		
		function addUser(){
			
		}
	</script>
</body>
</html>