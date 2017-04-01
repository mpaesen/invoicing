package model.test;

public enum DummyType {
	CUSTOMER("Customer", 0),
	CONTACT("Contact", 1),
	ADDRESS("Address", 2),
	PRODUCT("Product", 3),
	PRICE("Price", 4),
	INVOICE("Invoice", 5),
	QUOTE("Quote", 6);

	private String type;
	private int seq;
	
	private DummyType(String type, int seq) {
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
