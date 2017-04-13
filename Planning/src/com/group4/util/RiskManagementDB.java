package com.group4.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import com.java.mongo.RiskManagement;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;


public class RiskManagementDB {
	
	public static void addRiskManagement(){
		RiskManagement riskManagement = new RiskManagement("project 1", new ArrayList<String>(Arrays.asList("riesgo 1")), new ArrayList<Double>(Arrays.asList(0.60)), new ArrayList<Integer>(Arrays.asList(4)));
		
		RiskManagement b = new RiskManagement();
		//Object a = new Object();
		
		try {
			
		// PASO 1: Conexi�n al Server de MongoDB Pasandole el host y el puerto
			MongoClient mongoClient = new MongoClient("localhost", 27017);

		// PASO 2: Conexi�n a la base de datos
			DB db = mongoClient.getDB("TestPlanning");

		// PASO 3: Obtenemos una coleccion para trabajar con ella
			DBCollection collection = db.getCollection("TestRiskManagement");

		// PASO 4: CRUD (Create-Read-Update-Delete)

			// PASO 4.1: "CREATE" -> Metemos los objetos futbolistas (o documentos en Mongo) en la coleccion Futbolista
			collection.insert(riskManagement.toDBObjectProject());
			
			int numDocumentos = (int) collection.getCount();
			
			DBCursor cursor = collection.find();
			
			b = new RiskManagement((BasicDBObject) collection.findOne());
			System.out.print("//////////////////////////////////////////// >>> :" + b.getRisks());
			

			/*// PASO 4.4: "DELETE" -> Borramos todos los futbolistas que sean internacionales (internacional = true)
			DBObject findDoc = new BasicDBObject("internacional", true);
			collection.remove(findDoc);*/

		// PASO FINAL: Cerrar la conexion
			mongoClient.close();
			
			
		} catch (UnknownHostException ex) {
			System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
		}

	}

	
}

