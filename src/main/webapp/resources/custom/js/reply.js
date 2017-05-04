var Reply = function () {

    return {
    	initReply: function () {
    		var $comment = $('#comment')
    		var $content = $('#content')
    		var board_seq = $comment.attr('data-board-seq');
    		var my_user_email = $comment.attr('data-user-email');
    		
        	(function() {
        		var data = {board_seq: board_seq}
        		$.get('/board/reply', data)
        			.done((json) => {
           	    		makeReplyList(json)
        	    	}).fail(() => { alert("get fail") })
        	})()
        	
        	
        	$('#addReplyBtn').on('click', function() {
    			var data = {
    				board_seq : board_seq,
    				content: $content.val()
    			}
   			
    			$.post('/board/reply', data)
    				.done(json => {
    					makeReplyList(json)
    				}).fail(() => { alert("fail") })
    			
    			$content.val('')
    		})
        	
        	
        	$(document).on('click', '#delReplyBtn', function() {
        		var data = {
    				reply_seq: $(this).attr('data-reply-seq')
    			}
        		$.post('/board/reply/' + board_seq, data)
        			.done(json => {
        				makeReplyList(json)
        			}).fail(() => { alert("fail") })
        	})
  	
        	
        	function makeReplyList(data) {
    			var body = ""  
    			$.each( data, function( key, val ) {
    		        body+='<div class="media media-v2">'
    	            body+='   <div class="media-body">'
    	            body+='      <h4 class="media-heading">'
    	            body+='         <strong><a href="#">'+ val.user_nick +'</a></strong>'
    	            body+='         <small>'+val.reg_date+'</small>'
    	            body+='      </h4>'
    	            body+='      <pre>' + val.content + '</pre>'
    	            if (my_user_email == val.user_email) {
    	            	body+='  <button class="btn btn-default" id="delReplyBtn" data-reply-seq="'+val.reply_seq+'">삭제</button>'
    	            }
    	            body+='   </div>'
    	            body+='</div>'
    			 })
    			
    			 $comment.html(body)
    		}
    		
        }
    }
}()