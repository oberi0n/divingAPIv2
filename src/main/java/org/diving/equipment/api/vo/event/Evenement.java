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
public class Evenement implements Serializable{
	@Id
	@ManyToOne
	private Utilisateur utilisateur;
	@Id
	@ManyToOne
	private Equipment equipment;
	
	/*@Id
	private int utilisateur_idUtilisateur;
	@Id
	private int equipment_idEquipment;*/

	private Date dateDebut;
	
	private Date dateFin;
	
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

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

/*	public int getUtilisateur_idUtilisateur() {
		return utilisateur_idUtilisateur;
	}

	public void setUtilisateur_idUtilisateur(int utilisateur_idUtilisateur) {
		this.utilisateur_idUtilisateur = utilisateur_idUtilisateur;
	}

	public int getEquipment_idEquipment() {
		return equipment_idEquipment;
	}

	public void setEquipment_idEquipment(int equipment_idEquipment) {
		this.equipment_idEquipment = equipment_idEquipment;
	}
*/

}
