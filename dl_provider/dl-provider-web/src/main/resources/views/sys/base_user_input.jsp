<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<html>
<head>
</head>
<body >
	<div class="easyui-panel" style="padding:5px;width: 100%" title="登录用户管理->用户编辑">
	<form id="ff" name="ff" method="post" action="${path }/sys/baseUserController/doInput">
	 <input type="hidden" name="id"  id="id" value="${user.id }"/>
	 <input type="hidden" name="state"  id="state" value="${user.state }"/>
	 <input type="hidden" name="createTime"  id="createTime" value="<fmt:formatDate value="${user.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
	  <table class="my_table"  cellpadding="0" cellspacing="0">
	   <tr>
		   	<th width="25%">
				<label for="userid">登录ID:</label>
			</th>
			<td>
				&nbsp;<input class="easyui-validatebox" type="text" id="userid" name="userid" data-options="required:true" value="${user.userid }"/>
				<font color="red">*</font>
			</td>
		 </tr>
		 <tr>
		 	<th>
				<label for="username">用户名:</label>
			</th>
			<td>
				&nbsp;<input class="easyui-validatebox" type="text" name="username" data-options="required:true" value="${user.username }"/>
				<font color="red">*</font>
			</td>
		</tr>
	   <tr>
		   	<th width="25%">
				<label for="password">密码:</label>
			</th>
			<td>
				&nbsp;<input class="easyui-validatebox" type="password" name="password" data-options="required:true" value="${user.password }"/>
				<font color="red">*</font>
			</td>
		</tr>
		 <tr>
		 	<th>
				<label for="phone">用户手机:</label>
			</th>
			<td>
				&nbsp;<input class="easyui-validatebox" type="text" name="phone" data-options="required:false"    value="${user.phone }"/>
		 	</td>
		 </tr>
		 <tr>
		 	<td colspan="2" align="center">
	    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveSubmit()">保存</a>
	    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="location.href='${path }/sys/baseUserController'">返回</a>
		 	</td>
		 </tr>
		</table>
	</form>
	</div>
	<script type="text/javascript">
	 
	 var vad =  $("#ff").validate({
			debug:true,
			errorClass:"invalid",
			rules: {
				userid: {
					required:true,
					remote:{
						url:"${path}/sys/baseUserController/validateId",
						type:"get",
						datatype:'json',
						data:{
							userid:function(){
								return $("#userid").val();
							}
						}
					}
				},
				username: "required",
				password: "required",
			} ,
			messages: {
				userid: {
					required:"必须填写登录ID！",
					remote:"用户ID已存在!"
				},
				username: "必须填写用户名！",
				password: "必须填写密码！",
			} 
		});
	

	function saveSubmit(){
		var v =  vad.form();
		if(v==true){
			$('#ff')[0].submit();
		}
	}
</script>
</body>
</html>