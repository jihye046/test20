<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/joinPage.css" rel="styleSheet">
</head>
<body>
	<div class="row header-div" style="background-color: #EAEAEA">
		<div class="col-md-12 header-logo">
			<a href="/">
				<img alt="logo" src="../../../resources/images/logo2.png">
			</a>
		</div>
	</div>
	<main>
		<div class="join-background">
			<div class="join-form">
				<form action="/user/join" method="post">
					<p>👋 Hello</p>
					<input type="text" name="username" placeholder="이름" required><br>
					<input type="text" name="umobile" placeholder="'-'를 포함해서 입력해주세요." required><br>
					<input type="text" name="userId" placeholder="아이디" required><br>
					<input type="password" name="userPw" placeholder="비밀번호" required><br>
					<input type="text" name="unickName" placeholder="닉네임" required><br>
					<input id="uemail" type="email" name="uemail" placeholder="이메일" required>
					<button class="btn" type="button" id="mailCodeButton">본인인증</button>
					<br>
					<div class="mail-Check-Box">
						<input id="mailCheckInput" type="number" placeholder="인증번호를 입력해주세요" size="6" required>
						<span id="mailCheckSpan"></span>
					</div>
					
					<button id="joinBtn" class="btn btn-block btn-outline-secondary" type="submit" disabled="disabled">Join</button>
					<div class="join-form-links">
						<a href="/user/loginPage">돌아가기</a>
					</div>
				</form>
			</div>
		</div>
	</main>
</body>
<script src="../../../resources/js/joinPage.js"></script>
</html>
