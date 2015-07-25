package com.rasalhague.ersgamesstudiotesttask.handling;

public class DOCHandler extends FileHandler
{
    @Override
    public void handle(String filePath)
    {
        super.handle(filePath);

        simulateFileProcessing();
    }
}
