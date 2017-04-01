package utilities;


/**
 * Enumeration class LesDagEnum - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum NumberEnum
{
    CUSTOMER("Cus", 0),
    ADDRESS("Add", 1),
    INVOICE("Inv", 2),
    CONTACT("Cnt", 3),
    CREDIT_NOTE("Crn", 4),
    PRODUCT("Prd", 5),
    PRICE("Prc", 6),
    QUOTE("Qte", 7),
    NUMBER("Nbr", 8)
    ;
    
    private String type;
	public String getType() {
		return type;
	}

	public int getCode() {
		return code;
	}

	private int code;

    private NumberEnum(String type, int code){
    	this.type = type;
    	this.code = code;
    }
}
