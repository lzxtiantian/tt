package com.ymw.entity;

/**
 * 商品实体类
 * 
 * @author 杨梦伟
 *
 */
public class Product {
	private Integer id;
	private String name;
	private String url;
	private Double price;
	private String type;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Product(Integer id, String name, String url, Double price, String type) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.price = price;
		this.type = type;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", url=" + url + ", price=" + price + ", type=" + type + "]";
	}

}
