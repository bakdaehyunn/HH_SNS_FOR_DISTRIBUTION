<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
.feedcontainer {
	display: block;
	flex-wrap: wrap;
	align: center;
	justify-content: space-between;
}

.feeditem {
	width: 30%;
	margin: 0 auto;
	margin-bottom: 20px;
	padding: 20px;
	background-color: #f7f7f7;
	border: 1px solid #ddd;
}

.btn_add {
	display: flex;
	justify-content: space-between;
	margin-top: 10px;
}

.btn_update, .btn_delete {
	background-color: transparent;
	border: none;
	color: #1da1f2;
	font-size: 14px;
	cursor: pointer;
}

.btn_update:hover, .btn_delete:hover {
	text-decoration: underline;
}

.feedinput {
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
	position: relative;
}

.feedtxt {
	width: 100%;
	height: 100px;
	margin-right: 10px;
}

.feedinputbtn {
	position: absolute;
	width: 20%;
	height: 30px;
	background-color: #1da1f2;
	color: #fff;
	border: none;
	border-radius: 4px;
	font-size: 14px;
	cursor: pointer;
	bottom: 0;
	right: 0;
	margin-right: 10px;
	margin-bottom: 10px;
}
</style>
<meta charset="UTF-8">
<title>H&H</title>
</head>
<body>

	<div style="text-align: center;">
		<div id="feeds"></div>
	</div>

	<div class="feedinput">
		<input type="text" id="feedcontent"
			placeholder="무슨 일이 일어나고 있나요?"> <br>
		<button id="btn_add">작성</button>
	</div>

	<div class="feedcontainer">
		<div class="feeditem">
			<p>첫 번째 피드 아이템</p>
			<div class="btncontainer">
				<button id="btn_update">수정</button>
				<button id="btn_delete">삭제</button>
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