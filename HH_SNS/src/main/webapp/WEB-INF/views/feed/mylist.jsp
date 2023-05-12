<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.input_feed {
	display: block;
	flex-wrap: wrap;
	align-items: center;
	justify-content: space-between;
	width: 50%;
	margin: 0 auto;
	margin-bottom: 20px;
	margin-top: 20px;
	padding: 20px;
	background-color: #f7f7f7;
	border: 1px solid #ddd;
	height: 270px;
	position: relative; /* 추가 */
}

.feedContent {
	width: 70%;
	height: 70%;
	margin-right: 10px;
}

.btn_add {
	position: absolute;
	bottom: 0;
	width: 20%;
	height: 30px;
	background-color: #1da1f2;
	color: #fff;
	border: none;
	border-radius: 4px;
	font-size: 14px;
	cursor: pointer;
	margin-bottom: 1; /* 추가 */
	margin-right: 1px;
}

.div_post {
	display: block; /* flex에서 block으로 변경 */
	flex-wrap: wrap;
	justify-content: center;
	align-items: center;
	width: 80%;
	margin: 0 auto;
}

.post_item {
	width: 50%;
	margin: 0 auto; /* 가운데 정렬을 위해 추가 */
	margin-bottom: 20px;
	padding: 20px;
	background-color: #f7f7f7;
	border: 1px solid #ddd;
	margin: 0 auto; /* 가운데 정렬을 위해 추가 */
	margin-bottom: 20px;
	padding: 20px;
	background-color: #f7f7f7;
	margin-bottom: 20px;
	padding: 20px;
	background-color: #f7f7f7;
	padding: 20px;
	background-color: #f7f7f7;
	background-color: #f7f7f7;
}

.div_btn {
	display: flex;
	justify-content: space-between;
	margin-top: 10px;
}

.update_btn, .delete_btn {
	background-color: transparent;
	border: none;
	color: #1da1f2;
	font-size: 14px;
	cursor: pointer;
}

</style>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>회원 상세 페이지</title>
</head>
<body>
	<input type="hidden" id="feedId" value="1">

	<h1><a href="../feed/main">H&H</a></h1> <br>
	
	<!-- 
	not empty: 변수가 null이 아니고 값이 비어있지 않은 경우 참을 반환합니다.
	empty: 변수가 null
	== 또는 eq: 두 값이 같은 경우 참을 반환합니다.
	!= 또는 ne: 두 값이 다른 경우 참을 반환합니다.
	> 또는 gt: 왼쪽 값이 오른쪽 값보다 큰 경우 참을 반환합니다.
	< 또는 lt: 왼쪽 값이 오른쪽 값보다 작은 경우 참을 반환합니다.
	>= 또는 ge: 왼쪽 값이 오른쪽 값보다 크거나 같은 경우 참을 반환합니다.
	<= 또는 le: 왼쪽 값이 오른쪽 값보다 작거나 같은 경우 참을 반환합니다.
	
	 -->
	 
	 <%
	 String userId = (String) session.getAttribute("userId");
	 %>
	
	<div class="input_feed">
	<!-- 
	empty = null 
	userId = session
	-->
		<c:if test="${empty userId or userId ne feedvo.userId}">
		<p id="userProfile"><img width="100px" height="100px" src="display?fileName=${feedvo.userProfile}" /></p>
		</c:if>
		<c:if test="${not empty userId and userId eq feedvo.userId}">
		<p id="userProfile"><a href="../user/profileEdit"><img width="100px" height="100px" src="display?fileName=${feedvo.userProfile}" /></a></p>
		</c:if>
		<p id="userId"><b>${feedvo.userId}</b></p>
		<p id="userNickname">${feedvo.userNickname }</p>
		
		<c:if test="${empty userId and userId ne feedvo.userId}">
			<br>
		</c:if>
		<c:if test="${not empty userId and userId eq feedvo.userId}">
			<input type="text" id="feedContent" placeholder="피드 작성하기" required>
			<input type="submit" id="btn_add" value="등록"><br>
			<br>
			<button type="button" id="btn_profileEdit">프로필편집</button>
			<button type="button" id="btn_logout">로그아웃</button>
		</c:if>
		<br>
	</div>
	<hr>
	
	<div style="text-align: center;">
		<div id="feeds"></div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){

			$('#btn_login').click(function(){
				var target = encodeURI('/ex06/user/login');
				location = target;
			});
			
			$('#btn_profileEdit').click(function(){
				var target = encodeURI('/ex06/user/profileEdit');
				location = target;
			});
			
			$('#btn_logout').click(function(){
				location = '../user/logout';
			});
			
			getAllList();
			
			$('#btn_add').click(function() {
				var feedId = $('#feedId').val();
				const userId = document.getElementById("userId").textContent;
				var feedContent = $('#feedContent').val();
				console.log(feedContent);

				var obj = {
				'feedId' : feedId,
				'userId' : userId,
				'feedContent' : feedContent

				}
				console.log(obj);

				$.ajax({
					type : 'POST',
					url : '../feeds',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj),
					success : function(result) {
						console.log(result);
						if (result == 1) {
							console.log('★ 피드작성 완료');
							getAllMain();
						} else {
							console.log('★ 피드작성 실패');
						}
					}
				});// end ajax()
			});// end btn_add.click();
			
			
			function getAllList() {
				var feedId = $('#feedId').val();
				const userId = document.getElementById("userId").textContent;
				console.log('아이디 : ' + userId);

				var url = '../feeds/allbyId/' + userId;
					$.getJSON(
						url,
						function(data) {
							console.log(data);
							const userId = document.getElementById("userId").textContent;
							console.log(userId);
							var list = '';
								$(data).each(function() {
									console.log(this);
									
									var feedDate = new Date(this.feedDate);
									var yyyy = feedDate.getFullYear();
									var mm = String(feedDate.getMonth() + 1).padStart(2, '0'); // 0부터 시작하므로 +1
									var dd = String(feedDate.getDate()).padStart(2, '0');
									var feedDate = yyyy + '년 ' + mm + '월 ' + dd + '일';
									var disabled = 'disabled';
									var readonly = 'readonly';
									
									console.log('★');
									console.log(userId); // 접속한 회원
									console.log(this.userId); // 작성 된 회원
									
									list += '<div class="div_post">'
									+ '<div class="post_item">'
									+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
									+ '<p>' + '<img width="100px" height="100px" src="display?fileName=' + this.userProfile + '" />' + '</p>'
									+ '<p>' + '<b>' + this.userId +'</b>' + '</p>'
									+ '<p>' + this.userNickname + '</p>'
									+ feedDate
									+ '&nbsp;&nbsp;'
									+'<p id="feedContent">' + '<a href="../feed/detail?feedId=' + this.feedId + '">' + this.feedContent +'</p>'
									+ '</div>'
									+ '</div>';


							});// end data.funchion;
							
							$('#feeds').html(list);
						}//end function(data);
					);// end getJSON();
				}// end getAllList();
			
			
			
		});// end ready.function();
	</script>
</body>
</html>