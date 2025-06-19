<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/loginPage.css" rel="styleSheet">
</head>
<body>
	<main>
		<div class="login-background">
			<div class="login-form">
				<form action="/user/login" method="post">
					<a href="/">
						<img class="logo-image" alt="logo" src="../../../resources/images/logo2.png">
					</a>
					<input type="text" value="${empty userId ? '' : userId}" name="userId" placeholder="아이디"><br>
					<input type="password" name="userPw" placeholder="비밀번호"><br>
					<button class="btn btn-block btn-outline-secondary" type="submit">Sign In</button>
					<div class="login-form-links">
							<a href="/user/joinPage">회원가입</a>
							<a href="/user/verify-user?mode=id">아이디찾기</a>
							<a href="/user/password-userid-input">비밀번호찾기</a>
					</div>
				</form><br>
				<span>다른 계정으로 로그인하기</span>
				<div class="social-button">
					<a href="/social/naverLogin">
						<img class="social-login-logo" id="naverLogin" alt="naverLogin" src="../../../resources/images/btnG_Squareicon.png">
					</a>
					<a href="/social/googleLogin">
						<img class="social-login-logo" id="googleLogin" alt="googleLogin" src="../../../resources/images/btn_googleLogo.png">
					</a>
				</div>
			</div>
		</div>
	</main>
</body>
<div id="joinResult" data-join-result="${joinResult}"></div>
<div id="loginFail" data-loginFail-result="${loginFail}"></div>
<script src="../../../resources/js/loginPage.js"></script>
</html>
