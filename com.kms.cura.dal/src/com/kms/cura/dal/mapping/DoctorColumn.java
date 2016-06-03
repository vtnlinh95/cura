package com.kms.cura.dal.mapping;

public enum DoctorColumn {
	USER_ID("user_id"), NAME("name"), PHONE("phone"), DEGREE_ID("degree_id"), RATING("rating"), EXPERIENCE(
			"experience"), MIN_PRICE("price_min"), MAX_PRICE(
					"price_max"), GENDER("gender"), BIRTH("birth"), LOCATION("location"), INSURANCE("insurance");

	String columnName;

	private DoctorColumn(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}
}