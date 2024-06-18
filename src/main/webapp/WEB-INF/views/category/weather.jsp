<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/weather.css" rel="styleSheet">
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
				<span id="weather-main"></span>
				<span id="weather-description"></span>
				<span id="weather-icon"></span>
				<span id="weather-feels-like"></span>
				<span id="weather-temp"></span>
				<span id="weather-temp-max"></span>
				<span id="weather-temp-min"></span>
			</div>
		</div>
	</main>
</body>
<script src="../../../resources/js/common.js"></script>
<script src="../../../resources/js/weather.js"></script>
</html>