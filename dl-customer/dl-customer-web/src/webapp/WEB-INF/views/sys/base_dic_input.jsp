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
	<form id="fff" name="fff" method="post" action="${path }/sys/baseDicController/doInput">
	 <input type="hidden" name="id"  id="id" value="${baseDic.id }"/>
	 <input type="hidden" name="state"  id="state" value="${baseDic.state }"/>
	    <div>
			<label for="dicCode">字典编码:</label>
			<input class="easyui-validatebox" type="text" id="dicCode" name="dicCode"  data-options="required:true" value="${baseDic.dicCode }" />
			<font color="red">*</font>
			</div>
	     <div>
			<label for="dicValue">字典值:</label>
			<input class="easyui-validatebox" type="text" id="dicValue" name="dicValue" data-options="required:true" value="${baseDic.dicValue }"/>
			<font color="red">*</font>
	    </div>
	    <div>
			<label for="dicType">字典类型:</label>
			<input class="easyui-validatebox" type="text" name="dicType" data-options="required:true" value="${baseDic.dicType }"/>
			<font color="red">*</font>
		</div>
	    <div>
			<label for="dicText">备注:</label>
			<input class="easyui-validatebox" type="text" name="dicText"   value="${baseDic.dicText }"/>
	    </div>
    	<div>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveSubmit()">保存</a>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="location.href='${path }/sys/baseDicController'">返回</a>
    	</div>
	</form>
	<script type="text/javascript">
	 
	 var vad =  $("#fff").validate({
			debug:true,
			errorClass:"invalid",
			rules: {
				dicCode: "required",
			} ,
			messages: {
				dicCode: "必须填写活动名称！",
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