package persistency;

import persistency.logging.Logger;
import utilities.Constants;
import utilities.LoadProperties;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class RDBConnection extends DBConnection {
    private static LoadProperties props;

    public RDBConnection() {
        super();
        try {
            props = new LoadProperties(new File(Constants.SETTINGS_PATH + Constants.SETTINGS_FILE));
        } catch (final IOException e) {
            Logger.getLogger().logMsg(e.getMessage());
            //e.printStackTrace();
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