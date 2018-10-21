package model;

public class CodeHeader implements Business {
    private String idCode;
    private String codeDesc;
    private Integer codeLen;
    private boolean active;

    public CodeHeader(String idCode, String codeDesc, Integer codeLen,
                      boolean active) {
        super();
        this.idCode = idCode;
        this.codeDesc = codeDesc;
        this.codeLen = codeLen;
        this.active = active;
    }

    public CodeHeader(CodeHeader code) {
        super();
        this.idCode = code.idCode;
        this.codeDesc = code.codeDesc;
        this.codeLen = code.codeLen;
        this.active = code.active;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public Integer getCodeLen() {
        return codeLen;
    }

    public void setCodeLen(Integer codeLen) {
        this.codeLen = codeLen;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String toString() {
        return getIdCode() + ", " + getCodeDesc();
    }
}
