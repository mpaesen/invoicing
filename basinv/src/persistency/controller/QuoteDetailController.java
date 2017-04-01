/**
 * 
 */
package persistency.controller;

import java.util.Collection;
import java.util.Iterator;

import model.Business;
import model.BusinessTypeEnum;
import model.QuoteDetail;
import persistency.ArgIO;
import persistency.DBFacade;

/**
 * @author Mathy
 * 
 */
public class QuoteDetailController {
	private static DBFacade facade;
	private static StringBuilder callableStatement;

	/**
	 * Prepare parameters for SP createDetail
	 * 
	 * @param args
	 * @param argsType
	 * @param argsIO
	 * @param i
	 * @throws Exception
	 */
	public static void prepareDetail(QuoteDetail detail, Object[] args,
			int[] argsType, ArgIO[] argsIO, int i) throws Exception {
		switch (i) {
		case 0: {
			// IN id char(15),
			args[i] = detail.getIdQuote();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 1: {
			// in line int(3,0),
			args[i] = detail.getQteDetLine();
			argsType[i] = java.sql.Types.INTEGER;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 2: {
			// in Product char(15),
			args[i] = detail.getQteProdid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}

		case 3: {
			// in Qty decimal(7,2),
			args[i] = detail.getQteQty();
			argsType[i] = java.sql.Types.DECIMAL;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 4: {
			// in measure char(5),
			args[i] = detail.getQteMeasure();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 5: {
			// in price decimal(3,0),
			args[i] = detail.getQtePrice();
			argsType[i] = java.sql.Types.DECIMAL;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 6: {
			// in comments varchar(256),
			args[i] = detail.getQteComments();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}

		case 7: {
			// out success boolean)
			args[i] = null;
			argsType[i] = java.sql.Types.BOOLEAN;
			argsIO[i] = ArgIO.OUT;
			break;
		}
		default:
			throw new Exception("Error while creating SP createQuoteDetail()");
		}
	}

	/**
	 * @param detail
	 * @return
	 */
	public static boolean createQuoteDetail(QuoteDetail detail) {

		int LENGTH = 8;
		callableStatement = new StringBuilder();

		callableStatement
				.append("{call createQuoteDetail(?, ?, ?, ?, ?, ?, ?, ?)}");
		facade = new DBFacade();

		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				QuoteDetailController.prepareDetail(detail, args, argsType,
						argsIO, i);
			} catch (Exception e) {
				System.err.print(detail);
				e.printStackTrace();
			}
		}
		return (facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	}

	/**
	 * @param detail
	 * @return
	 */
	public static boolean updateQuoteDetail(QuoteDetail detail) {

		int LENGTH = 8;
		callableStatement = new StringBuilder();

		callableStatement
				.append("{call updateQuoteDetail(?, ?, ?, ?, ?, ?, ?, ?)}");
		facade = new DBFacade();

		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				QuoteDetailController.prepareDetail(detail, args, argsType,
						argsIO, i);
			} catch (Exception e) {
				System.err.print(detail);
				e.printStackTrace();
			}
		}
		return (facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	}

	public static boolean removeQuoteDetail(QuoteDetail detail) {

		int LENGTH = 3;
		callableStatement = new StringBuilder();

		callableStatement.append("{call removeQuoteDetail(?, ?, ?)}");
		facade = new DBFacade();

		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {
					case 0: {
						// IN id char(15),
						args[i] = detail.getIdQuote();
						argsType[i] = java.sql.Types.CHAR;
						argsIO[i] = ArgIO.IN;
						break;
					}
					case 1: {
						// in line int(3,0),
						args[i] = detail.getQteDetLine();
						argsType[i] = java.sql.Types.INTEGER;
						argsIO[i] = ArgIO.IN;
						break;
					}
					case 2: {
						// in line int(3,0),
						args[i] = "true";
						argsType[i] = java.sql.Types.BOOLEAN;
						argsIO[i] = ArgIO.OUT;
						break;
					}

				}
			} catch (Exception e) {
				System.err.print(detail);
				e.printStackTrace();
			}
		}
		return (facade.removeBusinessObject(callableStatement.toString(), args,
				argsType, argsIO));
	}

	/**
	 * @param id
	 * @return
	 */
	public static Collection<Business> readQuoteDetails(String id) {
		Collection<Business> list;
		int LENGTH = 1;
		callableStatement = new StringBuilder();

		callableStatement.append("{call readAllQuoteDetail(?)}");
		facade = new DBFacade();

		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				// IN id char(15),
				args[i] = id;
				argsType[i] = java.sql.Types.CHAR;
				argsIO[i] = ArgIO.IN;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list = (facade.getResult(BusinessTypeEnum.QUOTE_DETAIL,
				callableStatement.toString(), args, argsType, argsIO));
		return list;
	}

	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static QuoteDetail getOneQuoteDetail(String id, Integer line) {
		Collection<Business> list;
		int LENGTH = 2;
		callableStatement = new StringBuilder();
		callableStatement.append("{call readOneQuoteDetail(?, ?)}");
		facade = new DBFacade();

		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {
				case 0: {
					// IN id char(15),
					args[i] = id;
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					// IN line Integer,
					args[i] = line;
					argsType[i] = java.sql.Types.INTEGER;
					argsIO[i] = ArgIO.IN;
					break;
				}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list = (facade.getResult(BusinessTypeEnum.QUOTE_DETAIL,
				callableStatement.toString(), args, argsType, argsIO));
		if (!list.isEmpty()) {
			Iterator it = list.iterator();
			if (it.hasNext()) {
				return (QuoteDetail) it.next();
			}
		}
		return null;
	}
	
	/**
	 * read all quotes (with parameter filter)
	 * @param filter
	 * @return
	 */
	public static Collection<Business> getQuotesByProductId(String[] filter) {
		Collection<Business> list;

		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 2;
		callableStatement.append("{call readAllQuoteByProductId(?,?)}");
		Object[] args = {"",true};
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {

				case 0: {
					// in prodId char(id),
					if(!filter[i].equals("")){
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1:  {
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
							"Error while creating SP readAllQuoteByProductId()");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list = facade.getResult(BusinessTypeEnum.QUOTE_DETAIL,
				callableStatement.toString(), args, argsType, argsIO);
		return list;
	}
	

}
