package com.java.mongo;

import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class EarnedValue {
	
	private String id;
	private String fase;
	private String pv;
	private String av;
	private String porcentajeEV;
	private String nonbreProyecto;
	
	public EarnedValue(String id, String fase, String pv, String av, String porcentajeEV, String nonbreProyecto) {
		this.id = id;
		this.fase = fase;
		this.pv = pv;
		this.av = av;
		this.porcentajeEV = porcentajeEV;
		this.nonbreProyecto = nonbreProyecto;
	}
	
	public EarnedValue(String fase, String pv, String av, String porcentajeEV, String nonbreProyecto) {
		this.fase = fase;
		this.pv = pv;
		this.av = av;
		this.porcentajeEV = porcentajeEV;
		this.nonbreProyecto = nonbreProyecto;
	}
	
	
	// Transformo un objecto que me da MongoDB a un Objecto Java
	public EarnedValue(BasicDBObject dBObjectEV) {
		this.id = dBObjectEV.getString("id");
		this.fase = dBObjectEV.getString("fase");
		this.pv = dBObjectEV.getString("pv");
		this.av = dBObjectEV.getString("av");
		this.porcentajeEV = dBObjectEV.getString("porcentajeEV");
		this.nonbreProyecto = dBObjectEV.getString("nonbreProyecto");
	}

	public BasicDBObject toDBObjectEarnedValue() {
		// Creamos una instancia BasicDBObject
		BasicDBObject dBObjectEV = new BasicDBObject();
		
		if(this.getId()!=null){
			dBObjectEV.append("id", this.getId());
		}
		dBObjectEV.append("fase", this.getFase());
		dBObjectEV.append("pv", this.getPv());
		dBObjectEV.append("av", this.getAv());
		dBObjectEV.append("porcentajeEV", this.getPorcentajeEV());
		dBObjectEV.append("nonbreProyecto", this.getNonbreProyecto());

		return dBObjectEV;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public String getPv() {
		return pv;
	}
	public void setPv(String pv) {
		this.pv = pv;
	}
	public String getAv() {
		return av;
	}
	public void setAv(String av) {
		this.av = av;
	}
	public String getPorcentajeEV() {
		return porcentajeEV;
	}
	public void setPorcentajeEV(String porcentajeEV) {
		this.porcentajeEV = porcentajeEV;
	}
	public String getNonbreProyecto() {
		return nonbreProyecto;
	}
	public void setNonbreProyecto(String nonbreProyecto) {
		this.nonbreProyecto = nonbreProyecto;
	}
	
	
	
	
	

}
