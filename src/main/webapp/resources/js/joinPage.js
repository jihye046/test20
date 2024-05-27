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
            alert('인증번호가 전송되었습니다!')
            mailCheckBox.style.display = 'block'
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
