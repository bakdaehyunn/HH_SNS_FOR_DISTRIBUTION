<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>Profile Edit</title>
</head>
<body>
	<h1>팔로워 리스트</h1>
	<br>
	<br>
	<c:forEach var="vo" items="${list }">

	<div onclick="location.href='../feed/mylist?userId=${vo.userId }';">
	<img id="profileImage" src="display?fileName=${vo.userProfile }" alt="img" width="100" height="100" />
 	@${vo.userId } (${vo.userNickname })
	</div>
		<hr>	
	</c:forEach>
	
</body>
</html>