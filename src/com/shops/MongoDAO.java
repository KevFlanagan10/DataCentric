package com.shops;

import java.util.ArrayList;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
	
	String mongoDB = "storeHeadOfficeDB";
	String mongoCollection = "storeHeadOffice";
	
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public MongoDAO() throws Exception {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}
	
	public ArrayList<HeadOffice> loadHeadOffice() throws Exception{
		
		ArrayList<HeadOffice> headOffice = new ArrayList<HeadOffice>();
		FindIterable<Document> office = collection.find(); 
		for(Document d : office) {
			HeadOffice ofc = new HeadOffice();
			ofc.setId((int)(d.get("_id")));
			ofc.setLocation((String)(d.getString("location")));
			headOffice.add(ofc);
		}
		return headOffice;
	}
	
	public void addHeadOffice(HeadOffice head) throws Exception{
		Document d = new Document();
		d.append("_id", head.getId()).append("location", head.getLocation());
		collection.insertOne(d);
	}
}
