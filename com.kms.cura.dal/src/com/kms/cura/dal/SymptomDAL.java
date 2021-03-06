package com.kms.cura.dal;

import java.sql.SQLException;
import java.util.List;

import com.kms.cura.dal.database.ConditionDatabaseHelper;
import com.kms.cura.dal.database.SymptomDatabaseHelper;
import com.kms.cura.dal.mapping.SymptomColumn;
import com.kms.cura.entity.ConditionEntity;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.SymptomEntity;

public class SymptomDAL extends EntityDAL {
	private static SymptomDAL _instance;

	private SymptomDAL() {
		// hide constructor
	}

	public static SymptomDAL getInstance() {
		if (_instance == null) {
			_instance = new SymptomDAL();
		}
		return _instance;
	}

	public List<Entity> getAll() throws ClassNotFoundException, SQLException {
		return super.getAll(SymptomColumn.TABLE_NAME, new SymptomDatabaseHelper());
	}
	
	
	public List<SymptomEntity> getAssociatedSymptom(ConditionEntity entity)
			throws SQLException, ClassNotFoundException {
		SymptomDatabaseHelper dbh = new SymptomDatabaseHelper();
		return dbh.queryAssociatedSymptom(entity);
	}
}
