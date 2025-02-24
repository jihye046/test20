const updateElement = document.querySelector("#updateResult")
let updateResult = updateElement ? updateElement.getAttribute("data-update-result") : null

if(updateResult == "true"){
	alert("게시글이 수정되었습니다.")
}

/* 이미지 목록
================================================== */
// Fancybox 설정
/*				   
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
*/

const img_array = [
	{ src: 'https://cdn.pixabay.com/photo/2023/05/22/10/49/houses-8010401_1280.jpg', caption: 'Caption Images 1' },
    { src: 'https://cdn.pixabay.com/photo/2023/07/13/05/36/mountains-8123933_1280.jpg', caption: 'Caption Images 2' },
    { src: 'https://cdn.pixabay.com/photo/2022/10/24/20/22/muhlviertel-7544316_1280.jpg', caption: 'Caption Images 3' },
    { src: 'https://cdn.pixabay.com/photo/2022/12/12/21/35/stream-7651969_1280.jpg', caption: 'Caption Images 4' }
]

img_array.forEach(image => {
	const card = document.createElement('div')
	card.classList.add('card')
})

const nextBtn = document.querySelector(".next-btn")
const showNextImages = () => {}
nextBtn.addEventListener('click', function(){
	//showNextImages()
})
