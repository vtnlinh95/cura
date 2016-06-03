package com.kms.cura.dal.database;

public class ReferenceTableRow {
	private String firstRefKey;
	private String firstRefValue;
	private String secondRefKey;
	private String secondRefValue;

	public String getFirstRefKey() {
		return firstRefKey;
	}

	public void setFirstRefKey(String firstRefKey) {
		this.firstRefKey = firstRefKey;
	}

	public String getFirstRefValue() {
		return firstRefValue;
	}

	public void setFirstRefValue(String firstRefValue) {
		this.firstRefValue = firstRefValue;
	}

	public String getSecondRefKey() {
		return secondRefKey;
	}

	public void setSecondRefKey(String secondRefKey) {
		this.secondRefKey = secondRefKey;
	}

	public String getSecondRefValue() {
		return secondRefValue;
	}

	public void setSecondRefValue(String secondRefValue) {
		this.secondRefValue = secondRefValue;
	}
}