package com.kms.cura_server;

import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;


import com.google.gson.Gson;
import com.kms.cura.dal.AppointmentDAL;
import com.kms.cura.entity.AppointSearchEntity;
import com.kms.cura_server.response.APIResponse;
import com.kms.cura_server.response.AppointmentAPIResponse;

@Path("/appt")
public final class AppointmentAPI {
	
	@POST
	@Path("/getBookAppts")
	public String getBookAppts(String jsonData){
		AppointSearchEntity appointSearchEntity = new Gson().fromJson(jsonData, AppointSearchEntity.getAppointmentSearchType());
		try {
			return new AppointmentAPIResponse().successListApptsResponse(AppointmentDAL.getInstance().getAppointment(appointSearchEntity,null,null));
		} catch (ClassNotFoundException | SQLException e) {
			return APIResponse.unsuccessResponse(e.getMessage());
		}
	}
	
}
