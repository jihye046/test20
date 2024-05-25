<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/joinPage.css" rel="styleSheet">

</head>
<body>
	<main>
		<div class="join-background">
			<div class="join-form">
				<form action="/user/join" method="post">
					<p>👋 Hello</p>
					<input type="text" name="userId" placeholder="UserId"><br>
					<input type="password" name="userPw" placeholder="Password"><br>
					<input type="text" name="unickName" placeholder="Nickname"><br>
					<input id="uemail" type="email" name="uemail" placeholder="Email">
					<button class="btn" type="button" id="mailCodeButton">본인인증</button>
					<br>
					<div class="mail-Check-Box">
						<input id="mailCheckInput" type="number" placeholder="인증번호를 입력해주세요" size="6">
						<span id="mailCheckSpan"></span>
					</div>
					<button id="joinBtn" class="btn btn-block btn-outline-secondary" type="submit" disabled="disabled">Join</button>
					<div class="join-form-links">
						<a href="/user/loginPage">로그인</a>
					</div>
				</form>
			</div>
		</div>
	</main>
</body>
<script src="../../../resources/js/joinPage.js"></script>
</html>
