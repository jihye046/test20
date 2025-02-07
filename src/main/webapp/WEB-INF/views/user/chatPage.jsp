<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="../../../resources/css/chat.css" rel="styleSheet">
<head>
<meta charset="UTF-8">
<title>채팅상담</title>
</head>
<body>
	<div id="main">
		<div id="header"><h2>고객센터<small>${sessionScope.userId}</small></h2></div>
		<div id="list">
			
		</div>
		<input type="text" id="msg" placeholder = "대화 내용을 입력하세요.">
	</div>
</body>
<div id="userId" data-userId="${sessionScope.userId}"></div>
<script src="../../../resources/js/chatPage.js"></script>
</html>