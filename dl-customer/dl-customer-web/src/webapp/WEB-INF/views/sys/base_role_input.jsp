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
	<form id="fff" name="fff" method="post" action="${path }/sys/baseRoleController/doInput">
	 <input type="hidden" name="id"  id="id" value="${baseRole.id }"/>
	 <input type="hidden" name="state"  id="state" value="${baseRole.state }"/>
	 <input type="hidden" name="createTime"  id="createTime" value="<fmt:formatDate value="${baseRole.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
	    <div>
			<label for="code">角色编码:</label>
			<input class="easyui-validatebox" type="text" id="code" name="code"  data-options="required:true" value="${baseRole.code }" />
			<font color="red">*</font>
			</div>
	     <div>
			<label for="name">角色名称</label>
			<input class="easyui-validatebox" type="text" id="name" name="name" data-options="required:true" value="${baseRole.name }"/>
			<font color="red">*</font>
	    </div>
	    <div>
			<label for="type">角色类型:</label>
			<input class="easyui-validatebox" type="text" name="type" data-options="required:true" value="${baseRole.type }"/>
			<font color="red">*</font>
		</div>
	    <div>
			<label for="remark">备注:</label>
			<input class="easyui-validatebox" type="text" name="remark"   value="${baseRole.remark }"/>
	    </div>
    	<div>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveSubmit()">保存</a>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="location.href='${path }/sys/baseRoleController'">返回</a>
    	</div>
	</form>
	<script type="text/javascript">
	 
	 var vad =  $("#fff").validate({
			debug:true,
			errorClass:"invalid",
			rules: {
				code: "required",
				name: "required",
				type: "required",
			} ,
			messages: {
				code: "必须填写角色编码！",
				name: "必须填写角色名称！",
				type: "必须填写角色类型！",
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