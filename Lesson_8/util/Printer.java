package com.senla.util;

import com.senla.model.entity.Entity;
import java.util.List;

public class Printer {

    public static <T> void print(T value) {
        System.out.println(value);
    }

    public static void printArray(List<? extends Entity> entities){
        System.out.println();
        entities.forEach(Printer::print);
        System.out.println();
    }
}
