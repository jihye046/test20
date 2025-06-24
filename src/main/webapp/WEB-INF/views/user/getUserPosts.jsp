<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/getUserPosts.css" rel="styleSheet">
<script>
// 	var paging = ${paging.page}
// 	var searchGubun = "${param.searchGubun}"
// 	var searchText = "${param.searchText}"
//     var sortType = ("${param.sortType}" == "") ? "latest" : "${param.sortType}"
</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/loginInfo.jsp" %>
	<main class="my-posts-main">
		<div class="my-posts-container">
			<div class="page-title-section">
                <h2 class="page-title">내가 작성한 게시글</h2>
            </div>
            
            <div class="sort-buttons-section">
                <div class="sort-buttons-wrapper">
                	<button id="sort_latest" type="button" class="btn btn-sm btn-dark">최신순</button>
					<button id="sort_hit" type="button" class="btn btn-sm">조회순</button>
                </div>
            </div>
			
			<div class="posts-table-section">
				<div id="hitContainer" class="table-responsive">
					<table class="table my-posts-table">
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
							<c:forEach items="${getUserPosts}" var="dto">
								<tr>
									<td>${dto.bId}</td>
									<td>
										${dto.bName}
										
									</td>
									<td class="post-title-cell">
										<a href="/board/detailBoard?bId=${dto.bId}&
											bGroup=${dto.bGroup}&
											page=${paging.page}&
											userId=${sessionScope.userId}">
											${dto.bTitle}
										</a>
										<i class="fa-regular fa-comment-dots"></i> 
										<span class="commentCount">${dto.commentCount}</span>
									</td>
									<td>${dto.bDate}</td>
									<td>${dto.bHit}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>			
			
<!-- 			<nav class="pagination-section"> -->
<!-- 				<ul class="pagination justify-content-center custom-pagination"> -->
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${paging.page <= 1}"> --%>
<!-- 							<li class="page-item"></li> 내용 표시 x -->
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${paging.page - 3 <= 1}"> --%>
<!-- 									<li class="page-item"> -->
<%-- 										<a class="page-link" href="/board/paging?page=${paging.page-1}&sortType="> Previous </a> --%>
<!-- 									</li> -->
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<!-- 									<li class="page-item"> -->
<%-- 										<a class="page-link" href="/board/paging?page=${paging.page-3}&sortType="> Previous </a> --%>
<!-- 									</li> -->
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
<%-- 						</c:otherwise> --%>
<%-- 					</c:choose> --%>
<%-- 					<c:forEach begin="${paging.startPage}" end="${paging.endPage}" --%>
<%-- 						var="i" step="1"> --%>
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${i eq paging.page}"> --%>
<!-- 								<li class="page-item"> -->
<%-- 									<span class="page-link">${i}</span> --%>
<!-- 								</li> -->
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<!-- 								<li class="page-item"> -->
<%-- 									<a class="page-link" href="/board/paging?page=${i}&sortType=">${i}</a> --%>
<!-- 								</li> -->
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
<%-- 					</c:forEach> --%>
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${paging.page >= paging.maxPage}"> --%>
<!-- 							<li class="page-item"></li> 내용 표시 x -->
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${paging.page + 3 >= paging.maxPage}"> --%>
<!-- 									<li class="page-item"> -->
<%-- 										<a class="page-link" href="/board/paging?page=${paging.maxPage}&sortType=">Next</a> --%>
<!-- 									</li> -->
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<!-- 									<li class="page-item"> -->
<%-- 										<a class="page-link" href="/board/paging?page=${paging.page + 3}&sortType=">Next</a> --%>
<!-- 									</li> -->
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
<%-- 						</c:otherwise> --%>
<%-- 					</c:choose> --%>
<!-- 				</ul> -->
<!-- 			</nav> -->
		</div>
	</main>
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>

<div id="createResult" data-create-result="${createResult}"></div>
<div id="deleteResult" data-delete-result="${deleteResult}"></div>
<div id="userId" data-userId="${sessionScope.userId}"></div>
<div id="userNickname" data-userNickname="${sessionScope.userNickname}"></div>
<script src="../../../resources/js/pagingList.js"></script>
<script src="../../../resources/js/common.js"></script>

</html>