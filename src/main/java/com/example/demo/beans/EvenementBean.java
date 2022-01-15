package com.example.demo.beans;

import java.util.Date;

public class EvenementBean {
	Long id;
	String titre;
	Date date;
	String type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDateapparition() {
		return date;
	}
	public void setDateapparition(Date dateapparition) {
		this.date = dateapparition;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public EvenementBean(String titre, Date dateapparition, String type) {
		super();
		this.titre = titre;
		this.date = dateapparition;
		this.type = type;
	}
	public EvenementBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
