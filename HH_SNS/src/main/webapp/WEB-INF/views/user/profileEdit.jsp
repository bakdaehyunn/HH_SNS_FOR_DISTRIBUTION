<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Edit</title>
</head>
<body>
<h1>프로필 편집</h1>
	<br>
	<button class="button" onclick="onclick=document.all.files.click()">사진변경</button> <br>
	<form action="profileEdit" method="post" enctype="multipart/form-data">
	<input type="hidden" name="userId" value="${vo.getUserId() }">
	<input type="file" id="files" name="file" style="display:none">
	<input type="hidden" name="userProfile" value="${vo.getUserProfile() }"> 
	<p>닉네임 : <input type="text" name="userNickname" value="${vo.getUserNickname() }"></p>
	<input type="submit" value="프로필 변경">
	</form>
	
</body>
</html>