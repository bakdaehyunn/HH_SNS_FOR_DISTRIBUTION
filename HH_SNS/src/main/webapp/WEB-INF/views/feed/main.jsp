<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
.feed-container {
	display: flex;
	flex-wrap: wrap;
	align: center;
	justify-content: space-between;
}

.feed-item {
	width: 30%;
	margin-bottom: 20px;
	padding: 20px;
	background-color: #f7f7f7;
	border: 1px solid #ddd;
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

.edit-button:hover, .delete-button:hover {
	text-decoration: underline;
}
</style>
<meta charset="UTF-8">
<title>H&H</title>
</head>
<body>

	<div style="text-align: center;">
		<div id="feeds"></div>
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
			// 피드 삭제 버튼 클릭 시 해당 피드 아이템 삭제
			$('.delete-button').on('click', function() {
				$(this).parents('.feed-item').remove();
			});

			// 피드 수정 버튼 클릭 시 해당 피드 아이템의 텍스트 수정
			$('.edit-button').on('click', function() {
				var feedText = $(this).parent().siblings('p').text();
				var newText = prompt("수정할 내용을 입력하세요.", feedText);
				if (newText !== null) {
					$(this).parent().siblings('p').text(newText);
				}
			});
		});
	</script>


</body>
</html>