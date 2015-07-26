package com.rasalhague.ersgamesstudiotesttask.scanner;

import com.rasalhague.ersgamesstudiotesttask.handling.Handler;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FileScanner
{
    private final static Logger logger = Logger.getLogger(FileScanner.class);
    private final String             startingDirectory;
    private final int                threadAmount;
    private final ThreadPoolExecutor executorService;
    private       Handler            fileFoundHandler;
    long startTime;
    long endTime;

    public FileScanner(String startingDirectory, int threadAmount, Handler fileFoundHandler)
    {
        if (startingDirectory.isEmpty() || !(new File(startingDirectory).isDirectory()))
        {
            logger.warn("Wrong targetDirectoryPath in config" );
            throw new RuntimeException("Wrong targetDirectoryPath in config" );
        }

        this.startingDirectory = startingDirectory;
        this.threadAmount = threadAmount;
        this.executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadAmount);
        this.fileFoundHandler = fileFoundHandler;
    }

    public void scan()
    {
        startTime = System.currentTimeMillis();

        File file = new File(startingDirectory);
        passDirectory(file);
    }

    private void passDirectory(File directory)
    {
        File[] directoryItems = directory.listFiles();
        if (directoryItems == null)
        {
            logger.warn("directoryItems == null" );
            return;
        }

        for (final File dirItem : directoryItems)
        {
            if (dirItem.isDirectory())
            {
                //                                passDirectory(dirItem);

                executorService.submit(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        passDirectory(dirItem);
                    }
                });
            }
            else if (dirItem.isFile())
            {
                String dirItemPath = dirItem.getPath();
                logger.info("File has found: " + dirItemPath);
                fileFoundHandler.handle(dirItemPath);
            }
        }

        endTime = System.currentTimeMillis();
        logger.info("\n\nThread section execution time: " + (endTime - startTime) + "\n\n" );
    }

    public void waitForExecutorServiceEnd()
    {
        while (executorService.getActiveCount() > 0) {
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
