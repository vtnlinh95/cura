package com.kms.cura.dal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.kms.cura.dal.database.AppointmentDatabaseHelper;
import com.kms.cura.dal.database.UserDatabaseHelper;
import com.kms.cura.dal.user.UserDAL;
import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.entity.user.PatientUserEntity;

public class AppointmentDAL {
	private static AppointmentDAL _instance;

	private AppointmentDAL() {
		// TODO Auto-generated constructor stub
	}

	public static AppointmentDAL getInstance() {
		if (_instance == null) {
			_instance = new AppointmentDAL();
		}
		return _instance;
	}
	
	public List<AppointmentEntity> getAppointment(HashMap<String, Integer> criteria, PatientUserEntity patientUserEntity, DoctorUserEntity doctorUserEntity) throws ClassNotFoundException, SQLException{
		AppointmentDatabaseHelper appointmentDatabaseHelper = new AppointmentDatabaseHelper();
		return appointmentDatabaseHelper.getAppointment(criteria, patientUserEntity, doctorUserEntity);
	}
}
