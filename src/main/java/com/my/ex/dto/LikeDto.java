package com.my.ex.dto;

public class LikeDto {
	private int likeId;
	private int bId;
	private String userId;
	
	public LikeDto() {}

	public LikeDto(int likeId, int bId, String userId) {
		super();
		this.likeId = likeId;
		this.bId = bId;
		this.userId = userId;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LikeDto [likeId=" + likeId + ", bId=" + bId + ", userId=" + userId + "]";
	}
	
}
