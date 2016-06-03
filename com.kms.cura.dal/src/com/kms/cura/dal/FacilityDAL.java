package com.kms.cura.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kms.cura.dal.database.DatabaseHelper;
import com.kms.cura.entity.DayOfTheWeek;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.FacilityEntity;
import com.kms.cura.entity.OpeningHour;

public class FacilityDAL extends EntityDAL {
    private static final String FACILITIES_TABLE_NAME = "Facilities";
    private static FacilityDAL _instance;

    private FacilityDAL() {
	// hide constructor
    }

    public static FacilityDAL getInstance() {
	if (_instance == null) {
	    _instance = new FacilityDAL();
	}
	return _instance;
    }

    public List<Entity> getAll() throws ClassNotFoundException, SQLException {
	return super.getAll(FACILITIES_TABLE_NAME);
    }

    public Entity getByName(String name) throws SQLException, ClassNotFoundException {
	return super.getByName(FACILITIES_TABLE_NAME, name);
    }

    public Entity getByID(int id) throws SQLException, ClassNotFoundException {
	return super.getByID(FACILITIES_TABLE_NAME, id);
    }

    @Override
    protected Entity getEntityFromResultSet(ResultSet resultSet, DatabaseHelper dbh) throws SQLException {
	List<OpeningHour> openingHours = new ArrayList<OpeningHour>();
	ResultSet openingHoursRS = dbh.queryByReferenceID("opening_hours", "facility_id", resultSet.getInt("id"));
	while (openingHoursRS.next()) {
	    openingHours.add(new OpeningHour(DayOfTheWeek.getDayOfTheWeek(openingHoursRS.getInt("week_day")),
		    openingHoursRS.getTime("time_open"), openingHoursRS.getTime("time_close")));
	}
	return new FacilityEntity(resultSet.getString("id"), resultSet.getString("name"),
		resultSet.getString("address"), resultSet.getString("phone"), openingHours);
    }
}
