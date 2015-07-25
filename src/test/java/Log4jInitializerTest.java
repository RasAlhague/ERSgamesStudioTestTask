import com.rasalhague.ersgamesstudiotesttask.Log4jInitializer;
import com.rasalhague.ersgamesstudiotesttask.configuration.ConfigurationHolder;
import com.rasalhague.ersgamesstudiotesttask.configuration.ConfigurationManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Log4jInitializerTest Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>èþë 25, 2015</pre>
 */
public class Log4jInitializerTest
{
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
    public void testLog4jInitializer()
    {
        ConfigurationHolder configurationHolder = configurationManager.getConfigurationHolder();
        String              resultFilePath      = configurationHolder.getResultFilePath();

        new Log4jInitializer(resultFilePath);

        Logger resultsLogger = Logger.getLogger("resultsLogger");
        resultsLogger.info("hello from test");

        File configFile = new File(resultFilePath);
        Assert.assertTrue(configFile.exists());
    }
}
