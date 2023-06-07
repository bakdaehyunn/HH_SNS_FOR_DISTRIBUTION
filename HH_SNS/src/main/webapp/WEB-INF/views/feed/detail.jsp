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
	display: flex;
	justify-content: flex-start;
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
.reply_item {
	display: flex;
	margin-right: 10px;
	clear: left;
}

.comment_item {
	display: flex;
	clear: left;
	margin: 20px;
}

.comment_item hr {
	flex-grow: 1;
	border: none;
	border-top: 1px solid #000;
	margin: auto 10px;
}

.btn_comment {
	border: none;
	color: #6164c9;
	cursor: pointer;
	display: flex;
	justify-content: flex-start;
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
					좋아요 <input type="hidden" id="likeCount" value="${feedvo.likeCount }"><p>${feedvo.likeCount }개</p><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24" fill="none" 
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
	<br>
	
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
	
	<br>
	<hr>
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
			
			var feedId = $('#feedId').val();
			var feedContent = $('#feedContent').text();
			const userId = document.getElementById("userId").textContent;
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
			
			var feedId = $('#feedId').val();

			$.ajax({
				type : 'DELETE', 
				url : '../feeds/' + feedId, 
				headers : {
					'Content-Type' : 'application/json'
				},
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
			var userProfile = $('#userProfile').val();// 유저 프로필 사진
			var feedUserId = "<c:out value='${feedvo.userId }' />";
			var obj = {
					'feedId' : feedId,
					'userId' : userId,
					'userNickname' : userNickname,
					'replyContent' : replyContent,
					'userProfile' : userProfile,
					'feedUserId'  : feedUserId
			}
			console.log(obj);
			
			// $.ajax로 송수신
			$.ajax({
				type : 'POST', 
				url : '../replies', 
				headers : {
					'Content-Type' : 'application/json'
				}, 
				data : JSON.stringify(obj),// JSON으로 변환
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
						var yyyy = replyDateCreated.getFullYear();
						var mm = String(replyDateCreated.getMonth() + 1).padStart(2, '0'); // 0부터 시작하므로 +1
						var dd = String(replyDateCreated.getDate()).padStart(2, '0');
						var replyDateCreated = yyyy + '년 ' + mm + '월 ' + dd + '일';
						
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
							+ '<input type="text" ' + readonly + ' id="replyContent" value="'+ this.replyContent +'"><br>'
							+ '&nbsp;&nbsp;'
							+ replyDateCreated
							+ '&nbsp;&nbsp;'
							+ '<button class="btn_update" ' + disabled + '>수정</button>'
							+ '<button class="btn_delete" ' + disabled + '>삭제</button>'
							+ '<br>'
							+ '<button class="btn_comment"><a>답글</a></button>'
							+ '<br>'
							+ '<div type="hidden" class="comments"></div>'
							+ '</pre>' 
							+ '<br>'
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
	//--------------------------대댓글 파트-------------------------------------
	
	$('#replies').on('click', '.reply_item .btn_comment', function() {
		console.log(this);
		
		var comments = $(this).nextAll('.comments');
		
		var replyId = $(this).prevAll('#replyId').val();
		const userId = $("#userId").val();
		var userNickname = $('#userNickname').val();
		var userProfile = $('#userProfile').val();
		
		console.log('유저 아이디 : ' + userId + ', 댓글 번호 : ' + replyId);
		
	// 대댓글 작성 하는게 만들어져야한다. = (#replies)comments
		var list = '';
		if(userId != '') {
			list += '<div class="comment_item">'
				+ '<input style="margin-right: 30px;" id="commentContent">'
				+ '<input style="height: 30px;" type="submit" class="btn_add_comment" value="등록">'
				+ '<div id="check_comment" style="display: none;"></div>'
				+ '<br>'
				+ '<hr>'
				+ '<br>'
				+ '<div class="commentList"></div>'
				+ '</div>';
		} else {
			list += '<div class="comment_item">'
					+ '<input type="hidden" id="commentId" value="1">'
					+ '<input type="hidden" id="replyId" value="' + replyId + '">'
					+ '<br>'
					+ '<div><a href="../feed/mylist?userId=' + userId + '">' + '<img style="margin-right: 10px;" width="80px" height="80px" src="display?fileName=' + userProfile + '" /></a>'
					+ '<br>'
					+ '<a href="../feed/mylist?userId=' + userId + '">' + '<b>@'+ userId +"(" + userNickname + ")" + '</b></a>'
					+ '</div>'
					+ '&nbsp;&nbsp;'
					+ '<input style="margin-right: 30px;" id="commentContent">'
					+ '<input style="height: 30px;" type="submit" class="btn_add_comment" value="등록">'
					+ '<div id="check_comment" style="display: none;"></div>'
					+ '<br>'
					+ '<hr>'
					+ '<br>'
					+ '<div class="commentList"></div>'
					+ '</div>';
		}
				
		var commentsType = $('.comments').attr('type');
		console.log(commentsType);
		
	
		//console.log('멍 : ' + commentList.val());
		
	// '답글'을 눌렀을 때 펼쳐져야한다.
	
		if(commentsType == 'hidden') {
			comments.html(list).show();
			$('.commentList').addClass('asd');
			$('.comments').attr('type', 'visible');
			//$('.asd').text('뜬다');
			
			 getAllComment(replyId);
			console.log('펼친다.');
		} else {
			comments.hide();
			console.log('접는다.');
			$('.commentList').removeClass('asd');
			$('.comments').attr('type', 'hidden');
		}
	});// end on.click
	
	
	
	function getAllComment(replyId) {
	    console.log('★ : ' + replyId);
	    var commentList = '';
	// 대댓글이 작성 된 댓글의 replyId값과 같은 replyId의 값을 가진 대댓글의 리스트가 보여야한다. = commentList
	    var url = '../comments/all/' + replyId;
	    $.getJSON(
				url,
				function(data) {
					console.log(data);
					
					$(data).each(function() {
						console.log(this);
						
	// '답글'을 눌렀을 때 다른 '답글'을 눌러도 해당하는 replyId값과 일치하는 데이터만 보여야한다.
						commentList += '<div class="commentlist_item">'
							+ '<input type="hidden" id="replyId" value="' + this.replyId + '">'
							+ '<br>'
							+ '<div><a href="../feed/mylist?userId=' + this.userId + '">' 
							+ '<img style="margin-right: 10px;" width="80px" height="80px" src="display?fileName=' + this.userProfile + '" /></a>'
							+ '<a href="../feed/mylist?userId=' + this.userId + '">' + '<b>@'+ this.userId +"(" + this.userNickname + ")" + '</b></a>'
							+ '</div>'
							+ '&nbsp;&nbsp;'
							+ '<div id="commentContent" contentEditable="true">' + this.commentContent + '</div>'
							+ '</div>';
						//	 s="나는 성공한다.";
							//var asd=$('.commentList .asd');
							//$('.commentList .asd').text('asd');
						
				});// end data.funchion;
					$('.asd').html(commentList);
					//$('.asd').show();
					$('.commentList').removeClass('asd');
			}//end function(data);
			
		);// end getJSON();
		
	   
	}

	
	
	$(document).on('click', '.btn_add_comment', function() {
	    var btn = $(this);
	    console.log(btn);
	    
		var replyId = $(this).prevAll('#replyId').val();
		const userId = $("#userId").val();
		var userProfile = $('#userProfile').val();;
		var userNickname = $('#userNickname').val();
		var commentContent = $(this).prevAll('#commentContent').val();
		
		console.log('댓글 번호 : ' + replyId + ', 유저 아이디 : ' + userId + ', 대댓글 내용 : ' + commentContent + ', 유저 닉네임 : ' + userNickname + ', 유저 프로필 : ' + userProfile);
		
		var list = '';
		if(commentContent == '') {
			list += '<i style="font-size: 14px">댓글을 입력해주세요.</i>'
			$('#check_comment').html(list);
			$('#check_comment').show();
			return;
		}
		
		if(userId == null) {
			alert('로그인을 해주세요');
			return;
		}
	    
	    var obj = {
			'replyId' : replyId,
			'userId' : userId,
			'userNickname' : userNickname,
			'userProfile' : userProfile,
			'commentContent' : commentContent
		}
	    
	    var commentList = $(this).nextAll('.commentList');
		commentList.addClass('asd');
		console.log(commentList.hasClass('asd'));
		if (commentList.hasClass('asd')) {
		    console.log('된다');
		} else {
		    console.log('안된다');
		    return;
		}
		
		$.ajax({
			type : 'POST',
			url : '../comments',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : JSON.stringify(obj),
			success : function(result) {
				console.log(result);
				if (result == 1) {
					console.log('★ 대댓글 등록 성공');
					// 작성 = btn_add_comment 을 눌렀을 때 getAllComment에서 $('.commentList').html로 바로 출력해야한다. 
					commentList.addClass('asd');
					$('.asd').text('된다');
					getAllComment(replyId);
					
				} else {
					console.log('★ 대댓글 등록 실패');
				}
			}
		});//end ajax
	});// btn_add_comment.click
		
	//--------------------------좋아요 파트-------------------------------------
	var likeCnt = parseInt($('#likeCount').val());
	console.log('좋아요 : ' + likeCnt + '개');
	likecheck();
	
	function likecheck() {
		var likeId = $('#likeId').val();
		const userId = $('#userId').val();
		var feedId = $('#feedId').val();
		
		console.log('likecheck : ' + likeId + ', ' + userId + ', ' + feedId);
		
		var obj = {
				'likeId' : likeId,
				'userId' : userId,
				'feedId' : feedId
			}
		
		//if(userId == null) {
			//alert('로그인을 해주세요');
			//return;
		//}
		
		$.ajax({
			type : 'GET',
			url : '../likes/check',
			data : obj,
			success : function(result) {
				if(result == 1) {
					$('.btn_like').addClass('liked');
				} else {
					$('.btn_like').removeClass('liked');
				}
			}
		});//end ajax
		
		
	}// end likecheck
	
    // 좋아요 버튼 클릭 이벤트 핸들러
    $(document).on('click', '.btn_like', function() {
	    var likeId = $('#likeId').val();
	    const userId = $('#userId').val();
	    var feedId = $('#feedId').val();
	    
	  	if(userId == null) {
			alert('로그인을 해주세요');
			return;
		}
	    var feedUserId = "<c:out value='${feedvo.userId }' />";
		$btnLike = $(this);
	    
	    var obj = {
			'userId' : userId,
			'feedId' : feedId,
			'feedUserId'  :feedUserId
			
		}
		console.log(obj)
		
		if(userId == null) {
			alert('로그인을 해주세요');
			return;
		}
	    if(!$(this).hasClass('liked')){
			$.ajax({
					type : 'POST',
					url : '../likes',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj),
					success : function(result) {
						console.log(result);
						if (result == 1) {
							console.log($btnLike);
							console.log('★ 좋아요 등록 성공');
							$btnLike.addClass('liked');
							likeCnt += 1; // 좋아요 수 업데이트
				            $('#likeCount').val(likeCnt); // 엘리먼트 값 업데이트
				            $('.like_item p').text(likeCnt + '개'); // 텍스트 업데이트
						} else {
							console.log('★ 좋아요 등록 실패');
						}
					}
				});//end ajax
	    } else {
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
		 				$btnLike.removeClass('liked');
		 				likeCnt -= 1; // 좋아요 수 감소
		 				$('#likeCount').val(likeCnt); // 엘리먼트 값 업데이트
		 				$('.like_item p').text(likeCnt + '개'); // 텍스트 업데이트
		 			} else{
		 				console.log('★ 좋아요 삭제 실패');
		 			}
	 			}
	 		});// end ajax()
	    }
	    
	    });// end click not liked()
	
	}); // end ready();
	
	
	</script>
		
</body>
</html>