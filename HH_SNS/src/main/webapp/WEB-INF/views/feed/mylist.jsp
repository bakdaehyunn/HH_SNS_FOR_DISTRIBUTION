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

.btn_like.liked {
  fill: #e74c3c;
}


#click_txt {
	display: flex;
	justify-content: center;
	align-items: center;
}

.btn_feed {
	text-decoration: none; 
	color: #8c8c8c; 
	font-weight: bold; 
	font-size: 20px;
	background-color: transparent;
	border: none;
	cursor: pointer;
}

.btn_feed.active {
	text-decoration: none; 
	color:	#989bed;
	font-weight: bold; 
	font-size: 20px;
	background-color: transparent;
	border: none;
	cursor: pointer;
}

 .spacing {
    margin: 0 120px; /* 원하는 간격 값을 지정합니다. */
  }

.btn_heart {
	text-decoration: none; 
	color: #8c8c8c; 
	font-weight: bold; 
	font-size: 20px;
	background-color: transparent;
	border: none;
	cursor: pointer;
}

.btn_heart.active {
	text-decoration: none; 
	color:	#989bed;
	font-weight: bold; 
	font-size: 20px;
	background-color: transparent;
	border: none;
	cursor: pointer;
}

</style>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>회원 상세 페이지</title>
</head>
<body>
	<!-- <input type="hidden" id="feedId" value="1"> -->

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
	
	<div></div>
		<c:if test="${not empty userId and userId eq userinfovo.userId}">
		<p><a id="notiT" href="../user/noti">알람</a></p>
		</c:if>
		<!-- 세션아이디값 X -->
		<c:if test="${empty userId}">
		<p id="userProfile"><img width="100px" height="100px" src="display?fileName=${userinfovo.userProfile}" /></p>
		</c:if>
		<!-- 세션아이디값 O / 그리고 세션아이디 != 유저 아이디 -->
		<c:if test="${not empty userId  }">
		<p id="userProfile"><img width="100px" height="100px" src="display?fileName=${userinfovo.userProfile}" /></p>
		</c:if>
		<a href="../user/followerlist?userId=${userinfovo.userId}" id="followerCnt"> 팔로워 : ${followerCnt}</a>
		<a href="../user/followinglist?userId=${userinfovo.userId}" > 팔로잉 : ${followingCnt}</a>
		<input type="hidden" id="followerCntValue" value="${followerCnt }">
		<c:if test="${empty userId or userinfovo.userId ne userId}">
		<button class="followButton" id="btn_follow">팔로우 하기</button>
		</c:if>

		<p id="userId"><b>${userinfovo.userId}</b></p>
		<p id="userNickname">${userinfovo.userNickname }</p>
		
		<c:if test="${empty userId and userId ne userinfovo.userId}">
			<br>
		</c:if>
		<c:if test="${not empty userId and userId eq userinfovo.userId}">
			<div style="display: flex;">
			<div id="feedContent" contentEditable='true'></div>
			<input style=" width: auto; height: 30px;" type="submit" id="btn_add" value="등록">
			</div>
			<div id ="feedTagList" style="position: absolute; background-color: white; display: none;  width : 700px; border: 1px solid #ccc;"></div>
			<div id="check_feedContent" style="display: none;"></div>
			
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
			
			<br>
			<button type="button" id="btn_profileEdit">프로필편집</button>
			<button type="button" id="btn_logout">로그아웃</button>
		</c:if>
		
	</div>
	<br>
	<hr>
	<br>
	
	<input type="hidden" id="likeId" value="${likevo.likeId }">
	<input type="hidden" id="feedId" value="${feedvo.feedId }">
	
	<div id="click_txt">
		<a class="btn_feed active"><span>피드</span></a>
		<span class="spacing"></span>
		<a class="btn_heart"><span>마음</span></a>
	</div>
	
	<br>
	<hr>
	<br>
	
	<div style="text-align: center;">
		<div id="feeds"></div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			setInterval(checkNoti,1000);
			function checkNoti(){
				$.ajax({
					type: 'GET',
					url : '../users/notiCheck',
					headers : {
						'content-Type' : 'application/json'
					},
					success: function(data){
						console.log(data);
						if(data >= 1){
							$('#notiT').text('알람(New)');
							console.log("성공");
						}else {
							$('#notiT').text('알람');
							console.log("다시");
						}
					}//success

				});// ajax()
			}// checkNoTi()
			$('#notiT').mousedown(function(){
				$.ajax({
					type: 'PUT',
					url : '../users/notiRead',
					headers : {
						'content-Type' : 'application/json'
					},
					success: function(data){
						console.log(data);
						if(data >= 1){
							console.log("읽음");
						}
					}//success

				});// ajax()
			})
			var followerCnt =parseInt($('#followerCntValue').val());
			console.log('followercnt ' + followerCnt);
			followcheck();
			
			if ($('.btn_feed').hasClass('active')) {
				getAllList();
			} else if ($('.btn_heart').hasClass('active')) {
			       getAllHeart();
			}
			
			$(document).on('click', '.btn_feed', function() {
				// 피드를 누르면
				// 피드가 파랑색(active) 마음이 회색 
				if (!$('.btn_feed').hasClass('active')) {
					$('.btn_feed').addClass('active');
				}
			    $('.btn_heart').not(this).removeClass('active');
			    
			    getAllList();
			})// end click.btn_feed
			
			$(document).on('click', '.btn_heart', function() {
				// 마음을 누르면
				// 피드가 회색 마음이 파랑색(active)
				
				$('.btn_feed').not(this).removeClass('active');
				$('.btn_heart').addClass('active');
				
				getAllHeart();
				
			})// end click.btn_heart
			
			function followcheck(){
				console.log('followcheck()');
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
								$('button.followButton').addClass('following');
								$('button.followButton').text('팔로우 중');
								
							}else{
								console.log('팔로우한 계정 아님');
								$('button.followButton').removeClass('following');
								$('button.followButton').text('팔로우 하기');
							}
						}
						
					})
				}
				else{
					console.log('로그인 상태 아니라 팔로우 확인 불가');
				}
				
			}
			$('button.followButton').on('click',function(e){
				e.preventDefault();
				//var feedId = ${userinfovo.userId};
				var userinfoUserId = "<c:out value='${userinfovo.userId }' />";
				var params = "userId=" + userinfoUserId; //****************상대방 유저아이디******************************
				console.log(params);
					
					if(!$(this).hasClass('following')){
						console.log('post');
						$.ajax({
							type : 'POST',
							url : '../users/follow?' + params,
							headers : {
								'content-Type' : 'application/json'
							},
							//data : JSON.stringify(obj),
							//data : userinfoUserId,
							beforeSend : function(xmlHttpRequest){
								xmlHttpRequest.setRequestHeader("AJAX", "true");
							},
							success: function(result){
								console.log(result);
								if(result == 1){
									alert("팔로우 완료");
									followcheck();
									followerCnt = followerCnt + 1;
									$('#followerCnt').text("팔로워 : "+ followerCnt );
								}
							},
							error: function(e){
								if(e.status==400){
									var target = encodeURI('/ex06/user/login');
									location = target;
									console.log
									alert("로그인이 필요합니다.");
								}
							}
						})
					}else {
						console.log('delete');
						$.ajax({
							type : 'DELETE',
							url : '../users/' + userinfoUserId,
							headers : {
								'content-Type' : 'application/json'
							},
							success: function(result){
								console.log(result);
								if(result == 1){
									alert("팔로우 취소 완료");
									followcheck();
									followerCnt = followerCnt -1;
									$('#followerCnt').text("팔로워 : "+ followerCnt);
									
								}
							}
						})
						
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
				// attr = img 태그의 src속성의 값 가져옴
				
				if (preview.children().length >= 1) {
					// preview의 직계 자식
				    alert('사진은 최대 한 개까지만 가능합니다.');
				    return;
				  }
				
				preview.append(img);
	
			})
			
			$('#preview').prop('contentEditable', false);
			// prop = 선택한 요소의 프로퍼티의 값을 가져와서 지정해준다.
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
				if(feedPicture == undefined && feedContent == '') {
					console.log('둘 다 없음');
					list += '<i style="font-size: 14px">피드를 입력해주세요.</i>'
					$('#check_feedContent').html(list);
					$('#check_feedContent').show();
					return;
				}
				
				if(userId != '') {
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
								getAllList();
							} else {
								console.log('★ 피드작성 실패');
							}
						}
					});// end ajax()
				}
			});// end btn_add.click();
			
			
			function getAllList() {
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
									
									if(this.feedContent != null) {
										var feedContent = '<p class="feedContent">' + this.feedContent + '</p>';
									} else {
										console.log('작성된 문자 없음');
										var feedContent = '';
									}
									
									list += '<br>'
									+ '<div class="div_post">'
									+ '<div class="post_item">'
									+ '<div style="cursor: pointer;" class="post_tag clickable" data-feedId="' + this.feedId + '">'
									+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
									+ '<p>' + '<img width="100px" height="100px" src="display?fileName=' + this.userProfile + '" />' + '</p>'
									+ '<p>' + '<b>@' + this.userId + "(" + this.userNickname + ")" + '</b>' + '</p>'
									+ feedDate
									+ '&nbsp;&nbsp;'
									+ '<br>'
									+ feedContent
									+ imageUrl
									+ '</div>'
									+ '<hr>'
									
									+ '<div class="like_item">'
									+ '좋아요' 
									+ '<input type="hidden" id="likeCount" value="${feedvo.likeCount }">' + this.likeCount + '개'
									+ '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="none" stroke="#000000" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" class="btn_like">'
									+ '<path d="M20.84,4.32a5.5,5.5,0,0,0-7.78,0L12,5.46l-1.06-1.14a5.5,5.5,0,0,0-7.78,7.78L12,21.46l8.84-8.84a5.5,5.5,0,0,0,0-7.78Z"></path>'
									+ '</svg>'
									+' 댓글' + this.replyCount + '개'
									+ '</div>'
									
									+ '</div>'
									+ '</div>';
								
									var feedId = this.feedId;
									
									$.ajax({
										type : 'GET',
										url : '../likes/check/' + userId,
										success : function(data) {
										$(data).each(function() {
										
										});
									}
								});//end ajax
								


							});// end data.function;
						} else {
							console.log('피드가 없음');
							list += '<br>' 
							+ '<p><i>피드를 작성해보세요 !</i></p>';
						}
							
					$('#feeds').html(list);
				}//end function(data);
			);// end getJSON();
		}// end getAllList();
		
		$(document).on('click', '.post_tag.clickable', function() {
		    var feedId = $(this).data('feedid');
		    detailClick(feedId);
		});
		
		function detailClick(feedId) {
		    var url = '../feed/detail?feedId=' + feedId;
		    window.location.href = url;
		}
		
		function getAllHeart() {
			//var likeId = $('#likeId').val();
			const userId = document.getElementById("userId").textContent;
			//var feedId = $('#feedId').val();
			//console.log('좋아요 아이디 : ' + likeId + ", 좋아요 누른 피드 번호 : " + feedId);
			
			var url = '../feeds/mylist/' + userId;
			$.getJSON(
				url,
				function(data) {
					console.log(data);
					var datasize = data.length;
					console.log('피드 개수 : ' + datasize);
					var list = '';
					
					if(data.length > 0) {
						$(data).each(function() {
							var feedDate = new Date(this.feedDate);
							var yyyy = feedDate.getFullYear();
							var mm = String(feedDate.getMonth() + 1).padStart(2, '0');
							var dd = String(feedDate.getDate()).padStart(2, '0');
							var feedDate = yyyy + '년 ' + mm + '월 ' + dd + '일';
							
							if(this.feedPhoto != 'null') {
								var imageUrl = '<a href="../feed/detail?feedId=' + this.feedId + '"><img src="display?fileName=' + this.feedPhoto + '" alt="img"/></a>';
								console.log('photo : ' + this.feedPhoto);
								console.log('tag : ' + imageUrl);
							} else if(this.feedPhoto == 'null') {
								var imageUrl = '';
							}
							
							if(this.feedContent != null) {
								var feedContent = '<p class="feedContent">' + '<a href="../feed/detail?feedId=' + this.feedId + '">' + this.feedContent +'</a>' +'</p>';
							} else {
								console.log('작성된 문자 없음');
								var feedContent = '';
							}
							
							list += '<br>'
								+ '<div class="div_post">'
								+ '<div class="post_item">'
								+ '<div style="cursor: pointer;" class="post_tag clickable" data-feedId="' + this.feedId + '">'
								+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
								+ '<p>' + '<img width="100px" height="100px" src="display?fileName=' + this.userProfile + '" />' + '</p>'
								+ '<p>' + '<b>@' + this.userId + "(" + this.userNickname + ")" + '</b>' + '</p>'
								+ feedDate
								+ '&nbsp;&nbsp;'
								+ feedContent
								+ imageUrl
								+ '</div>'
								+ '<hr>'
								
								+ '<div class="like_item">'
								+ '좋아요' 
								+ '<input type="hidden" id="likeCount" value="${feedvo.likeCount }">' + this.likeCount + '개'
								+ '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="none" stroke="#000000" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" class="btn_like liked">'
								+ '<path d="M20.84,4.32a5.5,5.5,0,0,0-7.78,0L12,5.46l-1.06-1.14a5.5,5.5,0,0,0-7.78,7.78L12,21.46l8.84-8.84a5.5,5.5,0,0,0,0-7.78Z"></path>'
								+ '</svg>'
								+' 댓글' + this.replyCount + '개'
								+ '</div>'
								+ '</div>'
								+ '</div>';
						
						});// end each
					} else {
						console.log('피드가 없음');
						list += '<br>' 
						+ '<p><i>좋아요를 찍어보세요.</i></p>';
					}
						
				$('#feeds').html(list);
				}// end data
			);// end getJSON();
		}// end getAllHeart();
		
		$(document).on('click', '.post_tag.clickable', function() {
		    var feedId = $(this).data('feedid');
		    detailClick(feedId);
		});
		
		function detailClick(feedId) {
		    var url = '../feed/detail?feedId=' + feedId;
		    window.location.href = url;
		}
		
		var onTag=false;
		$('#feedContent').on('input',function(){
			var feedContent =$(this).text();
			var feedTagList = $('#feedTagList');
			console.log(feedContent);
			
			
			console.log('첫번째 글자 : '+ feedContent.length);
			if((feedContent =='@')||feedContent.substr(-2) == ' @'||feedContent.substr(-2) == String.fromCharCode(160)+'@'|| (onTag===true&&!(feedContent.substr(-1).trim().length == 0) )){
				console.log('태그시작');
				var pos = feedContent.lastIndexOf('@');
				console.log('위치: '+(pos));
				onTag=true;
				var followingUserId = feedContent.substr(pos+1);
				if(!followingUserId){
					console.log('아이디값 아직 없음');
					$('#feedTagList').html('');
					$('#feedTagList').hide();
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
						success: function(data){
							var list = '';
							if(data==''){
								$('#feedTagList').html(followingUserId+'에 대한 검색 결과가 없습니다.');
							}else{
								$(data).each(function(){
									console.log(this);
									list += '<div class="tag_item">'
									+'<img id="profileImage" src ="display?fileName='+ this.userProfile+'"alt="img" width="40" height="40" />'
									+'@'+this.userId +'('+this.userNickname+')'
									+'<input type="hidden" class="userId" value="'+this.userId+'">'
									+'</div>'
									+'<hr>';
								})
								
								$('#feedTagList').html(list);
							}
							$('#feedTagList').show();
						}
						
					});// ajax()
				}
				
			}else if (feedContent.substr(-1).trim().length == 0 ||  feedContent.substr(-2) =='@@' || onTag===false ){
				$('#feedTagList').text('');
				console.log('태그아님');
				onTag=false;
				
			} // if else 문 끝
		});// input 이벤트
		
		$(document).on('mousedown', '.tag_item', function(e){
			
			var feedContent =$('#feedContent').html();
			var pos = feedContent.lastIndexOf('@');
			console.log('위치: '+pos);
			var list = feedContent.substr(0,pos);
			
			var userId = $(this).find('.userId').val();
			list  +=  '<a href="../feed/mylist?userId=' + userId + '">' + '@'+userId +'</a>&nbsp;';
			$('#feedContent').html(list);
			$('#feedTagList').text('');
			$('#feedTagList').hide();
			onTag=false;
		});
		$('#feedContent').on('blur',function(){
			$('#feedTagList').hide();
			 
		});
		$('#feedContent').on('focus',function(){
			var feedContent =$(this).text();
			if((feedContent =='@')||feedContent.substr(-2) == ' @'||feedContent.substr(-2) == String.fromCharCode(160)+'@'|| (onTag===true&&!(feedContent.substr(-1).trim().length == 0) )){
				console.log('클릭 시 태그 상황 맞음');
				$('#feedTagList').show();
			}else{
				console.log('클릭 시 태그 상황 아님');
			};
			
		});	
			
			
});// end ready.function();
	</script>
</body>
</html>