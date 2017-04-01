/**
 * 
 */
package persistency.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

import model.Business;
import model.BusinessTypeEnum;
import model.CodeDetail;
import persistency.ArgIO;
import persistency.DBFacade;


/**
 * @author Mathy
 *
 */
public class CodeController {
	private static DBFacade facade;
	private static StringBuilder callableStatement;
	


	/**
	 * readOneCodeDetail
	 * @param ref
	 * @param det
	 * @return
	 */
	public static CodeDetail getOneCodeDetail(String ref, String det){
		CodeDetail detail = null;
		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 2;
		callableStatement.append("{call readOneCodeDetail(?, ?)}");
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			switch (i) {

			case 0: {
				// in ref char(30),
				args[i] = ref;
				argsType[i] = java.sql.Types.CHAR;
				argsIO[i] = ArgIO.IN;
				break;
			}
			case 1: {
				// in det char(5),
				args[i] = det;
				argsType[i] = java.sql.Types.CHAR;
				argsIO[i] = ArgIO.IN;
				break;
			}
			default:
				try {
						throw new Exception(
								"Error while creating SP readOneCodeDetail()");
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
		Collection<Business> list = facade.getResult(BusinessTypeEnum.CODE_DETAIL,
				callableStatement.toString(), args, argsType, argsIO);
		if (!list.isEmpty()) {
			Iterator<Business> it = list.iterator();
			while (it.hasNext()) {
				detail = (CodeDetail) it.next();
			}
		}
		return detail;
	}
	/**
	 * readAllDetails for one code header
	 * @param id
	 * @return
	 */
	public static Collection<Business> getAllCodeDetails(String id) {
		Collection<Business> list;

		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 1;
		callableStatement.append("{call readAllCodeDetail(?)}");
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				args[i] = id;
				argsType[i] = java.sql.Types.CHAR;
				argsIO[i] = ArgIO.IN;
			} catch (Exception e) {
				e.printStackTrace();
			}				
		}
		list = facade.getResult(BusinessTypeEnum.CODE_DETAIL,
				callableStatement.toString(), args, argsType, argsIO);
		return list;
	}

	/**
	 * @return
	 */
	public static Collection<Business> getAllCodeHeaders() {
		Collection<Business> list;

		callableStatement = new StringBuilder();
		facade = new DBFacade();
		
		callableStatement.append("{call readAllCodeHeader()}");
		list = facade.getResult(BusinessTypeEnum.CODE,
				callableStatement.toString());
		return list;
	}

	/**
	 * prepare parameters for SP createCodeDetail()
	 * @param args
	 * @param argsType
	 * @param argsIO
	 * @param i
	 * @throws Exception
	 */
	public static void prepareCode(CodeDetail detail, Object[] args, int[] argsType, ArgIO[] argsIO,
			int i) throws Exception {
		switch (i) {
		case 0: {
			// IN id char(3),
			args[i] = detail.getIdCode();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 1: {
			// in codeDet char(5),
			args[i] = detail.getCodeDet();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 2: {
			// in codeDesc char(30),
			args[i] = detail.getCodeDesc();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 3: {
			// out success boolean)
			args[i] = null;
			argsType[i] = java.sql.Types.BOOLEAN;
			argsIO[i] = ArgIO.OUT;
			break;
		}
		default:
			throw new Exception("Error while creating SP createAddress");
		}
	}

	/**
	 * Create a specific codeDetail
	 * @param codeDetail
	 * @return
	 */
	public static boolean createCode(CodeDetail detail) {		
		int LENGTH = 4;
		callableStatement = new StringBuilder();
		callableStatement
				.append("{call createCodeDetail(?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				CodeController.prepareCode(detail, args, argsType, argsIO, i);
			} catch (Exception e) {
				System.err.print(detail);
				e.printStackTrace();
			}
		}
		return(facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	
	}
	/**
	 * Update a specific codeDetail
	 * @param detail
	 * @return
	 */
	public static boolean updateCode(CodeDetail detail) {		
		int LENGTH = 4;
		callableStatement = new StringBuilder();
		callableStatement
				.append("{call updateCodeDetail(?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				CodeController.prepareCode(detail, args, argsType, argsIO, i);
			} catch (Exception e) {
				System.err.print(detail);
				e.printStackTrace();
			}
		}
		return(facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	
	}
	/**
	 * @return
	 */
	public static TreeMap<String, String> getCodeDetails(String code) {
		TreeMap<String, String> dropItemValues;
		CodeDetail detail;
		Collection<Business> list = CodeController.getAllCodeDetails(code);
		dropItemValues = new TreeMap<String, String>();
		Iterator<Business> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			detail = (CodeDetail) it.next();
			dropItemValues.put(detail.getCodeDesc(), detail.getCodeDet());
			i++;
		}
		return dropItemValues;
	}

	public static boolean removeCodeDetail(String code, String detail) {
		boolean success = true;
		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 3;
		callableStatement.append("{call removeCodeDetail(?, ?, ?)}");
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {
				case 0: {
					args[i] = code;
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					args[i] = detail;
					argsType[i] = java.sql.Types.CHAR;
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
					throw new Exception("Error while creating SP removeCodeDetail()");
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
