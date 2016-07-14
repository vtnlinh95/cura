package com.kms.cura.model;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kms.cura.entity.ConditionEntity;
import com.kms.cura.entity.SpecialityEntity;
import com.kms.cura.entity.json.EntityToJsonConverter;
import com.kms.cura.model.request.SpecialityModelResponse;
import com.kms.cura.utils.RequestUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtnvo on 6/15/2016.
 */
public class SpecialityModel extends EntityModel {
    private static SpecialityModel instance;
    private static String tag_string_req = "string_req";
    private List<SpecialityEntity> specialities = new ArrayList<SpecialityEntity>();

    private SpecialityModel() {

    }

    /**
     * If run first time then need to run in separate thread
     *
     * @return SpecialityModel
     */
    public static SpecialityModel getInstace() {
        if (instance == null) {
            instance = new SpecialityModel();
        }
        return instance;
    }

    public void init() throws Exception {
        initData();
    }

    public List<SpecialityEntity> getSpecialities() {
        return specialities;
    }


    public void initData() throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(Settings.SERVER_URL);
        builder.append(Settings.GET_ALL_SPECIALITY);
        SpecialityModelResponse response = new SpecialityModelResponse(specialities);
        StringRequest stringRequest = RequestUtils.createRequestGET(builder.toString(), response);
        VolleyHelper.getInstance().addToRequestQueue(stringRequest, tag_string_req);
        while (!response.isGotResponse()) ;
        if (!response.isResponseError()) {
            specialities = response.getEntityList();
            return;
        }
        throw new Exception(response.getError());
    }

    public List<SpecialityEntity> getSpecialitiesByCondition(ConditionEntity entity) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(Settings.SERVER_URL);
        builder.append(Settings.GET_SPECIALITY_CONDITION);
        List<SpecialityEntity> specialityEntities = new ArrayList<>();
        SpecialityModelResponse response = new SpecialityModelResponse(specialityEntities);
        StringRequest stringRequest = RequestUtils.createRequest(builder.toString(), Request.Method.POST,
                EntityToJsonConverter.convertEntityToJson(entity).toString(), response);
        VolleyHelper.getInstance().addToRequestQueue(stringRequest, tag_string_req);
        while (!response.isGotResponse());
        if(!response.isResponseError()) {
            specialityEntities = response.getEntityList();
            return specialityEntities;
        }
        throw new Exception(response.getError());
    }
}
