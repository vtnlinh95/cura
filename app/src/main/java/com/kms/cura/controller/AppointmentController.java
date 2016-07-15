package com.kms.cura.controller;

import com.kms.cura.entity.AppointmentEntity;
import com.kms.cura.model.AppointmentModel;

import java.util.List;

/**
 * Created by linhtnvo on 7/15/2016.
 */
public class AppointmentController {
    public static List<AppointmentEntity> bookAppointment(AppointmentEntity appointmentEntity){
        return AppointmentModel.getInstance().bookAppointment(appointmentEntity);
    }
}
