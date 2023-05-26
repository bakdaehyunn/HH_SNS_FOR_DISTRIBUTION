<%@page import="edu.spring.ex06.domain.LikeInfoVO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.div_post {
	display: block; /* flex에서 block으로 변경 */
	flex-wrap: wrap;
	justify-content: center;
	align-items: center;
	width: 100%;
	margin: 0 auto;
}

.post_item {
	width: 50%;
	margin-bottom: 20px;
	padding: 20px;
	background-color: #ffffff;
	border: 1px solid #ddd;
	margin-bottom: 20px;
}

#feedContent {
	display: flex;
	background-color: #ffffff;  
	min-width: 600px; 
	width: auto; 
	min-height: 80px; 
	height: auto; 
	margin-right: 20px;
	border: none;
}

#btn_update, #btn_delete {
	width: auto; 
	height: 30px;
}

.like_item {
    display: flex;
    align-items: center;
}

.btn_like {
  cursor: pointer;
}

.btn_like.liked {
  fill: #e74c3c; 
}


</style>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>page</title>
</head>
<body>
	<h1><a href="../feed/main">H&H</a></h1> <br>
	
	<%--768
	 String userId = (String) session.getAttribute("userId");
	 --%>
	 
	
	<div class="div_post">
		<div class="post_item">
			<input type="hidden" id="feedId" value="${feedvo.feedId}">
		<p><a href="../feed/mylist?userId=${feedvo.userId}"><img width="100px" height="100px" src="display?fileName=${feedvo.userProfile}" /></a></p>
			<p><a href="../feed/mylist?userId=${feedvo.userId}"><b>@${feedvo.userId}</b></a></p>
			<p>${feedvo.userNickname }</p>
			<fmt:formatDate value="${feedvo.feedDate}" var="feedDate" pattern="yyyy년 MM월 dd일"/>
			<p>${feedDate }</p>
			<div style="display: flex;">
			<div id="feedContent" contentEditable='true'>${feedvo.feedContent}</div>
			<c:if test="${empty userId or feedvo.userId ne userId}">
			    <input type="submit" id="btn_update" disabled value="수정">
			    <input type="submit" id="btn_delete" disabled value="삭제">
			</c:if>
			<c:if test="${not empty userId and feedvo.userId eq userId}">
			    <input type="submit" id="btn_update" value="수정">
			    <input type="submit" id="btn_delete" value="삭제">
			</c:if>
			</div>
			<c:if test="${feedvo.feedPhoto ne 'null'}">
				<br>
				<img id="feedPhoto" style="display:block; width:50%; height: 50%" onclick="window.open(this.src)" src="display?fileName=${feedvo.feedPhoto }" alt="img">			
			</c:if>
			    <hr>
			<input type="hidden" id="likeId" value="${likevo.likeId }">
			<input type="hidden" id="checkuserId" value="${likevo.userId }">
			<input type="hidden" id="checkfeedId" value="${likevo.feedId }">
				<div class="like_item">
					좋아요 <input type="hidden" id="likeCount" value="${feedvo.likeCount }">${feedvo.likeCount }개 <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="none" 
					stroke="#000000" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" class="btn_like">
					<path d="M20.84,4.32a5.5,5.5,0,0,0-7.78,0L12,5.46l-1.06-1.14a5.5,5.5,0,0,0-7.78,7.78L12,21.46l8.84-8.84a5.5,5.5,0,0,0,0-7.78Z"></path>
					</svg>
				</div>
		</div>
	</div>
	
	<div style="text-align: center;">
		<div id="feeds"></div>
	</div>

	
	<hr>
	
	<!-- -----------------------댓글 파트 ------------------------------ -->
	
	<input type="hidden" id="feedId" value="${feedvo.feedId}">
	<div style="text-align: center;">
		<c:if test="${empty userId }">
			<a href="../user/login">로그인 하러 가기</a>
		</c:if>
		<c:if test="${not empty userId }">
			<input type="hidden" id="userId" value="${userinfovo.userId}" >
			<input type="hidden" id="userNickname" value="${userinfovo.userNickname}"> 
			<input type="hidden" id="userProfile"  value="${userinfovo.userProfile }">
			<div><a href="../feed/mylist?userId=${feedvo.userId}"><img width="100px" height="100px" src="display?fileName=${userinfovo.userProfile}" /></a></div>
			<div><a href="../feed/mylist?userId=${userinfovo.userId } "><b>@${userinfovo.userId}(${userinfovo.userNickname})</b></a></div>
			<input type="text" id="replyContent">
		<button id="btn_add">작성</button>
		</c:if>
	</div>
	
	<div style="text-align: center;">
		<div id="replies"></div>
	</div>
	
	<script type="text/javascript"> 
	<!-- ----------------------- 피드 디테일 ------------------------------ -->
	$(document).ready(function() {
		$('#btn_login').click(function(){
			var target = encodeURI('/ex06/user/login');
			location = target;
		});
		$('#btn_update').click(function() {
			console.log(this);
			
			var feedId = $(this).prevAll('#feedId').val();
			var feedContent = $(this).prevAll('#feedContent').val();
			var userId = $(this).prevAll('#userId').val();
			console.log ("아이디 : " + userId);
			console.log("선택된 피드 번호 : " + feedId + ", 피드 내용 : " + feedContent);
			
			$.ajax({
				type : 'PUT', 
				url : '../feeds/' + feedId,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : feedContent, 
				success : function(result) {
					console.log(result);
					if(result == 1) {
						console.log('★ 피드수정 완료');
						alert('수정 완료');
						location = '../feed/main';
						// http://localhost:8080/ex06/mylist?userId=asss
						// http://localhost:8080/ex06/feed/mylist?userId=asss
						getAllMain();
					} else {
						console.log('★ 피드수정 실패');
						alert('수정 실패');
						location = '../feed/main';
					}
				}
			});// end ajax
			
		});// end feeds.update
		
		$('#btn_delete').click(function() {
			console.log(this);
			
			var feedId = $(this).prevAll('#feedId').val();

			var userId = $(this).prevAll('#userId').val();
			console.log('피드 : ' + feedId + ' 유저 : ' + userId);

			$.ajax({
				type : 'DELETE', 
				url : '../feeds/' + feedId, 
				headers : {
					'Content-Type' : 'application/json'
				},
				data : feedId,
				success : function(result) {
					console.log(result);
					if(result == 1) {
						location.replace("../feed/main");
						console.log('★ 피드삭제 완료');
						alert('삭제 완료');
						location = '../feed/main';
						getAllMain();
						
					} else {
						console.log('★ 피드삭제 실패');
						alert('삭제 실패');
						location = '../feed/main';
					}
				}
			});//end ajax
		}); // end feeds.delete
		
		//--------------------------댓글 파트-------------------------------------
		getAllReplies();
		
		$('#btn_add').click(function(){
			var feedId = $('#feedId').val(); // 게시판 번호 데이터
			var userId = $('#userId').val(); // 유저 ID
			var userNickname = $('#userNickname').val();// 유저 닉네임
			var replyContent = $('#replyContent').val(); // 댓글 내용
			var userProfile = $('#userProfile').val();;// 유저 프로필 사진
			var obj = {
					'feedId' : feedId,
					'userId' : userId,
					'userNickname' : userNickname,
					'replyContent' : replyContent,
					'userProfile' : userProfile
			}
			console.log(obj);
			
			// $.ajax로 송수신
			$.ajax({
				type : 'POST', 
				url : '../replies', 
				headers : {
					'Content-Type' : 'application/json'
				}, 
				data : JSON.stringify(obj), // JSON으로 변환
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('댓글 입력 성공');
						getAllReplies();
					}
				}
			});
		}); // end btn_add.click()
		
		// 게시판 댓글 전체 가져오기
		function getAllReplies() {
			var feedId = $('#feedId').val();
			var url = '../replies/all/' + feedId;
			console.log('test');
			$.getJSON(
				url, 		
				function(data) {
					// data : 서버에서 전송받은 list 데이터가 저장되어 있음.
					// getJSON()에서 json 데이터는 
					// javascript object로 자동 parsing됨.
					console.log(data);
					
					//var memberId = $('#memberId').val();
					var userId = $('#userId').val();
					var list = ''; // 댓글 데이터를 HTML에 표현할 문자열 변수
					
					// $(컬렉션).each() : 컬렉션 데이터를 반복문으로 꺼내는 함수
					$(data).each(function(){
						// this : 컬렉션의 각 인덱스 데이터를 의미
						console.log(this);
					
						var replyDateCreated = new Date(this.replyDate);
						var disabled = 'disabled';
						var readonly = 'readonly';
						if(userId == this.userId) { 
							disabled = '';
							readonly = '';
						}
						list += '<div class="reply_item">'
							+ '<pre>'
							+ '<input type="hidden" id="replyId" value="'+ this.replyId +'">'
							+ '<div><a href="../feed/mylist?userId=' + this.userId + '">' + '<img width="100px" height="100px" src="display?fileName=' + this.userProfile + '" /></a></div>'
							+ '<div><a href="../feed/mylist?userId=' + this.userId + '">' + '<b>@'+this.userId +"("+this.userNickname+")"+'</b></a></div>'
							+ '&nbsp;&nbsp;' // 공백
							+ '<div><input type="text" ' + readonly + ' id="replyContent" value="'+ this.replyContent +'"></div>'
							+ '&nbsp;&nbsp;'
							+ replyDateCreated
							+ '&nbsp;&nbsp;'
							+ '<button class="btn_update" ' + disabled + '>수정</button>'
							+ '<button class="btn_delete" ' + disabled + '>삭제</button>'
							+ '</pre>'
							+ '</div>';
					}); // end each()
						
					$('#replies').html(list);
				} // end function()
			); // end getJSON()
		} // end getAllReplies()
		
		// 수정 버튼을 클릭하면 선택된 댓글 수정
		$('#replies').on('click', '.reply_item .btn_update', function(){
			console.log(this);
			
			// 선택된 댓글의 replyId, replyContent 값을 저장
			// prevAll() : 선택된 노드 이전에 있는 모든 형제 노드를 접근
			var replyId = $(this).prevAll('#replyId').val();
			var replyContent = $(this).prevAll('#replyContent').val();
			console.log("선택된 댓글 번호 : " + replyId + ", 댓글 내용 : " + replyContent);
			
			// ajax 요청
			$.ajax({
				type : 'PUT', 
				url : '../replies/' + replyId,
				headers : {'Content-Type' : 'application/json' },
				data : replyContent, 
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('댓글 수정 성공!');
						getAllReplies();
					}
				}
			});
			
		}); // end replies.on()
		
		// 삭제 버튼을 클릭하면 선택된 댓글 삭제
		$('#replies').on('click', '.reply_item .btn_delete', function(){
			console.log(this);
			var feedId = $('#feedId').val();
			var replyId = $(this).prevAll('#replyId').val();
			
			// ajax 요청
			$.ajax({
				type : 'DELETE', 
				url : '../replies/' + replyId,
				headers : {'Content-Type' : 'application/json' },
				data : feedId,
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('댓글 삭제 성공!');
						getAllReplies();
					}
				}
			});
		});
		
	//--------------------------좋아요 파트-------------------------------------
	var heart;
	var likeCnt = parseInt($('#likeCount').val());
	console.log('좋아요 개수 : ' + likeCount + ' 개');
	likecheck();
	
	function likecheck() {
		var likeId = $('#likeId').val();
		const userId = $('#userId').val();
		var feedId = $('#feedId').val();
		
		console.log('likecheck : ' + likeId + ', ' + userId + ', ' + feedId);
    	
		var url = '../likes/all/' + feedId;
		$.getJSON(
			url,
			function(data) {
				console.log('list로 받아온 data : ' + data);
				
				var check = false;
				 $(data).each(function() {
				        console.log('for문 돌린 this : ' + this.feedId);
				        if (feedId == this.feedId && userId == this.userId) {
				        	check = true;
				        	return false; // 반복문 중단
				        }
				      });

				      if(check) {
				        heart = true;
				        console.log('이미 좋아요한 상태');
				        $('.btn_like').addClass('liked');
				      } else {
				        heart = false;
				        console.log('좋아요하지 않은 상태');
				        $('.btn_like').removeClass('liked');
				      }
			}// end function
		);// end getJSON
		
	}// end likecheck
	
    // 좋아요 버튼 클릭 이벤트 핸들러
    $(document).on('click', '.btn_like:not(.liked)', function() {
	    var likeId = $('#likeId').val();
	    const userId = $('#userId').val();
	    var feedId = $('#feedId').val();
	    
	    var checkuser = $('#checkuserId').val();
	    var checkfeed = $('checkfeedId').val();
	    
	    console.log('♥ 유저 : ' + checkuser + ', 피드 : ' + checkfeed);
		
		$btnLike = $(this);
	    
	    var obj = {
			'likeId' : likeId,
			'userId' : userId,
			'feedId' : feedId,
			'likeCount' : likeCount
		}
		console.log(obj)
		
		if(userId == null) {
			alert('로그인을 해주세요');
			return;
		}
	    
		$.ajax({
				type : 'POST',
				url : '../likes',
				headers : {
					'Content-Type' : 'application/json'
				},
				data : JSON.stringify(obj),
				success : function(result) {
					console.log(result);
					if(checkuser != userId && checkfeed != feedId) {
						if (result == 1) {
							console.log('★ 좋아요 등록 성공');
							likecheck();
							heart = $btnLike.addClass('liked');
							likeCnt = likeCnt + 1;
						} else{
							console.log('★ 좋아요 등록 실패');
						}
					}
				}
			});//end ajax
	    
	    });// end click not liked()
    
    $(document).on('click', '.btn_like.liked', function() {
    	console.log('delete');
    	var likeId = $('#likeId').val();
	    const userId = $('#userId').val();
	    var feedId = $('#feedId').val();
    	
    	$btnLike = $(this);
    	
    	var obj = {
    			'likeId' : likeId,
    			'userId' : userId,
    			'feedId' : feedId
    		};
    	
    	console.log(obj);
    	
    	 $.ajax({
    		type : 'DELETE', 
			url : '../likes/' + likeId,
 			headers : {
 				'Content-Type' : 'application/json'
 			},
 			data : JSON.stringify(obj),
 			success : function(result) {
 				console.log(result);
 				console.log(this);
	 			if (result == 1) {
	 				console.log('★ 좋아요 삭제 성공');
	 				likecheck();
	 				heart = $btnLike.removeClass('liked');
	 				likeCnt = likeCnt - 1;
	 			} else{
	 				console.log('★ 좋아요 삭제 실패');
	 			}
 			}
 		});// end ajax()
 		
    	
    }); //end click liked
	
	}); // end ready();
	
	
	</script>
		
</body>
</html>