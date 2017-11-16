package com.senla.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Input {
    private static Scanner in = new Scanner(System.in);
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");


    public static Integer nextInt(String message) {
        System.out.println(message);
        Integer input;
        while (true){
            try {
                input = Integer.valueOf(in.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Invalid input, util/Input/nextInt");
                continue;
            }
            break;
        }
        return input;
    }

    public static String nextLine(String message) {
        System.out.println(message);
        return in.next();
    }

    public static Date nextDate(String message) {
        System.out.println(message);
        Date date;
        String input;
        while (true) {
            input = in.next();
            try {
                date = df.parse(input);
            } catch (ParseException e) {
                System.out.println("Incorrect, input dd/MM/yyyy format of the date");
                continue;
            }
            break;
        }
        return date;
    }
}
