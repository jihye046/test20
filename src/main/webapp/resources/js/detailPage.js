const updateElement = document.querySelector("#updateResult")
let updateResult = updateElement ? updateElement.getAttribute("data-update-result") : null

if(updateResult == "true"){
	alert("게시글이 수정되었습니다.")
}

/* 이미지 목록
================================================== */
	// Fancybox 설정
$('[data-fancybox="gallery"]').fancybox({
  buttons: [
    "slideShow",
    "thumbs",
    "zoom",
    "fullScreen",
    "share",
    "close"
  ],
  loop: false,
  protect: true
})

	// bContent에서 모든 img 추출 후 {src, caption} 객체 생성
const extractImagesFromContent = (bContent) => {
	const extractImgArray = []

	// text를 HTML로 파싱
	const parser = new DOMParser()
	const doc = parser.parseFromString(bContent, 'text/html')

	const images = doc.querySelectorAll('img')
	images.forEach((image, index) => {
		extractImgArray.push({
			src: image.src,
			caption: `Image ${index + 1}`
		})
	})
	return extractImgArray
}

	// 이미지 카드 생성
const generateImageCard = (image) => {
    return `
		<div class='card'>
			<div class='card-image'>
				<a href='${image.src}' data-fancybox='gallery' data-caption='${image.caption}'>
					<img src='${image.src}' alt='Image Gallery'>
				</a>
			</div>
		</div>
	`
}

	// '더보기' 버튼이 포함된 이미지 카드 생성
const generateMoreButton = () => {
	return `
		<div class='card'>
			<div class='card-image more-button'>
				<div class='overlay'>
					<span class='more-text'>+ 더보기</span>
				</div>
			</div>
		</div>
	`
}

	// 이미지 모달 카드 생성
const generateModalCard = (image) => {
	return `
		<div class='modal-card'>
			<div class='modal-card-image'>
				<a href='${image.src}' data-fancybox='gallery' data-caption='${image.caption}'>
					<img src='${image.src}' alt='Image Gallery'>
				</a>
			</div>
		</div>
	`
}

	// 'bContent' 중 <img>만 추출한 배열
const img_array = extractImagesFromContent(window.bContent)

	// post와 image-container 구분선
const hr = document.querySelector('#postImageDivider')
if(img_array && img_array.length > 0){
	hr.classList.remove('hidden-hr')
	hr.classList.add('show-hr')
} else {
	hr.classList.remove('show-hr')
	hr.classList.add('hidden-hr')
}

	// 모달창 열기
const openModal = () => {
	const modal = document.querySelector('#gallery-modal')
	const modalImageContainer = modal.querySelector('.modal-image-container')

	modalImageContainer.innerHTML = ''
	img_array.forEach((image) => {
		const result = generateModalCard(image)
		const tempDiv = document.createElement('div')
		tempDiv.innerHTML = result
		modalImageContainer.append(tempDiv.firstElementChild)
	})

	modal.style.display = 'block'
}

	// 모달창 닫기
const closeModal = () => {
	// 닫기 버튼 눌렀을 때 모달 닫기
	const closeModalButton = document.querySelector('#closeModal')
	closeModalButton.addEventListener('click', function() {
		const modal = document.querySelector('.gallery-modal')
		modal.style.display = 'none'
	})
}

	// 이미지 갤러리 랜더링
const renderImages = () => {
	const container = document.querySelector('.image-container')
	const maxImage = 4

	// img_array[] > [0]~[3]까지 이미지를 먼저 append
	img_array.slice(0, maxImage).forEach((image, index) => {
		const result = generateImageCard(image)
		const tempDiv = document.createElement('div')
		tempDiv.innerHTML = result
		container.append(tempDiv.firstElementChild)

		// 이미지가 5개 이상인 경우 마지막 사진 위에 '더보기' 버튼 추가
		if(index === maxImage - 1 && img_array.length > maxImage) {
			const result = generateMoreButton()
			const tempDiv = document.createElement('div')
			tempDiv.innerHTML = result
			container.append(tempDiv.firstElementChild)
			
			// 네번째 이미지 위에 '더보기' 버튼 덮어씌우기
			const moreButton = container.querySelector('.more-button')
			const lastImage = container.querySelectorAll('.card-image')[maxImage - 1]
			lastImage.append(moreButton)

			moreButton.addEventListener('click', function(){
				openModal()
			})
		}
	})
}

const receiver = chatButton.getAttribute("data-bName") // 받는 사람(작성자)
const sender = document.querySelector("#userId").getAttribute("data-userId") // 보내는 사람

/* 로그인한 사용자가 작성자면 채팅 버튼 숨김 */
const hideChatButton = () => {
	const chatButton = document.querySelector("#chatButton")
	if(receiver == sender) {
		chatButton.style.display = 'none'
	}
}

/* 로그인한 사용자가 작성자인 경우만 수정, 삭제 버튼 보여주기 */
const hideDropdownMenu = () => {
	const dropdownMenu = document.querySelector('.dropdown')
	if(receiver != sender) {
		dropdownMenu.style.display = 'none'
	}
}

/* 웹소캣 1:1 채팅
================================================== */
chatButton.addEventListener('click', () => {
	const sorted = [receiver, sender].sort()
	const sortedName = `${sorted[0]}_${sorted[1]}`
	const chatWindow = window.open(`/user/chat/${receiver}`, 'chat', 'width=400,height=500,scrollbars=yes')
	
	chatWindow.addEventListener('load', function(){
		chatWindow.getChatHistory(sortedName.toString(), sender)
    	chatWindow.displayDate()
		chatWindow.connect2(sortedName, receiver, sender)
    })
})

/* 페이지 로드 시 실행될 함수
================================================== */
renderImages()
closeModal()
hideChatButton()
hideDropdownMenu()