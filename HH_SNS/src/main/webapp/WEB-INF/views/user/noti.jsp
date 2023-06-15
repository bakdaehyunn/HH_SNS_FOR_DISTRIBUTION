<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>Profile Edit</title>
</head>
<body>
	<h1>알림창</h1>
	<br>
	<br>
	<%--
	<c:forEach var="vo" items="${list }">
	<div onclick="location.href='../feed/mylist?userId=${vo.userId }';">
	<img id="profileImage" src="display?fileName=${vo.userProfile }" alt="img" width="100" height="100" />
 	@${vo.userId } (${vo.userNickname })
 	<input type="hidden" id=userinfoUserId value="${vo.userId }">
 	<c:if test="${empty userId or userId ne vo.userId }">
 	<button class="followButton" >팔로우 하기</button>
 	</c:if>
 	
	</div>
	
	<hr>	
	</c:forEach>
	 --%>
	 
	
	 <div id="notis"></div>
	<script type="text/javascript">
	$(document).ready(function() {
		//setInterval(checkNoti,5000);
		getNotiList();
		setInterval(getNotiList,1000);
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
					var list = '';
					$(data).each(function() {
						console.log(this);
						if(this.feedId != ''){
							if(this.notiCategory =='reply'){
								list +='<div class="noti_reply">'
								+ this.senderId +'님이 회원님의 게시물에 댓글을 달았습니다.'
								+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
								+'</div>'
								+'<hr>'
							}else if(this.notiCategory == 'like'){
								list +='<div class="noti_like">'
									+ this.senderId +'님이 회원님의 댓글에 좋아요를 눌렀습니다.'
									+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
									+'</div>'
									+'<hr>'
							}
						}
						else if(this.notiCategory == 'follow'){
							list +='<div class="noti_follow">'
								+ this.senderId +'님이 회원님을 팔로우 합니다.'
								+ '<input type="hidden" id="receiverId" value="' + this.receiverId + '">'
								+'</div>'
								+'<hr>'
						}
						$('#notis').html(list);
						
					});
				}//success

			});// ajax()
		}
		$(document).on('click','.noti_follow',function(){
			var receiverId = $(this).find('#receiverId').val();
			var target =encodeURI('/ex06/feed/mylist?userId='+receiverId);
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
		
	});// ready()
	</script>
</body>
</html>