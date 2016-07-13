package com.kms.cura.dal.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kms.cura.dal.AppointmentDAL;
import com.kms.cura.dal.database.DoctorUserDatabaseHelper;
import com.kms.cura.dal.database.PatientUserDatabaseHelper;
import com.kms.cura.dal.exception.DALException;
import com.kms.cura.dal.mapping.DoctorColumn;
import com.kms.cura.entity.AppointSearchEntity;
import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.entity.DoctorSearchEntity;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.entity.user.PatientUserEntity;
import com.kms.cura.entity.user.UserEntity;

public class DoctorUserDAL extends UserDAL {
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

	private DoctorUserEntity getAllReferenceAttributeforDoctor(DoctorUserEntity doctorUserEntity)
			throws ClassNotFoundException, SQLException {
		if (doctorUserEntity == null) {
			return null;
		}
		AppointmentEntity entity = new AppointmentEntity(null, doctorUserEntity, null, null, null, null, -1);
		AppointSearchEntity criteria = new AppointSearchEntity(entity);
		List<AppointmentEntity> list = AppointmentDAL.getInstance().getAppointment(criteria, null, doctorUserEntity);
		doctorUserEntity.addAllAppointmentList(list);
		return doctorUserEntity;
	}

	public DoctorUserEntity getByID(String id) throws SQLException, ClassNotFoundException {
		DoctorUserDatabaseHelper databaseHelper = new DoctorUserDatabaseHelper();
		try {
			return getAllReferenceAttributeforDoctor((DoctorUserEntity) databaseHelper.queryDoctorByID(id));
		} finally {
			databaseHelper.closeConnection();
		}
	}

	public DoctorUserEntity createUser(UserEntity entity) throws ClassNotFoundException, SQLException, DALException {
		if (!(entity instanceof DoctorUserEntity)) {
			return null;
		}
		DoctorUserDatabaseHelper databaseHelper = new DoctorUserDatabaseHelper();
		try {
			return getAllReferenceAttributeforDoctor(databaseHelper.insertDoctorUser(entity));
		} finally {
			databaseHelper.closeConnection();
		}
	}

	public DoctorUserEntity searchDoctor(UserEntity entity) throws ClassNotFoundException, SQLException {
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		try {
			return getAllReferenceAttributeforDoctor(dbh.searchDoctor(entity));
		} finally {
			dbh.closeConnection();
		}
	}

	public DoctorUserEntity updateDoctor(DoctorUserEntity doctorUserEntity) throws NumberFormatException, Exception {
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		try {
			return getAllReferenceAttributeforDoctor((DoctorUserEntity) dbh.updateDoctor(doctorUserEntity));
		} finally {
			dbh.closeConnection();
		}
	}

	public List<DoctorUserEntity> searchDoctorFunction(DoctorSearchEntity search)
			throws SQLException, ClassNotFoundException {
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		try {
			return dbh.searchDoctorFunction(search);
		} finally {
			dbh.closeConnection();
		}
	}
	
	public List<Entity> getAll() throws ClassNotFoundException, SQLException {
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		try{
			List<DoctorUserEntity> list = dbh.getAllDoctor();
			List<Entity> doctors = new ArrayList<>();
			for (DoctorUserEntity entity : list) {
				doctors.add(getAllReferenceAttributeforDoctor((DoctorUserEntity) entity));
			}
			return doctors;
		}
		finally{
			dbh.closeConnection();
		}
	}

}
