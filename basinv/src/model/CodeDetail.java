package model;

public class CodeDetail implements Business{
	private String idCode;
	private String codeDet;
	private String codeDesc;
	private boolean active;
	public CodeDetail(String idCode, String codeDet, String codeDesc,
			boolean active) {
		super();
		this.idCode = idCode;
		this.codeDet = codeDet;
		this.codeDesc = codeDesc;
		this.active = active;
	}
	public CodeDetail(CodeDetail code) {
		super();
		this.idCode = code.idCode;
		this.codeDet = code.codeDet;
		this.codeDesc = code.codeDesc;
		this.active = code.active;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public String getCodeDet() {
		return codeDet;
	}
	public void setCodeDet(String codeDet) {
		this.codeDet = codeDet;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean equals(CodeDetail detail){
		if(!this.idCode.equals(detail.idCode)){
			return false;
		}
		if(!this.codeDet.equals(detail.codeDet)){
			return false;
		}
		if(!this.codeDesc.equals(detail.codeDesc)){
			return false;
		}
		if(this.active != detail.active){
			return false;
		}
		return true;
	}
	
}
