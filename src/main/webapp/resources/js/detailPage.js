const updateElement = document.querySelector("#updateResult")
let updateResult = updateElement ? updateElement.getAttribute("data-update-result") : null
const bTitle = document.querySelector("#bTitle").getAttribute("data-bTitle")
const bContent = document.querySelector("#bContent").getAttribute("data-bContent")

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
	const imgUrl = getThumbnailUrl()
	const currentURL = window.location.href;
	getKakaoKey()
		.then(kakaoKey => {
			Kakao.init(kakaoKey)
			Kakao.Link.sendDefault({
				objectType: 'feed',
				content: {
					title: 'test20',
					description: bTitle,
					imageUrl: imgUrl,
					link: {
	                    mobileWebUrl: currentURL,
	                    webUrl: currentURL,
	                },
				},
				buttons: [
	                {
	                    title: '웹으로 보기',
	                    link: {
	                        mobileWebUrl: currentURL,
	                        webUrl: currentURL,
	                    },
	                },
	            ],
	            installTalk: true,
			})
		})
}

function getThumbnailUrl() {
	// 임시 DOM 요소를 만들어 bContent의 HTML을 파싱
    const tmpTag = document.querySelector("#tempDiv")
    tmpTag.innerHTML = bContent
    
	// 게시글에 이미지가 없는 경우 설정할 기본 이미지
	const defaultImageUrl = 'https://plus.unsplash.com/premium_photo-1674939147629-bfb664a1ae68?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'
	
	// 첫 번째 img 요소의 src를 가져오고, 없을 경우 기본 이미지 URL 반환
	const imgElement = tmpTag.querySelector('img')
	return imgElement ? imgElement.src : defaultImageUrl
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