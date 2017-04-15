package com.group4.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import com.java.mongo.Futbolista;
import com.java.mongo.RiskManagement;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class RiskManagementDB {
	
	static public MongoClient mongoClient;
	static public DB db;
	static public DBCollection collection;
	
	public static void addRiskManagement(){
		
		try{
			mongoClient = new MongoClient("localhost", 27017);
			db = mongoClient.getDB("TestPlanning");
			collection = db.getCollection("TestRiskManagement");
			
			RiskManagement riskManagement = new RiskManagement("project 4", new ArrayList<String>(Arrays.asList("riesgo 1","riesgo2","riesgo3")),
																			new ArrayList<String>(Arrays.asList("a","b","c")), 
																			new ArrayList<Integer>(Arrays.asList(4,5,2)));
			
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!! > "+ riskManagement.getProbability());
			
			collection.insert(riskManagement.toDBObjectProject());
			int numDocumentos = (int) collection.getCount();
			System.out.println("\nRiskManagement >>> Numero de documentos: "+numDocumentos);

			// PASO FINAL: Cerrar la conexion
			mongoClient.close();
		}catch (UnknownHostException ex) {
			System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
		}	

	}
	
	public static BasicDBObject findByProject(String proj){
		try{
			mongoClient = new MongoClient("localhost", 27017);
			db = mongoClient.getDB("TestPlanning");
			collection = db.getCollection("TestRiskManagement");
				
			RiskManagement rm = new RiskManagement();
			DBCursor cursor = collection.find();
			
			System.out.println("\nBuscar RiskManagement por proyecto");
			
			DBObject query = new BasicDBObject("project", new BasicDBObject("$regex", proj)); //$regex sirve para asignar capacidad al documento de leer Strings
			cursor = collection.find(query);

			try {
				while (cursor.hasNext()) {
					//System.out.println(cursor.next().toString());
					rm = new RiskManagement((BasicDBObject) cursor.next());
				}
			} finally {
				cursor.close();
			}
				
			System.out.print("//////////////////////////////////////////// >>> :" + rm.getProbability());
			cursor.close();
			mongoClient.close();
			return rm.toDBObjectProject();
			}catch (UnknownHostException ex) {
				System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
				return null;
			}		
	}
	
	public static void addRisk(){
		
	}

	
}

