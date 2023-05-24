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
	min-width: 660px;
	width: 50%;
	margin: 0 auto;
	margin-bottom: 20px;
	margin-top: 20px;
	padding: 20px;
	background-color: #ffffff;
	border: 1px solid #ddd;
	height: auto;
	position: relative; /* 추가 */
}

#feedContent {
	display: flex;
	background-color: #ffffff;  
	min-width: 600px; 
	width: auto; 
	min-height: 80px; 
	height: auto; 
	margin-right: 20px;
}

.feedContent {
	display: flex;
	background-color: #ffffff;  
	width: auto; 
	height: auto; 
	text-align: center; 
	justify-content: center;
	align-items: center;
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
	min-width: 620px;
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
	background-color: #ffffff;
	border: 1px solid #ddd;
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

.like_item {
    display: flex;
    align-items: center;
}

.btn_like {
  cursor: none;
}

.btn_like.liked path {
  fill: #e74c3c;
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
		<c:if test="${not empty userId}">
		<p id="userProfile"><img width="100px" height="100px" src="display?fileName=${userinfovo.userProfile}" /></p>
		</c:if>
		<a href="../user/mylist?userId=${userinfovo.userId}"> 팔로잉 : ${followingCnt}</a>
		<a href="../feed/mylist?userId=${userinfovo.userId}"> 팔로워 : ${followerCnt}</a>
		<div>팔로잉 : ${followingCnt}</div>
		<div></div>
		<p id="userId"><b>${userinfovo.userId}</b></p>
		<p id="userNickname">${userinfovo.userNickname }</p>
		
		<c:if test="${empty userId and userId ne userinfovo.userId}">
			<br>
		</c:if>
		<c:if test="${not empty userId and userId eq userinfovo.userId}">
			<div style="display: flex;">
			<div id="feedContent" contentEditable='true'>
			</div>
			<input style=" width: auto; height: 30px;" type="submit" id="btn_add" value="등록">
			</div>
			
			<form id="uploadForm" enctype="multipart/form-data">
  			<input type="file" id="upload" style="display: none;" accept=".gif, .jpg, .png" />
			</form>
	
			
			<div id="preview" contentEditable='true'></div>
			
			<br>
			<button style="border: none; background-color: #ffffff">
			<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 28 28" width="28" height="28" fill="#c7c7c7" stroke="#707070" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" id="feedPicture">
	  		<path d="M20.2 7l-1.7-1.7c-.2-.2-.5-.3-.8-.3H5.5c-.3 0-.6.1-.8.3L2.8 7c-.4.4-.5 1-.3 1.5L4 17.3c.1.6.6 1 1.2 1h12.6c.7 0 1.2-.4 1.2-1l1.2-8.8c.3-.6.1-1.2-.3-1.6zM12 17.5c-2.8 0-5.1-2.3-5.1-5.1s2.3-5.1 5.1-5.1 5.1 2.3 5.1 5.1-2.3 5.1-5.1 5.1z"></path>
			</svg>
			</button>
			
			
			<div id="check_feedContent" style="display: none;"></div>
			<br>
			<button type="button" id="btn_profileEdit">프로필편집</button>
			<button type="button" id="btn_logout">로그아웃</button>
		</c:if>
	</div>
	<br>
	<hr>
	<br>
	
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
			
			$('#feedPicture').click(function(){
				$('#upload').click();
			});
			$('#upload').change(function(){
				console.log(this.files[0]);
				var files = this.files;
				var preview = $('#preview');
				  
				var file = files[0];
				var srcURL = URL.createObjectURL(file);
				
				var img = $('<img>').attr('src', srcURL).css({ width: '60%', height: '60%' });
				
				if (preview.children().length >= 1) {
				    alert('사진은 최대 한 개까지만 가능합니다.');
				    return;
				  }
				
				preview.append(img);
				
				
				
			})
			
			$('#preview').prop('contentEditable', false);
			$('#preview').css('pointer-events', 'none');
			
			$('#btn_add').click(function() {
				var feedId = $('#feedId').val();
				const userId = document.getElementById("userId").textContent;
				var feedContent = $('#feedContent').text();
				
				console.log(feedContent);
	
				var formData = new FormData();
				var feedPicture = $('#upload')[0].files[0];

				console.log(formData);

				
				console.log('파일 : ' + feedPicture);
				  
				formData.append('feedId', feedId);
				formData.append('userId', userId);
				formData.append('feedContent', feedContent);
				formData.append('feedPicture', feedPicture);

				var list = '';
				if(feedContent == '') {
					list += '<i style="font-size: 14px">피드를 입력해주세요.</i>'
					$('#check_feedContent').html(list);
					$('#check_feedContent').show();
					return;
				}
				
				if(userId != '' || feedPicture == '') {
					$.ajax({
						type : 'POST',
						method : 'POST',
						cache : false,
						url : '/ex06/feeds',
						data : formData,
						contentType : false,
				        processData : false,  
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
				}
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
									
									if(this.feedPhoto != 'null') {
										var imageUrl = '<a href="../feed/detail?feedId=' + this.feedId + '"><img src="display?fileName=' + this.feedPhoto + '" alt="img"/></a>';
										console.log('photo : ' + this.feedPhoto);
										console.log('tag : ' + imageUrl);
									} else if(this.feedPhoto == 'null') {
										var imageUrl = '';
									}
									
									list += '<br>'
									+ '<div class="div_post">'
									+ '<div class="post_item">'
									+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
									+ '<p>' + '<img width="100px" height="100px" src="display?fileName=' + this.userProfile + '" />' + '</p>'
									+ '<p>' + '<b>@' + this.userId + "(" + this.userNickname + ")" + '</b>' + '</p>'
									+ feedDate
									+ '&nbsp;&nbsp;'
									+'<p class="feedContent">' + '<a href="../feed/detail?feedId=' + this.feedId + '">' + this.feedContent +'</a>' +'</p>'
									+ imageUrl
									+ '<hr>'
									
									+ '<div class="like_item">'
									+ '좋아요' 
									+ '<input type="hidden" id="likeCount" value="${feedvo.likeCount }">' + this.likeCount + '개'
									+ '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="none" stroke="#000000" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" class="btn_like">'
									+ '<path d="M20.84,4.32a5.5,5.5,0,0,0-7.78,0L12,5.46l-1.06-1.14a5.5,5.5,0,0,0-7.78,7.78L12,21.46l8.84-8.84a5.5,5.5,0,0,0,0-7.78Z"></path>'
									+ '</svg>'
									
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