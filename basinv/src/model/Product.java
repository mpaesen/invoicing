/**
 * 
 */
package model;

/**
 * @author bematpae
 * 
 */
public class Product implements Business {
	private String idProd;
	private String prodCode;
	private String prodDesc;
	private String prodCat;
	private String prodType;
	private boolean active;

	public Product(String idProd, String prodCode, String prodDesc,
			String prodCat, String prodType, boolean active) {
		super();
		this.idProd = idProd;
		this.prodCode = prodCode;
		this.prodDesc = prodDesc;
		this.prodCat = prodCat;
		this.prodType = prodType;
		this.active = active;
	}

	public Product(Product product) {
		this(product.idProd, product.prodCode, product.prodDesc,
				product.prodCat, product.prodType, product.active);
	}

	public String getIdProd() {
		return idProd;
	}

	public void setIdProd(String idProd) {
		this.idProd = idProd;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public String getProdCat() {
		return prodCat;
	}

	public void setProdCat(String prodCat) {
		this.prodCat = prodCat;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean equals(Product product) {
		if (!this.idProd.equals(product.idProd)) {
			return false;
		}
		if (!this.prodCat.equals(product.prodCat)) {
			return false;
		}
		if (!this.prodCode.equals(product.prodCode)) {
			return false;
		}
		if (!this.prodDesc.equals(product.prodDesc)) {
			return false;
		}
		if (!this.prodType.equals(product.prodType)) {
			return false;
		}
		if (this.active != product.active) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "Product " + getIdProd();
	}
}
