package utilities.test;

//import org.apache.log4j.lf5.LogLevel;

import org.apache.log4j.Level;
import persistency.logging.BaseLogger;

/*
#public static final LogLevel FATAL = new LogLevel("FATAL", 0);
#public static final LogLevel ERROR = new LogLevel("ERROR", 1);
#public static final LogLevel WARN = new LogLevel("WARN", 2);
#public static final LogLevel INFO = new LogLevel("INFO", 3);
#public static final LogLevel DEBUG = new LogLevel("DEBUG", 4);
#public static final LogLevel SEVERE = new LogLevel("SEVERE", 1);
#public static final LogLevel WARNING = new LogLevel("WARNING", 2);
#public static final LogLevel CONFIG = new LogLevel("CONFIG", 4);
#public static final LogLevel FINE = new LogLevel("FINE", 5);
#public static final LogLevel FINER = new LogLevel("FINER", 6);
#public static final LogLevel FINEST = new LogLevel("FINEST", 7);
 */

public class BaseLoggerTest {
    public static void main(String[] args) {
        BaseLogger.getLogger().logMsg("Test ", Level.FATAL);
        BaseLogger.getLogger().logMsg("Test ", Level.ERROR);
        BaseLogger.getLogger().logMsg("Test ", Level.WARN);
        BaseLogger.getLogger().logMsg("Test ", Level.INFO);
        BaseLogger.getLogger().logMsg("Test ", Level.DEBUG);

        //    BaseLogger.getLogger().logMsg("Test ", Level.SEVERE);//  BaseLogger.getLogger().logMsg("Test ", Level.WARNING);
        //    BaseLogger.getLogger().logMsg("Test ", Level.CONFIG);
        //    BaseLogger.getLogger().logMsg("Test ", Level.FINE);
        //    BaseLogger.getLogger().logMsg("Test ", Level.FINER);
        //    BaseLogger.getLogger().logMsg("Test ", Level.FINEST);
    }
}

