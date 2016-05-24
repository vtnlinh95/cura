package com.kms.cura.model;

import com.kms.cura.entity.Entity;
import com.kms.cura.entity.HelloWorldEntity;

import java.util.List;

public class HelloWorldModel extends EntityModel {
    private static HelloWorldModel instance;

    private HelloWorldModel() {
        // hide constructor
        initController();
    }

    private void initController() {
        // init controllers here
    }

    public static HelloWorldModel getInstace() {
        if (instance == null) {
            instance = new HelloWorldModel();
        }
        return instance;
    }

    /**
     * @return all HelloWorldEntity
     */
    public List<HelloWorldEntity> getAll() {
        return null;
    }

    public HelloWorldEntity get(String name) {
        Entity entity = super.getByName(name);
        if (entity instanceof HelloWorldEntity) {
            return (HelloWorldEntity) entity;
        }
        return new HelloWorldEntity(name);
    }

    public boolean update(HelloWorldEntity entity) {
        return super.update(entity);
    }

    public boolean save(HelloWorldEntity entity) {
        return super.save(entity);
    }

    public boolean delete(Entity entity) {
        return super.delete(entity);
    }
}
