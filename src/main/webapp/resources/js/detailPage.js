let updateElement = document.querySelector("#updateResult")
let updateResult = updateElement ? updateElement.getAttribute("data-update-result") : null

if(updateResult == "true"){
	alert("수정 완료")
}

// 좋아요
const likeBtn = document.querySelector("#likeBtn")
const bId = document.querySelector("#bId")
const bIdValue = bId.getAttribute("data-bId")

likeBtn.addEventListener('click', function(){
	if(likeBtn.src.includes("unliked.png")){
		$.ajax({
			type: "post",
			data: {
				bId: bIdValue,
				bLike: "Y"
			},
			url: "/board/updateLike",
			dataType: "text",
			success: function(status){
				bLikeStatus(status)
			},
			error: function(){
				console.log("updateLike1 error")
			}
		})
	} else {
		$.ajax({
			type: "post",
			data: {
				bId: bIdValue,
				bLike: "N"
			},
			url: "/board/updateLike",
			dataType: "text",
			success: function(status){
				bLikeStatus(status)
			},
			error: function(){
				console.log("updateLike2 error")
			}
		})
	}
	
})

let bLikeStatus = (status) => {
	if(status == "N"){
		likeBtn.src = "../../../resources/images/unliked.png"
	} else if(status == "Y"){
		likeBtn.src = "../../../resources/images/liked.png"
	}
}