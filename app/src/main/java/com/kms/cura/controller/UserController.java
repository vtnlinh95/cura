package com.kms.cura.controller;

import android.content.Context;

import com.kms.cura.entity.user.UserEntity;
import com.kms.cura.model.UserModel;

public class UserController {
    public static void registerPatient(String name, String email, String password) {
        UserEntity entity = new UserEntity(null, name, email, password);
        UserModel.getInstace().registerPatient(entity);
    }

    public static void userLogin(String email, String password) {
        UserEntity entity = new UserEntity(null, null, email, password);
        UserModel.getInstace().userLogin(entity);
    }

    public static boolean saveNewUser(String email, String password) {
        return UserModel.getInstace().save(new UserEntity(null, null, email, password));
    }
}
