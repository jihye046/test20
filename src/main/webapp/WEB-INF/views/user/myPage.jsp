<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/detailPage.css" rel="styleSheet">
<link href="../../../resources/css/myPage.css" rel="styleSheet">
<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
</head>
<body>
	<%@ include file="/WEB-INF/views/include/loginInfo.jsp" %>
	<main class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 detailPage-main">
				<div class="myPage-title">
					<h3>마이페이지</h3>
				</div>
				<div class="mypage-links">
					<div class="mypage-link">
						<a href="/user/getUserPosts">
							<i class="fa-regular fa-pen-to-square"></i>
							내가 작성한 게시글
						</a>
					</div>
					<div class="mypage-link">
						<a href="#">
							<i class="fa-regular fa-comment"></i>
							내가 작성한 댓글
						</a>
					</div>
					<div class="mypage-link">
						<a href="#">
							<i class="fa-regular fa-heart"></i>
							좋아요한 게시글
						</a>
					</div>
					<div class="mypage-link">
						<a href="#">비밀번호 변경</a>
					</div>
					<div class="mypage-link">
						<a href="#">닉네임 변경</a>
					</div>
					<div class="mypage-link">
						<a href="#">고객센터</a>
					</div>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
	</main>
</body>
<script src="../../../resources/js/myPage.js"></script>
<script src="../../../resources/js/common.js"></script>
</html>