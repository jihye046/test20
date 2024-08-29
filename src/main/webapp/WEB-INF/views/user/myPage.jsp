<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/detailPage.css" rel="styleSheet">
</head>
<body>
	<%@ include file="/WEB-INF/views/include/loginInfo.jsp" %>
	<main class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 detailPage-main">
				<div><h3>프로필 수정</h3></div>
				<table class="table">
				<thead>
					<tr>
						<th>
							#
						</th>
						<th>
							Product
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							1
						</td>
						<td>
							TB - Monthly
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			<div class="col-md-2"></div>
		</div>
	</main>
</body>
<script src="../../../resources/js/myPage.js"></script>
<script src="../../../resources/js/common.js"></script>
</html>