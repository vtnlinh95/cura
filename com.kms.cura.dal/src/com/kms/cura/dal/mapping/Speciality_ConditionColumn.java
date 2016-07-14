package com.kms.cura.dal.mapping;

public enum Speciality_ConditionColumn {
	CONDITION_ID("condition_id"), SPECIALITY_ID("speciality_id");
	String columnName;

	private Speciality_ConditionColumn(String columnName) {
		this.columnName = columnName;
	}

	private Speciality_ConditionColumn(EntityColumn entityColumn) {
		this.columnName = entityColumn.getColumnName();
	}

	public final String getColumnName() {
		return columnName;
	}

	public static final String TABLE_NAME = "specialities_conditions";
}
