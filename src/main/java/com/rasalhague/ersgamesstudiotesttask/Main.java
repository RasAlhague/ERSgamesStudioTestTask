package com.rasalhague.ersgamesstudiotesttask;

import com.rasalhague.ersgamesstudiotesttask.configuration.ConfigurationHolder;
import com.rasalhague.ersgamesstudiotesttask.configuration.ConfigurationManager;
import com.rasalhague.ersgamesstudiotesttask.scanner.FileScanner;

public class Main
{
    private ConfigurationManager configurationManager = new ConfigurationManager();

    public static void main(String[] args)
    {
        new Main().start();
    }

    private void start()
    {
        ConfigurationHolder configurationHolder = configurationManager.getConfigurationHolder();
        new Log4jInitializer(configurationHolder.getResultFilePath());

        FileScanner fileScanner = new FileScanner(configurationHolder.getTargetDirectoryPath(),
                                                  configurationHolder.getMaxThreadsAmount(),
                                                  new FileFoundHandler());

        fileScanner.scan();
    }
}
