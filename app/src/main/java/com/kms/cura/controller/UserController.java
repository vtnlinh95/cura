package com.kms.cura.controller;

import com.kms.cura.entity.user.UserEntity;
import com.kms.cura.model.UserModel;

public class UserController {
    public static boolean saveNewUser(String email, String password) {
        return UserModel.getInstace().save(new UserEntity(null, null, email, password));
    }
}
