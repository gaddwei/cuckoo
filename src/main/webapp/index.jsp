<%@page contentType="text/html; utf-8" pageEncoding="UTF-8"%>
<%@page import="cn.cuckoo.homepage.entity.HomeImg"%>
<%@ page import="cn.cuckoo.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>  布谷游记</title>
	<link rel="stylesheet" href="base/bootstrap-3.3.7/dist/css/bootstrap.css">  
	<link href="http://localhost:8080/ueditor1_4_3-utf8-jsp/themes/default/css/ueditor.min.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" href="homePage/css/index.css"/>
	<link rel="stylesheet" href="homePage/css/upfile.css"/>
</head>
<body >
	<jsp:include   page="homePage/module/head.jsp" flush="true"/>
  <!-- 轮播图片 -->
	<div id="myCarousel" class="carousel slide">
  		<ol class="carousel-indicators" >
  		 	 <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
   			 <li data-target="#myCarousel" data-slide-to="1"></li>
   			 <li data-target="#myCarousel" data-slide-to="2"></li>
   			 <li data-target="#myCarousel" data-slide-to="3"></li>
   			 <li data-target="#myCarousel" data-slide-to="4"></li>
  		</ol>
  <!-- Carousel items -->
  		<div class="carousel-inner">
  		<%  
  				
  				List<HomeImg> list =cn.cuckoo.util.Util.gethomeImgList();
  				for(int i=0; i<list.size();i++){
  					HomeImg hs = list.get(i);
  					if(i==0){
  		%>				
  					<div class="active item"><img class="img_Carousel" alt="" src="../<%= hs.getImgLoc()%>">
		   		  	 <a href="<%="./tohome/user.do?userId="+hs.getUserId()+"&imgId="+hs.getId() %>"  class="carousel-caption"><%= hs.getDescription()%></a>
		   		   </div>
  		<%
  					}else{
  		%>
	   		   			<div class="item"><img class="img_Carousel" alt="" src="../<%= hs.getImgLoc()%>">
			   		  	 <a href="<%="./tohome/user.do?userId="+hs.getUserId()+"&imgId="+hs.getId() %>" class="carousel-caption"><%= hs.getDescription()%></a>
			   		   </div>
   		   <%}} %>
  		</div>
  <!-- Carousel nav -->
  <a class="carousel-control left" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
  <a class="carousel-control right" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
</div>

<div style="margin-top: 1px; background-color: #cccccc">
<!-- 中间菜单 -->
	<div style=" height: 50px;margin:0px;background-color:white; box-shadow: 0px 8px 8px #888888;">
		<div style="height: 30px; width:430px; margin: 0 auto; line-height: 50px;">
			<p id = "elect">
				<i class="menu-elect" id="hot_notes">热门游记</i>
				<i><img src="base/img/decollator.png" class="decollator"></i>
				<i class="menu-elect" id="write_notes">写游记</i>
				<i><img src="base/img/decollator.png" class="decollator"></i>
				<i class="menu-elect" id="show_notes">我要上首页</i>
			</p>
		</div>
	</div>
	<!-- 中间内容区域 -->
		<div id="main_content" style="width:85%; margin: 30px auto;background-color: white;box-shadow: 10px 5px 10px #888888;" >
		 	<jsp:include   page="homePage/module/hot_notes_page.jsp" flush="true"/> 
		 	<jsp:include   page="homePage/module/write_note_page.jsp" flush="true"/> 
		 	<jsp:include   page="homePage/module/need_home_page.jsp" flush="true"/> 
		</div>
	<!-- 底部 -->
	<jsp:include   page="homePage/module/foot.jsp" flush="true"/> 
	</div>
	
	<!-- 加载js文件 -->
	<script type="text/javascript" src="base/jquery/jquery-2.1.1.min.js"></script>
	<!-- bootstrap js -->
	<script type="text/javascript" src="base/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
	<!-- JQUERY 异步图片上传js 插件 -->
	<script type="text/javascript" src="base/jquery/ajaxfileupload.js"></script>
	<!-- 操作cookie的插件 -->
	<script type="text/javascript" src="base/js/cookie_util.js"></script>
	<!-- 本网页的js -->
	<script type="text/javascript" src="homePage/js/util.js"></script>
	<script type="text/javascript" src="homePage/js/index.js"></script>
	<script type="text/javascript" src="homePage/js/head.js"></script>
	<script type="text/javascript" src="homePage/js/tohome.js"></script>
	<script type="text/javascript" src="homePage/js/issuenote.js"></script>
	
	<!-- #ue富文本配置文件# -->
    <script type="text/javascript" charset="utf-8" src="http://localhost:8080/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="http://localhost:8080/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript" src="http://localhost:8080/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
  
	<!-- //实例化编辑器 -->
	<script type="text/javascript">
	 var ue = UE.getEditor('editor');
		   /*  var um = UM.getEditor('myEditor',{
		    	autoHeightEnabled: false,
		    	initialFrameWidth : 1000,
		        initialFrameHeight: 500
		    }); */
	    </script>

	<jsp:include   page="homePage/alert/alert-register.jsp" flush="true"/> 
	<jsp:include   page="homePage/alert/alert-login.jsp" flush="true"/> 
	<jsp:include   page="homePage/alert/alert-tohome.jsp" flush="true"/> 
</body>
</html>