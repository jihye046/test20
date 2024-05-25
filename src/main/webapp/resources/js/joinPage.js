document.addEventListener("DOMContentLoaded", function() {
  // 이메일 인증
  const uemailInput = document.querySelector('#uemail')
  const mailCodeButton = document.querySelector('#mailCodeButton')
  const mailCheckBox = document.querySelector('.mail-Check-Box')
  const mailCheckInput = document.querySelector('#mailCheckInput')
  const mailCheckSpan = document.querySelector('#mailCheckSpan')
  const joinBtn = document.querySelector('#joinBtn')

  mailCodeButton.addEventListener('click', function(){
    let uemail = uemailInput.value
    if(uemail == null || uemail == "") {
      alert('이메일을 입력해주세요')
    } else {
      alert('인증번호가 전송되었습니다')
      mailCheckBox.style.display = 'block'
    }
  })
  mailCheckInput.addEventListener('input', function(){
    let code = mailCheckInput.value

    $.ajax({
      type: "post",
      data: {code: code},
      url: "/user/emailCheck",
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
