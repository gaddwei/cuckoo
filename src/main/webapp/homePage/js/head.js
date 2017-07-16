$(function(){
	$('#menu_edit').on('click',select_edit);
	$('#menu_out').on('click',select_out);
	var name = getCookie('nickname');
	if(name){
		$('#loginIcon').attr('src',getCookie('addr_head_ico'));
		$('#loginName').text(name);
		$('#Login').show();
		$('#noLogin').hide();
		init_a();
		return;
	}
	$('#noLogin').show();
	$('#Login').hide();
	
	 $('#register').click(showRegister);
	 $('#login').click(showLogin);
});

function showRegister(){
	  $('#identifier').modal('toggle')
}

function showLogin(){
	  $('#dialog_login').modal('toggle')
}



// login 
var SUCCESS = 0;
var rootPath = getRootPath_web();
function init_a(){
	var userId = getCookie("userId");
	$('#a-menu_circle').removeAttr('href').attr('href',rootPath+'/account/logging/circle.do?');
}
//验证邮箱输入
function sure_email_login(){
	var email = $('#login_email').val();
	if(!email){
		$('#warning_login_email').text("* 邮箱必填").css('color','red');
		return false;
	}
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
	if(!reg.test(email)){
		$('#warning_login_email').text("* 不是正确的邮箱格式").css('color','red');
		return false;
	 }
	$('#warning_login_email').html("&nbsp;");
	return true;
}

//密码验证
function sure_password_login(){
	var password = $('#login_password').val();
	if(!password){
		$('#warning_login_password').text("* 密码必填").css('color','red');
		return false;
	}
	var reg = /^[a-zA-Z0-9]{6,12}$/; 
	if(!reg.test(password)){
		$('#warning_login_password').text("* 密码必须包含6到12个数字字母").css('color','red');
		return false;
	 }
	$('#warning_login_password').html("&nbsp;");
	return true;
}

function submitLogin(){
	var d= sure_email_login()+sure_password_login();
	 if(!sure_email_login()+sure_password_login() == 2) return;
	 var email = $('#login_email').val();
	 var password = $('#login_password').val();
	 var dat = {'email':email,'password':password};
	 $.post(rootPath+'/account/login.do',dat,function(result){
		 var state = result.state;
		 var data= result.data;
		 if(state == SUCCESS){
			  setCookie('nickname',data.nickname);
			  setCookie('addr_head_ico',data.addr_head_ico);
			  location.reload();
			  return;
		 }
		 if(state == 2){
			 $('#warning_login_password').text(" 账户或密码错误").css('color','red');
			 return;
		 }
	 });
}

/*     *********************register****************             */



//验证邮箱输入
function sure_email(){
	var email = $('#re_email').val();
	if(!email){
		$('#msg_email').text("* 邮箱必填").css('color','red');
		return false;
	}
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
	if(!reg.test(email)){
		$('#msg_email').text("* 不是正确的邮箱格式").css('color','red');
		return false;
	 }
	$('#msg_email').html("&nbsp;");
	return true;
}

//昵称输入
function sure_nick(){
	var nick = $('#re_nick').val();
	if(!nick){
		$('#msg_nick').text("* 昵称必填").css('color','red');
		return false;
	}
	$('#msg_nick').html("&nbsp;");
	return true;
}

//密码验证
function sure_password(){
	var pwd = $('#re_password').val();
	var re_pwd = $('#re_re_password').val();
	if(!pwd){
		$('#msg_password').text("* 密码必填").css('color','red');
		return false;
	}
	if(pwd != re_pwd){
		$('#msg_password').text("* 两次密码输入不一致").css('color','red');
		return false;
	}
	$('#msg_password').html("&nbsp;");
	return true;
}

//年龄验证
function sure_age(){
	var age = $('#re_age').val();
	if(!age){
		$('#msg_age').html("&nbsp;");
		return;
	}
	var reg = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
	if(!reg.test(age)){
		$('#msg_age').text("* 年龄不合法，为1-120").css('color','red');
		return false;
	}
	$('#msg_age').html("&nbsp;");
	return true;
}

//头像改变
function chang_ico(){
	var val = $('#re_userico').val();
	$('#re_ico').attr('src',val);
}


//头像上传
function ajaxFileUpload() {
	//判断是否有选择上传文件
    var imgPath = $("#re_userico").val();
    if (imgPath == "") {
        alert("请选择上传图片！");
        return;
    }
    //判断上传文件的后缀名
    var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
    if (strExtension != 'jpg' && strExtension != 'JPG' && strExtension != 'gif' && strExtension != 'PNG' && strExtension != 'png' ) {
        alert("请选择图片文件");
        $("#re_userico").val("");
        return;
    }
    $.ajaxFileUpload
    (
        {
            url: rootPath+'/account/registerico.do', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 're_userico', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            success: function(data) //服务器成功响应处理函数
            {
                $("#re_ico").attr("src", data.responseText);
                console.log(data);
            },
            error: function (data)//服务器响应失败处理函数
            {
            	$("#re_ico").attr("src", data.responseText);
            }
        }
    )
    return false;
}

function submitData(){
	  if(sure_email()+sure_nick()+sure_password()!=3) return;
	  var re_icopath = $('#re_ico').attr('src');
	  var re_nick = $('#re_nick').val();
	  var re_sex = 1;
	  var re_age = $('#re_age').val();
	  var re_email = $('#re_email').val();
	  var re_password = $('#re_password').val();
	  var re_re_password = $('#re_re_password').val();
	  console.log(data);
	  var data = {'re_icopath':re_icopath,'re_nick':re_nick,
			  're_sex':re_sex,'re_age':re_age,'re_email':re_email,'re_password':re_password,'re_re_password':re_re_password};
	  $.post(rootPath+'/account/register.do',data,function(result){
		  var data = result.data;
		  if(result.state == SUCCESS){
			  setCookie('nickname',data.nickname);
			  setCookie('addr_head_ico',data.addr_head_ico);
			  location.reload();
			  return;
		  }
		  if(result.state == 2){
			  alert(result.message);
			  return;
		  }
		  alert('哇！ 请重试...');
	  },'json');

}

function select_edit(){
	console.log(222222);
}
function select_out(){
	dleCookie("nickname");
	dleCookie("userId");
	dleCookie("token");
	dleCookie("addr_head_ico");
	location.reload();
}


