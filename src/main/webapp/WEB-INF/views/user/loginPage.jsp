<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/loginPage.css" rel="styleSheet">
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
		<div class="login-background">
			<div class="login-form">
				<form action="/user/login" method="post">
					<p>Welcome Back 👊</p>
					<input type="text" value="${empty sessionScope.userId ? '' : sessionScope.userId}" name="userId" placeholder="UserId"><br>
					<input type="password" name="userPw" placeholder="Password"><br>
					<button class="btn btn-block btn-outline-secondary" type="submit">Sign In</button>
					<div class="login-form-links">
						<a href="/user/joinPage">회원가입</a>
						<a href="#">비밀번호찾기</a>
					</div>
				</form><br>
				<span style="color: gray;">다른 계정으로 로그인하기</span>
				<div>
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
<script src="../../../resources/js/loginPage.js"></script>
</html>
