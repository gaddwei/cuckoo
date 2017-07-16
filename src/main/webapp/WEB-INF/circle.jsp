<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>  布谷游记</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/base/bootstrap-3.3.7/dist/css/bootstrap.css">  
	<link rel="stylesheet" href="<%=request.getContextPath()%>/homePage/css/index.css"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/homePage/css/upfile.css"/>
	<style type="text/css">
		body {background-image:url(../../base/img/bg.jpg);background-repeat: repeat;background-attachment:fixed;}
	</style>
</head>
<body >
	<jsp:include page="../homePage/module/head.jsp" flush="true"/>
	<div style="height: 300px;width: 100%; position: ">
		<jsp:include page="../homePage/module/person-show.jsp" flush="true"/> 
	</div>
<div class="note-show1">
	<div class="content">
		<ul class="circle-content" id="circle-content">
		</ul>
		<button class="btn-more-circle btn btn-primary" onclick="btn_more_circle()">点击加载更多...</button>
	</div>
	</div>
<script type="text/javascript">
 	var circleNotes = ${circleNotes};
 	var selfInfo = ${selfInfo};
 	console.log(selfInfo);
</script>
<!-- 底部 -->
<div>
	<jsp:include page="../homePage/module/foot.jsp" flush="true"/> 
	</div>
	
	<!-- 加载js文件 -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/base/jquery/jquery-2.1.1.min.js"></script>
	<!-- bootstrap js -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/base/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
	<!-- JQUERY 异步图片上传js 插件 -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/base/jquery/ajaxfileupload.js"></script>
	<!-- 操作cookie的插件 -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/base/js/cookie_util.js"></script>
	<!-- 本网页的js -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/homePage/js/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/homePage/js/head.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/homePage/js/circle.js"></script>
	
	
	<jsp:include   page="../homePage/alert/alert-register.jsp" flush="true"/> 
	<jsp:include   page="../homePage/alert/alert-login.jsp" flush="true"/> 
	<jsp:include   page="../homePage/alert/alert-transmit.jsp" flush="true"/> 
</body>
</html>
