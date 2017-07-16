<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<!-- 登录界面 -->
<div class="modal fade" id="dialog_login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog" style="width:400px;" >
       <div class="modal-content"  > 
           <div class="modal-header" >
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
               <div >
               		<span class="glyphicon glyphicon-user" style=" margin-bottom:1px; font-size: 25px; margin-left: 47%;"></span><br>
               		<span style="margin-left: 43%;">用户登录</span>
               </div>
           </div>
           <div class="modal-body" >
            <!-- 表单控件 -->
            <form >
            	<div style="width: 300px; margin: 20px auto;">
            		<div class="input-group">
					  <span class="input-group-addon glyphicon glyphicon-envelope" id="basic-addon1"></span>
					  <input type="email" class="form-control" placeholder="email" aria-describedby="basic-addon1" id="login_email" name="email" onblur="sure_email_login()">
					</div>
						<p id="warning_login_email" style="font-size: 10px; margin: 7px 38px;">&nbsp;</p>
					<div class="input-group">
					  <span class="input-group-addon glyphicon glyphicon-lock" id="basic-addon1"></span>
					  <input type="password" class="form-control" placeholder="password" aria-describedby="basic-addon1" id="login_password" name="password" onblur="sure_password_login()">
					</div>
						<p id="warning_login_password" style="font-size: 10px; margin: 7px 38px;">&nbsp;</p>
            	</div>
            	
            <div class="modal-footer" style="height: 20px;">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="submitLogin()">确认登录</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
</div>