package com.kms.cura.dal.user;

import java.sql.SQLException;
import java.util.List;

import com.kms.cura.dal.EntityDAL;
import com.kms.cura.dal.database.DatabaseHelper;
import com.kms.cura.dal.mapping.UserColumn;
import com.kms.cura.entity.Entity;

public class UserDAL extends EntityDAL {
	private static UserDAL _instance;

	protected UserDAL() {
		// hide constructor
	}

	public static UserDAL getInstance() {
		if (_instance == null) {
			_instance = new UserDAL();
		}
		return _instance;
	}

	public List<Entity> getAll(DatabaseHelper dbh) throws ClassNotFoundException, SQLException {
		return super.getAll(UserColumn.TABLE_NAME, dbh);
	}
}
