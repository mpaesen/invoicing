package persistency;

import persistency.logging.BaseLogger;
import utilities.Constants;
import utilities.LoadProperties;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class RDBConnection extends DBConnection {
    private static LoadProperties props;

    public RDBConnection() {
        super();
        StringBuilder strManyDirectories = new StringBuilder();
        strManyDirectories.append(System.getProperty(Constants.DOCUMENT_ROOT)); // get
        strManyDirectories.append(Constants.USER_DOCUMENTS);
        strManyDirectories.append(Constants.RESOURCES_PATH);
        strManyDirectories.append(Constants.SETTINGS_PATH);
        strManyDirectories.append(Constants.SETTINGS_FILE);
        try {
            props = new LoadProperties(new File(strManyDirectories.toString()));
        } catch (final IOException e) {
            BaseLogger.getLogger().logMsg(e.getMessage());
        }
        setDocPath(props.getProperty(Constants.DOCUMENT_PATH));
        setUrl(props.getProperty(Constants.URL));

        final Properties info = new Properties();
        info.setProperty(Constants.USER, props.getProperty(Constants.USER));
        info.setProperty(Constants.PASSWORD, props.getProperty(Constants.PASSWORD));
        info.setProperty(Constants.DRIVER, props.getProperty(Constants.DRIVER));
        setInfo(info);
    }

    public static LoadProperties getProps() {
        return props;
    }
}