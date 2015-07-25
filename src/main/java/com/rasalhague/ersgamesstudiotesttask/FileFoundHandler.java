package com.rasalhague.ersgamesstudiotesttask;

import com.rasalhague.ersgamesstudiotesttask.handling.DefaultHandler;
import com.rasalhague.ersgamesstudiotesttask.handling.FileHandler;
import com.rasalhague.ersgamesstudiotesttask.handling.Handler;

public class FileFoundHandler implements Handler
{
    @Override
    public void handle(final String filePath)
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Handler fileHandler = FileHandler.getHandler(getFileExtension(filePath));
                if (fileHandler != null && fileHandler.getClass() != DefaultHandler.class)
                {
                    fileHandler.handle(filePath);
                }
            }
        });
        thread.setDaemon(false);
        thread.start();
    }

    private String getFileExtension(String filePath)
    {
        filePath = filePath.toLowerCase();

        if (filePath.lastIndexOf(".") != -1 && filePath.lastIndexOf(".") != 0)
        { return filePath.substring(filePath.lastIndexOf(".") + 1); }
        else { return ""; }
    }
}
