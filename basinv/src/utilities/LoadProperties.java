package utilities;

/*
 * Created on Aug 20, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


/**
 * @author bempn
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.io.File;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

public class LoadProperties {
	private Properties p = null;

	public LoadProperties(File f) throws IOException {
		p = new Properties();
		p.load(new FileInputStream(f));
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

	public static LoadProperties getPropertiesFile(String file) {
		LoadProperties sp = null;
		try {
			File f = new File(file);
			if (f.exists()) {
				sp = new LoadProperties(f);
			} else {
				System.out.println("File " + f + " does not exist!");
			}

		} catch (Exception e) {
			e.printStackTrace();

		} 
			return sp;
		
	}

}