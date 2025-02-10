let chatList = document.querySelector("#chatList")

/* 가장 아래로 스크롤 */
const scrollList = () => {
	if(chatList) {
		chatList.scrollTop = chatList.scrollHeight
	} else {
		alert('chatList 찾을 수 없음')
		return
	}
}

/* 대화창 출력 */
const print = (name, msg, side, state, time) => {
	const userSide = (window.name === 'hong2') ? 'me' : 'other'
	
	let temp = `
		<div class="item ${state} ${side}">
			<div>
				<div>${name}</div>
				<div>${msg}</div>
			</div>
			<div>${time}</div>
		</div>`

	if(chatList){
        const div = document.createElement('div')
        div.innerHTML = temp
        chatList.append(div)
	} else {
		alert('chatList 찾을 수 없음')
		return
	}
	
	scrollList() // 새로운 내용이 추가되면 가장 아래로 스크롤
}

/* 서버 연결 상태 출력 */
const log = (msg) => {
    console.log(`[${new Date().toLocaleTimeString()}] ${msg}`)
}

/* 이모티콘 출력 */
const printEmotion = (name, msg, side, state, time) => {
	let temp = `
		<div class="item ${state} ${side}">
			<div>
				<div>${name}</div>
				<div style="background-color:#fff; border:0;">
					<img src="../../../resources/images/emoticon/${msg}.png">
				</div>
				<div>${time}</div>
			</div>
		</div>`

	if(chatList){
		const div = document.createElement('div')
        div.innerHTML = temp
        chatList.append(div)
	} else {
		alert('chatList 찾을 수 없음')
		return
	}
	setTimeout(scrollList, 100) // 이미지 로딩 시간때문에 0.1초 시간차 둠
}

/* 웹소캣 연결 */
window.connect = () => {
	// msg 요소 여부 체크
    const msg = document.querySelector("#msg")
    if (msg) {
        msg.focus()
    } else {
        console.error('msg 요소 없음')
        return
    }
    
    // window.name 여부 체크
    const element = document.querySelector("#userId")
	const userId = element ? element.getAttribute("data-userId") : null

	window.name = `${userId}`
	if(!window.name) {
		console.log('window.name 없음')
		return
	}
	
	// 소켓 생성
    const ws = new WebSocket('ws://localhost/chatServer')
    /*
		code: 상태코드
			  1: 새로운 유저가 들어옴
			  2: 기존 유저가 나감
			  3: 메시지 전달
			  4: 이모티콘 전달
	*/
	
	// code: 1 웹소켓 연결이 성공한 경우
    ws.onopen = function(evt) {
		log('서버 연결 성공')
		
		let message = {
			code: '1', 
			sender: window.name, 
			receiver: '', 
			content: '', 
			regdate: new Date().toLocaleTimeString("ko-KR", {hour: "2-digit", minute: "2-digit"})
		}
		
        // 연결된 서버에게 메시지를 전송할 때: ws.send('전달할 메시지')
		ws.send(JSON.stringify(message))
		if(window.name === 'hong2'){
			print('', `안녕하세요. ${userId} 고객님😊<br>무엇을 도와드릴까요?`, 'me', 'state', message.regdate) // 관리자
		} else {
			print('', `안녕하세요. ${userId} 고객님😊<br>무엇을 도와드릴까요?`, 'other', 'state', message.regdate) // 클라이언트
		}
		
		msg.focus()
    }
    
    // code: 2, 채팅방을 나가는 경우
    const disconnect = () => {
		let message = {
			code: '2',
			sender: window.name,
			receiver: '',
			content: '',
			regdate: new Date().toLocaleTimeString("ko-KR", {hour: "2-digit", minute: "2-digit"})
		}
	
		ws.send(JSON.stringify(message))
	}
	
	window.addEventListener('beforeunload', function(event){ // beforeunload: 사용자가 페이지를 떠나려고 할 때 발생
		event.preventDefault()
		event.returnValue = '' // 크롬에서는 컨펌 창 메시지를 커스터마이징할 수 없으므로 공란으로 둠
		disconnect()
	})
	
	// cdoe: 3, 메시지를 전송하는 경우
	window.handleKeyDown = (event) => {
		if(event.key === 'Enter') {
			let message = {
				code: '3',
				sender: window.name,
				receiver: '',
				content: msg.value,
				regdate: new Date().toLocaleTimeString("ko-KR", {hour: "2-digit", minute: "2-digit"})
			}
			
			// code: 4, 이모티콘을 전송하는 경우 
			if(msg.value.startsWith('/')) {
				message.code = '4'
			}

			ws.send(JSON.stringify(message))
            msg.value = ''
			msg.focus()

			if(message.code == '3') {
				print(window.name, message.content, 'me', 'msg', message.regdate)	
			} else if(message.code == '4') {
				printEmotion(window.name, '고양이', 'me', 'msg', message.regdate)	
			}
		}
	}

    // 서버에서 클라이언트에게 전달한 메시지
    ws.onmessage = function(evt) {
		let message = JSON.parse(evt.data)
		console.log(`message: ${message}`)
		
		if(message.code == '1') {
			print('', `[${message.sender}]님이 들어왔습니다.`, 'other', 'state', message.regdate)
		} else if (message.code == '2') {
			print('', `[${message.sender}]님이 나갔습니다.`, 'other', 'state', message.regdate)
		} else if (message.code == '3') {
			print(message.sender, message.content, 'other', 'msg', message.regdate)
		} else if (message.code == '4') {
			printEmotion(message.sender, message.content, 'other', 'msg', message.regdate)
		}
    }
	
} /* connect */

/* 오늘 일자 표시 */
window.displayDate = () => {
    const dateDisplay = document.querySelector("#dateDisplay")
    if(!dateDisplay) return

    const today = new Date()
    const year = today.getFullYear()
    const month = ("0" + (today.getMonth() + 1)).slice(-2)
    const date = ("0" + today.getDate()).slice(-2)
    const dayList = ["일", "월", "화", "수", "목", "금", "토"]
    const day = dayList[today.getDay()] // today.getDay(): 요일을 숫자로 변환 (0 = 일요일, 1 = 월요일 ...)

    dateDisplay.innerText = `${year}.${month}.${date} (${day})`
}