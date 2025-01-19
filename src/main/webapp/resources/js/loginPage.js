const redirectAlert = (elementId, dataAttribute, msg) => {
	const element = document.querySelector(`#${elementId}`)
	let result = element ? element.getAttribute(dataAttribute) : null
	if(result) alert(msg)
}

// 회원가입 성공 메시지 처리
redirectAlert("joinResult", "data-join-result", "회원가입을 축하드립니다!")

// 로그인 실패 메시지 처리
redirectAlert("loginFail", "data-loginFail-result", "아이디 또는 패스워드를 확인해주세요.")