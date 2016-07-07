package com.kms.cura.dal.user;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kms.cura.dal.AppointmentDAL;
import com.kms.cura.dal.database.DatabaseHelper;
import com.kms.cura.dal.database.DoctorUserDatabaseHelper;
import com.kms.cura.dal.database.PatientUserDatabaseHelper;
import com.kms.cura.dal.exception.DALException;
import com.kms.cura.dal.mapping.AppointmentColumn;
import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.entity.user.PatientUserEntity;
import com.kms.cura.entity.user.UserEntity;

public class PatientUserDAL extends UserDAL {
	private static final String PATIENT_TABLE_NAME = "Patient";
	private static PatientUserDAL _instance;

	private PatientUserDAL() {
		// hide constructor
	}

	public static PatientUserDAL getInstance() {
		if (_instance == null) {
			_instance = new PatientUserDAL();
		}
		return _instance;
	}

	public List<Entity> getAll(DatabaseHelper dbh) throws ClassNotFoundException, SQLException {
		List<Entity> list = super.getAll(PATIENT_TABLE_NAME, dbh);
		List<Entity> patientUserEntities = new ArrayList<>();
		for (Entity entity : list) {
			patientUserEntities.add(getAllReferenceAttributeforPatient((PatientUserEntity) entity));
		}
		return patientUserEntities;
	}

	public PatientUserEntity getAllReferenceAttributeforPatient(PatientUserEntity patientUserEntity)
			throws ClassNotFoundException, SQLException {
		if (patientUserEntity.getAppointmentList() != null) {
			return patientUserEntity;
		}
		HashMap<String, Integer> map = new HashMap<>();
		map.put(AppointmentColumn.PATIENT_ID.getColumnName(), Integer.parseInt(patientUserEntity.getId()));
		List<AppointmentEntity> list = AppointmentDAL.getInstance().getAppointment(map, patientUserEntity, null);
		patientUserEntity.setAppointmentList(list);
		for (AppointmentEntity entity : list) {
			DoctorUserEntity doctor = entity.getDoctorUserEntity();
			doctor = DoctorUserDAL.getInstance().getAllReferenceAttributeforDoctor(doctor);
			entity.setDoctorUserEntity(doctor);
		}
		return patientUserEntity;
	}

	@Override
	public PatientUserEntity createUser(UserEntity entity) throws ClassNotFoundException, SQLException, DALException {
		PatientUserEntity patientUserEntity = (PatientUserEntity) super.createUser(entity);
		return getAllReferenceAttributeforPatient(patientUserEntity);
	}

	@Override
	protected UserEntity insertUser(UserEntity entity) throws SQLException, DALException, ClassNotFoundException {
		if (!(entity instanceof PatientUserEntity)) {
			// do whatever with error
		}
		PatientUserDatabaseHelper dbh = new PatientUserDatabaseHelper();
		PatientUserEntity patient = (PatientUserEntity) dbh.insertUser((PatientUserEntity) entity);
		return getAllReferenceAttributeforPatient(patient);
	}

	public PatientUserEntity searchPatient(UserEntity entity) throws ClassNotFoundException, SQLException {
		PatientUserDatabaseHelper dbh = new PatientUserDatabaseHelper();
		PatientUserEntity patient = dbh.searchPatient(entity);
		return getAllReferenceAttributeforPatient(patient);
	}

	public PatientUserEntity getByID(int id) throws SQLException, ClassNotFoundException {
		PatientUserDatabaseHelper databaseHelper = new PatientUserDatabaseHelper();
		return getAllReferenceAttributeforPatient((PatientUserEntity) databaseHelper.querybyID(id));
	}

}
