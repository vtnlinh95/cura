package com.kms.cura.entity;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.kms.cura.entity.user.DoctorUserEntity;

public class FacilityEntity extends Entity {
	public final static String FACILITY_LIST = "facility_list";
	private String address;
	private String phone;
	private List<OpeningHour> openingHours;

	public FacilityEntity(String id, String name, String address, String phone, List<OpeningHour> openingHours) {
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

	public static Type getFacilityType() {
		Type type = new TypeToken<FacilityEntity>() {
		}.getType();
		return type;
	}
}
