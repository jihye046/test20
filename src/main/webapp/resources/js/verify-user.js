const authMethodRadios = document.querySelectorAll("input[name='authMethod']") // 라디오 버튼
const phoneAuthSection = document.querySelector("#phoneAuthSection") // 휴대폰 인증 작성 폼
const emailAuthSection = document.querySelector("#emailAuthSection") // 이메일 인증 작성 폼
const phoneDescription = document.querySelector("#phoneDescription") // 휴대폰 인증 설명
const emailDescription = document.querySelector("#emailDescription") // 이메일 인증 설명
const mobilePrefix = document.querySelector("#mobilePrefix") // 휴대폰 인증 시 국가 selectBox
const emailDomain = document.querySelector("#emailDomain") // 이메일 인증 시 도메인 selectBox
const directEmailDomainInput = document.querySelector("#directEmailDomain") // 이메일 '직접 입력' input
const userEmailInput = document.querySelector("#userEmail") // 이메일 아이디 입력 input
const verificationMessageSpan = document.querySelector("#verificationMessage") // 인증번호 발송 안내 메시지


/* 전송 버튼 제어
================================================== */
const submitForm = () => {
    document.querySelector("#submitButton").addEventListener('click', () => {
        const form = document.querySelector("#verify-form")
        const mode = document.querySelector("#mode").getAttribute("data-mode")
        const userId = document.querySelector("#verifyUserId").getAttribute("data-verifyUserId")

        if(mode == 'id'){
            form.action = '/user/findIdResultPage'
        } else if(mode == 'password'){
            form.action = '/user/password-reset-page'
        }

        form.submit()
    })
}

/* 인증번호 발송, 응답
================================================== */
    // 사용자가 입력한 인증 코드 확인
const checkVerificationCode = () => {
    document.querySelector("#verificationCode").addEventListener('input', function() {
        let userCode = this.value

        axios.post('/userMail/check', new URLSearchParams({userCode: userCode}))
        .then(response => {
            verificationMessageSpan.textContent = response.data
            if(response.data == '인증성공'){
                verificationMessageSpan.style.color = '#28a745'
                document.querySelector("#submitButton").disabled = false
            } else if(response.data == '인증실패'){
                verificationMessageSpan.style.color = '#dc3545'
                document.querySelector("#submitButton").disabled = true
            }
        })
        .catch(error => {
            console.error('error: ', error)
        })
    })
}

	// 인증 시간 타이머
let timerInterval
const startVerificationTimer = (durationInSeconds) => {
    clearInterval(timerInterval) // 시간 초기화
    const timer = document.querySelector("#verificationTimer")
    let remainingTime = durationInSeconds

    const updateDisplay = () => {
        const minutes = String(Math.floor(remainingTime / 60)).padStart(2, '0')
        const seconds = String(remainingTime % 60).padStart(2, '0')
        timer.textContent = `${minutes} : ${seconds}`
    }

    updateDisplay()
    timerInterval = setInterval(() => {
        remainingTime--

        if(remainingTime <= 0) {
            clearInterval(timerInterval)
            timer.textContent = '인증 시간이 만료되었습니다.'
            document.querySelector("#verificationCode").disabled = 'true'
        } else {
            updateDisplay()
        }
    }, 1000)
}

    // 사용자 정보 확인 후 이메일 발송
const sendEmailVerificationCode = (uemail) => {
    const verificationCodeGroup = document.querySelector(".verification-code-group") // 인증번호 입력 div
    verificationCodeGroup.style.display = 'block'

    axios.get("/userMail/send?uemail=" + uemail)
    .then(response => {
        if(response.data){
            // 인증번호 발송 성공 시
            verificationMessageSpan.textContent = '인증번호가 발송되었습니다.'
            verificationMessageSpan.style.color = '#28a745'
            startVerificationTimer(5 * 60) // 5분

            checkVerificationCode()
        } else {
            // 인증번호 발송 실패 시
            verificationMessageSpan.textContent = '인증번호 발송 실패. 다시 시도해주세요'
            verificationMessageSpan.style.color = '#dc3545'
            verificationCodeGroup.querySelector("#verificationCode").disabled = true
        }
    })
    .catch(error => {
        console.error('error: ', error)
    })
}

    // 사용자 연락처 정보가 DB와 일치하는지 확인
const checkUserInfoMatch = () => {
    const mode = document.querySelector("#mode").getAttribute("data-mode")
    const name = document.querySelector("#nameEmail").value
    let email = ''
    const verifyUserId = document.querySelector("#verifyUserId").getAttribute("data-verifyUserId")
    
    if(emailDomain.value == 'direct'){
        email = directEmailDomainInput.value
    } else {
        email = userEmailInput.value + emailDomain.value
    }

    let params = {userName: name, uemail: email}
    if(mode == 'password'){
        params.userId = verifyUserId
    }

    axios.get('/user/checkUserInfoMatch', {params})
        .then(response => {
            if(response.data){
                sendEmailVerificationCode(email)
            } else {
                alert('일치하는 정보가 없습니다. 확인 후 다시 시도해주세요.')
                document.querySelector("#nameEmail").value = ''
                document.querySelector("#userEmail").value = ''
            }
        })
        .catch(error => {
            console.error('error: ', error)
        })

}

	// 휴대폰 인증 - 인증번호 받기 클릭 시
document.querySelector("#sendSmsButton").addEventListener('click', () => {
    // SMS 인증 기능은 유료 API 필요로 인해 미구현
})

	// 이메일 인증 - 인증번호 받기 클릭 시
document.querySelector("#sendEmailButton").addEventListener('click', () => {
    checkUserInfoMatch();
})

/* 이메일 도메인 - '직접 입력' 선택 시
================================================== */
emailDomain.addEventListener('change', () => {
    if(emailDomain.value == 'direct'){
        // 도메인까지 직접 입력
        userEmailInput.style.display = 'none'
        directEmailDomainInput.style.display = 'inline-block'
        directEmailDomainInput.value = ''
    } else {
        // 아이디만 입력
        directEmailDomainInput.style.display = 'none'
        userEmailInput.style.display = 'inline-block'
    }
})

/* 라디오 버튼 변경 시 UI 전환
================================================== */
const toggleAuthMethod = () => {
    const selectMethod = document.querySelector("input[name='authMethod']:checked").value
    if(selectMethod == "phone") {
        // 인증 작성 폼
        phoneAuthSection.style.display = 'block'
        emailAuthSection.style.display = 'none'

        // 인증 설명
        phoneDescription.style.display = 'block'
        emailDescription.style.display = 'none'

        // 이메일 작성 폼의 입력 필드 초기화
        document.querySelector("#nameEmail").value = ''
        document.querySelector("#userEmail").value = ''
        emailDomain.selectedIndex = 0
        directEmailDomainInput.style.display = 'none'
    } else if(selectMethod == 'email') {
        // 인증 작성 폼
        emailAuthSection.style.display = 'block'
        phoneAuthSection.style.display = 'none'

        // 인증 설명
        emailDescription.style.display = 'block'
        phoneDescription.style.display = 'none'

        // 휴대폰 작성 폼의 입력 필드 초기화
        document.querySelector("#namePhone").value = ''
        mobilePrefix.selectedIndex = 0
        document.querySelector("#mobileNumber").value = ''
    }

    // 인증 방식 전환 시
    document.querySelector(".verification-code-group").style.display = 'none' // 인증번호 입력 섹션 숨기기
    document.querySelector("#verificationCode").value = '' // 인증번호 초기화
    document.querySelector("#verificationMessage").textContent = '' // 인증메시지 초기화
    document.querySelector("#submitButton").disabled = true // 버튼 비활성화
    userEmailInput.style.display = 'inline-block'
}


/* 페이지 로드 시 실행할 함수
================================================== */
authMethodRadios.forEach(radio => {
    radio.addEventListener('change', toggleAuthMethod)
})

toggleAuthMethod()
submitForm()