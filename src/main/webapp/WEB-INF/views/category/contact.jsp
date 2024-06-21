<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/info.css" rel="styleSheet">
<!-- ckeditor -->
<script src="${pageContext.request.contextPath}/resources/static/ckeditor/build/ckeditor.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/loginInfo.jsp" %>
	<!-- 지도 -->
	<div id="map" style="width:500px;height:400px;"></div>
	
</body>
<script src="../../../resources/js/common.js"></script>
<script src="../../../resources/js/contact.js"></script>
</html>