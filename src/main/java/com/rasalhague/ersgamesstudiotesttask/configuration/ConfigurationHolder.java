package com.rasalhague.ersgamesstudiotesttask.configuration;

public class ConfigurationHolder
{
    private Integer maxThreadsAmount = 3;
    private String  targetDirectoryPath = "";
    private String  resultFilePath = "./logs/result.txt";

    public Integer getMaxThreadsAmount()
    {
        return maxThreadsAmount;
    }

    public String getTargetDirectoryPath()
    {
        return targetDirectoryPath;
    }

    public String getResultFilePath()
    {
        return resultFilePath;
    }
}
