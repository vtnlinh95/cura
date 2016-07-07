package com.kms.cura.dal.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kms.cura.dal.DegreeDAL;
import com.kms.cura.dal.FacilityDAL;
import com.kms.cura.dal.database.DatabaseHelper;
import com.kms.cura.dal.database.DegreeDatabaseHelper;
import com.kms.cura.dal.database.DoctorUserDatabaseHelper;
import com.kms.cura.dal.database.FacilityDatabaseHelper;
import com.kms.cura.dal.database.WorkingHourDatabaseHelper;
import com.kms.cura.dal.exception.DALException;
import com.kms.cura.dal.mapping.AppointmentColumn;
import com.kms.cura.dal.mapping.DoctorColumn;
import com.kms.cura.entity.DegreeEntity;
import com.kms.cura.entity.DoctorSearchEntity;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.FacilityEntity;
import com.kms.cura.entity.WorkingHourEntity;
import com.kms.cura.entity.user.DoctorUserEntity;
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

	@Override
	protected DoctorUserEntity getByID(String tableName, int id, DatabaseHelper dbh)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		DoctorUserEntity doctor = (DoctorUserEntity) super.getByID(tableName, id, dbh);
		// set appointment
		return doctor;
	}

	public List<Entity> getAll(DatabaseHelper dbh) throws ClassNotFoundException, SQLException {
		return super.getAll(DOCTOR_TABLE_NAME, dbh);
	}

	@Override
	public DoctorUserEntity createUser(UserEntity entity) throws ClassNotFoundException, SQLException, DALException {
		return (DoctorUserEntity) super.createUser(entity);
	}

	@Override
	protected UserEntity insertUser(UserEntity entity) throws SQLException, DALException, ClassNotFoundException {
		if (!(entity instanceof DoctorUserEntity)) {
			return null;
		}
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		return dbh.insertUser((DoctorUserEntity) entity);
	}

	public DoctorUserEntity searchDoctor(UserEntity entity) throws ClassNotFoundException, SQLException {
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		return dbh.searchDoctor(entity);
	}

	public DoctorUserEntity updateDoctor(DoctorUserEntity doctorUserEntity) throws NumberFormatException, Exception {
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		return (DoctorUserEntity) dbh.updateDoctor(doctorUserEntity);
	}

	public List<DoctorUserEntity> searchDoctorFunction(DoctorSearchEntity search)
			throws SQLException, ClassNotFoundException {
		DoctorUserDatabaseHelper dbh = new DoctorUserDatabaseHelper();
		return dbh.searchDoctorFunction(search);
	}
}
