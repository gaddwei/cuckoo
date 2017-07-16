<%@ page contentType="text/html; charset=utf-8"%>
<div id="div_write_page" style="display:none;">
<div style="height: 50px; width:100%;">

</div>
<div style="width: 80%; width: 1000px;margin:0 auto;">
	<div style="margin: 15px 0px; font-size: 20px;">
		游记标题:
		<input type="text" class="form-control input_title_note" id="note_title" placeholder="请输入标题..."/>
	</div>
	<div style="margin: 10px 0px; font-size: 20px;">
		展示照片:
		<img alt="" id="note_ioc_show" src="base/img/timg.jpg"  class="img-rounded write_img">
		<div class="file-box" style="margin-left: 90px;">
	 				<input type='button' class='btn btn1' value='上传游记封面图片...' />
	    			<input type="file" name="userico" class="file_ico" id="note_ioc" size="28" onchange="noteImgUpload()" />
 				</div>
	</div>
	<div style=" margin: 0 10px;">
		<select  id="select_province" class="xla_k" onchange="change_province()">
    		<option value="-1">选择省份</option>
   		 </select>
		<select  id="select_city" class="xla_k" onchange="change_city()">
    		<option value="-1">选择城市</option>
   		 </select>
   		 <select id="select_area" class="xla_k" onchange="change_area()">
    		<option value="选择品牌">选择区</option>
   		 </select>
   		 <select id="select_min" class="xla_k" onchange="select_min()">
    		<option value="选择品牌">选择乡镇</option>
   		 </select>
	</div>
	<!--style给定宽度可以影响编辑器的最终宽度-->
<script type="text/plain" id="editor"  style="margin-top:20px; height:500px; width: 1000px;; ">
    <p>这里我可以写一些输入提示</p>
</script>
<div>
	<input type="button" class="btn btn-primary "  id="note-publish" value="发   布" onclick="note_publish()"/>
</div>
</div>


</div>