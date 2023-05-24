<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>Profile Edit</title>
</head>
<body>
<h1>프로필 편집</h1>
	<br>
	
	<c:forEach var="vo" items="${list }">
		${vo.userId }
		<a href='detail?boardId=${vo.boardId }'>${vo.boardTitle }</a>
		${vo.memberId }
		<br>	
	</c:forEach>
	<script type="text/javascript">
	$(document).ready(function(){
		
	});
	
	</script>
</body>
</html>