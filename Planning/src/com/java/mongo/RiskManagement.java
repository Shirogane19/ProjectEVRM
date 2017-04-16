package com.java.mongo;

import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class RiskManagement {

	private String id;
	private String numR;
	private String description;
	private String probability;
	private String impact;
	private String nameProject;
	
	public RiskManagement(){
	}
	
	public RiskManagement(String id, String numR, String description, String probability, String impact, String nameProject){
		this.id = id;
		this.numR = numR;
		this.description = description;
		this.probability = probability;
		this.impact = impact;
		this.nameProject = nameProject;
	}
	
	public RiskManagement(String numR, String description, String probability, String impact, String nameProject){
		this.numR = numR;
		this.description = description;
		this.probability = probability;
		this.impact = impact;
		this.nameProject = nameProject;
	}
	
	public RiskManagement(BasicDBObject dBOjectRM){
		this.id = dBOjectRM.getString("id");
		this.numR = dBOjectRM.getString("numR");
		this.description = dBOjectRM.getString("description");
		this.probability = dBOjectRM.getString("probability");
		this.impact = dBOjectRM.getString("impact");
		this.nameProject = dBOjectRM.getString("nameProject");
	}

	public BasicDBObject toDBObjectRiskManagement() {
		// Creamos una instancia BasicDBObject
		BasicDBObject dBObjectRM = new BasicDBObject();
		
		if(this.getId()!=null){
			dBObjectRM.append("id", this.getId());
		}
		dBObjectRM.append("numR", this.getNumR());
		dBObjectRM.append("description", this.getDescription());
		dBObjectRM.append("probability", this.getProbability());
		dBObjectRM.append("impact", this.getImpact());
		dBObjectRM.append("nameProject", this.getProject());
		return dBObjectRM;
	}

	public String getId(){
		return id;
	}
	
	public String getNumR(){
		return numR;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getProbability(){
		return probability;
	}
	
	public String getImpact() {
		return impact;
	}
	
	public String getProject(){
		return nameProject;
	}
	
}

