var model = {};
model.attention = false;
$(function(){
	initComment();
	initTransponds();
	ifCan_comment_issue();
	init_btn_attention();
	init_praise();
	$('#comments-ul').on('click','.del-comment',del_comment);
});
function initComment(){
	$('#note-person-ico').attr("src",getRootPath()+"/"+noteMap.user.addr_head_ico);
	if(noteMap.comments.length<=0){
		return;
	}
	var currentUserId = getCookie("userId");
	var nickname = getCookie("nickname");
	for(i=0;i<noteMap.comments.length;i++){
		var obj = noteMap.comments[i];
		var date = new Date(Date.parse(obj.comment.creatime));
		var ico = getRootPath()+'/'+obj.user.addr_head_ico;
		var li =' <li>'+
					'<div class="note-comment-div">'+
					'	<img alt="" src="'+ico+'" class="note-comment-img">'+
					'	<p class="comment-creatime">'+formatDate(date)+'</p>'+
					'	<p class="comment-nickname">'+obj.user.nickname+'</p>'+
					'	<p class="comment-msg">'+obj.comment.comment+'</p>';
		if(nickname && currentUserId){
			if(currentUserId == noteMap.user.id || currentUserId == obj.user.id){
				li+='	<button class="del-comment btn btn-primary" >删除</button>';
			}
		}
		li+='</div></li>;'			
		var $li = $(li);
		$li.data("commentId",obj.id);
		$('#comments-ul').prepend($li);
	}
}
function initTransponds(){
	if(noteMap.transponds.length<=0){
		return;
	}
	var currentUserId = getCookie("userId");
	var nickname = getCookie("nickname");
	for(i=0;i<noteMap.transponds.length;i++){
		var obj = noteMap.transponds[i];
		var date = new Date(Date.parse(obj.transpond.creatime));
		var ico = getRootPath()+'/'+obj.user.addr_head_ico;
		var li =' <li>'+
					'<div class="note-comment-div">'+
					'	<img alt="" src="'+ico+'" class="note-comment-img">'+
					'	<p class="comment-creatime">'+formatDate(date)+'</p>'+
					'	<p class="comment-nickname">'+obj.user.nickname+'</p>'+
					'	<p class="comment-msg">'+obj.transpond.comment+'</p>';
		if(nickname && currentUserId){
			if(currentUserId == noteMap.user.id || currentUserId == obj.user.id){
				li+='	<button class="del-comment btn btn-primary" >删除</button>';
			}
		}
		li+='</div></li>;'			
		var $li = $(li);
		$li.data("transpondId",obj.id);
		$('#transponds-ul').prepend($li);
	}
}
function init_praise(){
	var userId = getCookie('userId');
	if(!userId || userId == ''){
		return;
	}
	var url = getRootPath_web()+"/note/logging/ifPraise.do";
	var data ={'noteId': noteMap.id};
	$.get(url,data,function(_result){
		if(_result.state == 0){
			if(_result.data){
				model.ifPraise = true;
				$('#note-praise-flag').removeClass('glyphicon-heart-empty').addClass('glyphicon-heart');
				return;
			}
			return;
		}
		alert(_result.message);
	});
}
function ifCan_comment_issue(){
	var nickname = getCookie("nickname");
	if(!nickname){
		$('#comment_issue').attr("disabled","disabled");
		$('#comment_issue_msg').show();
		return;
	}
	$('#comment_issue').removeAttr("disabled");
	$('#comment_issue_msg').hide();
}
//评论发布
function comment_issue(){
	var userId = getCookie("userId");
	if(!userId){
		showLogin();
		return;
	}
	var comment = $('#tr-comment').val();
	if(comment.length<3){
		return;
	}
	var noteId  = noteMap.id;
	if(!noteId){
		alert("上传失败，请重新刷新页面！");
	}
	var data = {"noteId":noteId,"comment":comment};
	var url = getRootPath_web()+"/note/logging/upcomment.do"
	$.post(url,data,function(_result){
		if(_result.state == 0){
			upComment();
			return;
		}
		alert(_result.message);
	});
}

function upComment(){
	var comment = $('#tr-comment').val();
	var src = getCookie("addr_head_ico");
	var $li = $('<li>'+
					'<div class="note-comment-div">'+
						'<img alt="" src="'+src+'" class="note-comment-img">'+
						'<p class="comment-creatime">'+formatDate(new Date())+'</p>'+
						'<p class="comment-nickname">'+getCookie("nickname")+'</p>'+
						'<p class="comment-msg">'+comment+'</p>'+
						'<button class="del-comment btn btn-primary" >删除</button>'+
					'</div>'+
				'</li>');
	$('#comments-ul').prepend($li);
	$('#tr-comment').val(" ");
}

function del_comment(){
	var $li = $(this).parent().parent();
	var id = $li.data("commentId");
	var index = $li.data("index");
	var url =getRootPath_web()+"/note/logging/delComment.do"
	var data= {"id":id};
	$.get(url,data,function(_result){
		if(_result.state == 0){
			$li.remove();
			return;
		}
		alert(_result.massage);
	});
}


function note_comments(){
	$("#div-note-transponds").hide();
	$("#div-note-comment").show();
}
function note_transponds(){
	$("#div-note-comment").hide();
	$("#div-note-transponds").show();
}

function note_submittransponds(){
	if(!getCookie("nickname")){
		showLogin();
		return;
	}
	var transmit = $('#transmit-num-text').val();
	if(transmit.length<3 || transmit.length>120){
		alert("转发字数大于3小于120");
		return;
	}
	var noteId = noteMap.note.id;
	if(!noteId){
		alert("上传失败，请重新刷新！");
		return;
	}
	var url = getRootPath_web()+"/note/logging/saveTranspond.do";
	var data = {"noteId":noteId,"transmit":transmit};
	$.post(url,data,function(_result){
		if(_result.state == 0){
			upTransponds();
			return;
		}
		alert(_result.message);
	});
}

function upTransponds(){
	var comment = $('#transmit-num-text').val();
	var src = getCookie("addr_head_ico");
	var $li = $('<li>'+
					'<div class="note-comment-div">'+
						'<img alt="" src="'+src+'" class="note-comment-img">'+
						'<p class="comment-creatime">'+formatDate(new Date())+'</p>'+
						'<p class="comment-nickname">'+getCookie("nickname")+'</p>'+
						'<p class="comment-msg">'+comment+'</p>'+
						'<button class="del-comment btn btn-primary" >删除</button>'+
					'</div>'+
				'</li>');
	$('#transponds-ul').prepend($li);
	$('#transmit-num-text').val(" ");
}

function init_btn_attention(){
	$('#note-user-nickname').show().text(noteMap.user.nickname);
	change_btn_Attention(false);
	$('#btn_attention').show();
	var logId = getCookie("userId");
	if(!logId){
		return;
	}
	var noteUserId = noteMap.user.id;
	if(!noteUserId){
		return;
	}
	if(logId == noteUserId){
		$('#btn_attention').hide();
		return;
	}
	var url =  getRootPath_web()+"/account/getAttentionState.do";
	var data = {"noteUserId":noteUserId,"userId":logId};
	$.get(url,data,function(_result){
		if(_result.state == 0){
			if(_result.data){
				change_btn_Attention(true);
				model.attention = true;
				return;
			}
			return;
		}
		alert(_result.message);
	});
}

function operateAttention(){
	var logId = getCookie("userId");
	if(!logId){
		showLogin();
		return;
	}
	var noteUserId = noteMap.user.id;
	if(!noteUserId){
		return;
	}
	var operateState = "";
	if(model.attention){
		operateState = "cancelAttention";
	}else{
		operateState = "addAttention";
	}
	var url =  getRootPath_web()+"/account/logging/operateAttention.do";
	var data = {"attention":noteUserId,"operateState":operateState};
	$.get(url,data,function(_result){
		if(_result.state == 0){
			if('addAttention'.indexOf(_result.data) > -1 ){
				change_btn_Attention(true);
				model.attention = true;
				return;
			}
			if('cancelAttention'.indexOf(_result.data) > -1 ){
				change_btn_Attention(false);
				model.attention = false;
				return;
			}
			return;
		}
		alert(_result.message);
	});
}

function change_btn_Attention(state){
	if(!state){
		$('#btn_attention > i').removeClass('glyphicon-ok').addClass('glyphicon-plus');
		$('#btn_attention > i').html('关&nbsp;注');
		$('#btn_attention').removeClass('btn_isAttention').addClass('btn_noAttention');
		model.attention = false;
		return;
	}
	$('#btn_attention > i').removeClass('glyphicon-plus').addClass('glyphicon-ok');
	$('#btn_attention > i').html('已关注');
	$('#btn_attention').removeClass('btn_noAttention').addClass('btn_isAttention');
	model.attention = true;
}

function note_praise(){
	var logId = getCookie("userId");
	if(!logId){
		showLogin();
		return;
	}
	var data = {'noteId':noteMap.id};
	var url =  getRootPath_web()+"/note/logging/praiseActtion.do";
	$.get(url,data,function(_result){
		if(_result.state == 0){
			var operatePraise = _result.data;
			if('addPraise'.indexOf(operatePraise)>-1){
				$('#note-praise-flag').removeClass('glyphicon-heart-empty').addClass('glyphicon-heart');
				$('#note-praise-flag').text(noteMap.praises.length+1);
			}
			if('delPraise'.indexOf(operatePraise)>-1){
				$('#note-praise-flag').removeClass('glyphicon-heart').addClass('glyphicon-heart-empty');
				$('#note-praise-flag').text(noteMap.praises.length<=0? 0:noteMap.praises.length-1);
			}
			return;
		}
		alert(_result.message);
	});
}



















