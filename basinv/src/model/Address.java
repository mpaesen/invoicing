package model;

import persistency.controller.CodeController;
import utilities.CodeEnum;

public class Address implements Business {
	private String idAddress;
	private String addRef;
	private String addStreet;
	private String addNumber;
	private String addBox;
	private String addZip;
	private String addCity;
	private String addCountry;

	private String addType;
	private boolean active;

	public Address(String idAddress, String addRef, String addStreet,
			String addNumber, String addBox, String addZip, String addCity,
			String addCountry, String addType, boolean active) {
		super();
		this.idAddress = idAddress;
		this.addRef = addRef;
		this.addStreet = addStreet;
		this.addNumber = addNumber;
		this.addBox = addBox;
		this.addZip = addZip;
		this.addCity = addCity;
		this.addType = addType;
		this.addCountry = addCountry;
		this.active = active;
	}

	public Address(Address address) {
		this.idAddress = address.idAddress;
		this.addRef = address.addRef;
		this.addStreet = address.addStreet;
		this.addNumber = address.addNumber;
		this.addBox = address.addBox;
		this.addZip = address.addZip;
		this.addCity = address.addCity;
		this.addType = address.addType;
		this.addCountry = address.addCountry;
		this.active = address.active;

	}

	public String getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(String idAddress) {
		this.idAddress = idAddress;
	}

	public String getAddRef() {
		return addRef;
	}

	public void setAddRef(String addRef) {
		this.addRef = addRef;
	}

	public String getAddStreet() {
		return addStreet;
	}

	public void setAddStreet(String addStreet) {
		this.addStreet = addStreet;
	}

	public String getAddNumber() {
		return addNumber;
	}

	public void setAddNumber(String addNumber) {
		this.addNumber = addNumber;
	}

	public String getAddBox() {
		return addBox;
	}

	public void setAddBox(String addBox) {
		this.addBox = addBox;
	}

	public String getAddZip() {
		return addZip;
	}

	public void setAddZip(String addZip) {
		this.addZip = addZip;
	}

	public String getAddCity() {
		return addCity;
	}

	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getAddCountry() {
		return addCountry;
	}

	public void setAddCountry(String addCountry) {
		this.addCountry = addCountry;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString(boolean t) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nAddress:\t" + getIdAddress()
				+ " for Customer/Contact:\t" + getAddRef() + "\n\t"
				+ getAddCity() + ", " + getAddZip() + ", " + getAddStreet()
				+ ", " + getAddNumber() + ", " + getAddBox() + ", "
				+ getAddType() + ", " + isActive());
		return buffer.toString();
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getAddStreet()
				+ ", "
				+ getAddNumber()
				+ " "
				+ getAddZip()
				+ " "
				+ getAddCity()
				+ " "
				+ CodeController.getOneCodeDetail(CodeEnum.COUNTRY.getType(),
						getAddCountry()).getCodeDesc());
		return buffer.toString();
	}

	public boolean equals(Address address) {
		if (!this.getAddRef().equals(address.getAddRef())) {
			return false;
		}
		if (!this.getAddStreet().equals(address.getAddStreet())) {
			return false;
		}
		if (!this.getAddNumber().equals(address.getAddNumber())) {
			return false;
		}
		if (!this.getAddBox().equals(address.getAddBox())) {
			return false;
		}
		if (!this.getAddZip().equals(address.getAddZip())) {
			return false;
		}
		if (!this.getAddCity().equals(address.getAddCity())) {
			return false;
		}
		if (!this.getAddCountry().equals(address.getAddCountry())) {
			return false;
		}
		if (!this.getAddType().equals(address.getAddType())) {
			return false;
		}
		return true;
	}
}
