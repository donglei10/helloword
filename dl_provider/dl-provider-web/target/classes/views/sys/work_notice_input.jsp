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

	<form id="fff" name="fff" method="post" action="${path }/sys/noticeController/doInput">
	<div class="easyui-panel" style="padding:5px;width: 100%" title="公告管理->公告编辑">
	 <input type="hidden" name="id"  id="id" value="${workNotice.id }"/>
	  <table class="my_table"  cellpadding="0" cellspacing="0">
	   <tr>
			<th width="15%">公告标题:</th>
			<td width="85%">
				<input class="easyui-validatebox" type="text" id="title" name="title"  data-options="required:true" value="${workNotice.title }" />
				<font color="red">*</font>
			</td>
		</tr>
		<tr>
			<th >公告内容:</th>
			<td>
				<textarea id="contents" name="contents" class="easyui-validatebox" style="width: 70%" rows="2"  >${workNotice.contents }</textarea>
				<font color="red">*</font>
			</td>
	    </tr>
	    <tr>
	    	<td colspan="2" align="center">
		    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveSubmit()">保存</a>
	    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="location.href='${path }/sys/noticeController'">返回</a>
	    	</td>
	    </tr>
    	</table>
    </div>
	</form>
	<script type="text/javascript">
	 
	 var vad =  $("#fff").validate({
			debug:true,
			errorClass:"invalid",
			rules: {
				title: "required",
				contents: "required",
			} ,
			messages: {
				title: "必须填写公告标题！",
				contents: "必须填写公告内容！",
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