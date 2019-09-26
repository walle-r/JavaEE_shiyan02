package com.yuw.bean;

public class UserInfoBean {
	private Integer id;
	private String name;
	private String price;

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}
	
	private String price2;

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
		this.name = name == null ? null : name.trim();
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price == null ? null : price.trim();
	}
	@Override
	public String toString() {
		return "UserInfoBean{" + "id=" + id + ", "
				 + "name='" + name + '\'' + ", "
			 + "price='" + price + '\'' + ", price2='"
				  + price2 + '\'' + '}';
	}
}