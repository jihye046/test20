<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row header-div">
	<div class="col-md-6 header-logo">
		<!-- sidebar -->
		<i class="fa-solid fa-bars fa-xl" id="sidebarBtn"></i>
		<div id="sidebarMenu" class="sidebarMenu">
			<a href="#" id="closeBtn">
				<i class="fa-solid fa-xmark fa-2xs"></i>
			</a>
			<div class="category">
				<p>개인페이지</p>
		        <a href="#">Mypage</a>
	        </div>
	        <div class="category">
	        	<p>회사정보</p>
		        <a href="#">Contact</a>
	        </div>
	        <div class="category">
		        <p>기타서비스</p>
		        <a href="/category/weather">Weather</a>
	        </div>
		</div>
		<!-- sidebar End -->
		<a href="/">
			<img alt="logo" src="../../../resources/images/home.png">
		</a>
	</div>
	<div class="col-md-6 header-welcomeText">
		<h4 id="welcomeText"></h4>
		<a href="${empty sessionScope.userId ? '/user/loginPage' : '/user/logout'}">${empty sessionScope.userId ? '로그인' : '로그아웃'}</a>
		<a href="/board/createPage">글쓰기</a>
	</div>
</div>
