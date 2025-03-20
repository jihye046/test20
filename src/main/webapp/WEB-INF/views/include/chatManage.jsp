<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 채팅 아이콘 -->
    <div id="chatIconWrapper">
		<div id="chatIcon" class="chat-icon" onclick="loadChatRooms()">
	    	<i class="fas fa-comment-dots"></i> 
	    	<div class="badge"></div>
	   	</div>
    </div>
    
    <!-- 모달창 -->
    <div id="chatModal" class="chat-modal">
       	<!-- 채팅방 목록 컨테이너 -->
        <div class="chat-modal-content">
	        <div class="chat-rooms">
	        	<!-- 채팅방 검색 -->
	        	<div class="input-wrapper">
			    	<i class="fa-solid fa-magnifying-glass" style="color: #6d6f74;"></i>
			    	<input placeholder="검색" type="text">
			    </div>
<!-- 	            <div class="no-chat-rooms-message">채팅방이 없습니다.</div> 채팅방이 없을 때 표시되는 멘트 -->
				<!-- 채팅방 목록 -->
				<div class="roomList" id="roomList"></div>
	        </div>
	        
	        <!-- 채팅 내용 -->
	        <div class="chat-records" >
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
<div id="userId" data-userId="${sessionScope.userId}"></div>