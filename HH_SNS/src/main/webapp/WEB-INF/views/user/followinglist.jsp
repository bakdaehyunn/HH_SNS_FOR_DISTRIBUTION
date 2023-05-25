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
	<h1>팔로잉 리스트</h1>
	<br>
	<br>
	<c:forEach var="vo" items="${list }">
	<div onclick="location.href='../feed/mylist?userId=${vo.userId }';">
	<img id="profileImage" src="display?fileName=${vo.userProfile }" alt="img" width="100" height="100" />
 	<input type="hidden" id=userinfoUserId value="${vo.userId }">
 	@${vo.userId } (${vo.userNickname })
 	<c:if test="${empty userId or userId ne vo.userId }">
 	<button class="followButton" >팔로우 하기</button>
 	</c:if>
 	
	</div>
	
	<hr>	
	</c:forEach>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.followButton').on('click',function(e){
			e.stopPropagation();
			var userinfoUserId = $(this).prevAll('#userinfoUserId').val();
			var userId = "<c:out value='${userId}' />";
			if(userId != ''){
				var obj = {
					'userinfoUserId'  : userinfoUserId,
					'userId' : userId
				}	
				console.log(obj);
				if(!$(this).hasClass('following')){
					console.log('post');
					$.ajax({
						type : 'POST',
						url : '../users/follow',
						context: this,
						headers : {
							'content-Type' : 'application/json'
						},
						data : JSON.stringify(obj),
						success: function(result){
							console.log(result);
							if(result == 1){
								alert("팔로우 완료");
								$(this).addClass('following');
								$(this).text('팔로우 중');
							}
						}
					})
				}else {
					console.log('delete');
					$.ajax({
						type : 'DELETE',
						url : '../users/' + userinfoUserId,
						context: this,
						headers : {
							'content-Type' : 'application/json'
						},
						success: function(result){
							console.log(result);
							if(result == 1){
								alert("팔로우 취소 완료");
								$(this).removeClass('following');
								$(this).text('팔로우 하기');
								
							}
						}
					})
					
				}
			} else{
				alert("로그인 해주세요.");
				location.href="../user/login";
			}
		});
		
		$('.followButton').each(function(index,item){
			
			var userinfoUserId = $(this).prevAll('#userinfoUserId').val();
			var userId = "<c:out value='${userId}' />";
			console.log(userinfoUserId);
			if(userId != ''){
				$.ajax({
					type : 'GET',
					url : '../users/followCheck/'+userinfoUserId,
					context: this,
					header : {
						'Content-Type' : 'application/json'
					},
					success : function(result){
						console.log(result);
						if(result == 1){
							console.log('팔로우한 계정');
							console.log($(this).text());
							$(this).addClass('following');
							$(this).text('팔로우 중');
							
						}else{
							console.log('팔로우한 계정 아님');
							$(this).removeClass('following');
							$(this).text('팔로우 하기');
						}
					}
					
				})
			}
			
		});
	});
	</script>
</body>
</html>