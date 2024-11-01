const updateElement = document.querySelector("#updateResult")
let updateResult = updateElement ? updateElement.getAttribute("data-update-result") : null

if(updateResult == "true"){
	alert("게시글이 수정되었습니다.")
}

// 네이버 공유
function shareNaver() {
	const title = document.querySelector("#bTitle").getAttribute("data-bTitle")
	const currentUrl = window.location.href
	
	if (!currentUrl) {
        console.error("Current URL is null or undefined")
        return; 
    }
	
	window.open(
		"https://share.naver.com/web/shareView?url=" 
		+ (encodeURI(encodeURIComponent(currentUrl))) 
		+ "&title=" 
		+ encodeURI(title),
		'_blank' // 새 창에서 열기
	)
}


// 카카오 공유
function shareKakao() {
	getKakaoKey()
		.then(kakaoKey => {
			Kakao.init(kakaoKey)
			Kakao.Link.sendDefault({

				
			})

		})
}

function getKakaoKey() {
	return new Promise((resolve, reject) => {
		$.ajax({
			type: "post",
			url: "/board/getKakaoKey",
			success: function(kakaoKey){
				resolve(kakaoKey)
			},
			error: function(error){
				reject(error)
			}
		})
	})
}

// 좋아요
const likeBtn = document.querySelector("#likeBtn")
const bId = document.querySelector("#bId").getAttribute("data-bId")
const totalLikes = document.querySelector("#totalLikes")
const likedPng = "../../../resources/images/liked.png"
const unlikedPng = "../../../resources/images/unliked.png"

document.addEventListener("DOMContentLoaded", function() {
	const isLiked = document.querySelector("#isLiked").getAttribute("data-isLiked") === "true"
	likeBtn.src = (isLiked) ? likedPng : unlikedPng
})

likeBtn.addEventListener('click', function(){
	if(likeBtn.src.includes("unliked.png")){
		$.ajax({
			type: "post",
			data: {
				bId: bId
			},
			url: "/board/addLike",
			success: function(data){
				likeBtn.src = likedPng
				totalLikes.innerHTML = data
			},
			error: function(error){
				console.error("fail", error)
			}
		})
	} else if(likeBtn.src.includes("liked.png")){
		$.ajax({
			type: "post",
			data: {
				bId: bId 
			},
			url: "/board/removeLike",
			success: function(data){
				likeBtn.src = unlikedPng
				totalLikes.innerHTML = data
			},
			error: function(error){
				console.error("fail", error)
			}
		})
	}
})