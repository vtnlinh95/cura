package com.kms.cura.model.request;

import com.android.volley.VolleyError;
import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.model.AppointmentModel;

import java.util.List;

/**
 * Created by linhtnvo on 7/15/2016.
 */
public class AppointmentModelResponse implements EntityModelResponse {
    boolean gotResponse;
    boolean responseError = false;
    String error;
    List<AppointmentEntity> appts;

    public List<AppointmentEntity> getAppts() {
        return appts;
    }

    public void setAppts(List<AppointmentEntity> appts) {
        this.appts = appts;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        responseError =  true;
        this.error = error.getMessage();
        gotResponse = true;
    }

    @Override
    public void onResponse(String response) {

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
