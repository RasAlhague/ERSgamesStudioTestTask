import com.rasalhague.ersgamesstudiotesttask.FileFoundHandler;
import com.rasalhague.ersgamesstudiotesttask.configuration.ConfigurationHolder;
import com.rasalhague.ersgamesstudiotesttask.configuration.ConfigurationManager;
import com.rasalhague.ersgamesstudiotesttask.scanner.FileScanner;
import org.junit.Test;

/**
 * FileScanner Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>èþë 25, 2015</pre>
 */
public class FileScannerTest
{
    ConfigurationManager configurationManager = new ConfigurationManager();

    /**
     * Method: scan()
     */
    @Test
    public void testScan() throws Exception
    {
        ConfigurationHolder configurationHolder = configurationManager.getConfigurationHolder();

        FileScanner fileScanner = new FileScanner(configurationHolder.getTargetDirectoryPath(),
                                                  configurationHolder.getMaxThreadsAmount(), new FileFoundHandler());

        fileScanner.scan();

        //wait until threads do they work
        Thread.sleep(10000);
    }
} 
