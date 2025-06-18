// 아이디 마스킹
const maskUserId = (userId) => {
    if(!userId || userId.lenght < 4) {
        return userId
    } else {
        const prefixLength = 2
        const suffixLength = 2
        const maskLength = userId.length - prefixLength - suffixLength

        if(maskLength <= 0) return userId
        return userId.slice(0, prefixLength) + '*'.repeat(maskLength) + userId.slice(-suffixLength)
    }
}

const userIds = document.querySelectorAll(".found-id")
userIds.forEach(userId => {
    userId.textContent = maskUserId(userId.textContent)
})