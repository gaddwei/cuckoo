<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<!-- 登录界面 -->
<div class="modal fade" id="dialog_tohome" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog"  >
       <div class="modal-content" style="width: 630px;" > 
           <div class="modal-header" >
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
               <h4 class="modal-title" id="myModalLabel">发布我的首页</h4>
           </div>
           <div class="modal-body" >
           	<img class="img-responsive img-tohome" id="img-home" src="base/img/4.jpg">
            <!-- 表单控件 -->
            <form >
            <div class="file-box-home">
	 				<input type='button' class='btn btn1' value='上传首页图片...' />
	    			<input type="file" name="userico" class="file_ico" id="img_tohome" size="28" onchange="ajaxHomeImgUpload()" />
 				</div>
 				<div class="input-group">
					  <input type="text" class="form-control" style="width:600px;" placeholder="首页描述..." id="tx_cr_home" name="tx_cr_home" >
					</div>
					<p id="warning_tohome_cr" style="font-size: 10px; margin-top: 5px;">&nbsp;</p>
            <div class="modal-footer" style="height: 20px;">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="submitToHome()">确认发布</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
</div>