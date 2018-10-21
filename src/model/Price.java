package model;

import utilities.Date;

import java.math.BigDecimal;

public class Price implements Business {
    private String idPrice;
    private String priProdid;
    private Date prifrom;
    private BigDecimal priUnit;
    private String priMeasure;
    private boolean active;

    public Price(String idPrice, String priProdid, Date prifrom,
                 BigDecimal priUnit, String priMeasure, boolean active) {
        super();
        this.idPrice = idPrice;
        this.priProdid = priProdid;
        this.prifrom = prifrom;
        this.priUnit = priUnit;
        this.priMeasure = priMeasure;
        this.active = active;
    }

    public Price(Price price) {
        this(price.idPrice, price.priProdid, price.prifrom, price.priUnit,
                price.priMeasure, price.active);
    }

    public String getIdPrice() {
        return idPrice;
    }

    public void setIdPrice(String idPrice) {
        this.idPrice = idPrice;
    }

    public String getPriProdid() {
        return priProdid;
    }

    public void setPriProdid(String priProdid) {
        this.priProdid = priProdid;
    }

    public Date getPrifrom() {
        return prifrom;
    }

    public void setPrifrom(Date prifrom) {
        this.prifrom = prifrom;
    }

    public BigDecimal getPriUnit() {
        return priUnit;
    }

    public void setPriUnit(BigDecimal priUnit) {
        this.priUnit = priUnit;
    }

    public String getPriMeasure() {
        return priMeasure;
    }

    public void setPriMeasure(String priMeasure) {
        this.priMeasure = priMeasure;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean equals(Price price) {
        if (this.active != price.active) {
            return false;
        }

        if (!this.prifrom.equals(price.prifrom)) {
            return false;
        }
        if (!this.priMeasure.equals(price.priMeasure)) {
            return false;
        }
        if (!this.priProdid.equals(price.priProdid)) {
            return false;
        }
        return this.priUnit.equals(price.priUnit);
    }

    public String toString() {
        return getPriUnit() + " per " + getPriMeasure() + " vanaf "
                + getPrifrom();
    }
}
