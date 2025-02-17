<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- menu -->
		<div class="navigation-wrap bg-light start-header start-style">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<nav class="navbar navbar-expand-md navbar-light">
						<a class="navbar-brand" href="/"><img src="../../../resources/images/logo2.png" alt=""></a>	
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav ml-auto py-4 py-md-0">
								<li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
									<a class="nav-link" href="/user/myPage">Mypage</a>
								</li>
								<li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
									<a class="nav-link" href="/weather/weatherPage">Weather</a>
								</li>
								<li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
									<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Map</a>
									<div class="dropdown-menu">
										<a class="dropdown-item" href="/map/searchedAddMap">검색한 주소지 지도</a>
										<a class="dropdown-item" href="/map/specificAddMap">특정 주소지 지도</a>
										<a class="dropdown-item" href="/map/findRoutePage">특정 주소지까지 길찾기</a>
										<a class="dropdown-item" href="/map/findRoute">검색한 주소지까지 길찾기</a>
									</div>
								</li>
								<li class="nav-item pl-4 pl-md-0 ml-0 ml-md-4">
									<a class="nav-link" href="#">Contact</a>
								</li>
							</ul>
						</div>
						<div class="col-md-6 header-welcomeText">
							<h5 id="welcomeText"></h5>
							<a id="loginLogoutLink" href="${empty sessionScope.userId ?
														  '/user/loginPage' : 
														  '/user/logout'}" 
														  style="font-size: 15px; padding: 10px;">
														  ${empty sessionScope.userId ? '로그인' : '로그아웃'}</a>
							<a href="/board/createPage" style="font-size: 15px;">글쓰기</a>
						</div>
					</nav>		
				</div>
			</div>
		</div>
	</div>
	<div class="my-5 py-5">
	</div>
		<!-- logo & userInfo -->
<!-- 		<a href="/"> -->
<!-- 			<img alt="logo" src="../../../resources/images/home.png"> -->
<!-- 		</a> -->
<!-- 	</div> -->
<script src="../../../resources/js/loginInfo.js"></script>