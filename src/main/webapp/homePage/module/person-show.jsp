<%@page contentType="text/html; utf-8" pageEncoding="UTF-8"%>
<div class="person-show">
	<img alt="" class="person-show-img" src="<%=request.getContextPath() %>/base/img/5.jpg">
	<div class="person-show-center">
		<img alt="" class="img-circle" id="note-person-ico" src="<%=request.getContextPath() %>/base/img/4.jpg">
		<p id="note-user-nickname">NNNMAR</p>
		<button id="btn_attention" class="btn" onclick="operateAttention()"><i class="glyphicon glyphicon-plus">关&nbsp;注</i></button>
		<div class="myself-info">
			<br>
			<span id="info-attentions">关注:6</span>&nbsp;
			<span id="info-fans">粉丝:7</span>
		</div>
	</div>
	<p class="person-bottom"></p>
</div>