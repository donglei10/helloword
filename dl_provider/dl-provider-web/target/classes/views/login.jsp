<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../views/common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="${path }/static/images/favicon.ico" type="image/x-icon" />
<title>活动集</title>
<style>
body,ul,ol,li,p,h1,h2,h3,h4,h5,h6,table,td,tr,th,div,dl,dt,dd,form,fieldset,img,cite,strong,em,div{margin:0;padding:0;border:0;}
h1, h2, h3, h4, h5, h6{ font-size:12px;}
img, table, td, th ,embed{ border:none;}
select,input{font-size:12px;}
ul, ol, li{ list-style-type:none; padding:0;}
a{color:#373634; text-decoration:none;}
a:visited{text-decoration:none;}
a:hover{ color:#FF0000;}
body{ font-family:"宋体"; font-size:12px; color:#666666;margin:0; padding:0; height:auto; margin:0 auto; background:url(${path }/static/image/index/bg.jpg) no-repeat top;}

.kdlogin{ width:980px; height:350px; margin:0 auto; margin-top:80px; }
.dlleft{ width:360px; height:380px; float:left;}
.dlright{ width:570px; height:380px; float:left;background:url(${path }/static/image/index/logn_06.gif) no-repeat; margin-left:50px;}
.dlbtn{ width:356px; height:54px;background:url(${path }/static/image/index/logn_13.gif) no-repeat; cursor:pointer; border:none;}
.zcbtn{ width:356px; height:54px;background:url(${path }/static/image/index/logn_15.jpg) no-repeat; cursor:pointer; border:none; }
.dlleft dl{ width:360px; height:auto; float:left;font-size:14px; font-weight:bold;color:#999999; }
.dlleft dl dt{ height:86px; width:300px;}
.dlleft dl dd{ margin-top:10px;}
.dlinput{ width:350px; height:47px; background:url(${path }/static/image/index/logn_10.gif) no-repeat; line-height:53px; font-size:16px; font-weight:bold; border:none; color:#999999; padding:3px; font-family:"黑体";}
.dlmm{ width:360px; height:30px; line-height:30px; margin:0; padding:0;}
.dlmm a{ float:right; padding-right:20px;color:#999999;}
</style>
<title>用户登录界面</title>
</head>
<script type="text/javascript">
 if("${reload}"=="reload"){
	 window.parent.location.href = "${path}/login";
 }
</script>
<body>
<div class="kdlogin">
	<div class="dlleft">
	    <dl>
	    	<dt><img src="${path }/static/image/index/logn_03.gif" /></dt>
				<c:if test="${reload !='reload' }">
					<form action="${path }/security" method="post">
					 <dd><input class="dlinput" type="text" id="username"  name="username" value="" placeholder="用户名（身份证号）"></dd>
				     <dd><input class="dlinput" type="password" id="password" name="password" value="" placeholder="密码"></dd>
				     <dd class="dlmm"><input type="checkbox"> 下次自动登录 <a href="${path }/sys/password/passOne">忘记密码</a></dd>
				     <dd><input  type="submit"  class="dlbtn" value=""></dd>
				     <dd><input type="button" class="zcbtn" onclick="location.href='${path }/sys/register/register'"></dd>
				     <dd id="show"><label><font color="red">${error }</font></label></dd>
					</form>
				</c:if>
	 	</dl>
    </div>
    <div class="dlright">
    </div>    
</div>
</body>
</html>
