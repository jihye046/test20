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
    	const handleRedirect = (msg, redirectUrl) => {
    		alert(msg)
            window.location.href = redirectUrl		
    	}
    
    	if(data.status == 'success') {
    		handleRedirect(data.msg, '/board/paging')
    	} else {
    		handleRedirect(data.msg, '/user/changeNicknameForm') 
        }
    })
    .catch(error => {
        console.error('Error:', error)
    })
})