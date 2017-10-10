package com.cimri.challange.entity;

import javax.persistence.*;


@Entity
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

  
    private String productId;

    @Version
    private Integer version;
    
    private String title;
    
    private String brand;
    
    private String category;
    
    private String url;
    
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ProductEntity [productId=" + productId + ", version=" + version + ", title=" + title + ", brand="
				+ brand + ", category=" + category + ", url=" + url + "]";
	}

  
}
