package model;

public class Contact implements Business {
    private String idContact;
    private String idCus;
    private String conLName;
    private String conFName;
    private String conPhone;
    private String conMobile;
    private String conEMail;
    private String conTitle;
    private String conPref;
    private String conLang;
    private boolean active;

    public Contact(String idContact, String idCus, String conLName,
                   String conFName, String conPhone, String conMobile,
                   String conEMail, String conTitle, String conPref, String conLang,
                   boolean active) {
        super();
        this.idContact = idContact.trim();
        this.idCus = idCus.trim();
        this.conLName = conLName.trim();
        this.conFName = conFName.trim();
        this.conPhone = conPhone.trim();
        this.conMobile = conMobile.trim();
        this.conEMail = conEMail.trim();
        this.conTitle = conTitle.trim();
        this.conPref = conPref.trim();
        this.conLang = conLang.trim();
        this.active = active;
    }

    public Contact(Contact contact) {
        this.idContact = contact.idContact;
        this.idCus = contact.idCus;
        this.conLName = contact.conLName;
        this.conFName = contact.conFName;
        this.conPhone = contact.conPhone;
        this.conMobile = contact.conMobile;
        this.conEMail = contact.conEMail;
        this.conTitle = contact.conTitle;
        this.conPref = contact.conPref;
        this.conLang = contact.conLang;
        this.active = contact.active;

    }

    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;
    }

    public String getIdCus() {
        return idCus;
    }

    public void setIdCus(String idCus) {
        this.idCus = idCus;
    }

    public String getConLName() {
        return conLName;
    }

    public void setConLName(String conLName) {
        this.conLName = conLName;
    }

    public String getConFName() {
        return conFName;
    }

    public void setConFName(String conFName) {
        this.conFName = conFName;
    }

    public String getConPhone() {
        return conPhone;
    }

    public void setConPhone(String conPhone) {
        this.conPhone = conPhone;
    }

    public String getConMobile() {
        return conMobile;
    }

    public void setConMobile(String conMobile) {
        this.conMobile = conMobile;
    }

    public String getConEMail() {
        return conEMail;
    }

    public void setConEMail(String conEMail) {
        this.conEMail = conEMail;
    }

    public String getConTitle() {
        return conTitle;
    }

    public void setConTitle(String conTitle) {
        this.conTitle = conTitle;
    }

    public String getConPref() {
        return conPref;
    }

    public void setConPref(String conPref) {
        this.conPref = conPref;
    }

    public String getConLang() {
        return conLang;
    }

    public void setConLang(String conLang) {
        this.conLang = conLang;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\nContact:\t" + getIdContact() + " for Customer:\t"
                + getIdCus() + "\n\t" + getConLName() + ", " + getConFName()
                + ", " + getConPhone() + ", " + getConMobile() + ", "
                + getConEMail() + ", " + getConLang() + ", " + getConPref()
                + ", " + getConTitle() + ", " + isActive());
        return buffer.toString();
    }

    public boolean equals(Contact contact) {
        if (!this.getIdCus().equalsIgnoreCase(contact.getIdCus())) {
            return false;
        }
        if (!this.getConLName().equalsIgnoreCase(contact.getConLName())) {
            return false;
        }
        if (!this.getConFName().equalsIgnoreCase(contact.getConFName())) {
            return false;
        }
        if (!this.getConPhone().equalsIgnoreCase(contact.getConPhone())) {
            return false;
        }

        if (!this.getConMobile().equalsIgnoreCase(contact.getConMobile())) {
            return false;
        }
        if (!this.getConEMail().equalsIgnoreCase(contact.getConEMail())) {
            return false;
        }

        if (!this.getConTitle().equalsIgnoreCase(contact.getConTitle())) {
            return false;
        }
        if (!this.getConPref().equalsIgnoreCase(contact.getConPref())) {
            return false;
        }
        return this.getConLang().equalsIgnoreCase(contact.getConLang());
    }
}
