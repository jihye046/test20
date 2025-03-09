<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/chatPage1.css" rel="styleSheet">
<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<div id="header"><h2>><small>${sessionScope.userId}</small></h2></div>
		<div id="dateDisplay"></div>
		<div id="chatList">
		
		</div>
		<input type="text" id="msg" placeholder = "대화 내용을 입력하세요." onkeydown="handleKeyDown(event)">
	</div>
</body>
<div id="userId" data-userId="${sessionScope.userId}"></div>
<script src="../../../resources/js/chatPage1.js" defer></script>
</html>