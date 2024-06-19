<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/weather.css" rel="styleSheet">
<!-- Bootstrap Icons CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
<!-- ckeditor -->
<script src="${pageContext.request.contextPath}/resources/static/ckeditor/build/ckeditor.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/loginInfo.jsp" %>
	<main class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 detailPage-main" >
				<i id="currentLocationBtn" class="fa-solid fa-location-crosshairs">&nbsp;현위치</i><br>			
				<div class="currentLocationWeather">
					<!-- 세션에 저장된 위치의 날씨 정보를 보여주고, 없다면 서울 날씨를 보여줌 -->
					
					<!-- getCurrentWeather(${sessionScope.latitude}, ${sessionScope.longitude})  -->

				</div>
			</div>
		</div>
	</main>
</body>
<div id="latitude" data-latitude="${sessionScope.latitude}"></div>
<div id="longitude" data-longitude="${sessionScope.longitude}"></div>
<script src="../../../resources/js/common.js"></script>
<script src="../../../resources/js/weather.js"></script>
</html>