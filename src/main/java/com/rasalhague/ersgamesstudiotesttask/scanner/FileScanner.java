package com.rasalhague.ersgamesstudiotesttask.scanner;

import com.rasalhague.ersgamesstudiotesttask.handling.Handler;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileScanner
{
    private final static Logger logger = Logger.getLogger(FileScanner.class);
    private final String          startingDirectory;
    private final int             threadAmount;
    private final ExecutorService executorService;
    private       Handler         fileFoundHandler;
    private int fileCounter = 0;

    public FileScanner(String startingDirectory, int threadAmount, Handler fileFoundHandler)
    {
        if (startingDirectory.isEmpty() || !(new File(startingDirectory).isDirectory()))
        {
            logger.warn("Wrong targetDirectoryPath in config");
            throw new RuntimeException("Wrong targetDirectoryPath in config");
        }

        this.startingDirectory = startingDirectory;
        this.threadAmount = threadAmount;
        this.executorService = Executors.newFixedThreadPool(threadAmount);
        this.fileFoundHandler = fileFoundHandler;
    }

    public int getFileCounter()
    {
        return fileCounter;
    }

    public void scan()
    {
        File file = new File(startingDirectory);
        passDirectory(file);
    }

    private void passDirectory(File directory)
    {
        File[] directoryItems = directory.listFiles();
        if (directoryItems == null)
        {
            logger.warn("directoryItems == null");
            return;
        }

        for (final File dirItem : directoryItems)
        {
            if (dirItem.isDirectory())
            {
                passDirectory(dirItem);
            }
            else if (dirItem.isFile())
            {
                String dirItemPath = dirItem.getPath();
                fileFoundHandler.handle(dirItemPath);
                logger.info("File has found: " + dirItemPath);

                fileCounter++;
//                logger.info(fileCounter);
            }
        }
    }
}
