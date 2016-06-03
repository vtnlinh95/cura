package com.kms.cura.dal.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.kms.cura.dal.exception.DALException;
import com.kms.cura.dal.exception.DuplicatedUserEmailException;
import com.kms.cura.dal.mapping.DoctorColumn;
import com.kms.cura.dal.mapping.PatientColumn;
import com.kms.cura.entity.FacilityEntity;
import com.kms.cura.entity.SpecialityEntity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.entity.user.PatientUserEntity;
import com.kms.cura.entity.user.UserEntity;

public class UserDatabaseHelper extends DatabaseHelper {
	public UserDatabaseHelper() throws ClassNotFoundException, SQLException {
		super();
	}

	public ResultSet insertUser(UserEntity entity) throws SQLException, DALException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		if (entity instanceof UserEntity) {
			stmt = con.prepareStatement("SELECT * FROM Users WHERE email = ? ");
			stmt.setString(1, entity.getEmail());
			rs = stmt.executeQuery();
			if (rs.next()) {
				throw new DuplicatedUserEmailException(entity.getEmail());
			}
			stmt = con.prepareStatement("INSERT INTO Users (email,password) VALUES (?,?)");
			stmt.setString(1, entity.getEmail());
			stmt.setString(2, entity.getPassword());
			stmt.executeUpdate();

			stmt = con.prepareStatement("SELECT * FROM Users WHERE email = ? ");
			stmt.setString(1, entity.getEmail());
			rs = stmt.executeQuery();
			return rs;
		}
		return null;
	}

	public ResultSet insertPatientUser(PatientUserEntity entity) throws SQLException, DALException {
		PreparedStatement stmt = null;
		ResultSet rs = insertUser(entity);
		if (!rs.next() || !(entity instanceof PatientUserEntity)) {
			return null;
		}
		try {
			con.setAutoCommit(false);
			if (!(entity instanceof PatientUserEntity)) {
			}
			int userId = rs.getInt("id");
			entity.setId(String.valueOf(userId));
			stmt = getInsertStatementForUser(entity);
			stmt.executeUpdate();

			stmt = con
					.prepareStatement("SELECT * FROM Patient WHERE " + PatientColumn.USER_ID.getColumnName() + " = ? ");
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			con.commit();
			return rs;
		} catch (SQLException e) {
			if (con != null) {
				System.err.print("Transaction is being rolled back");
				con.rollback();
			}
		} finally {
			con.setAutoCommit(true);
		}
		return rs;
	}

	public ResultSet insertDoctorUser(DoctorUserEntity entity) throws SQLException, DALException {
		PreparedStatement stmt = null;
		ResultSet rs = insertUser(entity);

		if (!rs.next() || !(entity instanceof DoctorUserEntity)) {
			return null;
		}
		try {
			con.setAutoCommit(false);
			if (!(entity instanceof DoctorUserEntity)) {
			}
			int userId = rs.getInt("id");
			entity.setId(String.valueOf(userId));
			stmt = getInsertStatementForUser(entity);
			stmt.executeUpdate();

			insertToDoctorReferenceTables(entity);

			stmt = con.prepareStatement("SELECT * FROM doctor WHERE " + DoctorColumn.USER_ID.getColumnName() + "= ? ");
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();
			con.commit();
			return rs;
		} catch (SQLException e) {
			if (con != null) {
				System.err.print("Transaction is being rolled back");
				con.rollback();
			}
		} finally {
			con.setAutoCommit(true);
		}
		return rs;
	}

	public static Map<String, Object> getColumnValueMapForEntity(PatientUserEntity entity) {
		if (entity == null) {
			// handle error;
			return null;
		}
		Map<String, Object> columnValueMap = new LinkedHashMap<String, Object>();
		for (PatientColumn patientColumn : PatientColumn.values()) {
			String columnName = patientColumn.getColumnName();
			switch (patientColumn) {
			case BIRTH:
				columnValueMap.put(columnName, entity.getBirth());
				break;
			case GENDER:
				columnValueMap.put(columnName, entity.getGender());
				break;
			case HEALTH_CONCERN:
				columnValueMap.put(columnName, entity.getHealthConcern());
				break;
			case INSURANCE:
				columnValueMap.put(columnName, entity.getInsurance());
				break;
			case LOCATION:
				columnValueMap.put(columnName, entity.getLocation());
				break;
			case NAME:
				columnValueMap.put(columnName, entity.getName());
				break;
			case USER_ID:
				columnValueMap.put(columnName, entity.getId());
				break;
			}

		}
		return columnValueMap;
	}

	private Map<String, Object> getColumnValueMapForEntity(DoctorUserEntity entity) {
		if (entity == null) {
			// handle error;
			return null;
		}
		Map<String, Object> columnValueMap = new LinkedHashMap<String, Object>();
		for (DoctorColumn doctortColumn : DoctorColumn.values()) {
			String columnName = doctortColumn.getColumnName();
			switch (doctortColumn) {
			case BIRTH:
				columnValueMap.put(columnName, entity.getBirth());
				break;
			case GENDER:
				columnValueMap.put(columnName, entity.getGender());
				break;
			case INSURANCE:
				columnValueMap.put(columnName, entity.getInsurance());
				break;
			case LOCATION:
				columnValueMap.put(columnName, entity.getLocation());
				break;
			case NAME:
				columnValueMap.put(columnName, entity.getName());
				break;
			case USER_ID:
				columnValueMap.put(columnName, entity.getId());
				break;
			case DEGREE_ID:
				columnValueMap.put(columnName, entity.getDegree().getId());
				break;
			case EXPERIENCE:
				columnValueMap.put(columnName, entity.getExperience());
				break;
			case MAX_PRICE:
				columnValueMap.put(columnName, entity.getMaxPrice());
				break;
			case MIN_PRICE:
				columnValueMap.put(columnName, entity.getMinPrice());
				break;
			case PHONE:
				columnValueMap.put(columnName, entity.getPhone());
				break;
			case RATING:
				columnValueMap.put(columnName, entity.getRating());
				break;
			}
		}
		return columnValueMap;
	}

	public PreparedStatement getInsertStatementForUser(PatientUserEntity entity) throws SQLException {
		Map<String, Object> patientValueMap = getColumnValueMapForEntity(entity);
		String databaseName = "Patient";
		String databaseMethod = "INSERT INTO";
		return createPreparedStatement(patientValueMap, databaseName, databaseMethod);
	}

	public PreparedStatement getInsertStatementForUser(DoctorUserEntity entity) throws SQLException {
		Map<String, Object> doctorValueMap = getColumnValueMapForEntity(entity);
		String databaseName = "Doctor";
		String databaseMethod = "INSERT INTO";
		return createPreparedStatement(doctorValueMap, databaseName, databaseMethod);
	}

	protected PreparedStatement createPreparedStatement(Map<String, Object> valueMap, String databaseName,
			String databaseMethod) throws SQLException {
		StringBuilder insertString = new StringBuilder(databaseMethod + " " + databaseName + " (");
		StringBuilder valueString = new StringBuilder(") VALUES (");
		boolean isFirst = true;
		for (Entry<String, Object> entry : valueMap.entrySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				insertString.append(",");
				valueString.append(",");
			}
			insertString.append(entry.getKey());
			valueString.append("?");
		}
		valueString.append(")");
		String statementString = insertString.toString() + valueString.toString();
		PreparedStatement stmt = con.prepareStatement(statementString);
		setPreparedStatementValue(valueMap, stmt);
		return stmt;
	}

	protected void setPreparedStatementValue(Map<String, Object> valueMap, PreparedStatement stmt) throws SQLException {
		int count = 1;
		for (Entry<String, Object> valueEntry : valueMap.entrySet()) {
			if (valueEntry.getValue() instanceof Integer) {
				stmt.setInt(count, (Integer) valueEntry.getValue());
			} else if (valueEntry.getValue() instanceof String) {
				stmt.setString(count, (String) valueEntry.getValue());
			} else if (valueEntry.getValue() instanceof Date) {
				stmt.setDate(count, (Date) valueEntry.getValue());
			} else if (valueEntry.getValue() instanceof Double) {
				stmt.setDouble(count, (Double) valueEntry.getValue());
			} else {
				// handle for null
				stmt.setString(count, null);
			}
			count++;
		}
	}

	protected void insertToDoctorReferenceTables(DoctorUserEntity entity) throws SQLException {
		List<ReferenceTableRow> referenceRows = new ArrayList<ReferenceTableRow>();
		for (FacilityEntity facility : entity.getFacility()) {
			ReferenceTableRow referenceRow = new ReferenceTableRow();
			referenceRow.setFirstRefKey("facility_id");
			referenceRow.setFirstRefValue(facility.getId());
			referenceRow.setSecondRefKey("doctor_id");
			referenceRow.setSecondRefValue(entity.getId());
			referenceRows.add(referenceRow);
		}
		insertReferenceRowsToReferenceTable("Doctor_Facilities", referenceRows);

		referenceRows.clear();
		for (SpecialityEntity speciality : entity.getSpeciality()) {
			ReferenceTableRow referenceRow = new ReferenceTableRow();
			referenceRow.setFirstRefKey("speciality_id");
			referenceRow.setFirstRefValue(speciality.getId());
			referenceRow.setSecondRefKey("doctor_id");
			referenceRow.setSecondRefValue(entity.getId());
			referenceRows.add(referenceRow);
		}
		insertReferenceRowsToReferenceTable("Doctor_Specialties", referenceRows);
	}
}
