const updateElement = document.querySelector("#updateResult")
let updateResult = updateElement ? updateElement.getAttribute("data-update-result") : null



if(updateResult == "true"){
	alert("게시글이 수정되었습니다.")
}
