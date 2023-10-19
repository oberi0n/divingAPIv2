package org.diving.equipment.api.vo.event;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.diving.equipment.api.vo.equipment.Equipment;
import org.diving.equipment.api.vo.utilisateur.Utilisateur;

@Entity
@Table(name = "Evenement")
public class EvenementHistorique implements Serializable{
	@Id
	private String idEvenement;
	
	@Id
	@ManyToOne
	private Utilisateur utilisateur;

	private Date dateDebut;
	
	private Date dateFin;
	
	public String getIdEvenement() {
		return idEvenement;
	}

	public void setIdEvenement(String idEvenement) {
		this.idEvenement = idEvenement;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
