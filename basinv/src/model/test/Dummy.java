package model.test;
import java.util.Random;
public class Dummy {
	private static Random random = new Random();
	public static final int VAT_LENGTH = 999999999;
	public static final int VAT_MINIMUM = 10000000;
	public static final int PHONE_MINIMUM = 100000;
	public static final int PHONE_MAXIMUM = 999999;
	public static final int PHONE_ZONE_MINIMUM = 2;
	public static final int PHONE_ZONE_MAXIMUM = 99;
	public static final int MOBILE_PROVIDER_MINIMUM = 400;
	public static final int MOBILE_PROVIDER_MAXIMUM = 499;
	
	public static final int MAX_CUSTOMER = 10;
	public static final int MAX_ADDRESS = 3;
	public static final int MAX_CONTACT = 5;
	public static final int MAX_PRODUCT = 20;
	public static final int MAX_PRICE = 3;
	public static final int MAX_QUOTE = 10;
	public static final int MAX_QUOTE_LINE = 8;
	public static final int MAX_INVOICE = 5;
	public static final int MAX_INVOICE_LINE = 8;
	
	public static Random getRandom() {
		return random;
	}
}
