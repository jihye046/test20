/* 수정 완료 메시지
================================================== */
const showProfileMessage = (msg) => {
    const profileMessage = document.querySelector("#profileMessage")
    profileImage.textContent = msg

    profileMessage.classList.add('show')
    setTimeout(() => {
        profileMessage.classList.remove('show')
    }, 2000)
}

/* 드롭다운 메뉴 닫기
================================================== */
const closeDropdownMenu = () => {
    const dropDownMenu = document.querySelector("#dropdownMenu")
    dropDownMenu.style.display = 'none'
}

/* 드롭다운 메뉴 토글
================================================== */
const profileImage = document.querySelector("#profileImage")
const toggleDropdwon = () => {
    profileImage.addEventListener('click', function(){
        const dropDownMenu = document.querySelector("#dropdownMenu")
        if(dropDownMenu.style.display == 'none' || dropDownMenu.style.display == '') {
            dropDownMenu.style.display = 'block'
        } else {
            dropDownMenu.style.display = 'none'
        }
    })
}

/* 프로필 이미지 변경
================================================== */
    // 기본 이미지로 변경
const setDefaultImage = () => {
    const defaultImage = document.querySelector("#defaultImage")
    defaultImage.addEventListener('click', function() {
        profileImage.src = '../../../resources/images/profile_default.png'
        closeDropdownMenu()
        showProfileMessage('프로필이 수정되었습니다.')
    })
}

    // 파일에서 사진 선택, 미리보기
const setProfileImageFromFile = () => {
    const fileImage = document.querySelector("#fileImage")
    const maxSize = 5 * 1024 * 1024 // 5MB
    const profileImageInput = document.querySelector("#profileImageInput")
    let selectedFile = null // 선택된 파일 저장

    fileImage.addEventListener('click', function() {
        if(profileImageInput) {
            // 파일 업로드
            const pond = FilePond.create(profileImageInput, {
                allowDrop: false, // 드래그 앤 드롭 사용 안함
                allowMultiple: false, // 여러 파일 선택 방지
                onaddfile: (error, file) => {
                    if(!error) {
                        if(file.file.size > maxSize) {
                            // 파일 크기 체크
                            alert('파일 크기는 5MB를 초과할 수 없습니다.')
                            pond.removeFile(file.id) // 업로드한 파일 제거
                        } else if (!file.file.type.startsWith('image/')) {
                            // 이미지 여부 체크
                            alert('이미지 파일만 업로드할 수 있습니다.')
                            pond.removeFile(file.id)
                        } else {
                            const imageUrl = URL.createObjectURL(file.file)
                            profileImage.src = imageUrl
                            selectedFile = file.file
                        }
                    } else {
                        alert('알 수 없는 오류가 발생했습니다.')
                    }
                }
            })
        } else {
            console.log('input 업로드 안됨')
        }
    })
    return () => selectedFile
}

/* 변경 버튼 클릭
================================================== */
const updateButton = document.querySelector("#updateButton")
const getSelectedFile = setProfileImageFromFile() // 클로저 함수 자체

updateButton.addEventListener('click', function(){
    const currentNickname = document.querySelector("#currentNickname").getAttribute("data-currentNickname")
    const newNickname = document.querySelector("#nickname").value.trim()
    const selectedFile = getSelectedFile() // 클로저 함수에 담긴 값
    const defaultImg = '../../../resources/images/profile_default.png'

    // 닉네임 검사
    if(!newNickname) {
        alert('닉네임을 입력해주세요.')
        return
    } else if(newNickname == currentNickname) {
        alert('동일한 닉네임입니다.')
        return
    }

    // 파일 검사
    if(selectedFile) {
        // 프로필 이미지, 닉네임 모두 변경하는 경우
        updateProfile(selectedFile, newNickname)
    } else {
        // 프로필 이미지를 변경하지 않고 닉네임만 변경하는 경우
        updateProfile(defaultImg, newNickname)
    } 
})

/* 프로필 이미지와 닉네임을 서버로 전송
================================================== */
const updateProfile = (newFile, newNickname) => {
    const formData = new FormData()

    if(newFile) {
        // 프로필 이미지, 닉네임 모두 변경하는 경우
        formData.append('profileImage', newFile)
    }
    // 닉네임은 항상 추가 (파일이 없더라도)
    formData.append('nickname', newNickname)

	// formData 내용 확인
    for (let [key, value] of formData.entries()) {
        console.log(key, value);
    }


    fetch('/user/updateProfile', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        const handleRedirect = (msg, redirectUrl) => {
            showProfileMessage(msg)
            window.location.href = redirectUrl
        }

        if(data.status == 'success') {
            handleRedirect(data.msg, '/board/paging')
        } else {
            handleRedirect(data.msg, '/user/updateProfileForm')
        }
    })
    .catch(error => {
    	console.log('Error', error)
    })
}

/* 페이지 로드 시 실행될 함수
================================================== */
toggleDropdwon()
setDefaultImage()
setProfileImageFromFile()
//updateNickname()
//updateProfile()