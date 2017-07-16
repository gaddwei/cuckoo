<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<!-- 登录界面 -->
<div class="modal fade" id="transmit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog" style="width:450px; " >
       <div class="modal-content"  style="border-top: solid #FA7F40 3px;"> 
           <div class="modal-header" >
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
               <div >
               		<span >用户转发游记</span>
               </div>
           </div>
           <div class="modal-body" >
           		<div class="transmit-area-head">
           			<textarea rows="5" cols="50" class="transmit-area" placeholder="总字数小于120字大于10字" id="transmit-area"></textarea>
            		<button id="transmit-area-btn" class="btn">转发</button>
           		</div>
           		<fieldset><legend class="transmit-num-head" id="transmint-total">目前已转发5条</legend></fieldset>
            	<ul class="transmit-modal-ul" id="transmit-modal-ul">
            	</ul>
            <a href="" id="transmit-more">查看更多...</a>
        </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
</div>