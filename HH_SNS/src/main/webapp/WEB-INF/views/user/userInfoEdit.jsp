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
	
	<form action="userInfoEdit" method="post" >
	<input type="text" name="userId" value="${vo.userId }">
	<input type="text" name="userPassword" >
	<input type="text" name="userName" value="${vo.userName }">
	<input type="submit" value="개인정보 변경">
	</form>
	<script type="text/javascript">
	$(document).ready(function(){
		
	});
	
	</script>
</body>
</html>