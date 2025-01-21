<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/joinPage.css" rel="styleSheet">
</head>
<body>
	<div class="row header-div" style="background-color: #EAEAEA">
		<div class="col-md-6 header-logo">
			<a href="/">
				<img alt="logo" src="../../../resources/images/home.png">
			</a>
		</div>
	</div>
	<main>
		<div class="join-background">
			<div class="join-form" style="height: 600px;">
				<p>Change Nickname</p>
				<input type="text" id="nickname" value="${currentNickname}"><br>
				<button id="changeNicknameBtn" class="btn btn-block btn-outline-secondary" style="width: 200px;">변경</button>
			</div>
		</div>
	</main>
</body>
<script src="../../../resources/js/changeNickname.js"></script>
</html>
