var popular_Search = function() {

	let $scrollbar = $('#scrollbar');
	
	let number = [ 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight' ];

	let initUrl ='/search/init';
	let afterUrl = '/search/after';
	
	let flag = true;
	
	
	
	function init() {
		
		window.onunload = function () {
			flag = false;
		}
		
		$.ajax({
			url : initUrl,
			type : 'get',
			success : function(data) {
				mkList(data);
			},
			dataType : 'json'
		}).done(() => poll());
	}


	

	function poll() {
		$.ajax({
			url : afterUrl,
			type : 'get',
			data: {
				flag: flag
			},
			success : function(data) {
				if (data !== null) {
					mkList(data);					
				}
			},
			dataType : "json",
			complete : poll,
			timeout : 30000
		});
	}
	


	
	
	function mkList(data) {
		let num = 0;
		let html = '';
		for (var one of data) {
			//let skey = one.skey;
			//let count = one.count;
			const {skey, count} = one;
			html 
				+= '<div class="profile-post color-' + number[num++] + '">'
					+ '<span class="profile-post-numb">0' + num + '</span>'
					+ '<div class="profile-post-in">'
						+ '<h3 class="heading-xs"><a href="/?skey=' + skey + '">' + skey + '</a></h3>'
					+ '</div>'
				+   '</div>' 
		}
		$scrollbar.html(html);
	}


	
	
	return {
		init : init
	}


}();