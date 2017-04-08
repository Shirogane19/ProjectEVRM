package com.group4.util;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class ConnectionMongoDB {
	
	MongoClient mongoClient;
	
	public void createConnection(){
		try{
			mongoClient = new MongoClient("localhost", 27017);
		}catch (UnknownHostException ex) {
			System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
		}
		
	}
	
	public Object getConection(){
		return mongoClient;
	}

}
