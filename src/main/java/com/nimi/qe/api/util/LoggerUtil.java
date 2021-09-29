package com.nimi.qe.api.util;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoggerUtil {

    private LoggerUtil(){

    }

    private static Logger logger = LogManager.getLogger(LoggerUtil.class);

    public static void logInfo(String logInfo){
        logger.info(logInfo);
    }

    public static void logError(String errorMessage, Throwable logError){
        logger.error(errorMessage, logError);
    }
}
