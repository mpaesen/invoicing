package utilities;

/*
 * Created on Aug 20, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author bempn
 * <p>
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

//import org.apache.log4j.lf5.LogLevel;

import org.apache.log4j.Level;
import persistency.logging.BaseLogger;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class LoadProperties {
    private Properties p = null;

    public LoadProperties(File f) throws IOException {
        p = new Properties();
        p.load(new FileInputStream(f));
    }

    public static LoadProperties getPropertiesFile(String file) {
        LoadProperties sp = null;
        File f;
        try {
            f = new File(file);
            if (f.exists()) {
                sp = new LoadProperties(f);
            } else {
                System.err.println("File " + f + " does not exist!");
                BaseLogger.logMsg("File " + f + " does not exist!", Level.DEBUG);
            }

        } catch (Exception e) {
            BaseLogger.logMsg("File " + file + " does not exist!");
        }
        return sp;

    }

    public String getProperty(String key) {
        return p.getProperty(key);
    }

    public void setProperty(String key, String value) {
        p.setProperty(key, value);
    }

    public void list(PrintStream s) {
        p.list(s);
    }

    public void listWeb(PrintWriter s) {
        p.list(s);
    }

    public Enumeration elements() {
        return p.elements();
    }

    public Enumeration keys() {
        return p.keys();
    }

}