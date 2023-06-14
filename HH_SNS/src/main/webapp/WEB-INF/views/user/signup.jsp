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
	<form id="signUpForm" action="signup" method="post">
		<%-- 아이디 중복 체크(비동기) 및 정규식 --%>
		<div>아이디 : <input type="text" id="userId" name="userId" placeholder="아이디"></div>
		<div id="userIdGuide" style="display: none;"></div>
		<div>패스워드 : <input type="password" id="userPassword" name="userPassword" placeholder="패스워드" ></div>
		<div id="userPasswordGuide" style="display: none;"></div>
		<div> 이름: <input type="text" id="userName" name="userName" placeholder="이름" maxlength='20' ></div>
		<div id="userNameGuide" style="display: none;"></div>
		<div>닉네임 : <input type="text" id="userNickname" name="userNickname" placeholder="닉네임"  maxlength='20' ></div>
		<div id="userNicknameGuide" style="display: none;"></div>
		<%-- 이메일 인증(비동기) 이메일 @(옵션)  생년월일 생년월일 옵션  --%>
		<div>이메일 : <input type="text" id="userEmail" name="userEmail" > 
			<button type="button" id="emailVerifSend">인증번호 받기</button>
		</div> 
		<div id="userEmailGuide" style="display: none;"></div>
		<div>
			인증번호 : <input type="text" id="emailVerifInput" readonly>
		</div>
		<div id="emailVerifInputGuide" style="display: none;"></div>
		<div>생년월일 : <input type="date" name="userBirth" id="userBirth" required></div>
		<input type="hidden" name="userProfile" value=""></div>
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
			function isName(asValue){
				var regExp = /^[가-힣a-zA-Z]*$/;
				return regExp.test(asValue);
			}
			function isNickname(asValue){
				var regExp = /^[가-힣a-zA-Z0-9]*$/;
				return regExp.test(asValue);
			};
			
			var userIdBool =false;
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
									userIdBool = false;
								} else if(data == 0){
									list = '해당 아이디는 사용 가능 합니다.';
									userIdBool = true;
								}
								$('#userIdGuide').html(list);
								$('#userIdGuide').show();
							} //end function()
							
						}); // end ajax
					}
					else {
						userIdBool =false;
						list = '아이디는 1-8자의 영문과 숫자와 일부 특수문자(._-)만 입력 가능';
						$('#userIdGuide').html(list);
					}
				} else {
					userIdBool =false;
					list ='아이디를 입력해주세요. ';
					$('#userIdGuide').html(list);
					$('#userIdGuide').show();
					//$('#userIdGuide').hide();
				}
				
			});//end userid.keyup event 
			
			var emailVerifCode ='-1'; // 발급된 이메일 인증번호 저장 변수
			
			var EmailSendBool = false;
			$('#emailVerifSend').click(function(){
				var userEmail = $('#userEmail').val();
				$.ajax({
					type : 'POST',
					url : '../users/emailVerif',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : userEmail,
					success : function (result){
						emailVerifCode =result
						EmailSendBool = true;
						$('#emailVerifInput').attr("readonly", false);
						alert('해당 이메일로 인증번호가 전송되었습니다.');
					}
				})
				
			});
			var emailVerifBool = false;
			$('#emailVerifInput').blur(function(){
				var list ='';
				if($('#emailVerifInput').val() != null){
					var emailVerifInput = $('#emailVerifInput').val();
					if(emailVerifInput == emailVerifCode){
						console.log("이메일 인증 성공");
						list='이메일 인증 성공';
						emailVerifBool = true;
					}else{
					emailVerifBool = false;
					}
					$('#emailVerifInputGuide').show();
					$('#emailVerifInputGuide').html(list);
				}
			});
			
			$('#userEmail').keyup(function(){
				var userEmail = $('#userEmail').val();
				console.log(userEmail);
				var list = '';
				if(userEmail != ''){
					if(isEmail(userEmail)){	
						var emaildivide = userEmail.split(".");
						$.ajax({
							type : 'GET',
							url : '../users/userEmail/'+emaildivide[0]+'/'+emaildivide[1],
							headers : {'Content-Type' : 'application/json'},
							success : function(result){
								console.log(result);
								if(result == 1){
									console.log(data);
									list ='해당 이메일은 이미 사용 중 입니다.';
									$('#userEmailGuide').html(list);
									$('#userEmailGuide').show();
								}else{
									list ='해당 이메일은 사용 가능합니다.';
									$('#userEmailGuide').html(list);
									$('#userEmailGuide').show();
								} 
							
							}
						})
					} else {
						list ='올바른 이메일을 입력해주세요.';
						$('#userEmailGuide').html(list);
						$('#userEmailGuide').show();
					}
				}else {
					
					list = '이메일을 입력해주세요.';
					$('#userEmailGuide').html(list);
					$('#userEmailGuide').show();
				}
			});
			var userPasswordBool = false;
			$('#userPassword').keyup(function(){
				var userPassword = $('#userPassword').val();
				console.log(userPassword);
				var list = '';
				if(userPassword != ''){
					if(isPassword(userPassword)){
						list = '해당 패스워드는 사용가능 합니다.';
						userPasswordBool = true;
					}else{
						list = '영문과 숫자 조합의 8-20자의 비밀번호를 설정해주세요. 특수문자(!@#$%^&*)도 사용'
						userPasswordBool = false;
					}
					$('#userPasswordGuide').html(list);	
					$('#userPasswordGuide').show();
				}else {
					list = '패스워드를 입력해주세요. ';
					$('#userPasswordGuide').html(list);	
					$('#userPasswordGuide').show();
					userPasswordBool = false;
				}
			});
			var userNameBool = false;
			$('#userName').blur(function(){
				var userName = $('#userName').val();
				console.log(userName);
				var list = '';
				if(userName != ''){
					if(isName(userName)){
						userNameBool= true;
						list = '';
					}else{
						userNameBool= false;
						list = '한글 또는 영어로만 입력 가능합니다.';
					}
					$('#userNameGuide').html(list);
					$('#userNameGuide').show();
				}else{
					userNameBool= false;
					list = '이름을 입력해주세요.';
					$('#userNameGuide').html(list);
					$('#userNameGuide').show();
				}
			});
			var userNicknameBool = false;
			$('#userNickname').blur(function(){
				var userNickname =  $('#userNickname').val();
				console.log(userNickname);
				var list = '';
				if(userNickname != ''){
					if(isNickname(userNickname)){
						userNicknameBool = true;
						list = '';
				
					}else{
						userNicknameBool = false;
						list = '한글, 영어, 숫자로만 입력 가능합니다.';
						
					}$('#userNicknameGuide').html(list);
					$('#userNicknameGuide').show();
				}else{
					userNicknameBool = false;
					var list = '닉네임을 입력해주세요.';
					$('#userNicknameGuide').html(list);
					$('#userNicknameGuide').show();
				}
			});
			
			$('#signUpForm').submit(function(e){
				warn="필수 입력 사항입니다.";
				if(userIdBool == false){
					e.preventDefault();
					$('#userId').focus();
					$('#userIdGuide').html(warn);
					$('#userIdGuide').show();
					
				}	
				else if(userPasswordBool==false){
					e.preventDefault();
					$('#userPassword').focus();
					$('#userPasswordGuide').html(warn);
					$('#userPasswordGuide').show();
					
				}
				else if(userNameBool == false){
					e.preventDefault();
					$('#userName').focus();
					$('#userNameGuide').html(warn);
					$('#userNameGuide').show();
					
				}else if(userNicknameBool == false){
					e.preventDefault();
					$('#userNickname').focus();
					$('#userNicknameGuide').html(warn);
					$('#userNicknameGuide').show();
					
				}else if(EmailSendBool == false){
					e.preventDefault();
					warn='이메일 인증을 진행해주세요.'
					$('#userEmail').focus();
					$('#userEmailGuide').html(warn);
					$('#userEmailGuide').show();
					
				}else if(emailVerifBool == false){
					e.preventDefault();
					warn='이메일 인증을 진행해주세요.'
					$('#emailVerifInput').focus();
					$('#emailVerifInputGuide').html(warn);
					$('#emailVerifInputGuid').show();
				}
				return true;
			});
			
				
		
		});// end document
	</script>
</body>
</html>