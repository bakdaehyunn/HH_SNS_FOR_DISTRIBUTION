<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
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
		$(document).ready(function(){
			var result = $('#insertAlert').val();
			if(result == 'signUpSuccess') {
				alert('회원 가입 성공!');
			}else if (result =='logInUnsuccess'){
				alert('로그인 실패!');
			}
		})
		
	</script>
</body>
</html>