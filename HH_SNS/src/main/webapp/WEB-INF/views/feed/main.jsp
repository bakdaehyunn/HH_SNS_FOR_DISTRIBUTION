<%@page import="edu.spring.ex06.domain.UserInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
.input_feed {
	display: block;
	flex-wrap: wrap;
	align-items: center;
	justify-content: space-between;
	width: 30%;
	margin: 0 auto;
	margin-bottom: 20px;
	margin-top: 20px;
	padding: 20px;
	background-color: #f7f7f7;
	border: 1px solid #ddd;
	height: 200px;
	position: relative; /* 추가 */
}

.feedContent {
	width: 70%;
	height: 70%;
	margin-right: 10px;
}

.btn_add {
	position: absolute;
	bottom: 0;
	width: 20%;
	height: 30px;
	background-color: #1da1f2;
	color: #fff;
	border: none;
	border-radius: 4px;
	font-size: 14px;
	cursor: pointer;
	margin-bottom: 1; /* 추가 */
	margin-right: 1px;
}

.div_post {
	display: block; /* flex에서 block으로 변경 */
	flex-wrap: wrap;
	justify-content: center;
	align-items: center;
	width: 80%;
	margin: 0 auto;
}

.post_item {
	width: 30%;
	margin: 0 auto; /* 가운데 정렬을 위해 추가 */
	margin-bottom: 20px;
	padding: 20px;
	background-color: #f7f7f7;
	border: 1px solid #ddd;
	margin: 0 auto; /* 가운데 정렬을 위해 추가 */
	margin-bottom: 20px;
	padding: 20px;
	background-color: #f7f7f7;
	margin-bottom: 20px;
	padding: 20px;
	background-color: #f7f7f7;
	padding: 20px;
	background-color: #f7f7f7;
	background-color: #f7f7f7;
}

.div_btn {
	display: flex;
	justify-content: space-between;
	margin-top: 10px;
}

.update_btn, .delete_btn {
	background-color: transparent;
	border: none;
	color: #1da1f2;
	font-size: 14px;
	cursor: pointer;
}
</style>
<meta charset="UTF-8">
<title>H&H</title>
</head>
<body>

	<%
		String userId = (String) session.getAttribute("userId");
		
	%>

	<input type="hidden" id="feedId" value="1">

	<div style="text-align: center;">
		<div id="feeds"></div>
	</div>

	<form action="main" method="get">
		<div class="input_feed">
			<p id="userId">${userId}</p>
			<input type="text" id="feedContent" placeholder="피드 작성하기" required>
			<input type="submit" id="btn_add" value="등록">
		</div>
	</form>


	<c:forEach var="vo" items="${main}">
		<div class="div_post">
			<div class="post_item">
				<p>${vo.feedContent }</p>
				<div class="div_btn">
					<input type="submit" class="btn_update" value="수정"> <input
						type="submit" class="btn_delete" value="삭제">
				</div>
			</div>
		</div>
	</c:forEach>



	<input type="hidden" id="insertAlert" value="${insert_result }">

	<script type="text/javascript">
		// - css 선택자 :
		//	'p' : 태그(요소)
		//	'#p1' : 아이디
		//	'.c1' : 클래스
		
		$(document).ready(function() {
			// 피드 작성버튼
			$('#btn_add').click(function() {
				console.log('왜 안뜨지');
				var feedId = $('#feedId').val();
				var userId = $('#userId').val();
				var feedContent = $('#feedContent').val();
				
				console.log(userId);
				console.log(feedContent);

				var obj = {
					'feedId' : feedId,
					'userId' : userId,
					'feedContent' : feedContent
					
				}
				console.log(obj);

				$.ajax({
					type : 'POST',
					url : '/ex06/feeds',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj),
					success : function(result) {
						console.log(result);
						if (result == 1) {
							console.log('★ 피드작성 완료');
							getAllmain();
						} else {
							console.log('★ 피드작성 실패');
				          }
					}

				});// end ajax()
			});// end btn_add.click();
		}); // end ready();
	</script>


</body>
</html>