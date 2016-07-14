package com.kms.cura.model;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.kms.cura.entity.AppointSearchEntity;
import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.entity.json.EntityToJsonConverter;
import com.kms.cura.model.request.AppointmentModelResponse;
import com.kms.cura.utils.RequestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtnvo on 7/14/2016.
 */
public class AppointmentModel {
    private static AppointmentModel instance;
    private static String tag_string_req = "string_req";

    private AppointmentModel() {

    }

    /**
     * If run first time then need to run in separate thread
     * @return FacilityModel
     */
    public static AppointmentModel getInstance() {
        if (instance == null) {
            instance = new AppointmentModel();
        }
        return instance;
    }

    public List<AppointmentEntity> getAppointment(AppointSearchEntity search) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(Settings.SERVER_URL);
        builder.append(Settings.GET_APPT);
        List<AppointmentEntity> appointmentEntities = new ArrayList<>();
        AppointmentModelResponse response = new AppointmentModelResponse();
        String json = new Gson().toJsonTree(search,AppointSearchEntity.getAppointmentSearchType()).toString();
        StringRequest stringRequest = RequestUtils.createRequest(builder.toString(), Request.Method.POST,
                json, response);
        VolleyHelper.getInstance().addToRequestQueue(stringRequest, tag_string_req);
        while (!response.isGotResponse());
        if(!response.isResponseError()) {
            appointmentEntities = response.getAppointment();
            return appointmentEntities;
        }
        throw new Exception(response.getError());
    }
}
