<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>
<%-- 아이디 비밀번호 이름 닉네임, 생일 이메일 --%>
	<form action="signup" method="post">
		<%-- 아이디 중복 체크(비동기) 및 정규식 --%>
		<p>아이디 : <input type="text" name="userId" placeholder="아이디" required></p>
		<p>패스워드 : <input type="password" name="userPassword" placeholder="패스워드" required></p>
		<p> 이름: <input type="text" name="userName" placeholder="이름" required></p>
		<p>닉네임 : <input type="text" name="userNickname" placeholder="닉네임" required></p>
		<%-- 이메일 인증(비동기) 이메일 @(옵션)  생년월일 생년월일 옵션  --%>
		<p>이메일 : <input type="email" name="userEmail" required></p> 
		<p>생년월일 : <input type="date" name="userBirth"required></p>
		<input type="hidden" name="userProfile" value=""></p>
		<input type="submit" value="가입">
	</form>
</body>
</html>