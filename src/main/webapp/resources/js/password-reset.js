
const newPasswordInput = document.querySelector("#newPassword")
const confirmPasswordInput = document.querySelector("#confirmPassword")
const requirementMessage = document.querySelector("#passwordRequirement") // 비밀번호 요구사항 출력 필드
const mismatchMessage = document.querySelector("#passwordMismatchMessage") // 비밀번호 일치여부 출력 필드
const resetButton = document.querySelector("#resetPasswordButton")

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

/* 폼 전송
================================================== */
const form = document.querySelector("#password-reset-form")
form.addEventListener('submit', (e) => {
    e.preventDefault()

    const isValid = validatePassword(newPasswordInput.value)
    const isMatched = checkPasswordMatch()

    if(!isValid || !isMatched){
        alert('비밀번호를 다시 확인해주세요.')
        return
    }

    form.submit()
})

/* 페이지 로드 시 실행될 함수
================================================== */
    // 새 비밀번호 입력 시 리스너
newPasswordInput.addEventListener('input', () => {
    const isValid = validatePassword(newPasswordInput.value) // 유효성 통과하면 true
    const isMatched = checkPasswordMatch() // 일치하면 true

    resetButton.disabled = !(isValid && isMatched) // true: 비활성화, false: 활성화
})

    // 새 비밀번호 확인 입력 시 리스너
confirmPasswordInput.addEventListener('input', () => {
    const isValid = validatePassword(newPasswordInput.value) // 유효성 통과하면 true
    const isMatched = checkPasswordMatch() // 일치하면 true

    resetButton.disabled = !(isValid && isMatched) // true: 비활성화, false: 활성화
})