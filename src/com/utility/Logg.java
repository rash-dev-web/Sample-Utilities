package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Logg{

private static Logger _logger;
//private static final String fileName = "defaultlog";
private static final String dateAndTimeFormat = "MM-dd-yyyy_hh.mm.ss";
private static final String logProperttFilePath = System.getProperty("user.dir")+"/log4j.properties";
//private static String testcasename;

public static void setRunTimeLogFile(String testcasename){
    /**
     * This is the static block which appends the log file name with the
     * timestamp to make it unique
     */
    try 
    {
    	
    	DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(dateAndTimeFormat);
    	LocalDateTime currentTime = LocalDateTime.now();
    	String dateTime = dtFormatter.format(currentTime);
    	String fileName= testcasename+"_"+dateTime;
    	
        /*String dateTime = dateAndTimeFormat
                .getFormattedCurrentDateAndTime(dateAndTimeFormat);*/
        String FileName = fileName+"_" +dateTime+".logs";
        File file = new File(System.getProperty("user.dir")+"/Log/"+FileName);

        if (file.createNewFile()) 
        {
            Properties props = new Properties();
            props.load(new FileInputStream(logProperttFilePath));
            props.setProperty("log4j.appender.file.File", "D:\\ns00341167\\TECHM\\Nitesh\\Schedulettracker\\eclipsegit\\Nit\\Opusframe\\Log\\"+FileName);
            LogManager.resetConfiguration();
            PropertyConfigurator.configure(props);
            System.out.println("Property log4j.appender.File.File = logs/"
                    + FileName);
        }
    } catch (IOException ex)
    	{
        ex.printStackTrace();
        System.out.print("IO Exception in static method of Logger Class. "
                + ex.getMessage());
        System.exit(-1);
    }

}

/**
 * This method creates instance of the Logger class coming from log4j jar by
 * implementing a singelton
 * 
 * @return _logger - new instance if no instance exist else an existing
 *         instance if the method is invoked previously
 */
public static Logger createLogger() {

    if (_logger == null) {
        _logger = LogManager.getLogger(Logg.class);
        return _logger;
    } else
        return _logger;
}
}

