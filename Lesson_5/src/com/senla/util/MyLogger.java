package com.senla.util;

import org.apache.log4j.Logger;

public class MyLogger {
    final static Logger logger = Logger.getLogger(MyLogger.class);


    public void logInfo(String str) {
        if (logger.isInfoEnabled()) {
            logger.info("info: " + str);
        }
    }

    public void logDebug(String str) {
        if (logger.isDebugEnabled()) {
            logger.debug("debug: " + str);
        }
    }
}
