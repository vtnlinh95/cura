package com.kms.cura.dal;

import java.sql.SQLException;
import java.util.List;

import com.kms.cura.dal.database.ConditionDatabaseHelper;
import com.kms.cura.entity.Entity;

public class ConditionDAL extends EntityDAL {
	private static ConditionDAL _instance;

	private ConditionDAL() {
		// hide constructor
	}

	public static ConditionDAL getInstance() {
		if (_instance == null) {
			_instance = new ConditionDAL();
		}
		return _instance;
	}

	public List<Entity> getAll() throws ClassNotFoundException, SQLException {
		return new ConditionDatabaseHelper().queryAll();
	}
}
