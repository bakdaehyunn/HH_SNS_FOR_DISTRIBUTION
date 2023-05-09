<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
.feed-input {
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

.feed-input-text {
	width: 70%;
	height: 70%;
	margin-right: 10px;
}

.feed-input-button {
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
	margin-bottom: 2; /* 추가 */
	margin-right: 2px;
}

.feed-container {
	display: block; /* flex에서 block으로 변경 */
	flex-wrap: wrap;
	justify-content: center;
	align-items: center;
	width: 80%;
	margin: 0 auto;
}

.feed-item {
	width: 30%; margin : 0 auto; /* 가운데 정렬을 위해 추가 */ 
	margin-bottom : 20px;
	padding : 20px; background-color : #f7f7f7;
	border: 1px solid #ddd;
	margin: 0 auto; /* 가운데 정렬을 위해 추가 */
	margin-bottom: 20px;
	padding: 20px;
	background-color: #f7f7f7;
}

.button-container {
	display: flex;
	justify-content: space-between;
	margin-top: 10px;
}

.edit-button, .delete-button {
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

	<div style="text-align: center;">
		<div id="feeds"></div>
	</div>

	<div class="feed-input">
		<input type="text" class="feed-input-text" placeholder="피드 작성하기">
		<button class="feed-input-button">작성</button>
	</div>

	<div class="feed-container">
		<div class="feed-item">
			<p>첫 번째 피드 아이템</p>
			<div class="button-container">
				<button class="edit-button">수정</button>
				<button class="delete-button">삭제</button>
			</div>
		</div>
		<div class="feed-item">
			<p>두 번째 피드 아이템</p>
			<div class="button-container">
				<button class="edit-button">수정</button>
				<button class="delete-button">삭제</button>
			</div>
		</div>
		<div class="feed-item">
			<p>세 번째 피드 아이템</p>
			<div class="button-container">
				<button class="edit-button">수정</button>
				<button class="delete-button">삭제</button>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			// 피드 작성버튼
			$('#btn_add').on('click', function() {
			});
			
			// userId가 일치 할 시에 누르는 삭제 버튼
			$('#btn_delete').on('click', function() {
				$(this).parents('.feed-item').remove();
			});

			// userId가 일치 할 시에 누르는 수정 버튼
			$('#btn_update').on('click', function() {
				}
			});
		});
	</script>


</body>
</html>