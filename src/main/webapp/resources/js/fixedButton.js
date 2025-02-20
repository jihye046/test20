const activeClass = "fa-solid" // ♥
const inactiveClass = "fa-regular" // ♡
const bId = document.querySelector("#bId").getAttribute("data-bId")

const linkBtn = document.querySelector(".icon-button.link")

/* 좋아요
================================================== */
const likeBtn = document.querySelector(".icon-button.like") // <button>
const likeIcon = likeBtn.querySelector(".fa-heart") // <i>
const isLiked = document.querySelector("#isLiked").
				getAttribute("data-isLiked") === "true"

	// 초기 화면
const setInitialLikeIcon = () => {
    if(isLiked){
        likeIcon.classList.add(activeClass)
    } else {
        likeIcon.classList.add(inactiveClass)
    }
}

	// 좋아요 클릭했을 때
likeBtn.addEventListener('click', function(){
	const likeCount = likeBtn.querySelector(".count")
	if(likeIcon.classList.contains(activeClass)){ // 좋아요 눌려진 상태
		$.ajax({
			type: "post",
			data: {
				bId: bId
			},
			url: "/board/removeLike",
			success: function(data){
				likeIcon.classList.remove(activeClass)
				likeIcon.classList.add(inactiveClass)
				likeCount.textContent = data // 카운트
			},
			error: function(error){
				console.error("fail", error)
			}
		})
    } else if(likeIcon.classList.contains(inactiveClass)){ // 좋아요 안눌려진 상태
        $.ajax({
			type: "post",
			data: {
				bId: bId 
			},
			url: "/board/addLike",
			success: function(data){
				likeIcon.classList.remove(inactiveClass)
				likeIcon.classList.add(activeClass)
                likeCount.textContent = data // 카운트
			},
			error: function(error){
				console.error("fail", error)
			}
		})
    }
})

/* 북마크
================================================== */
const bookmarkBtn = document.querySelector(".icon-button.bookmark") // <button>
const bookmarkIcon = bookmarkBtn.querySelector(".fa-bookmark") // <i>
const isBookmarked = document.querySelector("#isBookmarked").
					getAttribute("data-isBookmarked") === "true"

	// 초기 화면
const setInitialBookmarkIcon = () => {
	if(isBookmarked){
		bookmarkIcon.classList.add(activeClass)
	} else {
		bookmarkIcon.classList.add(inactiveClass)
	}
}

	// 북마크 클릭했을 때
bookmarkBtn.addEventListener('click', function(){
	const bookmarkCount = bookmarkBtn.querySelector('.count')

	if(bookmarkIcon.classList.contains(activeClass)){
		fetch('/board/removeBookmark', { // 북마크 눌려진 상태
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
			bookmarkIcon.classList.remove(activeClass)
			bookmarkIcon.classList.add(inactiveClass)
			bookmarkCount.textContent = data.bookmarkCount
		})
		.catch(error => {
			console.error('Error: ', error)
		})
	} else {
		fetch('/board/addBookmark', { // 북마크 안눌려진 상태
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
			bookmarkIcon.classList.remove(inactiveClass)
			bookmarkIcon.classList.add(activeClass)
			bookmarkCount.textContent = data.bookmarkCount
		})
		.catch(error => {
			console.error(`Error: ${error}`)
		})
	}
})

// 공유 버튼 클릭시
const shareBtn = document.querySelector(".icon-button.share")
const shareOptions =document.querySelector(".share-options")
shareBtn.addEventListener('click', function(){
	if(shareOptions.style.display == 'none' || shareOptions.style.display == ''){
		shareOptions.style.display = 'flex'
	} else {
		shareOptions.style.display = 'none'
	}
})

// 링크 버튼 클릭시
linkBtn.addEventListener('click', function(){
    alert('링크 버튼 클릭')
})

setInitialLikeIcon()
setInitialBookmarkIcon()