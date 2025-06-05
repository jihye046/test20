<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/list.css" rel="styleSheet">
<link href="../../../resources/css/common.css" rel="styleSheet">
<script>
	var paging = ${paging.page}
	var searchGubun = "${param.searchGubun}"
	var searchText = "${param.searchText}"
    var sortType = ("${param.sortType}" == "") ? "latest" : "${param.sortType}"
<%--     var sortType = "<%= (request.getParameter("sortType") == null) ? --%>
<%--     				"latest" : request.getParameter("sortType") %>"  --%>
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/loginInfo.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
<!-- 					<div class="col-md-7"></div> -->
<!-- 					<div class="col-md-5 d-flex"> -->
						<div class="form-and-buttons-container">
							<div class="buttons-container">
								<button class="styled-button" id="sort_latest" type="button" class="btn btn-sm btn-dark">최신순</button>
								<button class="styled-button" id="sort_hit" type="button" class="btn btn-sm">조회순</button>
							</div>
							<div class="form-container">
								<form action="/board/paging">
									<input type="hidden" name="sortType" id="sortTypeInput">
									<select class="styled-select" name="searchGubun">
										<option value="bTitle">제목</option>
										<option value="bContent">내용</option>
										<option value="bName">작성자</option>
									</select> 
									<input class="styled-input" type="text" name="searchText" placeholder="검색">
	<!-- 								<button type="submit" class="btn">검색</button> -->
								</form>
							</div>
						</div>
<!-- 					</div> -->
				</div>
			</div>
			<div class="col-md-12">
				<div id="hitContainer">
					<!-- card -->
					<div class="card-container">
						<c:forEach items="${boardList}" var="dto">
							<figure class="snip1518 hover">
								<div class="image" data-content="${dto.bContent}">
									<!-- 이미지가 없는 게시글이면 흰색 배경 기본 -->
								</div>
								<figcaption>
									<div class="post-bName">${dto.bName}</div>
									<div class="post-bTitle">${dto.bTitle}</div>
									<footer>
										<div class="create-date">${dto.bDate}</div>
										<div class="icons">
											<div class="views"><i class="ion-eye"></i>${dto.bHit}</div>
											<div class="love"><i class="ion-heart"></i>${dto.bLike}</div>
											<div class="comment"><i class="fa-thin fa-comment fa-2xs"></i>${dto.commentCount}</div>
										</div>
									</footer>
								</figcaption>
								<a href="/board/detailBoard?bId=${dto.bId}&bGroup=${dto.bGroup}&page=${paging.page}&userId=${sessionScope.userId}"></a>
							</figure>
						</c:forEach>
					</div>
					<!-- card end-->
					
				</div>
				<!-- paging -->
				<nav>
					<ul class="pagination justify-content-center">
						<c:choose>
							<c:when test="${paging.page <= 1}">
								<li class="page-item"></li> <!-- 내용 표시 x -->
							</c:when>
							<c:otherwise>
								<c:choose>
<%-- 									<c:when test="${paging.page - 3 <= 1}"> --%>
									<c:when test="${paging.page - 5 <= 1}">
										<li class="page-item">
											<a class="page-link" href="/board/paging?page=${paging.page-1}&sortType="> < </a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" href="/board/paging?page=${paging.page-5}&sortType="> < </a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
							var="i" step="1">
							<c:choose>
								<c:when test="${i eq paging.page}">
									<li class="page-item">
										<span class="page-link" style="background-color: #ad9f94; pointer-events: none;">${i}</span>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
										<a class="page-link" href="/board/paging?page=${i}&sortType=">${i}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${paging.page >= paging.maxPage}">
								<li class="page-item"></li> <!-- 내용 표시 x -->
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${paging.page + 5 >= paging.maxPage}">
										<li class="page-item">
											<a class="page-link" href="/board/paging?page=${paging.maxPage}&sortType="> > </a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" href="/board/paging?page=${paging.page + 5}&sortType="> > </a>
										</li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>
				<!-- paging end -->
			</div>
		</div>
	</div>
	<!-- 채팅아이콘 -->
	<%@ include file="/WEB-INF/views/include/chatManage.jsp" %>
<%-- 	<%@ include file="/WEB-INF/views/include/footer.jsp" %> --%>
</body>
<div id="createResult" data-create-result="${createResult}"></div>
<div id="deleteResult" data-delete-result="${deleteResult}"></div>
<div id="userId" data-userId="${sessionScope.userId}"></div>
<div id="userNickname" data-userNickname="${sessionScope.userNickname}"></div>
<script src="../../../resources/js/pagingList.js"></script>
<script src="../../../resources/js/common.js"></script>
</html>