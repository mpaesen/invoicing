package persistency.test;

import persistency.ArgIO;
import persistency.DBFacade;

public class ClearDummyDatabase {
	private static DBFacade facade;
	private static StringBuilder callableStatement;

	public static void main(String[] args) {
		boolean success = true;
		callableStatement = new StringBuilder();
		facade = new DBFacade();
		final int LENGTH = 0;
		callableStatement.append("{call emptyDatabase()}");
		args = new String[LENGTH];
		final int[] argsType = new int[LENGTH];
		final ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {
					case 0: {
						args[i] = "dummy";
						argsType[i] = java.sql.Types.CHAR;
						argsIO[i] = ArgIO.IN;
						break;
					}
					case 1: {
						// active success boolean)
						// args[i] = false;
						argsType[i] = java.sql.Types.BOOLEAN;
						argsIO[i] = ArgIO.OUT;
						break;
					}
					default:
						throw new Exception("Error while creating SP emptyDatabase()");
				}

			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		try {
			success = facade.removeBusinessObject(callableStatement.toString(), args, argsType, argsIO);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		if (!success)
			new Exception("Call emptyDatabase()").printStackTrace();

	}
}
