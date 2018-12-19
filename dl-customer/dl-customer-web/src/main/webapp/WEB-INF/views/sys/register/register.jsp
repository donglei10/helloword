<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
</head>
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
<section class="divW container register-con" >
	<h1>用户注册</h1>
    <section class="divW login user-info">
        <form action="${path }/sys/register/doRegister" method="post">
            <div class="login-input">
                <label><span>* </span>姓名：</label>
                <input name="username" id="username" value="" type="text" class="divBorderRadius-6" placeholder="姓名" >
            </div>
            <div class="login-input">
                <label><span>* </span>院系：</label>
                <select name="stDepartmentName" class="divBorderRadius-6">
                        <option value="">选择院系</option>
                        <option value="少年班">少年班</option>
                        <option value="数学科学学院">数学科学学院</option>
                        <option value="物理学院">物理学院</option>
                        <option value="化学与材料科学学院">化学与材料科学学院</option>
                        <option value="生命科学学院">生命科学学院</option>
                        <option value="工程科学学院">工程科学学院</option>
                        <option value="信息科学技术学院">信息科学技术学院</option>
                        <option value="计算机科学与技术学院">计算机科学与技术学院</option>
                        <option value="地球和空间科学学院">地球和空间科学学院</option>
                        <option value="管理学院">管理学院</option>
                        <option value="公共事务学院">公共事务学院</option>
                        <option value="人文与社会科学学院">人文与社会科学学院</option>
                        <option value="核科学技术学院">核科学技术学院</option>
                        <option value="环境科学与光电技术学院">环境科学与光电技术学院</option>
                        <option value="软件学院">软件学院</option>
                        <option value="苏州研究院">苏州研究院</option>
                        <option value="上海研究院">上海研究院</option>
                        <option value="国家同步辐射实验室">国家同步辐射实验室</option>
                        <option value="合肥微尺度物质科学国家实验室">合肥微尺度物质科学国家实验室</option>
                        <option value="火灾科学国家重点实验室">火灾科学国家重点实验室</option>
                </select>
            </div>
            <div class="login-input">
                <label><span>* </span>班级：</label>
                <input name="stClass" value="" type="text" class="divBorderRadius-6" placeholder="班级" >
            </div>
            <div class="login-input">
                <label><span>* </span>学号：</label>
                <input name="stCard" value="" type="text" class="divBorderRadius-6" placeholder="学号" >
            </div>
            <div class="login-input radio-input">
                <label><span>* </span>性别：</label>
                <input class="" type="radio" name="gender" checked="checked" value="男" /><i>男</i>
                <input class="" type="radio" name="gender"  <c:if test="${youthMembersExt.gender=='女' }">checked="checked"</c:if> value="女" /><i>女</i>
            </div>
            <div class="login-input">
                <label><span>* </span>身份证号：</label>
                <input name="idCard" value="" type="text" onblur="vaidate()" class="divBorderRadius-6" placeholder="身份证号" >
            </div>
            <div class="login-input">
                <label><span>* </span>手机号：</label>
                <input name="mobilePhone" value="" type="text" onblur="vaidate()" class="divBorderRadius-6" placeholder="手机号" >
            </div>
            <div class="login-input">
                <label><span>* </span>身高(cm)：</label>
                <input name="height" value="" type="text" class="divBorderRadius-6" placeholder="身高">
            </div>
            <div class="login-input">
                <label><span>* </span>政治面貌：</label>
                <select name="politicalAffiliation" class="divBorderRadius-6">
                        <option value="">选择政治面貌</option>
                        <option value="中共党员">中共党员</option>
                        <option value="中共预备党员">中共预备党员</option>
                        <option value="共青团员">共青团员</option>
                        <option value="群众">群众</option>
                        <option value="其他">其他</option>
                </select>
            </div>
            <div class="login-input">
                <label><span>* </span>邮箱：</label>
                <input name="email" value="" type="text" class="divBorderRadius-6" placeholder="邮箱">
            </div>
            
            <h4></h4>
            <div class="login-input">
                <label>昵称：</label>
                <input name="nickname" value="" type="text" class="divBorderRadius-6" placeholder="昵称">
            </div>
            <div class="login-input">
                <label>住址：</label>
                <input name="address" value="" type="text" class="divBorderRadius-6" placeholder="住址">
            </div>
            <div class="login-input">
                <label>个性签名：</label>
                <input name="artSignName" value="" type="text" class="divBorderRadius-6" placeholder="个性签名">
            </div>
            <div class="login-input">
                <label>QQ号：</label>
                <input name="qq" value="" type="text" class="divBorderRadius-6" placeholder="QQ号">
            </div>
            <div class="login-input">
                <label>兴趣爱好：</label>
                <textarea name="interest" class="divBorderRadius-2" placeholder="兴趣爱好"></textarea>
            </div>
            
            <div class="login-btn">
                <input type="submit" value="注册" class="divBorderRadius-4" id="J_userInfoBtn">
            </div>
        </form>
        <div class="login-error" id="J_loginError">
            <!--如果用户不存在就显示下面这条信息-->
            <span id="error">${error }</span>
        </div>
    </section>
</section>

<script>
$(document).ready(function(){
	var realName = $('input[name=username]'); 
	var department = $('input[name=stDepartmentName]');
	var classId = $('input[name=stClass]'); 
	var studentID = $('input[name=stCard]'); 
	var gender = $('input[name=gender]'); 
	var idCard = $('input[name=idCard]'); 
	var phoneNumber = $('input[name=mobilePhone]'); 
	var height = $('input[name=height]'); 
	var policy = $('input[name=politicalAffiliation]'); 
	var email = $('input[name=email]');
	var checkSubmitFlg = false;
	$("#J_userInfoBtn").click(function(){
		$("#J_loginError").find('span').html("");
		
		if(realName.val() == ''){
			$("#J_loginError").find('span').html("？姓名，不能为空");
			return false;
		}
		
		if(department.val() == ''){
			$("#J_loginError").find('span').html("？院系，不能为空");
			return false;
		}
		
		if(classId.val() == ''){
			$("#J_loginError").find('span').html("？班级，不能为空");
			return false;
		}
		
		if(studentID.val() == ''){
			$("#J_loginError").find('span').html("？学号，不能为空");
			return false;
		}
		
		if(idCard.val() == ''){
			$("#J_loginError").find('span').html("？身份证号，不能为空");
			return false;
		}
		
		if(phoneNumber.val() == ''){
			$("#J_loginError").find('span').html("？手机号，不能为空");
			return false;
		}
		if(!phoneNumber.val().match(/^1[0-9]{10}$/)){
			$("#J_loginError").find('span').html("？请填写11位手机号");
			return false;
		}
		
		if(height.val() == ''){
			$("#J_loginError").find('span').html("？身高，不能为空");
			return false;
		}
		if(!height.val().match(/^[0-9]{3}$/)){
			$("#J_loginError").find('span').html("？请填写身高，3位数字");
			return false;
		}
		
		if(policy.val() == ''){
			$("#J_loginError").find('span').html("？政治面貌，不能为空");
			return false;
		}
		
		if(email.val() == ''){
			$("#J_loginError").find('span').html("？邮箱，不能为空");
			return false;
		}
		if(!email.val().match(/^[_.0-9a-z-a-z-]+@([0-9a-z][0-9a-z-]+.)+[a-z]{2,4}$/)){
			$("#J_loginError").find('span').html("？请填写带@的正确邮箱");
			return false;
		}
		
		//防止数据重复提交
 		if(!checkSubmitFlg && bl){
 			checkSubmitFlg = true;
 			$(this).css("background","#C5C5C5");
 			return true;
 		}else{
 			$("#J_loginError").find('span').html("？正在处理中...");
 			return false;
 		} 
	});  
		
});

function vaidate(){
	var bl = false;
	$.post("${path}/sys/register/ishaveData", { "idCard": $('input[name=idCard]').val(), "mobilePhone": $('input[name=mobilePhone]').val() },function(v){
		if(v.idCardMsg){
			$("#J_loginError").find('span').html(v.idCardMsg); 
		}
		if(v.phoneMsg){
			$("#J_loginError").find('span').append('\<br>'+v.phoneMsg); 
		}
	} );
	return bl;
}
 
</script>

<footer class="divW footer">
	<p>© All Rights Reserved. 活动集(2015)</p>
</footer>

</body>
</html>
