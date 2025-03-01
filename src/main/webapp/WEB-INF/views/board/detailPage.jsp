<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/detailPage.css" rel="styleSheet">
<link href="../../../resources/css/fixedButton.css" rel="styleSheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fancyapps/fancybox@3.5.0/dist/jquery.fancybox.min.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/include/loginInfo.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<!-- 포스트 상단 -->
				<div class="post-header">
					<div class="title-and-name">
						<h1>${dto.bTitle}</h1>
						<span class=bName>${dto.bName}</span>
						<button class="button-primary" type="button">팔로우</button>
					</div>
					<!-- 드롭다운 버튼 -->
					<div class="dropdown">
						<button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
				    		<i class="fa-solid fa-ellipsis-vertical" style="margin-left: 10px; color: #808080;"></i>
				    	</button>
						<div class="dropdown-menu dropdown-menu-right">
				    		<a class="dropdown-item" href="/board/updatePage?bId=${dto.bId}">수정</a>
				    		<a class="dropdown-item" href="/board/deleteBoard?bId=${dto.bId}" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
<!-- 				    		<a class="dropdown-item social-share-btn" href="javascript:shareNaver()">네이버 공유 -->
<!-- 					    		<span> -->
<!-- 									<script type="text/javascript" src="https://ssl.pstatic.net/share/js/naver_sharebutton.js"></script> -->
<!-- 									<script type="text/javascript">new ShareNaver.makeButton({"type": "b"});</script> -->
<!-- 								</span> -->
<!-- 							</a>	 -->
<!-- 							<a id="kakaotalk-sharing-btn" class="dropdown-item social-share-btn" href="javascript:shareKakao()">카카오 공유 -->
<!-- 								<img src="https://buly.kr/DPTKLrS" alt="kakao" style="width:42px; padding-left: 20px;"> -->
<!-- 							</a> -->
			    		</div>
			    	</div><!-- 드롭다운 end -->
				</div> <!-- 포스트 상단 end -->
				<hr>				
				<!-- 포스트 중단 -->	
				<div class="post">
					${dto.bContent}
					<div class="post-meta-div">
						<dl class="post-meta-dl">
							<span class="post-meta-item">${dto.bDate} </span>
							<dt class="post-meta-item like-button-container">
								좋아요
							</dt>
							<dd class="post-meta-item" id="totalLikes">${dto.bLike}</dd>
							<dt class="post-meta-item">스크랩</dt>
							<dd class="post-meta-item">0</dd>
							<dt class="post-meta-item">조회수</dt>
							<dd class="post-meta-item">${dto.bHit}</dd>
						</dl>
					</div>
					<hr id="postImageDivider" class="hidden-hr">
				</div> 
				<!-- 동적으로 이미지를  추가할 div -->
				<div class="image-container"></div> <!-- 포스트 중단 end -->
				<!-- 전체 이미지를 보여줄 모달 -->
				<div id="modal" class="modal">
					<div class="modal-content">
						<div class="modal-header">
							<span>사진 모아보기</span>
							<span class="close-button" id="closeModal">&times;</span>
						</div>
						
						<div class="modal-image-container">
							<!-- 이미지 영역 -->
						</div>
					</div>
				</div> 
				<!-- fixed button -->
				<div class="fixed-buttons">
					<button class="icon-button like ">
						<i class="fa-heart" style="color: #666666;"></i>
						<span class="count">${dto.bLike}</span>
					</button>			
						
					<button class="icon-button bookmark">
						<i class="fa-bookmark" style="color: #666666;"></i>
						<span class="count">${dto.bBookmark}</span>
					</button>
					
					<div class="share">
						<button class="icon-button share">
							<i class="fa-regular fa-share-from-square" style="color: #666666;"></i>
						</button>
					
					<!-- 공유 옵션 -->
						<div class="share-option-div">
							<button class="share-option naver" onclick="shareNaver()">
					    		<span>
									<script type="text/javascript" src="https://ssl.pstatic.net/share/js/naver_sharebutton.js"></script>
									<script type="text/javascript">new ShareNaver.makeButton({"type": "b"});</script>
								</span>
							</button>
							<button class="share-option kakaoTalk" onclick="shareKakao()">
								<img src="https://buly.kr/DPTKLrS" alt="kakao" style="width:20px;">
							</button>
						</div>
					</div>
					<!-- 공유 옵션 end -->

					<button class="icon-button link">
						<i class="fa-solid fa-link" style="color: #666666;"></i>
					</button>
				</div> <!-- fixed button end -->
				<!-- 댓글 -->
				<hr>
				<div class="reply-form">
					<div class="commentCount-div">
						<span>댓글</span><span class="commentCount">${dto.commentCount}</span>
					</div>
					<div class="comment-input-container">
						<input class="styled-input" type="text" id="comment-input" placeholder="댓글" required>
						<button class="submit-comment button-filled-primary" type="button" id="commentBtn">댓글</button>
					</div>
					<div class="comments">
						<div class="comments-sortButton-container">
							<span class="comments-sortButton" id="commnts_sort_like">추천순</span>
							<span class="comments-sortButton" id="commnts_sort_latest">최신순</span>
						</div>
						<div>
							<c:forEach items="${commentsPagingList}" var="comment">
								<c:if test="${comment.bIndent == 1}">
									<article>
										<img id="profile-photo" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
										<span class="author-name"><a href="#">${comment.bName}</a></span>
									    <p class="post-content">${comment.bContent}</p>
									    <time class="post-time">${comment.bDate}</time>
									    <button type="button" class="button-filled-primary comment-child-btn" 
									    					  data-bGroup="${comment.bGroup}"
									    					  data-bStep="${comment.bStep}"
									    					  data-bIndent="${comment.bIndent}">
									    	답글 달기
									    </button>
									    <button type="button" class="button-filled-primary" id="thumbupButton" data-recommend-bId="${comment.bId}">
											<i class="${comment.recommended ? 'fa-solid fa-thumbs-up' : 'fa-regular fa-thumbs-up'}"></i>
									    	<span class="total-Recommendation">${comment.bLike}</span>
								    	</button>
								    </article>	
								</c:if>
								<c:if test="${comment.bIndent != 1}">
									<article class="comment-child">
										<img id="profile-photo comment-child" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
										<h4 class="author-name comment-child"><a href="#">${comment.bName}</a></h4>
									    <p class="post-content comment-child">${comment.bContent}</p>
										<time class="post-time comment-child">${comment.bDate}</time>
										<button type="button" class="button-filled-primary comment-child"  id="thumbupButton" data-recommend-bId="${comment.bId}">
											<i class="${comment.recommended ? 'fa-solid fa-thumbs-up' : 'fa-regular fa-thumbs-up'}"></i>
									    	<span class="total-Recommendation">${comment.bLike}</span>
							    		</button>
								    </article>
								</c:if>
						    </c:forEach>
						    <!-- paging -->
							<nav>
								<ul class="pagination justify-content-center">
									<!-- Previous 버튼 -->
									<c:choose>
										<c:when test="${commentsPaging.page <= 3}">
											<li class="page-item"></li> <!-- < 버튼 표시 x -->
										</c:when>
										<c:otherwise>
													<li class="page-item">
														<a class="page-link" href="#" data-bGroup="${dto.bGroup}" data-page="${commentsPaging.page-3}" data-sortType=""> < </a>
													</li>
										</c:otherwise>
									</c:choose> 
									<c:forEach begin="${commentsPaging.startPage}" end="${commentsPaging.endPage}"
										var="i" step="1">
										<c:choose>
											<c:when test="${i eq commentsPaging.page}">
												<li class="page-item">
													<span class="page-link" style="pointer-events: none;">${i}</span>
												</li>
											</c:when>
											<c:otherwise>
												<li class="page-item">
													<a class="page-link" href="#" data-bGroup="${dto.bGroup}" data-page="${i}" data-sortType="">${i}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach> 
									<c:choose>
										<c:when test="${commentsPaging.maxPage <= (commentsPaging.page+3) || commentsPaging.page >= commentsPaging.maxPage}">
											<li class="page-item"></li>
										</c:when>
										<c:otherwise>
													<li class="page-item">
														<a class="page-link" href="#" data-bGroup="${dto.bGroup}" data-page="${commentsPaging.page + 3}" data-sortType=""> > </a>
													</li>
										</c:otherwise>
									</c:choose>
								</ul>
							</nav>
							<!-- paging end -->
						</div>
					</div>
				 </div> <!-- 댓글 end -->
				 <div class="copy-message" id="copyMessage">링크가 복사되었습니다.</div>
			</div> <!-- col-md-12 end -->
		</div> <!-- row end -->
	</div> <!-- container-fluid end -->
	
	<!-- 카카오톡 공유 임시 DOM -->
	<div id="tempDiv"style="display: none;" ></div>
	<!-- fancybox 이미지 갤러리 -->
	<script src="https://cdn.jsdelivr.net/npm/@fancyapps/fancybox@3.5.0/dist/jquery.fancybox.min.js"></script>	
</body>

<div id="updateResult" data-update-result="${updateResult}"></div>
<div id="bId" data-bId="${dto.bId}"></div>
<div id="bTitle" data-bTitle="${dto.bTitle}"></div>
<div id="bContent" data-bContent='${dto.bContent}'></div>
<div id="isLiked" data-isLiked="${isLiked}"></div>
<div id="isBookmarked" data-isBookmarked="${isBookmarked}"></div>

<script src="../../../resources/js/common.js"></script>
<script src="../../../resources/js/fixedButton.js"></script>
<script src="../../../resources/js/detailPage.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script> <!-- 카카오 공유 -->

<script>
/* 댓글 리스너, 페이징
================================================== */
const replyBtn = document.querySelector("#commentBtn")
const replyTable = document.querySelector(".comments")
const replyInput = document.querySelector("#comment-input")
const COMMENTS_PAGE_LIMIT = 6
const COMMENTS_BLOCK_LIMIT = 3
let currentPage = 1

	// 현재 페이지 블록
const setPage = (page) => {
	currentPage = page
}

	// 댓글 개수UI 업데이트
const editCommentCount = (commentCount) => {
	const commentCountDiv = document.querySelector(".commentCount-div")
	const commentCountSpan = commentCountDiv.querySelector(".commentCount")
	commentCountSpan.textContent = commentCount
}
	
	// 댓글UI 업데이트
const editCommentTable = (replyList) => {
	let output = `
		<div class="comments-sortButton-container">
			<span class="comments-sortButton" id="commnts_sort_like">추천순</span>
			<span class="comments-sortButton" id="commnts_sort_latest">최신순</span>
		</div>	
		<div>`
		for(let i in replyList){
			if(replyList[i].bIndent == 1){ // 댓글
			output += `<article>
							<img id="profile-photo" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
							<span class="author-name"><a href="#">\${replyList[i].bName}</a></span>
					    	<p class="post-content">\${replyList[i].bContent}</p>
					    	<time class="post-time">\${replyList[i].bDate}</time>
					    	<button type="button" class="button-filled-primary comment-child-btn" 
					    						  data-bGroup="\${replyList[i].bGroup}"
					    						  data-bStep="\${replyList[i].bStep}"
					    						  data-bIndent="\${replyList[i].bIndent}">
					    		답글 달기
					    	</button>
				    		<button type="button" class="button-filled-primary"  id="thumbupButton" data-recommend-bId="\${replyList[i].bId}">
						    	<i class="\${replyList[i].recommended ? 'fa-solid fa-thumbs-up' : 'fa-regular fa-thumbs-up'}" ></i>
						    	<span class="total-Recommendation">\${replyList[i].bLike}</span>
					    	</button>
				       </article>`
			   		  
			} else { // 답글
				output += `<article class="comment-child">
								<img id="profile-photo comment-child" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
								<h4 class="author-name comment-child"><a href="#">\${replyList[i].bName}</a></h4>
							    <p class="post-content comment-child">\${replyList[i].bContent}</p>
							    <time class="post-time comment-child">\${replyList[i].bDate}</time>
							    <button type="button" class="button-filled-primary comment-child"  id="thumbupButton" data-recommend-bId="\${replyList[i].bId}">
							    	<i class="\${replyList[i].recommended ? 'fa-solid fa-thumbs-up' : 'fa-regular fa-thumbs-up'}"></i>
							    	<span class="total-Recommendation">\${replyList[i].bLike}</span>
					    		</button>
							</article>`
			}
		}
	output += `</div>`
	return output
}

	// 답글UI 업데이트
const replyChildUI = () => {
	let output = `<div class="comment-input-container commentCell">
					<input type="text" name="bContent" class="styled-input" required>
					<button class="submit-comment button-filled-primary" type="button" id="commentChildSendBtn">작성</button>
				  </div>`
	return output
}

	// 페이지네이션 블록UI 업데이트
const updatePagingBlock = (dto, commentsPaging) => {
	let output = `<nav><ul class="pagination justify-content-center">`
		
	// Previous 버튼
	if(commentsPaging.page <= COMMENTS_BLOCK_LIMIT) {
		output += `<li class="page-item"></li>`
	} else {
		let previousPageLink = `<a class="page-link" href="#" data-bGroup="${dto.bGroup}" data-page="\${commentsPaging.page - COMMENTS_BLOCK_LIMIT}" data-sortType=""> < </a>`
		output += `<li class="page-item">\${previousPageLink}</li>`
	}
	
	// 페이지 블록 번호
	for(let i = commentsPaging.startPage; i <= commentsPaging.endPage; i++){
	    let pagingLink = (i == commentsPaging.page) ? 
	    	`<span class="page-link" style="pointer-events: none;">\${i}</span>` :
	    	`<a class="page-link" href="#" data-bGroup="${dto.bGroup}" data-page="\${i}" data-sortType="">\${i}</a>`
		        
		    output += `<li class="page-item">\${pagingLink}</li>`
	}
		    
	// Next 버튼
	if(commentsPaging.page >= commentsPaging.maxPage){
		output += `<li class="page-item"></li>`
	} else {
		let nextPageLink = (commentsPaging.page + COMMENTS_BLOCK_LIMIT >= commentsPaging.maxPage) ? 
			`<a class="page-link" href="#" data-bGroup="${dto.bGroup}" data-page="\${commentsPaging.maxPage}" data-sortType=""> > </a>` :
			`<a class="page-link" href="#" data-bGroup="${dto.bGroup}" data-page="\${commentsPaging.page + COMMENTS_BLOCK_LIMIT}" data-sortType=""> > </a>`
		output += `<li class="page-item">\${nextPageLink}</li>`
	} 
	
	return output
}

	// 댓글 블록 링크 리스너
const ajaxBlockLink  = () => {
	document.querySelectorAll('a.page-link').forEach(function(link){
		link.addEventListener('click', function(){
			event.preventDefault()
			
			const bGroup = link.getAttribute('data-bGroup')
			const pageNum = link.getAttribute('data-page')
			setPage(pageNum)
			const sortType = link.getAttribute('data-sortType')
			
			$.ajax({
				url: "/board/commentsPaging/ajax",
				data: {
					bGroup: `\${bGroup}`,
					page: `\${pageNum}`,
					sortType: `\${sortType}`
				},
				success: function(data){
					replyInput.value = '' 
					replyTable.innerHTML = ''
					let dto = data["commentsPagingList"];
			        let commentsPaging = data["commentsPagingDto"];
			        
			        // UI 업데이트
			        let output = editCommentTable(dto) // 댓글
					output += updatePagingBlock(dto, commentsPaging) // 페이징 블록
					replyTable.innerHTML = output
					
					// 리스너 재등록
					ajaxBlockLink() // 댓글 블록 링크
					registerEventListeners() // 답글
					handleRecommendation() // 추천 버튼
				},
				error: function(error){
					console.log(error)
				} 
			})
		})
	})
}

	// 댓글 버튼 리스너
replyBtn.addEventListener('click', function(){
	let replyInputValue = replyInput.value
	if(!replyInputValue || !(replyInputValue.trim())){
		alert("내용을 입력해주세요.")
		return
	} else {
		$.ajax({
			type: "post",
			url: "/board/replyInsert",
			data: {
				page: currentPage,
				bId: "${dto.bId}",
				bContent: replyInputValue,
				bGroup: "${dto.bGroup}",
				bStep: "${dto.bStep}",
				bIndent: "${dto.bIndent}"
			},
			dataType: "json",
			success: function(data){
				replyInput.value = '' 
				replyTable.innerHTML = ''
				let dto = data.commentsListResponse.commentsPagingList
				let commentsPaging = data.commentsListResponse.commentsPagingDto
				let commentsCount = data.commentsCount // dto에 set안했음. dto.commentCount값으로 확인하지말고 map에 담긴걸로 확인 
				
				// UI 업데이트
				let output = editCommentTable(dto)
				output += updatePagingBlock(dto, commentsPaging)
				replyTable.innerHTML = output
				editCommentCount(commentsCount)
				
				// 리스너 재등록
				ajaxBlockLink()
				registerEventListeners()
				handleRecommendation()
			},
			error: function(){
				console.log("error")
			}
		})
	}
})

	// 답글 버튼 리스너
function registerEventListeners(){
	const childReplyBtns = document.querySelectorAll(".comment-child-btn")
	childReplyBtns.forEach(childReplyBtn => { 
	    childReplyBtn.addEventListener('click', function(){
	        
	        // 다른 답글 창이 열려있다면 닫기
	        const openReplyCells = document.querySelectorAll('.commentCell');
			openReplyCells.forEach(cell => {
			    cell.parentNode.removeChild(cell);
			})

	        const article = this.closest('article') // 클릭된 버튼이 속한 행 찾기
	        const bGroup = this.getAttribute('data-bGroup')
	        const bStep = this.getAttribute('data-bStep')	
	        const bIndent = this.getAttribute('data-bIndent')
	        
	        // 새로운 입력 필드 추가
            const output = replyChildUI()
            // 행의 끝에 새로운 입력 필드 추가
            article.insertAdjacentHTML('afterend', output);
            
        	const sendButton = article.nextElementSibling.querySelector('button');
            sendButton.addEventListener('click', function() {
            	const bContent = article.nextElementSibling.querySelector('input').value // 입력된 내용 가져오기
            	if(!bContent || !(bContent.trim())){
            		alert("내용을 입력해주세요.")
            		return
            	} else {
            		$.ajax({
                    	type: "post",
                    	url: "replyChildInsert",
                    	data: {
                    		page: currentPage,
                    		bContent: bContent,
                    		bGroup: bGroup,
                    		bStep: bStep,
                    		bIndent: bIndent
                    	},
                    	dataType: "json",
                    	success: function(data){
                    		replyInput.value = ''
                   			replyTable.innerHTML = ''
               				let dto = data.commentsListResponse.commentsPagingList
              				let commentsPaging = data.commentsListResponse.commentsPagingDto
              				let commentsCount = data.commentsCount
                    		
                    		// UI 업데이트
                    		let output = editCommentTable(dto)
                    		output += updatePagingBlock(dto, commentsPaging)
                   			replyTable.innerHTML = output
                   			editCommentCount(commentsCount)
                   			
                   			// 리스너 재등록
                   			ajaxBlockLink()
                   			registerEventListeners()
							handleRecommendation()
                    	},
                    	error: function(){
                    		console.log("error")
                    	}
                    })
            	}
            	article.nextElementSibling.remove() // 답글 창 닫기
            })
	    })
	})
}
	
/* 댓글 추천순
================================================== */
const addRecommend = (bId, icon, totalRecommendation) => {
	fetch('/board/addRecommend', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		}, 
		body: JSON.stringify({
			bId: bId
		})
	})
	.then((response) => response.json())
	.then((data) => {
		icon.classList.remove('fa-regular')
		icon.classList.add('fa-solid')
		totalRecommendation.textContent = data
	})
	.catch(error => {
		console.log('error: ', error)
	})
}

const removeRecommend = (bId, icon, totalRecommendation) => {
	fetch('/board/removeRecommend', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		}, 
		body: JSON.stringify({
			bId: bId
		})
	})
	.then((response) => response.json())
	.then((data) => {
		icon.classList.remove('fa-solid')
		icon.classList.add('fa-regular')
		totalRecommendation.textContent = data
	})
	.catch(error => {
		console.log('error: ', error)
	})
}

const handleRecommendation = () => {
	document.querySelectorAll('#thumbupButton').forEach(thumbupBtn => {
		thumbupBtn.addEventListener('click', function() {
			const icon = thumbupBtn.querySelector('i')
			const bId = thumbupBtn.getAttribute('data-recommend-bId')
			const totalRecommendation = thumbupBtn.querySelector('.total-Recommendation')

			if(icon.classList.contains('fa-regular')) {
				addRecommend(bId, icon, totalRecommendation)
			} else if(icon.classList.contains('fa-solid')) {
				removeRecommend(bId, icon, totalRecommendation)
			}
		})
	})
}


/* 페이지 로드 시 실행될 함수
================================================== */
registerEventListeners()
ajaxBlockLink()
handleRecommendation()
</script>
</html>