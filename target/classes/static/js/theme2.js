var responseData = {};
$(document).on('click', '.panel-heading span.icon_minim', function (e) {
    var $this = $(this);
    if (!$this.hasClass('panel-collapsed')) {
        $this.parents('.panel').find('.panel-body').slideUp();
        $this.addClass('panel-collapsed');
        $this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
    } else {
        $this.parents('.panel').find('.panel-body').slideDown();
        $this.removeClass('panel-collapsed');
        $this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
    }
});
$(document).on('focus', '.panel-footer input.chat_input', function (e) {
    var $this = $(this);
    if ($('#minim_chat_window').hasClass('panel-collapsed')) {
        $this.parents('.panel').find('.panel-body').slideDown();
        $('#minim_chat_window').removeClass('panel-collapsed');
        $('#minim_chat_window').removeClass('glyphicon-plus').addClass('glyphicon-minus');
    }
});
$(document).on('click', '#new_chat', function (e) {
    var size = $( ".chat-window:last-child" ).css("margin-left");
     size_total = parseInt(size) + 400;
    alert(size_total);
    var clone = $( "#chat_window_1" ).clone().appendTo( ".container" );
    clone.css("margin-left", size_total);
});
$(document).on('click', '.icon_close', function (e) {
    //$(this).parent().parent().parent().parent().remove();
    $( "#chat_window_1" ).remove();
});

$("#btn-chat").on("click",function(){
	
	var chatMsg = $("#chat_message").val();

	
	$.get( "http://localhost:4007/chatbot/call", {"message":"" +$("#chat_message").val() +"" },function( data ) {
		responseData = data;
        console.log( data ); // John
//        $(".msg_container_base h3").remove();
//        $(".msg_container_base br").remove();
    		var html = '';
    		
    		html+='<div class="row msg_container base_sent">';
	    	html+='<div class="col-xs-10 col-md-10 msg-txt">';
	    	html+='<div class="messages msg_receive">';
	     	html+='<p>'+$("#chat_message").val()	+'</p><br>';
	     	var dt1 = new Date();
	        //document.getElementById("datetime").innerHTML = dt.toLocaleTimeString();
	        
	        html+='<time datetime="2009-11-13T20:00">'+dt1.toLocaleTimeString()+'</time>';
	   // 	html+='<time datetime="2009-11-13T20:00">John  51 min</time>';
	    	html+='</div>';
	    	html+='</div>';
	    	html+='</div>';
	    	
	    
        
    		html+='<div class="row msg_container base_receive">';
	    	html+='<div class="col-xs-10 col-md-10 msg-txt">';
	    	html+='<div class="messages msg_sent">';
        
        for (var i=0;i<responseData.response.length;i++){
	    		console.log(responseData.response[i]["Test Case ID"]);
	    		console.log(responseData.response[i]["Similarity Index"]);
	    	
    
	    	
	    	html+='<p>'+'SOP ID:<a href="#" class="show_details_modal" data-array-index="'+i+'" data-test-case-id="'+responseData.response[i]["Test Case ID"]+'">'+responseData.response[i]["Test Case ID"]+' </a> Similarity Index:'+responseData.response[i]["Similarity Index"]+'</p><br>';

        }
        var dt = new Date();
        //document.getElementById("datetime").innerHTML = dt.toLocaleTimeString();
        
        html+='<time datetime="2009-11-13T20:00">'+dt.toLocaleTimeString()+'</time>';
	    	html+='</div>';
	    	html+='</div>';
	    	html+='</div>';
        
        $('.msg_container_base').append(html);
        $("#chat_message").val('');	
        
        
      }, "json").error(function() { 
         alert("error"); 
      });
	
	
	
});

$('body').on('click','.show_details_modal', function(e){
	//alert('hi');
	var testCaseId = $(this).attr('data-test-case-id');
	var arrayIndex = $(this).attr('data-array-index');
	//console.log('clicked'+testCaseId);
	console.log(responseData.response[arrayIndex]);
	var modal_body_html = '';
	//modal_body_html += '<div><strong>Test Case ID: </strong>'+responseData.response[arrayIndex]['Test Case ID']+'</div>';
	modal_body_html += '<div><strong>Test Name: </strong>'+responseData.response[arrayIndex]['Test Name']+'</div><br>';
	modal_body_html += '<div><strong>Test Description: </strong>'+responseData.response[arrayIndex]['Test Description']+'</div><br>';
	$('#defectDetailsModalLabel').html('SOP ID: '+responseData.response[arrayIndex]['Test Case ID']);
	$('#defectDetailsModalBody').html(modal_body_html);
	$('#defectDetailsModal').modal('show');
	
	
});