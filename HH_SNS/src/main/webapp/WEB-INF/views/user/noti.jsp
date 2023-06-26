<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#notis{
  border: 1px solid #ccc;
  padding: 20px;
  width: 520px;
  margin: 0 auto;
  text-align: center;
}
#title{
 text-align: center;
}
#notificationItem {
  border: 1px solid #ccc;
  padding: 10px;
  width:500px;
  margin-bottom: 10px;
  background-color: #f8f8f8;
}
</style>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>Profile Edit</title>
</head>
<body>
<h1><a href="../feed/main">H&H</a></h1> <br>
	<h1 id="title">알림창</i></h1>
	<br>
	<br>
	 <div id="notis"></div>
	<script type="text/javascript">
	$(document).ready(function() {
		//setInterval(checkNoti,5000);
		getNotiList();
		setInterval(getNotiList,1000); // 1초마다 함수 실행
		function checkNoti(){
			$.ajax({
				type: 'GET',
				url : '../users/notiCheck',
				headers : {
					'content-Type' : 'application/json'
				},
				success: function(data){
					//if(data != ''){
						//$(data).each(function() {
						//	console.log(this);
						//});
					console.log(data);
					if(data == 1){
						console.log("성공");
					}else {
						console.log("다시");
					}
				}//success

			});// ajax()
		}// checkNoTi()
		function getNotiList(){
			$.ajax({
				type: 'GET',
				url : '../users/notiList',
				headers : {
					'content-Type' : 'application/json'
				},
				success: function(data){
					if(data ==''){
						$('#notis').html('알림 내역이 없습니다.!!');
					}
					var list = '';
					$(data).each(function() {
						console.log(this);
						if(this.feedId != ''){ // feedId 값 있는 카테고리
							if(this.notiCategory =='reply'){ // 댓글 알림
								list +='<div class="noti_reply" id="notificationItem" >'
								+ this.senderId +'님이 회원님의 게시물에 댓글을 달았습니다.'
								+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
								+ '<input type="hidden" id="notiId" value="' + this.notiId + '">'
								+'<button class="delete" style="display: inline-block;">X</button>'+'</div>'
								
							}else if(this.notiCategory == 'like'){ // 좋아요 알림
								list +='<div class="noti_like" id="notificationItem">'
									+ this.senderId +'님이 회원님의 댓글에 좋아요를 눌렀습니다.'
									+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
									+ '<input type="hidden" id="notiId" value="' + this.notiId + '">'
									+'<button class="delete" style="display: inline-block;">X</button>'+'</div>'
									
							}else if(this.notiCategory =='comment'){ // 대댓글 알림
								list +='<div class="noti_comment" id="notificationItem" >'
									+ this.senderId +'님이 회원님의 댓글에 대댓글을 달았습니다.'
									+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
									+ '<input type="hidden" id="notiId" value="' + this.notiId + '">'
									+'<button class="delete" style="display: inline-block;">X</button>'+'</div>'
							
							}
						}// FeedId 값 없는 카테고리
						else if(this.notiCategory == 'follow'){ // 팔로우 알림
							list +='<div class="noti_follow" id="notificationItem">'
								+ this.senderId +'님이 회원님을 팔로우 합니다.'
								+ '<input type="hidden" id="senderId" value="' + this.senderId + '">'
								+ '<input type="hidden" id="notiId" value="' + this.notiId + '">'
								+'<button class="delete"style="display: inline-block;">X</button>'+'</div>'
								
						}
						$('#notis').html(list);
						
					});
				}//success

			});// ajax()
		}
		$(document).on('click','.delete',function(e){
			e.stopPropagation();
			var notiId = $(this).prevAll('#notiId').val();
			$.ajax({
				type : 'DELETE',
				url : '../users/notiDelete/' + notiId,
				context: this,
				headers : {
					'content-Type' : 'application/json'
				},
				success: function(result){
					console.log(result);
					if(result == 1){
						console.log('알림 삭제 성공');
						getNotiList();
						
					}
				}
			})
		});
	
		$(document).on('click','.noti_follow',function(){
			var senderId = $(this).find('#senderId').val();
			var target =encodeURI('/ex06/feed/mylist?userId='+senderId);
			location = target;
		});
		$(document).on('click','.noti_reply',function(){
			var feedId = $(this).find('#feedId').val();
			var target =encodeURI('/ex06/feed/detail?feedId='+feedId);
			location = target;
		});
		$(document).on('click','.noti_like',function(){
			var feedId = $(this).find('#feedId').val();
			var target =encodeURI('/ex06/feed/detail?feedId='+feedId);
			location = target;
		});
		$(document).on('click','.noti_comment',function(){
			var feedId = $(this).find('#feedId').val();
			var target =encodeURI('/ex06/feed/detail?feedId='+feedId);
			location = target;
		});
		
	});// ready()
	</script>
</body>
</html>