/**
 * 
 */
package persistency.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import model.Address;
import model.Business;
import model.BusinessTypeEnum;
import model.Customer;
import model.Invoice;
import model.Quote;
import persistency.ArgIO;
import persistency.DBFacade;
import utilities.Constants;
import utilities.Date;
import utilities.DatumException;
import utilities.FixTypes;
import utilities.NumberEnum;

/**
 * @author Mathy
 * 
 */
public class InvoiceController {
	private static DBFacade facade;
	private static StringBuilder callableStatement;

	public static Invoice getInvoice(String id) {
		Invoice reference = null;
		DBFacade facade = new DBFacade();
		StringBuilder callableStatement = new StringBuilder();

		int LENGTH = 1;
		callableStatement.append("{call readOneInvoice(?)}");
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
		Collection<Business> list = facade.getResult(BusinessTypeEnum.INVOICE,
				callableStatement.toString(), args, argsType, argsIO);
		if (!list.isEmpty()) {
			Iterator<Business> it = list.iterator();
			while (it.hasNext()) {
				reference = (Invoice) it.next();
			}
		}
		return reference;
	}

	/**
	 * Prepare parameters for SP createInvoice()
	 * 
	 * @param args
	 * @param argsType
	 * @param argsIO
	 * @param i
	 * @throws Exception
	 */
	public static void prepareInvoice(Invoice invoice, Object[] args,
			int[] argsType, ArgIO[] argsIO, int i) throws Exception {
		switch (i) {
		case 0: {
			// IN id char(15),
			args[i] = invoice.getIdInvoice();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 1: {
			// in cusID char(15),
			args[i] = invoice.getInvCusid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 2: {
			// in invAddid char(15),
			args[i] = invoice.getInvAddid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 3: {
			// in DlvAddid char(15),
			args[i] = invoice.getInvDlvAddid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 4: {
			// in crtDate date,
			args[i] = new utilities.Date();
			argsType[i] = java.sql.Types.DATE;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 5: {
			// in crtUser char(15),
			args[i] = invoice.getInvCrtUserid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 6: {
			// in updDate date,
			args[i] = new utilities.Date();
			argsType[i] = java.sql.Types.DATE;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 7: {
			// in updUser char(15),
			args[i] = invoice.getInvUpdUserid();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 8: {
			// invoice date,
			args[i] = invoice.getInvDate();
			argsType[i] = java.sql.Types.DATE;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 9: {
			// in due date,
			args[i] = invoice.getInvDueDate();
			argsType[i] = java.sql.Types.DATE;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 10: {
			// in status char(5),
			args[i] = invoice.getInvStatus();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 11: {
			// in type char(1),
			args[i] = invoice.getInvType();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 12: {
			// in vat char(5),
			args[i] = invoice.isInvVat();
			argsType[i] = java.sql.Types.BOOLEAN;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 13: {
			// in Comments varchar(256)
			args[i] = invoice.getInvHeaderComments();
			argsType[i] = java.sql.Types.VARCHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 14: {
			// out success boolean)
			args[i] = null;
			argsType[i] = java.sql.Types.BOOLEAN;
			argsIO[i] = ArgIO.OUT;
			break;
		}
		default:
			throw new Exception("Error while creating SP createInvoice()");
		}
	}

	public static boolean createInvoice(Invoice invoice) {
		int LENGTH = 15;
		callableStatement = new StringBuilder();
		callableStatement
				.append("{call createInvoiceHeader(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				InvoiceController.prepareInvoice(invoice, args, argsType,
						argsIO, i);
			} catch (Exception e) {
				System.err.print(invoice);
				e.printStackTrace();
			}
		}
		return (facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	}

	public static boolean updateInvoice(Invoice invoice) {
		int LENGTH = 15;
		callableStatement = new StringBuilder();
		callableStatement
				.append("{call updateInvoiceHeader(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				InvoiceController.prepareInvoice(invoice, args, argsType,
						argsIO, i);
			} catch (Exception e) {
				System.err.print(invoice);
				e.printStackTrace();
			}
		}
		return (facade.createObject(callableStatement.toString(), args,
				argsType, argsIO));
	}

	public static boolean removeInvoice(String id) {
		boolean success = true;
		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 2;
		callableStatement.append("{call removeInvoice(?, ?)}");
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
					// args[i] = false;
					argsType[i] = java.sql.Types.BOOLEAN;
					argsIO[i] = ArgIO.OUT;
					break;
				}
				default:
					throw new Exception(
							"Error while creating SP removeInvoice()");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		success = facade.removeBusinessObject(callableStatement.toString(),
				args, argsType, argsIO);

		return success;
	}

	/**
	 * read all quotes (with parameter filter)
	 * 
	 * @param filter
	 * @return
	 */
	public static Collection<Business> getInvoices(String[] filter) {
		Collection<Business> list;

		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 4;
		callableStatement.append("{call readAllInvoice(?,?,?,?)}");
		Object[] args = { Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, true };
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {

				case 0: {
					// in Customer char(15),
					if (!filter[i].equals(Constants.EMPTY)) {
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					// in istatus char(5),
					if (!filter[i].equals(Constants.EMPTY)) {
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 2: {
					// in itype char(5),
					if (!filter[i].equals(Constants.EMPTY)) {
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 3: {
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
							"Error while creating SP readAllInvoice()");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list = facade.getResult(BusinessTypeEnum.INVOICE, callableStatement
				.toString(), args, argsType, argsIO);
		return list;
	}

	public static Collection<Business> getInvoicesByCustomerName(String[] filter) {
		Collection<Business> list;

		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 4;
		callableStatement.append("{call readAllInvoiceByCustomerName(?,?,?,?)}");
		Object[] args = { Constants.EMPTY, Constants.EMPTY, Constants.EMPTY, true };
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {

				case 0: {
					// in CusName char(30),
					if (!filter[i].equals(Constants.EMPTY)) {
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					// in istatus char(5),
					if (!filter[i].equals(Constants.EMPTY)) {
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 2: {
					// in iType char(1),
					if (!filter[i].equals(Constants.EMPTY)) {
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 3: {
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
							"Error while creating SP readAllInvoiceByCustomer()");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list = facade.getResult(BusinessTypeEnum.INVOICE_VIEW,
				callableStatement.toString(), args, argsType, argsIO);
		return list;
	}

	public static Invoice createNewInvoice(Quote quote) {
		String comments = quote.getQteHeaderComments();
		Address address = null;
		Customer customer = CustomerController.getCustomer(quote
				.getQteCusid());
		List<Address> list = getAddressesByCustomer(customer, FixTypes.INVOICE_ADRESS_TYPE);
		if (!list.isEmpty()) {
			 address = list.get(0);
		}

		Date toDay = null, dueDate = null;
		try {
			toDay = new Date();
			dueDate = new Date(toDay);
			BigDecimal deLay = new BigDecimal(CodeController.getOneCodeDetail(
					Constants.PAYMENT_CONDITIONS, customer.getCusPay()).getCodeDet());
			dueDate.veranderDatum(deLay.intValue());
		} catch (DatumException e) {
			e.printStackTrace();
		}

		String invoiceID = NumberController.readLastNumber(
				NumberEnum.INVOICE.getType(), toDay.getJaar()).toString();

		Invoice newInvoice = new Invoice(invoiceID, customer.getIdCus(),
				address.getIdAddress(), quote.getQteDlvAddid(), toDay, // crtDate
				Integer.toHexString(1), // invcrtuserid | char (15)
				toDay, // invupddate | datetime
				Integer.toHexString(1), // invupduserid | char(15)
				toDay,// invDate
				dueDate, // invDueDate | date
				FixTypes.DEFAULT_INVOICE_STATUS, // invStatus | char(1)
				FixTypes.DEFAULT_INVOICE_TYPE, // invType | char(1)
				// (default value: Offer)
				true, // invVat | tinyint(1)
				comments, // invHeadercomments varchar(256)
				true // Active | tinyint(1)
		);
		createInvoice(newInvoice);
		return newInvoice;
	}
		
	public static Invoice createNewCreditNote(Invoice invoice) {

		Customer customer = CustomerController.getCustomer(invoice
				.getInvCusid());

		Date toDay = null, dueDate = null;
		try {
			toDay = new Date();
			dueDate = new Date(toDay);
			BigDecimal deLay = new BigDecimal(CodeController.getOneCodeDetail(
					Constants.PAYMENT_CONDITIONS, customer.getCusPay()).getCodeDet());
			dueDate.veranderDatum(deLay.intValue());
		} catch (DatumException e) {
			e.printStackTrace();
		}

		String invoiceID = NumberController.readLastNumber(
				NumberEnum.CREDIT_NOTE.getType(), toDay.getJaar()).toString();

		Invoice newCreditNote = new Invoice(invoiceID, customer.getIdCus(),
				invoice.getInvAddid(), invoice.getInvDlvAddid(), toDay, // crtDate
				Integer.toHexString(1), // invcrtuserid | char (15)
				toDay, // invupddate | datetime
				Integer.toHexString(1), // invupduserid | char(15)
				toDay,// invDate
				dueDate, // invDueDate | date
				FixTypes.DEFAULT_INVOICE_STATUS, // invStatus | char(1)
				FixTypes.CREDIT_NOTE, // invType | char(1)
				// (default value: Offer)
				true, // invVat | tinyint(1)
				Constants.COMMENTS + invoice.getIdInvoice(), // invHeadercomments varchar(256)
				true // Active | tinyint(1)
		);
		createInvoice(newCreditNote);
		invoice.setInvStatus(FixTypes.INVOICE_STATUS_CONFIRMED);
		
		updateInvoice(invoice);
		return newCreditNote;
	}

	/**
	 * All deliveradresses or only the invoice address
	 * 
	 * @param type
	 * @return
	 */
	public static List<Address> getAddressesByCustomer(Customer customer,
			String type) {
		String id = customer.getIdCus();
		String[] filter = { id, type, "true" };

		ArrayList<Address> list = new ArrayList<Address>();
		Address address;
		Collection<Business> businessList = AddressController
				.getAddresses(filter);
		Iterator<Business> it = businessList.iterator();

		while (it.hasNext()) {
			address = (Address) it.next();
			list.add(address);
		}
		return list;
	}

}
