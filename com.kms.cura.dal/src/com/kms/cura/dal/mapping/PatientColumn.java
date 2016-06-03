package com.kms.cura.dal.mapping;

public enum PatientColumn {
	USER_ID("user_id"), NAME("name"), GENDER("gender"), BIRTH("birth"), LOCATION("location"), INSURANCE(
			"insurance"), HEALTH_CONCERN("health_concern");

	String columnName;

	private PatientColumn(String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnName() {
		return columnName;
	}
}