package com.java.mongo;

import java.io.Serializable;

import com.mongodb.BasicDBObject;

public class Project implements Serializable{
	
	private String name;
	private RiskManagement risk;
	private EarnedValue ev;
	
	public Project(){
	}
	
	public Project(String name, RiskManagement risk, EarnedValue ev){
		this.name = name;
		this.risk = risk;
		this.ev = ev;
	}
	
	public Project(BasicDBObject dBOjectProject){
		this.name = dBOjectProject.getString("name");
		this.risk = (RiskManagement) dBOjectProject.get("risk");
		this.ev = (EarnedValue) dBOjectProject.get("ev");
		
	}
	
	public BasicDBObject toDBObjectProject() {
		// Creamos una instancia BasicDBObject
		BasicDBObject dBOjectProject = new BasicDBObject();
		dBOjectProject.append("name", this.getName());
		dBOjectProject.append("risk", this.getRisk());
		dBOjectProject.append("ev", this.getEarnedValue());
		return dBOjectProject;
	}
	
	public String getName(){
		return name;
	}
	
	public RiskManagement getRisk(){
		return risk;
	}
	
	public EarnedValue getEarnedValue(){
		return ev;
	}
	
	
}
