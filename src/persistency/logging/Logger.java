package persistency.logging;

import org.jetbrains.annotations.NotNull;
import utilities.Constants;
import utilities.CreateDirectory;
import utilities.LoadProperties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


public class Logger {
    private static Logger logger;
    private LoadProperties properties;

    private Logger() {
        logger = this;
    }

    /**
     * this method initializes the logger, creates an object
     */
    public static void initialize() {
        logger = new Logger();
    }

    public static Logger getLogger() {
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
    public int getRegisteredLevel() {
        int i = 0;
        try {
            properties = new LoadProperties(new File(Constants.SETTINGS_PATH
                    + Constants.LOGGER_FILE));
            //InputStream inputstream = Logger.class.getResourceAsStream("Logger.properties");
            //properties.load(inputstream);
            //properties.close();
            i = Integer.parseInt(properties.getProperty(Constants.LOG_LEVEL));
            if (i < 0 || i > 3)
                i = 0;

        } catch (Exception e) {
            System.err.println("Logger: Failed in the getRegisteredLevel method");
            e.printStackTrace();
        }
        return i;
    }

    /**
     * One file will be made daily. So putting date, time in filename
     *
     * @param gc GregorianCalender
     * @return String name of file
     */
    private String getFileName(@NotNull GregorianCalendar gc) {
        StringBuilder logPath = new StringBuilder();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
        String dateString = dateFormat.format(gc.getTime());
        String fileName = "/ExceptionLog-" + dateString + ".txt";

        logPath.append(System.getProperty(Constants.LOGGING_DETAIL_PATH)); // get
        // Document
        // Root
        //logPath.append(DBConnection.getDocPath());
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
    public void logMsg(String message) {
        try {
            GregorianCalendar gc = new GregorianCalendar();
            String fileName = getFileName(gc);
            FileOutputStream fos = new FileOutputStream(fileName, true);
            PrintStream ps = new PrintStream(fos);
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM, d, yyyy 'at' hh:mm:ss a");
            ps.println("<" + dateFormat.format(gc.getTime()) + ">[" + message + "]");
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
