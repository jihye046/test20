const openChatWindow = () => {
    const chatWindow = window.open('/user/chat', 'chat', 'width=400,height=500,scrollbars=yes')
    
    chatWindow.addEventListener('load', function(){
    	chatWindow.displayDate()
		chatWindow.connect()
    })
}