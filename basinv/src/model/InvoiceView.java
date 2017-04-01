package model;

public class InvoiceView extends Invoice {
	private String cusName;
	private String cusType;
	private String cusVat;
	
	public InvoiceView(Invoice invoice, String cusName, String cusType, String cusVat) {
		super(invoice);
		this.cusName = cusName;
		this.cusType = cusType;
		this.cusVat = cusVat;
	}

	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusType() {
		return cusType;
	}
	public void setCusType(String cusType) {
		this.cusType = cusType;
	}
	public String getCusVat() {
		return cusVat;
	}
	public void setCusVat(String cusVat) {
		this.cusVat = cusVat;
	}

}
