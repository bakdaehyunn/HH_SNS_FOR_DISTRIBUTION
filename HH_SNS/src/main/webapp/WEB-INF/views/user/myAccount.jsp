<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#myAccountForm {
  border: 1px solid #ccc;
  padding: 20px;
  width: 350px;
  margin: 0 auto;
  text-align: center;
}

#myAccountForm div {
  margin-bottom: 10px;
}

#myAccountForm input[type="submit"] {
  margin-top: 10px;
}

#accountDeleteLink {
  display: block;
  margin-top: 20px;
}
#title{
 text-align: center;
}
</style>
<meta charset="UTF-8">
<title>my account</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<h1><a href="../feed/main">H&H</a></h1> <br>
	<h1 id="title">회원정보 수정</h1>
	<form id="myAccountForm" action="myAccount" method="post">
		<div>아이디 : <input type="text" id="userId" name="userId" value="${vo.userId }" readonly></div>
		<div id="userIdGuide" style="display: none;"></div>
		<div>패스워드 : <input type="password" id="userPassword" name="userPassword" value="${vo.userName }" ></div>
		<div id="userPasswordGuide" style="display: none;"></div>
		<div> 이름: <input type="text" id="userName" name="userName" value="${vo.userName }" maxlength='20' ></div>
		<div id="userNameGuide" style="display: none;"></div>
	    <fmt:formatDate value="${vo.userBirth  }"
		pattern="yyyy-MM-dd" var="userBirth" />
		<div>생년월일 : <input type="text" name="userBirth" id="userBirth" value="${userBirth }" readonly ></div>
		<div id="userBirthGuide" style="display: none;"></div>
		<div>이메일 : <input type="text" id="userEmail" name="userEmail" value="${vo.userEmail }" > 
			<button type="button" id="emailVerifSend">인증번호 받기</button>
		</div> 
		<div id="userEmailGuide" style="display: none;"></div>
		<div>
			인증번호 : <input type="text" id="emailVerifInput" readonly>
		</div>
		<div id="emailVerifInputGuide" style="display: none;"></div>
		
		
		<input type="hidden" name="userProfile" value=""></div>
		<input type="submit" value="수정">
	</form>
	<a href="accDelete" id="accountDeleteLink">회원탈퇴</a>
	
	<script type="text/javascript">
		$(document).ready(function(){
			
			function isBirthday(asValue){
				var regExp=/^[1-2]{1}[0-9]{3}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
				return regExp.test(asValue);
			}
			function isBirthdayB(asValue){
				var regExp=/^(19|20)[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
				return regExp.test(asValue);
			}
			function isNum(asValue) {
				var regExp= /^[0-9]{8}$/
				return regExp.test(asValue);
				//숫자 8자리 입력 가능
			}
			function isUserid(asValue) {
			    var regExp = /^(?=.*[a-zA-Z])[-a-zA-Z0-9_.]{1,8}$/;
			    return regExp.test(asValue);
			}//아이디는 1-8자의 영문과 숫자와 일부 특수문자(._-)만 입력 가능
			function isPassword(asValue) {
			    var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z!@#$%^&*]{8,20}$/;
			    return regExp.test(asValue);
			}//영문과 숫자 조합의 8-20자의 비밀번호를 설정. 특수문자(!@#$%^&*)도 사용
			function isEmail(asValue) {
				var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
				return regExp.test(asValue);
			}
			function isName(asValue){
				var regExp = /^[가-힣a-zA-Z]*$/;
				return regExp.test(asValue);
			}
			
			
			
			
			
			var emailVerifCode ='-1'; // 발급된 이메일 인증번호 저장 변수
			
			var EmailSendBool = true;
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
			var emailVerifBool = true;
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
			$('#userEmail').mousedown(function(){
				$('#userEmail').val('');
				emailVerifBool = true;
				EmailSendBool = true;
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
			var userPasswordBool = true;
			$('#userPassword').mousedown(function(){
				$('#userPassword').val('');
				userPasswordBool= false;
			});
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
			var userNameBool = true;
			$('#userName').mousedown(function(){
				$('#userName').val('');
				userNameBool =false;
			});
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
		
			
			var userBirthBool = true;
			$('#userBirth').focus(function(){
				var userBirth =$(this).val();
				console.log(userBirth);
				if(userBirthBool == true && isBirthdayB(userBirth)){
					$('#userBirth').val('');
					$('#userBirthGuide').text('');
					$('#userBirthGuide').show();
				}
				
			});
			$('#userBirth').blur(function(){
				var userBirth =$(this).val();
				console.log(userBirth);
				if(userBirth != ''){
					if(isNum(userBirth)){
						if(isBirthday(userBirth)){
							var  bYear = userBirth.substr(0,4);
							var bMonth =  userBirth.substr(4,2);
							var bDay = userBirth.substr(6,2);
							var  birthYear = parseInt(userBirth.substr(0,4));
							var birthMonth =  parseInt(userBirth.substr(4,2));
							var birthDay =  parseInt(userBirth.substr(6,2));
							var today = new Date();
							var year =  parseInt(today.getFullYear());
							var month =  parseInt(('0' + (today.getMonth() + 1)).slice(-2));
							var day =  parseInt(('0' + today.getDate()).slice(-2));
							if(birthYear>year ||(birthYear == year && birthMonth > month) || (birthYear == year && birthMonth == month && birthDay > day)){
								list = '미래에서 오셨군요.';
							}else if(birthYear < 1900||(birthYear==1900 && birthMonth<01)||(birthYear == 1900 && birthMonth == 01 && birthDay < 1)){
								list = '과거에서 오셨군요.';
							}else{
								userBirthBool = true;
								list = '';
								$('#userBirth').val(bYear+'-'+bMonth+'-'+bDay);
							}
						}else{
							list = '생년월일을 다시 확인해주세요';
						}
					}else if(userBirthBool == true && isBirthdayB(userBirth)){
						$('#userBirth').val('');
					}
					else{
						userBirthBool = false;
						list = '생년월일은 8자리 숫자로 입력해 주세요. ';
					}
					$('#userBirthGuide').text(list);
					$('#userBirthGuide').show();
				}else{
					list = '생년월일을 입력해 주세요. ';
					$('#userBirthGuide').text(list);
					$('#userBirthGuide').show();
				}
				
			});
			
			$('#myAccountForm').submit(function(e){
				warn="필수 입력 사항입니다.";
				if(userPasswordBool==false){
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
					
				}else if(userBirthBool == false){
					e.preventDefault();
					$('#userBirth').focus();
					$('#userBirthGuide').html(warn);
					$('#userBirthGuide').show();
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