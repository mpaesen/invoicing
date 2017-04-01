/**
 * 
 */
package persistency.controller;

import java.util.Collection;
import java.util.Iterator;

import model.Business;
import model.BusinessTypeEnum;
import model.Quote;
import persistency.ArgIO;
import persistency.DBFacade;

/**
 * @author Mathy
 *
 */
public class QuoteController {
	private static DBFacade facade;
	private static StringBuilder callableStatement;
	
	public static final String QUOTE_TYPE_OFFER = "O";
	public static final String QUOTE_TYPE_PRICE = "P";
	public static final String QUOTE_STATUS_CONFIRMED = "C";
	public static final String QUOTE_STATUS_PRINTED = "P";
	public static final String QUOTE_STATUS_OPEN = "O";
	public static final String DEFAULT_QUOTE_TYPE = "O";
	public static final String DEFAULT_QUOTE_STATUS = "O";



	public static Quote getQuote(String id) {
		Quote reference = null;
		DBFacade facade = new DBFacade();
		StringBuilder callableStatement  = new StringBuilder();
		
		int LENGTH = 1;
		callableStatement.append("{call readOneQuote(?)}");
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
		Collection<Business> list = facade.getResult(BusinessTypeEnum.QUOTE,
				callableStatement.toString(), args, argsType, argsIO);
		if (!list.isEmpty()) {
			Iterator<Business> it = list.iterator();
			while (it.hasNext()) {
				reference = (Quote) it.next();
			}
		}		
		return reference;
	}

	/**
	 * Prepare parameters for SP createQuote()
	 * 
	 * @param args
	 * @param argsType
	 * @param argsIO
	 * @param i
	 * @throws Exception
	 */
	public static void prepareQuote(Quote quote, Object[] args, int[] argsType, ArgIO[] argsIO,
			int i) throws Exception {
		switch (i) {
		case 0: {
			// IN id char(15),
			args[i] = quote.getIdQuote();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 1: {
			// in cusID char(15),
			args[i] = quote.getQteCusid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 2: {
			// in DlvAddid char(15),
			args[i] = quote.getQteDlvAddid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 3: {
			// in crtDate date,
			args[i] = quote.getQteCrtdate();
			argsType[i] = java.sql.Types.DATE;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 4: {
			// in crtUser char(15),
			args[i] = quote.getQteCrtUserid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 5: {
			// in updDate date,
			args[i] = quote.getQteUpdDate();
			argsType[i] = java.sql.Types.DATE;
			argsIO[i] = ArgIO.IN;
			break;
		}case 6: {
			// in updUser char(15),
			args[i] = quote.getQteUpdUserid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}case 7: {
			// in reqDlvDate date,
			args[i] = quote.getQteReqDlvDate();
			argsType[i] = java.sql.Types.DATE;
			argsIO[i] = ArgIO.IN;
			break;
		}case 8: {
			// in status char(5),
			args[i] = quote.getQteStatus();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}case 9: {
			// in type char(5),
			args[i] = quote.getQteType();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}case 10: {
			// in vat char(5),
			args[i] = quote.isQteVat();
			argsType[i] = java.sql.Types.BOOLEAN;
			argsIO[i] = ArgIO.IN;
			break;
		}case 11: {
			// in type varchar(256),
			args[i] = quote.getQteHeaderComments();
			argsType[i] = java.sql.Types.VARCHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 12: {
			// out success boolean)
			args[i] = null;
			argsType[i] = java.sql.Types.BOOLEAN;
			argsIO[i] = ArgIO.OUT;
			break;
		}
		default:
			throw new Exception("Error while creating SP createQuote()");
		}
	}

	/**
	 * @param quote
	 * @return
	 */
	public static boolean createQuote(Quote quote) {
		int LENGTH = 13;
		callableStatement = new StringBuilder();
		callableStatement.append("{call createQuoteHeader(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				QuoteController.prepareQuote(quote, args, argsType, argsIO, i);
			} catch (Exception e) {
				System.err.print(quote);
				e.printStackTrace();
			}
		}
		return(facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	}
	
	/**
	 * @param quote
	 * @return
	 */
	public static boolean updateQuote(Quote quote) {
		int LENGTH = 13;
		callableStatement = new StringBuilder();
		callableStatement.append("{call updateQuoteHeader(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				QuoteController.prepareQuote(quote, args, argsType, argsIO, i);
			} catch (Exception e) {
				System.err.print(quote);
				e.printStackTrace();
			}
		}
		return(facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	}
	
	
	
	/**
	 * read all quotes (with parameter filter)
	 * @param filter
	 * @return
	 */
	public static Collection<Business> getQuotes(String[] filter) {
		Collection<Business> list;

		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 3;
		callableStatement.append("{call readAllQuote(?,?,?)}");
		Object[] args = {"","",true};
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {

				case 0: {
					// in Cusid char(id),
					if(!filter[i].equals("")){
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					// in Qstatus char(5),
					if(!filter[i].equals("")){
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 2: {
					// active boolean)
					if(filter[i].equals("false")){
						args[i] = false;
					}
					argsType[i] = java.sql.Types.BOOLEAN;
					argsIO[i] = ArgIO.IN;
					break;
				}
				default:
					throw new Exception(
							"Error while creating SP readAllQuote()");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list = facade.getResult(BusinessTypeEnum.QUOTE,
				callableStatement.toString(), args, argsType, argsIO);
		return list;
	}
	
	/**
	 * read all quotes (with parameter filter)
	 * @param filter
	 * @return
	 */
	public static Collection<Business> getQuotesByCustomerName(String[] filter) {
		Collection<Business> list;

		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 3;
		callableStatement.append("{call readAllQuoteByCustomerName(?,?,?)}");
		Object[] args = {"","",true};
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {

				case 0: {
					// in CusName char(30),
					if(!filter[i].equals("")){
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					// in Qstatus char(5),
					if(!filter[i].equals("")){
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 2: {
					// active boolean)
					if(filter[i].equals("false")){
						args[i] = false;
					}
					argsType[i] = java.sql.Types.BOOLEAN;
					argsIO[i] = ArgIO.IN;
					break;
				}
				default:
					throw new Exception(
							"Error while creating SP readAllQuoteByCustomerName()");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list = facade.getResult(BusinessTypeEnum.QUOTE_VIEW,
				callableStatement.toString(), args, argsType, argsIO);
		return list;
	}	
	
	
	/**
	 * @param id
	 * @return
	 */
	public static boolean removeQuote(String id) {
		boolean success = true;
		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 2;
		callableStatement.append("{call removeQuote(?, ?)}");
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {
				case 0: {
					args[i] = id;
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					// active success boolean)
					//args[i] = false;
					argsType[i] = java.sql.Types.BOOLEAN;
					argsIO[i] = ArgIO.OUT;
					break;
				}
				default:
					throw new Exception(
							"Error while creating SP removeQuote()");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		success = facade.removeBusinessObject(
				callableStatement.toString(), args, argsType, argsIO);

		return success;
	}
	/**
	 * @param cusID
	 * @return
	 */
	public static boolean removeQuoteByCustomer(String cusID) {
		boolean success = true;
		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 2;
		callableStatement.append("{call removeQuoteByCustomer(?, ?)}");
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {
				case 0: {
					args[i] = cusID;
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}

				case 1: {
					// active success boolean)
					//args[i] = false;
					argsType[i] = java.sql.Types.BOOLEAN;
					argsIO[i] = ArgIO.OUT;
					break;
				}
				default:
					throw new Exception(
							"Error while creating SP removeQuoteByCustomer()");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		success = facade.removeBusinessObject(
				callableStatement.toString(), args, argsType, argsIO);

		return success;
	}

}
