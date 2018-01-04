package com.senla.util;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Input {
    private static Scanner in = new Scanner(System.in);
    private final static Logger LOGGER = Logger.getLogger(Input.class);


    public static Integer nextInt(String message) {
        Printer.print(message);
        Integer input;
        while (true){
            try {
                input = Integer.valueOf(in.nextLine());
            }catch (NumberFormatException e){
                LOGGER.error(e.getMessage());
                continue;
            }
            break;
        }
        return input;
    }

    public static String nextLine(String message) {
        Printer.print(message);
        return in.next();
    }

    public static Date nextDate(String message) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Printer.print(message);
        Date date;
        String input;
        while (true) {
            input = in.next();
            try {
                date = df.parse(input);
            } catch (ParseException e) {
                Printer.print("Incorrect, input dd/MM/yyyy format of the date");
                LOGGER.error(e.getMessage());
                continue;
            }
            break;
        }
        return date;
    }
}
