package com.kms.cura.dal.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import com.kms.cura.dal.exception.DALException;
import com.kms.cura.dal.mapping.UserColumn;
import com.kms.cura.entity.user.UserEntity;

public class UserDatabaseHelper extends DatabaseHelper {
	public UserDatabaseHelper() throws ClassNotFoundException, SQLException {
		super();
	}

	protected void insertUser(UserEntity entity) throws SQLException, DALException, ClassNotFoundException {
		PreparedStatement stmt = null, stmt2 = null;
		if (!(entity instanceof UserEntity)) {
			return;
		}
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ");
			sb.append(UserColumn.TABLE_NAME);
			sb.append(" (");
			sb.append(UserColumn.EMAIL.getColumnName());
			sb.append(",");
			sb.append(UserColumn.PASSWORD.getColumnName());
			sb.append(")");
			sb.append(" VALUES (?,?)");

			stmt2 = con.prepareStatement(sb.toString());
			stmt2.setString(1, entity.getEmail());
			stmt2.setString(2, entity.getPassword());
			stmt2.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (stmt2 != null) {
				stmt2.close();
			}
		}
	}

	@Override
	protected UserEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException, ClassNotFoundException {
		return new UserEntity(resultSet.getString(UserColumn.ID.getColumnName()), null,
				resultSet.getString(UserColumn.EMAIL.getColumnName()),
				resultSet.getString(UserColumn.PASSWORD.getColumnName()));
	}

	public UserEntity queryByEmail(String email) throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con
					.prepareStatement("SELECT * FROM " + UserColumn.TABLE_NAME + " WHERE " + UserColumn.EMAIL + " = ?");
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				return new UserEntity(rs.getString(UserColumn.ID.getColumnName()), null,
						rs.getString(UserColumn.EMAIL.getColumnName()),
						rs.getString(UserColumn.PASSWORD.getColumnName()));
			}
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public UserEntity queryUserEntitybyEmailPassword(String childTable, String email, String password,
			String childRefIdColumnName) throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(childTable);
		builder.append(" LEFT JOIN ");
		builder.append(UserColumn.TABLE_NAME);
		builder.append(" ON ");
		builder.append(childTable);
		builder.append(".");
		builder.append(childRefIdColumnName);
		builder.append(" = ");
		builder.append(UserColumn.TABLE_NAME);
		builder.append(".");
		builder.append(UserColumn.ID);
		builder.append(" WHERE ");
		builder.append(UserColumn.EMAIL.getColumnName());
		builder.append(" = ? AND ");
		builder.append(UserColumn.PASSWORD.getColumnName());
		builder.append(" = ?");
		try {
			stmt = con.prepareStatement(builder.toString());
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				return getEntityFromResultSet(rs);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return null;
	}

	public UserEntity queryUserEntitybyEmail(String childTable, String email, String childRefIdColumnName)
			throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(childTable);
		builder.append(" LEFT JOIN ");
		builder.append(UserColumn.TABLE_NAME);
		builder.append(" ON ");
		builder.append(childTable);
		builder.append(".");
		builder.append(childRefIdColumnName);
		builder.append(" = ");
		builder.append(UserColumn.TABLE_NAME);
		builder.append(".");
		builder.append(UserColumn.ID.getColumnName());
		builder.append(" WHERE ");
		builder.append(UserColumn.EMAIL.getColumnName());
		builder.append(" = ?");
		try {
			stmt = con.prepareStatement(builder.toString());
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				return getEntityFromResultSet(rs);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return null;
	}

	public UserEntity queryUserEntitybyId(String childTable, String id, String childRefIdColumnName)
			throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(childTable);
		builder.append(" LEFT JOIN ");
		builder.append(UserColumn.TABLE_NAME);
		builder.append(" ON ");
		builder.append(childTable);
		builder.append(".");
		builder.append(childRefIdColumnName);
		builder.append(" = ");
		builder.append(UserColumn.TABLE_NAME);
		builder.append(".");
		builder.append(UserColumn.ID.getColumnName());
		builder.append(" WHERE ");
		builder.append(UserColumn.ID.getColumnName());
		builder.append(" = ?");
		try {
			stmt = con.prepareStatement(builder.toString());
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				return getEntityFromResultSet(rs);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return null;
	}
}
