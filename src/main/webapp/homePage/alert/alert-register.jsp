<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<!-- 注册界面 -->
<div class="modal fade" id="identifier" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog" style="width: 530px">
       <div class="modal-content">
           <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
               <h4 class="modal-title" id="myModalLabel">用户注册</h4>
           </div>
           <div class="modal-body" style="height:440px ;margin-left: 20px; ">
            <!-- 表单控件 -->
            <form >
            	 <div>
            		头像:
	            	<img alt="用户头像" id="re_ico" src="<%=request.getContextPath()%>/base/img/usericon.jpg"  class="img-rounded userico" ><br>
	            <div class="file-box">
	 				<input type='button' class='btn btn1' value='上传头像...' />
	    			<input type="file" name="userico" class="file_ico" id="re_userico" size="28" onchange="ajaxFileUpload()" />
 				</div>
            	</div><br>
            	<div>
            		昵称:
            		<input type="text" class="form-control " placeholder="请输入昵称" id="re_nick" name ="re_nick" onblur="sure_nick()"/>&nbsp;
            		<p id="msg_nick" class="msg_warning" >&nbsp;</p>
            	</div>
            	<div>
            		性别:
            		<input type="radio" name="re_sex" id="man" value="1"/><label for="man" style="display: inline-block;" >&nbsp;男 </label>
            		<input type="radio" name="re_sex" id="wom" value="0"/><label for="wom" style="display: inline-block;" >&nbsp;女</label>
            	</div><br>
            	<div>
            		年龄:
            		<input type="text" class="form-control " placeholder="请输入年龄" name="re_age" id="re_age" onblur="sure_age()"/>
            		<p id="msg_age" class="msg_warning" >&nbsp;</p>
            	</div>
            	
            	<div>
            		邮箱:
            		<input type="email" class="form-control " placeholder="请输入邮箱" id="re_email" name="re_email" onblur="sure_email()"/>
            		<p id="msg_email" class="msg_warning" >&nbsp;</p>
            	</div>
            	<div>
            		密码:
            		<input type="password" class="form-control " placeholder="请输入密码" id="re_password" name="re_password" onblur="sure_password()"/>&nbsp;&nbsp;
            		重复密码:
            		<input type="password" class="form-control " placeholder="请确认密码" id="re_re_password"  name="re_re_password" onblur="sure_password()"/>
            		<p id="msg_password" class="msg_warning" >&nbsp;</p>
            	</div>
            </div>
            <div class="modal-footer" style="height: 30px;">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="submitData()">确认注册</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>