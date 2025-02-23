const updateElement = document.querySelector("#updateResult")
let updateResult = updateElement ? updateElement.getAttribute("data-update-result") : null

if(updateResult == "true"){
	alert("게시글이 수정되었습니다.")
}

/* 이미지 목록
================================================== */
const imageContainer = document.querySelector('.image-container')
const images = imageContainer.querySelectorAll('img')
const imageCount = images.length // 5




const getPostImageList = () => {
	//const post = document.querySelector('.post')
	//const postImages = post.querySelectorAll('img')
	//const parser = new DOMParser()
	//const doc = parser.parseFromString(postImages, 'text/html')
	
}
