const changeNicknameBtn = document.querySelector("#changeNicknameBtn")

// 닉네임 변경
changeNicknameBtn.addEventListener('click', function(){
    const newNickname = document.querySelector("#nickname").value
    fetch('/user/chaneNickname', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nickname: newNickname
        })
    })
    .then((response) => response.json())
    .then((data) => {
        if(data.status == 'success'){
            alert(data.msg)
        } else {
            alert(data.msg)
        }
    })
    .catch(error => {
        console.error('Error:', error)
    })
})