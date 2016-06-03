package com.kms.cura.model;

import com.kms.cura.entity.Entity;
import com.kms.cura.entity.user.UserEntity;

import java.util.List;

public class UserModel extends EntityModel {
    private static UserModel instance;

    private UserModel() {
    }

    public static UserModel getInstace() {
        if (instance == null) {
            instance = new UserModel();
        }
        return instance;
    }

    /**
     * Call to server api to save new entity
     * @param user
     * @return true if save successfully, otherwise false
     */
    public boolean save(UserEntity user) {
        // call to api to save new user
        return false;
    }
}
