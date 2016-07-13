package com.kms.cura.dal.mapping;

public enum AppointmentColumn {
	DOCTOR_ID("doctor_id"),PATIENT_ID("patient_id"),FACILITY_ID ("facility_id"),APPT_DAY("appt_day"),START_TIME("start_time"),END_TIME("end_time"),STATUS("current_status");
	
	String columnName;
	
	private AppointmentColumn(String columnName) {
		this.columnName = columnName;
	}

	private AppointmentColumn(EntityColumn entityColumn) {
		this.columnName = entityColumn.getColumnName();
	}

	public  String getColumnName() {
		return columnName;
	}

	public static final String TABLE_NAME = "appointments";
}
