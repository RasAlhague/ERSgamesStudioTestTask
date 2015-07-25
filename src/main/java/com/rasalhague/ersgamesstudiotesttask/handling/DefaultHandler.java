package com.rasalhague.ersgamesstudiotesttask.handling;

public class DefaultHandler extends FileHandler
{
    @Override
    public void handle(String filePath)
    {
        resultsLogger.info("DefaultHandler has reached");
    }
}
