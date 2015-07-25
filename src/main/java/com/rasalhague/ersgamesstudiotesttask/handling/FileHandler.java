package com.rasalhague.ersgamesstudiotesttask.handling;

import com.rasalhague.ersgamesstudiotesttask.Utils;
import org.apache.log4j.Logger;

public abstract class FileHandler implements Handler
{
    protected static final Logger resultsLogger = Logger.getLogger("resultsLogger");

    public static Handler getHandler(String fileExtension)
    {
        switch (fileExtension)
        {
            case "txt":
            {
                return new TXTHandler();
            }
            case "doc":
            {
                return new DOCHandler();
            }
            case "html":
            {
                return new HTMLHandler();
            }
            case "gif":
            {
                return new GIFHandler();
            }
            case "rtf":
            {
                return new RTFHandler();
            }
            default:
            {
                return new DefaultHandler();
            }
        }
    }

    @Override
    public void handle(String filePath)
    {
        resultsLogger.info("File has found: " + filePath);
    }

    protected void simulateFileProcessing()
    {
        try
        {
            resultsLogger.info("Simulating file processing");
            Thread.sleep(5000);
            resultsLogger.info("File processing simulation successful");
        }
        catch (InterruptedException e)
        {
            resultsLogger.warn(Utils.getStackTraceString(e));
            e.printStackTrace();
        }
    }
}
