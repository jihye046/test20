<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/createPage.css" rel="styleSheet">
<!-- ckeditor -->
<script src="${pageContext.request.contextPath}/resources/static/ckeditor/build/ckeditor.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/loginInfo.jsp" %>
	<main>
		<div class="container">
        	<div class="row">
        		<div class="col-md-2"></div>
					<div class="col-md-8 createPage-main">
						<form action="/board/createBoard" method="post" class="create-form">
							<input type="text" size="100" name="bName" placeholder="😃 이름" required>
							<input type="text" size="100" name="bTitle" placeholder="🔈 제목" required>
							<textarea id="editor" name="bContent" placeholder="내용을 입력해주세요"></textarea>
							<button class="btn btn-outline-info" type="submit">등록</button>
						</form>
					</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</main>
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
	<script src="../../../resources/js/createPage.js"></script>
</body>
</html>