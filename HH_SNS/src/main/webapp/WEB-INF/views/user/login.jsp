<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
</head>
<body>
	<h1>로그인</h1>
	<form action="login" method="POST">
		<input type="text" name="userId"><br>
		<input type="password" name="userPassword">
		<input type="submit" value="로그인"></button>
	</form>
	<a href="signup">회원가입</a>
	
	<!--  BoardController -> registerPOST()에서 보낸 데이터 저장 -->
	<input type="hidden" id="insertAlert" value="${insert_result }">
	
	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if(result == 'success') {
			alert('새 글 작성 성공!');
		}
	</script>
</body>
</html>