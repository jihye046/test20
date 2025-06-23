const redirectAlert = (elementId, dataAttribute, msg) => {
	const element = document.querySelector(`#${elementId}`)
	let result = element ? element.getAttribute(dataAttribute) : null
	if(result) alert(msg)
}

// 회원가입 성공 메시지 처리
const resultData = document.querySelector("#joinResult").getAttribute("data-join-result")
if(resultData) {
	alert('회원가입을 축하드립니다!')
} else {
	alert('일시적인 오류로 회원가입이 완료되지 않았습니다. 잠시 후 다시 시도해 주세요.')
}
// redirectAlert("joinResult", "data-join-result", "회원가입을 축하드립니다!")

// 로그인 실패 메시지 처리
redirectAlert("loginFail", "data-loginFail-result", "아이디 또는 패스워드를 확인해주세요.")