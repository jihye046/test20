<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row header-div">
	<div class="col-md-6 header-logo">
		<i class="fa-solid fa-bars fa-xl" style="padding-right: 10px;" id="sidebar"></i>
		<a href="/">
			<img alt="logo" src="../../../resources/images/home.png">
		</a>
	</div>
	<div class="col-md-6 header-welcomeText">
		<h4 id="welcomeText"></h4>
		<a href="${empty sessionScope.userId ? '/user/loginPage' : '/user/logout'}">${empty sessionScope.userId ? '로그인' : '로그아웃'}</a>
		<a href="/board/createPage">글쓰기</a>
	</div>
	
	<!-- sidebar -->
	

</div>
