<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/password-userid-input.css" rel="styleSheet">
<script type="module" src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
	<main>
		<main>
	        <div class="find-password-background">
	            <div class="find-password-card">
	
	                <a href="/" class="logo-link">
	                    <img class="logo-image" alt="logo" src="../../../resources/images/logo2.png">
	                </a>
	
	                <form class="find-password-form">
	                    <div class="auth-method-description">
	                        <p>비밀번호를 찾고자 하는 아이디를 입력해주세요.</p>
	                    </div>
	
	                    <div class="input-group">
	                        <label for="userId">아이디</label>
	                        <input type="text" id="userId" name="userId" placeholder="아이디를 입력해주세요" required>
	                    </div>
	
	                    <button type="submit" id="nextStepButton" class="btn-submit">다음</button>
	
	                    <div class="form-links">
	                        <a href="/user/loginPage">로그인 페이지로 돌아가기</a>
	                        <span>|</span>
	                        <a href="/user/verify-user">아이디 찾기</a>
	                    </div>
	                </form>
	
	            </div>
	        </div>
    </main>
	</main>
</body>
<script src="../../../resources/js/password-userid-input.js"></script>
</html>