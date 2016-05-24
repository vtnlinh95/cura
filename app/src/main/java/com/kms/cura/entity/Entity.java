package com.kms.cura.entity;

/**
 * Abstract class for all pojo object to handle entities
 */
public abstract class Entity {
    protected String name;

    public Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
