package com.kms.cura.model.request;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.entity.user.UserEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by linhtnvo on 7/14/2016.
 */
public class AppointmentModelResponse implements EntityModelResponse {
    List<AppointmentEntity> appointment;
    boolean gotResponse;
    boolean responseError = false;
    String error;

    public List<AppointmentEntity> getAppointment() {
        return appointment;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        responseError =  true;
        this.error = error.getMessage();
        gotResponse = true;
    }

    @Override
    public void onResponse(String response) {
        JSONObject jsonObject = null;
        Gson gson = new Gson();
        try {
            jsonObject = new JSONObject(response);
            boolean status = jsonObject.getBoolean(UserEntity.STATUS_KEY);
            if (status) {

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isGotResponse(){
        return gotResponse;
    }

    public boolean isResponseError() {
        return responseError;
    }

    public String getError() {
        return error;
    }
}
