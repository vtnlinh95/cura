package com.kms.cura.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kms.cura.dal.database.DatabaseHelper;
import com.kms.cura.entity.DegreeEntity;
import com.kms.cura.entity.Entity;

public class DegreeDAL extends EntityDAL {
    private static final String DEGREE_TABLE_NAME = "degree";
    private static DegreeDAL _instance;

    private DegreeDAL() {
	// hide constructor
    }

    public static DegreeDAL getInstance() {
	if (_instance == null) {
	    _instance = new DegreeDAL();
	}
	return _instance;
    }

    public List<Entity> getAll() throws ClassNotFoundException, SQLException {
	return super.getAll(DEGREE_TABLE_NAME);
    }

    public Entity getByName(String name) throws SQLException, ClassNotFoundException {
	return super.getByName(DEGREE_TABLE_NAME, name);
    }

    public Entity getByID(int id) throws SQLException, ClassNotFoundException {
	return super.getByID(DEGREE_TABLE_NAME, id);
    }

    @Override
    protected Entity getEntityFromResultSet(ResultSet resultSet, DatabaseHelper dbh) throws SQLException {
	return new DegreeEntity(resultSet.getString("id"), resultSet.getString("name"));
    }
}
