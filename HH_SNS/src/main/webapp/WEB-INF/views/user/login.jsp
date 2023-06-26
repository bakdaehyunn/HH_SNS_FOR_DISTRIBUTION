<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
#loginForm {
     width: 400px;
     margin: 0 auto;
     border: 1px solid #ccc;
     padding: 20px;
}

#loginForm input[type="text"],
#loginForm input[type="password"] {
    width: 90%;
    padding: 10px;
    margin-bottom: 10px;
}

#loginForm input[type="submit"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    cursor: pointer;
}

#loginGuide {
    margin-top: 10px;
    text-align: center;
    font-size: 14px;
}
#title{
 text-align: center;
}
</style>
</head>
<body>
<h1><a href="../feed/main">H&H</a></h1> <br>
	<h1 id="title">로그인</h1>

	<form id="loginForm" action="login" method="POST">
		아이디 : <input type="text" id="userId" name="userId"><br>
		비밀번호 : <input type="password" id="userPassword" name="userPassword">
		<input type="submit" value="로그인">
		<div id="loginGuide" style="display: none ;"></div>
	</form>
	
	<a href="signup">회원가입</a>
	
	<!--  BoardController -> registerPOST()에서 보낸 데이터 저장 -->
	<input type="hidden" id="loginAlert" value="${login_result }">
	<input type="hidden" id="deleteAlert" value="${delete_result }">
	<input  type="hidden" id="signupAlert" value="${signup_result }">
	<script type="text/javascript">
		
		
		$(document).ready(function(){
			var deleteResult =$('#deleteAlert').val();
			var loginResult = $('#loginAlert').val();
			var signupResult = $('#signupAlert').val();
			if(signupResult == 'signUpSuccess') { // 회원가입 성공 시
				alert('회원 가입 성공!');
			}
			if(loginResult == 'logInUnsuccess'){ // 로그인 실패 시
				$('#loginGuide').text('아이디 또는 비밀번호를 잘못 입력했습니다.');
				$('#loginGuide').show();
			}
		
			if(deleteResult == 'accDeleteSuccess' ){ //회원 탈퇴 시
				alert('회원 탈퇴가 완료되었습니다.')
			}
			
			$('#loginForm').submit(function(e){ // 로그인 폼 제출 시
				var userId= $('#userId').val();
				var userPassword = $('#userPassword').val();
				if(userId ==''){// 아이디 입력 안했을 때
					e.preventDefault();
					$('#userId').focus();
					$('#loginGuide').text('아이디를 입력해주세요.');
					$('#loginGuide').show();
				}else if(userPassword == ''){// 패스워드 입력 안했을 때
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