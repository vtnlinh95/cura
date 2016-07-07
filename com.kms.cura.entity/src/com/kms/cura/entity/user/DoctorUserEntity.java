package com.kms.cura.entity.user;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.kms.cura.entity.DegreeEntity;
import com.kms.cura.entity.FacilityEntity;
import com.kms.cura.entity.SpecialityEntity;
import com.kms.cura.entity.WorkingHourEntity;

public class DoctorUserEntity extends UserEntity {
    public final static String DOCTOR_LIST = "doctor_list";
    public static int DOCTOR_TYPE = 1;
    public final static String GENDER_MALE = "M";
    private String phone;
    private DegreeEntity degree;
    private List<SpecialityEntity> speciality;
    private double rating;
    private int experience;
    private double minPrice;
    private double maxPrice;
    private List<WorkingHourEntity> workingTime;
    private String gender;
    private Date birth;
    private String insurance;

    public DoctorUserEntity(String id, String name, String email, String password, String phone, DegreeEntity degree,
                            List<SpecialityEntity> speciality, double rating, int experience, double minPrice, double maxPrice,
                            List<WorkingHourEntity> workingTime, String gender, Date birth, String insurance) {
        super(id, name, email, password);
        this.phone = phone;
        this.degree = degree;
        this.speciality = speciality;
        this.rating = rating;
        this.experience = experience;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.workingTime = workingTime;
        this.gender = gender;
        this.birth = birth;
        this.insurance = insurance;
    }

    public DoctorUserEntity(String id, String name, String email, String password, String phone, DegreeEntity degree,
                            List<SpecialityEntity> speciality, List<WorkingHourEntity> workingTime, String gender, Date birth) {
        super(id, name, email, password);
        this.phone = phone;
        this.degree = degree;
        this.speciality = speciality;
        this.workingTime = workingTime;
        this.gender = gender;
        this.birth = birth;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DegreeEntity getDegree() {
        return degree;
    }

    public void setDegree(DegreeEntity degree) {
        this.degree = degree;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<SpecialityEntity> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(List<SpecialityEntity> speciality) {
        this.speciality = speciality;
    }

    public List<WorkingHourEntity> getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(List<WorkingHourEntity> workingTime) {
		this.workingTime = workingTime;
	}

	public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    @Override
    public int getType() {
        return DOCTOR_TYPE;
    }

    public static Type getDoctorEntityType() {
        return new TypeToken<DoctorUserEntity>() {
        }.getType();
    }

    public static Type getDoctorEntityListType() {
        return new TypeToken<Collection<DoctorUserEntity>>() {
        }.getType();
    }
}

