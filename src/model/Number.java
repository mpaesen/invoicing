package model;

public class Number implements Business {
    private String nbrCategory;
    private Integer nbrYear;
    private String nbrStrValue;
    private String nbrLstValue;
    private boolean active;

    public Number(String nbrCategory, Integer nbrYear, String nbrStrValue,
                  String nbrLstValue, boolean active) {
        super();
        this.active = active;
        this.nbrCategory = nbrCategory.trim();
        this.nbrLstValue = nbrLstValue.trim();
        this.nbrStrValue = nbrStrValue.trim();
        this.nbrYear = nbrYear;
    }

    public Number(Number number) {
        super();
        this.active = number.active;
        this.nbrCategory = number.nbrCategory;
        this.nbrLstValue = number.nbrLstValue;
        this.nbrStrValue = number.nbrStrValue;
        this.nbrYear = number.nbrYear;
    }

    public String getNbrCategory() {
        return nbrCategory;
    }

    public void setNbrCategory(String nbrCategory) {
        this.nbrCategory = nbrCategory;
    }

    public Integer getNbrYear() {
        return nbrYear;
    }

    public void setNbrYear(Integer nbrYear) {
        this.nbrYear = nbrYear;
    }

    public String getNbrStrValue() {
        return nbrStrValue;
    }

    public void setNbrStrValue(String nbrStrValue) {
        this.nbrStrValue = nbrStrValue;
    }

    public String getNbrLstValue() {
        return nbrLstValue;
    }

    public void setNbrLstValue(String nbrLstValue) {
        this.nbrLstValue = nbrLstValue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String toString() {
        return String.valueOf(getNbrLstValue());
    }

    public boolean equals(Number number) {
        if (!nbrCategory.equals(number.getNbrCategory())) {
            return false;
        }
        if (!nbrYear.equals(number.getNbrYear())) {
            return false;
        }
        if (!nbrStrValue.equals(number.getNbrStrValue())) {
            return false;
        }
        return nbrLstValue.equals(number.getNbrLstValue());
    }
}
