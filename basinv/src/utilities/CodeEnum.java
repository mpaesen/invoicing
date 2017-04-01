package utilities;


/**
 * Enumeration class LesDagEnum - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum CodeEnum
{
    CUSTOMER_TYPE("Ctp", 0),
    ADDRESS_TYPE("Atp", 1),
    CUSTOMER_CATEGORY("Cct", 2),
    CURRENCY("Cur", 3),
    INVOICE_STATUS("Ist", 4),
    INVOICE_TYPE("Itp", 5),
    PRODUCT_CATEGORY("Pct", 6),
    PREFIX("Pre", 7),
    PRODUCT_TYPE("Ptp", 8),
    QUOTE_STATUS("Qst", 9),
    QUOTE_TYPE("Qtp", 10),
    TITLE("Tit", 11),
    UNIT_OF_MEASURE("Ums",12),
    ZIPCODE("Zip", 13),
    LANGUAGE("Lng", 14),
    COUNTRY("Cnt", 15),
    NUMBER("Nbr", 16),
    PAYMENT("Pay", 17)
    ;
    
    private String type;
	public String getType() {
		return type;
	}

	public int getCode() {
		return code;
	}

	private int code;

    private CodeEnum(String type, int code){
    	this.type = type;
    	this.code = code;
    }
}
