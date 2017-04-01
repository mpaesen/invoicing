/**
 * 
 */
package persistency.controller;

import java.util.Collection;
import java.util.Iterator;

import model.Business;
import model.BusinessTypeEnum;
import model.Customer;
import persistency.ArgIO;
import persistency.DBFacade;

/**
 * @author Mathy
 * 
 */
public class CustomerController {
	private static DBFacade facade;
	private static StringBuilder callableStatement;


	/**
	 * Read one customer with SP readOneCustomer()
	 * 
	 * @param id
	 * @return
	 */
	public static Customer getCustomer(String id) {
		Customer reference = null;
		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 1;
		callableStatement.append("{call readOneCustomer(?)}");
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
		Collection<Business> list = facade.getResult(BusinessTypeEnum.CUSTOMER,
				callableStatement.toString(), args, argsType, argsIO);
		if (!list.isEmpty()) {
			Iterator<Business> it = list.iterator();
			while (it.hasNext()) {
				reference = (Customer) it.next();
			}
		}
		return reference;
	}

	/**
	 * Read one customer with SP readOneCustomer()
	 * 
	 * @param id
	 * @return
	 */
	public static boolean removeCustomer(String id) {
		boolean success = true;
		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 2;
		callableStatement.append("{call removeCustomer(?, ?)}");
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
							"Error while creating SP removeOneCustomerdAllCustomer()");
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
	 * read list of customers depending on applied filter
	 * 
	 * @param filter
	 *            [name*, Type, active]
	 * @return Collection of Business objects (Customers)
	 */
	public static Collection<Business> getCustomers(String[] filter) {
		Collection<Business> list;

		callableStatement = new StringBuilder();
		facade = new DBFacade();
		int LENGTH = 3;
		callableStatement.append("{call readAllCustomer(?,?,?)}");
		Object[] args = { "", "", true };
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				switch (i) {

				case 0: {
					// in Cname char(30),
					if (!filter[i].equals("")) {
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;
				}
				case 1: {
					// in Ctype char(5),
					if (!filter[i].equals("")) {
						args[i] = filter[i];
					}
					argsType[i] = java.sql.Types.CHAR;
					argsIO[i] = ArgIO.IN;
					break;

				}
				case 2: {
					// active success boolean)
					if (filter[i].equals("false")) {
						args[i] = false;
					}
					argsType[i] = java.sql.Types.BOOLEAN;
					argsIO[i] = ArgIO.IN;
					break;
				}
				default:
					throw new Exception(
							"Error while creating SP readAllCustomer()");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list = facade.getResult(BusinessTypeEnum.CUSTOMER, callableStatement
				.toString(), args, argsType, argsIO);
		return list;
	}

	/**
	 * Prepare parameters for SP createCustomer()
	 * 
	 * @param args
	 * @param argsType
	 * @param argsIO
	 * @param i
	 * @throws Exception
	 */
	public static void prepareCustomer(Customer customer, Object[] args,
			int[] argsType, ArgIO[] argsIO, int i) throws Exception {
		switch (i) {
		case 0: {
			// IN id char(15),
			args[i] = customer.getIdCus();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 1: {
			// in Cname char(50),
			args[i] = customer.getCusName();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 2: {
			// in cvat char(15),
			args[i] = customer.getCusVat();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 3: {
			// in phone char(20),
			args[i] = customer.getCusPhone();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 4: {
			// in fax char(20),
			args[i] = customer.getCusFax();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 5: {
			// in Mobile char(20),
			args[i] = customer.getCusMobile();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 6: {
			// in email char(30),
			args[i] = customer.getCusEMail();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 7: {
			// in cuswebsite char(30),
			args[i] = customer.getCusWebsite();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 8: {
			// in cType char(5),
			args[i] = customer.getCusType();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 9: {
			// in class char(5),
			args[i] = customer.getCusClass();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 10: {
			// in lng char(5),
			args[i] = customer.getCusLang();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}

		case 11: {
			// in Cur char(5),
			args[i] = customer.getCusCur();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 12: {
			// in Pay char(5),
			args[i] = customer.getCusPay();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 13: {
			// in activity char(50),
			args[i] = customer.getCusActivity();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 14: {
			// in info char(50),
			args[i] = customer.getCusAccount();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 15: {
			// in account char(20),
			args[i] = customer.getCusInfo();
			argsType[i] = java.sql.Types.CHAR;
			argsIO[i] = ArgIO.IN;
			break;
		}
		case 16: {
			// out success boolean)
			args[i] = null;
			argsType[i] = java.sql.Types.BOOLEAN;
			argsIO[i] = ArgIO.OUT;
			break;
		}
		default:
			throw new Exception("Error while creating SP createCustomer");
		}
	}

	/**
	 * Creates one concrete customer
	 * 
	 * @param customer
	 * @return
	 */
	public static boolean createCustomer(Customer customer) {
		int LENGTH = 17;
		callableStatement = new StringBuilder();
		callableStatement
				.append("{call createCustomer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				CustomerController.prepareCustomer(customer, args, argsType,
						argsIO, i);
			} catch (Exception e) {
				System.err.print(customer);
				e.printStackTrace();
			}
		}
		return facade.createObject(callableStatement.toString(), args,
				argsType, argsIO);

	}
	/**
	 * Updates one concrete customer
	 * 
	 * @param customer
	 * @return
	 */
	public static boolean updateCustomer(Customer customer) {
		int LENGTH = 17;
		callableStatement = new StringBuilder();
		callableStatement
				.append("{call updateCustomer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		facade = new DBFacade();
		Object[] args = new Object[LENGTH];
		int[] argsType = new int[LENGTH];
		ArgIO[] argsIO = new ArgIO[LENGTH];
		for (int i = 0; i < args.length; i++) {
			try {
				CustomerController.prepareCustomer(customer, args, argsType,
						argsIO, i);
			} catch (Exception e) {
				System.err.print(customer);
				e.printStackTrace();
			}
		}
		return facade.createObject(callableStatement.toString(), args,
				argsType, argsIO);

	}
}
