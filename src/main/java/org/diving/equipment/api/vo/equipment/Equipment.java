package org.diving.equipment.api.vo.equipment;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Equipment")
public class Equipment implements Serializable {
	@Id
	private Integer idEquipment;
	private String libelle;
	private String marque;
	private String taille;
	private String numeroSerie;
	private String tag;
	// db
	private Date dateAchat;
	private Date dateDernierEntretien;
	private String photoB64;

	private Date dateRequalif;
	private String fabriquant;
	private String numeroAffiche;
	private String statutTIV;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getIdEquipment() {
		return idEquipment;
	}

	public void setIdEquipment(Integer idEquipment) {
		this.idEquipment = idEquipment;
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

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public Date getDateDernierEntretien() {
		return dateDernierEntretien;
	}

	public void setDateDernierEntretien(Date dateDernierEntretien) {
		this.dateDernierEntretien = dateDernierEntretien;
	}

	public String getPhotoB64() {
		return photoB64;
	}

	public void setPhotoB64(String photoB64) {
		this.photoB64 = photoB64;
	}

	public Date getDateRequalif() {
		return dateRequalif;
	}

	public void setDateRequalif(Date dateRequalif) {
		this.dateRequalif = dateRequalif;
	}

	public String getFabriquant() {
		return fabriquant;
	}

	public void setFabriquant(String fabriquant) {
		this.fabriquant = fabriquant;
	}

	public String getNumeroAffiche() {
		return numeroAffiche;
	}

	public void setNumeroAffiche(String numeroAffiche) {
		this.numeroAffiche = numeroAffiche;
	}

	public String getStatutTIV() {
		return statutTIV;
	}

	public void setStatutTIV(String statutTIV) {
		this.statutTIV = statutTIV;
	}

	public Equipment(Integer idEquipment, String libelle, String marque, String taille, String numeroSerie) {
		super();
		this.idEquipment = idEquipment;
		this.libelle = libelle;
		this.marque = marque;
		this.taille = taille;
		this.numeroSerie = numeroSerie;
	}

	public Equipment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
