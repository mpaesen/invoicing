/**
 * 
 */
package model.test;

import model.Address;
import model.Business;
import model.BusinessTypeEnum;
import model.Customer;
import model.Invoice;
import model.Product;
import model.Quote;

/**
 * @author Mathy
 * 
 */
public class DummyFactory {
	public static Business createBusiness(BusinessTypeEnum businessTypeEnum) {
		Business object, object2;
		switch (businessTypeEnum.getSeq()) {
		case 0:
			object = DummyCustomer.createCustomer();
			break;
		case 1: {
			object = DummyCustomer.createCustomer();
			object = DummyContact.createContact(((Customer) object).getIdCus());
			break;
		}
		case 2: {
			object = DummyCustomer.createCustomer();
			object = DummyAddress.createAddress(((Customer) object).getIdCus());
			break;
		}case 3: {
			
			object = DummyProduct.createProduct();
			break;
		}case 4: {
			
			object = DummyProduct.createProduct();
			object = DummyPrice.createPrice(((Product)object).getIdProd());
			break;
		}case 5: {
			object = DummyCustomer.createCustomer();
			object2 = DummyAddress.createAddress(((Customer) object).getIdCus());
			object = DummyQuote.createQuote(((Customer) object).getIdCus(), ((Address)object2).getIdAddress());
			break;
		}case 6: {
			object = DummyCustomer.createCustomer();
			object2 = DummyAddress.createAddress(((Customer) object).getIdCus());
			object = DummyQuote.createQuote(((Customer) object).getIdCus(), ((Address)object2).getIdAddress());
			object2 = DummyProduct.createProduct();
			object = DummyQuoteDetail.createQuoteDetail(((Quote) object).getIdQuote(), ((Product)object2).getIdProd());	
			break;
		}case 7: {
			object = DummyCustomer.createCustomer();
			object2 = DummyAddress.createAddress(((Customer) object).getIdCus());
			object = DummyInvoice.createInvoice(((Customer) object2).getIdCus(), ((Address)object2).getIdAddress());				
			break;
		}case 8: {
			object = DummyCustomer.createCustomer();
			object2 = DummyAddress.createAddress(((Customer) object).getIdCus());
			object = DummyInvoice.createInvoice(((Customer) object).getIdCus(),((Address)object2).getIdAddress());
			object2 = DummyProduct.createProduct();
			object = DummyInvoiceDetail.createInvoiceDetail(((Invoice) object).getIdInvoice(), ((Product)object2).getIdProd());				
			break;
		}case 9: {
			object = DummyNumber.createNumber();				
			break;
		}default:
			object = null;
		}
		return object;
	}

}
