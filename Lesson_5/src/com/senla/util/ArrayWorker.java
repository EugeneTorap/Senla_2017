package com.senla.util;

import com.senla.entity.Entity;

import java.util.List;

public class ArrayWorker {

    public static <T extends Entity> T search(List<T> entities, int id){
        for (T entity : entities) {
            if (id == entity.getId()) {
                return entity;
            }
        }
        return null;
    }

    public static <T extends Entity> int searchIndex(List<T> entities, int id){
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == id)
                return i;
        }
        return -1;
    }
}
