package br.com.restaurant.model;

@SuppressWarnings("serial")
public class ProductModel extends AbstractModel {
	
	private String name;
	private String description;
	private Float cost_price;
	private Float sale_price;
	private ProductTypeModel productType;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getCost_price() {
		return cost_price;
	}
	public void setCost_price(Float cost_price) {
		this.cost_price = cost_price;
	}
	public Float getSale_price() {
		return sale_price;
	}
	public void setSale_price(Float sale_price) {
		this.sale_price = sale_price;
	}
	public ProductTypeModel getProductType() {
		return productType;
	}
	public void setProductType(ProductTypeModel productType) {
		this.productType = productType;
	}
	
	
}
