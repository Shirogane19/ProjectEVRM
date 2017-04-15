package com.java.mongo;

import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class RiskManagement {

	private String project;
	private ArrayList<String> risks;
	private ArrayList<String> probability;
	private ArrayList<Integer> impact;
	
	public RiskManagement(){
	}
	
	public RiskManagement(String project, ArrayList<String> risks, ArrayList<String> probability, ArrayList<Integer> impact){
		this.project = project;
		this.risks = risks;
		this.probability = probability;
		this.impact = impact;
	}
	
	public RiskManagement(BasicDBObject dBOjectRisk){
		this.project = dBOjectRisk.getString("project");
		BasicDBList riskList = (BasicDBList) dBOjectRisk.get("risks");
		System.out.println("RiskManagement(BasicDBObject dBOjectRisk)  : " + dBOjectRisk.get("risks"));
		this.risks = new ArrayList<String>();
		int a = 0;
		for (Object demarc1 : riskList) {
			System.out.println("Contador : " +a);
			this.risks.add(demarc1.toString());
			a++;
		}
		BasicDBList probabilityList = (BasicDBList) dBOjectRisk.get("probability");
		this.probability = new ArrayList<String>();
		for (Object demarc2 : probabilityList) {
			this.risks.add(demarc2.toString());
		}
		BasicDBList impactList = (BasicDBList) dBOjectRisk.get("impact");
		this.impact = new ArrayList<Integer>();
		for (Object demarc3 : impactList) {
			this.risks.add(demarc3.toString());
		}
	}

	public BasicDBObject toDBObjectProject() {
		// Creamos una instancia BasicDBObject
		BasicDBObject dBOjectRisk = new BasicDBObject();
		dBOjectRisk.append("project", this.getProject());
		dBOjectRisk.append("risks", this.getRisks());
		System.out.print("toDBObjectProject      : " + this.getRisks());
		dBOjectRisk.append("probability", this.getProbability());
		System.out.print("toDBObjectProject      : " + this.getProbability());
		dBOjectRisk.append("impact", this.getImpact());
		System.out.print("toDBObjectProject      : " + this.getImpact());
		return dBOjectRisk;
	}

	public String getProject(){
		return project;
	}
	
	public ArrayList<String> getRisks(){
		return risks;
	}
	
	public ArrayList<String> getProbability(){
		return probability;
	}
	
	public ArrayList<Integer> getImpact() {
		return impact;
	}
	
}

