/**
 * 
 */
package persistency.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import model.Business;
import model.BusinessTypeEnum;
import model.Invoice;
import model.InvoiceDetail;
import model.Quote;
import model.QuoteDetail;
import persistency.ArgIO;
import persistency.DBFacade;
import utilities.Figures;

/**
 * @author Mathy
 * 
 */
public class InvoiceDetailController {
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
	public static void prepareDetail(InvoiceDetail detail, Object[] args,
			int[] argsType, ArgIO[] argsIO, int i) throws Exception {
		switch (i) {
		case 0: {
			// IN id char(15),
			args[i] = detail.getIdInvoice();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 1: {
			// in line int(3,0),
			args[i] = detail.getInvDetLine();
			argsType[i] = java.sql.Types.INTEGER;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 2: {
			// in Product char(15),
			args[i] = detail.getInvProdid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}

		case 3: {
			// in Qty decimal(7,2),
			args[i] = detail.getInvQty();
			argsType[i] = java.sql.Types.DECIMAL;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 4: {
			// in measure char(5),
			args[i] = detail.getInvMeasure();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 5: {
			// in price decimal(3,0),
			args[i] = detail.getInvPrice();
			argsType[i] = java.sql.Types.DECIMAL;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 6: {// in vat% char(5),
			args[i] = detail.getInvVat();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 7: {
			// in comments varchar(256),
			args[i] = detail.getInvComments();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}

		case 8: {

			// out success boolean)
			args[i] = null;
			argsType[i] = java.sql.Types.BOOLEAN;
			argsIO[i] = ArgIO.OUT;
			break;
		}
		default:
			throw new Exception("Error while creating SP createInvoiceDetail()");
		}
	}

	public static boolean removeInvoiceDetail(InvoiceDetail detail) {

		int LENGTH = 3;
		callableStatement = new StringBuilder();

		callableStatement.append("{call removeInvoiceDetail(?, ?, ?)}");
		facade = new DBFacade();

		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {
				case 0: {
					// IN id char(15),
					args[i] = detail.getIdInvoice();
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					// in line int(3,0),
					args[i] = detail.getInvDetLine();
					argsType[i] = java.sql.Types.INTEGER;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 2: {
					// in active,
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

	public static boolean createInvoiceDetail(InvoiceDetail detail) {

		int LENGTH = 9;
		callableStatement = new StringBuilder();
		callableStatement
				.append("{call createInvoiceDetail(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				InvoiceDetailController.prepareDetail(detail, args, argsType,
						argsIO, i);
			} catch (Exception e) {
				System.err.print(detail);
				e.printStackTrace();
			}
		}
		return (facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	}

	public static boolean updateInvoiceDetail(InvoiceDetail detail) {

		int LENGTH = 9;
		callableStatement = new StringBuilder();
		callableStatement
				.append("{call updateInvoiceDetail(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				InvoiceDetailController.prepareDetail(detail, args, argsType,
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
	 * @param id
	 * @return
	 */
	public static Collection<Business> readInvoiceDetails(String id) {
		Collection<Business> list;
		int LENGTH = 1;
		callableStatement = new StringBuilder();

		callableStatement.append("{call readAllInvoiceDetail(?)}");
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
		list = (facade.getResult(BusinessTypeEnum.INVOICE_DETAIL,
				callableStatement.toString(), args, argsType, argsIO));
		return list;
	}

	/**
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static InvoiceDetail getOneInvoiceDetail(String id, Integer line) {
		Collection<Business> list;
		int LENGTH = 2;
		callableStatement = new StringBuilder();
		callableStatement.append("{call readOneInvoiceDetail(?, ?)}");
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
		list = (facade.getResult(BusinessTypeEnum.INVOICE_DETAIL,
				callableStatement.toString(), args, argsType, argsIO));
		if (!list.isEmpty()) {
			Iterator it = list.iterator();
			if (it.hasNext()) {
				return (InvoiceDetail) it.next();
			}
		}
		return null;
	}

	/**
	 * read all invoices (with parameter filter)
	 * 
	 * @param filter
	 * @return
	 */
	public static Collection<Business> getInvoicesByProductId(String[] filter) {
		Collection<Business> list;

		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 2;
		callableStatement.append("{call readAllInvoiceByProductId(?,?)}");
		Object[] args = { "", true };
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {

				case 0: {
					// in prodId char(id),
					if (!filter[i].equals("")) {
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					// active boolean)
					if (filter[i].equals("false")) {
						args[i] = false;
					}
					argsType[i] = java.sql.Types.BOOLEAN;
					argsIO[i] = ArgIO.IN;
					break;
				}
				default:
					throw new Exception(
							"Error while creating SP readAllInvoiceByProductId()");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list = facade.getResult(BusinessTypeEnum.INVOICE_DETAIL,
				callableStatement.toString(), args, argsType, argsIO);
		return list;
	}

	public static void createNewInvoiceDetails(Invoice invoice, Quote quote) {
		Collection<Business> list = QuoteDetailController
				.readQuoteDetails(quote.getIdQuote());
		if (list != null) {
			Iterator it = list.iterator();
			QuoteDetail detail = null;
			while (it.hasNext()) {
				detail = (QuoteDetail) it.next();
				createDetail(invoice, detail);
			}
		}
	}
	
	public static void createNewCreditNoteDetails(Invoice creditNote, Invoice invoice) {
		Collection<Business> list = InvoiceDetailController
				.readInvoiceDetails(invoice.getIdInvoice());
		if (list != null) {
			Iterator it = list.iterator();
			InvoiceDetail detail = null;
			while (it.hasNext()) {
				detail = (InvoiceDetail) it.next();
				createDetail(creditNote, detail);
			}
		}
	}

	private static void createDetail(Invoice invoice, QuoteDetail detail) {
		InvoiceDetail newDetail = new InvoiceDetail(invoice.getIdInvoice(),
				detail.getQteDetLine(), detail.getQteProdid(), detail
						.getQteQty(), detail.getQteMeasure(), detail
						.getQtePrice(), Figures.ZERO_STRING, detail.getQteComments(),
				true);
		createInvoiceDetail(newDetail);
	}
	
	private static void createDetail(Invoice invoice, InvoiceDetail detail) {
		InvoiceDetail newDetail = new InvoiceDetail(invoice.getIdInvoice(),
				detail.getInvDetLine(), detail.getInvProdid(), new BigDecimal(detail
						.getInvQty().doubleValue() * Figures.INVERSE), detail.getInvMeasure(), detail
						.getInvPrice(), detail.getInvVat(), detail.getInvComments(),
				true);
		createInvoiceDetail(newDetail);
	}

}
