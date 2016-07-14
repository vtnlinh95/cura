package com.kms.cura.dal.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kms.cura.dal.mapping.Condition_SymptomColumn;
import com.kms.cura.dal.mapping.SymptomColumn;
import com.kms.cura.entity.ConditionEntity;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.SymptomEntity;

public class SymptomDatabaseHelper extends DatabaseHelper {

	public SymptomDatabaseHelper() throws ClassNotFoundException, SQLException {
		super();
	}

	@Override
	protected SymptomEntity getEntityFromResultSet(ResultSet resultSet) throws SQLException, ClassNotFoundException {
		return new SymptomEntity(resultSet.getString(SymptomColumn.ID.getColumnName()),
				resultSet.getString(SymptomColumn.NAME.getColumnName()));
	}

	public List<SymptomEntity> queryAssociatedSymptom(ConditionEntity conditionEntity)
			throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StringBuilder builder = new StringBuilder();
		List<SymptomEntity> symptomEntities = new ArrayList<>();
		builder.append("Select * from ");
		builder.append(Condition_SymptomColumn.TABLE_NAME);
		builder.append(" c left join ");
		builder.append(SymptomColumn.TABLE_NAME);
		builder.append(" s on c.");
		builder.append(Condition_SymptomColumn.SYMPTOM_ID);
		builder.append(" = s.");
		builder.append(SymptomColumn.ID);
		builder.append(" where ");
		builder.append(Condition_SymptomColumn.CONDITION_ID);
		builder.append(" = ");
		builder.append(conditionEntity.getId());
		try {
			stmt = con.prepareStatement(builder.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				SymptomEntity entity = getEntityFromResultSet(rs);
				symptomEntities.add(entity);
			}
			return symptomEntities;
		} finally {
			stmt.close();
		}

	}
}
