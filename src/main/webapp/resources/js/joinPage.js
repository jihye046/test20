const newPasswordInput = document.querySelector("#password") // 설정할 비밀번호 Input
const confirmPasswordInput = document.querySelector("#confirmPassword") // 새 비밀번호 확인 Input
const requirementMessage = document.querySelector("#passwordRequirement") // 비밀번호 요구사항 출력 필드
const mismatchMessage = document.querySelector("#passwordMismatchMessage") // 비밀번호 일치여부 출력 필드
const joinBtn = document.querySelector("#joinBtn") // 회원가입 버튼

/* 아이디 유효성 검사
================================================== */

/* 아이디 중복 검사
================================================== */

/* 메시지 스타일
================================================== */
    // 성공
const setValidStyle = (el, message) => {
    el.textContent = message
    el.style.color = '#28a745'
}

    // 실패
const setInvalidStyle = (el, message) => {
    el.textContent = message
    el.style.color = '#ff6347'
}

/* 비밀번호 유효성 검사
================================================== */
const validatePassword = (password) => {
    const minLenght = 8 // 최소 8자 이상
    const maxLenght = 16 // 최대 16자 이하
    const hasSpace = /\s/.test(password) // 스페이스, 탭, 줄바꿈이 있으면 true, 없으면 false
    const hasUpper = /[A-Z]/.test(password) // 대문자가 하나 이상이면 true, 없으면 false
    const hasLower = /[a-z]/.test(password) // 소문자가 하나 이상이면 true, 없으면 false
    const hasNumber = /[0-9]/.test(password) // 숫자가 하나 이상이면 true, 없으면 false
    const hasSpecial = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(password) // 특수문자가 하나 이상이면 true, 없으면 false
    
    if(password.length < minLenght || password.length > maxLenght){
        setInvalidStyle(requirementMessage, '비밀번호는 8자 이상 16자 이하로 입력해주세요.')
        return false
    } else if(hasSpace){
        setInvalidStyle(requirementMessage, '비밀번호에 공백은 사용할 수 없습니다.')
        return false
    } else if(!hasUpper){
        setInvalidStyle(requirementMessage, '비밀번호에 최소 하나 이상의 대문자가 포함되어야 합니다.')
        return false
    } else if(!hasLower){
        setInvalidStyle(requirementMessage, '비밀번호에 최소 하나 이상의 소문자가 포함되어야 합니다.')
        return false
    } else if(!hasNumber){
        setInvalidStyle(requirementMessage, '비밀번호에 최소 하나 이상의 숫자가 포함되어야 합니다.')
        return false
    } else if(!hasSpecial){
        setInvalidStyle(requirementMessage, '비밀번호에 최소 하나 이상의 특수문자가 포함되어야 합니다.')
        return false
    } else {
        setValidStyle(requirementMessage, '')
        return true
    }
}

/* 비밀번호 일치 여부 검사
================================================== */
const checkPasswordMatch = () => {
    const password = newPasswordInput.value
    let confirmPassword = confirmPasswordInput.value

    if(password != confirmPassword){
        setInvalidStyle(mismatchMessage, '비밀번호가 일치하지 않습니다.')
        return false
    } else {
        setValidStyle(mismatchMessage, '')
        return true
    }
}

/* 폼 전송 제어
================================================== */
const form = document.querySelector("#join-form")
form.addEventListener('submit', (e) => {
  e.preventDefault()

  const isValid = validatePassword(newPasswordInput.value)
  const isMatched = checkPasswordMatch()

  if(!isValid || !isMatched){
    alert('비밀번호를 다시 확인해주세요.')
    return
  }

  //form.submit()
})

/* 휴대폰 번호 입력 시 하이픈 자동 생성
================================================== */
const mobileInput = document.querySelector("#umobile")

mobileInput.addEventListener('input', (e) => {
  const oldValue = mobileInput.value
  const oldCursor = mobileInput.selectionStart
  
  let numbers = oldValue.replace(/\D/g, '') // 숫자 이외 문자는 제거
	
  // 하이픈 포함한 새 값 생성
  let formatted = ''
  if(numbers.length <= 3){
    formatted = numbers
  } else if(numbers.length <= 7){
    formatted = numbers.slice(0, 3) + '-' + numbers.slice(3)
  } else {
    formatted = numbers.slice(0, 3) + '-' + numbers.slice(3, 7) + '-' + numbers.slice(7, 11)
  }

  if(formatted != oldValue){
  	// 새로운 input창 값  
    mobileInput.value = formatted

	  // 새로운 커서 위치
    let newCursor = oldCursor
    if(oldCursor == 4 || oldCursor == 9){
      newCursor++
    }

    mobileInput.setSelectionRange(newCursor, newCursor)
  }
})

/* 인증 시간 타이머
================================================== */
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
            document.querySelector("#joinBtn").disabled = 'true'
        } else {
            updateDisplay()
        }
    }, 1000)
}

/* 페이지 로드 시 실행될 함수
================================================== */
document.addEventListener("DOMContentLoaded", function() {
  // 이메일 인증
  const uemailInput = document.querySelector('#uemail')
  const mailCodeButton = document.querySelector('#mailCodeButton')
  const mailCheckBox = document.querySelector('.mail-Check-Box')
  const mailCheckInput = document.querySelector('#mailCheckInput')
  const mailCheckSpan = document.querySelector('#mailCheckSpan')
  const joinBtn = document.querySelector('#joinBtn')

  // 메일 발송
  mailCodeButton.addEventListener('click', function(){
    let uemail = uemailInput.value
    console.log(`uemail:${uemail}`)
    if(uemail == null || uemail == "") {
      console.log('a')
      alert('이메일을 입력해주세요')
    } else {
      console.log('b')
      $.ajax({
        type: "get",
        url: "/userMail/send?uemail=" + uemail,
        success: function(data){
          if(data == "success"){
            alert('인증번호가 전송되었습니다.')
            mailCheckBox.style.display = 'block'
            startVerificationTimer(5 * 60) // 5분
          } else {
            alert('이메일을 확인해 주신 후 본인인증 버튼을 다시 눌러주세요.')
          }
          
        },
        error: function(error){
          console.error("email check fail", error)
        }
      })
      
    }
  })

  // 인증번호 확인 결과
  mailCheckInput.addEventListener('input', function(){
    let userCode = mailCheckInput.value

    $.ajax({
      type: "post",
      data: {userCode: userCode},
      url: "/userMail/check",
      success: function(data){
        mailCheckResultStyle(data)
      },
      error: function(error){
        console.error("email check fail", error)
      }
    })
  })
	
  /* 회원가입 버튼 활성화 조건
    - 이메일 인증 성공
    - 새 비밀번호 유효성 검사 통과
    - 새 비밀번호와 확인 비밀번호 일치
  ================================================== */
  let isEmailVerified = false // 이메일 인증 성공 여부

  // 조건에 따라 회원가입 버튼 활성화/비활성화 처리
  const updateJoinBtnState = () => {
    const isValid = validatePassword(newPasswordInput.value) // 비밀번호 유효성 검사
    const isMatched = checkPasswordMatch()                   // 비밀번호 일치 여부 확인

    joinBtn.disabled = !(isEmailVerified && isValid && isMatched) // 세 조건 모두 만족해야 활성화
  }

  // 이메일 인증번호 일치 여부에 따른 상태 처리 및 결과 출력
  const mailCheckResultStyle = (result) => {
    let output = ""
    if(result == "인증성공"){
      output = `
                  <span id="mailCheckSpan" class="mailCheckSuccess">${result}</span>
              `
      isEmailVerified = true
    } else{
      output = `
                  <span id="mailCheckSpan" class="mailCheckFail">${result}</span>
              `
      isEmailVerified = false
    }
    mailCheckSpan.innerHTML = output
    updateJoinBtnState()
  }

  // 새 비밀번호 입력 시 리스너
  newPasswordInput.addEventListener('input', updateJoinBtnState)

  // 새 비밀번호 확인 입력 시 리스너
  confirmPasswordInput.addEventListener('input', updateJoinBtnState)

})
