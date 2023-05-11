<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>게시판 메인 페이지</title>
</head>
<body>
	<h1>게시판 메인</h1>
	<c:if test="${empty userId }"> 
		<button type="button" id="btn_login">로그인</button>
	</c:if>
	<c:if test="${not empty userId }"> 
		 <p><img width="100px" height="100px" src="display?fileName=${userinfovo.getUserProfile() }" /></p>
		<p>@${userId }님 </p>
		<button type="button" id="btn_logout">로그아웃</button>
		<button type="button" id="btn_profileEdit">프로필편집</button>
	</c:if>
	
	<hr>
	<a href="register">새 글 작성</a>
	<a href="test?data1=hello&data2=abc1234def">로그인이 필요한 메뉴</a>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#btn_login').click(function(){
				var target = encodeURI('/ex06/user/login');
				location = target;
			});
			$('#btn_logout').click(function(){
				location = '../user/logout';
			});
			$('#btn_profileEdit').click(function(){
				var target = encodeURI('/ex06/user/profileEdit');
				location = target;
			});
		});
	</script>
</body>
</html>