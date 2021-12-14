package utilities;

//import org.apache.log4j.lf5.LogLevel;

import org.apache.log4j.Level;
import persistency.logging.BaseLogger;

import java.io.File;

public class CreateDirectory {
    public static void run(String strManyDirectories) {
        try {

            // Create multiple directories
            boolean success = (new File(strManyDirectories)).mkdirs();
            if (success) {
                System.out.println("Directories: " + strManyDirectories
                        + " created");
                BaseLogger.logMsg("Directories: " + strManyDirectories
                                + " created",
                        Level.DEBUG);
            }

        } catch (Exception e) {// Catch exception if any
            BaseLogger.logMsg(e.getMessage());
        }
    }
}