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
					<input type="email" name="uemail" placeholder="Email"><br>
					<button class="btn btn-block btn-outline-secondary" type="submit">Join</button>
					<div class="join-form-links">
						<a href="/user/loginPage">로그인</a>
					</div>
				</form>
			</div>
		</div>
	</main>
</body>
</html>