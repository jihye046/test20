document.querySelector("#nextStepButton").addEventListener('click', (e) => {
    e.preventDefault()

    const userId = document.querySelector("#userId").value

    axios.get('/user/check-userid-match?userId=' + userId)
    .then(exists => {
        if(exists.data){
            window.location.href='/user/verify-user?mode=password&userId='+userId
        } else {
            alert('존재하지 않는 아이디입니다.')
        }
    })
    .catch(error => {
        console.error('error: ', error)
    })
})