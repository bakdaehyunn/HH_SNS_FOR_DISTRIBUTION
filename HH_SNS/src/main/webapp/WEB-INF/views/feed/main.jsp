<%@page import="edu.spring.ex06.domain.UserInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

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
	word-break:break-all;
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

.search_user {
	display: block;
	flex-wrap: wrap;
	align-items: center;
	justify-content: space-between;
	width: 50%;
	margin: 0 auto;
	margin-bottom: 10px;
	margin-top: 10px;
	padding: 10px;
	background-color: #f7f7f7;
	border: 1px solid #ddd;
	height: auto;
	position: relative; /* 추가 */
	text-align: center;
}

#txt_user {
  	margin-bottom: 10px;
  	text-align: center;
}

#txt_userPreofile, #txt_userId, #txt_userNickname {
	display: block;
	margin-top: 10px;
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

.img_photo {
	max-width: 250px;
	max-height: 250px;
}


</style>
<meta charset="UTF-8">
<title>H&H</title>
</head>
<body >

	
	<!-- 
	▼ 등록버튼은 절 대 form안에 넣지말기 ㅎ-ㅎ 
	-->
	
	<h1><a href="../feed/main">H&H</a></h1> <br>
	
		<div class="input_feed" style=" border: 1px solid black;">
		<c:if test="${empty userId}">
		<p id="userProfile"><img width="100px" height="100px" src="display?fileName=default.png" />　　로그인시 이용 가능합니다.</p>
		<p id="userId"><a href="../feed/mylist?userId=${userinfovo.userId }"><b>${userId}</b></a></p>
		</c:if>
		<c:if test="${not empty userId and userId eq userinfovo.userId}">
		<p><a id="notiT" href="../user/noti">알림</a></p>
		<p id="userProfile"><a href="../user/profileEdit"><img width="100px" height="100px" src="display?fileName=${userinfovo.userProfile}" /></a></p>
		<p id="userId"><a href="../feed/mylist?userId=${userinfovo.userId }"><b>${userId}</b></a></p>
		</c:if>
		<p id="userNickname">${userinfovo.userNickname }</p>
		<c:if test="${empty userId }">
		<input type="submit" id="btn_login" value="로그인">
		<a href="../user/signup">회원가입</a>
		</c:if>
		<c:if test="${not empty userId }">
		<div >
		<div id="feedContent" style=" border: 1px solid black;"contentEditable='true'></div>
		
		<input style=" width: auto; height: 30px;" type="submit" id="btn_add" value="등록">
		<br>
		<div id ="feedTagList" style="position: absolute; background-color: white; display: none;   width : 700px;  border: 1px solid #ccc;"></div>
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
	
	<!-- ▼ 이건 RESTController랑 관계 X 그냥 여기서 보여줄 태그일 뿐임 ㅎ-ㅎ -->
	<div style="text-align: center;">
		<div id="feeds">
		</div>
	</div>

	<!--  BoardController -> registerPOST()에서 보낸 데이터 저장
	<input type="hidden" id="insertAlert" value="${insert_result }">
	 -->

	<script type="text/javascript">
		// - css 선택자 :
		//	'p' : 태그(요소)
		//	'#p1' : 아이디
		//	'.c1' : 클래스

		$(document).ready(function() {
			
			getAllMain();
			//likecheck(feedId);
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
							$('#notiT').text('알림(New)');
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
			$('#btn_login').click(function(){
				var target = encodeURI('../user/login');
				location = target;
			});
			
			$('#btn_profileEdit').click(function(){
				var target = encodeURI('../user/profileEdit');
				location = target;
			});
			
			$('#btn_logout').click(function(){
				location = '../user/logout';
			});
			
			
			$('#feedPicture').click(function(){
				$('#upload').click();
			});
			$('#upload').change(function(){
				console.log(this.files[0]);
				var files = this.files;
				var preview = $('#preview');
				  
				var file = files[0];
				var srcURL = URL.createObjectURL(file);
				
				var img = $('<img>').attr('src', srcURL).css({ width: '40%', height: '40%' });
				
				if (preview.children().length >= 1) {
				    alert('사진은 최대 한 개까지만 가능합니다.');
				    return;
				  }
				
				preview.append(img);
			})
			
			$('#preview').prop('contentEditable', false);
			$('#preview').css('pointer-events', 'none');
			
			$('#feedContent').on('input',function(){
				var feedContent =$(this).text();
				var feedHashtagList = $('#feedHashtagList');
				console.log(feedContent);
				console.log('첫번째 글자 : '+ feedContent.substr(-2));
				if((feedContent.substr(0,1) =='@')||feedContent.substr(-2) == ' @'){
					console.log('aasdsad');
					$('#feedHashtagList').append('asdsad <br>');
				}else{
					$('#feedHashtagList').text('');
				}
			});
			
			// 피드 작성버튼
			$('#btn_add').click(function() {
				const userId = document.getElementById("userId").textContent;
				var feedContent = $('#feedContent').html();
				
				// ▼ 문제점 ----------------------------------------------

				// var form = $('#uploadForm')[0];
				var formData = new FormData();
				var feedPicture = $('#upload')[0].files[0];
				
				console.log(formData);
				
				// 현재 문제점 :
					// 파일 없이 내용(content)로만 작성시 파일 이름 못읽는다고 에러남 ^!^
					// 파일 + 내용 작성 시 form데이터가 안가서 restcontroller에서 null뜸
					// => vo랑 multi 같은 이름이였음 근데 왜 그런진 잘 모름 ㅎㅎ;;
					//	  또, 형이 안맞았음..... 오ㅐ..?
				
				console.log('파일 : ' + feedPicture);
				  
				formData.append('userId', userId);
				formData.append('feedContent', feedContent);
				formData.append('feedPicture', feedPicture);
				console.log('유저 아이디 : ' + userId);
				console.log('피드 내용 : ' + feedContent);
				
				// ---------------------------------------------------

				var list = '';
				if(feedPicture == undefined && feedContent == '') {
					console.log('둘 다 없음');
					list += '<i style="font-size: 14px">피드를 입력해주세요.</i>'
					$('#check_feedContent').html(list);
					$('#check_feedContent').show();
					return;
				}
				
				
				if(userId != '' ) {
					$.ajax({
						type : 'POST',
						method : 'POST',
						cache : false,
						url : '../feeds',
						data : formData,
						contentType : false,
				        processData : false,  
						success : function(result) {
							console.log(result);
							if (result == 1) {
								console.log('★ 피드작성 완료');
			                    getAllMain();
			                    $('#feedContent').html('');
			                    $('#preview').html('');
			                    $('#upload').val('');
							} else {
								console.log('★ 피드작성 실패');
							}
						}
					});// end ajax()
				}
			});// end btn_add.click();
			
					// - css 선택자 :
					//	'p' : 태그(요소)
					//	'#p1' : 아이디
					//	'.c1' : 클래스

					function getAllMain() {
						const userId = document.getElementById("userId").textContent;
						console.log(userId);

						var url = '../feeds/all';
							$.getJSON(
								url,
								function(data) {
									console.log(data);
									var list = '';
										$(data).each(function() {
											console.log(this);

											var feedDate = new Date(this.feedDate);
											var yyyy = feedDate.getFullYear();
											var mm = String(feedDate.getMonth() + 1).padStart(2, '0'); // 0부터 시작하므로 +1
											var dd = String(feedDate.getDate()).padStart(2, '0');
											var feedDate = yyyy + '년 ' + mm + '월 ' + dd + '일';
											
											if(this.feedPhoto != 'null') {
												var imageUrl = '<a href="../feed/detail?feedId=' + this.feedId + '"><img class="img_photo" src="display?fileName=' + this.feedPhoto + '" alt="img"/></a>';
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
											// 회원이어야지만 작성은 가능하나
											// 회원이 아니여도 피드를 볼 수 있고
											// 피드에 있는 링크를 통해 그 회원의 개인 피드(=list), 회원의 전체 피드(=main / detail) 볼 수 있다.
											console.log('★');
											console.log(userId); // 접속한 회원
											console.log(this.userId); // 작성 된 회원
											
											list += '<div class="div_post" >'
											+ '<div class="post_item" style=" border: 1px solid black;">'
											+ '<div style="cursor: pointer;" class="post_tag clickable" data-feedId="' + this.feedId + '">'
											+ '<input type="hidden" id="feedId" value="' + this.feedId + '">'
											+ '<p>' + '<a href="../feed/mylist?userId=' + this.userId + '">' + '<img width="100px" height="100px" src="display?fileName=' + this.userProfile + '" />' + '</a>' +'</p>'
											+ '<p id="userId">' + '<a href="../feed/mylist?userId=' + this.userId + '">' + '<b>@' + this.userId + "(" + this.userNickname + ")" + '</b>' + '</a>' +'</p>'
											+ feedDate
											+ '&nbsp;&nbsp;'
											+ '<br>'
											+ feedContent
											+ imageUrl
											+ '</div>'
											+ '<hr>'
											
											+ '<div class="like_item">'
											+ '좋아요' 
											+ this.likeCount + '개' 
											
											+ '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="none" stroke="#000000" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" class="btn_like">'
											+ '<path d="M20.84,4.32a5.5,5.5,0,0,0-7.78,0L12,5.46l-1.06-1.14a5.5,5.5,0,0,0-7.78,7.78L12,21.46l8.84-8.84a5.5,5.5,0,0,0,0-7.78Z"></path>'
											+ '</svg>'
											+' 댓글' + this.replyCount + '개'
											+ '</div>'
											
											+ '</div>'
											+ '</div>';
											
											var feedId = this.feedId;
											//likecheck(feedId);
							});// end data.funchion;
							$('#feeds').html(list);
						}//end function(data);
					);// end getJSON();
				}// end getAllMain();
				
				$(document).on('click', '.post_tag.clickable', function() {
				    var feedId = $(this).data('feedid');
				    detailClick(feedId);
				});
				
				function detailClick(feedId) {
				    var url = '../feed/detail?feedId=' + feedId;
				    window.location.href = url;
				}
				
				// ************** 태그 관련 ***************
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
				
				$(document).on('mousedown', '.tag_item', function(){
					
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
				
				 /* function likecheck(feedId) {
				        console.log('------------------');
				        const userId = document.getElementById("userId").textContent;
				        var btn_like = $('.btn_like');
				        console.log(btn_like);

				        console.log('유저 아이디 : ' + userId + ', 피드 번호 : ' + feedId);

				        var url = '../likes/checkClass/' + userId + '/' + feedId;
						$.getJSON(
							url,
							function(data) {
								console.log(data);
								var isLike = false; // 좋아요 상태 변수 추가
								for (var i = 0; i < data.length; i++) {
									var item = data[i];
									if (userId == item.userId && feedId == item.feedId) {
										isLike = true; // 좋아요 상태 갱신
										likeId = item.likeId; // likeId 새로운걸로 다시 가져옴 ㅎㅎ
						                break; // 반복문 종료
									}
								}

								if (isLike) {
									btn_like.addClass('liked');
									console.log('클래스 추가 완료');
									return;
								} else {
									btn_like.removeClass('liked');
									console.log('클래스 삭제 완료');
									return;
								}
							}// function
						);// end getJSON
				    }// end likecheck() */
				
				
			}); // end ready();
	</script>


</body>
</html>