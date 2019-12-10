package com.shops;


import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product {
	
	int prodID;
	String desc;
	
	public int getProdID() {
		return prodID;
	}
	public void setProdID(int prodID) {
		this.prodID = prodID;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
