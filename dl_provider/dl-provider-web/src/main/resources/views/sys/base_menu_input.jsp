<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<html>
<head>
<style type="text/css">
	.table_th th{
		background-color: linear-gradient(to bottom, #eff5ff 0px, #e0ecff 100%) repeat-x scroll 0 0 rgba(0, 0, 0, 0);
	}
	.buttion_file{
		width: 15px;
		height:10px;
	}
</style>
</head>
<body >

	<form id="fff" name="fff" method="post" action="${path }/sys/baseMenuController/doInput">
	 <input type="hidden" name="id"  id="id" value="${baseMenu.id }"/>
	 <input type="hidden" name="state"  id="state" value="${baseMenu.state }"/>
	    <div>
			<label for="text">菜单名称:</label>
			<input class="easyui-validatebox" type="text" id="text" name="text"  data-options="required:true" value="${baseMenu.text }" />
			<font color="red">*</font>
			</div>
	     <div>
			<label for="url">连接:</label>
			<input class="easyui-validatebox" type="text" id="url" name="url" data-options="required:true" value="${baseMenu.url }"/>
			<font color="red">*</font>
	    </div>
	     <div>
			<label for="url">图标:</label>
			<input class="easyui-validatebox" type="text" id="ico" name="ico" data-options="required:true" value="${baseMenu.ico }"/>
	    </div>
	    <div>
			<label for="parentName">父级菜单:</label>
			<input class="easyui-validatebox" type="text" name="parentName" data-options="required:true" value="${baseMenu.parentName }"/>
			<input class="easyui-validatebox" type="hidden" name="parentid" data-options="required:true" value="${baseMenu.parentid }"/>
		</div>
		<div>
			<label for="remark">备注:</label>
			<input class="easyui-validatebox" type="text" name="remark"   value="${baseMenu.remark }"/>
	    </div>
    	<div>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveSubmit()">保存</a>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="location.href='${path }/sys/baseMenuController'">返回</a>
    	</div>
	</form>
	<script type="text/javascript">
	 
	 var vad =  $("#fff").validate({
			debug:true,
			errorClass:"invalid",
			rules: {
				text: "required",
				url: "required",
			} ,
			messages: {
				text: "必须填写菜单名称！",
				url: "必须填写菜单连接！",
			} 
		});
	

	function saveSubmit(type){
			var v =  vad.form();
			if(v==true){
				$('#fff')[0].submit();
			}
		}
	
</script>
	　
</body>
</html>