package utilities;

public enum CRUDOperationEnum {
	NEW("New", 0),
	UPDATE("Update",1),
	REMOVE("Remove", 2);
	
	private String type;
	private int code;
	
	private String getType() {
		return type;
	}

	private int getCode() {
		return code;
	}

	private CRUDOperationEnum(String type, int code){
		this.type = type;
		this.code = code;
	}
}
