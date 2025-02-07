// let name;
const element = document.querySelector("#userId")
const userId = element ? element.getAttribute("data-userId") : null

/* 가장 아래로 스크롤 */
const scrollList = () => {
	const list = document.querySelector("#list")
	list.scrollTop = list.scrollHeight + 300 // list.scrollHeight: 전체 콘텐츠의 높이
}

/* 대화창 출력 */
const print = (name, msg, side, state, time) => {
	let temp = `
		<div class="item ${state} ${side}">
			<div>
				<div>${name}</div>
				<div>${msg}</div>
			</div>
			<div>${time}</div>
		</div>`
	let list = document.querySelector("#list")
	alert(temp)
	if(list){
		alert('list 찾음')
		list.append(temp)
	} else {
		alert('list 없음')
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
				<div style="background-color:#fff; boarder:0;">
					<img src="../../../resources/images/emoticon/${msg}.png">
				</div>
				<div>${time}</div>
			</div>
		</div>`

	const list = document.querySelector("#list")
	list.append(temp)
	if(list){
		list.append(temp)
	} else {
		console.error(error)
	}
	setTimeout(scrollList, 100) // 이미지 로딩 시간때문에 0.1초 시간차를 둠
}

/* 채팅방에 접속한 경우 */
const connect = () => {
	window.name = `${userId}`
	if(!window.name) {
		alert('window.name is null')
		return
	} 
    //window.name = name
    const header = document.querySelector("#header")
//    header.textContent(name)
//    header.textContent = name

    // 소켓 생성
    const ws = new WebSocket('ws://localhost/chatServer')
	
    // 웹소켓 연결이 성공한 경우
    ws.onopen = function(evt) {
        log('서버 연결 성공')
		
		/*
		code: 상태코드
			  1: 새로운 유저가 들어옴
			  2: 기존 유저가 나감
			  3: 메시지 전달
			  4: 이모티콘 전달
		*/
		alert(window.name)
		let message = {
			code: '1', // 상태코드
			sender: window.name, // 보내는 유저명
			receiver: '', // 받는 유저명
			content: '', // 대화 내용
			regdate: new Date().toLocaleString() // 날짜/시간
		}
		
        // 연결된 서버에게 메시지를 전송할 때: ws.send('전달할 메시지')
		ws.send(JSON.stringify(message))
		alert(123123)
		print('', '대화방에 참여했습니다.', 'me', 'state', message.regdate)
		
		document.addEventListener('DOMContentLoaded', function(){
			document.querySelector("#msg").focus()
		})
        
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

/* 채팅방을 나가는 경우 */
const disconnect = () => {
	let message = {
		code: '2',
		sender: window.name,
		receiver: '',
		content: '',
		regdate: new Date().toLocaleString()
	}

	ws.send(JSON.stringify(message))
}

window.addEventListener('beforeunload', function(event){ // beforeunload: 사용자가 페이지를 떠나려고 할 때 발생
	disconnect()
})

/* 메시지를 전송하는 경우 */
msg.addEventListener('keydown', function(event){
	if(event.key === 13) { // 13은 엔터키의 아스키코드
		let message = {
			code: '3',
			sender: window.name,
			receiver: '',
			content: document.querySelector("#msg").value,
			regdate: new Date().toLocaleString()
		}
		
		/* 이모티콘을 전송하는 경우 */
		if(document.querySelector("#msg").value.startsWith('/')) { // 전송메시지 '/'로 시작하면 이모티콘 전송으로 설정
			message.code = '4'
		}

		ws.send(JSON.stringify(message))
		document.querySelector("#msg").focus()

		if(message.code == '3') {
			print(window.name, message.content, 'me', 'msg', message.regdate)	
		} else if(message.code == '4') {
			printEmotion(window.name, message.content, 'me', 'msg', message.regdate)	
		}
	}
})


