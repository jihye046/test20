<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/detailPage.css" rel="styleSheet">
<link href="../../../resources/css/fixedButton.css" rel="styleSheet">
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
					<!-- 사용자가 올린 사진 list -->
					<div class="image-container">
						<button class="image-button">
							<img alt="image1" src=""/>	
						</button>
						<button class="image-button">
							<img alt="image2" src=""/>	
						</button>
						<button class="image-button">
							<img alt="image3" src=""/>	
						</button>
						<button class="image-button">
							<img alt="image4" src=""/>	
						</button>
						<button class="image-button">
							<img alt="image5" src=""/>
							<span class="more-text">+ 더보기</span>	
						</button>
						
					</div>
					<!-- 포스트 중단 end -->
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
						<span>댓글</span> <span class="commentCount">${dto.commentCount}</span>
					</div>
					<div class="comment-input-container">
						<input class="styled-input" type="text" id="comment-input" placeholder="댓글" required>
						<button class="submit-comment button-filled-primary" type="button" id="commentBtn">댓글</button>
					</div>
					<div class="comments">
						<div>
							<c:forEach items="${replyList}" var="comment">
								<c:if test="${comment.bIndent == 1}">
									<article>
										<img id="profile-photo" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
										<h4 class="author-name"><a href="#">${comment.bName}</a></h4>
										<time class="post-time">${comment.bDate}</time>
									    <p class="post-content">${comment.bContent}</p>
									    <button type="button" class="button-filled-primary comment-child-btn" 
									    					  data-bGroup="${comment.bGroup}"
									    					  data-bStep="${comment.bStep}"
									    					  data-bIndent="${comment.bIndent}">
									    	답글
									    </button>
								    </article>	
								</c:if>
								<c:if test="${comment.bIndent != 1}">
									<article class="comment-child">
										<img id="profile-photo comment-child" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
										<h4 class="author-name comment-child"><a href="#">${comment.bName}</a></h4>
										<time class="post-time comment-child">${comment.bDate}</time>
									    <p class="post-content comment-child">${comment.bContent}</p>
								    </article>
								</c:if>
						    </c:forEach>
						</div>
					</div>
				 </div>
				 <div class="copy-message" id="copyMessage">링크가 복사되었습니다.</div>
			</div> <!-- col-md-12 end -->
		</div> <!-- row end -->
	</div> <!-- container-fluid end -->
	
	<!-- 카카오톡 공유 임시 DOM -->
	<div id="tempDiv"style="display: none;" ></div>
</body>

<div id="updateResult" data-update-result="${updateResult}"></div>
<div id="bId" data-bId="${dto.bId}"></div>
<div id="bTitle" data-bTitle="${dto.bTitle}"></div>
<div id="bContent" data-bContent='${dto.bContent}'></div>
<div id="isLiked" data-isLiked="${isLiked}"></div>
<div id="isBookmarked" data-isBookmarked="${isBookmarked}"></div>

<script src="../../../resources/js/common.js"></script>
<script src="../../../resources/js/detailPage.js"></script>
<script src="../../../resources/js/fixedButton.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script> <!-- 카카오 공유 -->

<script>
// 댓글
const replyBtn = document.querySelector("#commentBtn")
const replyTable = document.querySelector(".comments")
const replyInput = document.querySelector("#comment-input")

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
				bId: "${dto.bId}",
				bContent: replyInputValue,
				bGroup: "${dto.bGroup}",
				bStep: "${dto.bStep}",
				bIndent: "${dto.bIndent}"
			},
			dataType: "json",
			success: function(replyList){
				replyInput.value = '' // replyInputValue = ''으로 하면 안됨
				replyTable.innerHTML = ''
				const output = editReplyTable(replyList)
				replyTable.innerHTML = output
				registerEventListeners()
			},
			error: function(){
				console.log("error")
			}
		})
	}
})

const editReplyTable = (replyList) => {
	let output = `<div>`
		for(let i in replyList){
			if(replyList[i].bIndent == 1){ // 댓글
			output += `<article>
							<img id="profile-photo" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
							<h4 class="author-name"><a href="#">\${replyList[i].bName}</a></h4>
							<time class="post-time">\${replyList[i].bDate}</time>
					    	<p class="post-content">\${replyList[i].bContent}</p>
					    	<button type="button" class="button-filled-primary comment-child-btn" 
					    						  data-bGroup="\${replyList[i].bGroup}"
					    						  data-bStep="\${replyList[i].bStep}"
					    						  data-bIndent="\${replyList[i].bIndent}">
					    		답글
					    	</button>	
				       </article>`
			   		  
			} else { // 답글
				output += `<article class="comment-child">
								<img id="profile-photo comment-child" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
								<h4 class="author-name comment-child"><a href="#">\${replyList[i].bName}</a></h4>
								<time class="post-time comment-child">\${replyList[i].bDate}</time>
							    <p class="post-content comment-child">\${replyList[i].bContent}</p>
							</article>`
			}
		}
	output += `</div>`
	return output
}

// 답글 버튼을 눌렀을 때
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
                    		bContent: bContent,
                    		bGroup: bGroup,
                    		bStep: bStep,
                    		bIndent: bIndent
                    	},
                    	dataType: "json",
                    	success: function(replyList){
                    		replyInput.value = ''
                   			replyTable.innerHTML = ''
                   			const output = editReplyTable(replyList)
                   			replyTable.innerHTML = output
                   			registerEventListeners()
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

// 답글 작성 UI
const replyChildUI = () => {
	let output = `<div class="comment-input-container commentCell">
					<input type="text" name="bContent" class="styled-input" required>
					<button class="submit-comment button-filled-primary" type="button" id="commentChildSendBtn">작성</button>
				  </div>`
	return output
}

registerEventListeners()
</script>
</html>