var model = {};
model.pageSize = 10;
model.lastEnd = 0;

$(function(){
	$('#circle-content').on('click','i.i-circle-transpond',circle_transpond);
	$('#transmit-area-btn').on('click',transmit_transpond_note);
	updateCircle(circleNotes);
	initDate();
});
function initDate(){
	$('#note-person-ico').attr('src',getCookie('addr_head_ico'));
	$('.myself-info').show();
	$('#info-attentions').text("关注:"+selfInfo.attentions);
	$('#info-fans').text("粉丝:"+selfInfo.fans);
}
function btn_more_circle(){
	var url=getRootPath_web()+"/account/logging/moreCircle.do";
	var data = {"start":model.lastEnd+1,"end":model.lastEnd+1+model.pageSize};
	$.get(url,data,function(_result){
		if(_result.state == 0){
			updateCircle(_result.data);
			return;
		}
	});
}
function circle_transpond(){
	 var index = $(this).parent().data('index');
	 model.index = index;
	 var url = getRootPath_web()+"/note/getCircleTranspond.do";
	 var noteId = model.circleNotes[index].noteid;
	 var data = {"noteId":noteId};
	 $('#transmit').modal('toggle');
	 $('#transmit-more').attr('href',getRootPath_web()+'/note/no.do?noteId='+noteId);
	 $('#transmit-modal-ul').empty();
	 $.get(url,data,function(_result){
		 if(_result.state == 0){
			 var data = _result.data;
			 $('#transmint-total').text('目前已转发'+data.total+'条');
			 if(data.tranponds.length > 0){
				 for(i=0;i<data.tranponds.length;i++){
					var tranpond = data.tranponds[i];
					var comment =subStr( tranpond.comment,30);
					var date = formatDate(new Date(tranpond.creatime));
					 var temp = '<li class = "transmit-modal-li">'+
									' <img alt="" src="'+getRootPath()+'/'+tranpond.ioc+'">'+
				         			'<div>'+
				         			'<p><strong>'+tranpond.nickname+':</strong>'+comment+'</p>'+
				        			'<p class = "transmit-modal-li-date">'+date+'</p></div>'+
				        			'<fieldset><legend class="transmit-num-head"></legend></fieldset>'+
				        		'</li>';
					 $('#transmit-modal-ul').append($(temp));
				 }
			 }
			 return;
		 }
	 });
}
function transmit_transpond_note(){
	var comment = $('#transmit-area').val();
	if(!comment || comment.length<10 || comment.length>120){
		return;
	}
	var url = getRootPath_web()+"/note/logging/saveTranspond.do";
	var noteId = model.circleNotes[model.index].noteid;
	var data = {"noteId":noteId,"transmit":comment};
	$.post(url,data,function(_result){
		if(_result.state == 0){
			location.reload();
			return;
		}
		alert(_result.message);
	});
}
function updateCircle(circleNotes){
	if(circleNotes.length <= 0){
		return;
	}
	if(!model.circleNotes){
		model.circleNotes = circleNotes;
		model.circleIndex = 0;
	}else{
		model.circleNotes = model.circleNotes.concat(circleNotes);
	}
	model.lastEnd += circleNotes.length;
	var rootPath = getRootPath();
	for(i=0;i < circleNotes.length;i++ ){
		var circleNote = circleNotes[i];
		var date = formatDate(new Date(circleNote.creatime));
		var toUrl = getRootPath_web()+'/note/no.do?noteId='+circleNote.noteid;
		var temp = '<li class="circle-content-card">'+
						'<div class="seft-info">'+
							'<img alt="" src="'+rootPath+'/'+circleNote.ico+'" class="circle-card-ico img-circle">'+
							'<div class="circle-content-card-info"><span>'+circleNote.nickname+'</span><br>'+
							'<span>'+date+'</span>'+
						'</div></div>'+
						'<div class="circle-card-details">';
		if(circleNote.comment){
			temp += '<p>'+subStr200(circleNote.comment)+'</p>'+
					'<div class="circle-details"><a  href="'+toUrl+'">'+
						'<p><strong class="transpond-nickname">转发来自 '+circleNote.notenickname+':</strong>'+subStr300(circleNote.txtContent)+'</p>';
			if(circleNote.imgs.length>0){
				temp+=' <ul class="circle-content-card-ul">';
					for(j=0;j<circleNote.imgs.length;j++){
						temp+= '<li ><img class="circle-content-card-img img-thumbnail" alt="" src="'+circleNote.imgs[j]+'"> </li>';
						if(j>=3){
							break;
						}
					}
					temp+='</ul><p style="clear: left;"></p>' ;	
				}		
			 temp+='</div></a>';
		}else{
			temp += '<p>'+subStr300(circleNote.txtContent)+'</p>';
			if(circleNote.imgs.length>0){
				temp+=' <ul class="circle-content-card-ul">';
					for(j=0;j<circleNote.imgs.length;j++){
						temp+= '<li ><img class="circle-content-card-img img-thumbnail" alt="" src="'+circleNote.imgs[j]+'"> </li>';
						if(j>=3){
							break;
						}
					}
					temp+='</ul><p style="clear: left;"></p>' ;	
				}
		}
		temp += '</div>'+
				'	<i class="glyphicon glyphicon-retweet i-circle-transpond" > 转发</i>'+
			'</li>';
		var $li = $(temp);
		$li.data('index',model.circleIndex++);
		$('#circle-content').append($li);
	}
	

	
	
function subStr(str,num){
	return str.substr(0,num)+'...';
}	
function subStr200(str){
	return str.substr(0,200)+'...';
}
function subStr300(str){
	return str.substr(0,200)+'...';
}
}