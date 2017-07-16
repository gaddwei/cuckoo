<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@page import="cn.cuckoo.note.entity.*"  %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>  布谷游记</title>
	<link rel="stylesheet" href="../base/bootstrap-3.3.7/dist/css/bootstrap.css">  
	<link rel="stylesheet" href="../homePage/css/index.css"/>
	<link rel="stylesheet" href="../homePage/css/upfile.css"/>
</head>
<body >
	<jsp:include page="../homePage/module/head.jsp" flush="true"/>
	<div style="height: 300px;width: 100%; position: ">
		<jsp:include page="../homePage/module/person-show.jsp" flush="true"/> 
		<h3 id="note-show-title">标题:${noteMap.note.title}</h3>
	</div>
<div class="note-show">
	<div class="content">
		${noteMap.note.content}
	</div>
	
	<div class="issue-comment">
		<h4>评论:</h4>
		<textarea id="tr-comment" rows="3" cols="30"></textarea>
		<div><button class="btn btn-primary" id="comment_issue" onclick="comment_issue()">&nbsp;发布&nbsp;</button><span id="comment_issue_msg">请先登录</span></div>
	</div>
	<div class="comment">
		<ul style="list-style-type: none;margin-left: -30px;margin-right: 15px;">
			<li style="float: left;"><i class="glyphicon glyphicon-retweet" onclick="note_transponds()">${noteMap.transponds.size()}</i></li>
			<li style="margin-right: opx; border-left:solid red 2px; "></li>
			<li style="float: left;"><i class="glyphicon glyphicon-edit" onclick="note_comments()">${noteMap.comments.size()}</i></li>
			<li style="float: left;"><i id="note-praise-flag" class="glyphicon glyphicon-heart-empty " onclick="note_praise()">${noteMap.praises.size()}</i></li>
		</ul>
		<p style="clear: left; height: 5px;"></p>
		<div style=" width: 90%;">
			<div id="div-note-comment" >
				<strong style="margin-left: 16px;margin-top: 10px;">评论:</strong>
				<ul id="comments-ul" style="list-style-type: none;">
				</ul>
			</div>
			<div  id="div-note-transponds">
				<strong style="margin-left: 16px;margin-top: 10px;">转发:</strong>
				<div style="position: relative; ">
					<textarea rows="5" cols="50" class="note-transponds" id="transmit-num-text"></textarea>
					<button id="btn-note-transponds" class="btn" onclick="note_submittransponds()">转发</button>
				</div>
				<fieldset><legend class="transmit-num-head">  &nbsp;</legend></fieldset>
				<ul id="transponds-ul" style="list-style-type: none;">
				</ul>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
 	var noteMap = ${noteMapStr};
 	console.log(noteMap);
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/homePage/js/note.js"></script>
	
	
	<jsp:include   page="../homePage/alert/alert-register.jsp" flush="true"/> 
	<jsp:include   page="../homePage/alert/alert-login.jsp" flush="true"/> 
	<jsp:include   page="../homePage/alert/alert-transmit.jsp" flush="true"/> 
</body>
</html>
