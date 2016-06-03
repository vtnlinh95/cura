package com.kms.cura.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kms.cura.dal.database.DatabaseHelper;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.SpecialityEntity;

public class SpecialityDAL extends EntityDAL {
	private static final String SPECIALITY_TABLE_NAME = "specialties";
	private static SpecialityDAL _instance;

	private SpecialityDAL() {
		// hide constructor
	}

	public static SpecialityDAL getInstance() {
		if (_instance == null) {
			_instance = new SpecialityDAL();
		}
		return _instance;
	}

	public List<Entity> getAll() throws ClassNotFoundException, SQLException {
		return super.getAll(SPECIALITY_TABLE_NAME);
	}

	public Entity getByName(String name) throws SQLException, ClassNotFoundException {
		return super.getByName(SPECIALITY_TABLE_NAME, name);
	}

	public Entity getByID(int id) throws SQLException, ClassNotFoundException {
		return super.getByID(SPECIALITY_TABLE_NAME, id);
	}

	@Override
	protected Entity getEntityFromResultSet(ResultSet resultSet, DatabaseHelper dbh) throws SQLException {
		return new SpecialityEntity(resultSet.getString("id"), resultSet.getString("name"));
	}
}
