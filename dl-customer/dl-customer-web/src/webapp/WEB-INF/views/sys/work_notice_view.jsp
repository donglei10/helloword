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
	<div class="easyui-panel" style="padding:5px;width: 100%" title="公告管理->公告查看">
	 <input type="hidden" name="id"  id="id" value="${workNotice.id }"/>
	  <table class="my_table"  cellpadding="0" cellspacing="0">
	   <tr>
			<th width="15%">标题:</th>
			<td width="35%">
				${workNotice.title }
			</td>
			<th width="15%">创建时间:</th>
			<td width="35%">
				<fmt:formatDate value="${workNotice.createTime }" pattern="yyyy-MM-dd HH:mm"/>
			</td>
		</tr>
		<tr>
			<th >内容:</th>
			<td colspan="3">
				${workNotice.contents }
			</td>
	    </tr>
	    <tr>
	    	<td colspan="4" align="center">
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