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
	background-color: #f7f7f7;
	border: 1px solid #ddd;
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
<title>page</title>
</head>
<body>
	<h1><a href="../feed/main">H&H</a></h1> <br>
	
	<div class="div_post">
		<div class="post_item">
			<input type="hidden" id="feedId" value="${feedvo.feedId}">
		<p><a href="../feed/list?userId=${feedvo.userId}"><img width="100px" height="100px" src="display?fileName=${feedvo.userProfile}" /></a></p>
			<p><a href="../feed/list?userId=${feedvo.userId}"><b>@${feedvo.userId}</b></a></p>
			<p>${feedvo.userNickname }</p>
			<fmt:formatDate value="${feedvo.feedDate}" var="feedDate" pattern="yyyy년 MM월 dd일"/>
			<p>${feedDate }</p>
			<input type="text" id="feedContent" value="${feedvo.feedContent }">
			<c:if test="${empty userId or feedvo.userId ne userId}">
			    <input type="submit" id="btn_update" disabled value="수정">
			    <input type="submit" id="btn_delete" disabled value="삭제">
			</c:if>
			<c:if test="${not empty userId and feedvo.userId eq userId}">
			    <input type="submit" id="btn_update" value="수정">
			    <input type="submit" id="btn_delete" value="삭제">
			</c:if>
		</div>
	</div>
	
	<div style="text-align: center;">
		<div id="feeds"></div>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$('#btn_update').click(function() {
			console.log(this);
			
			var feedId = $(this).prevAll('#feedId').val();
			var feedContent = $(this).prevAll('#feedContent').val();
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
						getAllMain();
					} else {
						console.log('★ 피드수정 실패');
					}
				}
			});// end ajax
			
		});// end feeds.update
		
		// 삭제 버튼을 클릭하면 선택된 댓글 삭제
		$('#btn_delete').click(function() {
			console.log(this);
			
			var feedId = $(this).prevAll('#feedId').val();
			
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
						getAllMain();
						
					} else {
						console.log('★ 피드삭제 실패');
					}
				}
			});//end ajax
		}); // end feeds.delete
	}); // end ready();
	
	</script>
		
</body>
</html>