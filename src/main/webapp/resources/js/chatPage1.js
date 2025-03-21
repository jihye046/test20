/* 전역 변수
================================================== */
let chatList = document.querySelector("#chatList")
let roomList = document.querySelector("#roomList")
let chatRecords = document.querySelector(".chat-records")
const searchTextInput = document.querySelector("#searchTextInput")

/* 채팅 아이콘 - 안읽은 메시지 총 개수
================================================== */
const getUnreadMessageTotalCount = () => {
	const userId = document.querySelector("#userId").getAttribute("data-userId")
	
	fetch(`/chat/getUnreadMessageTotalCount?receiver=${userId}`)
	.then(response => response.json())
	.then(data => {
		if(data > 0){
			const badge = document.querySelector(".badge")
			badge.innerHTML = `<div id="notificationBadge" class="notification-badge">${data}</div>`
		} else {
			badge.innerHTML = ''
		}
	})	
	.catch(error => {
		console.error('error: ', error)
	})
} 

/* 채팅 아이콘 클릭시 모달창 열기 
================================================== */
const loadChatRooms = () => {
	document.querySelector('#chatModal').style.display = 'flex'
	roomList.innerHTML = ''
	searchTextInput.value = ''
	getRoomList()
}

/* 채팅 목록 가져오기
================================================== */
const getRoomList = () => {
	const userId = document.querySelector("#userId").getAttribute("data-userId")
	
	fetch(`/chat/getRoomList?userId=${userId}`)
	.then(response => response.json())
	.then(data => {
		data.forEach(chatRoomDto => {
			printRoomList(chatRoomDto)
		})
		openChatRoom()
	})
	.catch(error => {
		console.error('Error:', error)
	})
}

/* 검색 목록 가져오기
================================================== */
const searchUser = () => {
	const searchText = searchTextInput.value
	const userId = document.querySelector("#userId").getAttribute("data-userId")

	roomList.innerHTML = ''		
	if(searchText != '') {
		fetch(`/chat/getRoomList?userId=${userId}&searchText=${searchText}`)
		.then(response => response.json())
		.then(data => {
			data.forEach(chatRoomDto => {
				printRoomList(chatRoomDto)
			})
			openChatRoom()
		})
		.catch(error => {
			console.error('Error:', error)
		})
	} else {
		getRoomList()
	}
}

/* 검색창 엔터키 리스너
================================================== */
const handleSearchOnEnter = () => {
	searchTextInput.addEventListener('keydown', (event) => {
		if(event.key == 'Enter') {
			searchUser()
		}
	})
}

/* 채팅 목록 클릭시 채팅방 열기
================================================== */
const openChatRoom = () => {
	document.querySelectorAll(".chat-room").forEach(room => {
		room.addEventListener('click', function() {
			const roomId = this.getAttribute('data-room-id')
			const otherUserId = this.querySelector(".userNickname").textContent
			const imageUrl = this.querySelector("img").src
			const unreadBadge = this.querySelector(".message-badge")
			const userId = document.querySelector("#userId").getAttribute("data-userId")

			hideUnreadBadge(unreadBadge, roomId, userId)
			getChatHistory(roomId, imageUrl, otherUserId)
			connect()
		})
	})
}

/* 각 채팅방 안읽음 표시 없애기
================================================== */
const hideUnreadBadge = (unreadBadge, roomId, userId) => {
	if(unreadBadge) {
		fetch('/chat/setIsRead', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				roomId: roomId,
				receiver: userId
			})
		})
		.then(response => {
			if(response.ok) {
				unreadBadge.style.display = 'none'
				getUnreadMessageTotalCount()
			} else {
				console.error('failed to update')
			}
		})
		.catch(error => {
			console.error('error: ', error)
		})
	}
}

/* 채팅방 내용 불러오기
================================================== */
const getChatHistory = (roomId, imageUrl, otherUserId) => {
	fetch(`/chat/getChatHistory?roomId=${roomId}`)
	.then(response => response.json())
	.then(data => {
		chatList.innerHTML = ''

		data.forEach(messageDto => {
			if(messageDto.sender == userId) {
				print(messageDto.sender, messageDto.content, 'me', 'msg', messageDto.regTime)	
			} else {
				print(messageDto.sender, messageDto.content, 'other', 'msg', messageDto.regTime)
			}
		})

		printHeader(imageUrl, otherUserId)
		document.querySelector('.chat-rooms').classList.add('shrink')
		document.querySelector('.chat-records').classList.add('show')
	})
	.catch(error => {
		console.error('error: ', error)
	})
}

/* 메시지 전송
================================================== */
function sendMessage() {
    const input = document.querySelector('.chat-input input')
    const message = input.value.trim()
    if (message !== "") {
        const newMessage = document.createElement('div')
        newMessage.classList.add('chat-bubble', 'chat-bubble-right')
        newMessage.textContent = message
        document.querySelector('.chat-records').appendChild(newMessage)
        input.value = ""
    }
}

/* 모달 밖 클릭 시 닫기
================================================== */	
window.onclick = function(event) {
	const galleryModal = document.querySelector('.gallery-modal')
	const chatModal = document.querySelector('#chatModal')
    
    if (event.target == galleryModal) {
        galleryModal.style.display = 'none'
    } else if (event.target == chatModal) {
    	chatModal.style.display = 'none'
    }
	
	document.querySelector('.chat-rooms').classList.remove('shrink')
	document.querySelector('.chat-records').classList.remove('show')
}

/* 새로운 내용이 추가되면 가장 아래로 스크롤
================================================== */
const scrollList = () => {
	if(chatList) {
		chatList.scrollTop = chatList.scrollHeight
	} else {
		alert('chatList 찾을 수 없음')
		return
	}
}

/* 채팅방 상대정보(header) 출력
================================================== */
const printHeader = (uprofile_image, otherUserId) => {
	const header = document.querySelector("#header")
	console.log(`printHeader(): ${otherUserId}`)
	header.innerHTML = 
	`
		<div class="header">
			<img src=${uprofile_image} alt="image" class="user-avatar">
			<span class="userNickname" data-userNickname="${otherUserId}">${otherUserId}</span>
		</div>
	`
}

/* 채팅방 목록 출력
================================================== */
const printRoomList = (chatRoomDto) => {
	const messageDto = chatRoomDto["messageDto"]
	const otherUserId = chatRoomDto["otherUserId"]
	const uprofile_image = chatRoomDto["uprofile_image"]
	const unreadMessageCount = chatRoomDto["unreadMessageCount"]
	const today = displayDate()
	const messageTime = (messageDto.regdate == today) ? messageDto.regTime : messageDto.regdate
	
	let temp = 
	`
		<div class="chat-room" data-room-id=${messageDto.roomId}>
			<img src="${uprofile_image}" alt="image" class="user-avatar"/>
			<div class="chat-room-info">
            	<span class="userNickname">${otherUserId}</span>
            	<span class="last-message">${messageDto.content}</span>
				<span class="chat-time">${messageTime}</span>	
        	</div>
	`
        	
	if(unreadMessageCount != 0) {
		temp += `<span class="message-badge">${unreadMessageCount}</span>`
	}
	
	temp += `</div>`
	
	if(roomList){
        const div = document.createElement('div')
        div.innerHTML = temp
        roomList.append(div)
	} else {
		alert('chatList 찾을 수 없음')
		return
	}
	
	printHeader(uprofile_image, otherUserId)
	scrollList()
}

/* 채팅 일자 출력
================================================== */
const printDate = (regdate) => {
	let temp = 
	`
		<div class="date-wrapper">
			<div class="date">${regdate}</div>
		</div>
	`
	
	if(chatList){
        const div = document.createElement('div')
        div.innerHTML = temp
        chatList.append(div)
	} else {
		alert('chatList 찾을 수 없음')
		return
	}
	
	scrollList()
}

/* 채팅창 출력
================================================== */
const print = (name, msg, side, state, time) => {
	let temp = 
	`
		<div class="item ${state} ${side}">
			<div>
				<div>${name}</div>
				<div>${msg}</div>
			</div>
			<div>${time}</div>
		</div>
	`

	if(chatList) {
        const div = document.createElement('div')
        div.innerHTML = temp
        chatList.append(div)
	} else {
		alert('chatList 찾을 수 없음')
		return
	}
	
	scrollList()
}

/* 오늘 일자 표시
================================================== */
window.displayDate = () => {
	const dateDisplay = document.querySelector("#dateDisplay")
	if(!dateDisplay) return

	const today = new Date()
	const year = today.getFullYear()
	const month = ("0" + (today.getMonth() + 1)).slice(-2)
	const date = ("0" + today.getDate()).slice(-2)
	const dayList = ["일", "월", "화", "수", "목", "금", "토"]
	const day = dayList[today.getDay()]

	dateDisplay.innerText = `${year}.${month}.${date} (${day})`
	return `${year}년 ${month}월 ${date}일 ${day}요일`
}

/* 서버 연결 상태 출력
================================================== */
const log = (msg) => {
    console.log(`[${new Date().toLocaleTimeString()}] ${msg}`)
}

/* 이모티콘 출력
================================================== */
const printEmotion = (name, msg, side, state, time) => {
	let temp = 
	`
		<div class="item ${state} ${side}">
			<div>
				<div>${name}</div>
				<div style="background-color:#fff; border:0;">
					<img src="../../../resources/images/emoticon/${msg}.png">
				</div>
				<div>${time}</div>
			</div>
		</div>
	`

	if(chatList){
		const div = document.createElement('div')
        div.innerHTML = temp
        chatList.append(div)
	} else {
		alert('chatList 찾을 수 없음')
		return
	}
	setTimeout(scrollList, 100)
}

/* 웹소캣 연결
================================================== */
window.connect = () => {

    const msg = document.querySelector("#msg")
    if (msg) {
        msg.focus()
    } else {
        console.error('msg 요소 없음')
        return
    }
    
    // window.name 여부 체크
    const userId = document.querySelector("#userId").getAttribute("data-userId")
	const receiver = document.querySelector("#userNickname").getAttribute("data-userNickname")
	console.log(userId)
	console.log(receiver)

	window.name = `${userId}`
	if(!window.name) {
		log('window.name 없음')
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
	// 내 대화창
	/* 
    ws.onopen = function(evt) {
		log('서버 연결 성공')
		
		let message = {
			code: '1', 
			sender: window.name, 
			receiver: receiver, 
			content: '', 
			regdate: displayDate(),
			regTime: new Date().toLocaleTimeString("ko-KR", {hour: "2-digit", minute: "2-digit"})
		}
        // 연결된 서버에게 메시지를 전송할 때: ws.send('전달할 메시지')
		ws.send(JSON.stringify(message))
		
		
		//print('', `대화방에 참여했습니다.`, 'me', 'state', message.regTime)
		
		// 과거 메시지가 있으면 가져오고 오늘일자를 그 밑에 보여줌, 그 밑에는 오늘 대화 내용이 들어가도록
		msg.focus()
    }
		*/
    
    // code: 2, 채팅방을 나가는 경우
    const disconnect = () => {
		let message = {
			code: '2',
			sender: window.name,
			receiver: '',
			content: '',
			regdate: displayDate(),
			regTime: new Date().toLocaleTimeString("ko-KR", {hour: "2-digit", minute: "2-digit"})
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
				receiver: receiver,
				content: msg.value,
				regdate: displayDate(),
				regTime: new Date().toLocaleTimeString("ko-KR", {hour: "2-digit", minute: "2-digit"})
			}
			
			// code: 4, 이모티콘을 전송하는 경우 
			if(msg.value.startsWith('/')) {
				message.code = '4'
			}

			ws.send(JSON.stringify(message))
            msg.value = ''
			msg.focus()

			if(message.code == '3') {
				print(window.name, message.content, 'me', 'msg', message.regTime)	
			} else if(message.code == '4') {
				printEmotion(window.name, '고양이', 'me', 'msg', message.regTime)	
			}
		}
	}

    // 서버에서 클라이언트에게 전달한 메시지(상대방 대화창)
    ws.onmessage = function(msg) {
		let message = JSON.parse(msg.data)
		console.log(msg.data)
		console.log(message)
		
		if(message.code == '1') {
			if(message.sender == window.name) {
				print(message.sender, message.content, 'me', 'msg', message.regTime)
			} else {
				print(message.sender, message.content, 'other', 'msg', message.regTime)	
			}
			displayDate()
			
			//print('', `[${message.sender}]님이 들어왔습니다.`, 'other', 'state', message.regTime)
		} else if (message.code == '2') {
			print('', `[${message.sender}]님이 나갔습니다.`, 'other', 'state', message.regTime)
		} else if (message.code == '3') {
			print(message.sender, message.content, 'other', 'msg', message.regTime)
		} else if (message.code == '4') {
			printEmotion(message.sender, message.content, 'other', 'msg', message.regTime)
		}
    }
	
}

/* 페이지 로드 시 실행될 함수
================================================== */
window.onload = function() {
	getUnreadMessageTotalCount()
	handleSearchOnEnter()
	
	// 클릭 이벤트 전파 차단
	chatRecords.addEventListener('click', function(event) {
		event.stopPropagation(); 
	})
}