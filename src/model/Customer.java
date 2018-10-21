package model;

public class Customer implements Business {
    private String idCus;
    private String cusName;
    private String cusVat;
    private String cusPhone;
    private String cusMobile;
    private String cusFax;
    private String cusEMail;
    private String cusWebsite;
    private String cusType;
    private String cusClass;
    private String cusLang;
    private String cusCur;
    private String cusPay;
    private String cusActivity;
    private String cusAccount;
    private String cusInfo;

    private boolean active;

    public Customer(final String idCus, final String cusName, final String cusVat, final String cusPhone, final String cusMobile, final String cusFax, final String cusEMail, final String cusWebsite, final String cusType, final String cusClass, final String cusLang, final String cusCur,
                    final String cusPay, final String cusActivity, final String cusAccount, final String cusInfo, final boolean active) {
        super();
        this.idCus = idCus;
        this.cusName = cusName;
        this.cusVat = cusVat;
        this.cusPhone = cusPhone;
        this.cusMobile = cusMobile;
        this.cusFax = cusFax;
        this.cusEMail = cusEMail;
        this.cusWebsite = cusWebsite;
        this.cusType = cusType;
        this.cusClass = cusClass;
        this.cusLang = cusLang;
        this.cusCur = cusCur;
        this.cusPay = cusPay;
        this.cusActivity = cusActivity;
        this.cusAccount = cusAccount;
        this.cusInfo = cusInfo;
        this.active = active;
    }

    public Customer(final Customer customer) {
        this.idCus = customer.idCus;
        this.cusName = customer.cusName;
        this.cusVat = customer.cusVat;
        this.cusPhone = customer.cusPhone;
        this.cusMobile = customer.cusMobile;
        this.cusFax = customer.cusFax;
        this.cusEMail = customer.cusEMail;
        this.cusWebsite = customer.cusWebsite;
        this.cusType = customer.cusType;
        this.cusClass = customer.cusClass;
        this.cusLang = customer.cusLang;
        this.cusCur = customer.cusCur;
        this.cusPay = customer.cusPay;
        this.cusActivity = customer.cusActivity;
        this.cusAccount = customer.cusAccount;
        this.cusInfo = customer.cusInfo;
        this.active = customer.active;

    }

    public String getIdCus() {
        return idCus;
    }

    public void setIdCus(final String idCus) {
        this.idCus = idCus;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(final String cusName) {
        this.cusName = cusName;
    }

    public String getCusVat() {
        return cusVat;
    }

    public void setCusVat(final String cusVat) {
        this.cusVat = cusVat;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(final String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusMobile() {
        return cusMobile;
    }

    public void setCusMobile(final String cusMobile) {
        this.cusMobile = cusMobile;
    }

    public String getCusFax() {
        return cusFax;
    }

    public void setCusFax(final String cusFax) {
        this.cusFax = cusFax;
    }

    public String getCusEMail() {
        return cusEMail;
    }

    public void setCusEMail(final String cusEMail) {
        this.cusEMail = cusEMail;
    }

    public String getCusWebsite() {
        return cusWebsite;
    }

    public void setCusWebsite(final String cusWebsite) {
        this.cusWebsite = cusWebsite;
    }

    public String getCusType() {
        return cusType;
    }

    public void setCusType(final String cusType) {
        this.cusType = cusType;
    }

    public String getCusClass() {
        return cusClass;
    }

    public void setCusClass(final String cusClass) {
        this.cusClass = cusClass;
    }

    public String getCusLang() {
        return cusLang;
    }

    public void setCusLang(final String cusLang) {
        this.cusLang = cusLang;
    }

    public String getCusCur() {
        return cusCur;
    }

    public void setCusCur(final String cusCur) {
        this.cusCur = cusCur;
    }

    public String getCusPay() {
        return cusPay;
    }

    public void setCusPay(final String cusPay) {
        this.cusPay = cusPay;
    }

    public String getCusAccount() {
        return cusAccount;
    }

    public void setCusAccount(final String cusAccount) {
        this.cusAccount = cusAccount;
    }

    public String getCusActivity() {
        return cusActivity;
    }

    public void setCusActivity(final String cusActivity) {
        this.cusActivity = cusActivity;
    }

    public String getCusInfo() {
        return cusInfo;
    }

    public void setCusInfo(final String cusInfo) {
        this.cusInfo = cusInfo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("Customer:\t" + getIdCus() + "\n\t" + getCusName() + ", " + getCusVat() + ", " + getCusPhone() + ", " + getCusFax() + ", " + getCusMobile() + ", " + getCusEMail() + ", " + getCusLang() + ", " + getCusType() + ", " + getCusClass() + ", " + getCusCur() + ", " + getCusWebsite()
                + ", " + getCusPay() + ", " + isActive());
        return buffer.toString();
    }

    public boolean equals(final Customer customer) {
        if (!this.getCusName().equals(customer.getCusName())) {
            return false;
        }
        if (!this.getCusVat().equals(customer.getCusVat())) {
            return false;
        }

        if (!this.getCusPhone().equals(customer.getCusPhone())) {
            return false;
        }

        if (!this.getCusMobile().equals(customer.getCusMobile())) {
            return false;
        }

        if (!this.getCusFax().equals(customer.getCusFax())) {
            return false;
        }
        if (!this.getCusEMail().equals(customer.getCusEMail())) {
            return false;
        }

        if (!this.getCusClass().equals(customer.getCusClass())) {
            return false;
        }
        if (!this.getCusType().equals(customer.getCusType())) {
            return false;
        }
        if (!this.getCusCur().equals(customer.getCusCur())) {
            return false;
        }
        if (!this.getCusWebsite().equals(customer.getCusWebsite())) {
            return false;
        }

        if (!this.getCusLang().equals(customer.getCusLang())) {
            return false;
        }
        if (!this.getCusPay().equals(customer.getCusPay())) {
            return false;
        }
        if (!this.getCusAccount().equals(customer.getCusAccount())) {
            return false;
        }
        if (!this.getCusActivity().equals(customer.getCusActivity())) {
            return false;
        }

        return this.getCusInfo().equals(customer.getCusInfo());
    }

}
