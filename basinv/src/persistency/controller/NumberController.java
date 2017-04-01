/**
 * 
 */
package persistency.controller;

import java.util.Collection;
import java.util.Iterator;

import model.Business;
import model.BusinessTypeEnum;
import model.Number;

import persistency.ArgIO;
import persistency.DBFacade;

/**
 * @author Mathy
 * 
 */
public class NumberController {
	
	private static DBFacade facade;
	private static StringBuilder callableStatement;
	
	/**
	 * Create a specific Number
	 * @param Number
	 * @return
	 */
	public static boolean createNumber(Number detail) {		
		int LENGTH = 5;
		callableStatement = new StringBuilder();
		callableStatement
				.append("{call createNumber(?, ?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				NumberController.prepareNumber(detail, args, argsType, argsIO, i);
			} catch (Exception e) {
				System.err.print(detail);
				e.printStackTrace();
			}
		}
		return(facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	
	}
	/**
	 * Update a specific Number
	 * @param Number
	 * @return
	 */
	public static boolean updateNumber(Number detail) {		
		int LENGTH = 5;
		callableStatement = new StringBuilder();
		callableStatement
				.append("{call updateNumber(?, ?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				NumberController.prepareNumber(detail, args, argsType, argsIO, i);
			} catch (Exception e) {
				System.err.print(detail);
				e.printStackTrace();
			}
		}
		return(facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	
	}

	
	/**
	 * create and read the last created number for a certain type
	 * @param category
	 * @param year
	 * @return
	 */
	public static Number readLastNumber(String category, Integer year) {
		Number reference = null;
		facade = new DBFacade();
		callableStatement = new StringBuilder();

		int LENGTH = 2;
		callableStatement.append("{call readLastNumber(?, ?)}");
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {
				case 0: {
					args[i] = category;
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					args[i] = year;
					argsType[i] = java.sql.Types.INTEGER;
					argsIO[i] = ArgIO.IN;
					break;
				}				
				default:
					throw new Exception("Parameter" + i + " not vallid");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Collection<Business> list = facade.getResult(BusinessTypeEnum.NUMBER,
				callableStatement.toString(), args, argsType, argsIO);
		if (!list.isEmpty()) {
			Iterator<Business> it = list.iterator();
			while (it.hasNext()) {
				reference = (Number) it.next();
			}
		}
		return reference;
	}


	/**
	 * Prepare parameters for SP createNumber()
	 * @param args
	 * @param argsType
	 * @param argsIO
	 * @param i
	 * @throws Exception
	 */
	public static void prepareNumber(Number Number, Object[] args,
			int[] argsType, ArgIO[] argsIO, int i) throws Exception {
		switch (i) {
		case 0: {
			// IN category char(15),
			args[i] = Number.getNbrCategory();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 1: {
			// in eyar decimal(4.0) ,
			args[i] = Number.getNbrYear();
			argsType[i] = java.sql.Types.INTEGER;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 2: {
			// in strNbr char(15),
			args[i] = Number.getNbrStrValue();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 3: {
			// in lstNbr char(15),
			args[i] = Number.getNbrLstValue();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}

		case 4: {
			// out success boolean)
			args[i] = null;
			argsType[i] = java.sql.Types.BOOLEAN;
			argsIO[i] = ArgIO.OUT;
			break;
		}
		default:
			throw new Exception("Error while creating SP createNumber()");
		}
	}

	/**
	 * @return
	 */
	public static Collection<Business> getAllNumbers() {
		Collection<Business> list;

		callableStatement = new StringBuilder();
		facade = new DBFacade();
		
		callableStatement.append("{call readAllNumber()}");
		list = facade.getResult(BusinessTypeEnum.NUMBER,
				callableStatement.toString());
		return list;
	}

	public static Number readOneNumber(String category, int year) {
		Number reference = null;
		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 2;
		callableStatement.append("{call readOneNumber(?, ?)}");
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {
				case 0: {
					args[i] = category;
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					args[i] = year;
					argsType[i] = java.sql.Types.INTEGER;
					argsIO[i] = ArgIO.IN;
					break;
				}
				default:
					throw new Exception("Error while creating SP readOneNumber()");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Collection<Business> list = facade.getResult(BusinessTypeEnum.NUMBER,
				callableStatement.toString(), args, argsType, argsIO);
		if (!list.isEmpty()) {
			Iterator<Business> it = list.iterator();
			while (it.hasNext()) {
				reference = (Number) it.next();
			}
		}
		return reference;

	}
	public static boolean removeNumber(String category, int year) {
		boolean success = true;
		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 3;
		callableStatement.append("{call removeNumber(?, ?, ?)}");
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {
				case 0: {
					args[i] = category;
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					args[i] = year;
					argsType[i] = java.sql.Types.INTEGER;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 2: {
					// active success boolean)
					args[i] = false;
					argsType[i] = java.sql.Types.BOOLEAN;
					argsIO[i] = ArgIO.OUT;
					break;
				}
				default:
					throw new Exception("Error while creating SP removeNumber()");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		success = facade.removeBusinessObject(callableStatement.toString(),
				args, argsType, argsIO);

		return success;

	}

}
