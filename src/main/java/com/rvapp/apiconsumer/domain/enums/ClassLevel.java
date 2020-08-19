package com.rvapp.apiconsumer.domain.enums;

public enum ClassLevel {
	BEGINNER(1),
	INTERMEDIATE(2),
	ADVANCED(3),
	BUSINESS(4);
	
    private int code;
	
	private ClassLevel(Integer code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
