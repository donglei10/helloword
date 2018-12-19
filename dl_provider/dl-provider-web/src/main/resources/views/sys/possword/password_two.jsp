<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<html>
<head>
</head>
<body >
<body>
<header id="header">
	<div class="divW header-sub">
        <div class="logo">
            <a href="#" title="活动集">
                <img src="${path }/static/images/logo.png" alt="logo">
            </a>
        </div>
    </div>
</header>
<section class="divW container register-con">
	<h1>修改密码</h1>
    <section class="divW login user-info retrieve-pass">
        <div class="retrieve-pass-step">
            <h3 >填写手机号</h3>
            <h3 class="retrieve-pass-step-current">重置密码</h3>
        </div>
        <form action="${path }/sys/password/passTwo" method="post">
        	<input name="userid" value="${userid }" type="hidden" class="divBorderRadius-6" placeholder="账号" >
            <div class="login-input">
                <label><span>* </span>新密码：</label>
                <input name="password" value="" type="password" class="divBorderRadius-6" placeholder="填写新密码" >
            </div>
            <div class="login-input">
                <label><span>* </span>新密码确认：</label>
                <input name="password1" value="" type="password" class="divBorderRadius-6" placeholder="再次确认新密码" >
            </div>
            
            <div class="login-btn">
                <input type="submit" value="保存" class="divBorderRadius-4" id="J_retrievePassBtn">
            </div>
        </form>
    </section>
</section>
<script>
$(document).ready(function(){ 
	var password = $('input[name=password]'); 
	var password1= $('input[name=password1]'); 
	$("#J_retrievePassBtn").click(function(){
		if(password.val() == '' || password1.val() == ''){
			alert("密码不能为空！");
			return false;
		}
		if(password.val() != password1.val()){
			alert("两次密码不一致！");
			return false;
		}
	});  
});
</script>

<footer class="divW footer">
	<p>© All Rights Reserved. 活动集(2015)</p>
</footer>

</body>
</html>
 