package model;

import utilities.Date;

public class Invoice implements Business {
    private String idInvoice;
    private String invCusid;
    private String invAddid;
    private String invDlvAddid;

    private Date invCrtDate;
    private String invCrtUserid;
    private Date invUpdDate;
    private String invUpdUserid;
    private Date invDate;
    private Date invDueDate;
    private String invStatus;
    private String invType;
    private boolean invVat;
    private String invHeaderComments;
    private boolean active;

    public Invoice(String idInvoice, String invCusid, String invAddid,
                   String invDlvAddid, Date invCrtDate, String invCrtUserid,
                   Date invUpdDate, String invUpdUserid, Date invDate,
                   Date invDueDate, String invStatus, String invType, boolean invVat,
                   String invHeaderComments, boolean active) {
        super();
        this.idInvoice = idInvoice;
        this.invCusid = invCusid;
        this.invAddid = invAddid;
        this.invDlvAddid = invDlvAddid;
        this.invCrtDate = invCrtDate;
        this.invCrtUserid = invCrtUserid;
        this.invUpdDate = invUpdDate;
        this.invUpdUserid = invUpdUserid;
        this.invDate = invDate;
        this.invDueDate = invDueDate;
        this.invStatus = invStatus;
        this.invType = invType;
        this.invVat = invVat;
        this.invHeaderComments = invHeaderComments;
        this.active = active;
    }

    public Invoice(Invoice invoice) {
        this.idInvoice = invoice.idInvoice;
        this.invCusid = invoice.invCusid;
        this.invAddid = invoice.invAddid;
        this.invDlvAddid = invoice.invDlvAddid;
        this.invCrtDate = invoice.invCrtDate;
        this.invCrtUserid = invoice.invCrtUserid;
        this.invUpdDate = invoice.invUpdDate;
        this.invUpdUserid = invoice.invUpdUserid;
        this.invDate = invoice.invDate;
        this.invDueDate = invoice.invDueDate;
        this.invStatus = invoice.invStatus;
        this.invType = invoice.invType;
        this.invVat = invoice.invVat;
        this.invHeaderComments = invoice.invHeaderComments;
        this.active = invoice.active;

    }

    public String getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(String idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getInvCusid() {
        return invCusid;
    }

    public void setInvCusid(String invCusid) {
        this.invCusid = invCusid;
    }

    public String getInvAddid() {
        return invAddid;
    }

    public void setInvAddid(String invAddid) {
        this.invAddid = invAddid;
    }

    public String getInvDlvAddid() {
        return invDlvAddid;
    }

    public void setInvDlvAddid(String invDlvAddid) {
        this.invDlvAddid = invDlvAddid;
    }

    public Date getInvCrtDate() {
        return invCrtDate;
    }

    public void setInvCrtDate(Date invCrtDate) {
        this.invCrtDate = invCrtDate;
    }

    public String getInvCrtUserid() {
        return invCrtUserid;
    }

    public void setInvCrtUserid(String invCrtUserid) {
        this.invCrtUserid = invCrtUserid;
    }

    public Date getInvUpdDate() {
        return invUpdDate;
    }

    public void setInvUpdDate(Date invUpdDate) {
        this.invUpdDate = invUpdDate;
    }

    public String getInvUpdUserid() {
        return invUpdUserid;
    }

    public void setInvUpdUserid(String invUpdUserid) {
        this.invUpdUserid = invUpdUserid;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public String getInvDate(String regEx) {

        return invDate.toString();
    }

    public Date getInvDueDate() {
        return invDueDate;
    }

    public void setInvDueDate(Date invDueDate) {
        this.invDueDate = invDueDate;
    }

    public String getInvDueDate(String regEx) {

        return invDueDate.toString();
    }

    public String getInvStatus() {
        return invStatus;
    }

    public void setInvStatus(String invStatus) {
        this.invStatus = invStatus;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public boolean isInvVat() {
        return invVat;
    }

    public void setInvVat(boolean invVat) {
        this.invVat = invVat;
    }

    public String getInvHeaderComments() {
        return invHeaderComments;
    }

    public void setInvHeaderComments(String invHeaderComments) {
        this.invHeaderComments = invHeaderComments;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean equals(Invoice invoice) {
        if (!this.idInvoice.equals(invoice.idInvoice)) {
            return false;
        }
        if (!this.invCusid.equals(invoice.invCusid)) {
            return false;
        }
        if (!this.invAddid.equals(invoice.invAddid)) {
            return false;
        }
        if (!this.invDlvAddid.equals(invoice.invDlvAddid)) {
            return false;
        }
        if (!this.invCrtDate.equals(invoice.invCrtDate)) {
            return false;
        }
        if (!this.invCrtUserid.equals(invoice.invCrtUserid)) {
            return false;
        }
        if (!this.invUpdDate.equals(invoice.invUpdDate)) {
            return false;
        }
        if (!this.invUpdUserid.equals(invoice.invUpdUserid)) {
            return false;
        }
        if (!this.invDate.equals(invoice.invDate)) {
            return false;
        }
        if (!this.invDueDate.equals(invoice.invDueDate)) {
            return false;
        }
        if (!this.invStatus.equals(invoice.invStatus)) {
            return false;
        }
        if (!this.invType.equals(invoice.invType)) {
            return false;
        }
        if (this.invVat != invoice.invVat) {
            return false;
        }
        if ((((this.getInvHeaderComments() == null) || (invoice
                .getInvHeaderComments() == null)))
                || ((!this.getInvHeaderComments().equals(
                invoice.getInvHeaderComments())))) {
            return false;
        }
        return !this.active != active;
    }

    public String toString() {
        return "Invoice " + getIdInvoice() + " for Customer " + getInvCusid();
    }
}
