package configuration;

import com.rasalhague.ersgamesstudiotesttask.configuration.ConfigurationHolder;
import com.rasalhague.ersgamesstudiotesttask.configuration.ConfigurationManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * ConfigurationManager Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>èþë 25, 2015</pre>
 */
public class ConfigurationManagerTest
{
    private static final String CONFIG_FILE_PATH = "cfg" + File.separator + "config.cfg";
    ConfigurationManager configurationManager;

    @Before
    public void before() throws Exception
    {
        configurationManager = new ConfigurationManager();
    }

    @After
    public void after() throws Exception
    {

    }

    @Test
    public void testConfigurationManager()
    {
        ConfigurationHolder configurationHolder = configurationManager.getConfigurationHolder();
        Assert.assertNotNull(configurationHolder.getMaxThreadsAmount());
        Assert.assertNotNull(configurationHolder.getResultFilePath());
        Assert.assertNotNull(configurationHolder.getTargetDirectoryPath());

        File configFile = new File(CONFIG_FILE_PATH);
        Assert.assertTrue(configFile.exists());
    }
} 
