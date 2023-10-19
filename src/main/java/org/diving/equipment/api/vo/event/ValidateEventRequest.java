package org.diving.equipment.api.vo.event;

public class ValidateEventRequest {
	
	private Integer idUtilisateur;
	
	private String tagEquipment;
	
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getTagEquipment() {
		return tagEquipment;
	}

	public void setTagEquipment(String tagEquipment) {
		this.tagEquipment = tagEquipment;
	}

	
}
