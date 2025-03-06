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
				<div class="form-header">
					<span class="profile-title">비밀번호 변경</span>
				</div>
				<div class="form-body">
					<div class="profile-section">
						<input type="password" id="oldPassword" placeholder="현재 비밀번호" required><br>
						<input type="password" id="newPassword" placeholder="새 비밀번호" required><br>
						<input type="password" id="confirmPassword" name="userPw" placeholder="새 비밀번호 확인" required><br>
					</div>
				</div>
				<div class="form-footer">
					<button id="changePasswordBtn" class="btn btn-block btn-outline-secondary" style="width: 200px;">확인</button>
				</div>
			</div>
			<div class="password-message" id="passwordMessage"></div>
		</div>
	</main>
</body>
<script src="../../../resources/js/changePassword.js"></script>
</html>
