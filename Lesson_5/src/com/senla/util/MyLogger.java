package com.senla.util;

import org.apache.log4j.Logger;

public class MyLogger {
    private final static Logger logger = Logger.getLogger(MyLogger.class);


    public static void debug(Exception e) {
        logger.debug(e);
    }
}
