package model;

import java.math.BigDecimal;

public class InvoiceDetail implements Business {
	private String idInvoice;
	private Integer invDetLine;
	private String invProdid;
	private BigDecimal invQty;
	private String invMeasure;
	private BigDecimal invPrice;
	private String invVat;
	private String invComments;
	private boolean active;

	public InvoiceDetail(String idInvoice, Integer invDetLine,
			String invProdid, BigDecimal invQty, String invMeasure,
			BigDecimal invPrice, String invVat, String invComments,
			boolean active) {
		super();
		this.idInvoice = idInvoice;
		this.invDetLine = invDetLine;
		this.invProdid = invProdid;
		this.invQty = invQty;
		this.invMeasure = invMeasure;
		this.invPrice = invPrice;
		this.invVat = invVat;
		this.invComments = invComments;
		this.active = active;
	}

	public InvoiceDetail(InvoiceDetail detail) {
		super();
		this.idInvoice = detail.idInvoice;
		this.invDetLine = detail.invDetLine;
		this.invProdid = detail.invProdid;
		this.invQty = detail.invQty;
		this.invMeasure = detail.invMeasure;
		this.invPrice = detail.invPrice;
		this.invVat = detail.invVat;
		this.invComments = detail.invComments;
		this.active = detail.active;

	}

	public String getIdInvoice() {
		return idInvoice;
	}

	public void setIdInvoice(String idInvoice) {
		this.idInvoice = idInvoice;
	}

	public Integer getInvDetLine() {
		return invDetLine;
	}

	public void setInvDetLine(Integer invDetLine) {
		this.invDetLine = invDetLine;
	}

	public String getInvProdid() {
		return invProdid;
	}

	public void setInvProdid(String invProdid) {
		this.invProdid = invProdid;
	}

	public BigDecimal getInvQty() {
		return invQty;
	}

	public void setInvQty(BigDecimal invQty) {
		this.invQty = invQty;
	}

	public String getInvMeasure() {
		return invMeasure;
	}

	public void setInvMeasure(String invMeasure) {
		this.invMeasure = invMeasure;
	}

	public BigDecimal getInvPrice() {
		return invPrice;
	}

	public void setInvPrice(BigDecimal invPrice) {
		this.invPrice = invPrice;
	}

	public String getInvVat() {
		return invVat;
	}

	public void setInvVat(String invVat) {
		this.invVat = invVat;
	}

	public String getInvComments() {
		return invComments;
	}

	public void setInvComments(String invComments) {
		this.invComments = invComments;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {
		return "Invoice: " + getIdInvoice() + " line: " + getInvDetLine()
				+ "\n\tproduct: " + getInvProdid() + " Price: " + getInvPrice()
				+ " Quantity: " + getInvQty();
	}

	public boolean equals(InvoiceDetail detail) {
		if (this.isActive() != detail.active) {
			return false;
		}
		if (!this.getInvDetLine().equals(detail.getInvDetLine())) {
			return false;
		}
		if (!this.getInvMeasure().equals(detail.getInvMeasure())) {
			return false;
		}
		if (!this.getInvPrice().equals(detail.getInvPrice())) {
			return false;
		}
		if ((this.getInvProdid() != null)
				&& (!this.getInvProdid().equals(detail.getInvProdid()))) {
			return false;
		}
		if (!this.getInvQty().equals(detail.getInvQty())) {
			return false;
		}
		if (!this.getInvVat().equals(detail.invVat)) {
			return false;
		}
		if ((((this.getInvComments() == null) || (detail.getInvComments() == null)))
				|| ((!this.getInvComments().equals(detail.getInvComments())))) {
			return false;
		}
		return true;
	}
}
