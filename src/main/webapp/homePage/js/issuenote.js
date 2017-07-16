$(function(){
	initPrCityData("select_province",1,0,"选择省份");
	initGetNoteCoverDate();
});
var noteModel = {};
noteModel.NoteCoverStart=0;
noteModel.NoteCoverPageSize=9;
model.selectSiteId = -1;
//省份
function change_province(){
	var id = $('#select_province option:selected').val();
	model.selectSiteId = id;
	initPrCityData("select_city",2,id,"选择城市");
}
//城市
function change_city(){
	var id = $('#select_city option:selected').val();
	model.selectSiteId = id;
	initPrCityData("select_area",3,id,"选择区");
}
function change_area(){
	var id = $('#select_area option:selected').val();
	model.selectSiteId = id;
	initPrCityData("select_min",4,id,"选择乡镇");
}

function select_min(){
	var id = $('#select_min option:selected').val();
	model.selectSiteId = id;
}
//AJAX后台加载数据
function initPrCityData(operateId , level, upid , desc){
	var data = {"level":level,"upid":upid};
	var url = "core/initProCityData.do";
	$.get(url,data,function(_result){
		if(_result.state == 0){
			var dat = _result.data;
			$("#"+operateId).empty();
			$("#"+operateId).append('<option value="-1">'+desc+'</option>');
			for(i=0;i<dat.length;i++){
				var district = dat[i];
				var $temp = $('<option value="'+district.id+'">'+district.name+'</option>');
				$("#"+operateId).append($temp);
			}
			
		}
	});
}


function noteImgUpload(){
	//util.js中的上传图片  1.上传图片的file id , 2.返回的url绑定在img上
	uploadImg('note_ioc','note_ioc_show');
}

function note_publish(){
	var title = $("#note_title").val();
	if(!title || title.lenght <= 1){
		alert("请输入游记标题");
		return;
	}
	var siteId = model.selectSiteId;
	var imgPath = $("#note_ioc_show").attr("src");
	if(!ue.hasContents()){
		alert("没有游记内容！");
		ue.focus();
		return;
	}
	var content =  ue.getContent();
	$("#note-publish").attr("disabled","disabled").val("leading...");
	var data = {"title":title,"siteId":siteId,"imgPath":imgPath,"content":content};
	var url = "note/logging/publisNote.do";
	$.post(url,data,function(_result){
		if(_result.state == 0){
			alert("游记发布成功！");
			$("#note_title").val("");
			ue.setContent("");
			$("#note-publish").removeAttr("disabled").val("发   布");
		}
		alert(_result.message);
		$("#note-publish").removeAttr("disabled").val("发   布");
	});
}

function initGetNoteCoverDate(){
	var url = "note/NoteCoverDate.do";
	var start = noteModel.NoteCoverStart;
	var end = start + noteModel.NoteCoverPageSize;
	var data = {"start": start,"end": end};
	$.get(url,data,function(_result){
		if(_result.state == 0){
			var data = _result.data;
			noteModel.updataNoteCovers(data);
			return;
		}
		alert(_result.message);
	});
}

noteModel.updataNoteCovers = function(data){
	this.NoteCoverStart += data.length;
	for(i=0;i<data.length;i++){
		var _result = data[i];
		if(_result.note.browse == null){
			_result.note.browse=0;
		}
		var	$card = $('<li class="hote-note-card">'+
						'<a href="note/no.do?noteId='+_result.id+'">'+
							'<div style=" width: 300px;height: 300px;" > '+
								'<img alt="" src="../'+_result.note.imgLoc+'" style=" width:100%;height: 60%; ">'+
								'<p class="card-user-name">'+_result.user.nickname+'</p>'+
								'<img alt="" src="../'+_result.user.addr_head_ico+'" class="img-circle" >'+
								'<div class="card-dsc">'+
									'<ul class="card-dsc-show">'+
										'<li><i class=" glyphicon glyphicon-retweet">'+_result.transpond+'</i></li>'+
										'<li><i class=" glyphicon glyphicon-edit">'+_result.comment+'</i></li>'+
										'<li><i class=" glyphicon glyphicon-eye-open">'+_result.note.browse+'</i></li>'+
									'</ul>'+
									'<p style="clear: left; height: 20px;"></p>'+
									'<p class="card-title">'+_result.note.title+'</p>'+
									'<p class="card-tags"><i class="glyphicon glyphicon-tags">&nbsp;'+_result.site+'</i></p>'+
								'</div>'+
							'</div>'+
						'</a>'+
					'</li>');
		$('#hote-note-cover').append($card);
	}
}

function more_note(){
	initGetNoteCoverDate();
}

