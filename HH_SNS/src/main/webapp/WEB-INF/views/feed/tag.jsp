<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div id="feedContent" class="editable" contenteditable="true"></div>
	<div id ="feedHashtagList" style="position: relative;"></div>
		</div>
	
	
	<script type="text/javascript">
		var s=false;
		$('#feedContent').on('input',function(){
			var feedContent =$(this).text();
			var feedHashtagList = $('#feedHashtagList');
			console.log(feedContent);
			
			console.log('첫번째 글자 : '+ feedContent.length);
			if((feedContent =='@')||feedContent.substr(-2) == ' @'|| (s===true&&!(feedContent.substr(-1).trim().length == 0) )){
				console.log('태그시작');
				var pos = feedContent.lastIndexOf('@');
				console.log('위치: '+(pos+1));
				s=true;
				var followingUserId = feedContent.substr(pos+1);
				if(!followingUserId){
					console.log('아이디값 아직 없음');
				}
				else{
					console.log('아이디값 있음');
					console.log('followingUserId:' + followingUserId);
					$.ajax({
						type: 'GET',
						url : '../feeds/tagList/'+followingUserId,
						hdeaders : {
							'Content-Type' : 'application/json'
						},
						data : followingUserId,
						success: function(data){
							var list = '';
							$(data).each(function(){
								console.log(this);
							})
						}
						
					});// ajax()
				}
				
				
				
				
				$('#feedHashtagList').append('aasdasd <br>');
			}else if (feedContent.substr(-1).trim().length == 0 ||  feedContent.substr(-2) =='@@' || s===false ){
				$('#feedHashtagList').text('');
				console.log('태그아님');
				s=false;
				
			} // if else 문 끝
		});// input 이벤트
	</script>
</body>
</html>