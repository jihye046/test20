/* 전역 변수
================================================== */
	// 이메일 관련
let isEmailVerified = false                                                        // 이메일 인증 성공 여부

	// 비밀번호 관련
const newPasswordInput = document.querySelector("#password")                       // 설정할 비밀번호 필드
const confirmPasswordInput = document.querySelector("#confirmPassword")            // 새 비밀번호 확인 필드
const passwordRequirementMessage = document.querySelector("#passwordRequirement")  // 비밀번호 요구사항 출력 필드
const mismatchMessage = document.querySelector("#passwordMismatchMessage")         // 비밀번호 일치여부 출력 필드

	// 아이디 관련
const userIdInput = document.querySelector("#userId")
const idRequirementMessage = document.querySelector("#idRequirement")              // 아이디 요구사항 출력 필드

	// 버튼
const joinBtn = document.querySelector("#joinBtn")                                 // 회원가입 버튼

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

/* 아이디 유효성 검사
================================================== */
const validateId = (id) => {
    const minLenght = 4                           // 최소 4자 이상
    const maxLenght = 12                          // 최대 12자 이하
    const hasSpace = /\s/.test(id)                // 스페이스, 탭, 줄바꿈이 있으면 true, 없으면 false
    const isFirstCharLower = /^[a-z]/.test(id)    // 첫 글자가 소문자이면 true, 아니면 false
    const hasInvalidChar = /[^a-z0-9_.]/.test(id) // 허용되지 않은 문자 포함 시 true, 없으면 false
    
    if(!isFirstCharLower){
        setInvalidStyle(idRequirementMessage, '첫 글자는 소문자로 시작해야 합니다.')
        return false
    } else if(hasInvalidChar){
        setInvalidStyle(idRequirementMessage, '아이디는 소문자로 시작해야 하며, 소문자, 숫자, 특수문자(_, .)만 사용할 수 있습니다.')
        return false
    } else if(id.length < minLenght || id.length > maxLenght){
        setInvalidStyle(idRequirementMessage, '아이디는 4자 이상 12자 이하로 입력해주세요.')
        return false
    } else if(hasSpace){
        setInvalidStyle(idRequirementMessage, '아이디에 공백은 사용할 수 없습니다.')
        return false
    } else {
        setValidStyle(idRequirementMessage, '')
        return true
    }
}

/* 아이디 중복 검사
================================================== */

/* 비밀번호 유효성 검사
================================================== */
const validatePassword = (password, showMessage = true) => {
    const minLenght = 8                      // 최소 8자 이상
    const maxLenght = 16                     // 최대 16자 이하
    const hasSpace = /\s/.test(password)     // 스페이스, 탭, 줄바꿈이 있으면 true, 없으면 false
    const hasUpper = /[A-Z]/.test(password)  // 대문자가 하나 이상이면 true, 없으면 false
    const hasLower = /[a-z]/.test(password)  // 소문자가 하나 이상이면 true, 없으면 false
    const hasNumber = /[0-9]/.test(password) // 숫자가 하나 이상이면 true, 없으면 false
    const hasSpecial = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(password) // 특수문자가 하나 이상이면 true, 없으면 false
    
    if(password.length < minLenght || password.length > maxLenght){
        // 사용자가 비밀번호 입력 전에도 경고 메시지가 뜨는 현상을 막기 위해 사용
        if(showMessage){
          setInvalidStyle(passwordRequirementMessage, '비밀번호는 8자 이상 16자 이하로 입력해주세요.')
        }
        return false
    } else if(hasSpace){
        if(showMessage){
          setInvalidStyle(passwordRequirementMessage, '비밀번호에 공백은 사용할 수 없습니다.')
        }
        return false
    } else if(!hasUpper){
        if(showMessage){
          setInvalidStyle(passwordRequirementMessage, '비밀번호에 최소 하나 이상의 대문자가 포함되어야 합니다.')
        }
        return false
    } else if(!hasLower){
        if(showMessage){
          setInvalidStyle(passwordRequirementMessage, '비밀번호에 최소 하나 이상의 소문자가 포함되어야 합니다.')
        }
        return false
    } else if(!hasNumber){
        if(showMessage){
          setInvalidStyle(passwordRequirementMessage, '비밀번호에 최소 하나 이상의 숫자가 포함되어야 합니다.')
        }
        return false
    } else if(!hasSpecial){
        if(showMessage){
          setInvalidStyle(passwordRequirementMessage, '비밀번호에 최소 하나 이상의 특수문자가 포함되어야 합니다.')
        }
        return false
    } else {
        if(showMessage){
          setValidStyle(passwordRequirementMessage, '')
        }
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

/* 휴대폰 번호 입력 시 하이픈 자동 생성
================================================== */
const mobileInput = document.querySelector("#umobile")

mobileInput.addEventListener('input', (e) => {
  const oldValue = mobileInput.value
  const oldCursor = mobileInput.selectionStart
  
  // 숫자 이외 문자는 제거
  let numbers = oldValue.replace(/\D/g, '') 
	
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
    // 시간 초기화
    clearInterval(timerInterval) 
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

/* 폼 전송 제어
================================================== */
const form = document.querySelector("#join-form")
form.addEventListener('submit', (e) => {
  e.preventDefault()

  const isPasswordValid = validatePassword(newPasswordInput.value)
  const isPasswordMatched = checkPasswordMatch()
  const isIdValid = validateId(userIdInput.value)

  if(!isEmailVerified){
    alert('이메일 인증을 다시 진행해주세요.')
    return
  } else if(!isPasswordValid || !isPasswordMatched){
    alert('비밀번호를 다시 확인해주세요.')
    return
  } else if(!isIdValid) {
    alert('아이디를 다시 확인해주세요.')
    return
  }

  //form.submit()
})

/* 페이지 로드 시 실행될 함수
================================================== */
document.addEventListener("DOMContentLoaded", function() {
  // 이메일 인증
  const uemailInput = document.querySelector('#uemail')
  const mailCodeButton = document.querySelector('#mailCodeButton')
  const mailCheckBox = document.querySelector('.mail-Check-Box')
  const mailCheckInput = document.querySelector('#mailCheckInput')
  const mailCheckSpan = document.querySelector('#mailCheckSpan')

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
  	// 조건에 따라 회원가입 버튼 활성화/비활성화 처리
  const updateJoinBtnState = () => {
      const isPasswordValid = validatePassword(newPasswordInput.value, false) // 비밀번호 유효성 검사
      const isPasswordMatched = checkPasswordMatch(false)                     // 비밀번호 일치 여부 확인
      const isIdValid = validateId(userIdInput.value, false)                  // 아이디 유효성 검사

      joinBtn.disabled = !(isEmailVerified && isPasswordValid && isPasswordMatched && isIdValid) // 네 조건 모두 만족해야 활성화
  }

  	// 이메일 인증번호 일치 여부에 따른 상태 처리 및 결과 출력
  const mailCheckResultStyle = (result) => {
      let output = ""
      if(result == "인증성공"){
        output = `<span id="mailCheckSpan" class="mailCheckSuccess">${result}</span>`
        isEmailVerified = true
      } else{
        output = `<span id="mailCheckSpan" class="mailCheckFail">${result}</span>`
        isEmailVerified = false
      }
      mailCheckSpan.innerHTML = output
      updateJoinBtnState()
  }

  	// 새 비밀번호 입력 시 리스너
  newPasswordInput.addEventListener('input', () => {
      validatePassword(newPasswordInput.value, true)
      checkPasswordMatch(true)
      updateJoinBtnState()
  })

  	// 새 비밀번호 확인 입력 시 리스너
  confirmPasswordInput.addEventListener('input', () => {
      validatePassword(newPasswordInput.value, true)
      checkPasswordMatch(true)
      updateJoinBtnState()
  })

  	// 아이디 입력 시 리스너
  userIdInput.addEventListener('input', () => {
      validateId(userIdInput.value, true)
      updateJoinBtnState()
  })
  
})
