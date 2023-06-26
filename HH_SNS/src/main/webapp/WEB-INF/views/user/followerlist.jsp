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
	<h1><a href="../feed/main">H&H</a></h1> <br>
	<h1>팔로워 리스트</h1>
	<br>
	<br>
	<input type = "hidden" id=userId value=${userId }>
	<c:forEach var="vo" items="${list }">
		<div onclick="location.href='../feed/mylist?userId=${vo.userId }';" >
		<img id="profileImage" src="display?fileName=${vo.userProfile }" alt="img" width="100" height="100" />
	 	@${vo.userId } (${vo.userNickname })
	 	<input type="hidden" id=userinfoUserId value="${vo.userId }">
		<c:if test="${empty userId or userId ne vo.userId }">
		 	<button class="followButton" >팔로우 하기</button>
 		</c:if>
		</div>
		<hr>	
	</c:forEach>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.followButton').on('click',function(e){ // 팔로우 버튼 클릭 시
			e.stopPropagation();
			var userinfoUserId = $(this).prevAll('#userinfoUserId').val();// 상대방 유저 아이디
			var userId = $('#userId').val();// 세션으로 존재하는 내 아이디
			if(userId != ''){
				var params = "userId=" + userinfoUserId; 
				console.log(params);
				
				if(!$(this).hasClass('following')){ // following 클래스가 없는 경우
					console.log('post');
					$.ajax({ //Ajax로 팔료우 하기 요청
						type : 'POST',
						url : '../users/follow?' + params,
						context: this,
						success: function(result){
							console.log(result);
							if(result == 1){ //팔로우 하기 성공
								alert("팔로우 완료");
								$(this).addClass('following');
								$(this).text('팔로우 중');
							}
						}
					})
				}else { //following 클래스가 있는 경우 
					console.log('delete');
					$.ajax({ //Ajax로 팔로우 취소 요청
						type : 'DELETE',
						url : '../users/' + userinfoUserId,
						context: this,
						headers : {
							'content-Type' : 'application/json'
						},
						success: function(result){
							console.log(result);
							if(result == 1){ // 팔로우 취소 성공
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
		
		$('.followButton').each(function(){ // 각각의 팔로우 버튼에서 팔로우 확인 실행
			var userinfoUserId = $(this).prevAll('#userinfoUserId').val();// 상대방 유저 아이디
			var userId = $('#userId').val();// 세션으로 존재하는 내 아이디
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
						if(result == 1){ //팔로우 한 경우
							console.log('팔로우한 계정');
							console.log($(this).text());
							$(this).addClass('following');
							$(this).text('팔로우 중');
							
						}else{ // 팔로우 하지 않은 경우
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