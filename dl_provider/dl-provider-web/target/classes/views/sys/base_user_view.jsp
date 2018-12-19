<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<html>
<head>
</head>
<body >
<div class="easyui-panel" style="padding:5px;width: 100%" title="登录用户管理->用户查看">
	  <table class="my_table"  cellpadding="0" cellspacing="0">
	   <tr>
		   	<th width="25%">
				<label for="userid">登录ID:</label>
			</th>
			<td>
				${user.userid }
			</td>
		 </tr>
		 <tr>
		 	<th>
				<label for="username">用户名:</label>
			</th>
			<td>
				${user.username }
			</td>
		</tr>
		 <tr>
		 	<th>
				<label for="phone">用户手机:</label>
			</th>
			<td>
				${user.phone }
		 	</td>
		 </tr>
		  <tr>
		   	<th width="25%">
				<label for="password">创建时间:</label>
			</th>
			<td>
				<fmt:formatDate value="${user.createTime }" pattern="yyyy-MM-dd HH:mm"/>
			</td>
		</tr>
		 <tr>
		 	<td colspan="2" align="center">
	    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-back'" onclick="location.href='${path }/sys/baseUserController'">返回</a>
		 	</td>
		 </tr>
		</table>
		</div>
</body>
</html>