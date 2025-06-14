<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/updatePage.css" rel="styleSheet">
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
        			<div class="col-md-8 updatePage-main">
			            <form action="/board/updateBoard" method="post" class="update-form"> <!-- 수정된 부분 -->
			                <input type="hidden" name="bId" value="${dto.bId}">
			                <input type="hidden" name="bGroup" value="${dto.bGroup}">
			                <div>
			                	* 이름 <input type="text" name="bName" value="${dto.bName}" readonly>
			                </div>
			                <div>
			                	* 제목 <input type="text" name="bTitle" value="${dto.bTitle}">
			                </div>
			                <textarea rows="10" cols="40" name="bContent" id="editor">${dto.bContent}</textarea>
			                <!-- map -->
							<div class="search-box">
								<input type="text" id="inputAdd" name="bAddress" value="${dto.bAddress}" readonly>
								<input type="button" onclick="searchedAdd()" value="주소 검색"><br>
							</div>
			                <button class="btn btn-outline-info" type="submit" id="updateBtn">완료</button>
			            </form>
		            </div>
	            <div class="col-md-2"></div>
            </div>
        </div>
    </main>
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
	<script src="../../../resources/js/uploadAdapter.js"></script>
	<script src="../../../resources/js/updatePage.js"></script>
</body>
</html>