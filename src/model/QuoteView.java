package model;

public class QuoteView extends Quote {
    private String cusName;
    private String cusType;

    public QuoteView(Quote quote, String cusName, String cusType) {
        super(quote);
        this.cusName = cusName;
        this.cusType = cusType;
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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Offerte ");
        builder.append(super.toString());
        builder.append(" voor de ");
        builder.append(getCusType());
        builder.append(" ");
        builder.append(getCusName());
        return builder.toString();
    }
}
