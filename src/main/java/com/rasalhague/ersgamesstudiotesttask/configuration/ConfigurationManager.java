package com.rasalhague.ersgamesstudiotesttask.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rasalhague.ersgamesstudiotesttask.Utils;
import org.apache.log4j.Logger;

import java.io.*;

public class ConfigurationManager
{
    private static final String CONFIG_FILE_PATH          = "cfg" + File.separator + "config.cfg";
    private static final Logger logger                    = Logger.getLogger(ConfigurationManager.class);
    private static final Gson   gson                      = new GsonBuilder().setPrettyPrinting().create();
    private static ConfigurationHolder configurationHolder;

    public ConfigurationManager()
    {
        try
        {
            if (configurationHolder == null)
            {
                /**
                 * Try to get file from /cfg/config.cfg (CONFIG_FILE_PATH)
                 */
                logger.info("Trying to get file from /cfg/config.cfg (CONFIG_FILE_PATH)");
                File configFile = new File(CONFIG_FILE_PATH);

                try (FileReader reader = new FileReader(configFile))
                {
                    configurationHolder = gson.fromJson(reader, ConfigurationHolder.class);
                }
                catch (FileNotFoundException e)
                {
                    logger.info(Utils.getStackTraceString(e));

                    /**
                     * If /cfg/config.cfg does not exists - get default values
                     */
                    logger.info("/cfg/config.cfg FileNotFoundException. Loading with default values");
                    initDefaultConfigurationHolder();

                    //and create a file in filesystem
                    logger.info("Creating config with default params in filesystem");
                    createConfigFile();
                }
            }
        }
        catch (IOException e)
        {
            logger.warn(Utils.getStackTraceString(e));
            e.printStackTrace();
        }
    }

    public ConfigurationHolder getConfigurationHolder()
    {
        return configurationHolder;
    }

    private void initDefaultConfigurationHolder()
    {
        configurationHolder = new ConfigurationHolder();
    }

    private void createConfigFile() throws IOException
    {
        File file = new File(CONFIG_FILE_PATH);
        file.getParentFile().mkdirs();
        file.createNewFile();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file)))
        {
            bufferedWriter.write(gson.toJson(configurationHolder));
        }
    }
}
