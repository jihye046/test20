<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/createPage.css" rel="styleSheet">
<!-- ckeditor -->
<script src="${pageContext.request.contextPath}/resources/static/ckeditor/build/ckeditor.js"></script>
<!-- 주소 검색 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/loginInfo.jsp" %>
	<main>
		<div class="container">
        	<div class="row">
        		<div class="col-md-2"></div>
					<div class="col-md-8 createPage-main">
						<form action="/board/createBoard" method="post" class="create-form">
							<input type="hidden" size="100" name="bName" placeholder="${sessionScope.userId}" value="${sessionScope.userId}" readonly>
							<input class="bTitleInput" type="text" size="100" name="bTitle" placeholder="제목" required>
							<textarea id="editor" name="bContent" placeholder="내용을 입력해주세요"></textarea>
							
							<!-- map -->
							<div class="search-box">
								<input type="text" id="inputAdd" name="bAddress" placeholder="주소" readonly>
								<input type="button" class="address-search-btn" onclick="searchedAdd()" value="주소 검색"><br>
							</div>
							
							<button class="btn btn-outline-info" type="submit">등록</button>
						</form>
					</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</main>
<%-- 	<%@ include file="/WEB-INF/views/include/footer.jsp" %> --%>
	<script src="../../../resources/js/createPage.js"></script>
	<script src="../../../resources/js/uploadAdapter.js"></script>
</body>
</html>