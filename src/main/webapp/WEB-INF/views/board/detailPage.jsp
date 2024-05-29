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
				<h1>${dto.bTitle}</h1>
				<span><strong>${dto.bName}</strong></span>
				<div class="p-wrapper">
					<div class="subTitle date">${dto.bDate}</div>
					<div class="info">
						<span class="subTitle">게시글번호 | ${dto.bId}</span>
						<span class="subTitle">조회수 | ${dto.bHit}</span>
						<div class="dropdown">
							<button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
						    	<i class="fa-solid fa-ellipsis-vertical" style="margin-left: 10px; color: #808080;"></i>
						    </button>
							<div class="dropdown-menu dropdown-menu-right">
						    	<a class="dropdown-item" href="/board/updatePage?bId=${dto.bId}">수정</a>
						    	<a class="dropdown-item" href="/board/deleteBoard?bId=${dto.bId}" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
						    </div>
					    </div>
					</div>
				</div>
				<p style="border-bottom: 1px solid #EAEAEA; padding: 5px 0px;"></p>
				<div style="border-bottom: 1px solid #EAEAEA; padding: 50px 0px;">
					${dto.bContent}
				</div>
				<div>
					<div class="likeBtn-div">
						<img id="likeBtn" alt="unlike" src="../../../resources/images/unliked.png">
						<span id="totalLikes" style="font-size: 13px;">${dto.bLike}</span>
					</div>
				</div>
				<div class="reply-container">
					<div>
						<input type="text" size="60" placeholder="댓글을 달아보세요" id="replyInput">
						<button class="btn btn-secondary" type="button" id="replyBtn">댓글</button>
					</div>
					<div class="reply-form">
						<table id="replyTable">
							<tr id="replyTitle">
								<th>bId</th>
								<th>bContent</th>
								<th>date</th>
								<th></th>
							</tr>
							<c:forEach items="${replyList}" var="reply">
								<tr id="replyContent">
									<c:if test="${reply.bIndent == 1}"> <!-- 댓글인 경우 -->
										<td>${reply.bId}</td>
										<td>${reply.bContent}</td>
										<td>${reply.bDate}</td>
										<td>
											<button type="button" class="btn btn-secondary childReplyBtn"
												    data-bGroup="${reply.bGroup}"
												    data-bStep="${reply.bStep}"
												    data-bIndent="${reply.bIndent}">
											     답글
								     	    </button>
						     	   		</td>
						     	    </c:if>
						     	    <c:if test="${reply.bIndent != 1}">
						     	    	<td class="replyChild-td">${reply.bId}</td>
										<td class="replyChild-td"><strong>@${reply.bGroup}</strong>&nbsp;${reply.bContent}</td>
										<td class="replyChild-td">${reply.bDate}</td>
										<td class="replyChild-td"></td>
						     	    </c:if>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</main>
</body>
<div id="updateResult" data-update-result="${updateResult}"></div>
<div id="bId" data-bId="${dto.bId}"></div>
<%-- <div id="userId" data-userId="${sessionScope.userId}"></div> --%>
<div id="isLiked" data-isLiked="${isLiked}"></div>
<script src="../../../resources/js/detailPage.js"></script>
<script>
// 댓글
const replyBtn = document.querySelector("#replyBtn")
const replyTable = document.querySelector("#replyTable")
const replyInput = document.querySelector("#replyInput")

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
	return output
}

// 답글
// 새로운 댓글이 추가될 때마다 이벤트 리스너를 등록
function registerEventListeners(){
	const childReplyBtns = document.querySelectorAll(".childReplyBtn") // .childReplyBtn 클래스를 가진 모든 버튼 요소를 찾음
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

const replyChildUI = () => {
	let output = `<tr>
					  <td colspan="7" class="replyCell">
					      <input type="text" name="bContent" style="width: 370px;">
					      <button class="btn btn-secondary" type="button" id="sendBtn">답글 작성</button>
					  </td>
				  </tr>`
	return output
}

registerEventListeners()
</script>
</html>