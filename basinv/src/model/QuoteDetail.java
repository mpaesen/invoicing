package model;

import java.math.BigDecimal;

public class QuoteDetail implements Business {
	private String idQuote;
	private Integer qteDetLine;
	private String qteProdid;
	private BigDecimal qteQty;
	private String qteMeasure;
	private BigDecimal qtePrice;
	private String qteComments;
	private boolean active;

	public QuoteDetail(String idQuote, Integer qteDetLine, String qteProdid,
			BigDecimal qteQty, String qteMeasure, BigDecimal qtePrice,
			String qteComments, boolean active) {
		super();
		this.idQuote = idQuote;
		this.qteDetLine = qteDetLine;
		this.qteProdid = qteProdid;
		this.qteQty = qteQty;
		this.qteMeasure = qteMeasure;
		this.qtePrice = qtePrice;
		this.qteComments = qteComments;
		this.active = active;
	}

	public QuoteDetail(QuoteDetail detail) {
		this.idQuote = detail.idQuote;
		this.qteDetLine = detail.qteDetLine;
		this.qteProdid = detail.qteProdid;
		this.qteQty = detail.qteQty;
		this.qteMeasure = detail.qteMeasure;
		this.qtePrice = detail.qtePrice;
		this.qteComments = detail.qteComments;
		this.active = detail.active;

	}

	public String getIdQuote() {
		return idQuote;
	}

	public void setIdQuote(String idQuote) {
		this.idQuote = idQuote;
	}

	public Integer getQteDetLine() {
		return qteDetLine;
	}

	public void setQteDetLine(Integer qteDetLine) {
		this.qteDetLine = qteDetLine;
	}

	public String getQteProdid() {
		return qteProdid;
	}

	public void setQteProdid(String qteProdid) {
		this.qteProdid = qteProdid;
	}

	public BigDecimal getQteQty() {
		return qteQty;
	}

	public void setQteQty(BigDecimal qteQty) {
		this.qteQty = qteQty;
	}

	public String getQteMeasure() {
		return qteMeasure;
	}

	public void setQteMeasure(String qteMeasure) {
		this.qteMeasure = qteMeasure;
	}

	public BigDecimal getQtePrice() {
		return qtePrice;
	}

	public void setQtePrice(BigDecimal qtePrice) {
		this.qtePrice = qtePrice;
	}

	public String getQteComments() {
		return qteComments;
	}

	public void setQteComments(String qteComments) {
		this.qteComments = qteComments;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {
		return "quote: " + getIdQuote() + " line: " + getQteDetLine()
				+ "\n\tproduct: " + getQteProdid() + " Price: " + getQtePrice()
				+ " Quantity: " + getQteQty();
	}

	public boolean equals(QuoteDetail detail) {
		if (this.isActive() != detail.active) {
			return false;
		}
		if (!this.getQteDetLine().equals(detail.getQteDetLine())) {
			return false;
		}
		if (!this.getQteMeasure().equals(detail.getQteMeasure())) {
			return false;
		}
		if (!this.getQtePrice().equals(detail.getQtePrice())) {
			return false;
		}
		if ((this.getQteProdid() != null)
				&& (!this.getQteProdid().equals(detail.getQteProdid()))) {
			return false;
		}
		if (!this.getQteQty().equals(detail.getQteQty())) {
			return false;
		}
		if ((((this.getQteComments() == null) || (detail.getQteComments() == null)))
				|| ((!this.getQteComments().equals(detail.getQteComments())))) {
			return false;
		}
		return true;
	}
}
