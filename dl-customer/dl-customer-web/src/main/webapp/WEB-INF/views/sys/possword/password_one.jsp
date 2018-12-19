<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<html>
<head>
</head>
<body >

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
	<h1>忘记密码</h1>
    <section class="divW login user-info retrieve-pass">
        <div class="retrieve-pass-step">
            <h3 class="retrieve-pass-step-current">填写手机号</h3>
            <h3>重置密码</h3>
        </div>
        <form id="pass" name="pass" action="${path }/sys/password/doPass" method="post">
            <div class="login-input">
                <label><span>* </span>账号：</label>
                <input name="userid" value="${userid }" type="text" class="divBorderRadius-6" placeholder="账号" >
            </div>
            <div class="login-input">
                <label><span>* </span>手机号码：</label>
                <input name="phone" value="" type="text" class="divBorderRadius-6" placeholder="填写手机号码" >
            </div>
            <div class="login-input">
                <label><span>* </span>验证码：</label>
                <input name="verificationCode" value="" type="text" style="width: 20%;"   style="float: left;"  placeholder="验证码" >
                <input  id="J_retrievePassBtn_1"  value="发送" type="button" style="width: 20%;" class="divBorderRadius-2" />
            </div>
            <div class="login-input">
            	<div>
             	 	<label style="text-align: right;">注意：</label><label style="text-align: left;"><font color="red">手机验证码，三分内有效！</font></label> 
            	</div>
            </div>
            <div class="login-input">
            	<div>
             	 	 <font color="red" id="error">${error }</font>
            	</div>
            </div>
            <div class="login-btn">
                <input type="submit" value="下一步" class="divBorderRadius-4" id="J_retrievePassBtn">
            </div>
        </form>
    </section>
</section>
<footer class="divW footer">
	<p>© All Rights Reserved. 活动集(2015)</p>
</footer>
<script>
$(document).ready(function(){ 
	var telephone = $('input[name=phone]'); 
	$("#J_retrievePassBtn").click(function(){
		if(telephone.val() == ''){
			alert("手机号不能为空！");
			return false;
		}
		if(!telephone.val().match(/^1[0-9]{10}$/)){
			alert("请填写11位手机号！");
			return false;
		}
		$("#pass").submit();
	});  
	$("#J_retrievePassBtn_1").click(function(){
		var url = "${path}/sys/password/sendMessage";
		$.ajax({
			  type: 'POST',
			  url: url,
			  data: {
				  "userid": $('input[name=userid]').val(),
				  "phone": $('input[name=phone]').val()
			  },
			  dataType: "json",
			  success:function(v){
			     if(v && v.error){
			    	 $("#error").text(v.error);
			     }
				}
			}); 
	});  
});
</script>
</body>
</html>