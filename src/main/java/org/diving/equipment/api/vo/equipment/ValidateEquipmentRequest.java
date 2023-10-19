package org.diving.equipment.api.vo.equipment;

import java.io.Serializable;


public class ValidateEquipmentRequest implements Serializable{
	
	private String libelle;
	private String marque;
	private String taille;
	private String numeroSerie;
	private String tag;
	private String photoB64;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getPhotoB64() {
		return photoB64;
	}

	public void setPhotoB64(String photoB64) {
		this.photoB64 = photoB64;
	}

	public ValidateEquipmentRequest(Integer idEquipment, String libelle, String marque, String taille, String numeroSerie) {
		super();
		this.libelle = libelle;
		this.marque = marque;
		this.taille = taille;
		this.numeroSerie = numeroSerie;
	}

	public ValidateEquipmentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
