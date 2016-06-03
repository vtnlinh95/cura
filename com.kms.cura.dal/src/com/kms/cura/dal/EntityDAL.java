package com.kms.cura.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kms.cura.dal.database.DatabaseHelper;
import com.kms.cura.entity.Entity;

public abstract class EntityDAL {
	protected abstract Entity getEntityFromResultSet(ResultSet resultSet, DatabaseHelper dbh)
			throws SQLException, ClassNotFoundException;

	protected List<Entity> getAll(String tableName) throws ClassNotFoundException, SQLException {
		DatabaseHelper dbh = null;
		try {
			dbh = new DatabaseHelper();
			ResultSet rs = dbh.queryAll(tableName);
			List<Entity> result = new ArrayList<Entity>();
			while (rs.next()) {
				result.add(getEntityFromResultSet(rs, dbh));
			}
			return result;
		} finally {
			if (dbh != null) {
				dbh.closeConnection();
			}
		}
	}

	protected Entity getByName(String tableName, String name) throws SQLException, ClassNotFoundException {
		DatabaseHelper dbh = null;
		try {
			dbh = new DatabaseHelper();
			ResultSet rs = dbh.queryByName(tableName, name);
			Entity result = null;
			if (rs.next()) {
				result = getEntityFromResultSet(rs, dbh);
			}
			return result;
		} finally {
			if (dbh != null) {
				dbh.closeConnection();
			}
		}
	}

	protected Entity getByID(String tableName, int id) throws SQLException, ClassNotFoundException {
		DatabaseHelper dbh = null;
		try {
			dbh = new DatabaseHelper();
			ResultSet rs = dbh.queryByID(tableName, id);
			Entity result = null;
			if (rs.next()) {
				result = getEntityFromResultSet(rs, dbh);
			}
			return result;
		} finally {
			if (dbh != null) {
				dbh.closeConnection();
			}
		}
	}
	
	protected Entity insert(String tableName, Entity entity) throws ClassNotFoundException, SQLException {
		DatabaseHelper dbh = null;
		try {
			dbh = new DatabaseHelper();
			ResultSet rs = dbh.insert(tableName, entity);
			Entity result = null;
			if (rs != null && rs.next()) {
				result = getEntityFromResultSet(rs, dbh);
			}
			return result;
		} finally {
			if (dbh != null) {
				dbh.closeConnection();
			}
		}
	}
}
