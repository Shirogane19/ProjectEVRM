package com.group4.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.java.mongo.EarnedValue;
import com.java.mongo.Futbolista;
import com.java.mongo.Project;
import com.java.mongo.RiskManagement;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ProjectDB {
	
	MongoClient mongoClient;
	DB db;
	DBCollection projectsCollection;
	
	//-----------------------------------------
	private void initMongoDB(){
		
		try{
			if(mongoClient == null){
				mongoClient = new MongoClient("localhost", 27017);
				db = mongoClient.getDB("TestProject");
//				db.getCollection("projects").drop();

				//System.out.println("EStoy iniciando mongo");
			}else{
				//System.out.println("ya esta iniciado mongo");
			}
			
		} catch (UnknownHostException ex) {
			System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
		}
		
	}
	
	
	public Boolean insert(Map data){
			
			initMongoDB();
			
			projectsCollection = db.getCollection("projects");
			
			Project pjt = new Project(data.get("name").toString());
			
	//		System.out.println("insert: " + ev.getId() + ev.getFase() + ev.getPv() + ev.getAv() + ev.getPorcentajeEV() + ev.getNonbreProyecto());
	//		System.out.println("1: " + ev.toDBObjectEarnedValue());
			
			try{
				projectsCollection.insert(pjt.toDBObjectProject());
			} catch (Exception ex) {
				System.out.println("Exception al insertar al server de Mongo: " + ex.getMessage());
				return false;
			}
			
			return true;
	}
	
	public List<BasicDBObject> getCollection(){
		
		initMongoDB();
		
		List<BasicDBObject> list = new ArrayList<BasicDBObject>();
		
		projectsCollection = db.getCollection("projects");
//		DBObject query = new BasicDBObject( "nonbreProyecto", data.get("nombreProyecto") );
		
		DBCursor cursor = projectsCollection.find();
		
		Project pjtObject = null;
		
		try {
			while (cursor.hasNext()) {
				pjtObject = new Project((BasicDBObject) cursor.next());
				list.add(pjtObject.toDBObjectProject());
			}
		} finally {
			cursor.close();
		}
		
		//System.out.println("List: " + list);
		
		return list;
	}

}
