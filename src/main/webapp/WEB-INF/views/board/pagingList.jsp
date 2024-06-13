<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/list.css" rel="styleSheet">
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
					<div class="col-md-6"></div>
					<div class="col-md-6 d-flex">
						<div>
							<form action="/board/paging">
								<input type="hidden" name="sortType" id="sortTypeInput">
								<select name="searchGubun">
									<option value="bTitle">제목</option>
									<option value="bContent">내용</option>
									<option value="bName">작성자</option>
								</select> <input type="text" name="searchText">
								<button type="submit" class="btn">검색</button>
							</form>
						</div>
						<div>
							<button id="sort_latest" type="button" class="btn btn-sm btn-dark">최신순</button>
							<button id="sort_hit" type="button" class="btn btn-sm">조회순</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div id="hitContainer">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>No.</th>
								<th>작성자</th>
								<th>제목</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${boardList}" var="dto">
								<tr>
									<td>${dto.bId}</td>
									<td>
										<a href="/board/detailBoard?bId=${dto.bId}&bGroup=${dto.bGroup}&page=${paging.page}&userId=${sessionScope.userId}">${dto.bName}</a>
										<i class="fa-regular fa-comment-dots"></i> 
										<span class="commentCount">${dto.commentCount}</span>
									</td>
									<td>${dto.bTitle}</td>
									<td>${dto.bDate}</td>
									<td>${dto.bHit}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
					<nav>
						<ul class="pagination justify-content-center">
							<c:choose>
								<c:when test="${paging.page <= 1}">
									<li class="page-item"></li> <!-- 내용 표시 x -->
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${paging.page - 3 <= 1}">
											<li class="page-item">
												<a class="page-link" href="/board/paging?page=${paging.page-1}&sortType="> Previous </a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="page-item">
												<a class="page-link" href="/board/paging?page=${paging.page-3}&sortType="> Previous </a>
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
											<span class="page-link">${i}</span>
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
										<c:when test="${paging.page + 3 >= paging.maxPage}">
											<li class="page-item">
												<a class="page-link" href="/board/paging?page=${paging.maxPage}&sortType=">Next</a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="page-item">
												<a class="page-link" href="/board/paging?page=${paging.page + 3}&sortType=">Next</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
<div id="createResult" data-create-result="${createResult}"></div>
<div id="deleteResult" data-delete-result="${deleteResult}"></div>
<div id="userId" data-userId="${sessionScope.userId}"></div>
<div id="userNickname" data-userNickname="${sessionScope.userNickname}"></div>
<script src="../../../resources/js/pagingList.js"></script>
</html>