package com.group4.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.java.mongo.EarnedValue;
import com.java.mongo.Futbolista;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class EarnedValueDB {
	
	MongoClient mongoClient;
	DB db;
	DBCollection EVcollection;
	
	//-----------------------------------------
	private void initMongoDB(){
		
		try{
			if(mongoClient == null){
				mongoClient = new MongoClient("localhost", 27017);
				db = mongoClient.getDB("TestProjectEV");
				//db.getCollection("earnedValues").drop(); //Borrar collection
				//System.out.println("EStoy iniciando mongo");
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
		
		EVcollection = db.getCollection("earnedValues");
		
		EarnedValue ev = new EarnedValue(	data.get("fase").toString(), 
											data.get("pv").toString(), 
											data.get("av").toString(), 
											data.get("porcentajeEV").toString(), 
											data.get("nonbreProyecto").toString()	);
		
//		System.out.println("insert: " + ev.getId() + ev.getFase() + ev.getPv() + ev.getAv() + ev.getPorcentajeEV() + ev.getNonbreProyecto());
//		System.out.println("1: " + ev.toDBObjectEarnedValue());
		
		try{
			EVcollection.insert(ev.toDBObjectEarnedValue());
		} catch (Exception ex) {
			System.out.println("Exception al insertar al server de Mongo: " + ex.getMessage());
			return false;
		}
		
		return true;
	}
	//-----------------------------------------
	public Boolean save(Map data){
		
		initMongoDB();
		
		//System.out.println("save: " + data);
		
		EVcollection = db.getCollection("earnedValues");
		
		BasicDBObject query = new BasicDBObject();
		
		List<BasicDBObject> parts = new ArrayList<BasicDBObject>();
		
		parts.add(new BasicDBObject("fase", data.get("fase").toString()));
		parts.add(new BasicDBObject("nonbreProyecto", data.get("nonbreProyecto").toString()));
		query.put("$and", parts);
		
		//System.out.println("query: " + query);
		
		EarnedValue ev = new EarnedValue(	data.get("fase").toString(), 
											data.get("pv").toString(), 
											data.get("av").toString(), 
											data.get("porcentajeEV").toString(), 
											data.get("nonbreProyecto").toString()	);
		
		try{
			EVcollection.update(query, ev.toDBObjectEarnedValue());
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
		
		EVcollection = db.getCollection("earnedValues");
		
		DBObject query = new BasicDBObject( "nonbreProyecto", data.get("nombreProyecto") );
		
		DBCursor cursor = EVcollection.find(query);
		
		EarnedValue evObject = null;
		
		try {
			while (cursor.hasNext()) {
				evObject = new EarnedValue((BasicDBObject) cursor.next());
				list.add(evObject.toDBObjectEarnedValue());
			}
		} finally {
			cursor.close();
		}
		
		//System.out.println("List: " + list);
		
		return list;
	}
	//-----------------------------------------
}
