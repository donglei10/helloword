// JavaScript Document

$(document).ready(function(){
	
	//检查账号设置中的输入格式是否正确
	var realName = $('input[name=realName]'); 
	var department = $('select[name=department]');
	var classId = $('input[name=class]'); 
	var studentID = $('input[name=studentID]'); 
	var gender = $('input[name=gender]'); 
	var idCard = $('input[name=idCard]'); 
	var phoneNumber = $('input[name=phoneNumber]'); 
	var height = $('input[name=height]'); 
	var policy = $('select[name=policy]'); 
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
		if(!checkSubmitFlg){
			checkSubmitFlg = true;
			$(this).css("background","#C5C5C5");
			return true;
		}else{
			$("#J_loginError").find('span').html("？正在处理中...");
			return false;
		}
		
	});  
	
	//密码修改
	var oldPassword = $('input[name=oldPassword]');
	var newPassword1 = $('input[name=newPassword1]');
	var newPassword2 = $('input[name=newPassword2]');
	
	$("#J_passwordBtn").click(function(){
		$("#J_loginError").find('span').html("");
		if(oldPassword.val() == ''){
			$("#J_loginError").find('span').html("？原密码不能为空");
			return false;
		}
		if(newPassword1.val() == ''){
			$("#J_loginError").find('span').html("？密码不能为空");
			return false;
		}
		if(newPassword2.val() == ''){
			$("#J_loginError").find('span').html("？请再输入一次新密码");
			return false;
		}
		if(newPassword1.val() != newPassword2.val()){
			$("#J_loginError").find('span').html("？两次输入密码不一致");
			return false;
		}
	});
	
	//检查活动发布是否正确
	var activityName = $('input[name=activityName]');
	var activityApprove = $('select[name=activityApprove]');
	
	$("#J_activityBtn").click(function(){
		$("#J_activityError").find('span').html("");
		if(activityName.val() == ''){
			$("#J_activityError").find('span').html("？活动名称不能为空");
			return false;
		}
		if(activityApprove.val() == ''){
			$("#J_activityError").find('span').html("？请选择审批单位");
			return false;
		}
	});
});















































