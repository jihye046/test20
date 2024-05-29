const updateElement = document.querySelector("#updateResult")
let updateResult = updateElement ? updateElement.getAttribute("data-update-result") : null

if(updateResult == "true"){
	alert("수정 완료")
}

// 좋아요
const likeBtn = document.querySelector("#likeBtn")
const bId = document.querySelector("#bId").getAttribute("data-bId")
let userId = document.querySelector("#userId").getAttribute("data-userId")
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
				bId: bId, 
				userId: userId
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
				bId: bId, 
				userId: userId
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