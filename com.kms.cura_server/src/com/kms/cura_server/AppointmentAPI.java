package com.kms.cura_server;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.json.JSONObject;

import com.kms.cura.dal.AppointmentDAL;
import com.kms.cura.dal.user.UserDAL;
import com.kms.cura_server.response.APIResponse;
import com.kms.cura_server.response.AppointmentAPIResponse;

@Path("/appt")
public final class AppointmentAPI {
	private static String CRITERIA = "criteria";
	@POST
	@Path("/getBookAppts")
	public String getBookAppts(String jsonData){
		JSONObject jsonObject = new JSONObject(jsonData);
		JSONObject object = jsonObject.getJSONObject(CRITERIA);
		Set<String> keySet = object.keySet();
		HashMap<String, Integer> criteria = new HashMap<>();
		for(String key : keySet){
			criteria.put(key, object.getInt(key));
		}
		try {
			return new AppointmentAPIResponse().successListApptsResponse(AppointmentDAL.getInstance().getAppointment(criteria,null,null));
		} catch (ClassNotFoundException | SQLException e) {
			return APIResponse.unsuccessResponse(e.getMessage());
		}
	}
}
