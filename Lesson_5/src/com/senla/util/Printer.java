package com.senla.util;

import com.senla.entity.Entity;
import java.util.List;

public class Printer {

    public static void print(int number) {
        System.out.println(number);
    }

    public static void print(Entity entity) {
        System.out.println(entity.toString());
    }

    public static void printArray(List<? extends Entity> entities){
        System.out.println();
        for (Entity entity : entities) {
            print(entity);
        }
    }
}
