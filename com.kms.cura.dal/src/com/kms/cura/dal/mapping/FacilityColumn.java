package com.kms.cura.dal.mapping;

public enum FacilityColumn {
	ID(EntityColumn.ID), NAME(EntityColumn.NAME), ADDRESS("address"), PHONE("phone");
	String columnName;

	private FacilityColumn(String columnName) {
		this.columnName = columnName;
	}

	private FacilityColumn(EntityColumn entityColumn) {
		this.columnName = entityColumn.getColumnName();
	}

	public final String getColumnName() {
		return columnName;
	}

	public static final String TABLE_NAME = "Facilities";

}