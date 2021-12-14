package persistency.logging;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import utilities.Constants;
import utilities.CreateDirectory;
import utilities.LoadProperties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static org.apache.log4j.Level.*;


public class BaseLogger {
    private static BaseLogger logger;
    private static Logger jLogger;
    private static Level baseLogLevel;
    private static LoadProperties properties;

    private BaseLogger() {
        logger = this;
    }

    /**
     * this method initializes the logger, creates an object
     */
    public static void initialize() {
        logger = new BaseLogger();
    }

    public static BaseLogger getLogger() {
        if (logger == null) {
            initialize();
        }
        return logger;
    }

    /**
     * Level of logging, error or information etc
     *
     * @RETURN level, int
     */
    public static Level getRegisteredLevel() {
        String baseLogLevelLabel;
        StringBuilder strManyDirectories = new StringBuilder();
        strManyDirectories.append(System.getProperty(Constants.DOCUMENT_ROOT)); // get
        strManyDirectories.append(Constants.USER_DOCUMENTS);
        strManyDirectories.append(Constants.RESOURCES_PATH);
        strManyDirectories.append(Constants.SETTINGS_PATH);
        strManyDirectories.append(Constants.LOGGER_FILE);
        if (baseLogLevel == null) {
            try {
                properties = new LoadProperties(new File(strManyDirectories.toString()));
                baseLogLevelLabel = properties.getProperty(Constants.LOG_LEVEL);
                switch (baseLogLevelLabel) {
                    case "ERROR":
                        baseLogLevel = ERROR;
                        break;
                    case "WARN":
                        baseLogLevel = WARN;
                        break;
                    case "INFO":
                        baseLogLevel = INFO;
                        break;
                    case "DEBUG":
                        baseLogLevel = DEBUG;
                        break;
                    //                   case "SEVERE":
                    //                     baseLogLevel = SEVERE;
                    //                   break;
                    case "WARNING":
                        baseLogLevel = WARN;
                        break;
//                    case "CONFIG":
                    //                       baseLogLevel = CONFIG;
                    //                      break;
//                    case "FINE":
                    //                       baseLogLevel = FINE;
//                        break;
                    //                  case "FINER":
                    //                       baseLogLevel = FINER;
//                        break;
                    //                   case "FINEST":
                    //                       baseLogLevel = FINEST;
                    //                       break;
                    default:
                        baseLogLevel = FATAL;
                }

            } catch (Exception e) {
                System.err.println("Logger: Failed in the getRegisteredLevel method");
                e.printStackTrace();
            }
        }
        return baseLogLevel;
    }

    /**
     * One file will be made daily. So putting date, time in filename
     *
     * @param gc GregorianCalender
     * @return String name of file
     */
    private static String getFileName(GregorianCalendar gc) {
        StringBuilder logPath = new StringBuilder();
        logPath.append(System.getProperty(Constants.DOCUMENT_ROOT)); // get
        logPath.append(Constants.USER_DOCUMENTS);
        //logPath.append(Constants.LOGGING_DETAIL_PATH);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
        String dateString = dateFormat.format(gc.getTime());
        String fileName = "/ExceptionLog-" + dateString + ".txt";

        logPath.append(Constants.LOGGING_DETAIL_PATH);

        CreateDirectory.run(logPath.toString());
        logPath.append(fileName);

        return logPath.toString();
    }

    /**
     * a mechanism to log messsages to th file
     *
     * @param message String
     */
    public static void logMsg(String message) {
        logMsg(message, getRegisteredLevel());
    }

    /**
     * a mechanism to log messsages to th file
     *
     * @param message String, LogLevel
     */
    public static void logMsg(String message, final Level requestedLevel) {
        String requestedLevelLabel = requestedLevel.toString();
        if (getRegisteredLevel().equals(requestedLevel)) {
            try {
                GregorianCalendar gc = new GregorianCalendar();
                String fileName = getFileName(gc);
                FileOutputStream fos = new FileOutputStream(fileName, true);
                PrintStream ps = new PrintStream(fos);
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM, d, yyyy 'at' hh:mm:ss a");

                message += " ** " + requestedLevelLabel + " **";
                ps.println("<" + dateFormat.format(gc.getTime()) + ">[" + message + "]");

                ps.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
