const createElement = document.querySelector("#createResult") 
let createResult = createElement ? createElement.getAttribute("data-create-result") : null

if(createResult == "true") {
	alert("게시글이 등록되었습니다.")
}

const sort_latest = document.querySelector("#sort_latest")
const sort_hit = document.querySelector("#sort_hit")

// 최신순 기본 설정
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("sort_latest").classList.add("active")
	let page = paging
	let gubun = searchGubun
	let text = searchText
	
	updatePaginationLinks(sortType)
	updateSortBtnStyle(sortType)
})

const sortTypeInput = document.querySelector('#sortTypeInput')

sort_latest.addEventListener('click',function(){
	if(!this.classList.contains('active')){
		sort('latest') // 현재 페이지 정렬
		updateLatestBtnClass()
		sortTypeInput.value = 'latest' // 검색할 때 정렬 유지
		updatePaginationLinks('latest') // 페이지 이동 시 sortType 파라미터값 유지
	}
})


sort_hit.addEventListener('click', function(){
	if(!this.classList.contains('active')){
		sort('hit')
		updateHitBtnClass()
		sortTypeInput.value = 'hit'
		updatePaginationLinks('hit')
	}
})

const updatePaginationLinks = (sortType) => {
	document.querySelectorAll('.page-link').forEach(function(link){
		try{
			let url = new URL(link.href)
			url.searchParams.set('sortType', sortType)
			link.href = url.toString()
		} catch(error){
			console.log("invalid url:", link.href)
		}
	})
}

const updateSortBtnStyle = (sortType) => {
	if(sortType == 'latest'){
		updateLatestBtnClass()
	} else if(sortType == 'hit'){
		updateHitBtnClass()
	}
}

const updateLatestBtnClass = () => {
	sort_latest.classList.remove('btn', 'btn-dark')
	sort_latest.classList.add('active', 'btn', 'btn-dark')
	
	sort_hit.classList.remove('btn', 'btn-dark', 'active')
	sort_hit.classList.add('btn')
}

const updateHitBtnClass = () => {
	sort_hit.classList.remove('btn', 'btn-dark')
	sort_hit.classList.add('active', 'btn', 'btn-dark')
	
	sort_latest.classList.remove('btn', 'btn-dark', 'active')
	sort_latest.classList.add('btn')
}

// 현재 위치한 페이지 정렬
const sort = (type) => {
	let page = paging
	let gubun = searchGubun
	let text = searchText
	
	$.ajax({
		type: "get",
		data: {
			page: page,
			searchGubun: gubun,
			searchText: text,
			sortType: type
		},
		url: "/board/paging/ajax",
		dataType: "json",
		success: function(data){
			let pagingList = data["sort_hitPagingList"]
            let paging = data["pageDto"]
			updateSortedByHits(pagingList, paging)
		},
		error: function(error){
			console.error("sort_hit fail", error)
		}
	})
}

const updateSortedByHits = (pagingList, paging) => {
	const hitContainer = document.querySelector("#hitContainer")
	hitContainer.innerHTML = ''
	let tableOutput = 
	`
		<table class="table table-hover">
			<thead>
				<tr>
					<th>No.</th>
					<th>이름</th>
					<th>제목</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
	`
	pagingList.forEach(function(dto){
		tableOutput += 
		`
			<tr>
				<td>${dto.bId}</td>
				<td>
					<a href="/board/detailBoard?bId=${dto.bId}&bGroup=${dto.bGroup}&page=${paging.page}">${dto.bName}</a>
					<i class="fa-regular fa-comment-dots"></i>
					<span class="commentCount">${dto.commentCount}</span>
				</td>
				<td>${dto.bTitle}</td>
				<td>${dto.bDate}</td>
				<td>${dto.bHit}</td>
	  		</tr>
  		`
	})
	tableOutput += `</tbody></table>`
	
	//let paginationOutput = pagination(paging)
	//hitContainer.innerHTML = tableOutput + paginationOutput
	hitContainer.innerHTML = tableOutput
}

const pagination = (paging) => {
	let output = 
	
	`
		<nav>
			<ul class="pagination justify-content-center">
	`
	
	// Previous 버튼 추가
	if(paging.page <= 1){
		output += `<li class="page-item"></li>`
	} else {
		let previousPageLink = (paging.page - 3 <= 1) ?
			`<a class="page-link" href="/board/paging?page=${paging.page-1}"> Previous </a>` :
        	`<a class="page-link" href="/board/paging?page=${paging.page-3}"> Previous </a>`

		output += `<li class="page-item">${previousPageLink}</li>`
	}
	
	// 페이지번호 추가 및 링크 생성
	for(let i = paging.startPage; i <= paging.endPage; i++){
	    let pagingLink = (i == paging.page) ? 
	    	`<span class="page-link">${i}</span>` :
	    	`<a class="page-link" href="/board/paging?page=${i}">${i}</a>`
	        
	    output += `<li class="page-item">${pagingLink}</li>`
	}
	
	// Next 버튼 추가
	if(paging.page >= paging.maxPage){
		paginationOutput += `<li class="page-item"></li>`
	} else {
		let nextPageLink = (paging.page + 3 >= paging.maxPage) ? 
			`<a class="page-link" href="/board/paging?page=${paging.maxPage}">Next</a>` :
			`<a class="page-link" href="/board/paging?page=${paging.page + 3}">Next</a>`
			
		output += `<li class="page-item">${nextPageLink}</li>`
	}
	
	return output
}


//document.addEventListener('DOMContentLoaded', function(){
	
	let deleteElement = document.querySelector("#deleteResult")
	let deleteResult = deleteElement ? deleteElement.getAttribute("data-delete-result") : null
 	
	let userId = document.querySelector("#userId").getAttribute("data-userId")
	

	if(deleteResult == "true"){
		alert("해당 게시글은 삭제되었습니다.")
	}

	if(userId) {
		document.querySelector("#welcomeText").innerHTML = `${userId}님 환영합니다👏`
	} else {
		document.querySelector("#welcomeText").innerHTML = "로그인해주세요"		
	}
	
	const badge = document.querySelector(".badge")
//})