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

//stores=============================================================	
		public ArrayList<Store> loadStores() throws Exception {
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
//products============================================================
//load products
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
			p.setPid(myRs.getInt("pid"));
			p.setSid(myRs.getInt("sid"));
			p.setProdName(myRs.getString("prodName"));
			p.setPrice(myRs.getDouble("price"));
			products.add(p);
		}
		//number of products
		return products;
	}


//load products
	public ArrayList<StoreProducts> loadStoreProducts(int id) throws Exception {
	
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
	
		String sql = "select p.pid,p.prodName, p.price, s.id, s.name, s.founded from product p inner join";
	
		myStmt = myConn.createStatement();
	
		myRs = myStmt.executeQuery(sql);
		
		ArrayList<StoreProducts> storeProducts = new ArrayList<StoreProducts>();
	
		// process result set
		while (myRs.next()) {
			StoreProducts sp = new StoreProducts();
			sp.setPid(myRs.getInt("pid"));
			sp.setId(myRs.getInt("id"));
			sp.setProdName(myRs.getString("prodName"));
			sp.setPrice(myRs.getDouble("price"));
			sp.setName(myRs.getDouble("name"));
			sp.setFounded(myRs.getDouble("founded"));
			storeProducts.add(sp);
		}
		//number of store products
		return storeProducts;
	}

//==============================================================
//adding a store
	public void addStore(Store store) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into store (name, founded)values (?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, store.getName());
		myStmt.setString(2, store.getFounded());
		myStmt.execute();			
	}

//=============================================================
//adding a product 
	public void addProduct(Product product) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into product values (?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, product.getPid());
		//myStmt.setString(2, product.());
		myStmt.execute();			
	}

//==============================================================
//delete a store
	public void deleteStore(int id) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "delete from store where id = " + id;
		myStmt = myConn.prepareStatement(sql);
		myStmt.execute();			
	}
	
//==============================================================
//delete a product
	public void deleteProduct(int id) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
			
		myConn = mysqlDS.getConnection();
		String sql = "delete product where pid = " + id;
		myStmt = myConn.prepareStatement(sql);
		myStmt.execute();			
		}


	

}



