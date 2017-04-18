package com.group4.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.java.mongo.RiskManagement;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class RiskManagementDB {
	
	MongoClient mongoClient;
	DB db;
	DBCollection RMcollection;
	
	//-----------------------------------------
	private void initMongoDB(){
		
		try{
			if(mongoClient == null){
				mongoClient = new MongoClient("localhost", 27017);
				db = mongoClient.getDB("TestProjectRM");
				//db.getCollection("TestRiskManagement2").drop(); //Borrar collection
				//System.out.println("Estoy iniciando mongo");
			}else{
				//System.out.println("ya esta iniciado mongo");
			}
			
		} catch (UnknownHostException ex) {
			System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
		}
		
	}
	//-----------------------------------------
	public Boolean insert(Map data){
			
		initMongoDB();
			
		RMcollection = db.getCollection("TestRiskManagement2");
			
		RiskManagement rm = new RiskManagement(data.get("numR").toString(), 
											data.get("description").toString(), 
											data.get("probability").toString(), 
											data.get("impact").toString(), 
											data.get("nameProject").toString()	);
			
			//System.out.println("insert: " + rm.getNumR() + rm.getDescription() + rm.getProbability() + rm.getImpact() + rm.getProject());
			//System.out.println("1: " + rm.toDBObjectRiskManagement());
			
		try{
			RMcollection.insert(rm.toDBObjectRiskManagement());
		} catch (Exception ex) {
			System.out.println("Exception al insertar al server de Mongo: " + ex.getMessage());
			return false;
		}
			
		return true;
		}
	//-----------------------------------------
	public Boolean save(Map data){
		
		initMongoDB();
		
		//System.out.println("save: " + data.get("numR").toString() + "save: "+data.get("nameProject"));
		
		RMcollection = db.getCollection("TestRiskManagement2");
		
		BasicDBObject query = new BasicDBObject();
		
		List<BasicDBObject> parts = new ArrayList<BasicDBObject>();
		
		parts.add(new BasicDBObject("numR", data.get("numR").toString()));
		parts.add(new BasicDBObject("nameProject", data.get("nameProject").toString()));
		query.put("$and", parts);
		
		//System.out.println("query: " + query);
		
		RiskManagement rm = new RiskManagement(	data.get("numR").toString(), 
												data.get("description").toString(), 
												data.get("probability").toString(), 
												data.get("impact").toString(), 
												data.get("nameProject").toString()	);

		
		try{
			RMcollection.update(query, rm.toDBObjectRiskManagement());
		} catch (Exception ex) {
			System.out.println("Exception al actualizar los datos: " + ex.getMessage());
			return false;
		}
		
		return true;
	}
	//-----------------------------------------
	public List<BasicDBObject> getCollection(Map data){
		
		initMongoDB();
		
		List<BasicDBObject> list = new ArrayList<BasicDBObject>();
		
		RMcollection = db.getCollection("TestRiskManagement2");
		
		DBObject query = new BasicDBObject( "nameProject", data.get("nameProject") );
		
		DBCursor cursor = RMcollection.find(query);
		
		RiskManagement rmObject = null;
		
		try {
			while (cursor.hasNext()) {
				rmObject = new RiskManagement((BasicDBObject) cursor.next());
				list.add(rmObject.toDBObjectRiskManagement());
			}
		} finally {
			cursor.close();
		}
		
		//System.out.println("List: " + list);
		
		return list;
	}
	
}

