package com.kms.cura_server;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kms.cura.dal.exception.DALException;
import com.kms.cura.dal.user.DoctorUserDAL;
import com.kms.cura.dal.user.PatientUserDAL;
import com.kms.cura.dal.user.UserDAL;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.json.EntityToJsonConverter;
import com.kms.cura.entity.json.JsonToEntityConverter;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.entity.user.PatientUserEntity;
import com.kms.cura_server.resources.Strings;

@Path("/user")
public final class UserAPI {
	@GET
	@Path("/getAllUser")
	public String getAllUser() {
		try {
			List<Entity> users = UserDAL.getInstance().getAll();
			JsonElement element = EntityToJsonConverter.convertEntityListToJson(users);
			return element.toString();
		} catch (ClassNotFoundException | SQLException e) {
			return Strings.error_internal + e.toString();
		}
	}

	@GET
	@Path("/getAllAdmin")
	public String getAllUserAdmin() {
		try {
			List<Entity> users = UserDAL.getInstance().getAll();
			JsonElement element = EntityToJsonConverter.convertEntityListToJson(users);
			return element.toString();
		} catch (ClassNotFoundException | SQLException e) {
			return Strings.error_internal + e.toString();
		}
	}

	@GET
	@Path("/getAllPatient")
	public String getAllPatient() {
		try {
			List<Entity> users = PatientUserDAL.getInstance().getAll();
			JsonElement element = EntityToJsonConverter.convertEntityListToJson(users);
			return element.toString();
		} catch (ClassNotFoundException | SQLException e) {
			return Strings.error_internal + e.toString();
		}
	}

	@GET
	@Path("/getAllDoctor")
	public String getAllDoctor() {
		try {
			List<Entity> users = DoctorUserDAL.getInstance().getAll();
			JsonElement element = EntityToJsonConverter.convertEntityListToJson(users);
			return element.toString();
		} catch (ClassNotFoundException | SQLException e) {
			return Strings.error_internal + e.toString();
		}
	}

	@POST
	@Path("/createPatient")
	public String createPatient(String jsonData) throws ClassNotFoundException, SQLException {
		try {
			PatientUserEntity entity = JsonToEntityConverter.convertJsonStringToEntity(jsonData, getPatientUserType());
			PatientUserEntity user = PatientUserDAL.getInstance().createUser(entity);
			return EntityToJsonConverter.convertEntityToJson(user).toString();
		} catch (ClassNotFoundException | SQLException | DALException e) {
			return Strings.error_internal + e.toString();
		}
	}

	@POST
	@Path("/createDoctor")
	public String createDoctor(String jsonData) throws ClassNotFoundException, SQLException {
		try {
			DoctorUserEntity entity = JsonToEntityConverter.convertJsonStringToEntity(jsonData, getDoctorEntityType());
			DoctorUserEntity user = DoctorUserDAL.getInstance().createUser(entity);
			return EntityToJsonConverter.convertEntityToJson(user).toString();
		} catch (ClassNotFoundException | SQLException | DALException e) {
			return Strings.error_internal + e.toString();
		}
	}

	private static Type getDoctorEntityType() {
		return new TypeToken<DoctorUserEntity>() {
		}.getType();
	}

	private Type getPatientUserType() {
		Type type = new TypeToken<PatientUserEntity>() {
		}.getType();
		return type;
	}
}
