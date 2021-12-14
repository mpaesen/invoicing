package utilities;

public enum CRUDOperationEnum {
    NEW("New", 0), UPDATE("Update", 1), REMOVE("Remove", 2);

    private final String type;
    private final int code;

    CRUDOperationEnum(String type, int code) {
        this.type = type;
        this.code = code;
    }

    private String getType() {
        return type;
    }

    private int getCode() {
        return code;
    }
}
