package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class Membre_Outils_Ids  implements Serializable{
private Long outil_id;
private Long auteur_id;

public Long getOutil_id() {
	return outil_id;
}
public void setOutil_id(Long publication_id) {
	this.outil_id = publication_id;
}
public Long getAuteur_id() {
	return auteur_id;
}
public void setAuteur_id(Long auteur_id) {
	this.auteur_id = auteur_id;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((auteur_id == null) ? 0 : auteur_id.hashCode());
	result = prime * result + ((outil_id== null) ? 0 : outil_id.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Membre_Outils_Ids other = (Membre_Outils_Ids) obj;
	if (auteur_id == null) {
		if (other.auteur_id != null)
			return false;
	} else if (!auteur_id.equals(other.auteur_id))
		return false;
	if (outil_id == null) {
		if (other.outil_id != null)
			return false;
	} else if (!outil_id.equals(other.outil_id))
		return false;
	return true;
}
public Membre_Outils_Ids(Long outil_id, Long auteur_id) {
	super();
	this.outil_id = outil_id;
	this.auteur_id = auteur_id;
}
public Membre_Outils_Ids() {
	super();
	// TODO Auto-generated constructor stub
}

}
