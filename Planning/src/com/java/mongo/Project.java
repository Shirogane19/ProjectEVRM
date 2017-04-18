package com.java.mongo;

import java.io.Serializable;

import com.mongodb.BasicDBObject;

public class Project implements Serializable{
	
	private String name;
	
	public Project(){
	}
	
	public Project(String name){
		this.name = name;
	}
	
	public Project(BasicDBObject dBOjectProject){
		this.name = dBOjectProject.getString("name");
		
	}
	
	public BasicDBObject toDBObjectProject() {
		// Creamos una instancia BasicDBObject
		BasicDBObject dBOjectProject = new BasicDBObject();
		dBOjectProject.append("name", this.getName());
		return dBOjectProject;
	}
	
	public String getName(){
		return name;
	}
	
	
}
