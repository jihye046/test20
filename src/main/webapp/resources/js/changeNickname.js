const changeNicknameBtn = document.querySelector("#changeNicknameBtn")

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
        console.log(data)
    })
    .catch(error => {
        console.error('Error:', error)
    })
})