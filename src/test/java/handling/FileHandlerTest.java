package handling;

import com.rasalhague.ersgamesstudiotesttask.handling.FileHandler;
import com.rasalhague.ersgamesstudiotesttask.handling.Handler;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class FileHandlerTest
{
    String[] extensions = new String[]{"txt", "html", "doc", "rtf", "gif"};

    public static int randInt(int min, int max)
    {
        Random rand      = new Random();
        int    randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    @Before
    public void setUp()
    {

    }

    @Test
    public void testGetHandler()
    {
        for (int i = 0; i < 100; i++)
        {
            String extension = extensions[randInt(0, 4)];

            final Handler txtHandler = FileHandler.getHandler(extension);
            Thread testPath = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    txtHandler.handle("testPath");
                }
            });
            testPath.start();
        }
    }
}
