<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta name="keywords" content="">
<meta name="description" content="">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<script type="text/javascript">
		var path = "${path}";
	</script>
	<link rel="stylesheet" type="text/css" href="${path }/static/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path }/static/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${path }/static/ajaxupload/uploadify.css">
	<link rel="stylesheet" type="text/css" href="${path }/static/css/style.css">
	<link rel="stylesheet" type="text/css" href="${path }/static/css/image.css">
	<link rel="stylesheet" type="text/css" href="${path }/static/css/base.css">
	<script type="text/javascript" src="${path }/static/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${path }/static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${path }/static/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${path }/static/validation/jquery.validate.js"></script>
	<script type="text/javascript" src="${path }/static/ajaxupload/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${path }/static/validation/additional-methods.min.js"></script>
	<script type="text/javascript" src="${path }/static/validation/messages_zh.min.js"></script>
	<script type="text/javascript" src="${path }/static/js/common/common.js"></script>
	<script   type="text/javascript" src="${path }/static/artdialog/jquery.artDialog.js?skin=default"></script>
	<script   type="text/javascript" src="${path }/static/artdialog/plugins/iframeTools.js"></script>
<style type="text/css">
	.invalid {
		color: red;
	}
</style>
<link rel="shortcut icon" href="${path }/static/images/favicon.ico" type="image/x-icon" />
<c:set value="R_004" var="rols" />
<c:forEach items="${WEB_LOGIN_USER.roles }" var="v" varStatus="vs">
	<c:set value="${rols },${v.code }" var="rols" />
</c:forEach>