<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	<!-- 채팅메신저 -->
	<link href="../../../resources/css/chatPage1.css" rel="styleSheet">
	<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<div id="header"><small>${receiver}님과의 대화방</small></div>
		<div id="dateDisplay"></div>
		<div id="chatList"></div>
 		<input type="text" id="msg" placeholder = "대화 내용을 입력하세요." onkeydown="handleKeyDown(event)">
	</div>
</body>
<div id="userId" data-userId="${sessionScope.userId}"></div>
<div id="bName" data-bName="${receiver}"></div>
<!-- <script src="../../../resources/js/chatPage1.js" defer></script> -->
<script src="../../../resources/js/directChat.js" defer></script>
</html>