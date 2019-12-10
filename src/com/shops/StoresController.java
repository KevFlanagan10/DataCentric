package com.shops;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class StoresController {
	ArrayList<Store> store;
	public ArrayList<Store> getStore() {
		return store;
	}

	public void setStore(ArrayList<Store> store) {
		this.store = store;
	}

	DAO dao;
	
	
	public StoresController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String loadStores() {
		System.out.println("In loadstores()");
		try {
			store = dao.loadStores();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
