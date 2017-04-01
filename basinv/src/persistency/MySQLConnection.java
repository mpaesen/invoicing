package persistency;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import utilities.Constants;
import utilities.LoadProperties;

public class MySQLConnection extends DBConnection{
	private LoadProperties props;
	public MySQLConnection(){
		super();
		try {
			 props = new LoadProperties(new File(Constants.SETTINGS_PATH+Constants.SETTINGS_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setUrl(props.getProperty(Constants.URL));
		Properties info = new Properties();
		info.setProperty(Constants.USER, props.getProperty(Constants.USER));
		info.setProperty(Constants.PASSWORD, props.getProperty(Constants.PASSWORD));
		setInfo(info);
	}
}
