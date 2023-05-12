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
	<img id="profileImage" src="display?fileName=${vo.userProfile }" alt="img" width="100" height="100" /><br>
	<button class="button"  id="button_out" >사진변경</button> <br>
	<form action="profileEdit" method="post" enctype="multipart/form-data">
	<input type="hidden" name="userId" value="${vo.userId }">
	<input type="file" id="button_in" name="file" style="display:none"; accept=".gif, .jpg, .png">
	<input type="hidden" name="userProfile" value="${vo.userProfile }"> 
	<p>닉네임 : <input type="text" name="userNickname" value="${vo.userNickname }"></p>
	<input type="submit" value="프로필 변경">
	</form>
	<script type="text/javascript">
	$(document).ready(function(){
		$('#button_out').click(function(){
			$('#button_in').click();
		});
		$('#button_in').change(function(){
			console.log(this.files[0]);
			var file = this.files[0];
			var srcURL = URL.createObjectURL(file);
			$('#profileImage').attr("src",srcURL);
		})
	});
	
	</script>
</body>
</html>