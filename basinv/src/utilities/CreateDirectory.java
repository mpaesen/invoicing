package utilities;

import java.io.File;

public class CreateDirectory {
	public static void run(String strManyDirectories) {
		try {

			// Create multiple directories
			boolean success = (new File(strManyDirectories)).mkdirs();
			if (success) {
				System.out.println("Directories: " + strManyDirectories
						+ " created");
			}

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}