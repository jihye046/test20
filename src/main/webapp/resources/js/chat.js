const element = document.querySelector("#userId")
const userId = element ? element.getAttribute("data-userId") : null

let name
let ws
const url = 'ws://localhost/socket/chatserver'

function connect(name){
    window.name = name
    const header = document.querySelector("#header")
    header.textContent(name)

    // 서버와 연결하기 > 소켓 생성
    ws = new WebSocket(url)

    ws.onopen = function(evt){
        log('서버 연결 성공')

        
    }
}