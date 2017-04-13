package com.group4.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	public static void addProject(){
		Project project = new Project();

		/*futbolistas.add(new Futbolista("Iker", "Casillas", 33, new ArrayList<String>(Arrays.asList("Portero")), true));
		futbolistas.add(new Futbolista("Carles", "Puyol", 36, new ArrayList<String>(Arrays.asList("Central", "Lateral")), true));
		futbolistas.add(new Futbolista("Sergio", "Ramos", 28, new ArrayList<String>(Arrays.asList("Lateral", "Central")), true));
		futbolistas.add(new Futbolista("Andr�s", "Iniesta", 30, new ArrayList<String>(Arrays.asList("Centrocampista", "Delantero")), true));
		futbolistas.add(new Futbolista("Fernando", "Torres", 30, new ArrayList<String>(Arrays.asList("Delantero")), true));
		futbolistas.add(new Futbolista("Leo", " Baptistao", 22, new ArrayList<String>(Arrays.asList("Delantero")), false));*/
		/*project.add(new RiskManagement(new ArrayList<String>(Arrays.asList("riesgo1","riesgo2"))),
					new EarnedValue(21)
				);*/
		
		Project b = new Project();
		//Object a = new Object();
		
		try {
			
		// PASO 1: Conexi�n al Server de MongoDB Pasandole el host y el puerto
			MongoClient mongoClient = new MongoClient("localhost", 27017);

		// PASO 2: Conexi�n a la base de datos
			DB db = mongoClient.getDB("TestPlanning");

		// PASO 3: Obtenemos una coleccion para trabajar con ella
			DBCollection collection = db.getCollection("TestProjects");
			//b = new Project((BasicDBObject) collection.findOne());
			//b.equals(futbolista);
			//System.out.print("//////////////////////////////////////////// >>> :" + b);

		// PASO 4: CRUD (Create-Read-Update-Delete)

			// PASO 4.1: "CREATE" -> Metemos los objetos futbolistas (o documentos en Mongo) en la coleccion Futbolista
			collection.insert(project.toDBObjectProject());
			
			int numDocumentos = (int) collection.getCount();
			
			DBCursor cursor = collection.find();
			

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
