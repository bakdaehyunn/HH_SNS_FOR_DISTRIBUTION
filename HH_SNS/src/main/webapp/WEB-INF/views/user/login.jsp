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

	<form id="loginForm" action="login" method="POST">
		<input type="text" id="userId" name="userId"><br>
		<input type="password" id="userPassword" name="userPassword">
		<input type="submit" value="로그인">
		<div id="loginGuide" style="display: none ;"></div>
	</form>
	
	<a href="signup">회원가입</a>
	
	<!--  BoardController -> registerPOST()에서 보낸 데이터 저장 -->
	<input type="hidden" id="insertAlert" value="${insert_result }">
	
	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if(result == 'success') {
			alert('회원 가입 성공!');
		}
		$(document).ready(function(){
			var result = $('#insertAlert').val();
			var result = $('#insertAlert').val();
			if(result == 'signUpSuccess') {
				alert('회원 가입 성공!');
			}else if(result == 'logInUnsuccess'){
				$('#loginGuide').text('아이디 또는 비밀번호를 잘못 입력했습니다.');
				$('#loginGuide').show();
			}
			
			$('#loginForm').submit(function(e){
				var userId= $('#userId').val();
				var userPassword = $('#userPassword').val();
				if(userId ==''){
					e.preventDefault();
					$('#userId').focus();
					$('#loginGuide').text('아이디를 입력해주세요.');
					$('#loginGuide').show();
				}else if(userPassword == ''){
					e.preventDefault();
					$('#userPassword').focus();
					$('#loginGuide').text('비밀번호를 입력해주세요.');
					$('#loginGuide').show();
				}
				return true;
			});
		})
		
	</script>
</body>
</html>