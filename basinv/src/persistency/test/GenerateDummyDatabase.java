package persistency.test;

import java.util.ArrayList;

import model.Address;
import model.Business;
import model.BusinessTypeEnum;
import model.Contact;
import model.Customer;
import model.Invoice;
import model.InvoiceDetail;
import model.Price;
import model.Product;
import model.Quote;
import model.QuoteDetail;
import model.test.Dummy;
import model.test.DummyAddress;
import model.test.DummyContact;
import model.test.DummyFactory;
import model.test.DummyInvoice;
import model.test.DummyInvoiceDetail;
import model.test.DummyPrice;
import model.test.DummyQuote;
import model.test.DummyQuoteDetail;
import persistency.controller.AddressController;
import persistency.controller.ContactController;
import persistency.controller.CustomerController;
import persistency.controller.InvoiceController;
import persistency.controller.InvoiceDetailController;
import persistency.controller.PriceController;
import persistency.controller.ProductController;
import persistency.controller.QuoteController;
import persistency.controller.QuoteDetailController;

public class GenerateDummyDatabase extends Dummy {

	/**
	 * @param args
	 */

	private static ArrayList<Business> customers = new ArrayList<Business>();
	private static ArrayList<Business> addresses = new ArrayList<Business>();
	private static ArrayList<Business> products = new ArrayList<Business>();
	private static ArrayList<Business> prices = new ArrayList<Business>();
	private static ArrayList<Business> quotes = new ArrayList<Business>();
	private static ArrayList<Business> invoices = new ArrayList<Business>();
	public static void main(String[] args) {

		customers = createCustomers(BusinessTypeEnum.CUSTOMER, MAX_CUSTOMER);
		createContacts(BusinessTypeEnum.CONTACT, MAX_CONTACT, customers);
		addresses = createAddresses(BusinessTypeEnum.ADDRESS, MAX_ADDRESS, customers);
		products = createProducts(BusinessTypeEnum.PRODUCT, MAX_PRODUCT);
		prices = createPrices(BusinessTypeEnum.PRICE, MAX_PRICE, products);
		quotes = createQuotes(BusinessTypeEnum.QUOTE, MAX_QUOTE, customers, addresses);
		createQuoteDetails(BusinessTypeEnum.QUOTE_DETAIL, MAX_QUOTE_LINE, quotes, products, prices);
		invoices = createInvoices(BusinessTypeEnum.INVOICE, MAX_INVOICE, customers, addresses);
		createInvoiceDetails(BusinessTypeEnum.INVOICE_DETAIL, MAX_INVOICE_LINE, invoices, products, prices);

	}

	public static ArrayList<Business> createCustomers(BusinessTypeEnum businessTypeEnum,
			int length) {
		ArrayList<Business> objects = new ArrayList<Business>();
		objects.ensureCapacity(length);
		Business object;
		for (int i = 0; i < length; i++) {
			object = DummyFactory.createBusiness(businessTypeEnum);
			objects.add(object);
			CustomerController.createCustomer((Customer)object);
		}
		return objects;
	}


	public static ArrayList<Business> createContacts(BusinessTypeEnum businessTypeEnum,
			int length, ArrayList<Business> customers) {
		ArrayList<Business> objects = new ArrayList<Business>();
		objects.ensureCapacity(length);
		Business object;
		for (Business customer : customers) {
			for (int i = 0; i < length; i++) {
				object = DummyContact.createContact(((Customer) customer).getIdCus());
				objects.add(object);
				ContactController.createContact((Contact)object);
			}
		}
		return objects;
	}
	

	public static ArrayList<Business> createAddresses(BusinessTypeEnum businessTypeEnum,
			int length, ArrayList<Business> customers) {
		ArrayList<Business> objects = new ArrayList<Business>();
		objects.ensureCapacity(length);
		Business object;
		for (Business customer : customers) {
			for (int i = 0; i < length; i++) {
				object = DummyAddress.createAddress(((Customer) customer).getIdCus());
				objects.add(object);
				AddressController.createAddress((Address)object);
			}
		}
		return objects;
	}

	public static ArrayList<Business> createProducts(BusinessTypeEnum businessTypeEnum,
			int length) {
		ArrayList<Business> objects = new ArrayList<Business>();
		objects.ensureCapacity(length);
		Business object;
		for (int i = 0; i < length; i++) {
			object = DummyFactory.createBusiness(businessTypeEnum);
			objects.add(object);
			ProductController.createProduct((Product)object);
		}
		return objects;
	}
	public static ArrayList<Business> createPrices(BusinessTypeEnum businessTypeEnum,
			int length, ArrayList<Business> products) {
		ArrayList<Business> objects = new ArrayList<Business>();
		objects.ensureCapacity(length);
		Business object;
		for (Business product : products) {
			for (int i = 0; i < length; i++) {
				object = DummyPrice.createPrice((((Product) product).getIdProd()));
				objects.add(object);
				PriceController.createPrice((Price)object);
			}
		}
		return objects;
	}
	public static ArrayList<Business> createQuotes(BusinessTypeEnum businessTypeEnum,
			int length, ArrayList<Business> customers, ArrayList<Business> addresses) {
		ArrayList<Business> objects = new ArrayList<Business>();
		objects.ensureCapacity(length);
		Business object;
		for (Business customer : customers) {
			for (int i = 0; i < length; i++) {
				object = DummyQuote.createQuote(((Customer) customer).getIdCus(), ((Address)addresses.get(1)).getIdAddress());
				objects.add(object);
				QuoteController.createQuote((Quote)object);
			}
		}
		return objects;
	}
	public static ArrayList<Business> createQuoteDetails(BusinessTypeEnum businessTypeEnum,
			int length, ArrayList<Business> quotes, ArrayList<Business> products, ArrayList<Business> prices) {
		ArrayList<Business> objects = new ArrayList<Business>();
		objects.ensureCapacity(length);
		Business object;
		for (Business quote : quotes) {
			for (int i = 0; i < length; i++) {
				object = DummyQuoteDetail.createQuoteDetail(((Quote) quote).getIdQuote(), ((Product)products.get(1)).getIdProd());
				objects.add(object);
				QuoteDetailController.createQuoteDetail((QuoteDetail)object);
			}
		}
		return objects;
	}
	public static ArrayList<Business> createInvoices(BusinessTypeEnum businessTypeEnum,
			int length, ArrayList<Business> customers, ArrayList<Business> addresses) {
		ArrayList<Business> objects = new ArrayList<Business>();
		objects.ensureCapacity(length);
		Business object;
		for (Business customer : customers) {
			for (int i = 0; i < length; i++) {
				object = DummyInvoice.createInvoice(((Customer) customer).getIdCus(), ((Address)addresses.get(1)).getIdAddress());
				objects.add(object);
				InvoiceController.createInvoice((Invoice)object);
			}
		}
		return objects;
	}
	public static ArrayList<Business> createInvoiceDetails(BusinessTypeEnum businessTypeEnum,
			int length, ArrayList<Business> invoices, ArrayList<Business> products, ArrayList<Business> prices) {
		ArrayList<Business> objects = new ArrayList<Business>();
		objects.ensureCapacity(length);
		Business object;
		for (Business invoice : invoices) {
			for (int i = 0; i < length; i++) {
				object = DummyInvoiceDetail.createInvoiceDetail(((Invoice) invoice).getIdInvoice(), ((Product)products.get(1)).getIdProd());
				objects.add(object);
				InvoiceDetailController.createInvoiceDetail((InvoiceDetail)object);
			}
		}
		return objects;
	}


}
