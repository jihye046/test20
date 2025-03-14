<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 채팅 아이콘 -->
    <div id="chatIconWrapper">
		<div id="chatIcon" class="chat-icon" onclick="openChatModal()">
	    	<i class="fas fa-comment-dots"></i> 
	       	<div id="notificationBadge" class="notification-badge">3</div>
	   	</div>
    </div>
    
    <!-- 모달창 -->
    <div id="chatModal" class="chat-modal">
       	<!-- 채팅방 목록 -->
        <div class="chat-modal-content">
	        <div class="chat-rooms">
	        	<div class="input-wrapper">
			    	<i class="fa-solid fa-magnifying-glass" style="color: #6d6f74;"></i>
			    	<input placeholder="검색" type="text">
			    </div>
	            <div class="no-chat-rooms-message">채팅방이 없습니다.</div> <!-- 채팅방이 없을 때 표시되는 멘트 -->
	            <div class="chat-room" id="chatRoom1">
	            	<img src="https://buly.kr/AapLwSi" alt="image" class="user-avatar"/>
	            	<div class="chat-room-info">
		            	<span class="userNickname">사용자1</span>
		            	<span class="last-message">마지막 대화내용입니다!</span>
	            	</div>
	            	<span class="chat-time">오후 02:14</span>
	            	<span class="message-badge">3</span>
            	</div>
            	<div class="chat-room" id="chatRoom2">
	            	<img src="https://buly.kr/FLXoQXC" alt="image" class="user-avatar"/>
	            	<div class="chat-room-info">
		            	<span class="userNickname">사용자2</span>
		            	<span class="last-message">반갑습니다.</span>
	            	</div>
	            	<span class="chat-time">2025.03.13</span>
	            	<span class="message-badge">3</span>
            	</div>
	        </div>
	        
	        <!-- 채팅방 -->
	        <div class="chat-records">
	            <div id="header">
	            	<img src="https://buly.kr/AapLwSi" alt="image" class="user-avatar">
	            	<span class="userNickname">사용자1</span>
            	</div>
				<div id="dateDisplay"></div>
				<div id="chatList"></div>
				<input type="text" id="msg" placeholder = "메시지를 입력하세요." onkeydown="handleKeyDown(event)">
	        </div>
        </div>
    </div>
    
<script src="../../../resources/js/chatPage1.js"></script>
