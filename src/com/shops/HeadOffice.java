package com.shops;

import javax.annotation.ManagedBean;

@ManagedBean
public class HeadOffice {
	int id;
	String location;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}