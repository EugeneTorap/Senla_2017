package com.senla.util;

public class IdGenerator {
    private static int idCounter = 0;

    public static int generateId(){
       return idCounter += 10;
    }
}
