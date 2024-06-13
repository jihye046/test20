package com.my.ex.dto.google;


public class GoogleCallbackDto {
	private String callbackCode;
	private String callbackState;
	private String callbackError;
	private String callbackError_Description;

	public GoogleCallbackDto() {}

	public GoogleCallbackDto(String callbackCode, String callbackState, String callbackError,
			String callbackError_Description) {
		this.callbackCode = callbackCode;
		this.callbackState = callbackState;
		this.callbackError = callbackError;
		this.callbackError_Description = callbackError_Description;
	}

	public String getCallbackCode() {
		return callbackCode;
	}

	public void setCallbackCode(String callbackCode) {
		this.callbackCode = callbackCode;
	}

	public String getCallbackState() {
		return callbackState;
	}

	public void setCallbackState(String callbackState) {
		this.callbackState = callbackState;
	}

	public String getCallbackError() {
		return callbackError;
	}

	public void setCallbackError(String callbackError) {
		this.callbackError = callbackError;
	}

	public String getCallbackError_Description() {
		return callbackError_Description;
	}

	public void setCallbackError_Description(String callbackError_Description) {
		this.callbackError_Description = callbackError_Description;
	}

	@Override
	public String toString() {
		return "GoogleCallbackDto [callbackCode=" + callbackCode + ", callbackState=" + callbackState
				+ ", callbackError=" + callbackError + ", callbackError_Description=" + callbackError_Description + "]";
	}

}
