<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<%
	  String userId = (String) session.getAttribute("userId");
	
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
	  Date date = new Date(); // 예시로 현재 날짜를 사용
	  String feedDate = dateFormat.format(date);
	
	%>
	
	<div class="div_post">
		<div class="post_item">
			<input type="hidden" id="feedId" value="${feedvo.feedId}">
			<p><a href="../feed/list"><img width="100px" height="100px" src="display?fileName=${feedvo.userProfile}" /></a></p>
			<p><a href="../feed/list"><b>@${feedvo.userId}</b></a></p>
			<p>${feedvo.userNickname }</p>
			<p><%=feedDate%></p>
			<input type="text" id="feedContent" value="${feedvo.feedContent }">
			<c:if test="${empty userId }">
				<button class="btn_update" disabled>수정</button>
				<button class="btn_delete" disabled>삭제</button>
			</c:if>
			<c:if test="${not empty userId }">
				<button class="btn_update" >수정</button>
				<button class="btn_delete" >삭제</button>
			</c:if>
		</div>
	</div>
		
</body>
</html>