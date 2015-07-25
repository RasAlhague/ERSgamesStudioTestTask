package com.rasalhague.ersgamesstudiotesttask;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log4jInitializer
{
    private final static Logger logger            = Logger.getLogger(Log4jInitializer.class);
    private final        String resultsLoggerName = "resultsLogger";

    public Log4jInitializer(String filePath)
    {
        FileAppender fileAppender = new FileAppender();
        fileAppender.setName(resultsLoggerName);
        fileAppender.setFile(filePath);
        fileAppender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"));
        fileAppender.setThreshold(Level.ALL);
        fileAppender.activateOptions();

        Logger.getLogger(resultsLoggerName).addAppender(fileAppender);
    }
}
