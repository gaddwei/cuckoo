//初始化
 $(function(){
	 var $menu_i = $('#elect');
	 $menu_i.on('click','i.menu-elect',select_show);
	 $('#hot_notes').css('color','blue');
	
 });
 
 
  function select_show(){
	  if($(this).text()=="热门游记"){
		  $('#hot_notes').css('color','blue');
		  $('#write_notes').css('color','black');
		  $('#show_notes').css('color','black');
		  show_hot_notes();
		  return;
	  }
	  if($(this).text()=="写游记"){
		  $('#write_notes').css('color','blue');
		  $('#hot_notes').css('color','black');
		  $('#show_notes').css('color','black');
		  show_write_note();
		  return;
	  }
	  if($(this).text()=="我要上首页"){
		  $('#show_notes').css('color','blue');
		  $('#hot_notes').css('color','black');
		  $('#write_notes').css('color','black');
		  show_need_homePage();
		  return;
	  }
  }
  
  
 
  function btn_tohome(){
	  var nickname = getCookie('nickname');
	  if(nickname){
		  $('#dialog_tohome').modal('toggle');
		  return;
	  }
	  showLogin();
  }
  
  function show_hot_notes(){
	  $('#div_write_page').hide();
	  $('#div_need_page').hide();
	  $('#div_hot_page').show();
  }
  
  function show_write_note(){
	  $('#div_hot_page').hide();
	  $('#div_need_page').hide();
	  $('#div_write_page').show();
	 
  }
  
  function show_need_homePage(){
	  $('#div_hot_page').hide();
	  $('#div_write_page').hide();
	  $('#div_need_page').show();
	  actingLoadMore();
  }
