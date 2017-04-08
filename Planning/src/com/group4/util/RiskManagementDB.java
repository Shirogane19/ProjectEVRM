package com.group4.util;

import com.java.mongo.Futbolista;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class RiskManagementDB {
	
	ConnectionMongoDB c = new ConnectionMongoDB();
	MongoClient m = (MongoClient) c.getConection();
	
	DB db = m.getDB("Futbol");
	
	DBCollection collection = db.getCollection("Futbolistas");
	Futbolista futbolista = new Futbolista((BasicDBObject) collection.findOne());
	
	public void printFutbolistas(){
		System.out.println("//////////////////////////////////>>> : " + futbolista.getNombre());
	}

	
}
