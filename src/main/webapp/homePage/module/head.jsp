<%@page contentType="text/html; utf-8" pageEncoding="UTF-8"%>
<div id = "head">
	<ul id="menu" >
		<li class="menu_li "><a href="<%=request.getContextPath() %>/index.jsp"><img alt="布谷鸟" src="<%=request.getContextPath()%>/base/img/logo.png" id="logo"></a></li>
		<li class="menu_li"><a href="#" class="menu_a">目的地</a></li>
		<li class="menu_li"><a href="#" class="menu_a">行程推荐</a></li>
		<li class="menu_li"><a href="#" class="menu_a">到此一游</a></li>
	</ul>
	<!-- 未登录状态 -->
	<div  style="float:right; margin-right: 30px;margin-top: 3px; display: none;" id="noLogin">
		<p id="right-head">
		<a id="seach" class="glyphicon glyphicon-search"></a>
		<a id="login" class="menu_a">登录</a>
		<a id="register" class="menu_a">注册</a>
		</p>
	</div>
	<!-- 登录状态 -->
	<div  style="float:right; margin-right: 60px;display: none;" id="Login">
		<ul id="menu" >
			<li class="menu_right seach"><a id="seach" class="glyphicon glyphicon-search"></a>&nbsp;</li>
			<li class="menu_right" style="margin-top: -3px;"><img id="loginIcon" class="img-circle" ></li>
			<li class="menu_right">
				<!-- 下拉选择菜单 -->
            	<div class="dropdown" >
                	<a href="#" class="dropdown-toggle" data-toggle="dropdown" style="font-size: 10px;">
                  	 <b class=" dropdown-toggle glyphicon glyphicon-chevron-down" id="select_Menu"></b>
                	</a>
                	<ul class="dropdown-menu " style="margin-top: 10px; background-color: white;">
                	<li class="select_menu_m  edit " id="menu_circle "><a id="a-menu_circle" style="font-size: 15px" ><span class="glyphicon glyphicon-paperclip">&nbsp;朋友圈</span></a></li>
                	<li class="select_menu_m  edit " id="menu_edit"><a href="#" style="font-size: 15px" ><span class="glyphicon glyphicon-cog">&nbsp;修改资料</span></a></li>
                	<li role="presentation" class="divider"></li>
                  	 <li class="select_menu_m  out " id="menu_out" ><a href="#" style="font-size: 15px"><span class="glyphicon glyphicon-off">&nbsp;退出登录</span></a></li>
                	</ul>
            		</div>
			</li>
			<li class="menu_right"><a ><span id="loginName"></span></a></li>
		</ul>
	</div>
</div>