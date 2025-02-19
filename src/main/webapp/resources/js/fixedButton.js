const likeBtn2 = document.querySelector(".icon-button.like") // button
const bookmarkBtn = document.querySelector(".icon-button.bookmark")
const shareBtn = document.querySelector(".icon-button.share")
const linkBtn = document.querySelector(".icon-button.link")

// 좋아요 카운트
const likeFont = likeBtn2.querySelector(".likeFont") // i
const isLiked = document.querySelector("#isLiked").getAttribute("data-isLiked") === "true"
let likeStatus = (isLiked) ? 'liked' : 'unliked'
const likeCount = likeBtn2.querySelector(".count")

// 좋아요 누른 상태
const liked_O = `<i class="fa-solid fa-heart" style="color: #666666;"></i>`
// 좋아요를 안누른 상태
const liked_X = `<i class="fa-regular fa-heart" style="color: #666666;"></i>`

// 초기 화면
const likeShow = () => {
    if(likeStatus == 'liked'){
        likeFont.innerHTML = liked_O
    } else if(likeStatus == 'unliked') {
        likeFont.innerHTML = liked_X
    }
}

// 좋아요 클릭했을 때
likeBtn2.addEventListener('click', function(){
	if(likeFont.classList.contains('fa-solid')){ // 좋아요 눌려진 상태
        alert('좋아요 눌려진 상태')
        /* 
		$.ajax({
			type: "post",
			data: {
				bId: bId
			},
			url: "/board/removeLike",
			success: function(data){
				likeFont.innerHTML = liked_X
				likeCount.textContent = data
				//totalLikes.innerHTML = data
			},
			error: function(error){
				console.error("fail", error)
			}
		})
            */
    } else if(likeFont.classList.contains('fa-regular')){ // 좋아요 안눌려진 상태
        alert('좋아요 안눌려진 상태')
        /* 
        $.ajax({
			type: "post",
			data: {
				bId: bId 
			},
			url: "/board/addLike",
			success: function(data){
                likeFont.innerHTML = liked_O
                likeCount.textContent = data
				//totalLikes.innerHTML = data
			},
			error: function(error){
				console.error("fail", error)
			}
		})
            */
    } else {
        alert('error')
    }
})



// 즐겨찾기 카운트
bookmarkBtn.addEventListener('click', function(){
    const bookmarkCount = bookmarkBtn.querySelector('.count')
    bookmarkCount.textContent = parseInt(bookmarkCount.textContent) + 1
})

// 공유 버튼 클릭시
shareBtn.addEventListener('click', function(){
    alert('공유 버튼 클릭')
})

// 링크 버튼 클릭시
linkBtn.addEventListener('click', function(){
    alert('링크 버튼 클릭')
})

likeShow()