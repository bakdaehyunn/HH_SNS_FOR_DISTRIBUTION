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
	<div id="feedContent"  contenteditable="true"></div>
	<div id="feedContents"  contenteditable="true"></div>
	<div id ="feedTagList" style="position: relative;"></div>
		</div>
	
	
	<script type="text/javascript">
		var onTag=false;
		var startPos;
		$('#feedContents').on('keypress',function(e){
			console.log(e.keyCode);
			if($('feedContents').text().substr(startPos,1) !='@'){
				if(e.keyCode==64){
					onTag=true;
					console.log("@");
					
				}else if(e.keyCode==32){
					onTag=false;
					console.log("스페이스바");
				}
			}else{
				onTag=false;
			}
			
		});
		$('#feedContents').on('input',function(){
			var feedContent =$(this).text();
			var feedTagList = $('#feedTagList');
			console.log(feedContent);
			startPos=this.selectionStart;
			
			
			if(onTag===true){
				console.log('태그시작');
				
				console.log('위치: '+(startPos));
				onTag=true;
				var followingUserId = feedContent.substr(startPos+1);
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
								list += '<div class="tag_item">'
								+'<img id="profileImage" src ="display?fileName='+ this.userProfile+'"alt="img" width="100" height="100" />'
								+'@'+this.userId +'('+this.userNickname+')'
								+'<input type="hidden" class="userId" value="'+this.userId+'">'
								+'</div>'
								+'<hr>';
							})
							$('#feedTagList').html(list);
						}
						
					});// ajax()
				}
				
				
				
				
				
			}
			else if (feedContent.substr(-1).trim().length == 0 ||  feedContent.substr(-2) =='@@' || onTag===false ){
				$('#feedTagList').text('');
				console.log('태그아님');
				onTag=false;
				
			} // if else 문 끝
		});// input 이벤트
		
		$('#feedTagList').on('click', '.tag_item', function(){
			var feedContent =$('#feedContents').text();
			var pos = feedContent.lastIndexOf('@');
			console.log('위치: '+pos);
			var list = feedContent.substr(0,pos);
			
			var userId = $(this).find('.userId').val();
			list  +=  '<a href="../feed/mylist?userId=' + userId + '">' + '@'+userId +'</a>'
			$('#feedContents').html(list);
		});
		
		
		
		
		
		/////////////////----------------------------------------------------------------------
		$('#feedContent').on('input',function(){
			var feedContent =$(this).text();
			var feedTagList = $('#feedTagList');
			console.log(feedContent);
			
			
			console.log('첫번째 글자 : '+ feedContent.length);
			if(onTag===true){
				console.log('태그시작');
				var pos = feedContent.lastIndexOf('@');
				console.log('위치: '+(pos));
				onTag=true;
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
								list += '<div class="tag_item">'
								+'<img id="profileImage" src ="display?fileName='+ this.userProfile+'"alt="img" width="100" height="100" />'
								+'@'+this.userId +'('+this.userNickname+')'
								+'<input type="hidden" class="userId" value="'+this.userId+'">'
								+'</div>'
								+'<hr>';
							})
							$('#feedTagList').html(list);
						}
						
					});// ajax()
				}
				
				
				
				
				
			}
			else if (feedContent.substr(-1).trim().length == 0 ||  feedContent.substr(-2) =='@@' || onTag===false ){
				$('#feedTagList').text('');
				console.log('태그아님');
				onTag=false;
				
			} // if else 문 끝
		});// input 이벤트
		
		$('#feedTagList').on('click', '.tag_item', function(){
			var feedContent =$('#feedContent').text();
			var pos = feedContent.lastIndexOf('@');
			console.log('위치: '+pos);
			var list = feedContent.substr(0,pos);
			
			var userId = $(this).find('.userId').val();
			list  +=  '<a href="../feed/mylist?userId=' + userId + '">' + '@'+userId +'</a>'
			$('#feedContent').html(list);
		});
	</script>
</body>
</html>