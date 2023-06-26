<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.profileEditContainer {
  border: 1px solid #ccc;
  padding: 20px;
  width: 300px;
  margin: 0 auto;
  text-align: center;
}

.profileEditContainer h1 {
  margin-bottom: 20px;
}

.profileEditContainer img {
  width: 100px;
  height: 100px;
  margin-bottom: 10px;
}

.profileEditContainer .button {
  margin-bottom: 10px;
}

.profileEditContainer form {
  margin-top: 10px;
}

.profileEditContainer input[type="text"],
.profileEditContainer input[type="file"] {
  margin-bottom: 10px;
}

.profileEditContainer input[type="submit"] {
  margin-top: 10px;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>Profile Edit</title>
</head>
<body>
<h1><a href="../feed/main">H&H</a></h1> <br>
<div class="profileEditContainer">
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
	</div>
	<a href="myAccount">내 정보수정</a>
	<script type="text/javascript">
	$(document).ready(function(){
		$('#button_out').click(function(){ //사진변경 버튼 클릭 시
			$('#button_in').click(); // file 선택창 활성화
		});
		$('#button_in').change(function(){ //파일 선택 시
			console.log(this.files[0]);
			var file = this.files[0]; 
			var srcURL = URL.createObjectURL(file);// 파일 경로 생성
			$('#profileImage').attr("src",srcURL); // 이미지 띄우기
		})
	});
	
	</script>
</body>
</html>