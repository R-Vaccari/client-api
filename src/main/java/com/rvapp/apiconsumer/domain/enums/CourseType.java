package com.rvapp.apiconsumer.domain.enums;

public enum CourseType {
	ENGLISH(1),
	GERMAN(2),
	ITALIAN(3),
	PORTUGUESE(4);
	
    private int code;
	
	private CourseType(Integer code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
