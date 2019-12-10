package com.shops;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.shops.Product;

public class DAO {
	DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
public ArrayList<Product> loadProducts() throws Exception {
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from product";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);
		
		ArrayList<Product> products = new ArrayList<Product>();

		// process result set
		while (myRs.next()) {
			Product p = new Product();
			p.setProdID(myRs.getInt("PRODID"));
			p.setDesc(myRs.getString("DESCRIP"));
			products.add(p);
		}
		//number of products
		return products;
	}
	
	public void addProduct(Product product) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into product values (?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, product.getProdID());
		myStmt.setString(2, product.getDesc());
		myStmt.execute();			
	}
	
	public void delete(int pid) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "delete from product where prodid = ?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, pid);
		myStmt.execute();			
	}


	
	// stores=============================================================

	
	public ArrayList<Store> loadStores() throws Exception {
		System.out.println("KevTest");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from store";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);
		
		ArrayList<Store> store = new ArrayList<>();

		// process result set
		while (myRs.next()) {
			Store s = new Store();
			s.setId(myRs.getInt("id"));
			s.setName(myRs.getString("name"));
			s.setFounded(myRs.getString("founded"));
			store.add(s);
		}
		//number of products
		return store;
	}
	
}



