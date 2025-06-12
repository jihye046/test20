<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/joinPage.css" rel="styleSheet">
</head>
<body>
	<div class="row header-div">
		<div class="col-md-12 header-logo">
			<a href="/">
				<img alt="logo" src="../../../resources/images/logo2.png">
			</a>
		</div>
	</div>
	<main>
		<div class="join-background">
	        <div class="join-card">
	            <h2 class="join-title">회원가입</h2>
	            <p class="join-subtitle">새로운 계정을 만들고 서비스를 이용해보세요.</p>
	
	            <form action="/user/join" method="post" class="join-form">
	                <div class="input-group">
	                    <label for="username">이름</label>
	                    <input type="text" id="username" name="username" placeholder="이름을 입력해주세요" required>
	                </div>
	                <div class="input-group">
	                    <label for="umobile">휴대폰 번호</label>
	                    <input type="text" id="umobile" name="umobile" placeholder="숫자만 입력해주세요" required>
	                </div>
	                <div class="input-group">
	                    <label for="userId">아이디</label>
	                    <input type="text" id="userId" name="userId" placeholder="아이디를 입력해주세요" required>
	                </div>
	                <div class="input-group">
	                    <label for="userPw">비밀번호</label>
	                    <input type="password" id="userPw" name="userPw" placeholder="영문, 숫자, 특수문자 포함 8자 이상" required>
	                </div>
	                <div class="input-group">
	                    <label for="unickName">닉네임</label>
	                    <input type="text" id="unickName" name="unickName" placeholder="닉네임을 입력해주세요" required>
	                </div>
	                <div class="input-group email-verification-group">
	                    <label for="uemail">이메일 주소</label>
	                    <div class="email-input-wrapper">
	                        <input type="email" id="uemail" name="uemail" placeholder="이메일 주소를 입력해주세요" required>
	                        <button type="button" id="mailCodeButton" class="btn-verify-email">인증번호 발송</button>
	                    </div>
	                </div>
	
	                <div class="input-group mail-Check-Box">
	                    <label for="mailCheckInput">인증번호</label>
	                    <input id="mailCheckInput" type="number" placeholder="인증번호 6자리 입력" size="6" required>
	                    <span id="mailCheckSpan" class="verification-message"></span>
	                </div>
	                
	                <button id="joinBtn" class="btn-submit" type="submit" disabled="disabled">회원가입</button>
	
	                <div class="form-links">
	                    <a href="/user/loginPage">이미 계정이 있으신가요? 로그인</a>
	                </div>
	            </form>
	        </div>
	    </div>
	</main>
</body>
<script src="../../../resources/js/joinPage.js"></script>
</html>
