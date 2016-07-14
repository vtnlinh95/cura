package com.kms.cura.dal.mapping;

public enum Condition_SymptomColumn {
	CONDITION_ID("condition_id"), SYMPTOM_ID("symptom_id");
	String columnName;

	private Condition_SymptomColumn(String columnName) {
		this.columnName = columnName;
	}

	private Condition_SymptomColumn(EntityColumn entityColumn) {
		this.columnName = entityColumn.getColumnName();
	}

	public final String getColumnName() {
		return columnName;
	}

	public static final String TABLE_NAME = "conditions_symptoms";
}
