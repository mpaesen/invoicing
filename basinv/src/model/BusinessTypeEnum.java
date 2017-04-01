package model;

public enum BusinessTypeEnum {
	CUSTOMER("Customer", 0),
	CONTACT("Contact", 1),
	ADDRESS("Address", 2),
	PRODUCT("Product", 3),
	PRICE("Price", 4),	
	QUOTE("Quote", 5),
	QUOTE_DETAIL("Quote", 6),
	INVOICE("Invoice", 7),	
	INVOICE_DETAIL("Quote", 8),
	NUMBER("Number", 9),
	CODE("Code", 10),
	CODE_DETAIL("Code Detail", 11),
	QUOTE_VIEW("Quotes by customer", 12),
	INVOICE_VIEW("Quotes by customer", 13);

	private String type;
	private int seq;
	
	private BusinessTypeEnum(String type, int seq) {
		this.seq = seq;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public int getSeq() {
		return seq;
	}
	

}
