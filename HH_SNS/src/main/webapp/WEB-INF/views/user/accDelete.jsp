<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.accDeleteContainer {
  border: 1px solid #ccc;
  padding: 20px;
  width: 350px;
  margin: 0 auto;
  text-align: center;
}

.accDeleteContainer h1 {
  margin-bottom: 20px;
}

.accDeleteContainer div {
  margin-bottom: 10px;
}

.accDeleteContainer input[type="password"] {
  margin-bottom: 10px;
}

.accDeleteContainer input[type="submit"] {
  margin-top: 10px;
}

.accDeleteContainer #deleteGuide {
  display: none;
}
</style>
<meta charset="UTF-8">
<title>AccDelete</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<h1><a href="../feed/main">H&H</a></h1> <br>
<div class="accDeleteContainer">
	<h1>회원탈퇴</h1>
	
	<form id="accDeleteForm" action="accDelete" method="POST">
		<div>탈퇴 시 피드, 댓글, 팔로우 등 모든 정보가 사라집니다.</div>
		<div> 탈퇴하기 위해서 비밀번호를 입력해주세요.</div>
		<div>비밀번호 : <input type="password" id="userPassword" name="userPassword"><div></div>
		<input type="submit" value="회원탈퇴">
		<div id="deleteGuide" style="display: none ;"></div>
	</form>
</div>	
	
	
	<input type="hidden" id="deleteAlert" value="${delete_result }">
	
	<script type="text/javascript">
		
		$(document).ready(function(){
			
			var result = $('#deleteAlert').val();
			
			if(result == 'accDeleteUnsuccess'){
				$('#deleteGuide').text('비밀번호가 일치 하지않습니다.');
				$('#deleteGuide').show();
			}
			
			$('#accDeleteForm').submit(function(e){
				
				var userPassword = $('#userPassword').val();
				if(userPassword == ''){
					e.preventDefault();
					$('#userPassword').focus();
					$('#deleteGuide').text('비밀번호를 입력해주세요.');
					$('#deleteGuide').show();
				}
				return true;
			});
		})
		
	</script>
</body>
</html>