package com.kms.cura.model;

import com.kms.cura.entity.Entity;
import com.kms.cura.entity.HelloWorldEntity;

/**
 * Abstract class for all model to deal with data loading
 * All methods of this class is protected, subclasses must create public methods for views to use
 */
public abstract class EntityModel {
    /**
     * Call to server api to get entity by name
     * @param name
     * @return entity, or null if not found
     */
    protected Entity getByName(String name) {
        return null;
    }

    /**
     * Call to server api to update entity
     * @param entity
     * @return true if update successfully, otherwise false
     */
    protected boolean update(Entity entity) {
        return false;
    }

    /**
     * Call to server api to save new entity
     * @param entity
     * @return true if save successfully, otherwise false
     */
    protected boolean save(Entity entity) {
        return false;
    }

    /**
     * Call to server api to delete entity
     * @param entity
     * @return true if delete successfully, otherwise false
     */
    protected boolean delete(Entity entity) {
        return false;
    }
}
