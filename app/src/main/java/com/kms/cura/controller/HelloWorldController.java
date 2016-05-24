package com.kms.cura.controller;

import com.kms.cura.entity.HelloWorldEntity;
import com.kms.cura.model.HelloWorldModel;

public class HelloWorldController {
    private HelloWorldController() {
        // hide constructor
    }

    public static HelloWorldEntity getHelloWorldEntityByName(String name) {
        return HelloWorldModel.getInstace().get(name);
    }
}
