package utilities.test;

import utilities.LoadProperties;

public class LoadPropertiesTest {
	private static void showFile(LoadProperties sp) {
		System.out.println("All known currencies:\n=====================");
		sp.list(System.out);
	}

	public static void main(String[] args) {
		try {
			if (args.length == 1) {
				LoadProperties sp = LoadProperties.getPropertiesFile(args[0]);
				showFile(sp);
			} else {
				System.out
						.println("Usage: java LoadPropertiesTest file.properties");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
