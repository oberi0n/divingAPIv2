package org.diving.equipment.provider;

import java.util.List;

import org.diving.equipment.api.vo.equipment.Equipment;
import org.diving.equipment.api.vo.event.Evenement;
import org.diving.equipment.api.vo.event.EvenementHistorique;
import org.diving.equipment.api.vo.utilisateur.Utilisateur;

public interface IEventProvider {
	
	public String createEvent(Evenement e);
	
	public void updateEvent(Evenement e);
	
	public Evenement searchEvent(Utilisateur utilisateur, Equipment equipment);
	
	public List<EvenementHistorique> getHistoriqueByEquipment(Equipment equipment);
	
}
