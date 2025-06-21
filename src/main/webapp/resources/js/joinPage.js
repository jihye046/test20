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
            document.querySelector("#joinBtn").disabled = 'true'
        } else {
            updateDisplay()
        }
    }, 1000)
}

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

  const mailCheckResultStyle = (result) => {
    let output = ""
    if(result == "인증성공"){
      output = `
                  <span id="mailCheckSpan" class="mailCheckSuccess">${result}</span>
              `
      joinBtn.disabled = false
    } else{
      output = `
                  <span id="mailCheckSpan" class="mailCheckFail">${result}</span>
              `
      joinBtn.disabled = true
    }
    mailCheckSpan.innerHTML = output
  }

})
