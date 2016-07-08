package com.kms.cura.entity;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.entity.user.PatientUserEntity;

public class AppointmentEntity {
	public static int PENDING_STT = 0;
	public static int ACCEPTED_STT = 1;
	public static int REJECT_STT = 2;
	public static int CANCEL_STT = 3;
	
	private PatientUserEntity patientUserEntity;
	private DoctorUserEntity doctorUserEntity;
	private Date apptDay;
	private Time startTime;
	private Time endTime;
	private int status;
	
	public AppointmentEntity(PatientUserEntity patientUserEntity, DoctorUserEntity doctorUserEntity, Date apptDay,
			Time startTime, Time endTime, int status) {
		this.patientUserEntity = patientUserEntity;
		this.doctorUserEntity = doctorUserEntity;
		this.apptDay = apptDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	public PatientUserEntity getPatientUserEntity() {
		return patientUserEntity;
	}

	public void setPatientUserEntity(PatientUserEntity patientUserEntity) {
		this.patientUserEntity = patientUserEntity;
	}

	public DoctorUserEntity getDoctorUserEntity() {
		return doctorUserEntity;
	}

	public void setDoctorUserEntity(DoctorUserEntity doctorUserEntity) {
		this.doctorUserEntity = doctorUserEntity;
	}

	public Date getApptDay() {
		return apptDay;
	}

	public void setApptDay(Date apptDay) {
		this.apptDay = apptDay;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public static Type getAppointmentType() {
        return new TypeToken<AppointmentEntity>() {
        }.getType();
    }

	public static Type getAppointmentListType() {
		 return new TypeToken<List<AppointmentEntity>>() {
	        }.getType();
	}
	
}
