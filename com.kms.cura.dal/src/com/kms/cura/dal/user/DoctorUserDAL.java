package com.kms.cura.dal.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kms.cura.dal.AppointmentDAL;
import com.kms.cura.dal.DegreeDAL;
import com.kms.cura.dal.FacilityDAL;
import com.kms.cura.dal.database.DatabaseHelper;
import com.kms.cura.dal.database.DegreeDatabaseHelper;
import com.kms.cura.dal.database.DoctorUserDatabaseHelper;
import com.kms.cura.dal.database.FacilityDatabaseHelper;
import com.kms.cura.dal.database.PatientUserDatabaseHelper;
import com.kms.cura.dal.database.WorkingHourDatabaseHelper;
import com.kms.cura.dal.exception.DALException;
import com.kms.cura.dal.mapping.AppointmentColumn;
import com.kms.cura.dal.mapping.DoctorColumn;
import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.entity.DegreeEntity;
import com.kms.cura.entity.DoctorSearchEntity;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.FacilityEntity;
import com.kms.cura.entity.WorkingHourEntity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.entity.user.PatientUserEntity;
import com.kms.cura.entity.user.UserEntity;

public class DoctorUserDAL extends UserDAL {
	private static final String DOCTOR_TABLE_NAME = "Doctor";
	private static DoctorUserDAL _instance;

	private DoctorUserDAL() {
		// hide constructor
	}

	public static DoctorUserDAL getInstance() {
		if (_instance == null) {
			_instance = new DoctorUserDAL();
		}
		return _instance;
	}
	
	public DoctorUserEntity getAllReferenceAttributeforDoctor(DoctorUserEntity doctorUserEntity) throws ClassNotFoundException, SQLException {
		if (doctorUserEntity.getAppointmentList() != null) {
			return doctorUserEntity;
		}
		HashMap<String, Integer> map = new HashMap<>();
		map.put(AppointmentColumn.DOCTOR_ID.getColumnName(), Integer.parseInt(doctorUserEntity.getId()));
		List<AppointmentEntity> list = AppointmentDAL.getInstance().getAppointment(map,null,doctorUserEntity);
		doctorUserEntity.setAppointmentList(list);
		for (AppointmentEntity entity : list){
			PatientUserEntity patientUserEntity = entity.getPatientUserEntity();
			patientUserEntity = PatientUserDAL.getInstance().getAllReferenceAttributeforPatient(patientUserEntity);
			entity.setPatientUserEntity(patientUserEntity);
		}
		return doctorUserEntity;
	}
	
	public List<Entity> getAll(DatabaseHelper dbh) throws ClassNotFoundException, SQLException {
		return super.getAll(DOCTOR_TABLE_NAME, dbh);
	}
	
	

	public DoctorUserEntity getByID(int id) throws SQLException, ClassNotFoundException {
		DoctorUserDatabaseHelper databaseHelper = new DoctorUserDatabaseHelper();
		return getAllReferenceAttributeforDoctor((DoctorUserEntity) databaseHelper.querybyID(id));
	}

	@Override
	public DoctorUserEntity createUser(UserEntity entity) throws ClassNotFoundException, SQLException, DALException {
		DoctorUserEntity doctorUserEntity = (DoctorUserEntity) super.createUser(entity);
		return getAllReferenceAttributeforDoctor(doctorUserEntity);
	}

	@Override
	protected UserEntity insertUser(UserEntity entity) throws SQLException, DALException, ClassNotFoundException {
		if (!(entity instanceof DoctorUserEntity)) {
			return null;
		}
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		return getAllReferenceAttributeforDoctor((DoctorUserEntity) dbh.insertUser((DoctorUserEntity) entity));
	}

	public DoctorUserEntity searchDoctor(UserEntity entity) throws ClassNotFoundException, SQLException {
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		return getAllReferenceAttributeforDoctor(dbh.searchDoctor(entity));
	}

	public DoctorUserEntity updateDoctor(DoctorUserEntity doctorUserEntity) throws NumberFormatException, Exception {
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		return getAllReferenceAttributeforDoctor((DoctorUserEntity) dbh.updateDoctor(doctorUserEntity));
	}

	public List<DoctorUserEntity> searchDoctorFunction(DoctorSearchEntity search)
			throws SQLException, ClassNotFoundException {
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		return dbh.searchDoctorFunction(search);
	}
}
