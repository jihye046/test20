const changePasswordBtn = document.querySelector("#changePasswordBtn")

changePasswordBtn.addEventListener('click', function() {
    const oldPassword = document.querySelector("#oldPassword").value
    const newPassword = document.querySelector("#newPassword").value
    const confirmPassword = document.querySelector("#confirmPassword").value

    // 새 비밀번호와 새 비밀번호 확인이 비어있는지 체크
    if (!newPassword || !confirmPassword) {
        alert("새 비밀번호와 새 비밀번호 확인을 모두 입력해주세요.");
        return
    }

    // 새 비밀번호와 새 비밀번호 확인이 일치하는지 체크
    if (newPassword !== confirmPassword) {
        alert("새 비밀번호를 다시 확인해주세요.");
        return
    }

    // 비밀번호가 일치하면 서버로 전송할 param 객체 생성
    const param = {
        oldPw: oldPassword,
        newPw: newPassword
    }

    // 서버로 비밀번호 변경 요청
    fetch('/user/changePassword', {
	    method: 'POST',
	    headers: {
	    	'Content-Type': 'application/json'
		},
    	body: JSON.stringify(param)
	})
    .then((response) => response.json())
    .then((data) => {
        const handleRedirect = (msg, redirectUrl) => {
            alert(msg)
            window.location.href = redirectUrl
        }

        if(data.status == 'success'){
            handleRedirect(data.msg, '/user/loginPage') 
        } else if(data.status == 'oldPasswordIncorrect'){
            handleRedirect(data.msg, '/user/changePasswordForm')
        } else if(data.status == 'isSameAsCurrentPassword'){
        	handleRedirect(data.msg, '/user/changePasswordForm')
        } else {
            handleRedirect(data.msg, '/user/changePasswordForm')
        }
    })
    .catch(error => {
        console.error('Error:', error)
    })
    
})