<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<link href="../../../resources/css/detailPage.css" rel="styleSheet">
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
				    		<a class="dropdown-item social-share-btn" href="javascript:shareNaver()">네이버 공유
					    		<span>
									<script type="text/javascript" src="https://ssl.pstatic.net/share/js/naver_sharebutton.js"></script>
									<script type="text/javascript">new ShareNaver.makeButton({"type": "b"});</script>
								</span>
							</a>	
							<a id="kakaotalk-sharing-btn" class="dropdown-item social-share-btn" href="javascript:shareKakao()">카카오 공유
								<img src="https://buly.kr/DPTKLrS" alt="kakao" style="width:42px; padding-left: 20px;">
							</a>
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
								<img id="like-button" alt="unlike" src="../../../resources/images/unliked.png">좋아요
							</dt>
							<dd class="post-meta-item" id="totalLikes">${dto.bLike}</dd>
							<dt class="post-meta-item">스크랩</dt>
							<dd class="post-meta-item">0</dd>
							<dt class="post-meta-item">조회수</dt>
							<dd class="post-meta-item">${dto.bHit}</dd>
						</dl>
					</div> 
				</div> <!-- 포스트 중단 end -->
				
<!-- 				<div> 포스트 하단 -->
				<!-- 새로운 댓글 -->
				<div class="reply-form">
					<div class="comment-input-container">
						<input class="styled-input" type="text" id="comment-input" placeholder="댓글" required>
						<button class="submit-comment button-filled-primary" type="button" id="commentBtn">댓글</button>
					</div>
					<div class="comments">
						<article>
							<c:forEach items="${replyList}" var="comment">
								<c:if test="${comment.bIndent == 1}">
									<img id="profile-photo" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
									<h4 class="author-name"><a href="#">${comment.bName}</a></h4>
									<time class="post-time">${comment.bDate}</time>
								    <p class="post-content">${comment.bContent}</p>
								    <button type="button" class="button-filled-primary comment-child-btn" data-bGroup="${comment.bGroup}"
								    													data-bStep="${comment.bStep}"
								    													data-bIndent="${comment.bIndent}">
								    	답글
								    </button>	
								</c:if>
								<c:if test="${comment.bIndent != 1}">
									<img id="profile-photo comment-child" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
									<h4 class="author-name comment-child"><a href="#">${comment.bName}</a></h4>
									<time class="post-time comment-child">${comment.bDate}</time>
								    <p class="post-content comment-child">${comment.bContent}</p>
								</c:if>
						    </c:forEach>
						</article>
					</div>
				 </div>
					
					<!-- 댓글 -->
<!-- 					<div class="reply-section"> -->
<!-- 						<div> -->
<!-- 							<input type="text" size="60" placeholder="댓글을 달아보세요" id="replyInput"> -->
<!-- 							<button class="btn btn-secondary" type="button" id="replyBtn">댓글</button> -->
<!-- 						</div> -->
<!-- 						<div class="reply-form"> -->
<!-- 							<table id="replyTable"> -->
<!-- 								<tr id="replyTitle"> -->
<!-- 									<th>bId</th> -->
<!-- 									<th>bContent</th> -->
<!-- 									<th>date</th> -->
<!-- 									<th></th> -->
<!-- 								</tr> -->
<%-- 								<c:forEach items="${replyList}" var="reply"> --%>
<!-- 									<tr id="replyContent"> -->
<%-- 										<c:if test="${reply.bIndent == 1}"> <!-- 댓글인 경우 --> --%>
<%-- 											<td>${reply.bId}</td> --%>
<%-- 											<td>${reply.bContent}</td> --%>
<%-- 											<td>${reply.bDate}</td> --%>
<!-- 											<td> -->
<!-- 												<button type="button" class="btn btn-secondary childReplyBtn" -->
<%-- 													    data-bGroup="${reply.bGroup}" --%>
<%-- 													    data-bStep="${reply.bStep}" --%>
<%-- 													    data-bIndent="${reply.bIndent}"> --%>
<!-- 												     답글 -->
<!-- 									     	    </button> -->
<!-- 							     	   		</td> -->
<%-- 							     	    </c:if> --%>
<%-- 							     	    <c:if test="${reply.bIndent != 1}"> --%>
<%-- 							     	    	<td class="replyChild-td">${reply.bId}</td> --%>
<%-- 											<td class="replyChild-td"><strong>@${reply.bGroup}</strong>&nbsp;${reply.bContent}</td> --%>
<%-- 											<td class="replyChild-td">${reply.bDate}</td> --%>
<!-- 											<td class="replyChild-td"></td> -->
<%-- 							     	    </c:if> --%>
<!-- 									</tr> -->
<%-- 								</c:forEach> --%>
<!-- 							</table> -->
<!-- 						</div> -->
<!-- 					</div>  -->
					<!-- 댓글 end -->
<!-- 				</div> 포스트 하단 end -->
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

<script src="../../../resources/js/common.js"></script>
<script src="../../../resources/js/detailPage.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script> <!-- 카카오 공유 -->

<script>
// 댓글
const replyBtn = document.querySelector("#commentBtn")
// const replyTable = document.querySelector("#replyTable")
const replyTable = document.querySelector(".reply-form") // jh
// const replyInput = document.querySelector("#replyInput")
const replyInput = document.querySelector("#comment-input") // jh

replyBtn.addEventListener('click', function(){
	let replyInputValue = replyInput.value
	
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
})

const editReplyTable = (replyList) => {
	/*
	let output = `<table id="replyTable">
					  <tr>
				  		  <th>bId</th>
						  <th>bContent</th>
						  <th>bDate</th>
						  <th></th>
					  </tr>`
  	for(let i in replyList){
  		if(replyList[i].bIndent == 1){
	  		output += `<tr>
	  					  <td>\${replyList[i].bId}</td>
	  					  <td>\${replyList[i].bContent}</td>
	  					  <td>\${replyList[i].bDate}</td>
	  					  <td>
	  					  	  <button type="button" class="btn btn-secondary childReplyBtn" 
	  					  			  data-bGroup="\${replyList[i].bGroup}"
	  					  			  data-bStep="\${replyList[i].bStep}"
	  					  			  data-bIndent="\${replyList[i].bIndent}">
					  			   답글
		  			   		  </button>
		  			   	  </td>
	  				   </tr>`
  		} else {
  			output += `<tr>
						  <td class="replyChild-td">\${replyList[i].bId}</td>
						  <td class="replyChild-td"><strong>@\${replyList[i].bGroup}</strong>&nbsp;\${replyList[i].bContent}</td>
						  <td class="replyChild-td">\${replyList[i].bDate}</td>
						  <td class="replyChild-td"></td>
					   </tr>`
  		}
  	}
		output += `</table>`
	*/
	let output = `<table id="replyTable">
				  <tr>
			  		  <th>bId</th>
					  <th>bContent</th>
					  <th>bDate</th>
					  <th></th>
				  </tr>`
				  
		for(let i in replyList){
			if(replyList[i].bIndent == 1){
			output += `
						<img id="profile-photo" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
						<h4 class="author-name"><a href="#">${comment.bName}</a></h4>
						<time class="post-time">${comment.bDate}</time>
					    <p class="post-content">${comment.bContent}</p>
					    <button type="button" class="button-filled-primary comment-child-btn" data-bGroup="${comment.bGroup}"
					    													data-bStep="${comment.bStep}"
					    													data-bIndent="${comment.bIndent}">
					    	답글
					    </button>	
			   		  `
			} else {
				output += `
							<img id="profile-photo comment-child" src="https://25.media.tumblr.com/avatar_c5eeb4b2e95b_128.png" />
							<h4 class="author-name comment-child"><a href="#">${comment.bName}</a></h4>
							<time class="post-time comment-child">${comment.bDate}</time>
						    <p class="post-content comment-child">${comment.bContent}</p>
				   		  `
		}
		}
		output += `</table>`
	return output
}

// 답글
// 새로운 댓글이 추가될 때마다 이벤트 리스너를 등록
/*
function registerEventListeners(){
	const childReplyBtns = document.querySelectorAll(".childReplyBtn")
	childReplyBtns.forEach(childReplyBtn => { // childReplyBtn - 임시변수
	    childReplyBtn.addEventListener('click', function(){
	        
	        // 기존에 열려있던 답글 창이 있다면 닫기
	        const openReplyCells = document.querySelectorAll('.replyCell'); // <td>
			openReplyCells.forEach(cell => {
			    cell.parentNode.removeChild(cell); // <tr>
			})

	        const tr = this.closest('tr') // 클릭된 버튼이 속한 행 찾기
	        const bGroup = this.getAttribute('data-bGroup') // JS와 HTML 간에 데이터를 주고받을 수 있으며, 'data-'로 시작하는 속성은 원하는 데이터를 속성으로 저장할 수 있음. JS에서는 'getAttribute()' 메서드를 사용하여 해당 속성 값을 가져올 수 있음'
	        const bStep = this.getAttribute('data-bStep')	
	        const bIndent = this.getAttribute('data-bIndent')
	        
	        // 새로운 입력 필드 추가
            const output = replyChildUI()
            // 행의 끝에 새로운 입력 필드 추가
            tr.insertAdjacentHTML('afterend', output);
            
        	const sendButton = tr.nextElementSibling.querySelector('button');
            sendButton.addEventListener('click', function() {
            	const bContent = tr.nextElementSibling.querySelector('input').value // 입력된 내용 가져오기
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
            	
            	tr.nextElementSibling.remove() // 답글 창 닫기
            })
            
	    })
	})
}
*/

/*
function registerEventListeners(){
	const childReplyBtns = document.querySelectorAll(".comment-child-btn")
	childReplyBtns.forEach(childReplyBtn => { // childReplyBtn - 임시변수
	    childReplyBtn.addEventListener('click', function(){
	        
	        // 기존에 열려있던 답글 창이 있다면 닫기
	        const openReplyCells = document.querySelectorAll('.commentCell'); // <td>
			openReplyCells.forEach(cell => {
			    cell.parentNode.removeChild(cell); // <tr>
			})

	        const tr = this.closest('tr') // 클릭된 버튼이 속한 행 찾기
	        const bGroup = this.getAttribute('data-bGroup') // JS와 HTML 간에 데이터를 주고받을 수 있으며, 'data-'로 시작하는 속성은 원하는 데이터를 속성으로 저장할 수 있음. JS에서는 'getAttribute()' 메서드를 사용하여 해당 속성 값을 가져올 수 있음'
	        const bStep = this.getAttribute('data-bStep')	
	        const bIndent = this.getAttribute('data-bIndent')
	        
	        // 새로운 입력 필드 추가
            const output = replyChildUI()
            // 행의 끝에 새로운 입력 필드 추가
            tr.insertAdjacentHTML('afterend', output);
            
        	const sendButton = tr.nextElementSibling.querySelector('button');
            sendButton.addEventListener('click', function() {
            	const bContent = tr.nextElementSibling.querySelector('input').value // 입력된 내용 가져오기
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
            	
            	tr.nextElementSibling.remove() // 답글 창 닫기
            })
            
	    })
	})
}
*/
function registerEventListeners(){
	const childReplyBtns = document.querySelectorAll(".comment-child-btn")
	childReplyBtns.forEach(childReplyBtn => { // childReplyBtn - 임시변수
	    childReplyBtn.addEventListener('click', function(){
	        
	        // 기존에 열려있던 답글 창이 있다면 닫기
	        const openReplyCells = document.querySelectorAll('.commentCell'); // <td>
			openReplyCells.forEach(cell => {
			    cell.parentNode.removeChild(cell); // <tr>
			})

	        const tr = this.closest('tr') // 클릭된 버튼이 속한 행 찾기
	        const bGroup = this.getAttribute('data-bGroup') // JS와 HTML 간에 데이터를 주고받을 수 있으며, 'data-'로 시작하는 속성은 원하는 데이터를 속성으로 저장할 수 있음. JS에서는 'getAttribute()' 메서드를 사용하여 해당 속성 값을 가져올 수 있음'
	        const bStep = this.getAttribute('data-bStep')	
	        const bIndent = this.getAttribute('data-bIndent')
	        
	        // 새로운 입력 필드 추가
            const output = replyChildUI()
            // 행의 끝에 새로운 입력 필드 추가
            tr.insertAdjacentHTML('afterend', output);
            
        	const sendButton = tr.nextElementSibling.querySelector('button');
            sendButton.addEventListener('click', function() {
            	const bContent = tr.nextElementSibling.querySelector('input').value // 입력된 내용 가져오기
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
            	
            	tr.nextElementSibling.remove() // 답글 창 닫기
            })
            
	    })
	})
}

const replyChildUI = () => {
	let output = `<tr>
					  <td colspan="7" class="commentCell">
					      <input type="text" name="bContent" style="width: 370px;">
					      <button class="btn btn-secondary" type="button" id="sendBtn">답글 작성</button>
					  </td>
				  </tr>`
	return output
}

registerEventListeners()
</script>
</html>