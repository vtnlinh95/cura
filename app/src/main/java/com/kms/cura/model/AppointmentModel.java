package com.kms.cura.model;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.model.request.AppointmentModelResponse;
import com.kms.cura.utils.RequestUtils;

import java.util.List;

/**
 * Created by linhtnvo on 7/15/2016.
 */
public class AppointmentModel {
    private static AppointmentModel instance;
    private static String tag_string_req = "string_req";
    private AppointmentModel() {

    }

    public static AppointmentModel getInstance() {
        if (instance == null) {
            instance = new AppointmentModel();
        }
        return instance;
    }

    public List<AppointmentEntity> bookAppointment(AppointmentEntity entity) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(Settings.SERVER_URL);
        builder.append(Settings.CREATE_APPT);
        AppointmentModelResponse response = new AppointmentModelResponse();
        StringRequest stringRequest = RequestUtils.createRequest(builder.toString(), Request.Method.POST,
                new Gson().toJson(entity,AppointmentEntity.getAppointmentType()),response);
        VolleyHelper.getInstance().addToRequestQueue(stringRequest, tag_string_req);
        while (!response.isGotResponse());
        if(!response.isResponseError()) {
            return response.getAppts();
        }
        throw new Exception(response.getError());
    }

}
