var toHomePageSize = 15;
var toHomeCarLiNum=0;
var model = {};
$(function(){
	//上首页用户点赞操作
	$('#ul-list-cards').on('click','i.praise_state',actionPraise);
	$('#fat-btn').on('click',actingLoadMore);
	
});

function ajaxHomeImgUpload(){
	 var imgPath = $("#img_tohome").val();
	    if (imgPath == "") {
	        alert("请选择上传图片！");
	        return;
	    }
	    //判断上传文件的后缀名
	    var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
	    if (strExtension != 'jpg' && strExtension != 'JPG' && strExtension != 'gif' && strExtension != 'PNG' && strExtension != 'png' ) {
	        alert("请选择图片文件");
	        $("#img_tohome").val("");
	        return;
	    }
	 $.ajaxFileUpload
	    (
	        {
	            url: 'account/registerico.do', //用于文件上传的服务器端请求地址
	            secureuri: false, //是否需要安全协议，一般设置为false
	            fileElementId: 'img_tohome', //文件上传域的ID
	            dataType: 'json', //返回值类型 一般设置为json
	            success: function(data) //服务器成功响应处理函数
	            {
	                $("#img-home").attr("src", data.responseText);
	                console.log(data);
	            },
	            error: function (data)//服务器响应失败处理函数
	            {
	            	$("#img-home").attr("src", data.responseText);
	            }
	        }
	    )
}

function submitToHome(){
	var imgPath = $('#img-home').attr('src');
	if(!imgPath || imgPath == 'base/img/4.jpg'){
		return;
	}
	var cr = $('#tx_cr_home').val();
	if(!cr.length>0){
		$('#warning_tohome_cr').text("* 必填且字数小于100").css('color','red');
		return false;
	}
	$('#warning_tohome_cr').html("&nbsp;");
	var userId = getCookie('userId');
	var token = getCookie('token');
	var url = 'home/publisHome.do';
	
	var data = {'imgPath':imgPath,'cr':cr,'userId':userId,'token':token};
	$.post(url,data,function(data){
		if(data){
			alert('发布成功！');
			$('#dialog_tohome').modal('toggle');
			return;
		}
		alert('发布失败，请重试！！');
		return;
	});
}

function actingLoadMore(){
	var pages = $('#fat-btn').data("pages");
	if(!pages){
		pages = 1;
	}
	var url = 'home/loadmore.do';
	var userId = getCookie('userId');
	var data = {"pages":pages,"pageSize":toHomePageSize,"userId":userId};
	var _result;
	$.get(url,data,function(_result){
		if(_result.length > 0){
			model.updataTohomeCar(_result,pages);
			return;
		}
		$('#fat-btn').html('&nbsp;没有更多了...&nbsp;');
	});
}

//更新上首页卡片集
model.updataTohomeCar = function(_result,pages){
	$('.li_head_line:last').show();
	if(! this.toHomeCarDatas ){
		this.toHomeCarDatas=_result;
		this.toHomeCarLiNum=0;
	}else{
		//concat 方法用于合并两个数组
		this.toHomeCarDatas=
			this.toHomeCarDatas.concat(_result); 
	}
	for(var i=0 ;i < _result.length; i++){
		var user = _result[i].user;
		var homeImg = _result[i].homeImg;
		var date = formatDate(new Date(homeImg.creatime));
		var $template = $('<li style="height:350px;">' +
							'<div class = "go-page-card">' +
									'<div style="display: inline-block; margin-left: -25px;">'+
										'<ul style="list-style: none; ">'+
											'<li><img alt="" src="../'+user.addr_head_ico+'" class="img-circle go-page-card-ico"></li>'+
											'<li style="font-size:14px;text-align:center;width: 70px;"><span >'+user.nickname+'<span></li>'+
											'<li><span style="font-size:9px;text-align:center;width: 70px;">'+date+'<span></li>'+
											'<li><span class ="li_head_line" style="border:solid blue 1px;  margin:0 30px; font-size: 170px;"></span></li>'+
										'</ul>'+
									'</div>'+
							'<div class="go-page-card-divimg" >'+
								'<img alt="" src="../'+homeImg.imgLoc+'" class="img-rounded">'+
								'<span>'+homeImg.description+'</span>'+
								'<i class="praise_state glyphicon glyphicon-heart-empty " ">&nbsp;'+homeImg.praise+'</i>'+
							'</div>'+
							'</div>'+
						'</li>');
		$template.data('index',this.toHomeCarLiNum);
		this.toHomeCarLiNum++;
		if(_result[i].praise_state){
				$template.find('i').removeClass("glyphicon-heart-empty").addClass("glyphicon-heart");
		}
		$('#ul-list-cards').append($template);
	}
	$('.li_head_line:last').hide();
	$('#fat-btn').data("pages",++pages);
	return;
}
    

//点击红心事件
function actionPraise(){
	var nickname = getCookie('nickname');
	if(!nickname){
		showLogin();
		return;
	}
	console.log($(this));
	var index = $(this).parent().parent().parent().data("index");
	var cardData = model.toHomeCarDatas[index];
	if(cardData.praise_state){
		$($('i.praise_state')[index]).removeClass("glyphicon-heart").addClass("glyphicon-heart-empty");
		model.toHomeCarDatas[index].praise_state = false;
		var i;
		var num = model.toHomeCarDatas[index].homeImg.praise  > 0 ?  --(model.toHomeCarDatas[index].homeImg.praise) : 0 ;
		$($('i.praise_state')[index]).html("&nbsp;"+num);
		cancelPraiseTohome(cardData.homeImg.id,index);
		return;
	}else{
		$($('i.praise_state')[index]).removeClass("glyphicon-heart-empty").addClass("glyphicon-heart");
		model.toHomeCarDatas[index].praise_state = true;
		var num = ++(model.toHomeCarDatas[index].homeImg.praise);
		$($('i.praise_state')[index]).html("&nbsp;"+num);
		addPraiseTohome(cardData.homeImg.id,index);
		return;
	}
}

//点赞
function addPraiseTohome(imgId,index){
	var data = {"imgId":imgId};
	var url = 'home/logging/addPraise.do';
	$.get(url,data,function(_result){
			if(_result.state == 0){
				return;
			}
			$($('i.praise_state')[index]).html("&nbsp;"+(--(model.toHomeCarDatas[index].homeImg.praise)));
			$($('i.praise_state')[index]).removeClass("glyphicon-heart").addClass("glyphicon-heart-empty");
			model.toHomeCarDatas[index].praise_state = false;
			if(_result.state == 1){
				alert(_result.message);
				select_out();
			}
	});
}

//取消赞
function cancelPraiseTohome(imgId,index){
	var data = {"imgId":imgId};
	var url = 'home/logging/cancelPraise.do';
	$.get(url,data,function(_result){
			if(_result.state == 0){
				return;
			}
			$($('i.praise_state')[index]).html("&nbsp;"+(++(model.toHomeCarDatas[index].homeImg.praise)));
			$($('i.praise_state')[index]).removeClass("glyphicon-heart-empty").addClass("glyphicon-heart");
			model.toHomeCarDatas[index].praise_state = true;
			if(_result.state == 1){
				alert(_result.message);
				select_out();
			}
	});
}












