$(function(){
	// jquery 兼容的滚轮事件
	$(document).on("mousewheel DOMMouseScroll", scrollAction);
});

function scrollAction(e) {
    var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
                (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1));              // firefox
    if (delta > 0) {
    	$('#head').animate({"top":"0px"},10);
        // 向上滚
    } else if (delta < 0) {
        // 向下滚
    	$('#head').animate({"top":"-50px"},80);
    }
}


function reLogin(){
	
}

//上传图片
function uploadImg(btnId,operateId){
	 var imgPath = $("#"+btnId).val();
	    if (imgPath == "") {
	        alert("请选择上传图片！");
	        return;
	    }
	    //判断上传文件的后缀名
	    var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
	    if (strExtension != 'jpg' && strExtension != 'JPG' && strExtension != 'gif' && strExtension != 'PNG' && strExtension != 'png' ) {
	        alert("请选择图片文件");
	        $("#"+btnId).val("");
	        return;
	    }
	 $.ajaxFileUpload
	    (
	        {
	            url: 'util/tempImg.do', //用于文件上传的服务器端请求地址
	            secureuri: false, //是否需要安全协议，一般设置为false
	            fileElementId: btnId, //文件上传域的ID
	            dataType: 'json', //返回值类型 一般设置为json
	            success: function(data) //服务器成功响应处理函数
	            {
	                $("#"+operateId).attr("src", data.responseText);
	            },
	            error: function (data)//服务器响应失败处理函数
	            {
	            	 $("#"+operateId).attr("src", data.responseText);
	            }
	        }
	    )
}
//获取根目录信息
function getRootPath_web() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}
function getRootPath() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    return localhostPaht ;
}

function   formatDate(now)   {     
    var   year=now.getFullYear();     
    var   month=now.getMonth()+1;     
    var   date=now.getDate();     
    var   hour=now.getHours();     
    var   minute=now.getMinutes();
    var   second = now.getSeconds();
    return   year+"-"+month+"-"+date+" "+hour+":"+minute+':'+second;     
   } 

function subStr(str,num){
	if(!str){
		return "";
	}
	if(str.length>num){
		return str.substr(0,num)+'...';
	}
	return str;
}	
