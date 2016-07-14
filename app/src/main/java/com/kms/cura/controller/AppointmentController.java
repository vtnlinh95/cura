package com.kms.cura.controller;

import com.kms.cura.entity.AppointSearchEntity;
import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.model.AppointmentModel;

import java.util.List;

/**
 * Created by linhtnvo on 7/14/2016.
 */
public class AppointmentController {
    public static List<AppointmentEntity> getAppointmentList(AppointSearchEntity search) throws Exception {
        return AppointmentModel.getInstance().getAppointment(search);
    }
}
