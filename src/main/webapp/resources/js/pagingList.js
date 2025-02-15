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
	updateBoardCards()
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
	let tableOutput = `<div class="card-container">`
	pagingList.forEach(function(dto){
		tableOutput +=
	  		`
	  			<figure class="snip1518 hover">
					<div class="image" data-content="${dto.bContent}">
					</div>
					<figcaption>
						<h5>${dto.bName}</h5>
						<h3>${dto.bTitle}</h3>
						<footer>
							<div class="date">${dto.bDate}</div>
							<div class="icons">
								<div class="views"><i class="ion-eye"></i>${dto.bHit}</div>
								<div class="love"><i class="ion-heart"></i>${dto.bLike}</div>
								<div class="comment"><i class="fa-thin fa-comment fa-2xs"></i>${dto.commentCount}</div>
							</div>
						</footer>
					</figcaption>
					<a href="/board/detailBoard?bId=${dto.bId}&bGroup=${dto.bGroup}&page=${paging.page}"></a>
				</figure>
	  		`
	})
	tableOutput += `</div>`
	hitContainer.innerHTML = tableOutput
	
	updateBoardCards()
}

const pagination = (paging) => {
	let output = `<nav><ul class="pagination justify-content-center">`
	
	// Previous 버튼 추가
	if(paging.page <= 1){
		output += `<li class="page-item"></li>`
	} else {
		let previousPageLink = (paging.page - 5 <= 1) ?
			`<a class="page-link" href="/board/paging?page=${paging.page-1}"> Previous </a>` :
        	`<a class="page-link" href="/board/paging?page=${paging.page-5}"> Previous </a>`

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
		let nextPageLink = (paging.page + 5 >= paging.maxPage) ? 
			`<a class="page-link" href="/board/paging?page=${paging.maxPage}">Next</a>` :
			`<a class="page-link" href="/board/paging?page=${paging.page + 5}">Next</a>`
			
		output += `<li class="page-item">${nextPageLink}</li>`
	}
	return output
}


let deleteElement = document.querySelector("#deleteResult")
let deleteResult = deleteElement ? deleteElement.getAttribute("data-delete-result") : null

const userId = document.querySelector("#userId").getAttribute("data-userId")
const userNickname = document.querySelector("#userNickname").getAttribute("data-userNickname")


if(deleteResult == "true"){
	alert("게시글이 삭제되었습니다.")
}

if(userId) {
	document.querySelector("#welcomeText").innerHTML = `<a href="/user/myPage">${userNickname}</a>님 환영합니다👏`
} else {
	document.querySelector("#welcomeText").innerHTML = "로그인해주세요"		
}

const badge = document.querySelector(".badge")

// 게시글 이미지
const updateBoardCards = () => {
	const boardCards = document.querySelectorAll('.image')
	boardCards.forEach(function (card) {
		const bContent = card.getAttribute('data-content')
		const parser = new DOMParser()
		const doc = parser.parseFromString(bContent, 'text/html') // HTML로 파싱
		const firstImg = doc.querySelector('img') // 첫 번째 <img> 태그 찾기
		
		if (firstImg) {
			const imageSrc = firstImg.src
			card.innerHTML = `<img src="${imageSrc}" alt="image"/>`
		} else {
			card.innerHTML = '<img src="https://buly.kr/1RDn8CU" alt="image">'
		}
	})
}

updateBoardCards()