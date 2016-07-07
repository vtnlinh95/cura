package com.kms.cura.entity;

public enum DayOfTheWeek {
	MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6);

	int code;

	DayOfTheWeek(int code) {
		this.code = code;
	}

	public static DayOfTheWeek getDayOfTheWeek(int code) {
		for (DayOfTheWeek dayOfWeek : values()) {
			if (dayOfWeek.code == code) {
				return dayOfWeek;
			}
		}
		return null;
	}
	
	public int getCode() {
		return this.code;
	}

}
