package com.kms.cura.entity;

import java.util.List;

public class FacilityEntity extends Entity {
	private String address;
	private String phone;
	private List<OpeningHour> openingHours;

	public FacilityEntity(String id, String name,String address, String phone, List<OpeningHour> openingHours) {
		super(id, name);
		this.address = address;
		this.phone = phone;
		this.openingHours = openingHours;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<OpeningHour> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(List<OpeningHour> openingHours) {
		this.openingHours = openingHours;
	}
}
