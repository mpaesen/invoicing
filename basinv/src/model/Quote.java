package model;

import utilities.Date;

public class Quote implements Business {
	private String idQuote;
	private String qteCusid;
	private String qteDlvAddid;
	private Date qteCrtdate;
	private String qteCrtUserid;
	private Date qteUpdDate;
	private String qteUpdUserid;
	private Date qteReqDlvDate;
	private String qteStatus;
	private String qteType;
	private boolean qteVat;
	private String qteHeaderComments;

	private boolean active;

	public Quote(String idQuote, String qteCusid, String qteDlvAddid,
			Date qteCrtdate, String qteCrtUserid, Date qteUpdDate,
			String qteUpdUserid, Date qteReqDlvDate, String qteStatus,
			String qteType, boolean qteVat, String qteHeaderComments,
			boolean active) {
		super();
		this.idQuote = idQuote;
		this.qteCusid = qteCusid;
		this.qteDlvAddid = qteDlvAddid;
		this.qteCrtdate = qteCrtdate;
		this.qteCrtUserid = qteCrtUserid;
		this.qteUpdDate = qteUpdDate;
		this.qteUpdUserid = qteUpdUserid;
		this.qteReqDlvDate = qteReqDlvDate;
		this.qteStatus = qteStatus;
		this.qteType = qteType;
		this.qteVat = qteVat;
		this.qteHeaderComments = qteHeaderComments;
		this.active = active;
	}

	/**
	 * Copy constructor
	 * 
	 * @param quote
	 */
	public Quote(Quote quote) {
		this(quote.idQuote, quote.qteCusid, quote.qteDlvAddid,
				quote.qteCrtdate, quote.qteCrtUserid, quote.qteUpdDate,
				quote.qteUpdUserid, quote.qteReqDlvDate, quote.qteStatus,
				quote.qteType, quote.qteVat, quote.qteHeaderComments,
				quote.active);
	}

	public boolean equals(Quote quote) {
		if (!this.idQuote.equals(quote.idQuote)) {
			return false;
		}
		if (!this.qteCusid.equals(quote.qteCusid)) {
			return false;
		}
		if (!this.qteDlvAddid.equals(quote.qteDlvAddid)) {
			return false;
		}
		if (!this.qteCrtdate.equals(quote.qteCrtdate)) {
			return false;
		}
		if (!this.qteCrtUserid.equals(quote.qteCrtUserid)) {
			return false;
		}
		if (!this.qteUpdDate.equals(quote.qteUpdDate)) {
			return false;
		}
		if (!this.qteUpdUserid.equals(quote.qteUpdUserid)) {
			return false;
		}
		if (!this.qteReqDlvDate.equals(quote.qteReqDlvDate)) {
			return false;
		}
		if (!this.qteStatus.equals(quote.qteStatus)) {
			return false;
		}
		if (!this.qteType.equals(quote.qteType)) {
			return false;
		}
		if (!this.qteVat == quote.qteVat) {
			return false;
		}
		if ((((this.getQteHeaderComments() == null) || (quote
				.getQteHeaderComments() == null)))
				|| ((!this.getQteHeaderComments().equals(
						quote.getQteHeaderComments())))) {
			return false;
		}
		if (!this.active == active) {
			return false;
		}
		return true;
	}

	public String getIdQuote() {
		return idQuote;
	}

	public void setIdQuote(String idQuote) {
		this.idQuote = idQuote;
	}

	public String getQteCusid() {
		return qteCusid;
	}

	public void setQteCusid(String qteCusid) {
		this.qteCusid = qteCusid;
	}

	public String getQteDlvAddid() {
		return qteDlvAddid;
	}

	public void setQteDlvAddid(String qteDlvAddid) {
		this.qteDlvAddid = qteDlvAddid;
	}

	public Date getQteCrtdate() {
		return qteCrtdate;
	}

	public void setQteCrtdate(Date qteCrtdate) {
		this.qteCrtdate = qteCrtdate;
	}

	public String getQteCrtUserid() {
		return qteCrtUserid;
	}

	public void setQteCrtUserid(String qteCrtUserid) {
		this.qteCrtUserid = qteCrtUserid;
	}

	public Date getQteUpdDate() {
		return qteUpdDate;
	}

	public void setQteUpdDate(Date qteUpdDate) {
		this.qteUpdDate = qteUpdDate;
	}

	public String getQteUpdUserid() {
		return qteUpdUserid;
	}

	public void setQteUpdUserid(String qteUpdUserid) {
		this.qteUpdUserid = qteUpdUserid;
	}

	public Date getQteReqDlvDate() {
		return qteReqDlvDate;
	}

	public String getQteReqDlvDate(String regEx) {
		return qteReqDlvDate.toString();
	}

	public void setQteReqDlvDate(Date qteReqDlvDate) {
		this.qteReqDlvDate = qteReqDlvDate;
	}

	public String getQteStatus() {
		return qteStatus;
	}

	public void setQteStatus(String qteStatus) {
		this.qteStatus = qteStatus;
	}

	public String getQteType() {
		return qteType;
	}

	public void setQteType(String qteType) {
		this.qteType = qteType;
	}

	public boolean isQteVat() {
		return qteVat;
	}

	public void setQteVat(boolean qteVat) {
		this.qteVat = qteVat;
	}

	public String getQteHeaderComments() {
		return qteHeaderComments;
	}

	public void setQteHeaderComments(String qteHeaderComments) {
		this.qteHeaderComments = qteHeaderComments;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {
		return "Quote " + getIdQuote() + " for Customer " + getQteCusid();
	}
}
