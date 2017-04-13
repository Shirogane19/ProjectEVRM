package com.java.mongo;

import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class RiskManagement {

	private ArrayList<String> risks;
	private ArrayList<Double> probability;
	private ArrayList<Integer> impact;
	private String project;
	
	public RiskManagement(){
	}
	
	public RiskManagement(String project, ArrayList<String> risks, ArrayList<Double> probability, ArrayList<Integer> impact){
		this.project = project;
		this.risks = risks;
		this.probability = probability;
		this.impact = impact;
	}
	
	public RiskManagement(BasicDBObject dBOjectRisk){
		this.project = dBOjectRisk.getString("project");
		BasicDBList riskList = (BasicDBList) dBOjectRisk.get("risks");
		this.risks = new ArrayList<String>();
		for (Object demarc : riskList) {
			this.risks.add(demarc.toString());
		}
		BasicDBList probabilityList = (BasicDBList) dBOjectRisk.get("probability");
		this.probability = new ArrayList<Double>();
		for (Object demarc : probabilityList) {
			this.risks.add(demarc.toString());
		}
		BasicDBList impactList = (BasicDBList) dBOjectRisk.get("impact");
		this.impact = new ArrayList<Integer>();
		for (Object demarc : impactList) {
			this.risks.add(demarc.toString());
		}
	}

	public BasicDBObject toDBObjectProject() {
		// Creamos una instancia BasicDBObject
		BasicDBObject dBOjectRisk = new BasicDBObject();
		dBOjectRisk.append("project", this.getProject());
		dBOjectRisk.append("risks", this.getRisks());
		dBOjectRisk.append("probability", this.getProbability());
		dBOjectRisk.append("impact", this.getImpact());
		return dBOjectRisk;
	}

	public String getProject(){
		return project;
	}
	
	public ArrayList<String> getRisks(){
		return risks;
	}
	
	public ArrayList<Double> getProbability(){
		return probability;
	}
	
	public ArrayList<Integer> getImpact() {
		return impact;
	}
	
}

