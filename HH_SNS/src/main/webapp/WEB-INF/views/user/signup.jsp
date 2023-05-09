<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<%-- 아이디 비밀번호 이름 닉네임, 생일 이메일 --%>
	<form action="signup" method="post">
		<%-- 아이디 중복 체크(비동기) 및 정규식 --%>
		<p>아이디 : <input type="text" id="userId" name="userId" placeholder="아이디" required></p>
		<p id="userIdGuide" style="display: none;"></p>
		<p>패스워드 : <input type="password" id="userPassword" name="userPassword" placeholder="패스워드" required></p>
		<p id="userPasswordGuide" style="display: none;"></p>
		<p> 이름: <input type="text" id="userName" name="userName" placeholder="이름" maxlength='20' required></p>
		<p id="userNameGuide" style="display: none;"></p>
		<p>닉네임 : <input type="text" id="userNickname" name="userNickname" placeholder="닉네임"  maxlength='20' required></p>
		<p id="userNicknameGuide" style="display: none;"></p>
		<%-- 이메일 인증(비동기) 이메일 @(옵션)  생년월일 생년월일 옵션  --%>
		<p>이메일 : <input type="email" id="userEmail" name="userEmail" required></p> 
		<p id="userEmailGuide" style="display: none;"></p>
		<p>생년월일 : <input type="date" name="userBirth"required></p>
		<input type="hidden" name="userProfile" value=""></p>
		<input type="submit" value="가입">
	</form>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			function isUserid(asValue) {
			    var regExp = /^(?=.*[a-zA-Z])[-a-zA-Z0-9_.]{1,8}$/;
			    return regExp.test(asValue);
			}//아이디는 8-15자의 영문과 숫자와 일부 특수문자(._-)만 입력 가능
			function isPassword(asValue) {
			    var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z!@#$%^&*]{8,20}$/;
			    return regExp.test(asValue);
			}//영문과 숫자 조합의 8-20자의 비밀번호를 설정해주세요. 특수문자(!@#$%^&*)도 사용
			function isEmail(asValue) {
				var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
				return regExp.test(asValue);
			}
			
			$('#userId').keyup(function(){
				var userId = $('#userId').val();
				console.log(userId);
				var list = '';
				if(userId != '' ){
					if(isUserid(userId)){
						// $.ajax로 송수신
						$.ajax({
							type : 'GET',
							url : '../users/userId/'+userId,
							headers : {'Content-Type' : 'application/json' },
							success :function(data){
								console.log(data);
								if(data == 1){
									list ='해당 아이디는 이미 사용 중 입니다. ';
								} else if(data == 0){
									list = '해당 아이디는 사용 가능 합니다.';
								}
								
								$('#userIdGuide').html(list);
								$('#userIdGuide').show();
							} //end function()
							
						}); // end ajax
					}
					else {
						list = '아이디는 1-8자의 영문과 숫자와 일부 특수문자(._-)만 입력 가능';
						$('#userIdGuide').html(list);
					}
				} else {
					list ='아이디를 입력해주세요. ';
					$('#userIdGuide').html(list);
					$('#userIdGuide').show();
					//$('#userIdGuide').hide();
				}
				
			});//end userid.keyup event
			$('#userEmail').keyup(function(){
				var userEmail = $('#userEmail').val();
				console.log(userEmail);
				var list = '';
				if(userEmail != ''){
					$('#userEmail').show();
					if(isEmail(userEmail)){
						$.ajax({
							type : 'GET',
							url : '../users/userEmail/'+userEmail,
							headers : {'Content-Type' : 'application/json'},
							success : function(data){
								console.log(data);
								if(data == 1){
									list ='해당 이메일은 이미 사용 중 입니다.';
									$('#userEmailGuide').html(list);
									$('#userEmailGuide').show();
								} 
							
							}
						});
					} 
					else {
						list ='올바른 이메일을 입력해주세요.'
					}
				}else {
					list = '이메일을 입력해주세요.';
					$('#userIdGuide').html(list);
					$('#userIdGuide').show();
				}
			});
			$('#userPassword').keyup(function(){
				var userPassword = $('#userPassword').val();
				console.log(userPassword);
				var list = '';
				if(userPassword != ''){
					if(isPassword(userPassword)){
						list = '해당 패스워드는 사용가능 합니다.';
					}else{
						list = '영문과 숫자 조합의 8-20자의 비밀번호를 설정해주세요. 특수문자(!@#$%^&*)도 사용'
					}
					$('#userPasswordGuide').html(list);	
					$('#userPasswordGuide').show();
				}else {
					list = '패스워드를 입력해주세요. ';
					$('#userPasswordGuide').html(list);	
					$('#userPasswordGuide').show();
				}
			});
			
			$('#userName').focusout(function(){
				var userName = $('#userName').val();
				console.log(userName);
				var list = '';
				if(userName == ''){
					var list = '이름을 입력해주세요.';
					$('#userNameGuide').html(list);
					$('#userNameGuide').show();
				}
			});
			$('#userNickname').focusout(function(){
				var userNickname =  $('#userNickname').val();
				console.log(userNickname);
				var list = '';
				if(userNickname == ''){
					var list = '닉네임을 입력해주세요.';
					$('#userNicknameGuide').html(list);
					$('#userNicknameGuide').show();
				}
			})
			
				
		
		});// end document
	</script>
</body>
</html>