document.addEventListener('DOMContentLoaded', function(){
	const joinElement = document.querySelector("#joinResult")
	let joinResult = joinElement ? joinElement.getAttribute("data-join-result") : null

	if(joinResult == "true"){
		alert("🎉회원가입을 축하드립니다🎉")
	}
})