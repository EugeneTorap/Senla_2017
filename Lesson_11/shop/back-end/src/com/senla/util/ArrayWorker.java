package com.senla.util;

import com.senla.api.model.IEntity;

import java.util.List;

public class ArrayWorker {

    public static <T extends IEntity> T search(List<T> entities, int id){
        for (T entity : entities) {
            if (id == entity.getId()) {
                return entity;
            }
        }
        return null;
    }

    public static <T extends IEntity> int searchIndex(List<T> entities, int id){
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == id)
                return i;
        }
        return -1;
    }

    public static <T extends IEntity> boolean isExist(List<T> entities, int id){
        for (T entity : entities) {
            if (id == entity.getId()) {
                return true;
            }
        }
        return false;
    }
}
