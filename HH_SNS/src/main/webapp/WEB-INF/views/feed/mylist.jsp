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
	height: auto;
	position: relative; /* 추가 */
}

.feedContent {
	width: 70%;
	height: auto;
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

	<div class="input_feed">
	<!-- 
	empty = null 
	userId = session
	-->

		<!-- 세션아이디값 X -->
		<c:if test="${empty userId}">
		<p id="userProfile"><img width="100px" height="100px" src="display?fileName=${userinfovo.userProfile}" /></p>
		</c:if>
		<!-- 세션아이디값 O / 그리고 세션아이디 != 유저 아이디 -->
		<c:if test="${not empty userId  }">
		<p id="userProfile"><img width="100px" height="100px" src="display?fileName=${userinfovo.userProfile}" /></p>
		</c:if>
		<a href="../user/followerlist?userId=${userinfovo.userId}"> 팔로워 : ${followerCnt}</a>
		<a href="../user/followinglist?userId=${userinfovo.userId}"> 팔로잉 : ${followingCnt}</a>
		<c:if test="${empty userId or userinfovo.userId ne userId}">
		<button id="btn_follow">팔로우 하기</button>
		</c:if>

		<p id="userId"><b>${userinfovo.userId}</b></p>
		<p id="userNickname">${userinfovo.userNickname }</p>
		
		<c:if test="${empty userId and userId ne userinfovo.userId}">
			<br>
		</c:if>
		<c:if test="${not empty userId and userId eq userinfovo.userId}">
			<div style="display: flex;">
			<div style="background-color: #ffffff;  min-width: 620px; width: auto; margin-right: 20px;" id="feedContent" contentEditable='true' >
			</div>
			<input type="submit" id="btn_add" value="등록">
			</div>
			<div id="check_feedContent" style="display: none;"></div>
			<br>
			<button type="button" id="btn_profileEdit">프로필편집</button>
			<button type="button" id="btn_logout">로그아웃</button>
		</c:if>
	</div>
	<hr>
	
	<div style="text-align: center;">
		<div id="feeds"></div>
	</div>
	<br>
	
	<script type="text/javascript">
		$(document).ready(function(){
			followcheck();
			function followcheck(){
				var userinfoUserId = "<c:out value='${userinfovo.userId }' />";
				var userId = "<c:out value='${userId}' />";
				if(userId != ''){
					$.ajax({
						type : 'GET',
						url : '../users/followCheck/'+userinfoUserId,
						header : {
							'Content-Type' : 'application/json'
						},
						success : function(result){
							console.log(result);
							if(result == 1){
								console.log('팔로우한 계정');
							}else{
								console.log('팔로우한 계정 아님');
							}
						}
						
					})
				}
				
			}
			$('#btn_follow').click(function(){
				//var feedId = ${userinfovo.userId};
				var userinfoUserId = "<c:out value='${userinfovo.userId }' />";
				var userId = "<c:out value='${userId}' />";
				if(userId != ''){
					var obj = {
						'userinfoUserId'  : userinfoUserId,
						'userId' : userId
					}	
					console.log(obj);
					$.ajax({
						type : 'POST',
						url : '../users/follow',
						headers : {
							'content-Type' : 'application/json'
						},
						data : JSON.stringify(obj),
						success: function(result){
							console.log(result);
							if(result == 1){
								alert("팔로우 완료");
							}
						}
					})
				} else{
					alert("로그인 해주세요.");
					location.href="../user/login";
				}
			});

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
				var feedContent = $('#feedContent').text();
				console.log(feedContent);

				var obj = {
				'feedId' : feedId,
				'userId' : userId,
				'feedContent' : feedContent

				}
				console.log(obj);
				
				var list = '';
				if(feedContent == '') {
					list ='<i style="font-size: 14px">피드를 입력해주세요.</i>';
					$('#check_feedContent').html(list);
					$('#check_feedContent').show();
					return;
				}

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
							getAllList();
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
							var datasize = data.length;
							console.log('피드 개수 : ' + datasize);
							const userId = document.getElementById("userId").textContent;
							console.log(userId);
							var list = '';
							if(data.length > 0) {
								$(data).each(function() {
									
									var feedDate = new Date(this.feedDate);
									var yyyy = feedDate.getFullYear();
									var mm = String(feedDate.getMonth() + 1).padStart(2, '0'); // 0부터 시작하므로 +1
									var dd = String(feedDate.getDate()).padStart(2, '0');
									var feedDate = yyyy + '년 ' + mm + '월 ' + dd + '일';
									list += '<br>'
									+ '<div class="div_post">'
									+ '<div class="post_item">'
									+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
									+ '<p>' + '<img width="100px" height="100px" src="display?fileName=' + this.userProfile + '" />' + '</p>'
									+ '<p>' + '<b>@' + this.userId + "(" + this.userNickname + ")" + '</b>' + '</p>'
									+ feedDate
									+ '&nbsp;&nbsp;'
									+'<p id="feedContent">' + '<a href="../feed/detail?feedId=' + this.feedId + '">' + this.feedContent +'</a>' +'</p>'
									+ '</div>'
									+ '</div>';


							});// end data.funchion;
						} else {
							console.log('피드가 없음');
							list += '<br>' 
							+ '<p><i>피드를 작성해보세요 !</i></p>';
						}
							
					$('#feeds').html(list);
				}//end function(data);
			);// end getJSON();
		}// end getAllList();
			
			
			
		});// end ready.function();
	</script>
</body>
</html>