package com.kms.cura.dal.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.kms.cura.dal.mapping.AppointmentColumn;
import com.kms.cura.dal.mapping.DoctorColumn;
import com.kms.cura.dal.mapping.PatientColumn;
import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.entity.user.PatientUserEntity;

public class AppointmentDatabaseHelper extends DatabaseHelper{

	public AppointmentDatabaseHelper() throws ClassNotFoundException, SQLException {
		super();
	}

	@Override
	protected Entity getEntityFromResultSet(ResultSet resultSet) throws SQLException, ClassNotFoundException {
		return null;
	}
	
	protected AppointmentEntity geAppointmentEntityFromResultSet(ResultSet resultSet,PatientUserEntity patientUserEntity, DoctorUserEntity doctorUserEntity) throws SQLException, ClassNotFoundException {
		PatientUserDatabaseHelper patientUserDatabaseHelper = new PatientUserDatabaseHelper();
		DoctorUserDatabaseHelper doctorUserDatabaseHelper = new DoctorUserDatabaseHelper();
		if(patientUserEntity != null){
			return new AppointmentEntity(patientUserEntity, 
					(DoctorUserEntity)doctorUserDatabaseHelper.querybyID(resultSet.getInt(AppointmentColumn.DOCTOR_ID.getColumnName())), 
					resultSet.getDate(AppointmentColumn.APPT_DAY.getColumnName()), 
					resultSet.getTime(AppointmentColumn.START_TIME.getColumnName()), 
					resultSet.getTime(AppointmentColumn.END_TIME.getColumnName()), resultSet.getInt(AppointmentColumn.STATUS.getColumnName()));
		}
		else if(doctorUserEntity != null){
			return new AppointmentEntity((PatientUserEntity)patientUserDatabaseHelper.querybyID(resultSet.getInt(AppointmentColumn.PATIENT_ID.getColumnName())), 
					doctorUserEntity, 
					resultSet.getDate(AppointmentColumn.APPT_DAY.getColumnName()), 
					resultSet.getTime(AppointmentColumn.START_TIME.getColumnName()), 
					resultSet.getTime(AppointmentColumn.END_TIME.getColumnName()), resultSet.getInt(AppointmentColumn.STATUS.getColumnName()));
		}
		return new AppointmentEntity((PatientUserEntity)patientUserDatabaseHelper.queryByID(PatientColumn.TABLE_NAME, resultSet.getInt(AppointmentColumn.PATIENT_ID.getColumnName())), 
				(DoctorUserEntity)doctorUserDatabaseHelper.queryByID(DoctorColumn.TABLE_NAME, resultSet.getInt(AppointmentColumn.DOCTOR_ID.getColumnName())), 
				resultSet.getDate(AppointmentColumn.APPT_DAY.getColumnName()), 
				resultSet.getTime(AppointmentColumn.START_TIME.getColumnName()), 
				resultSet.getTime(AppointmentColumn.END_TIME.getColumnName()), resultSet.getInt(AppointmentColumn.STATUS.getColumnName()));
	}
	
	
	
}
